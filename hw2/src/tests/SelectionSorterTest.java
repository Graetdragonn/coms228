package tests;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw2.AbstractSorter;
import edu.iastate.cs228.hw2.Point;
import edu.iastate.cs228.hw2.PolarAngleComparator;
import edu.iastate.cs228.hw2.SelectionSorter;

class SelectionSorterTest {

	@Test
	void manualPolarAngleTest() {
		Point p1 = new Point(8, 4);
		Point p2 = new Point(7, 3);
		Point p3 = new Point(5, -2);
		Point p4 = new Point(10, 5);
		Point p5 = new Point(0, 0);
		Point p6 = new Point(0, -10);
		
		Point[] points = {p1, p2, p3, p4, p5, p6};
		
		AbstractSorter aSorter = new SelectionSorter(points);
		aSorter.setReferencePoint(new Point(0,1));
		PolarAngleComparator origin = new PolarAngleComparator(new Point(0, 1));
		
		aSorter.setComparator(2);
		
		aSorter.sort();
		
		Point[] test = new Point[6];
		aSorter.getPoints(test);
		System.out.println(Arrays.toString(test));
	}

}
