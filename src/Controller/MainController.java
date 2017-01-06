package Controller;


import view.GameView;
import view.MainMenu;


public class MainController {
	

	MainMenu mMenu;
	GameView gView;
	ActionHandlerMainMenu actMainMenu;
	
	
	public MainController() {
		init();
	}
	
	public void init() {
		//Main menu view
		mMenu = new MainMenu();
		//Game view
		gView = new GameView();
		
		//Action handler for main menu
		actMainMenu = new ActionHandlerMainMenu(this);
		
		
		}

}


