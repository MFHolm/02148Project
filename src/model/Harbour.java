package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.comp.Node;
import org.cmg.resp.knowledge.ActualTemplateField;
import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;
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
		if(harbour.queryp(new Template(	new ActualTemplateField("dockAvailable"),
										new ActualTemplateField(dockId))) == null) { // Should check if dock is available
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
		return harbourTupleSpace.getAll(new Template(
				new FormalTemplateField(String.class),
				new FormalTemplateField(String.class),
				new FormalTemplateField(ShipType.class),
				new FormalTemplateField(Integer.class),
				new FormalTemplateField(Integer.class)));
	}
	
	private class HarbourAgent extends Agent {
		
		public HarbourAgent(String name){
			super(name);
		}
		
		@Override
		protected void doRun() throws Exception {	
				while(true){
					//Implementing accept request by checking for assignDock in the tuple space
					Tuple t = getp(new Template( 	new ActualTemplateField("assignDock"),
							new FormalTemplateField(String.class),
							new FormalTemplateField(String.class)));
					if (t != null){				
						String shipId = t.getElementAt(String.class, 1);
						String dockId = t.getElementAt(String.class, 2);
						Dock dock = docks.get(dockId);
						
						PointToPoint shipConnection = new PointToPoint(shipId, vp.getAddress());
						
						get(new Template (	new ActualTemplateField("dockAvailable"),
								new ActualTemplateField(dockId)), Self.SELF);
						
						
						put(new Tuple("assignedTo",dockId,dock.getRow(),dock.getCol()),shipConnection);
						
						
					
				}
			}	
		}
	}
}
