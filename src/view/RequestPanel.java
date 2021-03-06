package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.cmg.jresp.knowledge.Tuple;

import model.Harbour;
import model.ShipType;

public class RequestPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5444611754376978388L;
	Harbour harbour;
	ImageIcon yellowShip;
	ImageIcon redShip;
	ImageIcon greenShip;
	ImageIcon yellowShipMarked;
	ImageIcon redShipMarked;
	ImageIcon greenShipMarked;
	ImageIcon decline;
	LinkedList<Tuple> requests;
	ArrayList<JButton> buttons;
	String markedId;
	
	public RequestPanel() {
		this.setPreferredSize(new Dimension(370,800));
		BufferedImage img;
		try {
			img = ImageIO.read(new File(getClass().getResource("/resources/decline.png").toURI()));
			Image dimg = img.getScaledInstance(30, 30,
					Image.SCALE_SMOOTH);
			decline = new ImageIcon(dimg);
			
			img = ImageIO.read(new File(getClass().getResource("/resources/yellowShip.png").toURI()));
			dimg = img.getScaledInstance(70, 40,
					Image.SCALE_SMOOTH);
			yellowShip = new ImageIcon(dimg);
			
			img = ImageIO.read(new File(getClass().getResource("/resources/greenShip.png").toURI()));
			dimg = img.getScaledInstance(70, 40,
					Image.SCALE_SMOOTH);
			greenShip = new ImageIcon(dimg);
			
			img = ImageIO.read(new File(getClass().getResource("/resources/redShip.png").toURI()));
			dimg = img.getScaledInstance(70, 40,
					Image.SCALE_SMOOTH);
			redShip = new ImageIcon(dimg);
			
			img = ImageIO.read(new File(getClass().getResource("/resources/yellowShipIcon.png").toURI()));
			dimg = img.getScaledInstance(70, 40,
					Image.SCALE_SMOOTH);
			yellowShipMarked = new ImageIcon(dimg);
			
			img = ImageIO.read(new File(getClass().getResource("/resources/greenShipIcon.png").toURI()));
			dimg = img.getScaledInstance(70, 40,
					Image.SCALE_SMOOTH);
			greenShipMarked  = new ImageIcon(dimg);
			
			img = ImageIO.read(new File(getClass().getResource("/resources/redShipIcon.png").toURI()));
			dimg = img.getScaledInstance(70, 40,
					Image.SCALE_SMOOTH);
			redShipMarked  = new ImageIcon(dimg);
			
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.add(Box.createRigidArea(new Dimension(250,0)));
		// this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.requests = new LinkedList<Tuple>();
		this.buttons = new ArrayList<>();
		this.markedId = "";
		update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	public void clear() {
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	
	public void setRequests(LinkedList<Tuple> requests) {
		this.requests = requests;
	}
	public void setMarkedID(String id) {
		if (this.markedId.equals(id)) {
			this.markedId = "";
		}
		else {
			this.markedId = id;
		}
	}
	public String getMarkedShip() {
		return this.markedId;
	}
	public void update() {
		final int rows = 26;
		final int cols = 4;
		this.setLayout(new GridLayout(rows, cols));
		this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				final JButton button;
				if (row == 0 && col == 0) {
					  button = new JButton("Type");
				}else if (row == 0 && col == 1) {
					  button = new JButton("Time");
				}else if (row == 0 && col == 2) {
					  button = new JButton("$");
				}else if (row ==0 && col == 3) {
					  button = new JButton("Decline");
				}else if (row <= requests.size()){
					if (col == 0) {
						boolean marked = false;
						if (this.markedId.equals(requests.get(row -1).getElementAt(1))) {
							marked = true;
						}
						switch ((ShipType) requests.get(row -1).getElementAt(col+2)) {
						case YELLOW: 
							if (marked) {
								button = new JButton(yellowShipMarked);
							}else {
								button = new JButton(yellowShip);
							}
							break;
						case RED: 
							if (marked) {
								button = new JButton(redShipMarked);
							}else {
							button = new JButton(redShip);
							}
							break;
						case GREEN: 
							if (marked) {
								button = new JButton(greenShipMarked);
							}else {
							button = new JButton(greenShip);
							}
							break;
						default:
							button = new JButton(greenShip);
							System.out.println("Ship type not found in request panel");
							break;
						}
						button.setActionCommand("a" + (String) requests.get(row -1).getElementAt(1));
						buttons.add(button);
					}
					else if(col == 3){
						button = new JButton(decline);
						button.setActionCommand("d" + (String) requests.get(row -1).getElementAt(1));
						buttons.add(button);
						
					}
					else {
					String text = requests.get(row -1).getElementAt(col+2).toString();
					button = new JButton(text);
					}
				}
				else {
					button = new JButton();
				}
				button.setHorizontalAlignment(JLabel.CENTER);
				
				button.setMinimumSize(new Dimension(this.getPreferredSize().width-1, 
						(int)this.getPreferredSize().getHeight()));
				button.setBorderPainted( false );
				
				this.add(button);
			}
		}
	}
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

}
