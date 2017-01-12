package model;

import java.util.ArrayList;
import java.util.LinkedList;

import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.VirtualPort;

public class Model {
	private Map map;
	private static VirtualPort vp = new VirtualPort(8080);
	
	public Model(int mapHeigth, int mapWidth){
		this.map = new Map(vp, mapHeigth, mapWidth);
	}

	public Map getMap() {
		return map;
	}
public ArrayList<Tuple> getShipPositions() {
		return this.map.getShipPositions();
	}
	
	public LinkedList<Tuple> getRequests() {
		return this.map.getHarbour().getRequests();
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
