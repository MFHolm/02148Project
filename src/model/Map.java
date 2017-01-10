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
	private HashMap<String,Node> shipNodes;
	private TupleSpace coordinates;
	private Template lockTemp = new Template(new ActualTemplateField("lock"));
	private int width;
	private int heigth;
	
	public Map(VirtualPort vp){
		coordinates = new TupleSpace();
		this.sea = new Node("Sea",coordinates);
		this.harbour = new Harbour(vp);
		this.shipNodes = new HashMap<String,Node>();
		this.vp = vp;
		sea.addPort(this.vp);
		sea.addAgent(new SeaAgent("ShipController"));
		sea.put(new Tuple("lock"));
		sea.start();
		this.width = 30;
		this.heigth = 30;
		
	
	}
	
	public void addShip(BasicShip ship){
		Node newShipNode = new Node(ship.getId(), new TupleSpace());
		newShipNode.addPort(vp);
		newShipNode.addAgent(ship);
		shipNodes.put(ship.getId(),newShipNode);
		try {
			Tuple lock = coordinates.get(lockTemp);
			coordinates.put(new Tuple(ship.getId(),ship.getRow(),ship.getCol()));
			coordinates.put(lock);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		newShipNode.start();
		
		
	}
	
	public void handleRequest(String shipId, String dockId) throws IllegalArgumentException {
		Node shipNode = shipNodes.get(shipId);
		if(shipNode.queryp(new Template(new ActualTemplateField("reqSent")))==null){
			throw new IllegalArgumentException(shipId + " hasn't sent any request");
		} else {
			harbour.assignDock(shipId,dockId);
		}
	}
 
	
	public String getSeaName(){
		return sea.getName();
	}
	
	public Harbour getHarbour(){
		return harbour;
	}
	
	public Node getSeaNode(){
		return sea;
	}
	
	private class SeaAgent extends Agent {
		public SeaAgent(String name) {
			super(name);
			
		}

		@Override
		protected void doRun() throws Exception {	
			/*while(true){
				for(Tuple t : coordinates.getAll(lockTemp)){
					System.out.println(t);
					coordinates.put(t);
				}
			}*/

			
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
				int row1 = tuple.getElementAt(Integer.class, 2);
				int col1 = tuple.getElementAt(Integer.class, 3);
				String id = tuple.getElementAt(String.class, 0);
				ShipType type = tuple.getElementAt(ShipType.class, 1);
				Heading dir = tuple.getElementAt(Heading.class, 4);
				//If the ship occupies two positions, update position
				if (other != null) {
					originals.add(other);
					int row2 = other.getElementAt(Integer.class, 2);
					int col2 = other.getElementAt(Integer.class, 3);
					tuple = new Tuple(id,type,
							(row1 + row2) /2.0, (col1 + col2)/2.0,
							dir);
				}
				else {
					tuple = new Tuple(id, type, (double) row1, (double)col1, dir);
				}
				shipPos.add(tuple);
			}
		}
		while (tuple != null);
			
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