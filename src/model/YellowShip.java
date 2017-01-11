package model;

import java.io.IOException;

import org.cmg.resp.knowledge.ActualTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.Self;
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
			
			put(lock,mapConnection);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Sends request to the harbour tuple space
	 * for permission to enter
	 */
	@Override
	public void makeRequest() {		
		
		if (queryp(new Template(new ActualTemplateField("reqSent"))) == null){
			try {
				put(new Tuple("req",id,shipType,time,getMoney(time)),harbourConnection);
				put(new Tuple("reqSent"),Self.SELF); // Sends this tuple so it knows that the request was sent
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		} 
		
	}

	@Override
	protected void doRun() throws Exception {


		makeRequest();
		
		while(true){
			if(!isDocked()){
				checkDockPermission();
			}
		
		}
	}

	@Override
	protected int getMoney(int time) {
		return 100;
	}

}
