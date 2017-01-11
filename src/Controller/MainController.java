package Controller;


import model.BasicShip;
import model.Model;
import model.YellowShip;
import view.GameView;
import view.MainMenu;


public class MainController {
	
	MainMenu mMenu;
	GameView gView;
	ActionHandlerMainMenu actMainMenu;
	Model model;
	
	
	public MainController() {
		init();
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
		
		}
	
	public void createModel() {
		this.model = new Model();
	}
	
	public void run() {
		Boolean test = true;
		while(true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(test) {
				model.addShip(new YellowShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 4, 7));
				test = false;
			}

		}
	}

}


