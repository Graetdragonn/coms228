package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.Point;
import edu.iastate.cs228.hw2.PolarAngleComparator;

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

	
	@Test
	void distanceCheckOne() {
		Point p1 = new Point(1, 0);
		Point p2 = new Point(0, 5);
		
		PolarAngleComparator origin = new PolarAngleComparator(new Point(0,0));
		
		assertEquals(origin.compareDistance(p1, p2), -1);
	}
}
