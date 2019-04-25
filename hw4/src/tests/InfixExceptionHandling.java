package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.iastate.cs228.hw4.ExpressionFormatException;
import edu.iastate.cs228.hw4.InfixExpression;

/**
 * InfixExceptionHandling
 *
 * Usage:
 *
 * @author John Chandara <chandara@iastate.edu>
 * @license MIT License (X11 Variant)
 * @category Educational
 * @version 190411
 *
 */
public class InfixExceptionHandling {

	private void RunTest (String strExpression, String strMessage) {
		String strErrorMessage = "<no exception>";

		try {
			new InfixExpression (strExpression).postfix ();
		} catch (ExpressionFormatException expressionFormatException) {
			strErrorMessage = expressionFormatException.getMessage ();
		}

		assertTrue ("\n\tTest Candidate: " + strExpression + "\n\tExpected Exception: \"" + strMessage + "\"\n\tObserved Exception: \"" + strErrorMessage + "\"", strErrorMessage == strMessage);
	}


	@Test
	public void testInvalidCharacter () {
		final String[] arrTestCases = {
				"0 * 585 - 2 * - P + 15",
				"- 2 + B # - 3",
				"A ^ A * A - - ( B + B )",
				"2 ^ 2 + 2 + c + 8'",
				"1 + e."
		};


		for (String strCase : arrTestCases) {
			RunTest (strCase, "Invalid character");
		}
	}

	@Test
	public void testOperatorExpected () {
		final String[] arrTestCases = {
				"0 * 585 - 2 * - p 15",
				"- 2 b - 3",
				"a ^ a * a - - ( b b )",
				"2 2 + 2 + c 8",
				"1 e"
		};

		for (String strCase : arrTestCases) {
			RunTest (strCase, "Operator expected");
		}
	}

	@Test
	public void testOperandExpected () {
		final String[] arrTestCases = {
				"50 * / 2", "2 + ( * 1 )",
				"a + 2 -",
				"* 15 - - 2 * 1",
				"5 ^ - 2 * * 1"
		};

		for (String strCase : arrTestCases) {
			RunTest (strCase, "Operand expected");
		}
	}

	/*
	 * "2 + (2 * 1)", "(d + 1 * 5) * (5 + 2) / 2", "l + 2 - (3 * 2)",
	 * "a ^ a * a - - (b + b)", "e ^ c + - (2 + 5 + 8)",
	 */

	@Test
	public void testParenthesisOpen () {
		final String[] arrTestCases = {
				"2 + 2 * 1 )",
				"( d + 1 * 5 ) * 5 + 2 ) / 2",
				"l + 2 - 3 * 2 )",
				"a ^ a ) * a - - ( b + b ) ",
				"e ^ c + - ( 2 ) + 5 + 8 )",
		};

		for (String strCase : arrTestCases) {
			RunTest (strCase, "Missing '('");
		}
	}

	@Test
	public void testParenthesisClosed () {
		final String[] arrTestCases = {
				"2 + ( 2 * 1",
				"( d + 1 * 5 * ( 5 + 2 ) / 2",
				"l + ( 2 - ( 3 * 2 ",
				"a ^ a * a - - ( b + b",
				"e ^ c + - ( 2 + 5 + 8",
		};

		for (String strCase : arrTestCases) {
			RunTest (strCase, "Missing ')'");
		}
	}

}
