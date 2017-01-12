package model;

public class MoveMonitor {
	
	private boolean viewUpdated = false;
	private int n = 0;
	
	public MoveMonitor() {
		
	}
	
	public synchronized void moved() {
		
		n++;
		while(!viewUpdated) {
			try { wait(); } 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		n--;
		if(n == 0) { viewUpdated = false; }
	}
	
	public synchronized void updateView() {
		viewUpdated = true;
		notifyAll();
	}

}
