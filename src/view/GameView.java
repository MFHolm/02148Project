package view;


import java.awt.Dimension;
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
	
	
	public GameView() {
		initUI();
//		this.grid = grid;
	}
	
	public void initUI() {

		mainView = new GamePanel();
		JPanel requestView = new JPanel();
		
		  JButton requestViewButton = new JButton();
		  
		  try {
		    Image img = ImageIO.read(getClass().getResource("resources/Icon"));
		   // requestViewButton.setIcon(ImageIO.read(getClass().getResource("/resources/greenShip.png")));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		mainView.add(requestViewButton);
		
		requestView.setLayout(new BoxLayout(requestView, BoxLayout.PAGE_AXIS));
		requestView.add(Box.createRigidArea(new Dimension(250,0)));
		requestView.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
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
	}
	public void setMap(Map map) {
		this.mainView.setMap(map);
	}
}
