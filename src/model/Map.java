package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.cmg.jresp.behaviour.Agent;
import org.cmg.jresp.comp.Node;
import org.cmg.jresp.knowledge.Template;
import org.cmg.jresp.knowledge.Tuple;
import org.cmg.jresp.knowledge.ts.TupleSpace;
import org.cmg.jresp.topology.Self;
import org.cmg.jresp.topology.VirtualPort;

public class Map {
	private Node sea;
	private Harbour harbour;
	private VirtualPort vp;
	private HashMap<String,Node> shipNodes;
	private TupleSpace coordinates;
	private int width;
	private int heigth;
	private BarrierMonitor barrier;
	private ArrayList<LinkedList<Coordinate>> paths;
	
	public Map(VirtualPort vp, int mapHeight, int mapWidth) {
		coordinates = new TupleSpace();
		this.sea = new Node("sea",coordinates);
		this.harbour = new Harbour(vp);
		this.shipNodes = new HashMap<String,Node>();
		this.vp = vp;
		sea.addPort(vp);
		sea.addAgent(new SeaAgent("ShipController"));
		this.width = mapWidth;
		this.heigth = mapHeight;
		this.barrier = new BarrierMonitor();
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
		path.add(new Coordinate(14,12));
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
		
		path = new LinkedList<Coordinate>();
		path.add(new Coordinate(23,12));
		paths.add(path);
		
		
		path = new LinkedList<Coordinate>();
		path.add(new Coordinate(12,2));
		path.add(new Coordinate(12,3));
		path.add(new Coordinate(12,4));
		path.add(new Coordinate(12,5));
		path.add(new Coordinate(12,6));
		path.add(new Coordinate(13,6));
		path.add(new Coordinate(14,6));
		path.add(new Coordinate(15,6));
		path.add(new Coordinate(15,7));
		path.add(new Coordinate(15,8));
		path.add(new Coordinate(15,9));
		path.add(new Coordinate(15,10));
		path.add(new Coordinate(15,11));
		path.add(new Coordinate(14,11));
		path.add(new Coordinate(13,11));
		path.add(new Coordinate(12,11));
		path.add(new Coordinate(11,11));
		path.add(new Coordinate(10,11));
		path.add(new Coordinate(9,11));
		path.add(new Coordinate(8,11));
		path.add(new Coordinate(7,11));
		path.add(new Coordinate(6,11));
		path.add(new Coordinate(5,11));
		path.add(new Coordinate(4,11));
		path.add(new Coordinate(3,11));
		path.add(new Coordinate(3,10));
		path.add(new Coordinate(3,9));
		path.add(new Coordinate(3,8));
		path.add(new Coordinate(3,7));
		path.add(new Coordinate(3,6));
		path.add(new Coordinate(3,5));
		path.add(new Coordinate(3,4));
		path.add(new Coordinate(3,3));
		path.add(new Coordinate(3,2));
		path.add(new Coordinate(4,2));
		path.add(new Coordinate(5,2));
		path.add(new Coordinate(6,2));
		path.add(new Coordinate(7,2));
		path.add(new Coordinate(8,2));
		path.add(new Coordinate(9,2));
		path.add(new Coordinate(10,2));
		path.add(new Coordinate(11,2));
		paths.add(path);
		
		path = new LinkedList<Coordinate>();
		path.add(new Coordinate(12,1));
		paths.add(path);
		
		path = new LinkedList<Coordinate>();
		path.add(new Coordinate(2,17));
		path.add(new Coordinate(3,17));
		path.add(new Coordinate(4,17));
		path.add(new Coordinate(5,17));
		path.add(new Coordinate(6,17));
		path.add(new Coordinate(7,17));
		path.add(new Coordinate(7,16));
		path.add(new Coordinate(7,15));
		path.add(new Coordinate(7,14));
		path.add(new Coordinate(7,13));
		path.add(new Coordinate(7,12));
		path.add(new Coordinate(7,11));
		path.add(new Coordinate(7,10));
		path.add(new Coordinate(7,9));
		path.add(new Coordinate(7,8));
		path.add(new Coordinate(7,7));
		path.add(new Coordinate(7,6));
		path.add(new Coordinate(7,5));
		path.add(new Coordinate(7,4));
		path.add(new Coordinate(7,3));
		path.add(new Coordinate(7,2));
		path.add(new Coordinate(7,1));
		path.add(new Coordinate(6,1));
		path.add(new Coordinate(5,1));
		path.add(new Coordinate(4,1));
		path.add(new Coordinate(3,1));
		path.add(new Coordinate(2,1));
		path.add(new Coordinate(2,2));
		path.add(new Coordinate(2,3));
		path.add(new Coordinate(2,4));
		path.add(new Coordinate(2,5));
		path.add(new Coordinate(2,6));
		path.add(new Coordinate(2,7));
		path.add(new Coordinate(2,8));
		path.add(new Coordinate(2,9));
		path.add(new Coordinate(2,10));
		path.add(new Coordinate(2,11));
		path.add(new Coordinate(2,12));
		path.add(new Coordinate(2,13));
		path.add(new Coordinate(2,14));
		path.add(new Coordinate(2,15));
		path.add(new Coordinate(2,16));
		paths.add(path);
		
		path = new LinkedList<Coordinate>();
		path.add(new Coordinate(1,17));
		paths.add(path);
		
		
	}
	public void assignPath(BasicShip ship, int i) {
		switch (i) {
			case 1: 
			ship.setPath(paths.get(0));
			ship.setStartPath(paths.get(1));
			break;
			case 2: 
				ship.setPath(paths.get(2));
				ship.setStartPath(paths.get(3));
				break;
			case 3: 
				ship.setPath(paths.get(4));
				ship.setStartPath(paths.get(5));
				break;
			default : 
				System.out.println("Path not found");
		}
	}

