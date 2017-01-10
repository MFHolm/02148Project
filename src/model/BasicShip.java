package model;

import java.io.IOException;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.knowledge.ActualTemplateField;
import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.PointToPoint;
import org.cmg.resp.topology.VirtualPort;

public abstract class BasicShip extends Agent {
	protected int row,col;
	protected Heading heading;
	protected String id;
	protected VirtualPort vp;
	protected PointToPoint mapConnection;
	protected PointToPoint harbourConnection;
	protected boolean docked;
	protected ShipSize size;
	protected Template lockTemp = new Template(new ActualTemplateField("lock"));
	
	
	
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
	
	public abstract void makeRequest();
	
	protected void checkDockPermission(){
		Template dockAssignTemp = new Template( new ActualTemplateField("assignedTo"),
												new FormalTemplateField(String.class),
												new FormalTemplateField(Integer.class),
												new FormalTemplateField(Integer.class));
		Tuple t = getp(dockAssignTemp);
		if(t != null){
			Template coordTemp = new Template( 	new ActualTemplateField(id),
												new FormalTemplateField(Integer.class),
												new FormalTemplateField(Integer.class));
			try {
				System.out.println("Waiting");
				Tuple u = get(lockTemp,mapConnection);
				System.out.println("Done");
				get(coordTemp,mapConnection);
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

}
