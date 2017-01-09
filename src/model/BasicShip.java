package model;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.topology.PointToPoint;
import org.cmg.resp.topology.VirtualPort;

public abstract class BasicShip extends Agent {
	protected int xPos,yPos;
	protected Heading heading;
	protected String id;
	protected VirtualPort vp;
	protected PointToPoint mapConnection;
	protected boolean idle;
	protected ShipSize size;
	
	
	
	public BasicShip(String shipId, String mapId, VirtualPort vp, int xPos, int yPos){
		super(shipId);
		this.id = shipId;
		this.vp = vp;
		this.mapConnection = new PointToPoint(mapId,vp.getAddress());
		this.xPos = xPos;	
		this.yPos = yPos;
	}
	
	public abstract void move();
	
	public abstract void makeRequest();
	
	protected int getxPos() {
		return xPos;
	}
	
	protected int getyPos() {
		return yPos;
	}
	
	protected boolean isIdle() {
		return idle;
	}

	protected void setIdle(boolean idle) {
		this.idle = idle;
	}

	protected String getId() {
		return id;
	}

}
