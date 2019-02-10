package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 *  
 * @author Brian Bates
 *
 */


class AnimalTest {
	Plain p = new Plain(4);
	
	/*
	 * Test a Badger is created with the correct age.  Also tests that myAge() method is called.
	 */
	@Test
	void badgerAgeTest() {
		Badger b = new Badger(p, 0, 0, 4);
		
		assertEquals(b.myAge(), 4, "New Badger object is not constructed with the correct age.");
	}
	
	/*
	 * Test a Fox is created with the correct age.  Also tests that myAge() method is called.
	 */
	@Test
	void foxAgeTest() {
		Fox f = new Fox(p, 0, 0, 4);
		
		assertEquals(f.myAge(), 4, "New Fox object is not constructed with the correct age.");
	}
	
	/*
	 * Test a Badger is created with the correct age.  Also tests that myAge() method is called.
	 */
	@Test
	void rabbitAgeTest() {
		Rabbit r = new Rabbit(p, 0, 0, 3);
		
		assertEquals(r.myAge(), 3, "New rabbit object is not constructed with the correct age.");
	}
	

}
