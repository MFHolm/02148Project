package model;

public class BarrierMonitor {
	
	private boolean viewUpdated = false;
	private int n = 0;
	private int numOfShips = 0;
	
	public BarrierMonitor() {
		
	}
	
	public synchronized void moved() {
		
		n++;
		if(n == numOfShips) {
			notifyAll();
		}
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

	public synchronized void waitingForShips() {
		while(n < numOfShips) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void setWaitingFor(int size) {
		numOfShips = size;
	}

}
