package test;

import org.cmg.resp.comp.Node;
import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.Self;

import model.Model;
import model.YellowShip;

public class Test {
	public static void main(String[] args) {
		Model m = new Model();
		Node test = m.getMap().getSeaNode();
		YellowShip ys = new YellowShip("JOI-239","sea","harbour",m.getVp(),0,0);
		m.getMap().addShip(ys);
		m.getMap().getHarbour().addDock("D-1",5,5);
		ys.makeRequest();
		m.acceptRequest(ys.getName(), "D-1");
		try {
			Thread.sleep(2000);
			Tuple t = test.get(new Template( 	new FormalTemplateField(String.class),	
												new FormalTemplateField(Integer.class),
												new FormalTemplateField(Integer.class)));
			System.out.println(t.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
