package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw4.ExpressionFormatException;
import edu.iastate.cs228.hw4.InfixExpression;
import edu.iastate.cs228.hw4.PostfixExpression;
import edu.iastate.cs228.hw4.UnassignedVariableException;

/**
 * 
 * @author Brian Bates
 *
 */

class InfixExpressionTest {

	/**
	 * Tests the creation of an InfixExpression object with a 
	 * string expression and the return of the expression, properly formatted.
	 * 
	 * {@link InfixExpression#InfixExpression(String}
	 */
	@Test
	void constructorTest1() {
		// Basic constructor
		InfixExpression ie = new InfixExpression(" 3 + 5");
		
		// Simply check the string formed is correct
		assertEquals("3 + 5", ie.toString());
		
		// Repeat the test with more complicated expressions to be cleaned up
		ie = new InfixExpression("  3   + 5");
		assertEquals("3 + 5", ie.toString());
		
		// Repeat the test with more complicated expressions to be cleaned up
		ie = new InfixExpression("      (   x       +     y         ) *             z");
		assertEquals("(x + y) * z", ie.toString());
		
		// Repeat with an even more complicated expression
		ie = new InfixExpression("( ( x + 5 ) * 9 ) * 4");
		assertEquals("((x + 5) * 9) * 4", ie.toString());
		
		// Repeat, but with an expression containing a tab
		ie = new InfixExpression("(			( 	x 	+ 	5 ) *		9 ) * 4");
		assertEquals("((x + 5) * 9) * 4", ie.toString());
	}

	
	@Test
	void constructorTest2() {
		InfixExpression ie = new InfixExpression("I x  +  3");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			ie.evaluate();
		});
		
		assertEquals("Invalid character", exception.getMessage());
	}
	
	/**
	 * Series of increasingly complex tests created during developments
	 * {@link InfixExpression#evaluate()}
	 */
	@Test
	void eval1() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression(" 3 + 5");
		assertEquals(8, ie.evaluate());
	}
	
	/**
	 * Evaluates the InfixExpression
	 */
	@Test
	void eval2() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression("3 + 5 + 11");
		assertEquals(19, ie.evaluate());
	}
	
	/**
	 * Evaluates the InfixExpression
	 */
	@Test
	void eval3() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression("3 * 5");
		assertEquals(15, ie.evaluate());
	}
	
	/**
	 * Evaluates the InfixExpression
	 */
	@Test
	void eval4() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression("3 % 5");
		assertEquals(3, ie.evaluate());
	}
	
	/**
	 * Evaluates the InfixExpression
	 */
	@Test
	void eval5() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression(" ( 5 + 1 ) % 3");
		assertEquals(0, ie.evaluate());
	}
	
	/**
	 * Evaluates the InfixExpression
	 */
	@Test
	void eval6() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression("( 5 + 1 ) / 3");
		assertEquals(2, ie.evaluate());
	}
	
	/**
	 * Evaluates the InfixExpression
	 */
	@Test
	void eval7() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression(" 8 / ( 1 + 3 )");
		assertEquals(2, ie.evaluate());
	}
	
	/**
	 * Evaluates the InfixExpression
	 */
	@Test
	void eval8() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression(" 8 / ( 1 + 3 ) - 6");
		assertEquals("8 1 3 + / 6 -", ie.postfixString());
		assertEquals(-4, ie.evaluate());
	}
	
	/**
	 * Evaluates the InfixExpression
	 */
	@Test
	void eval9() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression(" 2 ^ 2");
		assertEquals("2 2 ^", ie.postfixString());
		assertEquals(4, ie.evaluate());
	}
	
	/**
	 * Throws UnassignedVariableException because the hash map of values has not been passed
	 */
	@Test
	void eval10() {
		InfixExpression ie = new InfixExpression(" x + 4");
		
		UnassignedVariableException exception = assertThrows(UnassignedVariableException.class, () -> {
			ie.evaluate();
		});
		
		assertEquals("Variable x was not assigned a value", exception.getMessage());
	}
	
	/**
	 * Evaluates the InfixExpression
	 */
	@Test
	void eval11() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression("8 * 2 + 19 / 2 * 2");
		assertEquals(34, ie.evaluate());
	}
	
	/**
	 * Evaluates the expression, then adds a new set hash map, then re-evaluates the expression
	 */
	@Test
	void eval12() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression("x + y + z");
		
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		hashMap.put('x', 1);
		hashMap.put('y', 1);
		hashMap.put('z', 1);
		ie.setVarTable(hashMap);
		
		assertEquals(3, ie.evaluate());
		
		// Now make a whole new hash map (not just update the value) and re-evaluate
		hashMap = new HashMap<Character, Integer>();
		hashMap.put('x', 5);
		hashMap.put('y', 5);
		hashMap.put('z', 5);
		ie.setVarTable(hashMap);
		
		assertEquals(15, ie.evaluate());		
	}
	
	/**
	 * Evaluates the expression with a given hashmap, then resets the expression and re-evaluates using the original hashmap
	 */
	@Test
	void eval13() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression("x + y + z"); 
		
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		hashMap.put('x', 1);
		hashMap.put('y', 1);
		hashMap.put('z', 1);
		ie.setVarTable(hashMap);
		
		assertEquals(3, ie.evaluate());
		
		// Now change the expression and re-evaluate using the original hash map
		ie.resetInfix("x * y * z");
		
		assertEquals(1, ie.evaluate());		
	}
	
	/**
	 * Another test of unary
	 * @throws ExpressionFormatException
	 * @throws UnassignedVariableException
	 */
	@Test
	void eval14() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression("8 - - - 3");
		assertEquals(5, ie.evaluate());
	}
	
	/**
	 * Multiplication test
	 * @throws ExpressionFormatException
	 * @throws UnassignedVariableException
	 */
	@Test
	void eval15() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression("0 * 585 - 2 * - 25 + 15");
		assertEquals(65, ie.evaluate());
	}
	
	@Test
	void eval16() throws ExpressionFormatException, UnassignedVariableException {
		InfixExpression ie = new InfixExpression("8 - - - 10 * - 2");
		assertEquals(28, ie.evaluate());
		assertEquals("8 10 ~ ~ 2 ~ * -", ie.postfixString());
	}
	
	/**
	 * The following infixPostfix#() tests are sequentially more difficult tests that check the progression.
	 * This is done to test the postfix conversion.
	 * 
	 * {@link InfixExpression#postfix()}
	 */
	@Test
	void infixPostfix1() throws ExpressionFormatException {
		InfixExpression ie = new InfixExpression("5");
		ie.postfix();
		assertEquals("5", ie.toString());
		assertEquals("5", ie.postfixString());
	}
	
	
	@Test
	void infixPostfix2() throws ExpressionFormatException {
		InfixExpression ie = new InfixExpression("- 3");
		ie.postfix();
		assertEquals("- 3", ie.toString());
		assertEquals("3 ~", ie.postfixString());
	}
	
	
	@Test
	void infixPostfix3() throws ExpressionFormatException {
		InfixExpression ie = new InfixExpression("x + 5");
		ie.postfix();
		assertEquals("x + 5", ie.toString());		// Infix
		assertEquals("x 5 +", ie.postfixString());	// Postfix
	}
	
	@Test
	void infixPostfix4() throws ExpressionFormatException {
		InfixExpression ie = new InfixExpression("( 2 + 3 )");
		ie.postfix();
		assertEquals("(2 + 3)", ie.toString());		// Infix
		assertEquals("2 3 +", ie.postfixString());	// Postfix
	}
	
	@Test
	void infixPostfix5() throws ExpressionFormatException {
		InfixExpression ie = new InfixExpression("  2 * i + 3 ");
		ie.postfix();
		assertEquals("2 * i + 3", ie.toString(), "infixPostfix5a()");		// Infix
		assertEquals("2 i * 3 +", ie.postfixString(), "infixPostfix5b()");	// Postfix
	}
	
	@Test
	void infixPostfix6() throws ExpressionFormatException {
		InfixExpression ie = new InfixExpression("  ( 2 * i + 3 )");
		ie.postfix();
		assertEquals("(2 * i + 3)", ie.toString(), "infixPostfix6a()");		// Infix
		assertEquals("2 i * 3 +", ie.postfixString(), "infixPostfix6b()");	// Postfix
	}
	
	@Test
	void infixPostfix7() throws ExpressionFormatException {
		InfixExpression ie = new InfixExpression(" - ( 2 * i + - 3 ) * 5");
		ie.postfix();
		assertEquals("- (2 * i + - 3) * 5", ie.toString(), "infixPostfix7a()");		// Infix
		assertEquals("2 i * 3 ~ + ~ 5 *", ie.postfixString(), "infixPostfix7b()");	// Postfix
	}
	
	@Test
	void infixPostfix8() throws ExpressionFormatException {
		InfixExpression ie = new InfixExpression("8 / ( 1 + 3 )");
		ie.postfix();
		assertEquals("8 / (1 + 3)", ie.toString(), "infixPostfix8()");
		assertEquals("8 1 3 + /", ie.postfixString());
	}
	
	@Test
	void infixPostfix9() throws ExpressionFormatException {
		InfixExpression ie = new InfixExpression(" 8 / ( 1 + 3 ) - 6");
		ie.postfix();
		assertEquals("8 / (1 + 3) - 6", ie.toString(), "infixPostfix9()");
		assertEquals("8 1 3 + / 6 -", ie.postfixString());
	}
	
	@Test
	void infixPostfix10() throws ExpressionFormatException {
		InfixExpression ie = new InfixExpression(" 8 / ( 1 + 3 ) - 6 ^ 2");
		ie.postfix();
		assertEquals("8 / (1 + 3) - 6 ^ 2", ie.toString(), "infixPostfix10()");
		assertEquals("8 1 3 + / 6 2 ^ -", ie.postfixString());
	}
	
	@Test
	void infixPostfix11() throws ExpressionFormatException {
		InfixExpression ie = new InfixExpression("( ( ( x + 5 ) ) )");
		ie.postfix();
		assertEquals("(((x + 5)))", ie.toString());
		assertEquals("x 5 +", ie.postfixString());
	}
	
	@Test
	void infixPostfix12() throws ExpressionFormatException {
		InfixExpression ie = new InfixExpression("( ( 2 + 3 ) - 4 )  * 5");
		ie.postfix();
		assertEquals("2 3 + 4 - 5 *", ie.postfixString());
	}
	
	/**
	 * A series of tests with intentionally malformed infix expressions.  
	 * Catch the message of the exception.
	 */
	@Test
	void malformedInfixTest1() {
		InfixExpression ie = new InfixExpression("5 5");
		assertEquals("5 5", ie.toString());
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			ie.postfix();
		});
		
		assertEquals("Operator expected", exception.getMessage());
	}
	
	@Test
	void malformedInfixTest2() {
		InfixExpression ie = new InfixExpression("5 +");
		assertEquals("5 +", ie.toString());
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			ie.postfix();
		});
		
		assertEquals("Operand expected", exception.getMessage());
	}
	
	@Test
	void malformedInfixTest3() {
		// Empty infix expression.  You need at least an operand.
		InfixExpression ie = new InfixExpression(" ");  
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			ie.postfix();
		});
		
		assertEquals("Operand expected", exception.getMessage());
	}
	
	@Test
	void malformedInfixTest4() {
		InfixExpression ie = new InfixExpression("( ( x + 5 )");
			
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			ie.postfix();
		});
		
		assertEquals("Missing ')'", exception.getMessage());
	}
	
	/**
	 * Expects an invalid expression because there should be at least one space between the parentheses and the operand or operator
	 */
	@Test
	void malformedInfixTest5() {
		InfixExpression ie = new InfixExpression("2 + (2 * 1)");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			ie.postfix();
		});
		
		assertEquals("Invalid character", exception.getMessage());
	}
	
	/**
	 * Invalid expression - not lower case variable
	 */
	@Test
	void malformedInfixTest6() {
		InfixExpression ie = new InfixExpression("2 + P");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			ie.postfix();
		});
		
		assertEquals("Invalid character", exception.getMessage());
	}
	
	/**
	 * Invalid expression - 'aa'
	 */
	@Test
	void malformedInfixTest7() {
		InfixExpression ie = new InfixExpression("2 + aa + 5");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			ie.postfix();
		});
		
		assertEquals("Invalid character", exception.getMessage());
	}
	
	/**
	 * Invalid expression - parenthesis not closed
	 */
	@Test
	void malformedInfixTest8() {
		InfixExpression ie = new InfixExpression("2 + ( ( x + 5 )");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			ie.postfix();
		});
		
		assertEquals("Missing ')'", exception.getMessage());
	}
	
	/**
	 * Invalid expression - open parenthesis
	 */
	@Test
	void malformedInfixTest9() {
		InfixExpression ie = new InfixExpression("2 + 2 * 1 )");
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			ie.postfix();
		});
		
		assertEquals("Missing '('", exception.getMessage());
	}
	
	/**
	 * This series of tests is for a call to {@link InfixExpression#postfixString()} without
	 * first calling postfix() - it's called in the body of postfixString().  
	 */
	@Test
	void postfixStringTest1() {
		InfixExpression ie = new InfixExpression("2 + x");
		
		// Without a prior call to postfix(), the postfixString() method calls postfix()
		// and returns the proper expression
		assertEquals("2 x +", ie.postfixString());
	}
	
	
	@Test
	void postfixStringTest2() {
		InfixExpression ie = new InfixExpression("2 + ");  // Malformed infix expression.
		
		ExpressionFormatException exception = assertThrows(ExpressionFormatException.class, () -> {
			ie.postfix();
		});
		
		// Because an error is thrown, the return string from postfixString() is null
		assertEquals(null, ie.postfixString());
		assertEquals("Operand expected", exception.getMessage());
	}
	
	/**
	 * Tests the return string after resetting the infix expression
	 */
	@Test
	void resetExpressionTest() {
		InfixExpression ie = new InfixExpression("3 + 5");
		
		// First check using an expression string with the identifier "I"
		// My code checks for this just in case
		ie.resetInfix(" x   +  8");
		assertEquals("x + 8", ie.toString());
		
		// Second, check the string return without an identifier
		ie.resetInfix("3   + 5");
		assertEquals("3 + 5", ie.toString());
	}
}
