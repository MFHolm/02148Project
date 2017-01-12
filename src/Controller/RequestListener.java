package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequestListener implements ActionListener {

	MainController mainC;

	public RequestListener(MainController mainC) {
		this.mainC = mainC;
		init();
	}

	public void init() {

		mainC.mMenu.getPlayBtn().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		switch (actionCommand) {
		
		}

	}
}
