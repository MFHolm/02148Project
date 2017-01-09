package test;

import java.awt.Graphics;

import org.cmg.resp.knowledge.Tuple;
import org.cmg.resp.topology.VirtualPort;

import model.Heading;
import model.Map;
import model.ShipType;
import view.GameView;

public class viewTester {
	public static void main(String[] args) {
		Map map = new Map( new VirtualPort(8080));
		map.getSea().put(new Tuple("id", ShipType.GREEN, 3,3,Heading.E));
		GameView gv = new GameView();
		gv.setVisible(true);
		gv.update();
	}
}
