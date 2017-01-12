package model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.knowledge.ActualTemplateField;
import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.PointToPoint;
import org.cmg.resp.topology.Self;
import org.cmg.resp.topology.VirtualPort;

public abstract class BasicShip extends Agent {
	protected Coordinate coord;
	protected boolean inTransition = false;
	protected int time;
	protected Heading heading;
	protected String id;
	protected VirtualPort vp;
	protected PointToPoint mapConnection;
	protected PointToPoint harbourConnection;
	protected boolean docked;
	protected ShipSize size;
	protected ShipType shipType;
	protected MoveMonitor monitor;
	protected List<Coordinate> path;
	protected int pathIndex = 0;
	

	public BasicShip(String shipId, String mapId, String harbourId, VirtualPort vp, int row, int col){
		super(shipId);
		this.id = shipId;
		this.vp = vp;
		this.mapConnection = new PointToPoint(mapId,vp.getAddress());
		this.harbourConnection = new PointToPoint(harbourId,vp.getAddress());
		this.coord = new Coordinate(row,col);
		path = new LinkedList<Coordinate>();
		setDocked(false);
	}
	
	public boolean move(Coordinate nextCord){
		//if(heading = Heading.N)
		
		return false;
	}
	
	/*
	 *  Returns the amount of money this ship is willing to give
	 *  based on the amount of time it will stay
	 */
	protected abstract int getMoney(int time);
	
	/*
	 * Sends request to the harbour tuple space
	 * for permission to enter
	 */
	public void makeRequest() {		
		
		if (queryp(Templates.getReqSentTemp()) == null){
			try {
				put(new Tuple("req",id,shipType,time,getMoney(time)),harbourConnection);
				put(new Tuple("reqSent"),Self.SELF); // Sends this tuple so it knows that the request was sent
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		} 
	}
	
	protected void checkDockPermission(){
		Tuple t = getp(Templates.getDockAssignTemp());
		if(t != null){

			try {
				Tuple u = get(Templates.getLockTemp(),mapConnection);
				get(Templates.getCoordTemp(id),mapConnection);
				coord.row = t.getElementAt(Integer.class, 2);
				coord.col = t.getElementAt(Integer.class, 3);
				put(new Tuple(id,coord.row,coord.col),mapConnection);
				put(u,mapConnection);
				setDocked(true);
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}
	
	protected void turnRight(){
		switch(heading){
		case N:
			heading = Heading.NE;
			break;
		case NE:
			heading = Heading.E;
			break;
		case E:
			heading = Heading.SE;
			break;
		case SE:
			heading = Heading.S;
			break;
		case S:
			heading = Heading.SW;
			break;
		case SW:
			heading = Heading.W;
			break;
		case W:
			heading = Heading.NW;
			break;
		case NW:
			heading = Heading.N;
			break;
		default:
			break;
		}
	}
	
	protected void turnLeft(){
		switch(heading){
		case N:
			heading = Heading.NW;
			break;
		case NW:
			heading = Heading.W;
			break;
		case W:
			heading = Heading.SW;
			break;
		case SW:
			heading = Heading.S;
			break;
		case S:
			heading = Heading.SE;
			break;
		case SE:
			heading = Heading.E;
			break;
		case E:
			heading = Heading.NE;
			break;
		case NE:
			heading = Heading.N;
			break;
		default:
			break;
		}
	}
	
	protected void moveForward(Coordinate nextCoord){
		Tuple lock;
		try {
			lock = get(Templates.getLockTemp(),mapConnection);
			if(inTransition){
				inTransition = !inTransition;
				get(Templates.getCoordTemp(id),mapConnection);
				get(Templates.getCoordTemp(id),mapConnection);
				put(new Tuple(id,shipType,nextCoord.row,nextCoord.col,heading),mapConnection);
				coord = nextCoord;
			} else {
				inTransition = !inTransition;
				put(new Tuple(id,shipType,nextCoord.row,nextCoord.col,heading),mapConnection);
				
			}
			put(lock,mapConnection);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	protected void doRun() throws Exception {
		Coordinate nextCoord = path.get(0);

		makeRequest();
		
		while(true){
			if(!isDocked()){
				checkDockPermission();
				if(move(nextCoord)){
					pathIndex++;
					nextCoord = path.get(pathIndex);
				}
				monitor.moved();
			}
			
		}
	}
	
	public MoveMonitor getMonitor() {
		return monitor;
	}

	public void setMonitor(MoveMonitor monitor) {
		this.monitor = monitor;
	}
	
	protected int getRow() {
		return coord.row;
	}
	
	protected int getCol() {
		return coord.col;
	}
	
	protected boolean isDocked() {
		return docked;
	}

	protected void setDocked(boolean idle) {
		this.docked = idle;
	}

	protected String getId() {
		return id;
	}

	public Heading getHeading() {
		return heading;
	}

	public ShipType getType() {
		return shipType;
	}
}
