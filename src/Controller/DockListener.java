package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DockListener implements ActionListener {
	MainController mainC;

	public DockListener(MainController mainC) {
		this.mainC = mainC;
		
	}
	public void init() {
		for (JButton d : mainC.gView.getGamePanel().getDockButtons()) {
			d.addActionListener(this);
		} 

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String dock = e.getActionCommand();
		this.mainC.dockClicked(dock);
		
	}

}
