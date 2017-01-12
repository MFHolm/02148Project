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
import javax.swing.JLabel;
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
	private JLabel money;
	private JLabel time;
	
	
	
	public GamePanel() {
		super();
		money = new JLabel("Money:     ");
		time = new JLabel("Time:      ");
		
		this.add(money);
		this.add(time);
		
//		this.grid = map.getGrid();
		try {
			this.background = ImageIO.read(getClass().getResource("/resources/map.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setBackground(new Color(102,200,209));
		try {
			this.greenShip = ImageIO.read(getClass().getResource("/resources/greenShip.png"));
			this.redShip = ImageIO.read(getClass().getResource("/resources/redShip.png"));
			this.yellowShip = ImageIO.read(getClass().getResource("/resources/yellowShip.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(800,800));
	}
	public void setMap(Map map) {
		this.map = map;
	}
	//Paints the background on the panel
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
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
			int w0 = img.getWidth();
			int h0 = img.getHeight();
			int centerX = w0 / 2;
			int centerY = h0 / 2;
			if (w0 > h0) {
				centerX = h0 / 2;
				centerY = h0 / 2;
			} else if (h0 > w0) {
				centerX = w0 / 2;
				centerY = w0 / 2;
			}
			AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, centerX, centerY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		//g2d.drawImage(op.filter(img, null),x,y, null);
			g2d.drawImage(op.filter(img, null), getActualX(col), getActualY(row), getActualX(1) + 1, getActualY(1) + 1, null);
			
			
			
			AffineTransform affineTransform = new AffineTransform();
			affineTransform.setToQuadrantRotation(1, centerX, centerY);
			
			AffineTransformOp opRotated = new AffineTransformOp(affineTransform,
					AffineTransformOp.TYPE_BILINEAR);
			
			BufferedImage transformedImage = new BufferedImage(w0, h0,
					img.getType());
			
			transformedImage = opRotated.filter(img, transformedImage);
			//g2d.drawImage(transformedImage, getActualX(col), getActualY(row), getActualX(1) + 1, getActualY(1) + 1, null);
			
		}
		
		
	}
	//Calculates the ratio between window size and grid size and multiplies it by a given number
	private int getActualY(double k) {
		return (int) (k * this.getHeight() / map.getHeight());
	}

	private int getActualX(double col) {
		return (int) (col * this.getWidth() / map.getWidth());
	}
	public void updateTime(double time) {
		time = Math.round(time * 100)/100;
		int min = (int) (time / 60);
		int sec = (int) (time % 60);
		String remaining;
		if (sec == 0) {
			remaining = (5-min)+ ":00";
		}
		else if (sec > 50) {
			remaining = (4-min)+ ":0" + (60-sec);
		}
		else {
			remaining = (4-min)+ ":" + (60-sec);
		}
		this.time.setText("Time: " + remaining);
	}
	
}

