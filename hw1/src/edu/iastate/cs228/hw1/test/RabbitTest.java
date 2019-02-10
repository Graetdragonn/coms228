package edu.iastate.cs228.hw1.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw1.Badger;
import edu.iastate.cs228.hw1.Empty;
import edu.iastate.cs228.hw1.Fox;
import edu.iastate.cs228.hw1.Grass;
import edu.iastate.cs228.hw1.Living;
import edu.iastate.cs228.hw1.Plain;
import edu.iastate.cs228.hw1.Rabbit;
import edu.iastate.cs228.hw1.State;

class RabbitTest {

	int width = 3;
	Plain pOld = new Plain(width);
	
	
	@Test
	void constructorTest() {
		try {
			Rabbit r = new Rabbit(pOld, 0, 0, 0);
		} catch (Exception e) {
			fail("Not able to contruct a Rabbit object.");
		}
	}
	
	/*
	 * Rule (a): Rabbit dies of old age
	 */
	@Test
	void ruleATest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule A for cell[0][0]
		pOld.grid[0][0] = new Rabbit(pOld, 0, 0, Living.RABBIT_MAX_AGE);
		pOld.grid[0][1] = new Empty(pOld, 0, 1);
		pOld.grid[1][0] = new Empty(pOld, 1, 0);
		pOld.grid[1][1] = new Empty(pOld, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.EMPTY), "Rule A test failed: Rabbit did not die of old age.  pNew.grid[0][0] was not EMPTY.");		
	}
	
	/*
	 * Rule (b): Rabbit starves
	 */
	@Test
	void ruleBTest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule B for cell[0][0]
		pOld.grid[0][0] = new Rabbit(pOld, 0, 0, 0);
		pOld.grid[0][1] = new Empty(pOld, 0, 1);
		pOld.grid[1][0] = new Empty(pOld, 1, 0);
		pOld.grid[1][1] = new Empty(pOld, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.EMPTY), "Rule B test failed: Rabbit did not die of starvation.  pNew.grid[0][0] was not EMPTY.");		
	}
	
	/*
	 * Rule C:  Badgers and Foxes outnumber Rabbits and Foxes outnumber Badgers
	 */
	@Test
	void ruleCTest() {
		pOld = new Plain(4);
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule C for cell[0][0]
		pOld.grid[0][0] = new Grass(pOld, 0, 0);
		pOld.grid[0][1] = new Rabbit(pOld, 0, 1, 1);
		pOld.grid[0][2] = new Badger(pOld, 0, 2, 0);
		pOld.grid[1][0] = new Fox(pOld, 1, 0, 1);
		pOld.grid[1][1] = new Fox(pOld, 1, 1, 1);
		pOld.grid[1][2] = new Fox(pOld, 1, 2, 1);
		
		pNew.grid[0][1] = pOld.grid[0][1].next(pNew);
		
		assertTrue(pNew.grid[0][1].who().equals(State.FOX), "Rule C test failed: Rabbit should be replaced with a Fox.  pNew.grid[0][1] was not a FOX (" + pNew.grid[0][1].who() + ")");		
	}
	
	/*
	 * Rule D:  Badgers and Foxes outnumber Rabbits and Badgers outnumber Foxes
	 */
	@Test
	void ruleDTest() {
		pOld = new Plain(4);
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule C for cell[0][0]
		pOld.grid[0][0] = new Grass(pOld, 0, 0);
		pOld.grid[0][1] = new Rabbit(pOld, 0, 1, 1);
		pOld.grid[0][2] = new Badger(pOld, 0, 2, 0);
		pOld.grid[1][0] = new Fox(pOld, 1, 0, 1);
		pOld.grid[1][1] = new Badger(pOld, 1, 1, 1);
		pOld.grid[1][2] = new Badger(pOld, 1, 2, 1);
		
		pNew.grid[0][1] = pOld.grid[0][1].next(pNew);
		
		assertTrue(pNew.grid[0][1].who().equals(State.BADGER), "Rule D test failed: Rabbit should be replaced with a Badger.  pNew.grid[0][1] was not a BADGER (" + pNew.grid[0][1].who() + ")");		
	}

	/*
	 * Rule E:  Rabbit lives on
	 */
	@Test
	void ruleETest() {
		pOld.randomInit();
		Plain pNew = new Plain(width);
		
		// Manipulate grid to test rule D for cell[0][0]
		pOld.grid[0][0] = new Rabbit(pOld, 0, 0, 1);
		pOld.grid[0][1] = new Grass(pOld, 0, 1);
		pOld.grid[1][0] = new Grass(pOld, 1, 0);
		pOld.grid[1][1] = new Grass(pOld, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.RABBIT), "Rule E test failed: Rabbit did not live on and age one year.  pNew.grid[0][0] was not a RABBIT.");		
	}
}
