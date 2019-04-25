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
 * BasicEvaluation
 * <p>
 * Usage:
 *
 * @author John Chandara <chandara@iastate.edu>
 * @version 190413
 * @license MIT License (X11 Variant)
 * @category Educational
 */
public class BasicEvaluation {
	private static int CollectResults (String strExpression) throws Exception {
		InfixExpression expression = new InfixExpression (strExpression);
		expression.postfix ();
		return new PostfixExpression (expression.postfixString ()).evaluate ();
	}

	@Test
	public void Addition () throws Exception {
		assertEquals (2, CollectResults ("1 + 1"));
		assertEquals (76, CollectResults ("2 + 72 + 2"));
		assertEquals (12, CollectResults ("5 + 10 - 3"));
		assertEquals (0, CollectResults ("- 2 + 5 - 3"));
		assertEquals (0, CollectResults ("5 + - 2 - 3"));
	}

	@Test
	public void Multiplication () throws Exception {
		assertEquals (2, CollectResults ("1 * 2"));
		assertEquals (25, CollectResults ("50 * 1 / 2"));
		assertEquals (34, CollectResults ("8 * 2 + 19 / 2 * 2"));
		assertEquals (137, CollectResults ("9 * 15 - - 2 * 1"));
		assertEquals (65, CollectResults ("0 * 585 - 2 * - 25 + 15"));
	}

	@Test
	public void Exponentiation () throws Exception {
		assertEquals (25, CollectResults ("5 ^ 2"));
		assertEquals (50, CollectResults ("5 ^ 2 * 2 * 1"));
		assertEquals (-22, CollectResults ("- 2 ^ 5 + 10"));
		assertEquals (126, CollectResults ("5 ^ 2 * 5 - - 1"));
		assertEquals (22, CollectResults ("2 ^ 2 + 2 + 8 + 8"));
	}

//	@Test
//	public void Parenthesis () throws Exception {
//		assertEquals (4, CollectResults ("2 + (2 * 1)"));
//		assertEquals (35, CollectResults ("(5 + 1 * 5) * (5 + 2) / 2"));
//		assertEquals (-2, CollectResults ("2 + 2 - (3 * 2)"));
//		assertEquals (5, CollectResults ("1 ^ 1 * 1 - - (2 + 2)"));
//		assertEquals (-14, CollectResults ("1 ^ 8 + - (2 + 5 + 8)"));
//	}
}
