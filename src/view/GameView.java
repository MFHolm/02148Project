package view;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Map;


public class GameView extends JFrame {
	
	GamePanel mainView;
	private int[][] grid;
	RequestPanel requestView;
	
	
	public GameView() {
		initUI();
//		this.grid = grid;
	}
	
	public void initUI() {

		mainView = new GamePanel();
		requestView = new RequestPanel();
		

		
		
		
		Box content = new Box(BoxLayout.X_AXIS);

		content.add(requestView);
		content.add(mainView);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.add(mainView);
		this.setSize(1250, 1000);
		this.setResizable(false);
		this.setContentPane(content);
		this.setVisible(false);
	
		
	}
	//Repaints the main panel
	public void update() {
		mainView.repaint();
		requestView.repaint();
		
	}
	public void setMap(Map map) {
		this.mainView.setMap(map);
	}
}
