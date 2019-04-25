package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw4.ExpressionFormatException;
import edu.iastate.cs228.hw4.PostfixExpression;
import edu.iastate.cs228.hw4.UnassignedVariableException;

/**
 * 
 * @author Brian Bates
 *
 */

class PostfixExpressionTest {

	
	/**
	 * Tests the construction of various postfix expressions
	 */
	@Test
	void constructorTest1() {
		PostfixExpression pe = new PostfixExpression("x 5 +");
		assertEquals("x 5 +", pe.toString());
		
		// Same expression as before, but using the resetPostfix() method
		pe.resetPostfix("x 5 +");
		assertEquals("x 5 +", pe.toString());
		
		pe.resetPostfix(" a  a ^  b     c /    +");
		assertEquals("a a ^ b c / +", pe.toString());
	}
	
	@Test
	void constructorTest2() {
		PostfixExpression pe = new PostfixExpression("P x 5 +");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("Invalid character", exception.getMessage());
	}

	/**
	 * Tests that an invalid character exception is thrown in evaluate()
	 */
	@Test
	void evalTest1() {
		PostfixExpression pe = new PostfixExpression(" x @");
		
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		
		// Add some elements
		hashMap.put('x', 1);
		
		pe.setVarTable(hashMap);
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("Invalid character", exception.getMessage());
	}
	
	/**
	 * Tests that a variable is not present in the hash map
	 */
	@Test
	void evalTest2() {
		PostfixExpression pe = new PostfixExpression("x 5 +");
		
		
		UnassignedVariableException exception = assertThrows(UnassignedVariableException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("Variable x was not assigned a value", exception.getMessage());
	}
	
	/**
	 * Tests that a variable has not been assigned a value.
	 */
	@Test
	void evalTest3() {
		PostfixExpression pe = new PostfixExpression("x 5 +");
		
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		
		// Add some elements
		hashMap.put('y', 1);
		
		pe.setVarTable(hashMap);
		
		UnassignedVariableException exception = assertThrows(UnassignedVariableException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("Variable x was not assigned a value", exception.getMessage());
	}
	
	/**
	 * Tests a zero in the expression
	 */
	@Test
	void evalTest4() throws ExpressionFormatException, UnassignedVariableException {
		PostfixExpression pe = new PostfixExpression("0 5 +");
		
		assertEquals(5, pe.evaluate());
	}
	
	@Test
	void evalTest5() {
		PostfixExpression pe = new PostfixExpression("5 0 /");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("Divide by zero", exception.getMessage());
	}
	
	/**
	 * Compute error exception raising zero to the power of zero
	 */
	@Test
	void evalTest6() {
		PostfixExpression pe = new PostfixExpression("0 0 ^");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("0^0", exception.getMessage());
	}
	
	/**
	 * Compute error exception.  The infix expression (5 * 0) ^ 0
	 */
	@Test
	void evalTest7() {
		PostfixExpression pe = new PostfixExpression("5 0 * 0 ^");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("0^0", exception.getMessage());
	}
	
	/**
	 * Compute error exception.  The infix expression (5 * 0) ^ (5 * 0)
	 */
	@Test
	void evalTest8() {
		PostfixExpression pe = new PostfixExpression("5 0 * 5 0 * ^");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("0^0", exception.getMessage());
	}
	
	/**
	 * Too many operands
	 */
	@Test
	void evalTest9() {
		PostfixExpression pe = new PostfixExpression("5 5");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("Too many operands", exception.getMessage());
	}
	
	/**
	 * Too many operators
	 */
	@Test
	void evalTest10() {
		PostfixExpression pe = new PostfixExpression("5 5 + ^");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("Too many operators", exception.getMessage());
	}
	
	/**
	 * Too many operands
	 */
	@Test
	void evalTest11() {
		PostfixExpression pe = new PostfixExpression("5 x 5 +");
		
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		
		// Add the value of x
		hashMap.put('x', 1);
		
		pe.setVarTable(hashMap);
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("Too many operands", exception.getMessage());
	}
	
	/**
	 * Too many operands
	 */
	@Test
	void evalTest12() throws ExpressionFormatException, UnassignedVariableException {
		PostfixExpression pe = new PostfixExpression("8 2 * 0 d / 2 * +");
		
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		
		// Add the value of x
		hashMap.put('d', 0);
		
		pe.setVarTable(hashMap);
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("Divide by zero", exception.getMessage());
	}
	
	/**
	 * Evaluates the expression with a given hash map, then resets the expression and re-evaluates using the original hash map
	 */
	@Test
	void evalTest13() throws ExpressionFormatException, UnassignedVariableException {
		PostfixExpression pe = new PostfixExpression("x y + z +"); 
		
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		hashMap.put('x', 1);
		hashMap.put('y', 1);
		hashMap.put('z', 1);
		pe.setVarTable(hashMap);
		
		assertEquals(3, pe.evaluate());
		
		// Now change the expression and re-evaluate using the original hash map
		pe.resetPostfix("x y * z *");
		
		assertEquals(1, pe.evaluate());
	}
	
	/**
	 * Evaluates the expression, then adds a new set of variable values to the hash map to get a new value
	 */
	@Test
	void evalTest14() throws ExpressionFormatException, UnassignedVariableException {
		PostfixExpression pe = new PostfixExpression("x y + z +"); 
		
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		hashMap.put('x', 1);
		hashMap.put('y', 1);
		hashMap.put('z', 1);
		pe.setVarTable(hashMap);
		
		assertEquals(3, pe.evaluate());
		
		// Now change the expression and re-evaluate using the original hash map
		hashMap = new HashMap<Character, Integer>();
		hashMap.put('x', 5);
		hashMap.put('y', 5);
		hashMap.put('z', 5);
		pe.setVarTable(hashMap);
		
		assertEquals(15, pe.evaluate());
	}
	
	/**
	 * Invalid character: 'Y' is not lower case
	 */
	@Test
	void malformedInput1() {
		PostfixExpression pe = new PostfixExpression("x Y + z +");
		
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		hashMap.put('x', 1);
		hashMap.put('y', 1);
		hashMap.put('z', 1);
		pe.setVarTable(hashMap);
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("Invalid character", exception.getMessage());
	}
	
	/**
	 * Expected operand
	 */
	@Test
	void malformedInput2() {
		PostfixExpression pe = new PostfixExpression("x y z +");
		
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		hashMap.put('x', 1);
		hashMap.put('y', 1);
		hashMap.put('z', 1);
		pe.setVarTable(hashMap);
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			pe.evaluate();
		});
		
		assertEquals("Too many operands", exception.getMessage());
	}
}
