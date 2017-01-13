package model;

import java.io.IOException;

import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.VirtualPort;

public class YellowShip extends BasicShip {

	public YellowShip(String id, String mapId, String harbourId, VirtualPort vp, int row, int col){
		super(id,mapId,harbourId,vp,row,col);
		this.size = ShipSize.SMALL;	
		this.shipType = ShipType.YELLOW;
		this.time = 60;
		this.heading = Heading.E;
	}

	@Override
	protected int getMoney(int time) {
		return 100;
	}

}
