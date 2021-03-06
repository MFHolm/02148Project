package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.cmg.jresp.knowledge.Tuple;
import org.cmg.jresp.topology.VirtualPort;

public class Model {
	private Map map;
	private double time;
	private int money;
	private static VirtualPort vp = new VirtualPort(8080);

	public Model(int mapHeigth, int mapWidth) {
		this.map = new Map(vp, mapHeigth, mapWidth);
	}

	public void incrementTime(double n) {
		this.time += n;
		updateDocks(n);
	}

	
	public void declineRequest(String shipId) {
		map.getSea().put(new Tuple("decline", shipId));
		map.getHarbour().getNode().put(new Tuple("declineReq", shipId));
		try {
			map.getHarbour().getNode().get(Templates.getRemovedTemp());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double getTime() {
		return time;
	}

	public int getMoney() {
		return money;
	}

	public void incrementMoney(int money) {
		this.money += money;
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

	public void acceptRequest(String shipId, String dockId) throws IllegalArgumentException {
		map.handleRequest(shipId, dockId);
	}

//	public void declineRequest(String shipId) {
//		map.getSea().put(new Tuple("declineReq", shipId));
//		map.getHarbour().getNode().put(new Tuple("declineReq", shipId));
//	}

	public void viewUpdated() {
		map.getMonitor().updateView();
	}

	public void addShip(BasicShip ship) {
		map.addShip(ship);
	}

	public void assignPath(BasicShip ship, int i) {
		map.assignPath(ship, i);
	}

	public String getSeaName() {
		return map.getSeaName();
	}

	public String getHarbourName() {
		return map.getHarbour().getNode().getName();
	}

	// Update time in dock and check if dock is done
	public void updateDocks(double t) {
		for (Entry<String, Dock> entry : map.getHarbour().getDockHashMap().entrySet()) {
			Dock dock = entry.getValue();
			String dockId = entry.getKey();
			if (dock.updateDock(t)) {
				incrementMoney(dock.emptyMoney());
				String ship = dock.shipLeaves();
				map.getSea().put(new Tuple("declineReq", ship));
				map.getHarbour().releaseDock(dockId);
			}
		}
	}

	public int getNumberOfShips() {
		return map.getShips().size();
	}

}
