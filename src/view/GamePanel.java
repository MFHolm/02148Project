package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GamePanel extends JPanel {

	private BufferedImage background;
	private int[][] grid;
	
	public GamePanel(int[][] grid) {
		super();
		this.grid = grid;
		this.setBackground(new Color(102,200,209));
	}
	
	//Paints the background on the panel
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		
		
		
	}

}

