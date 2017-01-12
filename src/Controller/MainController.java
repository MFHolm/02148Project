package Controller;


import javax.swing.Timer;

import model.BasicShip;
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
		
		this.timer = new Timer(100, new TimerListener(this));
		
		}
	public void start() {
		this.timer.start();
	}
	public void createModel() {
		this.model = new Model(mapHeigth, mapWidth);
	}
	private void addShip(BasicShip ship){
		model.addShip(ship);
		gView.getRequestPanel().setRequests(this.model.getRequests());
		gView.getRequestPanel().clear();
		gView.getRequestPanel().update();
		gView.getRequestPanel().repaint();
		requestListener.init();
	}
	
	Boolean test = true;
	public void run() throws InterruptedException {
//		System.out.println("time: "+ time);
		if (time <= 1 && time >= 0.9) {
			addShip(new YellowShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 4, 7));

			addShip(new RedShip("id2", model.getSeaName(), model.getHarbourName(), Model.getVp(), 2, 3));
			
			addShip(new GreenShip("id3", model.getSeaName(), model.getHarbourName(), Model.getVp(), 7, 9));
			
		}
		else if (time <= 3 && time >= 2.9) {
			addShip(new GreenShip("id4", model.getSeaName(), model.getHarbourName(), Model.getVp(), 16, 9));
			
		}
		gView.update(this.model.getShipPositions(), this.model.getRequests());
		gView.updateTime(this.time);
		
	}

}


