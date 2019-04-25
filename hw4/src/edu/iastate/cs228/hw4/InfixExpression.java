package edu.iastate.cs228.hw4;

/**
 *  
 * @author Brian Bates
 *
 */

import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * This class represents an infix expression. It implements infix to postfix conversion using 
 * one stack, and evaluates the converted postfix expression.    
 *
 */

public class InfixExpression extends Expression 
{
	private String infixExpression;   				// the infix expression to convert	
	private String postfixExpression;				// the postfix version of the infix expression
	private boolean postfixReady = false;   		// postfix already generated if true
	private int rankTotal = 0;						// Keeps track of the cumulative rank of the infix expression.
	private PureStack<Operator> operatorStack; 	  	// stack of operators 
	
	/**
	 * Constructor supplies a default hash map. 
	 * 
	 * @param s
	 */
	public InfixExpression (String s)
	{
		// Parse the incoming string for the infix expression
		infixExpression = removeExtraSpaces(s);

		// Initialize the operator stack
		operatorStack = new ArrayBasedStack<Operator>();
	}
		
	
	/**
	 * Constructor stores the input infix string, and initializes the operator stack and 
	 * the hash map.
	 * 
	 * @param st  input infix string. 
	 * @param varTbl  hash map storing all variables in the infix expression and their values. 
	 */
	public InfixExpression (String st, HashMap<Character, Integer> varTbl)
	{
		// Call the other constructor to store the string and initialize the operator stack
		this(st);
		
		// Initialize the hash map
		this.setVarTable(varTbl);
	}
	
	/**
	 * Outputs the infix expression according to the format in the project description.
	 * The key here - there should be no space between a parenthesis and an operand.
	 */
	@Override
	public String toString()
	{
		// Adjust the spacing for parenthesis
		infixExpression = infixExpression.replaceAll("\\(\\s+", "\\(");
		infixExpression = infixExpression.replaceAll("\\s+\\)", "\\)");
		
		return infixExpression; 
	}
	
	
	/** 
	 * Why is this even here - it's not stored as part of infix
	 * @return equivalent postfix expression, or a null string if a call to postfix() inside the body (when postfixReady == false) throws an exception.  
	 */
	public String postfixString() 
	{
		if (postfixReady == false) {
			try {
				postfix();
			} catch (Exception e) {
				return null;
			}
		}
		
		return postfixExpression;
	}


	/**
	 * Resets the infix expression. 
	 * 
	 * @param st
	 */
	public void resetInfix (String st)
	{
		// Clean up the string and store
		infixExpression = removeExtraSpaces(st);
		
		// Reset postfix
		postfixReady = false;
	}


