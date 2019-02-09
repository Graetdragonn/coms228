package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 

/**
 *  
 * @author Brian Bates
 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class Wildlife 
{
	
	/*
	 * Make sure you actually need to save these.  Maybe they can just be done in a loop.
	 */
	private static int trialNumber = 1;
	private static int gridWidth = 1;
	private static int cycles = 1;
	
	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		// TODO 
		// 
		// Generate wildlife simulations repeatedly like shown in the 
		// sample run in the project description. 
		//
		// 2. Print out standard messages as given in the project description. 
		// 
		// 3. For convenience, you may define two plains even and odd as below. 
		//    In an even numbered cycle (starting at zero), generate the plain 
		//    odd from the plain even; in an odd numbered cycle, generate even 
		//    from odd. 
		
		Plain even;   				 // the plain after an even number of cycles 
		Plain odd;                   // the plain after an odd number of cycles
		
		// 4. Print out initial and final plains only.  No intermediate plains should
		//    appear in the standard output.  (When debugging your program, you can 
		//    print intermediate plains.)
		// 
		// 5. You may save some randomly generated plains as your own test cases. 
		// 
		// 6. It is not necessary to handle file input & output exceptions for this 
		//    project. Assume data in an input file to be correctly formated.
		
		// 1. Getting user input to 1) generate a random plain, 2 to read a plain from an input
		//    file, and 3 to end the simulation. (An input file always ends with 
		//    the suffix .txt.)
		
		
		
		System.out.println("Simulation of Wildlife of the Plain");
		System.out.println("keys: 1 (random plain), 2 (file input), 3 (exit)");
		System.out.println();
		
		while (true) {
			// Begin a new trial by getting the user's choice
			int choice = getTrialChoice();			
			
			if ((choice != 1) && (choice != 2)) {
				System.exit(0);
			} else {
				// Random Plain Code
				if (choice == 1) {
					System.out.println("Random plain");
					
					// Get the grid size
					gridWidth = getGridWidth();
					
					// Create the Plains (not initialized)
					even = new Plain(gridWidth);
					odd = new Plain(gridWidth);
					
					// Initialize plain with life
					even.randomInit();
				} else {
					System.out.println("Plain input from file");
					
					// Get the file name from the console
					Scanner console = new Scanner(System.in);
					System.out.print("File name: ");
					
					even = new Plain(console.next());
					odd = new Plain(even.getWidth());
				}
				
				// Get the number of cycles to run
				cycles = getTrialCycles();
			
				// Print out the initial plain
				System.out.println();
				System.out.println("Initial Plain:");
				System.out.println();
				System.out.println(even.toString());
				
				// Go through the cycles
				for (int i = 0; i < cycles; i++) {
					if (i % 2 == 0) {
						// Currently Even
						updatePlain(even, odd);
					} else {
						// Currently Odd
						updatePlain(odd, even);
					}		
				}
				
				// Print out the final plain
				System.out.println("Final Plain:");
				System.out.println();
				
				if (cycles % 2 == 0) {
					System.out.println(even.toString());
					
					// Optionally, write final plain to file
					even.write("output.txt");
				} else {
					System.out.println(odd.toString());
					
					// Optionally, write final plain to file
					odd.write("output.txt");
				}
				
			}
		}
	}
	
	/**
	 * Get the user input from the console.  A check for the validity of the user input.
	 * @return the user's choice (1, 2, 3)
	 */
	public static int getTrialChoice() {
		Scanner in = new Scanner(System.in);
				
		// Wait for user input and check for validity
		System.out.print("Trial " + trialNumber + ": ");
		return(in.nextInt());
	}
	
	/**
	 * Get the user input for grid size from the console.  Grid width must be greater than 0
	 * @return the user's choice of grid width
	 */
	public static int getGridWidth() {
		Scanner in = new Scanner(System.in);
		
		// Grid size from user
		System.out.print("Enter grid width: ");
		int gridWidth = in.nextInt();
		
		// Check validity of grid width
		if (!(gridWidth > 0)) {
			System.out.println("Invalid grid size.  Grid must be greater than zero.");
			gridWidth = getGridWidth();
		}
		
		return(gridWidth);
	}
	
	
	public static int getTrialCycles() {
		Scanner in = new Scanner(System.in);
		
		// Cycles from user
		System.out.print("Enter the number of cycles: ");
		int cycles = in.nextInt();
		
		// Check validity of cycles
		while(cycles <= 0) {
			cycles = in.nextInt();
		}
		
		return cycles;
	}
	
	
	/**
	 * Update the new plain from the old plain in one cycle. 
	 * @param pOld  old plain
	 * @param pNew  new plain 
	 */
	public static void updatePlain(Plain pOld, Plain pNew)
	{
		// Iterate through every life form in the plain
		for (int i = 0; i < pOld.getWidth(); i++) {
			for (int j = 0; j < pOld.getWidth(); j++) {
				// Call next() on each cell assigning the response to the corresponding cell in the new plain
				pNew.grid[i][j] = pOld.grid[i][j].next(pNew);
			}
		}
	}
}
