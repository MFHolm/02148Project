package model;

import java.io.IOException;

import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.Self;
import org.cmg.resp.topology.VirtualPort;

public class YellowShip extends BasicShip {

	public YellowShip(String id, VirtualPort vp, int xPos, int yPos){
		super(id,vp,xPos,yPos);
		this.size = ShipSize.SMALL;	

	}
	
	@Override
	public void move() {
		
	}

	@Override
	public void makeRequest() {

	}

	@Override
	protected void doRun() throws Exception {
		try {
			put(new Tuple(id,xPos,yPos,size),Self.SELF);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
