package model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.cmg.jresp.behaviour.Agent;
import org.cmg.jresp.knowledge.Tuple;
import org.cmg.jresp.knowledge.ts.TupleSpace;
import org.cmg.jresp.topology.PointToPoint;
import org.cmg.jresp.topology.Self;
import org.cmg.jresp.topology.VirtualPort;

public abstract class BasicShip extends Agent {
	protected Coordinate coord;
	protected boolean inTransition = false;
	protected int time;
	protected Heading heading;
	protected String id;
	protected VirtualPort vp;
	protected PointToPoint mapConnection;
	protected PointToPoint harbourConnection;
	protected boolean docked;
	protected ShipSize size;
	protected ShipType shipType;
	protected BarrierMonitor barrier;
	protected List<Coordinate> path;
	protected TupleSpace coordinates;
	protected List<Coordinate> startPath;

	protected int pathIndex = 0;
	protected int startPathIndex = 0;
	private boolean waitingForDeath = false;

	public BasicShip(String shipId, String mapId, String harbourId, VirtualPort vp, int row, int col, Heading h) {
		super(shipId);
		this.id = shipId;
		this.vp = vp;
		this.mapConnection = new PointToPoint(mapId, vp.getAddress());
		this.harbourConnection = new PointToPoint(harbourId, vp.getAddress());
		this.coord = new Coordinate(row, col);
		path = new LinkedList<Coordinate>();
		startPath = new LinkedList<Coordinate>();
		setDocked(false);
		this.heading = h;
	}

