package edu.iastate.cs228.hw1.test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw1.Plain;
import edu.iastate.cs228.hw1.Wildlife;

class WildlifeTest {

	/*
	 * Test the first public file
	 */
	@Test
	void public1Test() throws FileNotFoundException {
		Plain even = new Plain("public1-3x3.txt");
		Plain odd = new Plain(even.getWidth());
		Plain solution = new Plain("public1-5cycles.txt");
		
		int cycles = 5;
		
		// Go through the cycles
		for (int i = 0; i < cycles; i++) {
			if (i % 2 == 0) {
				// Currently Even
				Wildlife.updatePlain(even, odd);
			} else {
				// Currently Odd
				Wildlife.updatePlain(odd, even);
			}		
		}
		assertTrue(odd.toString().equals(solution.toString()), "public1-3x3.txt, 5 cycles fails");
	}
	
	/*
	 * Test the second public file
	 */
	@Test
	void public2Test() throws FileNotFoundException {
		Plain even = new Plain("public2-6x6.txt");
		Plain odd = new Plain(even.getWidth());
		Plain solution = new Plain("public2-8cycles.txt");
		
		int cycles = 8;
		
		// Go through the cycles
		for (int i = 0; i < cycles; i++) {
			if (i % 2 == 0) {
				// Currently Even
				Wildlife.updatePlain(even, odd);
			} else {
				// Currently Odd
				Wildlife.updatePlain(odd, even);
			}		
		}
		assertTrue(even.toString().equals(solution.toString()), "public2-6x6.txt, 8 cycles fails");
	}

	
	/*
	 * Test the third public file
	 */
	@Test
	void public3Test() throws FileNotFoundException {
		Plain even = new Plain("public3-10x10.txt");
		Plain odd = new Plain(even.getWidth());
		Plain solution = new Plain("public3-6cycles.txt");
		
		int cycles = 6;
		
		// Go through the cycles
		for (int i = 0; i < cycles; i++) {
			if (i % 2 == 0) {
				// Currently Even
				Wildlife.updatePlain(even, odd);
			} else {
				// Currently Odd
				Wildlife.updatePlain(odd, even);
			}		
		}
		assertTrue(even.toString().equals(solution.toString()), "public3-10x10.txt, 6 cycles fails");
	}
	
	/*
	 * Test the sixth test file (First from simulation handout
	 */
	@Test
	void test6() throws FileNotFoundException {
		Plain even = new Plain("test6-3x3.txt");
		Plain odd = new Plain(even.getWidth());
		Plain solution = new Plain("test6-1cycle.txt");
		
		int cycles = 1;
		
		// Go through the cycles
		for (int i = 0; i < cycles; i++) {
			if (i % 2 == 0) {
				// Currently Even
				Wildlife.updatePlain(even, odd);
			} else {
				// Currently Odd
				Wildlife.updatePlain(odd, even);
			}		
		}
		assertTrue(odd.toString().equals(solution.toString()), "test6-3x3.txt, 1 cycle fails: \n" + odd.toString() + "\n" + "\n" + solution.toString());
	}
	
	/*
	 * Test the seventh test file (Second from simulation handout
	 */
	@Test
	void test7() throws FileNotFoundException {
		Plain even = new Plain("test7-6x6.txt");
		Plain odd = new Plain(even.getWidth());
		Plain solution = new Plain("test7-8cycles.txt");
		
		int cycles = 8;
		
		// Go through the cycles
		for (int i = 0; i < cycles; i++) {
			if (i % 2 == 0) {
				// Currently Even
				Wildlife.updatePlain(even, odd);
			} else {
				// Currently Odd
				Wildlife.updatePlain(odd, even);
			}		
		}
		assertTrue(even.toString().equals(solution.toString()), "test7-6x6.txt, 8 cycles fails: \n" + even.toString() + "\n" + "\n" + solution.toString());
	}
}
