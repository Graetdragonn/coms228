package edu.iastate.cs228.hw4;

import java.util.HashMap;

/**
 * Code provided in the project description.  Not actually part of the project.
 * 
 * @author Brian Bates
 *
 */
public class HashMapDemo {

	public static void main(String[] args) {

		// Create a new hashmap: keys are characters, data is integers
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		
		// Add some elements
		hashMap.put('x', 4);
		hashMap.put('y', 2);
		hashMap.put('z', 5);
		
		// Look for certain key and get the value back
		char c = 'x';
		if (hashMap.containsKey(c)) {
			int x = (int) hashMap.get(c);
			System.out.println(x);
		}
		
		
	}

}
