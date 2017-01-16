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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.cmg.resp.knowledge.Tuple;
import model.Heading;
import model.Map;
import model.ShipType;

public class GamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5206343816254801883L;
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
	
	private BufferedImage crane1;
	private BufferedImage crane2;
	private BufferedImage crane3;
	private BufferedImage crane4;
	private BufferedImage crane5;
	private BufferedImage crane6;
	
	private JLabel money;
	private JLabel time;
	private int mapHeigth;
	private int mapWidth;
	private String circleId;
	
	private JButton dock1;
	private JButton dock2;
	private JButton dock3;
	
	public GamePanel(int mapHeigth, int mapWidth) {
		super();
		this.setLayout(null);
		this.mapHeigth = mapHeigth;
		this.mapWidth = mapWidth;
		this.ships = new ArrayList<Tuple>();
		money = new JLabel("Money:     ");
		money.setBounds(250, 0, 70,40);
		time = new JLabel("Time:      ");
		time.setBounds(350, 0, 70,40);
		circleId = "";
		
		this.add(money);
		this.add(time);
		
		this.dock1 = new JButton();
		this.add(dock1);
		dock1.setBounds((int)21.5*32, 15*32-4, 70, 42);
		dock1.setBorderPainted(false);
		dock1.setActionCommand("dock1");
		
		this.dock2 = new JButton();
		this.add(dock2);
		dock2.setBounds((int)21.5*32, 13*32-4, 70, 42);
		dock2.setBorderPainted(false);
		dock2.setActionCommand("dock2");
		
		this.dock3 = new JButton();
		this.add(dock3);
		dock3.setBounds((int)21.5*32, 11*32-4, 70, 42);
		dock3.setBorderPainted(false);
		dock3.setActionCommand("dock3");

		// this.grid = map.getGrid();
		try {
			System.out.println(getClass().getResource("/resources/map.png"));
			this.background = ImageIO.read(getClass().getResource("/resources/map.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// this.setBackground(new Color(102,200,209));
		try {
			this.crane1 = ImageIO.read(getClass().getResource("/resources/crane1.png"));
			this.crane2 = ImageIO.read(getClass().getResource("/resources/crane2.png"));
			this.crane3 = ImageIO.read(getClass().getResource("/resources/crane3.png"));
			this.crane4 = ImageIO.read(getClass().getResource("/resources/crane4.png"));
			this.crane5 = ImageIO.read(getClass().getResource("/resources/crane5.png"));
			this.crane6 = ImageIO.read(getClass().getResource("/resources/crane6.png"));
			
			BufferedImage redShip = ImageIO.read(getClass().getResource("/resources/redShip.png"));
			this.redShipE = redShip;
			this.redShipNE = ImageIO.read(getClass().getResource("/resources/redShipNE.png"));
			this.redShipN = ImageIO.read(getClass().getResource("/resources/redShipN.png"));
			this.redShipNW = ImageIO.read(getClass().getResource("/resources/redShipNW.png"));
			this.redShipW = ImageIO.read(getClass().getResource("/resources/redShipW.png"));
			this.redShipSW = ImageIO.read(getClass().getResource("/resources/redShipSW.png"));
			this.redShipS = ImageIO.read(getClass().getResource("/resources/redShipS.png"));
			this.redShipSE = ImageIO.read(getClass().getResource("/resources/redShipSE.png"));

			BufferedImage greenShip = ImageIO.read(getClass().getResource("/resources/greenShip.png"));
			this.greenShipE = greenShip;
			this.greenShipNE = ImageIO.read(getClass().getResource("/resources/greenShipNE.png"));
			this.greenShipN = ImageIO.read(getClass().getResource("/resources/greenShipN.png"));
			this.greenShipNW = ImageIO.read(getClass().getResource("/resources/greenShipNW.png"));
			this.greenShipW = ImageIO.read(getClass().getResource("/resources/greenShipW.png"));
			this.greenShipSW = ImageIO.read(getClass().getResource("/resources/greenShipSW.png"));
			this.greenShipS = ImageIO.read(getClass().getResource("/resources/greenShipS.png"));
			this.greenShipSE = ImageIO.read(getClass().getResource("/resources/greenShipSE.png"));

			BufferedImage yellowShip = ImageIO.read(getClass().getResource("/resources/yellowShip.png"));
			this.yellowShipE = yellowShip;
			this.yellowShipNE = ImageIO.read(getClass().getResource("/resources/yellowShipNE.png"));
			this.yellowShipN = ImageIO.read(getClass().getResource("/resources/yellowShipN.png"));
			this.yellowShipNW = ImageIO.read(getClass().getResource("/resources/yellowShipNW.png"));
			this.yellowShipW = ImageIO.read(getClass().getResource("/resources/yellowShipW.png"));
			this.yellowShipSW = ImageIO.read(getClass().getResource("/resources/yellowShipSW.png"));
			this.yellowShipS = ImageIO.read(getClass().getResource("/resources/yellowShipS.png"));
			this.yellowShipSE = ImageIO.read(getClass().getResource("/resources/yellowShipSE.png"));

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
		g.drawImage(crane1, getActualX(24), getActualY(9), getActualX(1) + 300 ,  getActualY(1) + 300, null);
		Graphics2D g2d = (Graphics2D) g;
		int extra = 0;
		int n = 10;
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
				extra = n;
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
				extra = n;
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
				extra = n;
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
				extra = n;
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
					System.out.println("Ship img not found");
					break;
				}
			}
			g2d.drawImage(img, getActualX(col)-extra, getActualY(row)-extra, getActualX(1)+extra+10, getActualY(1)+extra+10, null);
			
		}
		if (!circleId.equals("")) {
			drawCircle(g);
		}
	}

	// Calculates the ratio between window size and grid size and multiplies it
	// by a given number
	private int getActualY(double k) {
		return (int) (k * this.getHeight() / this.mapHeigth)-4;
	}

	private int getActualX(double col) {
		return (int) (col * this.getWidth() / this.mapWidth)-4;
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
	
	public void updateMoney(int money) {
		this.money.setText("Money: " + money);
	}

//	private BufferedImage getRotatedInstance(BufferedImage img, double rotation) {
//		int w0 = img.getWidth();
//		int h0 = img.getHeight();
//		int centerX = w0 / 2;
//		int centerY = h0 / 2;
//		if (w0 > h0) {
//			centerX = h0 / 2;
//			centerY = h0 / 2;
//		} else if (h0 > w0) {
//			centerX = w0 / 2;
//			centerY = w0 / 2;
//		}
//		AffineTransform tx = AffineTransform.getRotateInstance(rotation, centerX, centerY);
//		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
//		return op.filter(img, null);
//	}
	public void drawCircle( Graphics g) {
		for (Tuple t : ships) {
			if (t.getElementAt(String.class, 0).equals(circleId)) {
				double x =  t.getElementAt(Double.class, 3);
				double y =  t.getElementAt(Double.class, 2);
				g.setColor(Color.RED);
				g.drawOval(getActualX(x)-5, getActualY(y)-3, 42, 42);
			}
		}
	}
	public void setCircleId(String id) {
		if (this.circleId.equals(id)) {
			this.circleId = "";
		}else {
		this.circleId = id;
		}
	}
	public ArrayList<JButton> getDockButtons() {
		ArrayList<JButton> d = new ArrayList<>();
		d.add(dock1);
		d.add(dock2);
		d.add(dock3);
		return d;
	}

}
