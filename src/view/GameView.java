package view;


import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

import org.cmg.resp.knowledge.Tuple;

import model.Harbour;
import model.Map;


public class GameView extends JFrame {
	
	GamePanel gamePanel;
	RequestPanel requestPanel;
	
	
	public GameView(int mapHeigth, int mapWidth) {
		initUI(mapHeigth, mapWidth);
//		this.grid = grid;
	}
	
	public void initUI(int mapHeigth, int mapWidth) {

		gamePanel = new GamePanel(mapHeigth, mapWidth);
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
	public void update(ArrayList<Tuple> ships, LinkedList<Tuple> requests) {
		gamePanel.setShipPositions(ships);
		gamePanel.repaint();

		
	}
	
	public void updateTime(double time) {
		this.gamePanel.updateTime(time);
	}
	public RequestPanel getRequestPanel() {
		return requestPanel;
	}
	public GamePanel getGamePanel() {
		return gamePanel;
	}

}
