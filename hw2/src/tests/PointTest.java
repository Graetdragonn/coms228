package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.Point;

class PointTest {

	/**
	 * Test to see if you can create a Point object without any coordinates
	 */
	@Test
	void basicConstructorTest() {
		try {
			Point p = new Point();
		} catch (Exception e) {
			fail("Failed to generate a Point object without coordinates: " + e);
		}
	}
	
	/**
	 * Test the Point object constructor that requires a set of coordinates
	 */
	@Test
	void secondConstructorTest() {
		// Generate a new Point object with a given set of coordinates
		try {
			Point p = new Point(5, 5);
		} catch (Exception e) {
			fail("Failed to create a Point object with given coordinates: " + e);
		}
	}

	/**
	 * Test to retrieve the x-coordinate of a Point object
	 */
	@Test
	void secondConstructorXCoordRetrieval() {
		// Generate a new Point object with a given set of coordinates
		Point p = new Point(5, 5);
		
		try {
			int x = p.getX();
		} catch (Exception e) {
			fail("Failed to retrieve the x-value from a Point object.");
		}
	}
	
	/**
	 * Test to retrieve the y-coordinate of a Point object
	 */
	@Test
	void secondConstructorYCoordRetrieval() {
		// Generate a new Point object with a given set of coordinates
		Point p = new Point(5, 5);
		
		try {
			int y = p.getY();
		} catch (Exception e) {
			fail("Failed to retrieve the y-value from a Point object.");
		}
	}
	
	/**
	 * Test to ensure two Point objects with the same coordinates are in fact equal
	 */
	@Test
	void equalsTrueTest() {
		Point a = new Point(5, 5);
		Point b = new Point(5, 5);
		
		if (!a.equals(b)) {
			fail("Two separate Point objects with the same coordinates were not considered equal");
		}
	}
	
	/**
	 * Test to ensure two Point objects with the same coordinates are in fact equal
	 */
	@Test
	void equalsFalseTest() {
		Point a = new Point(5, 5);
		Point b = new Point(1, 1);
		
		if (a.equals(b)) {
			fail("Two separate Point objects with differing coordinates were considered equal");
		}
	}
	
	/**
	 * Test that the return string is properly constructed for the toString override method
	 */
	@Test
	void stringOverrideTest() {
		Point a = new Point(1, 1);
		String s = a.toString();
		
		assertTrue(s.equals("(1, 1)"), s);
	}
}
