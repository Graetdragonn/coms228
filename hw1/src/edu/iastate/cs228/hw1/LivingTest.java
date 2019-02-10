package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 *  
 * @author Brian Bates
 *
 */


class LivingTest {

	// Simple test of the census() method
	@Test
	void test() {
		Plain pOld = new Plain(4);
		
		// Manipulate grid to test rule A for cell[0][0]
		pOld.grid[0][0] = new Rabbit(pOld, 0, 0, Living.RABBIT_MAX_AGE);
		pOld.grid[0][1] = new Empty(pOld, 0, 1);
		pOld.grid[1][0] = new Empty(pOld, 1, 0);
		pOld.grid[1][1] = new Empty(pOld, 1, 1);
		
		
		int[] population = {0, 0, 0, 0, 0};
		pOld.grid[0][0].census(population);
		
		System.out.println(Arrays.toString(population));
		
		if (population[0] != 0) {
			fail("Living census method failed.  0 Badgers should have been detected. (" + population[0] + ") were.");
		}
		
		if (population[1] != 3) {
			fail("Living census method failed.  3 Empty spaces should have been detected. (" + population[1] + ") were.");
		}
		
		if (population[2] != 0) {
			fail("Living census method failed.  0 Foxes should have been detected. (" + population[2] + ") were.");
		}
		
		if (population[3] != 0) {
			fail("Living census method failed.  0 Grass should have been detected.(" + population[3] + ") were.");
		}
		
		if (population[4] != 1) {
			fail("Living census method failed.  1 Rabbit should have been detected. (" + population[4] + ") were.");
		}
	}

}
