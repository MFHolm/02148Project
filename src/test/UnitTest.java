package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.cmg.jresp.exceptions.IllegalActionException;
import org.cmg.jresp.knowledge.Tuple;
import org.junit.Before;
import org.junit.Test;

import Controller.MainController;
import model.Heading;
import model.Model;
import model.RedShip;
import model.ShipType;

public class UnitTest {

	private MainController controller;
	private RedShip redShip;
	private Model model;
	
	@Before
	public void settup() {
		try {
			controller = new MainController("test");
		} catch (IllegalActionException e) {
			e.printStackTrace();
		}
		controller.createModel();
		model = controller.getModel();
		
	}
	
	//Test if a request is added to the request tuple space and the position is 
	//added to the sea tuple space when creating a ship
	@Test
	public void testAddShip() {
		LinkedList<Tuple> requests = model.getRequests();
		ArrayList<Tuple> positions = model.getShipPositions();
		//Requests and positions should be empty
		assertEquals(0, requests.size());
		assertEquals(0, positions.size());
		
		redShip = new RedShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 0, 17, Heading.S);
		model.addShip(redShip);
		requests = model.getRequests();
		positions = model.getShipPositions();
		//There should be one request
		assertEquals(1, positions.size());	
		assertEquals("id1", positions.get(0).getElementAt(String.class, 0));
		assertEquals(ShipType.RED, positions.get(0).getElementAt(ShipType.class, 1));
		assertTrue((double) positions.get(0).getElementAt(Double.class, 2) > -0.1 
				&& (double) positions.get(0).getElementAt(Double.class, 2) < 0.1);
		assertTrue( (double) positions.get(0).getElementAt(Double.class, 3) > 16.9 
				&& (double) positions.get(0).getElementAt(Double.class, 2) < 17.1);
		assertEquals(Heading.S, positions.get(0).getElementAt(Heading.class, 4));
		
		//There should be one position
		assertEquals(1, requests.size());	
		assertEquals("req", requests.get(0).getElementAt(String.class, 0));
		assertEquals("id1", requests.get(0).getElementAt(String.class, 1));
		assertEquals(ShipType.RED, requests.get(0).getElementAt(ShipType.class, 2));
		assertEquals(redShip.getTime(),(int) requests.get(0).getElementAt(Integer.class, 3));
		assertEquals(redShip.getMoney(redShip.getTime()),(int) requests.get(0).getElementAt(Integer.class, 4));
	}
		
	
	//Test that the ship cannot make multiple requests
	@Test
	public void testMakeRequest() {
		LinkedList<Tuple> requests = model.getRequests();
		//There should be one request
		assertEquals(1, requests.size());
		
		redShip.makeRequest();
		requests = model.getRequests();
		//There should still be one request
		assertEquals(1, requests.size());		
	}
	
	//Test accept request
	@Test
	public void testAcceptRequest() {
		redShip = new RedShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 0, 17, Heading.S);
		model.addShip(redShip);
		LinkedList<Tuple> requests = model.getRequests();
		
		//There should  be one request
		assertEquals(1, requests.size());	
		
		model.acceptRequest("id1", "dock1");
		requests = model.getRequests();
		//There should be no requests
		assertEquals(0, requests.size());
		
	}
	
	//Test accept request
	@Test
	public void testAcceptRequestDockOccupied() {
		redShip = new RedShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 0, 17, Heading.S);
		model.addShip(redShip);
	
		model.acceptRequest("id1", "dock1");

		
		redShip = new RedShip("id2", model.getSeaName(), model.getHarbourName(), Model.getVp(), 1, 17, Heading.S);
		model.addShip(redShip);
			
		try {
			model.acceptRequest("id2", "dock1");
		    fail( "Exception should have been thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("dock1 occupied.", e.getMessage());
		}	
	}
	
	//Test decline request
	@Test
	public void testRemoveShipFromDock() {
		redShip = new RedShip("id1", model.getSeaName(), model.getHarbourName(), Model.getVp(), 0, 17, Heading.S);
		model.addShip(redShip);
	
		
	}

}
