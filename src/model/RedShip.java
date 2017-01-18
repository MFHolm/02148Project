package model;

import org.cmg.jresp.topology.VirtualPort;

public class RedShip extends BasicShip{

	public RedShip(String shipId, String mapId, String harbourId, VirtualPort vp, int row, int col, Heading h) {
		super(shipId, mapId, harbourId, vp, row, col, h);
		this.size = ShipSize.SMALL;	
		this.shipType = ShipType.RED;
		this.time = 5;
	}


	@Override
	public int getMoney(int time) {
		return 20;
	}

}
