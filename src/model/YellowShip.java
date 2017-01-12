package model;

import java.io.IOException;

import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.VirtualPort;

public class YellowShip extends BasicShip {

	public YellowShip(String id, String mapId, String harbourId, String barrierId, VirtualPort vp, int row, int col){
		super(id,mapId,harbourId,barrierId,vp,row,col);
		this.size = ShipSize.SMALL;	
		this.shipType = ShipType.YELLOW;
		this.time = 60;
		this.heading = Heading.E;
	}
	
	@Override
	public void move() {
		/*switch(heading){
		case N:
			xPos
			
		}*/
		Tuple lock;
		try {
			lock = get(Templates.getLockTemp(),mapConnection);
			/*get(new Template(	new ActualTemplateField(id), 
								new FormalTemplateField(Integer.class),
								new FormalTemplateField(Integer.class)),mapConnection);*/
			System.out.println(id + " moved");
			put(lock,mapConnection);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	protected int getMoney(int time) {
		return 100;
	}

}
