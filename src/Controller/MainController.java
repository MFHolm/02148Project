package Controller;


import java.util.LinkedList;

import javax.swing.Timer;

import model.BasicShip;
import model.Coordinate;
import model.GreenShip;
import model.Model;
import model.RedShip;
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
	final int mapHeigth = 25;
	final int mapWidth = 25;
	int delay = 500;
	
	
	public MainController() {
		init();
	}
	public void incrementTimer() {
		this.model.incrementTime(0.5);
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
		
		this.timer = new Timer(delay, new TimerListener(this));
		
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

		if (model.getTime() <= 0.5 && model.getTime() >= 0) {
			RedShip s = new RedShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 23, 12);
			
			LinkedList<Coordinate> path = new LinkedList<Coordinate>();
			path.add(new Coordinate(22,12));
			path.add(new Coordinate(21,12));
			path.add(new Coordinate(20,12));
			path.add(new Coordinate(19,12));
			path.add(new Coordinate(18,12));
			path.add(new Coordinate(17,12));
			path.add(new Coordinate(16,12));
			path.add(new Coordinate(15,12));
			path.add(new Coordinate(14,12));
			path.add(new Coordinate(13,12));
			path.add(new Coordinate(12,12));
			path.add(new Coordinate(12,11));
			path.add(new Coordinate(12,10));
			path.add(new Coordinate(12,9));
			path.add(new Coordinate(12,8));
			path.add(new Coordinate(12,7));
			path.add(new Coordinate(13,7));
			path.add(new Coordinate(14,7));
			path.add(new Coordinate(15,7));
			path.add(new Coordinate(16,7));
			path.add(new Coordinate(16,6));
			path.add(new Coordinate(16,5));
			path.add(new Coordinate(15,5));
			path.add(new Coordinate(14,5));
			path.add(new Coordinate(13,5));
			path.add(new Coordinate(13,4));
			path.add(new Coordinate(13,3));
			path.add(new Coordinate(14,3));
			path.add(new Coordinate(15,3));
			path.add(new Coordinate(16,3));
			path.add(new Coordinate(17,3));
			path.add(new Coordinate(18,3));
			path.add(new Coordinate(19,3));
			path.add(new Coordinate(20,3));
			path.add(new Coordinate(21,3));
			path.add(new Coordinate(22,3));
			path.add(new Coordinate(22,4));
			path.add(new Coordinate(22,5));
			path.add(new Coordinate(22,6));
			path.add(new Coordinate(22,7));
			path.add(new Coordinate(22,8));
			path.add(new Coordinate(22,9));
			path.add(new Coordinate(22,10));
			path.add(new Coordinate(22,11));
			
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

		} else if (model.getTime() <= 3 && model.getTime() >= 2.9) {
			addShip(new GreenShip("id4", model.getSeaName(), model.getHarbourName(), Model.getVp(), 16, 9));
			
		}
		gView.update(this.model.getShipPositions(), this.model.getRequests());
		gView.updateTime(model.getTime());
		gView.updateMoney(model.getMoney());
		model.viewUpdated();
		
	}
	public void dockClicked(String dock) {
		String ship = this.gView.getRequestPanel().getMarkedShip();
		if (!ship.equals("")){
			try {
				model.acceptRequest(ship, dock);
				
				this.gView.getGamePanel().setCircleId(ship);
				this.gView.getRequestPanel().setMarkedID(ship);
				updateRequests();
				System.out.println(dock + " has been assigned to " + ship);
			}
			catch (IllegalArgumentException e) {
			System.out.println(e);
			}
		}
	}

}


