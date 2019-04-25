package edu.iastate.cs228.hw4;

import java.util.HashMap;

/**
 * The super class that InfixExpression and PostfixExpression extend
 * @author Brian Bates
 *
 */

public abstract class Expression 
{
	protected String postfixExpression; 		
	protected HashMap<Character, Integer> varTable; // hash map to store variables in the 

	
	protected Expression()
	{
		// no implementation needed 
		// removable when you are done
	}
	
	/**
	 * Initialization with a default hash map.
	 * 
	 * @param st
	 */
	protected Expression(String st) 
	{
		// Store the expression string
		postfixExpression = st;
		
		// Initialize the default hash map
		varTable = new HashMap<Character, Integer>();
	}
	
	/**
	 * Initialization with a provided hash map. 
	 * 
	 * @param varTbl
	 */
	protected Expression(String st, HashMap<Character, Integer> varTbl)
	{
		// Call the other constructor to minimize validation
		this(st);
		
		// TODO - test
		setVarTable(varTbl);
	}
	
	
	
	/**
	 * Setter for instance variable varTable.
	 * @param varTbl
	 */
	public void setVarTable(HashMap<Character, Integer> varTbl) 
	{
		if (varTable != null) {			// Table exists
			if (!varTable.isEmpty()) {  // Table is not empty
				varTable.clear();		// Clear it
			}
		}
		
		// Put the variables
		if (varTbl != null) {
			if (!varTbl.isEmpty()) { 
				varTable = new HashMap<Character, Integer>();
				varTable.putAll(varTbl);
			}
		}
	}
	
	
	/**
	 * Evaluates the infix or postfix expression. 
	 * 
	 * @return value of the expression 
	 * @throws ExpressionFormatException, UnassignedVariableException
	 */
	public abstract int evaluate() throws ExpressionFormatException, UnassignedVariableException;  

	
	
	// --------------------------------------------------------
	// Helper methods for InfixExpression and PostfixExpression 
	// --------------------------------------------------------

	/** 
	 * Checks if a string represents an integer.  You may call the static method 
	 * Integer.parseInt(). 
	 * 
	 * @param s
	 * @return
	 */
	protected static boolean isInt(String s) 
	{
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	/**
	 * Checks if a char represents an operator, i.e., one of '~', '+', '-', '*', '/', '%', '^', '(', ')'. 
	 * 
	 * @param c - the character to check if it is an operator
	 * @return true if the argument c is an operator, false otherwise
	 */
	protected static boolean isOperator(char c) 
	{
		// Create array of characters
		char[] operators = {'~', '+', '-', '*', '/', '%', '^', '(', ')'};
		
		// Iterator to see if c is in the array and return true if it is
		for (int i = 0; i < operators.length; i++) {
			if (c == operators[i]) {
				return true;
			}
		}
		
		return false; 
	}

	
	/** 
	 * Checks if a char is a variable, i.e., a lower case English letter. 
	 * 
	 * @param c a character to test
	 * @return true if the argument is a lower case English letter, false otherwise
	 */
	protected static boolean isVariable(char c) 
	{
		// Convert the character to String and then check if it is a lower case English letter
		return (Character.toString(c).matches("[a-z]"));				
	}
	
	
	/**
	 * Removes extra blank spaces in a string. 
	 * @param s a string containing the expression
	 * @return a string where only one white space separates each character
	 */
	protected static String removeExtraSpaces(String s) 
	{
		// Find all instances of tabs and replace them with a single white space
		s = s.replaceAll("\\t+", " ");
		
		// Find all instances of double white spaces and replace them with single white spaces
		s = s.replaceAll("\\s+", " ");
		
		// If there is a space at the beginning, remove it
		s = s.replaceAll("^\\s", "");   
		
		// If there is a space at the end, remove it
		s = s.replaceAll("\\s\\n", ""); // This is for manually entered cases
		s = s.replaceAll("\\s$", "");   // This is for the automated test cases where this is no new-line character
		
		return(s);
	}

	/**
	 * Made public for testing purposes
	 * @return the hash map stored
	 */
	protected HashMap<Character, Integer> getMap() {
		return varTable;
	}
	
	
	
	/**
	 * Checks that the hash map contains the key.  This method is used before a call to getValFromMap().
	 * @param c the key to find in the hash map
	 * @return true if the key is present in the hash map, false otherwise
	 */
	protected boolean mapContains(char c) {
		// TODO - test
		if (varTable.containsKey(c)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the value of a key in the hash map.  Requires a call to mapContains(char) before calling because
	 * it assumes the character is already present (same idea as Scanner.hasNext() before Scanner.next())
	 * @param c the key in the hash map
	 * @return the value of the key
	 */
	protected int getMapValue(char c) {
		// TODO - test
		return (int) varTable.get(c);
	}
}
