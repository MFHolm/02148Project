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
			System.out.println("waiting");
			try { wait();
			System.out.println("done waiting");} 
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
		System.out.println("n: "+ n + " numOfShips: "+ numOfShips);
		while(n < numOfShips) {
			try {
				System.out.println("waiting for ships");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("woken");
		}
	}

	public synchronized void setWaitingFor(int size) {
		numOfShips = size;
	}

}
