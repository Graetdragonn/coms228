package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author Brian Bates
 *
 */

public class InfixPostfix
{
	private static int TRIAL = 1;
	private static HashMap<Character, Integer> hashMap;
	private static String expr;      // Expression read from console
	private static int choice;

	/**
	 * Repeatedly evaluates input infix and postfix expressions either from console or from file.  
	 * See the project description for the input description. It constructs a HashMap object for 
	 * each expression and passes it to the created InfixExpression or PostfixExpression object. 
	 *  
	 * @param args
	 **/
	public static void main(String[] args) 
	{
		hashMap  = new HashMap<Character, Integer>();
		expr = "";
		String exprType = "";
		String lastExprType = "";
		
		// Initial program output
		System.out.println("Evaluation of Infix and Postfix Expressions");
		System.out.println("keys: 1 (standard input)  2 (file input)  3 (exit)");
		System.out.println("Enter \"I\" before an infix expression, \"P\" before a postfix expression");
		
		Scanner input = new Scanner(System.in);
		String filename;						// Filename read from console
		
		while(true) {
			// Get the user's choice as to how to run the next trial
			System.out.print("Trial " + TRIAL + ": ");
			choice = input.nextInt();
			input.nextLine();
			
			// 3 - Exit
			if (choice != 1 && choice != 2) {
				System.exit(0);
			} else if (choice == 1) {
				// 1 - Input the expression at the terminal
				// Get the expression
				System.out.print("Expression: ");
				
				// Get the expression from the terminal
				expr = input.nextLine();
				char identifier = expr.charAt(0);
				expr = expr.substring(1, expr.length());
				
				// Determine if the expression is for infix or postfix
				if (identifier == 'I') {
					// Infix expression
					try {
						processInfix();
					} catch (ExpressionFormatException e) {
						System.out.println(e.getMessage());
					} catch (UnassignedVariableException e) {
						System.out.println(e.getMessage());
					}
				} else if (identifier == 'P') {
					// Postfix expression
					try {
						processPostfix();
					} catch (ExpressionFormatException e) {
						System.out.println(e.getMessage());
					} catch (UnassignedVariableException e) {
						System.out.println(e.getMessage());
					}
				} else {
					// Mostly for fat-fingering during testing.  Probably can't hurt to leave it in
					System.out.println("Invalid expression identifier: " + identifier);
//					System.out.println("Exiting");
//					System.exit(0);
				}
				
				System.out.println();
			} else {
				// 2 - Read expression from file
				// Note the choice
				System.out.println("Input from a file");
				System.out.print("Enter file name: ");
				filename = input.next();
				
				System.out.println("");
				
				try {
					// Open the file
					Scanner read = new Scanner(new File(filename));
					
					while (read.hasNextLine()) {
						// Read the next line
						String line = read.nextLine();
						
						// If the line is blank, ignore it and get the next line
						if (line.length() != 0) {
							// Get the type of expression - infix, postfix or variable
							exprType = typeOfExpr(line);
							
							if (exprType == "infix" || exprType == "postfix") {
								// A new Infix or Postfix expression is found.  If there is a previous expression, process it first
								if (expr != "") {
									// Process the previous expression
									if (lastExprType == "infix") {
										// Infix expression
										try {
											processInfix();
										} catch (ExpressionFormatException e) {
											System.out.println(e.getMessage());
										} catch (UnassignedVariableException e) {
											System.out.println(e.getMessage());
										}
									} else {
										// Postfix expression
										try {
											processPostfix();
										} catch (ExpressionFormatException e) {
											System.out.println(e.getMessage());
										} catch (UnassignedVariableException e) {
											System.out.println(e.getMessage());
										}
									}
									System.out.println();
								}
								
								// New expression
								expr = line;
								expr = expr.substring(1, expr.length());
								
								// New hashmap
								hashMap = new HashMap<Character, Integer>();
								
								lastExprType = exprType;
							} else {
								addStringToHashMap(line);
							}
						}
						
					}
					
					// Close the expression Scanner
					read.close();
					
					// Last catch
					if (expr != "") {
						if (lastExprType == "infix") {
							try {
								processInfix();
							} catch (ExpressionFormatException e) {
								System.out.println(e.getMessage());
							} catch (UnassignedVariableException e) {
								System.out.println(e.getMessage());
							}
						} else {
							// Postfix expression
							try {
								processPostfix();
							} catch (ExpressionFormatException e) {
								System.out.println(e.getMessage());
							} catch (UnassignedVariableException e) {
								System.out.println(e.getMessage());
							}
						}
						System.out.println();
					}
				} catch (FileNotFoundException e) {
//					throw new FileNotFoundException("File '" + filename + "' not found.");
					System.out.println("File '" + filename + "' not found.");
					System.out.println();
				}
			} 
			
			TRIAL++;
			
			// Reset
			expr = "";
			lastExprType = "";
			hashMap = new HashMap<Character, Integer>();
		}
	}
	
