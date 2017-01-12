package test;

import java.util.LinkedList;

import org.cmg.resp.knowledge.Tuple;

import model.BasicShip;
import model.Model;
import model.YellowShip;

public class Test {
	public static void main(String[] args) {
		Model m = new Model();
		BasicShip ys1 = new YellowShip("Ship1", "sea", "harbour", m.getVp(), 1, 1);
		BasicShip ys2 = new YellowShip("Ship2", "sea", "harbour", m.getVp(), 2, 2);
		BasicShip ys3 = new YellowShip("Ship3", "sea", "harbour", m.getVp(), 2, 3);
		m.getMap().addShip(ys1);
		m.getMap().addShip(ys2);
	    m.getMap().addShip(ys3);
		ys1.makeRequest();
		ys2.makeRequest();
		ys3.makeRequest();
		LinkedList<Tuple> t = m.getHarbour().getRequests();
		for (Tuple tuple : t) {
			System.out.println(tuple);
		}
	}

}
