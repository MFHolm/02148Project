package test;

import java.util.LinkedList;
import java.util.List;

import model.BasicShip;
import model.Coordinate;
import model.Model;
import model.YellowShip;

public class Test {
	public static void main(String[] args) throws InterruptedException {
			Model m = new Model(500,500);
			BasicShip yellowSubmarine = new YellowShip("YS-1", "sea", "harbour", Model.getVp(), 5, 5);
			LinkedList<Coordinate> path = new LinkedList<Coordinate>();
			path.add(new Coordinate(5,6));
			path.add(new Coordinate(5,7));
			path.add(new Coordinate(5,8));
			path.add(new Coordinate(6,8));
			path.add(new Coordinate(7,8));
			path.add(new Coordinate(8,8));
			
			m.addShip(yellowSubmarine,path);
			
			for(int i = 0; i < path.size()+10; i++){
				Thread.sleep(2000);
				m.viewUpdated();
			}
			
		
		}
}
