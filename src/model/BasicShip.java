package model;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.topology.PointToPoint;
import org.cmg.resp.topology.VirtualPort;

public abstract class BasicShip extends Agent {
	protected int row,col;
	protected Heading heading;
	protected String id;
	protected VirtualPort vp;
	protected PointToPoint mapConnection;
	protected PointToPoint harbourConnection;
	protected boolean idle;
	protected ShipSize size;
	
	
	
	public BasicShip(String shipId, String mapId, String harbourId, VirtualPort vp, int row, int col){
		super(shipId);
		this.id = shipId;
		this.vp = vp;
		this.mapConnection = new PointToPoint(mapId,vp.getAddress());
		this.harbourConnection = new PointToPoint(harbourId,vp.getAddress());
		this.row = row;	
		this.col = col;
	}
	
	public abstract void move();
	
	public abstract void makeRequest();
	
	protected int getRow() {
		return row;
	}
	
	protected int getCol() {
		return col;
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
