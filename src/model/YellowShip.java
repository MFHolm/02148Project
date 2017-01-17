package model;

import org.cmg.jresp.topology.VirtualPort;

public class YellowShip extends BasicShip {

	public YellowShip(String id, String mapId, String harbourId, VirtualPort vp, int row, int col, Heading h){
		super(id,mapId,harbourId,vp,row,col, h);
		this.size = ShipSize.SMALL;	
		this.shipType = ShipType.YELLOW;
		this.time = 20;
	}

	@Override
	protected int getMoney(int time) {
		return 100;
	}

}
