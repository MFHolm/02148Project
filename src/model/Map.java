package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.comp.Node;
import org.cmg.resp.knowledge.ActualTemplateField;
import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.knowledge.ts.TupleSpace;
import org.cmg.resp.topology.Self;
import org.cmg.resp.topology.VirtualPort;

public class Map {
	private Node sea;
	private Harbour harbour;
	private VirtualPort vp;
	private HashMap<String,Node> shipNodes;
	private TupleSpace coordinates;
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
		this.width = 25;
		this.heigth = 25;
		
	
	}
	
	public void addShip(BasicShip ship){
		Node newShipNode = new Node(ship.getId(), new TupleSpace());
		newShipNode.addPort(vp);
		newShipNode.addAgent(ship);
		shipNodes.put(ship.getId(),newShipNode);
		try {
			Tuple lock = coordinates.get(Templates.getLockTemp());
			coordinates.put(new Tuple(ship.getId(),ship.getType(),ship.getRow(),ship.getCol(),ship.getHeading()));
			coordinates.put(lock);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		newShipNode.start();
		
		
	}
	
	public void handleRequest(String shipId, String dockId) throws IllegalArgumentException {
		Node shipNode = shipNodes.get(shipId);
		if(shipNode.queryp(Templates.getReqSentTemp())==null){
			throw new IllegalArgumentException(shipId + " hasn't sent any request");
		} else {
			harbour.assignDock(shipId,dockId);
		}
	}
	
	private class SeaAgent extends Agent {
		public SeaAgent(String name) {
			super(name);
			
		}

		@Override
		protected void doRun() throws Exception {
			
			while(true){
				
				if(queryp(Templates.getDeclineReqTemp()) != null) { // Checks if we should decline request and remove ship
					Tuple t = get(Templates.getDeclineReqTemp(),Self.SELF);
					String shipId = t.getElementAt(String.class, 1);
					declineRequest(shipId);
				}
				
			}

			
		}
		
		/*
		 * Removes ship coordinates from coordinates tuple space
		 * and removes ship node
		 */
		public void declineRequest(String shipId) {
			
			try {
				Tuple lock = get(Templates.getLockTemp(), Self.SELF);
				get(Templates.getCoordTemp(shipId),Self.SELF);
				put(lock,Self.SELF);
				
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			shipNodes.remove(shipId);
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
	
	public String getSeaName(){
		return sea.getName();
	}
	
	public Harbour getHarbour(){
		return harbour;
	}
	
	public Node getSeaNode(){
		return sea;
	}

	public HashMap<String,Node> getShips() {
		return shipNodes;
	}
}