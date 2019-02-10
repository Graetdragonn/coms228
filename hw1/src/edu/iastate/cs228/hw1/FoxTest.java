package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 *  
 * @author Brian Bates
 *
 */


class FoxTest {
	int width = 3;
	Plain pOld = new Plain(width);
	
	
	@Test
	void constructorTest() {
		try {
			Fox f = new Fox(pOld, 0, 0, 0);
		} catch (Exception e) {
			fail("Not able to contruct a Fox");
		}
	}
	
	/*
	 * Rule (a): Fox dies of old age
	 */
	@Test
	void ruleATest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule A for cell[0][0]
		pOld.grid[0][0] = new Fox(pOld, 0, 0, Living.FOX_MAX_AGE);
		pOld.grid[0][1] = new Empty(pOld, 0, 1);
		pOld.grid[1][0] = new Empty(pOld, 1, 0);
		pOld.grid[1][1] = new Empty(pOld, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.EMPTY), "Rule A test failed: Fox did not die of old age.  pNew.grid[0][0] was not EMPTY.");		
	}
	
	/*
	 * Rule (b): Fox is killed by too many Badgers
	 */
	@Test
	void ruleBTest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule B for cell[0][0]
		pOld.grid[0][0] = new Fox(pOld, 0, 0, 0);
		pOld.grid[0][1] = new Badger(pOld, 0, 1, 0);
		pOld.grid[1][0] = new Empty(pOld, 1, 0);
		pOld.grid[1][1] = new Badger(pOld, 1, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.BADGER), "Rule B test failed: More Badgers than Foxes.  pNew.grid[0][0] was not a BADGER.");		
	}
	
	@Test
	void ruleCTest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule C for cell[0][0]
		pOld.grid[0][0] = new Fox(pOld, 0, 0, 1);
		pOld.grid[0][1] = new Badger(pOld, 0, 1, 0);
		pOld.grid[1][0] = new Empty(pOld, 1, 0);
		pOld.grid[1][1] = new Badger(pOld, 1, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.BADGER), "Rule C test failed: More Foxes and Badgers than Rabbits.  pNew.grid[0][0] was not a BADGER.");		
	}
	
	@Test
	void ruleDTest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule D for cell[0][0]
		pOld.grid[0][0] = new Fox(pOld, 0, 0, 1);
		pOld.grid[0][1] = new Grass(pOld, 0, 1);
		pOld.grid[1][0] = new Rabbit(pOld, 1, 0, 1);
		pOld.grid[1][1] = new Grass(pOld, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.FOX), "Rule D test failed: Fox did not live on.  pNew.grid[0][0] was not a FOX.");		
	}

}
