package model;

import java.util.HashMap;
import java.util.LinkedList;

import org.cmg.resp.behaviour.Action.Query;
import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.comp.Node;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.knowledge.ts.TupleSpace;
import org.cmg.resp.topology.PointToPoint;
import org.cmg.resp.topology.Self;
import org.cmg.resp.topology.VirtualPort;

public class Harbour {
	private Node harbour;
	private VirtualPort vp;
	private HashMap<String,Dock> docks;
	private TupleSpace harbourTupleSpace;
	
	public Harbour(VirtualPort vp){
		this.vp = vp;
		harbourTupleSpace = new TupleSpace();
		harbour = new Node("harbour",harbourTupleSpace);
		docks = new HashMap<String,Dock>();
		harbour.addPort(vp);
		harbour.addAgent(new HarbourAgent("HarbourAgent"));
		harbour.start();
		this.addDock("dock1", 15, 22);
		this.addDock("dock2", 13, 22);
		this.addDock("dock3", 11, 22);
	}
	
	public Node getNode(){
		return harbour;
	}
	
	/* assignDock:
	 * Checks if the dock with the given dockId is available.
	 * If available it will tell the agent of the harbour tuple space
	 * to add the ship with shipId to the dock.
	 * Otherwise exception is thrown.
	 */
	public void assignDock(String shipId, String dockId) throws IllegalArgumentException {
		if(harbour.queryp(Templates.getDockAvailTemp(dockId)) == null) { // Should check if dock is available
			throw new IllegalArgumentException(dockId+" occupied.");
		} else {
			harbour.put(new Tuple("assignDock",shipId,dockId));
		}
		
	}
	
	public void addDock(String dockId, int row, int col) {
		//TODO
		docks.put(dockId, new Dock(row,col));
		harbour.put(new Tuple("dockAvailable",dockId));
		
	}
	public LinkedList<Tuple> getRequests() {
		LinkedList<Tuple> ships = new LinkedList<>();
		Tuple t = null;
		do {
			t = harbourTupleSpace.getp(Templates.getReqTemp());
			if (t != null) {
				ships.add(t);
			}
		}while (t != null);
		
//		LinkedList<Tuple> ships = harbourTupleSpace.getAll(Templates.getReqTemp());
		for (Tuple tuple : ships) {
			harbourTupleSpace.put(tuple);
		}
		return ships;
	}
	
	private class HarbourAgent extends Agent {
		
		public HarbourAgent(String name){
			super(name);
		}
		
		@Override
		protected void doRun() throws Exception {	
			while(true){
				//Implementing accept request by checking for assignDock in the tuple space
				Tuple t = getp(Templates.getAssignDockTemp());
				if (t != null){				
					String shipId = t.getElementAt(String.class, 1);
					String dockId = t.getElementAt(String.class, 2);
					Dock dock = docks.get(dockId);
					
					PointToPoint shipConnection = new PointToPoint(shipId, vp.getAddress());
					
					get(Templates.getDockAvailTemp(dockId), Self.SELF);
					
					get(Templates.getReqTemp(shipId), Self.SELF);
					put(new Tuple("assignedTo",dockId,dock.getRow(),dock.getCol()),shipConnection);
				}
				
				t = getp(Templates.getDeclineReqTemp());
				if (t != null) {
					String shipId = t.getElementAt(String.class, 1);
					get(Templates.getReqTemp(shipId),Self.SELF);
				}
			}	
		}
	}
}
