package Controller;


import java.io.IOException;
import java.util.LinkedList;

import javax.swing.Timer;

import model.BasicShip;
import model.Coordinate;
import model.GreenShip;
import model.Model;
import model.RedShip;
import model.YellowShip;
import view.GameView;
import view.MainMenu;


public class MainController {
	
	MainMenu mMenu;
	GameView gView;
	ActionHandlerMainMenu actMainMenu;
	RequestListener requestListener;
	DockListener dockListener;
	Model model;
	Timer timer;
	double time;
	final int mapHeigth = 25;
	final int mapWidth = 25;
	
	
	public MainController() {
		init();
	}
	public void incrementTimer() {
		this.time += 0.1;
	}
	
	public void init() {
		//testgrid
		int[][] grid = new int[1][1];
		
		//Main menu view
		mMenu = new MainMenu();
		//Game view
		gView = new GameView(mapHeigth, mapWidth);
		
		//Action handler for main menu
		actMainMenu = new ActionHandlerMainMenu(this);
		requestListener = new RequestListener(this);
		dockListener = new DockListener(this);
		dockListener.init();
		
		this.timer = new Timer(500, new TimerListener(this));
		
		}
	public void start() {
		this.timer.start();
	}
	public void createModel() {
		this.model = new Model(mapHeigth, mapWidth);
	}
	private void addShip(BasicShip ship){
		model.addShip(ship);
		updateRequests();
	}
	private void updateRequests() {
		gView.getRequestPanel().setRequests(this.model.getRequests());
		System.out.println(this.model.getRequests());
		gView.getRequestPanel().clear();
		gView.getRequestPanel().update();
		gView.getRequestPanel().repaint();
		requestListener.init();
	}
	
	Boolean test = true;
	public void run() throws InterruptedException {
//		System.out.println("time: "+ time);

		if (time <= 0.1 && time >= 0) {
			RedShip s = new RedShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 5, 5);
			
			
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
			s.setPath(path);
			
			addShip(s);
			
			addShip(new RedShip("id2", model.getSeaName(), model.getHarbourName(), Model.getVp(), 2, 3));

			RedShip gs = new RedShip("id3", model.getSeaName(), model.getHarbourName(), Model.getVp(), 6, 3);
			
			LinkedList<Coordinate> pathgs = new LinkedList<Coordinate>();
			pathgs.add(new Coordinate(6,4));
			pathgs.add(new Coordinate(6,5));
			pathgs.add(new Coordinate(6,6));
			pathgs.add(new Coordinate(6,7));
			pathgs.add(new Coordinate(6,8));
			pathgs.add(new Coordinate(6,9));
	

			gs.setPath(pathgs);
			addShip(gs);

		} else if (time <= 3 && time >= 2.9) {
			addShip(new GreenShip("id4", model.getSeaName(), model.getHarbourName(), Model.getVp(), 16, 9));
			
		}
		gView.update(this.model.getShipPositions(), this.model.getRequests());
		gView.updateTime(this.time);
		model.viewUpdated();
		
	}
	public void dockClicked(String dock) {
		String ship = this.gView.getRequestPanel().getMarkedShip();
		if (!ship.equals("")){
			try {
				model.acceptRequest(ship, dock);
				
				updateRequests();
				System.out.println(dock + " has been assigned to " + ship);
			}
			catch (IllegalArgumentException e) {
			System.out.println(e);
			}
		}
	}

}


