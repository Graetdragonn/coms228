package edu.iastate.cs228.hw2;

/**
 *  
 * @author Brian Bates
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		// TODO 
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       RotationalPointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		//
		
		System.out.println("Performance of Four Sorting Algorithms in Point Scanning");
		System.out.println("keys  1 (random integers)  2 (file input)  3 (exit)");
		
		Scanner in = new Scanner(System.in);
		int choice = 1;
		int trials = 1;
		
		RotationalPointScanner[] scanners = new RotationalPointScanner[4];
		
		// You can seed this for testing
		Random rand = new Random();
		
		// Create the main loop
		while(true) {
			
			System.out.print("Trial " + trials + ": ");
			choice = in.nextInt();
			
			if (choice != 1 && choice != 2) {
				System.exit(0);
			} else {
				
				if (choice == 1) {
					// Option 1:  Random point selection					
					System.out.print("Enter number of random points: ");
					int numRandPoints = in.nextInt();
					System.out.println();
					
					// Generate the random points
					Point[] points = generateRandomPoints(numRandPoints, rand);
					
					// Initialize the array using randomly generated points
					scanners[0] = new RotationalPointScanner(points, Algorithm.SelectionSort);
					scanners[1] = new RotationalPointScanner(points, Algorithm.InsertionSort);
					scanners[2] = new RotationalPointScanner(points, Algorithm.MergeSort);
					scanners[3] = new RotationalPointScanner(points, Algorithm.QuickSort);
					
				} else {
					// Option 2:  Read points from file
					System.out.println("Points from a file");
					System.out.print("File name: ");
					String file = in.next();
					
					// Initialize the array using the file of points
					scanners[0] = new RotationalPointScanner(file, Algorithm.SelectionSort);
					scanners[1] = new RotationalPointScanner(file, Algorithm.InsertionSort);
					scanners[2] = new RotationalPointScanner(file, Algorithm.MergeSort);
					scanners[3] = new RotationalPointScanner(file, Algorithm.QuickSort);
				}
			}
			
			
			// Iterate through the array scanners[]
			for (int i = 0; i < scanners.length; i++) {
				// Have every scanner call the scan() method
				scanners[i].scan();
				
				// Have every scanner call the draw() method //////////////////**********************///////////////(((********
				//scanners[i].draw();
			}
			
			
			// After all four scans are done for the input, print out the statistics table (cf. Section 2). 
			
			
			///////////////////////// THIS IS NOT RIGHT ANYMORE //////////////////////////////////////////
			// Print the summary
			System.out.printf("%-17s %-10s %-10s \n", "algorithm", "size", "time (ns)");
			System.out.println("--------------------------------------");
			
			// Loop through each scanner to display stats
			for (int i = 0; i < scanners.length; i++) {
				System.out.println(scanners[i].stats());
			}
			
			System.out.println("--------------------------------------");
			System.out.println();
			
			trials += 1;
		}
		
		
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		if (numPts < 1) {
			throw new IllegalArgumentException("Number of points is less than 1.");
		} else {
			Point[] points = new Point[numPts];
			int x;
			int y;
			
			for(int i = 0; i < numPts; i++) {
				x = rand.nextInt(101) - 50;
				y = rand.nextInt(101) - 50;
				
				Point p = new Point(x, y);
				points[i] = p;
			}
			
			return(points);
		}
	}
	
}
