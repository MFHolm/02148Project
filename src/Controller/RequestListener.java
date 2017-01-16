package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RequestListener implements ActionListener {

	MainController mainC;

	public RequestListener(MainController mainC) {
		this.mainC = mainC;
	}

	public void init() {

		
		for (JButton b : mainC.gView.getRequestPanel().getButtons()) {
			b.addActionListener(this);
		} 

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = e.getActionCommand();
		mainC.gView.getRequestPanel().setMarkedID(id);
		mainC.gView.getRequestPanel().clear();
		mainC.gView.getRequestPanel().update();
		mainC.gView.getRequestPanel().repaint();
		init();
		
		
		mainC.gView.getGamePanel().setCircleId(id);
	}
}
