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
		VideoStore vs = new VideoStore("small.txt");
		
		// 2. Simulate transactions as in the example given in Section 4 of the 
		//    the project description. 
		
		// Read from the console
		Scanner in = new Scanner(System.in);
		int choice;
		
		// Initial output to screen
		System.out.println("Transactions at a Video Store");
		System.out.println("keys: 1 (rent)      2 (bulk rent)");
		System.out.println("      3 (return)    4 (bulk return)");
		System.out.println("      5 (summary)   6 (exit)");
		System.out.println();
		System.out.print("Transaction: ");
			
		
		while(true) {
			// Get the next choice from the user
			choice = in.nextInt();
			
			String misc = in.nextLine();
			
			if (choice == 6) {
				// User chose to exit
				System.exit(0);
			} else if (choice == 1) {
				// Rent
				System.out.print("Film to rent: ");
				String line = in.nextLine();
				
				String title = vs.parseFilmName(line);
				int copies = vs.parseNumCopies(line);
				
				try {
					vs.videoRent(title, copies);
				} catch (IllegalArgumentException e) {
					System.out.println("Film " + title + " has an invalid request");
				} catch (FilmNotInInventoryException e) {
					System.out.println("Film " + title + " is not in inventory");
				} catch (AllCopiesRentedOutException e) {
					System.out.println("Film " + title + " has been rented out");
				}
			} else if (choice == 2) {
				// Bulk rent
				System.out.print("Video file (rent): ");
				String line = in.nextLine();
				
				try {
					vs.bulkRent(line);
				} catch (Exception e) {
					System.out.println(e);
				}
			} else if (choice == 3) {
				// Return 
				System.out.print("Film to return: ");
				String line = in.nextLine();
				
				String title = vs.parseFilmName(line);
				int copies = vs.parseNumCopies(line);
				
				try {
					vs.videoReturn(title, copies);
				} catch (IllegalArgumentException e) {
					System.out.println("Film " + title + " has an invalid request");
				} catch (FilmNotInInventoryException e) {
					System.out.println("Film " + title + " is not in inventory");
				} 				
			} else if (choice == 4) {
				// Bulk Return
				System.out.print("Video file (return): ");
				String line = in.nextLine();
				
				try {
					vs.bulkReturn(line);
				} catch (Exception e) {
					System.out.println(e);
				}
			} else if (choice == 5) {
				// Summary
				System.out.println(vs.transactionsSummary());
			} else {
				// Unknown choice
				
			}
			
			System.out.println();
			System.out.print("Transaction: ");
		}
	}
}
