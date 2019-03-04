package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.Point;
import edu.iastate.cs228.hw2.PolarAngleComparator;

class PolarAngleComparatorTest {
	
	/**
	 * Manual test of the crossproduct
	 */
	@Test
	void crossProductTest() {
		Point p1 = new Point(0, 1);
		Point p2 = new Point(1, 1);
		
		Point referencePoint = new Point(0, 0);
		
		int x1 = p1.getX() - referencePoint.getX();
    	int y1 = p1.getY() - referencePoint.getY();
    	
    	int x2 = p2.getX() - referencePoint.getX();
    	int y2 = p2.getY() - referencePoint.getY();
    	
    	assertEquals(((x1 * y2) - (x2 * y1)), -1);
	}

	/**
	 * Distance check test shows that p1 is closer to the referencePoint that p2
	 */
	@Test
	void distanceCheckOne() {
		Point p1 = new Point(1, 0);
		Point p2 = new Point(0, 5);
		
		PolarAngleComparator origin = new PolarAngleComparator(new Point(0,0));
		
		assertEquals(origin.compareDistance(p1, p2), -1);
	}
	
	/**
	 * Distance check test shows that p1 and p2 are equidistant from reference point
	 */
	@Test
	void distanceCheckTwo() {
		Point p1 = new Point(5, 0);
		Point p2 = new Point(0, 5);
		
		PolarAngleComparator origin = new PolarAngleComparator(new Point(0,0));
		
		assertEquals(origin.compareDistance(p1, p2), 0);
	}
	
	/**
	 * Test confirms two points that are the same, have the same polar angle
	 */
	@Test
	void polarAngleSamePointTest() {
		Point p1 = new Point(5, -2);
		Point p2 = new Point(5, -2);
		
		PolarAngleComparator origin = new PolarAngleComparator(new Point(0,0));
		
		assertEquals(origin.comparePolarAngle(p1, p2), 0);
	}
	
	/**
	 * Test shows that two points with the same angle, but different distances are ordered correctly
	 */
	@Test
	void polarAngleDistanceOrderTest() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, -10);
		
		PolarAngleComparator origin = new PolarAngleComparator(new Point(0,1));
		
		assertEquals(origin.compare(p1, p2), -1);
	}
	
	/**
	 * Testing 
	 */
	@Test
	void polarAngleTest() {
		Point p1 = new Point(0, 10);
		Point p2 = new Point(0, 5);
		
		Point[] points = {p1, p2};
		
		PolarAngleComparator origin = new PolarAngleComparator(new Point(0, 0));
		
		assertEquals(origin.comparePolarAngle(p1, p2), 0);
	}
	
	/**
	 * This test required a public dot- and cross-product so is commented out
	 */
	@Test
	void polarAngleTestTwo() {
//		Point p1 = new Point(0, 1);
//		Point p2 = new Point(1, 1);
//		
//		Point referencePoint = new Point(0,0);
//		PolarAngleComparator origin = new PolarAngleComparator(new Point(0, 0));
//		
//		if ((p1.equals(p2)) || ((p1.compareTo(referencePoint) != 0) && (p2.compareTo(referencePoint) != 0) && (origin.crossProduct(p1, p2) == 0) && (origin.dotProduct(p1, p2) > 0)))  {
//    		System.out.println(0);
//    	} else if (p1.equals(referencePoint) ||
//    		(p2.compareTo(referencePoint) != 0 && 
//    			// 1
//    			(p1.getY() < referencePoint.getY() && p2.getY() < referencePoint.getY() && origin.crossProduct(p1, p2) > 0)  ||
//    			// 2
//    			(p1.getY() == referencePoint.getY() &&
//    					((p2.getY() < referencePoint.getY()) ||
//    					(p2.getY() == referencePoint.getY() && p1.getX() > referencePoint.getX() && p2.getX() < referencePoint.getX()) ||
//    					(p2.getY() > referencePoint.getY() && p2.getX() > referencePoint.getX()))) ||
//    			// 3
//    			(p1.getY() > referencePoint.getY() &&
//    					((p2.getY() > referencePoint.getY() && origin.crossProduct(p1, p2) > 0) ||
//    					(p2.getY() == referencePoint.getY() && p2.getX() < referencePoint.getX()) ||
//    					(p2.getY() < referencePoint.getY()))))) {
//
//    		System.out.println(-1);
//    	} else {
//    		System.out.println(1);
//    	}
    	
    	
	}
	
	
	@Test
	void polarAngleTestThree() {
		Point p1 = new Point(0, 1);
		Point p2 = new Point(1, 1);
		
		Point referencePoint = new Point(0,0);
		PolarAngleComparator origin = new PolarAngleComparator(new Point(0, 0));
		
		assertEquals(origin.comparePolarAngle(p1, p2), 1);
	}
	
	
	
}
