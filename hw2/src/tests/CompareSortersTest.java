package tests;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.CompareSorters;
import edu.iastate.cs228.hw2.Point;

class CompareSortersTest {

	/*
	 * Ensure that if the number of random points requested is less than one
	 * that an exception is thrown
	 */
	@Test
	void randomNumberGeneratorExceptionTest() {
		Random rand = new Random();
		
		int numPts = -1;
		
		// This should throw an IllegalArgumentException
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
				CompareSorters.generateRandomPoints(numPts, rand);
		});
	}
	
	/*
	 * This is a test to ensure the expected array of points generated has
	 * the same length as the number of random points requested.
	 */
	@Test
	void randomNumberGeneratorLengthTest() {
		Random rand = new Random();
		int numPts = 1000;
		
		Point[] points = CompareSorters.generateRandomPoints(numPts, rand);
		
		
		Assertions.assertEquals(points.length, numPts, "Random generator did not produce the correct number of points.");
	}
	
	/*
	 * Evaluate all the points randomly generated to ensure their x- and y-values are within [50, 50]
	 */
	@Test
	void randomNumberGeneratorIntervalTest() {
		Random rand = new Random();
		int numPts = 1000;
		
		Point[] points = CompareSorters.generateRandomPoints(numPts, rand);
		
		int x;
		int y;
		
		for (int i = 0; i < numPts; i++) {
			x = points[i].getX();
			
			if (!(x <= 50)) {
				fail("x-coord of a point is not within the interval [50, 50]");
			}
			
			y = points[i].getY();
			
			if (!(y <= 50)) {
				fail("y-coord of a point is not within the interval [50, 50]");
			}
		}
	}
	
	
		
		
	
}
