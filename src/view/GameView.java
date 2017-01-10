package view;


import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Map;


public class GameView extends JFrame {
	
	GamePanel mainView;
	private int[][] grid;
	
	
	public GameView() {
		initUI();
//		this.grid = grid;
	}
	
	public void initUI() {
		mainView = new GamePanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mainView);
		this.setSize(1080, 1920);
		this.setVisible(false);
	
	}
	//Repaints the main panel
	public void update() {
		mainView.repaint();
	}
	public void setMap(Map map) {
		this.mainView.setMap(map);
	}
}
