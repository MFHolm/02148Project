package model;

import org.cmg.resp.topology.VirtualPort;

public class Model {
	private Map map;
	private static VirtualPort vp = new VirtualPort(8080);
	
	public Model(){
		this.map = new Map(vp);
	
		
	}

	public Map getMap() {
		return map;
	}

	public static VirtualPort getVp() {
		return vp;
	}
	
	public void acceptRequest(String shipId, String dockId) throws IllegalArgumentException {
		map.handleRequest(shipId,dockId);
	}
	
	public void declineRequest(String id){
		
	}


	
	
	

	
}
