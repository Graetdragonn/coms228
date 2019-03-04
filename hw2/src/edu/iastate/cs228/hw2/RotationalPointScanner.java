package edu.iastate.cs228.hw2;

import java.io.File;

/**
 * 
 * @author Brian Bates
 *
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array by polar angle with respect to a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class RotationalPointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
	protected String outputFileName;   // "select.txt", "insert.txt", "merge.txt", or "quick.txt"
	
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[]. Set outputFileName. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public RotationalPointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		// First argument check.
		if (pts == null) {
			throw new IllegalArgumentException("RotationalPointScanner argument pts was null.");
		}
		
		// Second argument check
		if (pts.length == 0) {
			throw new IllegalArgumentException("RotationalPointScanner argument pts had length zero (no points passed)");
		}
		
		// Do you need to COPY the pts array?  Probably better (can't go wrong I guess)
		points = new Point[pts.length];
		
		for (int i = 0; i < pts.length; i++) {
			points[i] = pts[i];
		}
		
		// Assign the correct algorithm
		this.sortingAlgorithm = algo;
	}

	
	/**
	 * This constructor reads points from a file. Set outputFileName. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected RotationalPointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		// Load the file
		File inputFile = new File(inputFileName);
		Scanner in = new Scanner(inputFile);
		
		// Create a temporary array to house the integers from the file
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		// Load the integers from the file.  Will not load a file with improper values (float, character, etc.)
		while (in.hasNextInt()) {
			temp.add(in.nextInt());
		}
		
		// Check for an even number of integers in the file
		if ((temp.size() % 2) != 0) {
			throw new InputMismatchException("Input file does not contain even number of values.  Length was: " + temp.size());
		}
		
		// Create the Points[] array
		this.points = new Point[temp.size() / 2];
		
		// Create a new Point object for every two values
		int j = 0;
		for (int i = 0; i < temp.size(); i+=2) {
			// Create the new Point object with sequential values in temp
			Point p = new Point(temp.get(i), temp.get(i+1));
			
			// Assign the Point object to the instance variable
			this.points[j] = p;
			
			// Iterate the counter
			j++;
		}
		
		// Assign the correct algorithm
		this.sortingAlgorithm = algo;
	}

	
	/**
	 * Carry out three rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates. 
	 *     d) Sort points[] again by the polar angle with respect to medianCoordinatePoint.
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting. Copy the sorting result back onto the array points[] by calling 
	 * the method getPoints() in AbstractSorter. 
	 *      
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		AbstractSorter aSorter; 
		
		// Create an object for the supertype AbstractSorter to reference
		if (sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(this.points);
		} else if (sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(this.points);
		} else if (sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(this.points);
		} else {
			aSorter = new QuickSorter(this.points);
		}
		
		
		
		// for each of the three rounds of sorting, have aSorter do the following: 
		// Call setComparator() with an argument of 0, 1, or 2.  In case it is 2, must have made
			// the call to setReferencePoint(medianCoordinatePoint) already.
		
		int x = 0;
		int y = 0;
		
		
		// Get the start time
		long startTime = System.nanoTime();
					
		for (int i = 0; i < 3; i++) {
			// First, set the comparator
			aSorter.setComparator(i);
			
			// Start the sort
			// 0. sort x-coord
			// 1. sort y-coord
			// 2. sort for PolarAngle
			if (i == 0 || i == 1) {
				aSorter.sort();
			}
			
			// Get the median values to create the medianCoordinatePoint
			if (i == 0) {
				x = aSorter.getMedian().getX();
			}
			
			if (i == 1) {
				y = aSorter.getMedian().getY();
				
				medianCoordinatePoint = new Point(x, y);
				aSorter.setReferencePoint(medianCoordinatePoint);
				System.out.println(aSorter.algorithm + " MCP: " + medianCoordinatePoint.toString());
			}
			
			// This special case when we need to sort by polar angle
			if (i == 3) {
				aSorter.sort();
			}
		}
		
		long endTime = System.nanoTime();
			
		// Assign the total sort time
		this.scanTime = endTime - startTime;
		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String returnString = String.format("%-17s %-10d %-10d", this.sortingAlgorithm, this.points.length, this.scanTime);
		return(returnString);
	}
	
	
	/**
	 * Write points[] after a call to scan().  When printed, the points will appear 
	 * in order of polar angle with respect to medianCoordinatePoint with every point occupying a separate 
	 * line.  The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		return null; 
		// TODO
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writePointsToFile() throws FileNotFoundException
	{
		// TODO 
	}	

	
	/**
	 * This method is called after each scan for visually check whether the result is correct.  You  
	 * just need to generate a list of points and a list of segments, depending on the value of 
	 * sortByAngle, as detailed in Section 4.1. Then create a Plot object to call the method myFrame().  
	 */
	public void draw()
	{		
		int numSegs = 0;  // number of segments to draw 

		// Based on Section 4.1, generate the line segments to draw for display of the sorting result.
		// Assign their number to numSegs, and store them in segments[] in the order. 
		Segment[] segments = new Segment[numSegs]; 
		
		// TODO 
		

		String sort = null; 
		
		switch(sortingAlgorithm)
		{
		case SelectionSort: 
			sort = "Selection Sort"; 
			break; 
		case InsertionSort: 
			sort = "Insertion Sort"; 
			break; 
		case MergeSort: 
			sort = "Mergesort"; 
			break; 
		case QuickSort: 
			sort = "Quicksort"; 
			break; 
		default: 
			break; 		
		}
		
		// The following statement creates a window to display the sorting result.
		Plot.myFrame(points, segments, sort);
		
	}
		
}
