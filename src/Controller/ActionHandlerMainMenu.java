package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandlerMainMenu implements ActionListener {
	
	MainController mainC;
	
	public ActionHandlerMainMenu(MainController mainC) {
		this.mainC = mainC;
		init();
	}
	
	public void init() {
		
		mainC.mMenu.getPlayBtn().addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		switch(actionCommand) {
		case "Play":
			mainC.createModel();
			mainC.mMenu.setVisible(false);
			mainC.gView.setVisible(true);
			mainC.run();
		}
		
	}
	

}
