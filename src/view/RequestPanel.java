package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.cmg.resp.knowledge.Tuple;

import layout.SpringUtilities;
import model.Harbour;
import model.ShipType;

public class RequestPanel extends JPanel {
	
	Harbour harbour;
	ImageIcon yellowShip;
	ImageIcon redShip;
	ImageIcon greenShip;

	public RequestPanel() {
		this.setPreferredSize(new Dimension(250,800));
		BufferedImage img;
		try {
			img = ImageIO.read(new File(getClass().getResource("/resources/yellowShip.png").toURI()));
			Image dimg = img.getScaledInstance(70, 40,
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
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.add(Box.createRigidArea(new Dimension(250,0)));
		// this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	public void clear() {
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	
	public void setHabour(Harbour harbour) {
		this.harbour = harbour;
	}
	
	public void update() {
		final int borderWidth = 1;
		final int rows = 26;
		final int cols = 3;
		LinkedList<Tuple> requests = harbour.getRequests();
		this.setLayout(new GridLayout(rows, cols));
		this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				final JButton label;
				if (row == 0 && col == 0) {
					  label = new JButton("Type");
				}else if (row == 0 && col == 1) {
					  label = new JButton("Time");
				}else if (row == 0 && col == 2) {
					  label = new JButton("$");
				}else if (row <= requests.size()){
					if (col == 0) {
						switch ((ShipType) requests.get(row -1).getElementAt(col+2)) {
						case YELLOW: 
							label = new JButton(yellowShip);
							break;
						case RED: 
							label = new JButton(redShip);
							break;
						case GREEN: 
							label = new JButton(greenShip);
							break;
						default:
							label = new JButton(greenShip);
							System.out.println("Ship type not found in request panel");
							break;
						}
					}
					else {
					String text = requests.get(row -1).getElementAt(col+2).toString();
					label = new JButton(text);
					}
				}
				else {
					label = new JButton();
				}
				label.setHorizontalAlignment(JLabel.CENTER);
				
				label.setMinimumSize(new Dimension(this.getPreferredSize().width-1, 
						(int)this.getPreferredSize().getHeight()));
				label.setBorderPainted( false );
				this.add(label);
			}
		}
	}

}
