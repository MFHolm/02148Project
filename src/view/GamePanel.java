package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.cmg.resp.knowledge.Tuple;

import model.Heading;
import model.Map;
import model.ShipType;


public class GamePanel extends JPanel {

	private BufferedImage background;
	private Map map;
	private int[][] grid;
	private BufferedImage redShip;
	private BufferedImage greenShip;
	private BufferedImage yellowShip;
	
	
	
	public GamePanel(Map map) {
		super();
		this.map = map;
//		this.grid = map.getGrid();
		this.setBackground(new Color(102,200,209));
	}
	
	//Paints the background on the panel
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		Tuple[] ships = map.getShipPositions();
		for (Tuple tuple : ships) {
			ShipType type = tuple.getElementAt(ShipType.class, 1);
			int row = tuple.getElementAt(Integer.class, 2);
			int col = tuple.getElementAt(Integer.class, 3);
			Heading dir = tuple.getElementAt(Heading.class, 4);
			BufferedImage img = null;
			double theta;
			switch (dir) {
				case NE: 
					theta = 45;
					break;
				case N: 
					theta = 90;
					break;
				case NW:
					theta = 135;
					break;
				case W:
					theta = 180;
					break;
				case SW:
					theta = 225;
					break;
				case S:
					theta = 270;
					break;
				case SE:
					theta = 315;
					break;
				default: 
					theta = 0;
			}
			switch (type) {
				case RED:
					img = redShip;
					break;
				case GREEN:
					img = greenShip;
					break;
				case YELLOW:
					img = yellowShip;
					break;
				default:
					System.out.println("Ship img not found");
					break;
			}
						
			g2d.rotate(theta);
			g2d.drawImage(img, col*this.getWidth()/map.getWidth(), row*this.getHeight()/map.getHeight(), null);
		}
		
		
	}

}

