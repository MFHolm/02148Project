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
		gView.setMap(model.getMap());
		gView.setHarbour(model.getHarbour());
	}
	
	public void run() throws InterruptedException {
		Boolean test = true;
			Thread.sleep(1000);
			System.out.println("What1");
			if(test) {
				model.addShip(new YellowShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 4, 7));
				test = false;
			}

			gView.update();

		
	}

}


