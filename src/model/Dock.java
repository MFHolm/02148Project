package model;

public class Dock {
	private int row,col;
	private int money;
	private double timeLeft;
	private String shipId;
	
	public Dock(int row, int col){
		this.row = row;
		this.col = col;
		this.timeLeft = 0;
	
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	public int emptyMoney() {
		int temp = money;
		money = 0;
		return temp;
	}
	public void decrementTimeLeft(double t) {
		this.timeLeft -= t;
	}
	public double getTimeLeft() {
		return timeLeft;
	}
	public boolean updateDock(double time) {
		
		if (timeLeft > 0) {
			decrementTimeLeft(time);
			if (timeLeft <= 0) {
				return true;
			}
		}
		return false;
		
	}

	public void shipArrives(String shipId, int time, int money) {
		this.shipId = shipId;
		this.timeLeft = time;
		this.money = money;
		
	}
	public String shipLeaves() {
		String temp = shipId;
		shipId = "";
		return temp;
	}
}
