package Controller;


import javax.swing.Timer;

import model.Model;
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
		gView = new GameView();
		
		//Action handler for main menu
		actMainMenu = new ActionHandlerMainMenu(this);
		
		this.timer = new Timer(100, new TimerListener(this));
		
		}
	public void start() {
		this.timer.start();
	}
	public void createModel() {
		this.model = new Model();
		gView.setMap(model.getMap());
		gView.setHarbour(model.getHarbour());
	}
	
	Boolean test = true;
	public void run() throws InterruptedException {
//		System.out.println("time: "+ time);
		if (time <= 5 && time >= 4.9) {
			model.addShip(new YellowShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 4, 7));
			
		}
		if (time <= 10 && time >= 9.9)  {
			model.addShip(new YellowShip("id2", model.getSeaName(), model.getHarbourName(), Model.getVp(), 2, 3));
			
		}
		gView.update();
		gView.updateTime(this.time);
		
	}

}


