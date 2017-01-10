package model;

import java.io.IOException;
import java.util.HashMap;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.comp.Node;
import org.cmg.resp.knowledge.ActualTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.knowledge.ts.TupleSpace;
import org.cmg.resp.topology.Self;
import org.cmg.resp.topology.VirtualPort;

public class Map {
	private Node sea;
	private Harbour harbour;
	private VirtualPort vp;
	private HashMap<String,Node> shipNodes;
	private TupleSpace coordinates;
	private Template lockTemp = new Template(new ActualTemplateField("lock"));
	
	public Map(VirtualPort vp){
		coordinates = new TupleSpace();
		this.sea = new Node("Sea",coordinates);
		this.harbour = new Harbour(vp);
		this.shipNodes = new HashMap<String,Node>();
		this.vp = vp;
		sea.addPort(this.vp);
		sea.addAgent(new SeaAgent("ShipController"));
		sea.put(new Tuple("lock"));
		sea.start();
		
	
	}
	
	public void addShip(BasicShip ship){
		Node newShipNode = new Node(ship.getId(), new TupleSpace());
		newShipNode.addPort(vp);
		newShipNode.addAgent(ship);
		shipNodes.put(ship.getId(),newShipNode);
		try {
			Tuple lock = coordinates.get(lockTemp);
			coordinates.put(new Tuple(ship.getId(),ship.getRow(),ship.getCol()));
			coordinates.put(lock);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		newShipNode.start();
		
		
	}
	
	public void handleRequest(String shipId, String dockId) throws IllegalArgumentException {
		Node shipNode = shipNodes.get(shipId);
		if(shipNode.queryp(new Template(new ActualTemplateField("reqSent")))==null){
			throw new IllegalArgumentException(shipId + " hasn't sent any request");
		} else {
			harbour.assignDock(shipId,dockId);
		}
	}
 
	
	public String getSeaName(){
		return sea.getName();
	}
	
	public Harbour getHarbour(){
		return harbour;
	}
	
	public Node getSeaNode(){
		return sea;
	}
	
	private class SeaAgent extends Agent {
		public SeaAgent(String name) {
			super(name);
			
		}

		@Override
		protected void doRun() throws Exception {	
			/*while(true){
				for(Tuple t : coordinates.getAll(lockTemp)){
					System.out.println(t);
					coordinates.put(t);
				}
			}*/

			
		}
	}

}