package test;

import java.awt.Graphics;
import java.net.URISyntaxException;
import java.util.LinkedList;

import javax.swing.JFrame;

import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;
import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.VirtualPort;

import model.Heading;
import model.Map;
import model.ShipType;
import view.GameView;
import view.RequestPanel;

public class viewTester {
	public static void main(String[] args) throws InterruptedException {
		/*Map map = new Map( new VirtualPort(8080));
		GameView gv = new GameView();
		gv.setMap(map);
		gv.setVisible(true);
		
		Template t = new Template(new FormalTemplateField(String.class),
				new FormalTemplateField(ShipType.class),
				new FormalTemplateField(Integer.class),
				new FormalTemplateField(Integer.class),
				new FormalTemplateField(Heading.class));
		
		int s = 100;
		map.getSea().put(new Tuple("id", ShipType.GREEN, -1,-1,Heading.E));
		gv.update();
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 0,0,Heading.E));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 0,0,Heading.E));
		map.getSea().put(new Tuple("id", ShipType.GREEN, 0,1,Heading.E));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 0,1,Heading.E));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 0,1,Heading.E));
		map.getSea().put(new Tuple("id", ShipType.GREEN, 0,2,Heading.E));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 0,2,Heading.E));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 0,2,Heading.SE));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 0,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 0,2,Heading.S));
		map.getSea().put(new Tuple("id", ShipType.GREEN, 1,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 1,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 1,2,Heading.S));
		map.getSea().put(new Tuple("id", ShipType.GREEN, 2,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 2,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 2,2,Heading.S));
		map.getSea().put(new Tuple("id", ShipType.GREEN, 3,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 3,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 3,2,Heading.S));
		map.getSea().put(new Tuple("id", ShipType.GREEN, 4,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 4,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 4,2,Heading.S));
		map.getSea().put(new Tuple("id", ShipType.GREEN, 5,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 5,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 5,2,Heading.S));
		map.getSea().put(new Tuple("id", ShipType.GREEN, 6,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 6,2,Heading.S));
		gv.update();
		
		Thread.sleep(s);
		map.getSea().get(t);
		map.getSea().put(new Tuple("id", ShipType.GREEN, 6,2,Heading.S));
		map.getSea().put(new Tuple("id", ShipType.GREEN, 7,2,Heading.S));
		gv.update();
		*/
		
		Map map = new Map( new VirtualPort(8080));
		GameView gv = new GameView();
		gv.setMap(map);
		gv.setVisible(true);
		
		LinkedList<Tuple> ships = new LinkedList<>();
		ships.add(new Tuple("req", "ship1", ShipType.GREEN, 10, 100));
		ships.add(new Tuple("req", "ship2", ShipType.RED, 10, 200));
		ships.add(new Tuple("req", "ship1", ShipType.GREEN, 10, 100));
		ships.add(new Tuple("req", "ship2", ShipType.YELLOW, 2, 200));
		ships.add(new Tuple("req", "ship1", ShipType.GREEN, 34, 100));
		ships.add(new Tuple("req", "ship2", ShipType.RED, 10, 222));
		ships.add(new Tuple("req", "ship1", ShipType.GREEN, 10, 100));
		ships.add(new Tuple("req", "ship2", ShipType.YELLOW, 10, 200));
		
//		Thread.sleep(500);
		gv.update(ships);
//		Thread.sleep(5000);
//		ships = new LinkedList<>();
//		ships.add(new Tuple("req", "ship1", ShipType.GREEN, 10, 100));
//		ships.add(new Tuple("req", "ship2", ShipType.RED, 10, 200));
//		ships.add(new Tuple("req", "ship1", ShipType.GREEN, 10, 100));
//		gv.update(ships);
		
	}
}
