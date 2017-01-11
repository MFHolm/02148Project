package test;

import org.cmg.resp.comp.Node;
import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.Self;

import model.Model;
import model.Templates;
import model.YellowShip;

public class Test {
	public static void main(String[] args) {
		Model m = new Model();
		Node test = m.getMap().getSeaNode();
		YellowShip ys = new YellowShip("JOI-239","sea","harbour",m.getVp(),0,0);
		m.getMap().addShip(ys);
		m.getMap().getHarbour().addDock("D-1",5,5);
		ys.makeRequest();
		try {
			Thread.sleep(2000);
			System.out.println("Herro");
			Tuple t = m.getHarbour().getNode().get(Templates.getReqTemp(ys.getName()));
			System.out.println("Whatup");
			Tuple u = m.getMap().getSeaNode().get(Templates.getCoordTemp(ys.getName()));
			System.out.println("Coords: "+ u + ", Request: " +t);
			m.getHarbour().getNode().put(t);
			m.getMap().getSeaNode().put(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m.declineRequest(ys.getName());
		
		try {
			Thread.sleep(2000);
			Tuple t = m.getHarbour().getNode().getp(Templates.getReqTemp(ys.getName()));
			Tuple u = m.getMap().getSeaNode().getp(Templates.getCoordTemp(ys.getName()));
			System.out.println("Coords: "+ u + ", Request: " + t + ", Ships: " + m.getMap().getShips().size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
