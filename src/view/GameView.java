package view;


import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameView extends JFrame {
	
	JPanel mainView;
	private int[][] grid;
	
	
	public GameView(int[][] grid) {
		initUI();
		this.grid = grid;
	}
	
	public void initUI() {
		mainView = new GamePanel(grid);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mainView);
		this.setSize(1080, 1920);
		this.setVisible(false);
	
	}
	
}
