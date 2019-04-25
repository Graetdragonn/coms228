 package edu.iastate.cs228.hw5;


import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Brian Bates
 *
 */

/**
 * 
 * The Transactions class simulates video transactions at a video store. 
 *
 */
public class Transactions 
{
	
	/**
	 * The main method generates a simulation of rental and return activities.  
	 *  
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		// TODO 
		// 
		// 1. Construct a VideoStore object.
		// 2. Simulate transactions as in the example given in Section 4 of the 
		//    the project description. 
		
		// Read from the console
		Scanner in = new Scanner(System.in);
		String choice;
		
		// Initial output to screen
		System.out.println("Transactions at a Video Store");
		System.out.println("keys: 1 (rent)      2 (bulk rent)");
		System.out.println("      3 (return)    4 (bulk return)");
		System.out.println("      5 (summary)   6 (exit)");
		System.out.println();
		System.out.print("Transaction: ");
			
		
		while(true) {
			// Get the next choice from the user
			choice = in.next();
			
			if (choice.equals("6")) {
				// User chose to exit
				System.exit(0);
			} else if (choice.equals("1")) {
				// Rent
			} else if (choice.equals("2")) {
				// Bulk rent
			} else if (choice.equals("3")) {
				// Return 
			} else if (choice.equals("4")) {
				// Bulk Return
			} else if (choice.equals("5")) {
				// Summary
			} else {
				// Unknown choice
				
			}
		}
	}
}
