package edu.iastate.cs228.hw1;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *  
 * @author Brian Bates
 *
 */


class PlainTest {

	private int width = 3;
	private Plain testPlain;
	
	
//	@BeforeEach
//	void construction() {
//		
//	}
	
	/*
	 * Testing if the basic constructor creates the correct width
	 */
	@Test
	void constructorWidthTest() {
		// Construct a randomly populated plain
		testPlain = new Plain(width);
		
		assertEquals(width, testPlain.getWidth(), "Plain is not the same width as constructor designated.");
	}
	
	/*
	 * Confirm a newly constructed plain does not have a populated grid
	 */
	@Test
	void plainNotInitializedOnConstruction() {
		// Construct a randomly populated plain
		testPlain = new Plain(width);
		
		boolean check = true;
		
		for (int i = 0; i < testPlain.grid.length; i++) {
			for (int j = 0; j < testPlain.grid[0].length; j++) {
				if (testPlain.grid[i][j] != null) {
					check = false;
				}
			}
		}
		
		assertEquals(check, true, "Test plain was initialized on construction.");
	}
	
	/*
	 * Test that the random distribution of each of the five living forms generated is actually normal
	 */
	@Test
	void randomlyPopulated() {
		int plainWidth = 10;
		int iterations = 1000;
		
		int badgerCount = 0;
		int emptyCount = 0;
		int foxCount = 0;
		int grassCount = 0;
		int rabbitCount = 0;
		
		for (int i = 0; i < iterations; i++) {
			// Generate a new plain and populate it with random animals
			testPlain = new Plain(plainWidth);
			testPlain.randomInit();
			
			for (int j = 0; j < plainWidth; j++) {
				for (int k = 0; k < plainWidth; k++) {
					if (testPlain.grid[j][k].who() == State.BADGER) {
						badgerCount += 1;
					}
					
					if (testPlain.grid[j][k].who() == State.EMPTY) {
						emptyCount += 1;
					}
					
					if (testPlain.grid[j][k].who() == State.FOX) {
						foxCount += 1;
					}
					
					if (testPlain.grid[j][k].who() == State.GRASS) {
						grassCount += 1;
					}
					
					if (testPlain.grid[j][k].who() == State.RABBIT) {
						rabbitCount += 1;
					}
				}
			}
		}
		
		if (!(19000 <= badgerCount) && !(badgerCount < 21000)) {
			fail("Number of Badgers generated is not evenly distributed");
		}
		
		if (!(19000 <= emptyCount) && !(emptyCount < 21000)) {
			fail("Number of Empties generatated is not evenly distributed");
		}
		
		if (!(19000 <= foxCount) && !(foxCount < 21000)) {
			fail("Number of Foxes generated is not evenly distributed");
		}
		
		if (!(19000 <= grassCount) && !(grassCount < 21000)) {
			fail("Number of Grasses generated is not evenly distributed");
		}
		
		if (!(19000 <= rabbitCount) && !(rabbitCount < 21000)) {
			fail("Number of Rabbits generated is not evenly distributed");
		}
	}

	/*
	 * Test that the grid is a square with equal number of rows and columns
	 */
	@Test
	public void squareGrid() {
		testPlain = new Plain(width);
		
		assertEquals(testPlain.grid.length, testPlain.grid[0].length, "The plain grid was not constructed as a perfect square.");
	}
	
}