	// helper methods if needed
	
	/**
	 * Determines the type of expression
	 * @param s
	 * @return
	 */
	private static String typeOfExpr(String s) {
		if (s.charAt(0) == 'I') {
			return "infix";
		} else if (s.charAt(0) == 'P') {
			return "postfix";
		} else {
			return "variable";
		}
	}
	
	
	/**
	 * Process a string and add the key-value pair to the HashMap
	 * @param s the string to parse and add to the hashmap
	 */
	private static void addStringToHashMap(String s) {
		// Remove all white spaces
		s = s.replaceAll("\\s+", "");
		
		// Split the string on the assignment operator
		String[] arr = s.split("=", 2);
		
		if (isVariable(arr[0].charAt(0)) && isInt(arr[1])) {
			hashMap.put(arr[0].charAt(0), Integer.parseInt(arr[1]));
		}
	}
	
	
	/**
	 * Looks through an expression, determining if there are variables present, getting the variables
	 * from the user and assigning them to the hashmap
	 */
	private static void getExpressionVariableValues() {
		// TODO - this works, but it's not elegant.  Why not use the hashmap here?
		
		// Create a new scanner to read from the console
		Scanner n = new Scanner(System.in);
		
		// Holder to check if the line "where" has preceded the request for variables
		// Only printed if at least one variable has been detected
		boolean label = false;
		
		// Holder to check if this variable character already exists
		boolean present = false;
		
		// Create an array list to hold variables we've already asked for
		ArrayList<Character> ignore = new ArrayList<Character>();
		
		// Iterate through each character in the expression
		for (int i = 0; i < expr.length(); i++) {
			char target = expr.charAt(i);
			
			if (isVariable(target)) {
				if (label == false) {
					// One time you print this statement to the console 0 - only if variables are present
					System.out.println("where");
					label = true;
				}
				
				// If the character is a variable, check if it's already been asked for
				for (int j = 0; j < ignore.size(); j++) {
					if (ignore.get(j) == target) {
						present = true;
						break;
					} 
				}
				
				//
				if (present == false) {
					// The variable has not been asked for, so ask for it
					System.out.print(target + " = ");
					
					// Get the value from the console
					int val = n.nextInt();
					n.nextLine();
					
					// Add this to the hash map
					hashMap.put(target, val);
					
					// Add the character to the ignore list
					ignore.add(target);
				}
			}
			
			// Reset present for the next variable (if there is one)
			present = false;
		}
		
	}
	
	
	private static boolean isVariable(char c) {
		return (Character.toString(c).matches("[a-z]"));
	}
	
	private static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Helper method to house processing of an infix expression
	 */
	private static void processInfix() throws ExpressionFormatException, UnassignedVariableException {
		// Infix expression
		InfixExpression ie;
		
//		if (!hashMap.isEmpty() && hashMap != null) {
		if (!hashMap.isEmpty()) {
			ie = new InfixExpression(expr, hashMap);
		} else {
			ie = new InfixExpression(expr);
		}
		
		
		// Call InfixExpression#postfix() to convert infix to postfix
		ie.postfix();
		
		
		// Output both forms
		System.out.println("Infix form: " + ie.toString());
		System.out.println("Postfix form: " + ie.postfixString());    
		
		// Output variables
		if (choice == 1) {
			// Get the variable values
			getExpressionVariableValues();
			
			// Assign the hash map to the infix expression object
			ie.setVarTable(hashMap);
		} else {
			if (!hashMap.isEmpty()) {
				System.out.println("where");
				printHashMap();
			}
		}
		
		// Output value
		System.out.println("Expression value: " + ie.evaluate());
		System.out.println();
	}

	/**
	 * Helper method to house processing of an postfix expression
	 */
	private static void processPostfix() throws ExpressionFormatException, UnassignedVariableException {
		// Postfix expression
		PostfixExpression pe;
		
		if (!hashMap.isEmpty()) {
			pe = new PostfixExpression(expr, hashMap);
		} else {
			pe = new PostfixExpression(expr);
		}
		
		
		// Output both forms
		System.out.println("Postfix form: " + pe.toString());    
		
		// Output variables
		if (choice == 1) {
			// Get the variable values
			getExpressionVariableValues();
			
			// Assign the hash map to the infix expression object
			pe.setVarTable(hashMap);
		} else {
			if (!hashMap.isEmpty()) {
				System.out.println("where");
				printHashMap();
			}
		}
		
		// Output value
		System.out.println("Expression value: " + pe.evaluate());
		System.out.println();
	}
	
	/**
	 * Outputs the key-value pairs of the hashmap
	 */
	private static void printHashMap() {
		for (HashMap.Entry<Character, Integer> entry : hashMap.entrySet()) {
		    System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		
//		for(Character key:hashMap.keySet()){
//	        System.out.println(key);
//	        System.out.println(hashMap.get(key));
//	    }
	}
}
