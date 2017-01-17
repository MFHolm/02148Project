package view;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;



public class MainMenu extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1511827731298941479L;
	private JPanel menuPanel;
	private JButton play;

	
	public MainMenu() {
		
		initUI();
	}
	
	public void initUI() {
		menuPanel = new JPanel();
		play = new JButton();
		
		play.setText("Play");
		play.setAlignmentX(Component.CENTER_ALIGNMENT);
		play.setActionCommand("Play");
		
		
		menuPanel.add(play);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(menuPanel);
		this.setSize(400, 700);
		this.setVisible(true);
	}
	
	public JButton getPlayBtn() {
		return play;
	}

}