	public void addShip(BasicShip ship){
		ship.setMonitor(barrier);
		ship.setCoordinates(coordinates);
		//ship.setPath(path);
		Node newShipNode = new Node(ship.getId(), new TupleSpace());
		newShipNode.addPort(vp);
		newShipNode.addAgent(ship);
		shipNodes.put(ship.getId(),newShipNode);
		
		try {
			sea.get(Templates.getFreeCoordTemp(ship.getRow(), ship.getCol()));
			sea.put(new Tuple(ship.getId(),ship.getType(),ship.getRow(),ship.getCol(),ship.getHeading()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		barrier.setWaitingFor(shipNodes.size());
		newShipNode.start();
		
		//Delay the thread until the request has been sent
		try {
			Tuple t = newShipNode.get(Templates.getReqAckTemp());
			newShipNode.put(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void handleRequest(String shipId, String dockId) throws IllegalArgumentException {
		Node shipNode = shipNodes.get(shipId);
		if(shipNode.queryp(Templates.getReqAckTemp())==null){
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
	
	public void removeShipFromSea(String shipId) {
		getShips().get(shipId).put(new Tuple("decline"));
		try {
			getShips().get(shipId).get(Templates.getRemovedTemp());
			
			shipNodes.get(shipId).stop();
			shipNodes.remove(shipId);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class SeaAgent extends Agent {
		public SeaAgent(String name) {
			super(name);
			
		}

		@Override
		protected void doRun() throws Exception {
			
			while(true){
				Tuple t = getp(Templates.getRemoveTemp());
				if(t != null) { // Checks if we should decline request and remove ship
					String shipId = t.getElementAt(String.class, 1);
					removeShip(shipId);
				}
				Tuple x = getp(Templates.getDeclinedShipTemp());
				if(x != null) { // Checks if we should decline request and remove ship
					String shipId = x.getElementAt(String.class, 1);
					removeShipFromSea(shipId);
				}
				
			}

			
		}
		
		/*
		 * Removes ship coordinates from coordinates tuple space
		 * and removes ship node
		 */
		public void removeShip(String shipId) {
			
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
			
			shipNodes.get(shipId).stop();
			shipNodes.remove(shipId);
			
			//monitor.setWaitingFor(shipNodes.size());
		}
	}


	//Returns all tuples in the tuple space of sea
	public ArrayList<Tuple> getShipPositions() {
		barrier.setWaitingFor(shipNodes.size());
		barrier.waitingForShips();
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

	public BarrierMonitor getMonitor() {
		return barrier;
	}
}