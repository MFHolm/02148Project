package test;

import java.util.ArrayList;
import java.util.List;

import model.BasicShip;
import model.Model;
import model.YellowShip;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		
		Model m = new Model();
		
		List<BasicShip> ships = new ArrayList<BasicShip>();
		
		for(int i = 0; i < 10; i++) {
			ships.add(new YellowShip("Ship"+i, "sea", "harbour", Model.getVp(), i, i));
			m.addShip(ships.get(i));
		}
		
		while(true) {
			m.viewUpdated();
			Thread.sleep(2000);
			System.out.println("View updated \n");
		}
		
		
	}

}