	public boolean move(Coordinate nextCoord) {
		Heading curHeading = heading;
		if (heading == headingNewCoord(nextCoord)) {
			return moveForward(nextCoord);
		}

		else if (heading == Heading.N && headingNewCoord(nextCoord) == Heading.E) {
			turnRight();
		} else if (heading == Heading.NE && headingNewCoord(nextCoord) == Heading.E) {
			turnRight();
		} else if (heading == Heading.NE && headingNewCoord(nextCoord) == Heading.N) {
			turnLeft();
		} else if (heading == Heading.N && headingNewCoord(nextCoord) == Heading.W) {
			turnLeft();
		} else if (heading == Heading.NW && headingNewCoord(nextCoord) == Heading.W) {
			turnLeft();
		} else if (heading == Heading.NW && headingNewCoord(nextCoord) == Heading.N) {
			turnRight();
		}

		else if (heading == Heading.E && headingNewCoord(nextCoord) == Heading.S) {
			turnRight();
		} else if (heading == Heading.E && headingNewCoord(nextCoord) == Heading.N) {
			turnLeft();
		}

		else if (heading == Heading.S && headingNewCoord(nextCoord) == Heading.W) {
			turnRight();
		} else if (heading == Heading.SE && headingNewCoord(nextCoord) == Heading.E) {
			turnLeft();
		} else if (heading == Heading.SE && headingNewCoord(nextCoord) == Heading.S) {
			turnRight();
		} else if (heading == Heading.S && headingNewCoord(nextCoord) == Heading.E) {
			turnLeft();
		} else if (heading == Heading.SW && headingNewCoord(nextCoord) == Heading.W) {
			turnRight();
		} else if (heading == Heading.SW && headingNewCoord(nextCoord) == Heading.S) {
			turnLeft();
		}

		else if (heading == Heading.W && headingNewCoord(nextCoord) == Heading.N) {
			turnRight();
		} else if (heading == Heading.W && headingNewCoord(nextCoord) == Heading.S) {
			turnLeft();
		}
		if (curHeading != heading) {
			try {
				get(Templates.getCoordTemp(id), mapConnection);
				put(new Tuple(id, shipType, coord.row, coord.col, heading), mapConnection);
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public Heading headingNewCoord(Coordinate nextCoord) {
		if (coord.row == nextCoord.row + 1 && coord.col == nextCoord.col) {
			// System.out.println("n");
			return Heading.N;
		} else if (coord.row == nextCoord.row - 1 && coord.col == nextCoord.col) {
			// System.out.println("s");
			return Heading.S;
		} else if (coord.row == nextCoord.row && coord.col == nextCoord.col - 1) {
			// System.out.println("e");
			return Heading.E;
		} else {
			// System.out.println("w");
			return Heading.W;
		}
	}

	/*
	 * Returns the amount of money this ship is willing to give based on the
	 * amount of time it will stay
	 */
	public abstract int getMoney(int time);

	/*
	 * Sends request to the harbour tuple space for permission to enter
	 */
	public void makeRequest() {

		if (queryp(Templates.getReqSentTemp()) == null) {
			try {
				put(new Tuple("req", id, shipType, time, getMoney(time)), harbourConnection);
				put(new Tuple("reqSent"), Self.SELF); // Sends this tuple so it
														// knows that the
														// request was sent
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void checkDockPermission() {
		Tuple t = getp(Templates.getDockAssignTemp());
		if (t != null) {

			try {

				Tuple pos1 = get(Templates.getCoordTemp(id), mapConnection);
				put(new Tuple("free", pos1.getElementAt(Integer.class, 2), pos1.getElementAt(Integer.class, 3)),
						mapConnection);
				if (inTransition) {
					Tuple pos2 = get(Templates.getCoordTemp(id), mapConnection);
					put(new Tuple("free", pos2.getElementAt(Integer.class, 2), pos2.getElementAt(Integer.class, 3)),
							mapConnection);

				}
				coord = new Coordinate(t.getElementAt(Integer.class, 2), t.getElementAt(Integer.class, 3));
				put(new Tuple(id, shipType, coord.row, coord.col, Heading.E), mapConnection);
				put(new Tuple(id, shipType, coord.row, coord.col + 1, Heading.E), mapConnection);
				get(Templates.getFreeCoordTemp(coord.row, coord.col), mapConnection);
				get(Templates.getFreeCoordTemp(coord.row, coord.col + 1), mapConnection);
				// put(new Tuple(id, shipType, coord.row, coord.col, heading),
				// mapConnection);
				// emptyPath();
				setDocked(true);
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected void turnRight() {
		switch (heading) {
		case N:
			heading = Heading.NE;
			break;
		case NE:
			heading = Heading.E;
			break;
		case E:
			heading = Heading.SE;
			break;
		case SE:
			heading = Heading.S;
			break;
		case S:
			heading = Heading.SW;
			break;
		case SW:
			heading = Heading.W;
			break;
		case W:
			heading = Heading.NW;
			break;
		case NW:
			heading = Heading.N;
			break;
		default:
			break;
		}
	}

	protected void turnLeft() {
		switch (heading) {
		case N:
			heading = Heading.NW;
			break;
		case NW:
			heading = Heading.W;
			break;
		case W:
			heading = Heading.SW;
			break;
		case SW:
			heading = Heading.S;
			break;
		case S:
			heading = Heading.SE;
			break;
		case SE:
			heading = Heading.E;
			break;
		case E:
			heading = Heading.NE;
			break;
		case NE:
			heading = Heading.N;
			break;
		default:
			break;
		}
	}

	protected boolean moveForward(Coordinate nextCoord) {

		boolean hasMoved = false;
		try {
			if (inTransition) {
				inTransition = !inTransition;
				get(Templates.getCoordTemp(id), mapConnection);
				get(Templates.getCoordTemp(id), mapConnection);
				put(new Tuple("free", coord.row, coord.col), mapConnection);
				put(new Tuple(id, shipType, nextCoord.row, nextCoord.col, heading), mapConnection);
				coord = nextCoord;
				hasMoved = true;
			} else {
				Tuple free = coordinates.getp(Templates.getFreeCoordTemp(nextCoord.row, nextCoord.col));
				if (free == null) {
					return false;
				}
				put(new Tuple(id, shipType, nextCoord.row, nextCoord.col, heading), mapConnection);
				inTransition = !inTransition;
				hasMoved = false;
			}
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hasMoved;
	}

	@Override
	protected void doRun() throws Exception {

		makeRequest();
		Coordinate nextCoord = null;
		if (startPath.size() > 0) {
			nextCoord = startPath.get(0).clone();
		}
		while (true) {
			checkRemove();
			
			if (!isDocked() && !waitingForDeath) {
				checkDockPermission();
			}
			if (!isDocked() && !waitingForDeath) {
				if (nextCoord != null) {
					if (move(nextCoord)) {
						if (startPathIndex < 1) {
							nextCoord = path.get(startPathIndex);
							startPathIndex++;
						} else {
							pathIndex = (pathIndex + 1) % path.size();
							nextCoord = path.get(pathIndex);
						}
					}
					// }

				}
			}
			barrier.moved();
		}
	}
	
	

	private void checkRemove() {
		if (!waitingForDeath) {
		Tuple t = getp(Templates.getDeclineTemp());
		if (t != null) {
			waitingForDeath  = true;
			try {
			Tuple pos1 = get(Templates.getCoordTemp(id), mapConnection);
			put(new Tuple("free", pos1.getElementAt(Integer.class, 2), pos1.getElementAt(Integer.class, 3)),
					mapConnection);
			if (inTransition) {
				Tuple pos2 = get(Templates.getCoordTemp(id), mapConnection);
				put(new Tuple("free", pos2.getElementAt(Integer.class, 2), pos2.getElementAt(Integer.class, 3)),
						mapConnection);
				}
			
			put(new Tuple("removed"), Self.SELF);
			
			}
				
			 catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			}
		}

	}

	public BarrierMonitor getMonitor() {
		return barrier;
	}

	public void setMonitor(BarrierMonitor monitor) {
		this.barrier = monitor;
	}

	protected int getRow() {
		return coord.row;
	}

	protected int getCol() {
		return coord.col;
	}

	protected boolean isDocked() {
		return docked;
	}

	protected void setDocked(boolean idle) {
		this.docked = idle;
	}

	protected String getId() {
		return id;
	}

	public Heading getHeading() {
		return heading;
	}

	public ShipType getType() {
		return shipType;
	}

	public List<Coordinate> getPath() {
		return path;
	}

	public void setPath(List<Coordinate> path) {
		this.path = path;
	}

	public void setStartPath(List<Coordinate> path) {
		this.startPath = path;
	}

	public void setCoordinates(TupleSpace coordinates) {
		this.coordinates = coordinates;
	}
	public int getTime() {
		return time;
	}
}
