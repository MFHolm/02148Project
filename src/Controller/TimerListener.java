package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {

	MainController controller;
	public TimerListener(MainController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		controller.incrementTimer();
			
		try {
			controller.run();
		} catch (InterruptedException e1) {
			System.out.println("Something went wrong");
		}
	}
}