	/**
	 * Converts infix expression to an equivalent postfix string stored at postfixExpression.
	 * If postfixReady == false, the method scans the infixExpression, and does the following
	 * (for algorithm details refer to the relevant PowerPoint slides): 
	 * 
	 *     1. Skips a whitespace character.
	 *     2. Writes a scanned operand to postfixExpression. 
	 *     3. When an operator is scanned, generates an operator object.  In case the operator is 
	 *        determined to be a unary minus, store the char '~' in the generated operator object.
	 *     4. If the scanned operator has a higher input precedence than the stack precedence of 
	 *        the top operator on the operatorStack, push it onto the stack.   
	 *     5. Otherwise, first calls outputHigherOrEqual() before pushing the scanned operator 
	 *        onto the stack. No push if the scanned operator is ). 
     *     6. Keeps track of the cumulative rank of the infix expression. 
     *     
     *  During the conversion, catches errors in the infixExpression by throwing 
     *  ExpressionFormatException with one of the following messages:
     *  
     *      -- "Operator expected" if the cumulative rank goes above 1;  --Done
     *      -- "Operand expected" if the rank goes below 0;  -- Done
     *      -- "Missing '('" if scanning a �)� results in popping the stack empty with no '(';
     *      -- "Missing ')'" if a '(' is left unmatched on the stack at the end of the scan;  -- Done 
     *      -- "Invalid character" if a scanned char is neither a digit nor an operator; 
     *   
     *  If an error is not one of the above types, throw the exception with a message you define.
     *      
     *  Sets postfixReady to true.  
	 */
	public void postfix() throws ExpressionFormatException
	{
		if (postfixReady == false) {
			// Scanner to process the expression string
			Scanner r = new Scanner(infixExpression);
			 
			// Initialize postfixExpression
			postfixExpression = new String();
			
			// Holds the last String object returned from the Scanner for comparison
			String last = " ";
			
			// Reset (if necessary) rankTotal
			rankTotal = 0;
			
			// Scan the infixExpression string, skipping a white space
			while (r.hasNext()) {
				// Get the next
				String s = r.next();
				
				if (isVariable(s.charAt(0)) && s.length() == 1) {
					// It's a variable
					postfixExpression = postfixExpression + " " + s;
					
					// Update the rank
					rankTotal++;
				} else if (isInt(s)) {
					// It's an integer
					postfixExpression = postfixExpression + " " +  s;
					
					// Update the rank
					rankTotal++;
				} else if (isOperator(s.charAt(0)) && s.length() == 1) {
					// It's an operator
					Operator o = new Operator(s.charAt(0));
					
					// Unary operator 
					if ((s.equals("-") && last.equals(" ")) ||  								// Condition 1 - first in an expression
						(s.equals("-") && isOperator(last.charAt(0)) && !last.equals(")")) ||	// Condition 2 - comes after another operator
						(s.equals("-") && last.equals("("))) {									// Condition 3 - comes after a left parenthesis
						
						// Create the operator passing in tilde
						o = new Operator('~');
						// Rank is not updated						
					} else if (s.equals("-") || s.equals("+")) {
						// Binary operators

						// Update rank
						rankTotal--;
					} else if (s.equals("*") || s.equals("/") || s.equals("%")) {
						// Update rank
						rankTotal--;
					} else if (s.equals("^")) {
						rankTotal--;
					} else if (s.equals("(")) {
						
					} else {
						
					}
					
					// Update stack
					updateStack(o);
				} else {
					throw new ExpressionFormatException("Invalid character");
				}
				
				// Assign the current String object to last for comparison in the next iteration (if there is one).  This is Unary operator
				// coming after another operator.
				last = s;
				
				// Check the rank and throw an error if necessary
				if (rankTotal < 0) throw new ExpressionFormatException("Operand expected");
				if (rankTotal > 1) throw new ExpressionFormatException("Operator expected");
			}
			
			// Close the scanner
			r.close();
			
			// Pop everything left on the stack.  
			popRemainingStack();
			
			// The final rank should always equal 1.  If it doesn't, the infix expression was invalid.
			// The case of rankTotal < 0 and rankTotal > 1 are caught above
			if (rankTotal == 0) {
				throw new ExpressionFormatException("Operand expected");
			}
			
			// Lastly, remove initial empty space if present
			if (postfixExpression.charAt(0) == ' ') {
				postfixExpression = postfixExpression.substring(1);
			}
			
			// Set the call to grab the postfix to ready
			postfixReady = true;
		}
	}
	
	
	/**
	 * This function first calls postfix() to convert infixExpression into postfixExpression. Then 
	 * it creates a PostfixExpression object and calls its evaluate() method (which may throw  
	 * an exception).  It also passes any exception thrown by the evaluate() method of the 
	 * PostfixExpression object upward the chain. 
	 * 
	 * @return value of the infix expression 
	 * @throws ExpressionFormatException, UnassignedVariableException
	 */
	public int evaluate() throws ExpressionFormatException, UnassignedVariableException
    {
    	// First call postfix() to convert infixExpression into a postfixExpression
		if (postfixReady == false) {
			postfix();
		}
		
		// Create a PostfixExpression object
		PostfixExpression pe = new PostfixExpression(postfixExpression, varTable);
		
		// Calls the PostfixExpression's evaluate() method (which may throw an exception)
		return pe.evaluate();
    }


