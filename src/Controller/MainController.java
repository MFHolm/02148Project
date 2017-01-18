package Controller;


import java.util.Random;

import javax.swing.Timer;

import org.cmg.jresp.exceptions.IllegalActionException;

import model.BasicShip;
import model.GreenShip;
import model.Heading;
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
	final int mapHeigth = 25;
	final int mapWidth = 25;
	int delay = 100;
	private int shipCounter = 0;
	
	
	public MainController() {
		init();
	}
	public MainController(String string) throws IllegalActionException {
		if (!string.equals("test")) {
			throw new IllegalActionException("This method should only be used for testing");
		}

		//Main menu view
		mMenu = new MainMenu();
		mMenu.setVisible(false);
		//Game view
		gView = new GameView(mapHeigth, mapWidth);
		gView.setVisible(false);
		//Action handler for main menu
		actMainMenu = new ActionHandlerMainMenu(this);
		requestListener = new RequestListener(this);
		dockListener = new DockListener(this);
		dockListener.init();
		
		this.timer = new Timer(delay, new TimerListener(this));
		
		
	}
	public void incrementTimer() {
		this.model.incrementTime(0.1);
	}
	public Model getModel() {
		return model;
	}
	public void init() {
		
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
	public void updateRequests() {
		gView.getRequestPanel().setRequests(this.model.getRequests());
		gView.getRequestPanel().clear();
		gView.getRequestPanel().update();
		gView.getRequestPanel().repaint();
		requestListener.init();
	}
	
	Boolean test = true;
	public void run() throws InterruptedException {
//		System.out.println("time: "+ time);

//		if (model.getTime() <= 0.5 && model.getTime() >= 0) {
//			RedShip s = new RedShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 24, 12, Heading.N);
//			model.assignPath(s, 1);
//			addShip(s);
//			
//			GreenShip s1 = new GreenShip("id2", model.getSeaName(), model.getHarbourName(), Model.getVp(), 12, 0, Heading.E);
//			model.assignPath(s1, 2);
//			addShip(s1);
//
//			YellowShip s2 = new YellowShip("id3", model.getSeaName(), model.getHarbourName(), Model.getVp(), 0, 17, Heading.S);
//			model.assignPath(s2, 3);
//			addShip(s2);
//			
//			
//
//		}
		if (model.getTime() >= 0 && model.getTime() <= 0.05 && model.getNumberOfShips() < 1) {
			addRandomShip();
		}
		if (model.getTime() % 3 >= 0 && model.getTime() % 3 <= 0.1 && model.getNumberOfShips() < 10) {
			addRandomShip();
		}
		
		gView.update(this.model.getShipPositions(), this.model.getRequests());
		gView.updateTime(model.getTime());
		gView.updateMoney(model.getMoney());
		model.viewUpdated();
		
	}
	private void addRandomShip() {
		Random r = new Random();
		int ship = r.nextInt(3);
		int path = r.nextInt(3);
		switch (ship) {
		case 0:
			RedShip s = null;
			switch(path) {
			case 0: 
				s = new RedShip("id" + shipCounter, model.getSeaName(), model.getHarbourName(), Model.getVp(), 24, 12, Heading.N);
				model.assignPath(s, 1);
				break;
			case 1: 
				s = new RedShip("id"+ shipCounter, model.getSeaName(), model.getHarbourName(), Model.getVp(), 12, 0, Heading.E);
				model.assignPath(s, 2);
				break;
			case 2: 
				s = new RedShip("id"+ shipCounter, model.getSeaName(), model.getHarbourName(), Model.getVp(), 0, 17, Heading.S);
				model.assignPath(s, 3);
				break;
			}
			addShip(s);
			break;
		case 1:
			GreenShip s1 = null;
			switch(path) {
			case 0: 
				s1 = new GreenShip("id" + shipCounter, model.getSeaName(), model.getHarbourName(), Model.getVp(), 24, 12, Heading.N);
				model.assignPath(s1, 1);
				break;
			case 1: 
				s1 = new GreenShip("id"+ shipCounter, model.getSeaName(), model.getHarbourName(), Model.getVp(), 12, 0, Heading.E);
				model.assignPath(s1, 2);
				break;
			case 2: 
				s1 = new GreenShip("id"+ shipCounter, model.getSeaName(), model.getHarbourName(), Model.getVp(), 0, 17, Heading.S);
				model.assignPath(s1, 3);
				break;
			}
			addShip(s1);
			break;
		case 2:
			YellowShip s2 = null;
			switch(path) {
			case 0: 
				s2 = new YellowShip("id" + shipCounter, model.getSeaName(), model.getHarbourName(), Model.getVp(), 24, 12, Heading.N);
				model.assignPath(s2, 1);
				break;
			case 1: 
				s2 = new YellowShip("id"+ shipCounter, model.getSeaName(), model.getHarbourName(), Model.getVp(), 12, 0, Heading.E);
				model.assignPath(s2, 2);
				break;
			case 2: 
				s2 = new YellowShip("id"+ shipCounter, model.getSeaName(), model.getHarbourName(), Model.getVp(), 0, 17, Heading.S);
				model.assignPath(s2, 3);
				break;
			}
			addShip(s2);
			break;
		}
		shipCounter++;
	}
	public void dockClicked(String dock) {
		String ship = this.gView.getRequestPanel().getMarkedShip();
		if (!ship.equals("")){
			try {
				model.acceptRequest(ship, dock);
				
				this.gView.getGamePanel().setCircleId(ship);
				this.gView.getRequestPanel().setMarkedID(ship);
				updateRequests();
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}


