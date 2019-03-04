package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *  
 * @author Brian Bates
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		// Invoke the superclass constructor
		super(pts);
		
		// Set the instance variable algorithm of the superclass
		super.algorithm = "mergesort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(this.points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		// The length of the array in the argument
		int n = pts.length;
		int m = n / 2;
		
		// If n <= 1, there is nothing to sort
		if (n <= 1) {
			return;
		}
		
		// Create the new arrays for the left and right side, adjusting for unequal sized arrays
		Point[] left = new Point[m];
		Point[] right = new Point[n-m];
		
		// Populate the left array
		for (int i = 0; i < m; i++) {
			left[i] = pts[i];
		}
		
		// Populate the right array
		int c = 0;
		for (int i = m; i < n; i++) {
			right[c] = pts[i];
			c++;
		}
		
		// Recursively call mergeSortRec() for each new array
		mergeSortRec(left);
		mergeSortRec(right);
		
		// At this point, left and right should be split up, so call merge
		Point[] temp = new Point[pts.length];
		temp = merge(left, right);
		
		for (int i = 0; i < temp.length; i++) {
			pts[i] = temp[i];
		}
		
	}

	/**
	 * Merge two Point arrays
	 * @param B the first Point array
	 * @param C the second Point array
	 */
	private Point[] merge(Point[] B, Point[] C) {
		int p = B.length;
		int q = C.length;
		
		// Create a new Point array
		Point[] D = new Point[p+q];
		
		int i = 0; int j = 0; int iter = 0;
		while ((i < p) && (j < q)) {
			if (B[i].compareTo(C[j]) <= 0) {
				D[iter++] = B[i];
				i++;
			} else {
				D[iter++] = C[j];
				j++;
			}
		}
		
		if (i >= p) {
			for (int z = j; z < q; z++) {
				D[iter++] = C[z];
			}
		} else {
			for (int z = i; z < p; z++) {
				D[iter++] = B[z];
			}
		}
		
		return(D);
	}
	
	// Other private methods in case you need ...

}
