package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw5.SplayTree;

class IntegerTest {

	/**
	 * This test involves the SplayTree using Integers as displayed in the handout.
	 * SplayTree is supposed to be generic, so it should work on this data just as easily.
	 * Note, addBST is used here to ignore splaying.
	 */
	@Test
	void stringTest1() {
		SplayTree<Integer> st = new SplayTree<Integer>(50);
		st.addBST(30);
		st.addBST(55);
		st.addBST(25);
		st.addBST(35);
		st.addBST(53);
		st.addBST(60);
		st.addBST(10);
		st.addBST(31);
		st.addBST(37);
		st.addBST(62);
		st.addBST(20);
		
		// Output SplayTree.
		String actual = st.toString();
		
		// Visual output - optional
//		System.out.println(actual);
		
		String expected = "50\n" +
		                  "    30\n" + 
		                  "        25\n" + 
		                  "            10\n" + 
		                  "                null\n" +
		                  "                20\n" + 
		                  "            null\n" +
		                  "        35\n" +
		                  "            31\n" +
		                  "            37\n" +
		                  "    55\n" + 
		                  "        53\n" + 
		                  "        60\n" +
		                  "            null\n" + 
		                  "            62\n";
		                  
		assertEquals(expected, actual);
		
	}
	
	/**
	 * Repeat, but from a file
	 */
	@Test
	void stringTest2() throws FileNotFoundException {
		SplayTree<Integer> st = new SplayTree<Integer>();
		Scanner line = new Scanner(new File("integers.txt"));
		
		while (line.hasNextLine()) {
			int c = line.nextInt();
			
			if (!line.equals("\\n") && !line.equals("") && !line.equals("\\s+")) {
				st.addBST(c);
			}
		}
		
		// Output SplayTree.
		String actual = st.toString();
		
		// Visual output - optional
		System.out.println(actual);
		
		String expected = "50\n" +
		                  "    30\n" + 
		                  "        25\n" + 
		                  "            10\n" + 
		                  "                null\n" +
		                  "                20\n" + 
		                  "            null\n" +
		                  "        35\n" +
		                  "            31\n" +
		                  "            37\n" +
		                  "    55\n" + 
		                  "        53\n" + 
		                  "        60\n" +
		                  "            null\n" + 
		                  "            62\n";
		                  
		assertEquals(expected, actual);
	}
	
	/**
	 * Repeat, but from a file
	 */
	@Test
	void stringTest3() throws FileNotFoundException {
		SplayTree<Integer> st = new SplayTree<Integer>();
		Scanner line = new Scanner(new File("small_ints.txt"));
		
		while (line.hasNextLine()) {
			int c = line.nextInt();
			
			if (!line.equals("\\n") && !line.equals("") && !line.equals("\\s+")) {
				st.addBST(c);
			}
		}
		
		// Output SplayTree.
		String actual = st.toString();
		
		// Visual output - optional
//		System.out.println(actual);
		
		String expected = "50\n" +
		                  "    30\n" +
		                  "        null\n" +
		                  "        40\n" +
		                  "    null\n";
		                  
		assertEquals(expected, actual);
	}
}
