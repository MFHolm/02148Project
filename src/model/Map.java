package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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
	private int width;
	private int heigth;
	private MoveMonitor monitor;
	private ArrayList<LinkedList<Coordinate>> paths;
	private ArrayList<LinkedList<Coordinate>> startPaths;
	
	public Map(VirtualPort vp, int mapHeight, int mapWidth) {
		coordinates = new TupleSpace();
		this.sea = new Node("sea",coordinates);
		this.harbour = new Harbour(vp);
		this.shipNodes = new HashMap<String,Node>();
		this.vp = vp;
		sea.addPort(vp);
		sea.addAgent(new SeaAgent("ShipController"));
		sea.put(new Tuple("lock"));
		this.width = mapWidth;
		this.heigth = mapHeight;
		this.monitor = new MoveMonitor();
		this.paths = new ArrayList<LinkedList<Coordinate>>();
		
		createPaths();
		initCoordinates();
		sea.start();
	}
	
	private void initCoordinates() {
		for(int i = 0; i < getHeight(); i++){
			for(int j = 0; j < getWidth(); j++){
				sea.put(new Tuple("free",i,j));
			}
		}
	}
	private void createPaths() {
		LinkedList<Coordinate> path = new LinkedList<Coordinate>();
		path.add(new Coordinate(22,12));
		path.add(new Coordinate(21,12));
		path.add(new Coordinate(20,12));
		path.add(new Coordinate(19,12));
		path.add(new Coordinate(18,12));
		path.add(new Coordinate(17,12));
		path.add(new Coordinate(16,12));
		path.add(new Coordinate(15,12));
		path.add(new Coordinate(13,12));
		path.add(new Coordinate(12,12));
		path.add(new Coordinate(12,11));
		path.add(new Coordinate(12,10));
		path.add(new Coordinate(12,9));
		path.add(new Coordinate(12,8));
		path.add(new Coordinate(12,7));
		path.add(new Coordinate(13,7));
		path.add(new Coordinate(14,7));
		path.add(new Coordinate(15,7));
		path.add(new Coordinate(16,7));
		path.add(new Coordinate(16,6));
		path.add(new Coordinate(16,5));
		path.add(new Coordinate(15,5));
		path.add(new Coordinate(14,5));
		path.add(new Coordinate(13,5));
		path.add(new Coordinate(13,4));
		path.add(new Coordinate(13,3));
		path.add(new Coordinate(14,3));
		path.add(new Coordinate(15,3));
		path.add(new Coordinate(16,3));
		path.add(new Coordinate(17,3));
		path.add(new Coordinate(18,3));
		path.add(new Coordinate(19,3));
		path.add(new Coordinate(20,3));
		path.add(new Coordinate(21,3));
		path.add(new Coordinate(22,3));
		path.add(new Coordinate(22,4));
		path.add(new Coordinate(22,5));
		path.add(new Coordinate(22,6));
		path.add(new Coordinate(22,7));
		path.add(new Coordinate(22,8));
		path.add(new Coordinate(22,9));
		path.add(new Coordinate(22,10));
		path.add(new Coordinate(22,11));
		
		paths.add(path);
	}

	public void addShip(BasicShip ship){
		ship.setMonitor(monitor);
		ship.setCoordinates(coordinates);
		//ship.setPath(path);
		Node newShipNode = new Node(ship.getId(), new TupleSpace());
		newShipNode.addPort(vp);
		newShipNode.addAgent(ship);
		shipNodes.put(ship.getId(),newShipNode);
		
		try {
			sea.get(Templates.getFreeCoordTemp(ship.getRow(), ship.getCol()));
			sea.put(new Tuple(ship.getId(),ship.getType(),ship.getRow(),ship.getCol(),ship.getHeading()));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		monitor.setWaitingFor(shipNodes.size());
		newShipNode.start();
		
		//Delay the thread until the request has been sent
		try {
			Tuple t = newShipNode.get(Templates.getReqSentTemp());
			newShipNode.put(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void handleRequest(String shipId, String dockId) throws IllegalArgumentException {
		Node shipNode = shipNodes.get(shipId);
		if(shipNode.queryp(Templates.getReqSentTemp())==null){
			throw new IllegalArgumentException(shipId + " hasn't sent any request");
		} else {
			harbour.assignDock(shipId,dockId);
			try {
				//Delay until ship has been assigned
				Tuple t = shipNodes.get(shipId).get(Templates.getDockAssignTemp());
				shipNodes.get(shipId).put(t);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
					System.out.println("remove " + shipId);
					declineRequest(shipId);
					System.out.println("ship removed");
				}
				
			}

			
		}
		
		/*
		 * Removes ship coordinates from coordinates tuple space
		 * and removes ship node
		 */
		public void declineRequest(String shipId) {
			
			try {
				Tuple pos1 = get(Templates.getCoordTemp(shipId),Self.SELF);
				//Two calls to get because ship has two positions in dock
				Tuple pos2 = get(Templates.getCoordTemp(shipId),Self.SELF);
				
				put(new Tuple("free",pos1.getElementAt(Integer.class, 2), pos1.getElementAt(Integer.class, 3)),Self.SELF);
				put(new Tuple("free",pos2.getElementAt(Integer.class, 2), pos2.getElementAt(Integer.class, 3)),Self.SELF);
				
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			shipNodes.remove(shipId);
			//monitor.setWaitingFor(shipNodes.size());
		}
	}


	//Returns all tuples in the tuple space of sea
	public ArrayList<Tuple> getShipPositions() {
		monitor.setWaitingFor(shipNodes.size());
		monitor.waitingForShips();
		ArrayList<Tuple> shipPos = new ArrayList<>();
		ArrayList<Tuple> originals = new ArrayList<>();
		
		
		
		Template t = Templates.getCoordTemp();
		Tuple tuple;
		do {
			tuple = sea.getp(t);
			if (tuple != null) {
				originals.add(tuple);
				Tuple other = sea.getp(Templates.getCoordTemp(tuple.getElementAt(String.class, 0)));
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
		
		//sea.put(new Tuple("lock"));
		
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

	public MoveMonitor getMonitor() {
		return monitor;
	}
}