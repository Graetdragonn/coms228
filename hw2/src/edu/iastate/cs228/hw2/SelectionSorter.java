package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Brian Bates
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		// Invoke the superclass constructor
		super(pts);
		
		// Set the instance variable algorithm of the superclass
		super.algorithm = "selection sort";
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		for (int i = 0; i < this.points.length - 1; i++) {
			
			int minIndex = i;

			for (int j = i + 1; j < this.points.length; j++) {
				if (this.points[j].compareTo(this.points[minIndex]) < 0) {
					minIndex = j;
				}
			}
			
			// Swap them
			super.swap(i, minIndex);
		}
	}	
}
