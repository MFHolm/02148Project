package model;

import java.util.ArrayList;
import java.util.List;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.comp.Node;
import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.knowledge.ts.TupleSpace;
import org.cmg.resp.topology.Self;
import org.cmg.resp.topology.VirtualPort;

public class Map {
	private Node sea;
	private VirtualPort vp;
	private List<BasicShip> ships;
	
	public Map(VirtualPort vp){
		this.ships = new ArrayList<BasicShip>();
		this.sea = new Node("Sea",new TupleSpace());
		this.vp = vp;
		sea.addPort(this.vp);
		sea.addAgent(new SeaAgent("SøAgent"));
		sea.start();
		
	}
	
	public void addShip(BasicShip ship){
		ships.add(ship);
		sea.addAgent(ship);
		
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