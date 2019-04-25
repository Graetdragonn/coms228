package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw4.Operator;

class OperatorTest {

	/**
	 * Tests the basic construction and retrieval of an operator
	 */
	@Test
	void basicConstructionTest() {
		Operator o = new Operator('~');
		assertEquals('~', o.getOp());
	}
	
	/**
	 * A series of tests to evaluate the comparison of two operators
	 */
	@Test
	void compareToTests() {
		Operator one = new Operator('~');
		Operator two = new Operator('+');
		assertTrue(one.compareTo(two) == 1);
		
		one = new Operator('+');
		two = new Operator('-');
		assertTrue(one.compareTo(two) == 0);
		
		one = new Operator('*');
		two = new Operator('+');
		assertTrue(one.compareTo(two) == 1);
	}

}
