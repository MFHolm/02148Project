package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.comp.Node;
import org.cmg.resp.knowledge.ActualTemplateField;
import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.knowledge.ts.TupleSpace;
import org.cmg.resp.topology.PointToPoint;
import org.cmg.resp.topology.Self;
import org.cmg.resp.topology.VirtualPort;

public class Map {
	private Node sea;
	private Harbour harbour;
	private VirtualPort vp;
	private HashMap<String,PointToPoint> shipConnections;
	private List<Node> shipNodes;
	private int width;
	private int heigth;
	
	public Map(VirtualPort vp){

		this.sea = new Node("Sea",new TupleSpace());
		this.harbour = new Harbour(vp);
		this.shipNodes = new ArrayList<Node>();
		this.shipConnections = new HashMap<String,PointToPoint>();
		this.vp = vp;
		sea.addPort(this.vp);
		sea.addAgent(new SeaAgent("ShipController"));
		sea.put(new Tuple("lock"));
		sea.start();
		this.width = 10;
		this.heigth = 10;
		
	}
	
	public void addShip(BasicShip ship){
		sea.addAgent(ship);
		Node newShipNode = new Node(ship.getId(), new TupleSpace());
		newShipNode.addPort(vp);
		newShipNode.addAgent(ship);
		shipNodes.add(newShipNode);
		shipConnections.put(ship.getId(), new PointToPoint(ship.getId(),vp.getAddress()));
		sea.put(new Tuple(ship.getId(),ship.getRow(),ship.getCol()));
		newShipNode.start();
		
		
	}
	
	public String getSeaName(){
		return sea.getName();
	}
	
	private class SeaAgent extends Agent {
		public SeaAgent(String name) {
			super(name);
			
		}

		@Override
		protected void doRun() throws Exception {	
			

			
		}
	}

	//Returns all tuples in the tuple space of sea
	public ArrayList<Tuple> getShipPositions() {
		ArrayList<Tuple> shipPos = new ArrayList<>();
		ArrayList<Tuple> originals = new ArrayList<>();
		try {
			sea.get(new Template(new ActualTemplateField("lock")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Template t = new Template(
				new FormalTemplateField(String.class),
				new FormalTemplateField(ShipType.class),
				new FormalTemplateField(Integer.class),
				new FormalTemplateField(Integer.class),
				new FormalTemplateField(Heading.class));

		Tuple tuple;
		do {
			tuple = sea.getp(t);
			if (tuple != null) {
				originals.add(tuple);
				Tuple other = sea.getp(new Template(
						new ActualTemplateField(tuple.getElementAt(String.class, 0)),
						new FormalTemplateField(ShipType.class),
						new FormalTemplateField(Integer.class),
						new FormalTemplateField(Integer.class),
						new FormalTemplateField(Heading.class)));
				//If the ship occupies two positions, update position
				if (other != null) {
					originals.add(other);
					int row1 = tuple.getElementAt(int.class, 2);
					int row2 = other.getElementAt(int.class, 2);
					int col1 = tuple.getElementAt(int.class, 3);
					int col2 = other.getElementAt(int.class, 3);
					tuple = new Tuple(tuple.getElementAt(String.class, 0),
							tuple.getElementAt(String.class, 1),
							(row1 + row2) /2, (col1 + col2)/2,
							tuple.getElementAt(Heading.class, 4));
				}
				shipPos.add(tuple);
			}
		}
		while (t != null);
			
		for (Tuple s : originals) {
			sea.put(s);
		}
		sea.put(new Tuple("lock"));
		
		return shipPos;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.heigth;
	}
	public Node getSea() {
		return this.sea;
	}
}