package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *  
 * @author Brian Bates
 *
 */


class GrassTest {

	int width = 3;
	Plain pOld = new Plain(width);
	
	
	@Test
	void constructorTest() {
		try {
			Grass g = new Grass(pOld, 0, 0);
		} catch (Exception e) {
			fail("Not able to contruct a Grass object.");
		}
	}
	
	/*
	 * Rule A: Grass is eaten by Rabbits
	 */
	@Test
	void ruleATest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule A for cell[0][0]
		pOld.grid[0][0] = new Grass(pOld, 0, 0);
		pOld.grid[0][1] = new Rabbit(pOld, 0, 1, 0);
		pOld.grid[1][0] = new Rabbit(pOld, 1, 0, 0);
		pOld.grid[1][1] = new Rabbit(pOld, 1, 1, 0);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.EMPTY), "Rule A test failed: Grass should have been eaten by Rabbits.  pNew.grid[0][0] was not EMPTY.");		
	}
	
	/*
	 * Rule B: Replace with Rabbit if there are three or more Rabbits in the neighborhood.
	 */
	@Test
	void ruleBTest() {
		pOld = new Plain(4);
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule B for cell[0][0]
		pOld.grid[0][0] = new Rabbit(pOld, 0, 0, 1);
		pOld.grid[0][1] = new Grass(pOld, 0, 1);
		pOld.grid[0][2] = new Rabbit(pOld, 0, 2, 1);
		pOld.grid[1][0] = new Rabbit(pOld, 1, 0, 1);
		pOld.grid[1][1] = new Grass(pOld, 1, 1);
		pOld.grid[1][2] = new Grass(pOld, 1, 2);
		
		pNew.grid[0][1] = pOld.grid[0][1].next(pNew);
		
		assertTrue(pNew.grid[0][1].who().equals(State.RABBIT), "Rule B test failed: Rabbit should replace Grass.  pNew.grid[0][1] was not RABBIT.");		
	}
	
	/*
	 * Rule C:  Badgers and Foxes outnumber Rabbits and Foxes outnumber Badgers
	 */
	@Test
	void ruleCTest() {
		pOld = new Plain(4);
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule B for cell[0][0]
		pOld.grid[0][0] = new Fox(pOld, 0, 0, 1);
		pOld.grid[0][1] = new Grass(pOld, 0, 1);
		pOld.grid[0][2] = new Rabbit(pOld, 0, 2, 1);
		pOld.grid[1][0] = new Rabbit(pOld, 1, 0, 1);
		pOld.grid[1][1] = new Grass(pOld, 1, 1);
		pOld.grid[1][2] = new Grass(pOld, 1, 2);
		
		pNew.grid[0][1] = pOld.grid[0][1].next(pNew);
		
		
		assertTrue(pNew.grid[0][1].who().equals(State.GRASS), "Rule C test failed: Grass should not change.  pNew.grid[0][0] was not a GRASS.");		
	}
	
	}
