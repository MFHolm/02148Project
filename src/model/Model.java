package model;

import org.cmg.resp.knowledge.Tuple;
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
	
	public Harbour getHarbour() {
		return map.getHarbour();
	}
	
	public void acceptRequest(String shipId, String dockId) throws IllegalArgumentException {
		map.handleRequest(shipId,dockId);
	}
	
	public void declineRequest(String shipId){
		map.getSea().put(new Tuple("declineReq",shipId));
		map.getHarbour().getNode().put(new Tuple("declineReq",shipId));
	}
	
	public void addShip(BasicShip ship) {
		map.addShip(ship);
	}
	
	public String getSeaName() {
		return map.getSeaName();
	}
	
	public String getHarbourName() {
		return map.getHarbour().getNode().getName();
	}
	
}
