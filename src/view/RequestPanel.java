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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import model.ShipType;

public class RequestPanel extends JPanel {

	public RequestPanel() {

		// this.add(Box.createRigidArea(new Dimension(250,0)));
		// this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	public void update() {
		final int borderWidth = 1;
		final int rows = 10;
		final int cols = 3;
		this.setLayout(new GridLayout(rows, cols));
		this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				final JLabel label;
				if (row == 0 && col == 0) {
					  label = new JLabel("Type");
				}else if (row == 0 && col == 1) {
					  label = new JLabel("Time");
				}else if (row == 0 && col == 2) {
					  label = new JLabel("Money");
				}else {
					label = new JLabel();
//					BufferedImage img = null;
//					try {
//						img = ImageIO.read(new File("/resources/yellowShip.png"));
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(),
//							Image.SCALE_SMOOTH);
//					ImageIcon imageIcon = new ImageIcon(dimg);
//					label.setIcon(imageIcon);
					ImageIcon imageIcon = new ImageIcon(new ImageIcon("/resources/yellowShip.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
					label.setIcon(imageIcon);
					}
				label.setHorizontalAlignment(JLabel.CENTER);
				if (row == 0) {
					if (col == 0) {
						// Top left corner, draw all sides
						label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					} else {
						// Top edge, draw all sides except left edge
						label.setBorder(
								BorderFactory.createMatteBorder(borderWidth, 0, borderWidth, borderWidth, Color.BLACK));
					}
				} else {
					if (col == 0) {
						// Left-hand edge, draw all sides except top
						label.setBorder(
								BorderFactory.createMatteBorder(0, borderWidth, borderWidth, borderWidth, Color.BLACK));
					} else {
						// Neither top edge nor left edge, skip both top and
						// left lines
						label.setBorder(BorderFactory.createMatteBorder(0, 0, borderWidth, borderWidth, Color.BLACK));
					}
				}
				this.add(label);
			}
		}
	}

}
