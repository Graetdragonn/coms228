package edu.iastate.cs228.hw4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

/**
 * Special case where I am testing protected methods in Expression that can't be accessed from the test package
 * 
 * @author Brian Bates
 *
 */

class ExpressionTest {

	/**
	 * Clear the hashmap before you putAll from a new map.  Requires the protected method mapContains() in Expression
	 */
	@Test
	void clearHashMapTest1() {
		Expression ie = new InfixExpression("5");
		
		// Initialize the hash map
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		
		// Add some elements
		hashMap.put('x', 1);
		hashMap.put('y', 2);
		hashMap.put('z', 3);
		
		// Add the initial hash map
		ie.setVarTable(hashMap);
		
		// Create a new Hash Map (you could have just cleared the old one)
		hashMap = new HashMap<Character, Integer>();
		
		hashMap.put('a', 4);
		hashMap.put('b', 5);
		hashMap.put('c', 6);
		
		ie.setVarTable(hashMap);
		
		// Check the hash map in the InfixExpression object does not contain the original key-value pairs
		char[] a = {'x', 'y', 'z'};
		
		for (int i = 0; i < a.length; i++) {
			if (ie.mapContains(a[i])) {
				fail("Hash map wasn't cleared before adding new elements");
			}
		}
	}
	
	/**
	 * Tests the detection of operators in a string.  This is here to test the "protected" method
	 * {@link Expression#isOperator(char)}
	 */
	@Test
	void operatorTest() {
		// Make a new InfixExpression with whatever
		InfixExpression ie = new InfixExpression("( x + y )");
		
		// Run the gamut of possible expressions for true operators
		assertTrue(ie.isOperator('~'));
		assertTrue(ie.isOperator('+'));
		assertTrue(ie.isOperator('-'));
		assertTrue(ie.isOperator('*'));
		assertTrue(ie.isOperator('/'));
		assertTrue(ie.isOperator('%'));
		assertTrue(ie.isOperator('^'));
		assertTrue(ie.isOperator('('));
		assertTrue(ie.isOperator(')'));
		
		// Run some false expressions
		assertFalse(ie.isOperator('!'));
		assertFalse(ie.isOperator('@'));
		assertFalse(ie.isOperator('#'));
		assertFalse(ie.isOperator('$'));
	}
	
	/**
	 * {@link Expression#isInt(String)}
	 */
	@Test
	void integerTest() {
		InfixExpression ie = new InfixExpression("5");
		
		// Two checks on integers
		assertTrue(ie.isInt("5"));
		assertFalse(ie.isInt("a"));
	}
	
	/**
	 * {@link Expression#isVariable(char)}
	 */
	@Test
	void isVariableTest() {
		InfixExpression ie = new InfixExpression("a");
		
		// A few checks
		assertTrue(ie.isVariable('a'));
		assertFalse(ie.isVariable('A'));  // Uppercase fail
		assertFalse(ie.isVariable('5'));  // Integer fail
		assertFalse(ie.isVariable('!'));  // Symbol fail
	}
	
	/**
	 * Tests that a key is present in the hash map
	 */
	@Test
	void presentHashValueTest1() {
		// Create an expression
		InfixExpression ie = new InfixExpression("I ( x + 5 )");
		
		// Create the hash map
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		hashMap.put('x', 5);
		
		// Add the hash map to the expression
		ie.setVarTable(hashMap);
		
		// Check the method can find the key in the map.  This is a protected helper method created in Expression
		assertTrue(ie.mapContains('x'));
	}
	
	/**
	 * Tests that a key is not present in the hash map
	 */
	@Test
	void presentHashValueTest2() {
		// Create an expression
		InfixExpression ie = new InfixExpression("I ( x + 5 )");
		
		// Create the hash map
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		hashMap.put('x', 5);
		
		// Add the hash map to the expression
		ie.setVarTable(hashMap);
		
		// Check the method can find the key in the map.  This is a protected helper method created in Expression
		assertFalse(ie.mapContains('a'));
	}
	
	/**
	 * Tests that a key value is found and returned
	 */
	@Test
	void presentHashValueTest3() {
		// Create an expression
		InfixExpression ie = new InfixExpression("I ( x + 5 )");
		
		// Create the hash map
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		hashMap.put('x', 5);
		
		// Add the hash map to the expression
		ie.setVarTable(hashMap);
		
		// Check the method can find the key in the map.  This is a protected helper method created in Expression
		assertTrue(ie.mapContains('x'));
		assertEquals(ie.getMapValue('x'), 5);
	}
	
	@Test
	void removeExtraWhiteSpaceTest1() {
		InfixExpression ie = new InfixExpression("5");
		
		// A few tests
		assertEquals("( x + 5 )", ie.removeExtraSpaces("(   x   +     5       )"));
		assertEquals("( x + 10 y * 3 )", ie.removeExtraSpaces("( x   +      10    y *       3 )"));
		assertEquals("a = 2", ie.removeExtraSpaces("a   = 2"));
		assertEquals("a = 2", ie.removeExtraSpaces("a = 2 "));
		assertEquals("( x + 5 )", ie.removeExtraSpaces("(x + 5)"));
	}
	
}
