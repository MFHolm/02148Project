package view;


import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.Harbour;
import model.Map;


public class GameView extends JFrame {
	
	GamePanel gamePanel;
	RequestPanel requestPanel;
	
	
	public GameView() {
		initUI();
//		this.grid = grid;
	}
	
	public void initUI() {

		gamePanel = new GamePanel();
		requestPanel = new RequestPanel();
		

		this.getContentPane().add(requestPanel, BorderLayout.LINE_START);
		this.getContentPane().add(gamePanel, BorderLayout.LINE_END);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.add(mainView);
		this.setSize(1050, 800);
		this.setResizable(false);
//		this.setContentPane(content);
		pack();
		this.setVisible(false);
	
		
	}
	//Repaints the main panel
	public void update() {
		gamePanel.repaint();
		requestPanel.clear();
		requestPanel.update();
		requestPanel.repaint();
		
	}
	public void setMap(Map map) {
		this.gamePanel.setMap(map);
	}
	
	public void setHarbour(Harbour harbour) {
		this.requestPanel.setHabour(harbour);
	}
	public void updateTime(double time) {
		this.gamePanel.updateTime(time);
	}

}
