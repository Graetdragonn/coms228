package edu.iastate.cs228.hw4;

/**
 *  
 * @author Brian Bates
 *
 */

/**
 * 
 * This class evaluates a postfix expression using one stack.    
 *
 */

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner; 

public class PostfixExpression extends Expression 
{
	private int leftOperand;            // left operand for the current evaluation step             
	private int rightOperand;           // right operand (or the only operand in the case of 
	                                    // a unary minus) for the current evaluation step	

	private PureStack<Integer> operandStack;  // stack of operands
	

	/**
	 * Constructor supplies a default hash map. 
	 * 
	 * @param s
	 */
	public PostfixExpression (String s)
	{
//		// Store the string
//		this.postfixExpression = getExpr(s);
		super(removeExtraSpaces(s));
		
		// Initialize the operands
		leftOperand = 0;
		rightOperand = 0;
		
		
		// Initialize the operand stack
		operandStack = new ArrayBasedStack<Integer>();
	}
	
	/**
	 * Constructor stores the input postfix string and initializes the operand stack.
	 * 
	 * @param st      input postfix string. 
	 * @param varTbl  hash map that stores variables from the postfix string and their values.
	 */
	public PostfixExpression (String st, HashMap<Character, Integer> varTbl)
	{
		// Stores the string and initializes the operand stack
		this(st);
		
		// Store the hash map
		this.setVarTable(varTbl);
	}
	
	
	

	
	/**
	 * Outputs the postfix expression according to the format in the project description.
	 */
	@Override 
	public String toString()
	{
		return postfixExpression; 
	}
	

	/**
	 * Resets the postfix expression. 
	 * @param st
	 */
	public void resetPostfix (String st)
	{
		postfixExpression = removeExtraSpaces(st); 
	}


	/**
     * Scan the postfixExpression and carry out the following:  
     * 
     *    1. Whenever an integer is encountered, push it onto operandStack.
     *    2. Whenever a binary (unary) operator is encountered, invoke it on the two (one) elements popped from  
     *       operandStack,  and push the result back onto the stack.  
     *    3. On encountering a character that is not a digit, an operator, or a blank space, stop 
     *       the evaluation. 
     *       
     * @return value of the postfix expression 
     * @throws ExpressionFormatException with one of the messages below: 
     *  
     *           -- "Invalid character" if encountering a character that is not a digit, an operator
     *              or a whitespace (blank, tab); 
     *           --	"Too many operands" if operandStack is non-empty at the end of evaluation; 
     *           -- "Too many operators" if getOperands() throws NoSuchElementException; 
     *           -- "Divide by zero" if division or modulo is the current operation and rightOperand == 0;
     *           -- "0^0" if the current operation is "^" and leftOperand == 0 and rightOperand == 0;
     *           -- self-defined message if the error is not one of the above.
     *           
     *         UnassignedVariableException if the operand is a variable does not have a value stored
     *            in the hash map.  In this case, the exception is thrown with the message
     *            
     *           -- "Variable <name> was not assigned a value", where <name> is the name of the variable.  
     *           
     */
	public int evaluate() throws ExpressionFormatException, UnassignedVariableException
    {
		Scanner in = new Scanner(postfixExpression);
		int value = 0;
		
		// While there are more strings in the expression
		while (in.hasNext()) {
			// Get the next string
			String s = in.next();
			
			// First catch that s represents a valid character
			if (!isInt(s) && !isOperator(s.charAt(0)) && !isVariable(s.charAt(0))) {
				throw new ExpressionFormatException("Invalid character");
			}
			
			if (isInt(s)) {
				// An integer
				// Push the integer onto the operand stack
				operandStack.push(Integer.parseInt(s));
			} else if (isVariable(s.charAt(0))) {
				// A variable
				char c = s.charAt(0);
				// Get the value of this variable from the hash map and add it to the operand stack
				if (mapContains(c)) {
					// Get the map value and add it to the operand stack
					operandStack.push(getMapValue(c));
				} else {
					// The variable doesn't exist
					throw new UnassignedVariableException("Variable " + s + " was not assigned a value");
				}
			} else {
				// An operator
				char c = s.charAt(0);				
				
				// Pop two items off the stack: "right" and "left"
				try {
					getOperands(c);
				} catch (NoSuchElementException e) {
					throw new ExpressionFormatException("Too many operators");
				}
				
				// Evaluate the expression and push the result back on the stack
				operandStack.push(compute(c));
			}
		}
		
		// Close the Scanner
		in.close();
		
		// TODO - Evaluate the elements remaining on the stack for possible Exception violations
		
		int finalValue = operandStack.pop();
		
		if (!operandStack.isEmpty()) {
			// If the stack is not empty after after popping the final value of the stack, there to were too many operands
			throw new ExpressionFormatException("Too many operands");
		}
		
		
		return finalValue;
    }
	

	/**
	 * For unary operator, pops the right operand from operandStack, and assign it to rightOperand. The stack must have at least
	 * one entry. Otherwise, throws NoSuchElementException.
	 * For binary operator, pops the right and left operands from operandStack, and assign them to rightOperand and leftOperand, respectively. The stack must have at least
	 * two entries. Otherwise, throws NoSuchElementException.
	 * @param op
	 * 			char operator for checking if it is binary or unary operator.
	 */
	private void getOperands(char op) throws NoSuchElementException 
	{
		// TODO - test
		if (op == '~') {
			// Unary operator
			// Check the operand stack has at least one entry
			if (operandStack.peek() != null) {
				rightOperand = operandStack.pop();
			} else {
				throw new NoSuchElementException();
			}
		} else if (op == '+' || op == '-' || op == '*' || op == '/' || op == '%' || op == '^') {
			// Binary operator
			if (operandStack.peek() != null) {
				rightOperand = operandStack.pop();
				
				// Binary operator requires two operands on the stack
				if (operandStack.peek() != null) {
					leftOperand = operandStack.pop();
				} else {
					throw new NoSuchElementException();
				}
			} else {
				throw new NoSuchElementException();
			}
		}
	}


	/**
	 * Computes "leftOperand op rightOprand" or "op rightOprand" if a unary operator. 
	 * 
	 * @param op operator that acts on leftOperand and rightOperand. 
	 * @return
	 * @throws ExpressionFormatException with one of the messages below: 
	 * 			--"Divide by zero" if division is the current operation and rightOperand == 0;
	 * 			--"0^0" if the current operation is "^" and leftOperand == 0 and rightOperand == 0;
	 */
	private int compute(char op) throws ExpressionFormatException
	{
		if (op == '~') {
			return rightOperand * -1;
		} 
		
		if (op == '/' || op == '%') {
			if (rightOperand == 0) {
				throw new ExpressionFormatException("Divide by zero");  
			} else {
				if (op == '/') {
					return leftOperand / rightOperand;
				} else {
					return leftOperand % rightOperand;
				}
			}
		}
		
		if (op == '^') {
			if (leftOperand == 0 && rightOperand == 0) {
				throw new ExpressionFormatException("0^0");
			} else {
				return (int) Math.pow(leftOperand, rightOperand);
			}
		}
		
		if (op == '+') {
			return leftOperand + rightOperand;
		}
		
		if (op == '-') {
			return leftOperand - rightOperand;
		}

		// Last case: op == '*'
		return leftOperand * rightOperand;
	}
}
