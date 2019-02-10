package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner; 

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid; 
	
	
	/**
	 *  Default constructor reads from a file 
	 */
	public Plain(String inputFileName) throws FileNotFoundException
	{		
		// Load the file
		File inputFile = new File(inputFileName);
		Scanner in = new Scanner(inputFile);
		
		ArrayList<String> str = new ArrayList<String>();
		
		// Add each string element to the ArrayList<String> str
		while (in.hasNext()) {
			str.add(in.next());
		}
		
		// The width of the plain is the square root of the total number
		// of elements in the array list.
		width = (int) Math.sqrt(str.size());
		grid = new Living[width][width];		
		
		// Set the grid
		int k = 0;
		String o;
		int a = 0;
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				// Get the string of the object
				o = str.get(k);
				
				// Badger
				if (o.charAt(0) == 'B') {
					// Returns the age of the object
					a = Character.getNumericValue(o.charAt(1));
					
					Badger b = new Badger(this, i, j, a);
					grid[i][j] = b;
				}
				
				// Empty
				if (o.charAt(0) == 'E') {
					Empty e = new Empty(this, i, j);
					grid[i][j] = e;
				}
				
				// Fox
				if (o.charAt(0) == 'F') {
					// Returns the age of the object
					a = Character.getNumericValue(o.charAt(1));
					
					Fox f = new Fox(this, i, j, a);
					grid[i][j] = f;
				}
				
				// Grass
				if (o.charAt(0) == 'G') {
					Grass g = new Grass(this, i, j);
					grid[i][j] = g;
				}
				
				// Rabbit
				if (o.charAt(0) == 'R') {
					// Returns the age of the object
					a = Character.getNumericValue(o.charAt(1));
					
					Rabbit r = new Rabbit(this, i, j, a);
					grid[i][j] = r;
				}
				
				k++;
			}
		}
		
		// Close the file
		in.close();
	}
	
	/**
	 * Constructor that builds a w x w grid without initializing it. 
	 * @param width the grid 
	 */
	public Plain(int w)
	{
		// TODO 
		width = w;
		grid = new Living[w][w];
	}
	
	
	public int getWidth()
	{
		// TODO  
		return width;   
	}
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random generator = new Random(); 
		int g;
		
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				g = generator.nextInt(5);
				
				if (g == 0) {
					// Badger
					Badger badger = new Badger(this, i, j, generator.nextInt(Living.BADGER_MAX_AGE));
					grid[i][j] = badger;
				} else if (g == 1) {
					// Empty
					Empty empty = new Empty(this, i, j);
					grid[i][j] = empty;
				} else if (g == 2) {
					// Fox
					Fox fox = new Fox(this, i, j, generator.nextInt(Living.FOX_MAX_AGE));
					grid[i][j] = fox;
				} else if (g == 3) {
					// Grass
					Grass grass = new Grass(this, i, j);
					grid[i][j] = grass;
				} else {
					// Rabbit
					Rabbit rabbit = new Rabbit(this, i, j, generator.nextInt(Living.RABBIT_MAX_AGE));
					grid[i][j] = rabbit;
				}
			}
			
		}
	}
	
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		// TODO
		String returnString = "";
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				
				Living l = grid[i][j];
				
				if (l.who() == State.BADGER) {
					Badger b = (Badger) l;
					returnString = returnString + "B" + b.myAge() + " ";
				}
				
				if (l.who() == State.EMPTY) {
					Empty e = (Empty) l;
					returnString = returnString + "E  ";
				}
				
				if (l.who() == State.FOX) {
					Fox f = (Fox) l;
					returnString = returnString + "F" + f.myAge() + " ";
				}
				
				if (l.who() == State.GRASS) {
					Grass g = (Grass) l;
					returnString = returnString + "G  ";
				}
				
				if (l.who() == State.RABBIT) {
					Rabbit r = (Rabbit) l;
					returnString = returnString + "R" + r.myAge() + " ";
				}
				
			}
			returnString = returnString + "\n";
		}
		
		
		return returnString; 
	}
	
	
	/**
	 * Helper method to get a 3x3 or smaller matrix given the x and y coordinate of the
	 * cell at the center of the matrix.
	 * 
	 * @param xpos the x position of the cell at the center of the neighborhood
	 * @param ypos the y position of the cell at the center of the neighborhood
	 * @return an ArrayList<Living> containing the neighborhood of the given position.
	 */
	public ArrayList<Living> getNeighborhood(int xpos, int ypos) {
		
		ArrayList<Living> neighborhood = new ArrayList<Living>();
		
		for (int i = xpos - 1; i <= xpos + 1; i++) {
			for (int j = ypos - 1; j <= ypos + 1; j++) {
				try {
					neighborhood.add(grid[i][j]);
				} catch (Exception e){
					
				}
			}
		}
		
		return neighborhood;
	}
	

	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly 
	 * generated plain for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		// TODO 
		// 
		// 1. Open the file. 
		// 
		// 2. Write to the file. The five life forms are represented by characters 
		//    B, E, F, G, R. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 
		// 3. Close the file. 
		PrintWriter out = new PrintWriter(outputFileName);
		
		out.println(this.toString());
		out.close();
	}			
}
