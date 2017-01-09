package model;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.topology.PointToPoint;
import org.cmg.resp.topology.VirtualPort;

public abstract class BasicShip extends Agent {
	protected int xPos,yPos;
	protected String id;
	protected VirtualPort vp;
	protected boolean idle;
	protected ShipSize size;
	
	
	
	public BasicShip(String id, VirtualPort vp, int xPos, int yPos){
		super(id);
		this.id = id;
		this.vp = vp;
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