	/**
	 * Pops the operator stack and output as long as the operator on the top of the stack has a 
	 * stack precedence greater than or equal to the input precedence of the current operator op.  
	 * Writes the popped operators to the string postfixExpression.  
	 * 
	 * If op is a ')', and the top of the stack is a '(', also pops '(' from the stack but does 
	 * not write it to postfixExpression. 
	 * 
	 * @param op  current operator
	 * @throws ExpressionFormatException with the following message:
	 * 			--"Missing '('" if op is a ')' and matching '(' is not found on stack
	 */
	private void outputHigherOrEqual(Operator op) throws ExpressionFormatException
	{
		if (op.getOp() == ')') {
			boolean match = false;
			
			while(!operatorStack.isEmpty()) {
				Operator o = operatorStack.pop();
				
				if (o.getOp() != '(') {
					match = false;
					
					postfixExpression = postfixExpression + " " + Character.toString(o.getOp());
				} else {
					match = true;
					break;
				}
			}
			
			if (match == false) {
				throw new ExpressionFormatException("Missing '('");
			}
		} else {
			// While the stack is not empty
			while(!operatorStack.isEmpty()) {
				// The top object on the stack has greater or equal precedence, pop it off
				if (operatorStack.peek().compareTo(op) >= 0) {				
					Operator o = operatorStack.pop();
					
					// Update the postfix expression
					if (o.getOp() != '(' && o.getOp() != ')') {
						postfixExpression = postfixExpression + " " + Character.toString(o.getOp());
					}
				} else {
					break;
				}
			}
		}
	}
	
	// other helper methods if needed
	
	
	/**
	 * Pop the remaining objects off the stack at the end of analyzing an expression 
	 * @throws ExpressionFormatException
	 */
	private void popRemainingStack() throws ExpressionFormatException {
		while(!operatorStack.isEmpty()) {
			char c = operatorStack.pop().getOp();
			
			if (c == '(') {
				// Throw this because this should have been removed before this point with the closing ')'.  Since it wasn't
				// the expression is incorrectly formatted.
				throw new ExpressionFormatException("Missing ')'");
			} else {
				postfixExpression = postfixExpression + " " + Character.toString(c);
			}
		}
	}
	
	/**
	 * Update the operator stack with the passed operator
	 * @param o the operator
	 * @throws ExpressionFormatException
	 */
	private void updateStack(Operator o) throws ExpressionFormatException {
//		if (o.getOp() != ')') {
//			if (!operatorStack.isEmpty()) {
//				// The stack is not empty.  Compare the top operator on the stack to this operator
//				if (operatorStack.peek().compareTo(o) >= 0) {
//					// The operator on the stack has a higher or equal precedence to the the operator passed in
//					// Pop the top operator off the stack
//					Operator op = operatorStack.pop();
//					
//					// Add the character of this operator to the postfixExpression
//					postfixExpression = postfixExpression + " " + op.getOp();
//					
//					// Push the passed
//					operatorStack.push(o);
//				} else {
//					// Otherwise call the outputHigherOrEqual() method and THEN add it to the stack
//					outputHigherOrEqual(o);
//					operatorStack.push(o);
//				}							
//			} else {
//				// The stack is empty, add the operator to the stack
//				operatorStack.push(o);
//			}
//		} else {
//			// Pop everything off the stack until you come across the matching '('
//			outputHigherOrEqual(o);
//		}
		if (o.getOp() != ')') {
			if (!operatorStack.isEmpty()) {
				// The stack is not empty.  Compare the top operator on the stack to this operator
				if (operatorStack.peek().compareTo(o) >= 0) {
					// The operator on the stack has a higher or equal precedence to the the operator passed in
					// Pop the top operator off the stack
					outputHigherOrEqual(o);
					
					// Push the passed
					operatorStack.push(o);
				} else {
					// Otherwise call the outputHigherOrEqual() method and THEN add it to the stack					
					operatorStack.push(o);
				}							
			} else {
				// The stack is empty, add the operator to the stack
				operatorStack.push(o);
			}
		} else {
			// Pop everything off the stack until you come across the matching '('
			outputHigherOrEqual(o);
		}
	}
}
