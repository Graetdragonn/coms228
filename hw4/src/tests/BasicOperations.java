package tests;

import org.junit.Assert;
import org.junit.Test;

import edu.iastate.cs228.hw4.InfixExpression;

/**
 * BasicOperations
 * <p>
 * Usage:
 *
 * @author John Chandara <chandara@iastate.edu>
 * @version 190413
 * @license MIT License (X11 Variant)
 * @category Educational
 */
public class BasicOperations {

	private static String CollectPostFix (String strExpression) throws Exception {
		final InfixExpression infixEx = new InfixExpression (strExpression);
		infixEx.postfix ();

		return infixEx.postfixString ();
	}

	@Test
	public void Addition () throws Exception {
		Assert.assertEquals ("1 e +", CollectPostFix ("1 + e"));
		Assert.assertEquals ("2 72 + c +", CollectPostFix ("2 + 72 + c"));
		Assert.assertEquals ("c a + 3 -", CollectPostFix ("c + a - 3"));
		Assert.assertEquals ("2 ~ b + 3 -", CollectPostFix ("- 2 + b - 3"));
		Assert.assertEquals ("a 2 ~ + 3 -", CollectPostFix ("a + - 2 - 3"));
	}

	@Test
	public void Multiplication () throws Exception {
		Assert.assertEquals ("1 18 *", CollectPostFix ("1 * 18"));
		Assert.assertEquals ("50 a * 2 /", CollectPostFix ("50 * a / 2"));
		Assert.assertEquals ("8 2 * 19 u / 2 * +", CollectPostFix ("8 * 2 + 19 / u * 2"));
		Assert.assertEquals ("9 15 * 2 ~ 1 * -", CollectPostFix ("9 * 15 - - 2 * 1"));
		Assert.assertEquals ("0 585 * 2 p ~ * - 15 +", CollectPostFix ("0 * 585 - 2 * - p + 15"));
	}

	@Test
	public void Exponentiation () throws Exception {
		Assert.assertEquals ("a ~ j ^ p +", CollectPostFix ("- a ^ j + p"));
		Assert.assertEquals ("5 2 ~ ^ 2 * 1 *", CollectPostFix ("5 ^ - 2 * 2 * 1"));
		Assert.assertEquals ("2 ~ s ^ f +", CollectPostFix ("- 2 ^ s + f"));
		Assert.assertEquals ("5 c ^ d * 1 ~ -", CollectPostFix ("5 ^ c * d - - 1"));
		Assert.assertEquals ("2 2 ^ 2 + c + 8 +", CollectPostFix ("2 ^ 2 + 2 + c + 8"));
	}

	@Test
	public void Parenthesis () throws Exception {
		Assert.assertEquals ("2 2 1 * +", CollectPostFix ("2 + ( 2 * 1 )"));
		Assert.assertEquals ("d 1 5 * + 5 2 + * 2 /", CollectPostFix ("( d + 1 * 5 ) * ( 5 + 2 ) / 2"));
		Assert.assertEquals ("l 2 + 3 2 * -", CollectPostFix ("l + 2 - ( 3 * 2 )"));
		Assert.assertEquals ("a a ^ a * b b + ~ -", CollectPostFix ("a ^ a * a - - ( b + b )"));
		Assert.assertEquals ("e c ^ 2 5 + 8 + ~ +", CollectPostFix ("e ^ c + - ( 2 + 5 + 8 )"));
	}

}
