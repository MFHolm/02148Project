package model;

import java.io.IOException;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.PointToPoint;
import org.cmg.resp.topology.Self;
import org.cmg.resp.topology.VirtualPort;

public abstract class BasicShip extends Agent {
	protected int row,col,time;
	protected Heading heading;
	protected String id;
	protected VirtualPort vp;
	protected PointToPoint mapConnection;
	protected PointToPoint harbourConnection;
	protected boolean docked;
	protected ShipSize size;
	protected ShipType shipType;
	
	
	public BasicShip(String shipId, String mapId, String harbourId, VirtualPort vp, int row, int col){
		super(shipId);
		this.id = shipId;
		this.vp = vp;
		this.mapConnection = new PointToPoint(mapId,vp.getAddress());
		this.harbourConnection = new PointToPoint(harbourId,vp.getAddress());
		this.row = row;	
		this.col = col;
		setDocked(false);
	}
	
	public abstract void move();
	
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
				row = t.getElementAt(Integer.class, 2);
				col = t.getElementAt(Integer.class, 3);
				put(new Tuple(id,row,col),mapConnection);
				put(u,mapConnection);
				setDocked(true);
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}
	
	@Override
	protected void doRun() throws Exception {


		makeRequest();
		
		while(true){
			if(!isDocked()){
				checkDockPermission();
				move();

			}
			
		}
	}
	
	protected int getRow() {
		return row;
	}
	
	protected int getCol() {
		return col;
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
