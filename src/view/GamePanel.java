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

	private ArrayList<Tuple> ships;
	private BufferedImage background;
	private Map map;
	private int[][] grid;
	private BufferedImage redShipE;
	private BufferedImage redShipNE;
	private BufferedImage redShipN;
	private BufferedImage redShipNW;
	private BufferedImage redShipW;
	private BufferedImage redShipSW;
	private BufferedImage redShipS;
	private BufferedImage redShipSE;

	private BufferedImage greenShipE;
	private BufferedImage greenShipNE;
	private BufferedImage greenShipN;
	private BufferedImage greenShipNW;
	private BufferedImage greenShipW;
	private BufferedImage greenShipSW;
	private BufferedImage greenShipS;
	private BufferedImage greenShipSE;

	private BufferedImage yellowShipE;
	private BufferedImage yellowShipNE;
	private BufferedImage yellowShipN;
	private BufferedImage yellowShipNW;
	private BufferedImage yellowShipW;
	private BufferedImage yellowShipSW;
	private BufferedImage yellowShipS;
	private BufferedImage yellowShipSE;

	private JLabel money;
	private JLabel time;
	private int mapHeigth;
	private int mapWidth;
	public GamePanel(int mapHeigth, int mapWidth) {
		super();
		this.mapHeigth = mapHeigth;
		this.mapWidth = mapWidth;
		this.ships = new ArrayList<Tuple>();
		money = new JLabel("Money:     ");
		time = new JLabel("Time:      ");

		this.add(money);
		this.add(time);

		// this.grid = map.getGrid();
		try {
			this.background = ImageIO.read(getClass().getResource("/resources/map.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// this.setBackground(new Color(102,200,209));
		try {
			BufferedImage redShip = ImageIO.read(getClass().getResource("/resources/redShip.png"));
			this.redShipE = redShip;
			this.redShipNE = this.getRotatedInstance(redShip, Math.toRadians(-45));
			this.redShipN = this.getRotatedInstance(redShip, Math.toRadians(-90));
			this.redShipNW = this.getRotatedInstance(redShip, Math.toRadians(-135));
			this.redShipW = this.getRotatedInstance(redShip, Math.toRadians(180));
			this.redShipSW = this.getRotatedInstance(redShip, Math.toRadians(135));
			this.redShipS = this.getRotatedInstance(redShip, Math.toRadians(90));
			this.redShipSE = this.getRotatedInstance(redShip, Math.toRadians(45));

			BufferedImage greenShip = ImageIO.read(getClass().getResource("/resources/greenShip.png"));
			this.greenShipE = greenShip;
			this.greenShipNE = this.getRotatedInstance(greenShip, Math.toRadians(-45));
			this.greenShipN = this.getRotatedInstance(greenShip, Math.toRadians(-90));
			this.greenShipNW = this.getRotatedInstance(greenShip, Math.toRadians(-135));
			this.greenShipW = this.getRotatedInstance(greenShip, Math.toRadians(180));
			this.greenShipSW = this.getRotatedInstance(greenShip, Math.toRadians(135));
			this.greenShipS = this.getRotatedInstance(greenShip, Math.toRadians(90));
			this.greenShipSE = this.getRotatedInstance(greenShip, Math.toRadians(45));

			BufferedImage yellowShip = ImageIO.read(getClass().getResource("/resources/yellowShip.png"));
			this.yellowShipE = yellowShip;
			this.yellowShipNE = this.getRotatedInstance(yellowShip, Math.toRadians(-45));
			this.yellowShipN = this.getRotatedInstance(yellowShip, Math.toRadians(-90));
			this.yellowShipNW = this.getRotatedInstance(yellowShip, Math.toRadians(-135));
			this.yellowShipW = this.getRotatedInstance(yellowShip, Math.toRadians(180));
			this.yellowShipSW = this.getRotatedInstance(yellowShip, Math.toRadians(135));
			this.yellowShipS = this.getRotatedInstance(yellowShip, Math.toRadians(90));
			this.yellowShipSE = this.getRotatedInstance(yellowShip, Math.toRadians(45));

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(800, 800));
	}

	public void setShipPositions(ArrayList<Tuple> ships) {
		this.ships = ships;
	}
	// Paints the background on the panel
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		Graphics2D g2d = (Graphics2D) g;
		for (Tuple tuple : this.ships) {
			ShipType type = tuple.getElementAt(ShipType.class, 1);
			double row = tuple.getElementAt(Double.class, 2);
			double col = tuple.getElementAt(Double.class, 3);
			Heading dir = tuple.getElementAt(Heading.class, 4);
			BufferedImage img = null;
			switch (dir) {
			case NE:
				switch (type) {
				case RED:
					img = redShipNE;
					break;
				case GREEN:
					img = greenShipNE;
					break;
				case YELLOW:
					img = yellowShipNE;
					break;
				default:
					System.out.println("Ship img not found");
					break;
				}
				break;
			case N:
				switch (type) {
				case RED:
					img = redShipN;
					break;
				case GREEN:
					img = greenShipN;
					break;
				case YELLOW:
					img = yellowShipN;
					break;
				default:
					System.out.println("Ship img not found");
					break;
				}
				break;
			case NW:
				switch (type) {
				case RED:
					img = redShipNW;
					break;
				case GREEN:
					img = greenShipNW;
					break;
				case YELLOW:
					img = yellowShipNW;
					break;
				default:
					System.out.println("Ship img not found");
					break;
				}
				break;
			case W:
				switch (type) {
				case RED:
					img = redShipW;
					break;
				case GREEN:
					img = greenShipW;
					break;
				case YELLOW:
					img = yellowShipW;
					break;
				default:
					System.out.println("Ship img not found");
					break;
				}
				break;
			case SW:
				switch (type) {
				case RED:
					img = redShipSW;
					break;
				case GREEN:
					img = greenShipSW;
					break;
				case YELLOW:
					img = yellowShipSW;
					break;
				default:
					System.out.println("Ship img not found");
					break;
				}
				break;
			case S:
				switch (type) {
				case RED:
					img = redShipS;
					break;
				case GREEN:
					img = greenShipS;
					break;
				case YELLOW:
					img = yellowShipS;
					break;
				default:
					System.out.println("Ship img not found");
					break;
				}
				break;
			case SE:
				switch (type) {
				case RED:
					img = redShipSE;
					break;
				case GREEN:
					img = greenShipSE;
					break;
				case YELLOW:
					img = yellowShipSE;
					break;
				default:
					System.out.println("Ship img not found");
					break;
				}
				break;
			default:
				switch (type) {
				case RED:
					img = redShipE;
					break;
				case GREEN:
					img = greenShipE;
					break;
				case YELLOW:
					img = yellowShipE;
					break;
				default:
					System.out.println("Ship img not found7");
					break;
				}
			}
			g2d.drawImage(img, getActualX(col), getActualY(row), getActualX(1) + 1, getActualY(1) + 1, null);
			
		}

	}

	// Calculates the ratio between window size and grid size and multiplies it
	// by a given number
	private int getActualY(double k) {
		return (int) (k * this.getHeight() / this.mapHeigth);
	}

	private int getActualX(double col) {
		return (int) (col * this.getWidth() / this.mapWidth);
	}

	public void updateTime(double time) {
		time = Math.round(time * 100) / 100;
		int min = (int) (time / 60);
		int sec = (int) (time % 60);
		String remaining;
		if (sec == 0) {
			remaining = (5 - min) + ":00";
		} else if (sec > 50) {
			remaining = (4 - min) + ":0" + (60 - sec);
		} else {
			remaining = (4 - min) + ":" + (60 - sec);
		}
		this.time.setText("Time: " + remaining);
	}

	private BufferedImage getRotatedInstance(BufferedImage img, double rotation) {
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
		AffineTransform tx = AffineTransform.getRotateInstance(rotation, centerX, centerY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(img, null);
	}

}
