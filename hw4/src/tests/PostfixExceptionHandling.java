package tests;

import edu.iastate.cs228.hw4.ExpressionFormatException;
import edu.iastate.cs228.hw4.InfixExpression;
import edu.iastate.cs228.hw4.PostfixExpression;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

/**
 * PostfixExceptionHandling
 * <p>
 * Usage:
 *
 * @author John Chandara <chandara@iastate.edu>
 * @version 190413
 * @license MIT License (X11 Variant)
 * @category Educational
 */
public class PostfixExceptionHandling {

	HashMap<Character, Integer> m_arrVariables;

	private void RunTest (String strExpression, String strMessage) {
		String strErrorMessage = "<no exception>";

		try {
			new PostfixExpression (strExpression, this.m_arrVariables).evaluate ();
		} catch (Exception exception) {
			strErrorMessage = exception.getMessage ();
		}

		assertTrue ("\n\tCandidate : " + strExpression + "\n\tExpected : \"" + strMessage + "\"\n\tObserved : \"" + strErrorMessage + "\"", strErrorMessage.equals (strMessage));
	}

	@Before
	public void SetupVariables () {

		this.m_arrVariables = new HashMap<> ();

		for (char iLetter = 'a'; iLetter < 'z' + 1; iLetter++) {
			if (iLetter < 'i' || iLetter > 'w')
				m_arrVariables.put (iLetter, iLetter - 'a');
		}
	}

	@Test
	public void testInvalidCharacter () {
		final String[] arrTestCases = {
				"0 585 * 2 ◘ p ~ * - 15 +",
				"2 ~ b ☺ + 3 -",
				"9 15 * 2 E ~ 1 * -",
				"1 E +",
				"2 2 ? 2 + c + 8 +"
		};

		for (String strCase : arrTestCases) {
			RunTest (strCase, "Invalid character");
		}
	}

	@Test
	public void testTooManyOperands () {
		final String[] arrTestCases = {
				"0 2 5 ~ + 3 -",
				"0 585 * 2 4 ~ * - 15 + 9",
				"5 2 ~ ^ 2 * 1 10 *",
				"2 2 ^ 2 + 9 + 8 5 +",
				"1 5 5 ^ 5 * 10 2 + ~ -"
		};

		for (String strCase : arrTestCases) {
			RunTest (strCase, "Too many operands");
		}
	}

	@Test
	public void testTooManyOperators () {
		final String[] arrTestCases = {
				"c + a + 3 -",
				"a ~ b ^ d - +",
				"0 585 * 2 - g ~ * - 15 +",
				"5 2 ~ ^ + 2 * 1 *",
				"a ~ b + ^ b +"
		};

		for (String strCase : arrTestCases) {
			RunTest (strCase, "Too many operators");
		}
	}

	@Test
	public void testDivideByZero () {
		final String[] arrTestCases = {
				"a 0 / 2 5 + 8 + ~ +",
				"8 2 * 0 a / 2 * +",
				"0 0 / ~ ^ 2 * 1 *",
				"b ~ z ^ x + 0 0 / +",
				"b b - b b - /"
		};

		for (String strCase : arrTestCases) {
			RunTest (strCase, "Divide by zero");
		}
	}

	@Test
	public void testZeroPowZero () {
		final String[] arrTestCases = {
				"a b * a ^",
				"a 2 ~ + 3 - 0 0 ^ +",
				"a 0 ^ 2 5 + 8 + ~ +",
				"2 72 + c + 0 0 ^ -",
				"50 a * 2 + 0 0 ^ +"
		};

		for (String strCase : arrTestCases) {
			RunTest (strCase, "0^0");
		}
	}

	@Test
	public void testCombination () {
		RunTest ("j 1 +", "Variable j was not assigned a value");
	}
}
