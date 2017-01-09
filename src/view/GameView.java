package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JFrame {
	
	JPanel mainView;
	
	
	public GameView() {
		initUI();
	}
	
	public void initUI() {
		mainView = new JPanel();
		
		mainView.setBackground(Color.blue);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mainView);
		this.setSize(1080, 1920);
		this.setVisible(false);
	}
}
