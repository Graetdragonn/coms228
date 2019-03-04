package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.Point;

class PolarAngleComparatorTest {
	
	/**
	 * Manual test of equation for cross product.  Compared to calculator output.
	 */
	@Test
	void crossProductTest() {
		Point p1 = new Point(1, 0);
		Point p2 = new Point(0, 1);
		
		assertEquals((p1.getX() * p2.getY()) - (p2.getX() * p1.getY()), 1);
	}
	
	/**
	 * Manual test of equation for dot product.  Compared to calculator output.
	 */
	@Test
	void dotProductTest() {
		Point p1 = new Point(1, 0);
		Point p2 = new Point(0, 1);
		
		assertEquals((p1.getX() * p2.getX()) + (p1.getY() * p2.getY()), 0);
	}

}
