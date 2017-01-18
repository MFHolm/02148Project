package model;

import org.cmg.jresp.topology.VirtualPort;

public class GreenShip extends BasicShip{

	public GreenShip(String shipId, String mapId, String harbourId, VirtualPort vp, int row, int col, Heading h) {
		super(shipId, mapId, harbourId, vp, row, col, h);
		this.size = ShipSize.SMALL;	
		this.shipType = ShipType.GREEN;
		this.time = 10;
	}


	@Override
	public int getMoney(int time) {
		return 50;
	}

}
