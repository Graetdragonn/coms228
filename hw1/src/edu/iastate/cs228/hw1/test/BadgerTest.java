package edu.iastate.cs228.hw1.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw1.Badger;
import edu.iastate.cs228.hw1.Grass;
import edu.iastate.cs228.hw1.Plain;
import edu.iastate.cs228.hw1.Rabbit;
import edu.iastate.cs228.hw1.State;

class BadgerTest {
	Plain p;
	Badger b;
	
	
	@BeforeEach
	void setup() {
		p = new Plain(3);
		b = new Badger(p, 0, 0, 0);
	}
	
	
	/*
	 * Confirm a new Badger object is of State BADGER
	 */
	@Test
	void stateTest() {
		assertEquals(b.who(), State.BADGER, "Badger object state not State.BADGER");
	}
	
	/*
	 * Newly constructed Badger object is age 0
	 */
	@Test
	void ageTest() {
		b = new Badger(p, 0, 0, 0);
		assertEquals(b.myAge(), 0, "Badgers age is not properly set on construction");
	}
	
	/*
	 * Test that a badger dies of old age
	 */
	@Test
	void ruleATest() {
		Plain pNew = new Plain(2);
		pNew.randomInit();
		
		pNew.grid[0][0] = new Badger(pNew, 0, 0, 4);
		pNew.grid[0][0] = new Grass(pNew, 0, 0);
		pNew.grid[0][0] = new Rabbit(pNew, 0, 0, 1);
		pNew.grid[0][0] = new Badger(pNew, 0, 0, 4);
	}
	

}
