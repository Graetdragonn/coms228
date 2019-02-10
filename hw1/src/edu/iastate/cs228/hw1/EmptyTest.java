package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 *  
 * @author Brian Bates
 *
 */


class EmptyTest {
	int width = 3;
	Plain pOld = new Plain(width);
	
	@Test
	void constructorTest() {
		try {
			Empty e = new Empty(pOld, 0, 0);
		} catch (Exception e) {
			fail("Unable to construct Empty");
		}
	}
	
	@Test
	void ruleATest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule A for cell[0][0]
		pOld.grid[0][0] = new Empty(pOld, 0, 0);
		pOld.grid[0][1] = new Empty(pOld, 0, 1);
		pOld.grid[1][0] = new Empty(pOld, 1, 0);
		pOld.grid[1][1] = new Empty(pOld, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.EMPTY), "Rule A test failed.  pNew.grid[0][0] was not EMPTY.");		
	}
	
	@Test
	void ruleBTest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule B for cell[0][0]
		pOld.grid[0][0] = new Empty(pOld, 0, 0);
		pOld.grid[0][1] = new Fox(pOld, 0, 1, 0);
		pOld.grid[1][0] = new Empty(pOld, 1, 0);
		pOld.grid[1][1] = new Fox(pOld, 1, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.FOX), "Rule B test failed.  pNew.grid[0][0] was not a FOX.");		
	}
	
	@Test
	void ruleCTest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule C for cell[0][0]
		pOld.grid[0][0] = new Empty(pOld, 0, 0);
		pOld.grid[0][1] = new Badger(pOld, 0, 1, 0);
		pOld.grid[1][0] = new Empty(pOld, 1, 0);
		pOld.grid[1][1] = new Badger(pOld, 1, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.BADGER), "Rule C test failed.  pNew.grid[0][0] was not a BADGER.");		
	}
	
	@Test
	void ruleDTest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule D for cell[0][0]
		pOld.grid[0][0] = new Empty(pOld, 0, 0);
		pOld.grid[0][1] = new Grass(pOld, 0, 1);
		pOld.grid[1][0] = new Empty(pOld, 1, 0);
		pOld.grid[1][1] = new Grass(pOld, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.GRASS), "Rule D test failed.  pNew.grid[0][0] was not a GRASS.");		
	}

	@Test
	void ruleETest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule E for cell[0][0]
		pOld.grid[0][0] = new Empty(pOld, 0, 0);
		pOld.grid[0][1] = new Empty(pOld, 0, 1);
		pOld.grid[1][0] = new Empty(pOld, 1, 0);
		pOld.grid[1][1] = new Empty(pOld, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.EMPTY), "Rule E test failed.  pNew.grid[0][0] was not a EMPTY.");		
	}
}
