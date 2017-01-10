package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

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
	
	
	
	public GamePanel() {
		super();
//		this.grid = map.getGrid();
		this.setBackground(new Color(102,200,209));
		try {
			this.greenShip = ImageIO.read(getClass().getResource("/resources/greenShip.png"));
			this.redShip = ImageIO.read(getClass().getResource("/resources/redShip.png"));
			this.yellowShip = ImageIO.read(getClass().getResource("/resources/yellowShip.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(1080,1920));
	}
	public void setMap(Map map) {
		this.map = map;
	}
	//Paints the background on the panel
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
		ArrayList<Tuple> ships = map.getShipPositions();
		for (Tuple tuple : ships) {
			ShipType type = tuple.getElementAt(ShipType.class, 1);
			double row = tuple.getElementAt(Double.class, 2);
			double col = tuple.getElementAt(Double.class, 3);
			Heading dir = tuple.getElementAt(Heading.class, 4);
			BufferedImage img = null;
			double theta;
			switch (dir) {
				case NE: 
					theta = Math.toRadians(-45);
					break;
				case N: 
					theta = Math.toRadians(-90);;
					break;
				case NW:
					theta =  Math.toRadians(-135);
					break;
				case W:
					theta =  Math.toRadians(180);
					break;
				case SW:
					theta =  Math.toRadians(135);
					break;
				case S:
					theta = Math.toRadians(90);
					break;
				case SE:
					theta = Math.toRadians(45);
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
			double rotationRequired = theta;
			double locationX = img.getWidth() / 2;
			double locationY = img.getHeight() / 2;
			AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			int boatLength = this.getWidth()/map.getWidth();
			int boatHeigth = this.getHeight()/map.getHeight();

			System.out.println("boat length " + boatLength + " boat heigth " + boatHeigth);
			System.out.println("window: "+this.getWidth() + " ," + this.getHeight());
			//g2d.drawImage(op.filter(img, null),x,y, null);
			g2d.drawImage(op.filter(img, null), getActualX(col), getActualY(row), getActualX(1) + 1, getActualY(1) + 1, null);
		}
		
		
	}
	//Calculates the ratio between window size and grid size and multiplies it by a given number
	private int getActualY(double k) {
		return (int) (k * this.getHeight() / map.getHeight());
	}

	private int getActualX(double col) {
		return (int) (col * this.getWidth() / map.getWidth());
	}
}

