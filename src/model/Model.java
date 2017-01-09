package model;

import org.cmg.resp.topology.VirtualPort;

public class Model {
	private Map map;
	private static VirtualPort vp = new VirtualPort(8080);
	
	public Model(){
		this.map = new Map(vp);
		
	}
	
	public void update(){
		//map.addShip(new YellowShip("wofijj", this.vp, 0,0));
		
	}
	
	public void declineRequest(String id){
		
	}
	
	public void acceptRequest(String id){
		
	}
	
}
