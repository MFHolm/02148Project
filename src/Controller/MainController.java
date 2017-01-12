package Controller;


import javax.swing.Timer;

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
		
		this.timer = new Timer(100, new TimerListener(this));
		
		}
	public void start() {
		this.timer.start();
	}
	public void createModel() {
		this.model = new Model(mapHeigth, mapWidth);
	}
	
	Boolean test = true;
	public void run() throws InterruptedException {
//		System.out.println("time: "+ time);
		if (time <= 5 && time >= 4.9) {
			//model.addShip(new YellowShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 4, 7));
			
		}
		if (time <= 10 && time >= 9.9)  {
			//model.addShip(new RedShip("id2", model.getSeaName(), model.getHarbourName(), Model.getVp(), 2, 3));
			
		}
		if (time <= 13 && time >= 12.9)  {
			//model.addShip(new GreenShip("id3", model.getSeaName(), model.getHarbourName(), Model.getVp(), 7, 9));
			
		}
		gView.update(this.model.getShipPositions(), this.model.getRequests());
		gView.updateTime(this.time);
		
	}

}


