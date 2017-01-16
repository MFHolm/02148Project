package model;

import org.cmg.resp.topology.VirtualPort;

public class RedShip extends BasicShip{

	public RedShip(String shipId, String mapId, String harbourId, VirtualPort vp, int row, int col) {
		super(shipId, mapId, harbourId, vp, row, col);
		this.size = ShipSize.SMALL;	
		this.shipType = ShipType.RED;
		this.time = 5;
		this.heading = Heading.N;
	}


	@Override
	protected int getMoney(int time) {
		return 20;
	}

}
