package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.comp.Node;
import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.knowledge.ts.TupleSpace;
import org.cmg.resp.topology.PointToPoint;
import org.cmg.resp.topology.Self;
import org.cmg.resp.topology.VirtualPort;

public class Map {
	private Node sea;
	private Harbour harbour;
	private VirtualPort vp;
	private HashMap<String,PointToPoint> shipConnections;
	private List<Node> shipNodes;
	
	
	public Map(VirtualPort vp){

		this.sea = new Node("Sea",new TupleSpace());
		this.harbour = new Harbour(vp);
		this.shipNodes = new ArrayList<Node>();
		this.shipConnections = new HashMap<String,PointToPoint>();
		this.vp = vp;
		sea.addPort(this.vp);
		sea.addAgent(new SeaAgent("ShipController"));
		sea.put(new Tuple("lock"));
		sea.start();
		
		
	}
	
	public void addShip(BasicShip ship){
		sea.addAgent(ship);
		Node newShipNode = new Node(ship.getId(), new TupleSpace());
		newShipNode.addPort(vp);
		newShipNode.addAgent(ship);
		shipNodes.add(newShipNode);
		shipConnections.put(ship.getId(), new PointToPoint(ship.getId(),vp.getAddress()));
		sea.put(new Tuple(ship.getId(),ship.getRow(),ship.getCol()));
		newShipNode.start();
		
		
	}
	
	public String getSeaName(){
		return sea.getName();
	}
	
	private class SeaAgent extends Agent {
		public SeaAgent(String name) {
			super(name);
			
		}

		@Override
		protected void doRun() throws Exception {	
			

			
		}
	}
}