package edu.iastate.cs228.hw1.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw1.Badger;
import edu.iastate.cs228.hw1.Empty;
import edu.iastate.cs228.hw1.Fox;
import edu.iastate.cs228.hw1.Grass;
import edu.iastate.cs228.hw1.Living;
import edu.iastate.cs228.hw1.Plain;
import edu.iastate.cs228.hw1.Rabbit;
import edu.iastate.cs228.hw1.State;

class BadgerTest {
	Plain p;
	Badger b;
	
	
	@BeforeEach
	void setup() {
		p = new Plain(3);
	}
	
	@Test
	void constructorTest() {
		try {
			Badger b = new Badger(p, 0, 0, 0);
		} catch (Exception e) {
			fail("constructorTest: \n" + "Unable to construct a Badger object.");
		}
	}
	
	
	/*
	 * Confirm a new Badger object is of State BADGER
	 */
	@Test
	void stateTest() {
		b = new Badger(p, 0, 0, 0);
		assertEquals(b.who(), State.BADGER, "Badger object state not State.BADGER");
	}
	
	/*
	 * Newly constructed Badger object is age 0
	 */
	@Test
	void correctAgeTest() {
//		assertEquals(Living.BADGER_MAX_AGE, 4);
		b = new Badger(p, 0, 0, Living.BADGER_MAX_AGE);
		assertEquals(b.myAge(), 4, "Badgers age is not properly set on construction:\n" + b.myAge());
	}
	
	
	/*
	 * Test Rule (a) that a badger dies of old age
	 */
	@Test
	void ruleATest() {
		Plain pOld = new Plain(2);
		Plain pNew = new Plain(2);
		pOld.randomInit();
		
		// Specifically adjust the grid to make the Badger it's maximum age
		pOld.grid[0][0] = new Badger(pOld, 0, 0, Living.BADGER_MAX_AGE);
		pOld.grid[0][1] = new Grass(pOld, 0, 1);
		pOld.grid[1][0] = new Rabbit(pOld, 1, 0, 1);
		pOld.grid[1][1] = new Badger(pOld, 1, 1, 4);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.EMPTY), "Badger did not die of old age.\n" + pNew.grid[0][0].who());
	}
	
	/*
	 * Test Rule (b) that a badger dies of a fox attack
	 */
	@Test
	void ruleBTest() {
		Plain pOld = new Plain(2);
		Plain pNew = new Plain(2);
		pOld.randomInit();
		
		// Specifically adjust the grid to make the Badger it's maximum age
		pOld.grid[0][0] = new Badger(pOld, 0, 0, 0);
		pOld.grid[0][1] = new Fox(pOld, 0, 1, 0);
		pOld.grid[1][0] = new Rabbit(pOld, 1, 0, 1);
		pOld.grid[1][1] = new Fox(pOld, 1, 1, 0);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.FOX), "Badger did not die from a fox attack.\n" + pNew.grid[0][0].who());
	}
	
	/*
	 * Test Rule (c) that a badger dies of hunger
	 * Badgers and Foxes together outnumber Rabbits
	 */
	@Test
	void ruleCTest() {
		Plain pOld = new Plain(2);
		Plain pNew = new Plain(2);
		pOld.randomInit();
		
		// Specifically adjust the grid to make the Badger it's maximum age
		pOld.grid[0][0] = new Badger(pOld, 0, 0, 0);
		pOld.grid[0][1] = new Fox(pOld, 0, 1, 0);
		pOld.grid[1][0] = new Rabbit(pOld, 1, 0, 1);
		pOld.grid[1][1] = new Empty(pOld, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		assertTrue(pNew.grid[0][0].who().equals(State.EMPTY), "Badger did not die from hunger.\n" + pNew.grid[0][0].who());
	}

	/*
	 * Test Rule (d) that a badger lives on
	 */
	@Test
	void ruleDTest() {
		Plain pOld = new Plain(2);
		Plain pNew = new Plain(2);
		pOld.randomInit();
		
		// Specifically adjust the grid to make the Badger it's maximum age
		pOld.grid[0][0] = new Badger(pOld, 0, 0, 0);
		pOld.grid[0][1] = new Grass(pOld, 0, 1);
		pOld.grid[1][0] = new Rabbit(pOld, 1, 0, 1);
		pOld.grid[1][1] = new Empty(pOld, 1, 1);
		
		pNew.grid[0][0] = pOld.grid[0][0].next(pNew);
		
		Badger b2 = (Badger) pNew.grid[0][0];
		
		assertTrue((pNew.grid[0][0].who().equals(State.BADGER)) && (b2.myAge() == 1), "Badger did not live on and age one year.\n" + pNew.grid[0][0].who());
	}
}
