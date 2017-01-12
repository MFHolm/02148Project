package model;

import org.cmg.resp.topology.VirtualPort;

public class GreenShip extends BasicShip{

	public GreenShip(String shipId, String mapId, String harbourId, VirtualPort vp, int row, int col) {
		super(shipId, mapId, harbourId, vp, row, col);
		this.size = ShipSize.SMALL;	
		this.shipType = ShipType.GREEN;
		this.time = 50;
		this.heading = Heading.E;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getMoney(int time) {
		return 50;
	}

}
