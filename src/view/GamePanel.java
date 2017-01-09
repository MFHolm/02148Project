package view;

import java.awt.Color;
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
	}
	public void setMap(Map map) {
		this.map = map;
	}
	//Paints the background on the panel
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		ArrayList<Tuple> ships = map.getShipPositions();
		for (Tuple tuple : ships) {
			ShipType type = tuple.getElementAt(ShipType.class, 1);
			int row = tuple.getElementAt(Integer.class, 2);
			int col = tuple.getElementAt(Integer.class, 3);
			Heading dir = tuple.getElementAt(Heading.class, 4);
			BufferedImage img = null;
			double theta;
			switch (dir) {
				case NE: 
					theta = Math.PI/4;
					break;
				case N: 
					theta =  Math.PI/2;;
					break;
				case NW:
					theta =  3 * Math.PI/4;
					break;
				case W:
					theta =  Math.PI;
					break;
				case SW:
					theta =  5* Math.PI/4;
					break;
				case S:
					theta = 3* Math.PI/2;
					break;
				case SE:
					theta = 7 * Math.PI /4;
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
			double scaleX = (this.getWidth()/(double)map.getWidth())/img.getWidth();
			double scaleY = (this.getHeight()/(double)map.getHeight())/ img.getHeight();
			int boatLength = this.getWidth()/map.getWidth();
			int boatHeigth = this.getHeight()/map.getHeight();
			int x = col* boatLength;
			int y = row* boatHeigth;
			g2d.scale(scaleX, scaleY);
			System.out.println("x: " + x + " y: " + y);
			System.out.println("boat length " + boatLength + " boat heigth " + boatHeigth);
			System.out.println("window: "+this.getWidth() + " ," + this.getHeight());
			g2d.drawImage(op.filter(img, null),x,y, null);
//	        g2d.rotate(theta, img.getWidth()/2, img.getHeight()/2);
//	        g2d.scale(0.5, 0.5);
//			g2d.drawImage(img, col*this.getWidth()/map.getWidth(), row*this.getHeight()/map.getHeight(), null);
		}
		
		
	}

}

