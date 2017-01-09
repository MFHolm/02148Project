package model;

import org.cmg.resp.behaviour.Agent;
import org.cmg.resp.comp.Node;
import org.cmg.resp.knowledge.ts.TupleSpace;
import org.cmg.resp.topology.VirtualPort;

public class Harbour {
	private Node harbour;
	private VirtualPort vp;
	
	public Harbour(VirtualPort vp){
		this.vp = vp;
		harbour = new Node("harbour",new TupleSpace());
		harbour.start();
	}
	
	
	private class HarbourAgent extends Agent {
		
		public HarbourAgent(String name){
			super(name);
		}

		@Override
		protected void doRun() throws Exception {
			// TODO Auto-generated method stub
			
		}
	}
}
