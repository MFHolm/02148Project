package test;

import java.util.LinkedList;
import java.util.List;

import model.BasicShip;
import model.Coordinate;
import model.Model;
import model.Templates;
import model.YellowShip;

public class Test {
	public static void main(String[] args) throws InterruptedException {
			Model m = new Model(25,25);
			//System.out.println(m.getMap().getFreePositions());
			BasicShip yellowSubmarine = new YellowShip("YS-1", "sea", "harbour", Model.getVp(), 5, 5);
			LinkedList<Coordinate> path = new LinkedList<Coordinate>(); 
			
			path.add(new Coordinate(5,6));
			path.add(new Coordinate(5,7));
			path.add(new Coordinate(5,8));
			path.add(new Coordinate(6,8));
			path.add(new Coordinate(7,8));
			path.add(new Coordinate(8,8));
			path.add(new Coordinate(8,7));
			path.add(new Coordinate(8,6));
			path.add(new Coordinate(8,5));
			path.add(new Coordinate(7,5));
			path.add(new Coordinate(6,5));
			path.add(new Coordinate(5,5));
			yellowSubmarine.setPath(path);
			m.addShip(yellowSubmarine);
			
			while(true){
				Thread.sleep(1000);
				m.viewUpdated();
			}
			
		
		}
}
