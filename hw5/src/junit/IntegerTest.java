package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw5.SplayTree;

class IntegerTest {

	/**
	 * Empty
	 */
	@Test
	void addTest1() {
		SplayTree<Integer> st = new SplayTree<Integer>();
		assertEquals(0, st.size());
	}
	
	@Test
	void addTest2() {
		SplayTree<Integer> st = new SplayTree<Integer>(30);
		assertEquals(1, st.size());
		assertEquals(new Integer(30), st.getRoot());
	}
	
	@Test
	void addTest3() {
		SplayTree<Integer> st = new SplayTree<Integer>(50);
		assertEquals((Integer) 50, st.getRoot());
		assertTrue(st.add(30));
		assertTrue(st.add(60));
		assertEquals(3, st.size());
		assertEquals((Integer) 60, st.getRoot());
	}
	
	@Test
	void addTest4() {
		SplayTree<Integer> st = new SplayTree<Integer>(50);
		st.add(50);
		st.add(30);
		st.add(60);
		st.add(70);
		st.add(55);
		st.add(52);
		st.add(54);
		st.add(40);
		st.add(20);
		st.add(35);
		
		String expected = "35"
			    		+ "\n    20"
			    		+ "\n        null"
			    		+ "\n        30"
			    		+ "\n    40"
			    		+ "\n        null"
			    		+ "\n        52"
			    		+ "\n            50"
			    		+ "\n            54"
			    		+ "\n                null"
			    		+ "\n                55"
			    		+ "\n                    null"
			    		+ "\n                    70"
			    		+ "\n                        60"
			    		+ "\n                        null";
		
		assertEquals(expected, st.toString());
		
		st.add(33);
		
	}
	
	/**
	 * Find a Node containing specific data.  Expecting to find the Node and only
	 * comparing the Node's data to the expected value
	 * 
	 * {@link SplayTree#findElement(Comparable)}
	 */
	@Test
	void findElement1() {
		SplayTree<Integer> st = new SplayTree<Integer>(50);
		st.addBST(40);
		st.addBST(60);
		
		assertEquals((Integer) 40, st.findElement(40));
		assertEquals((Integer) 50, st.findElement(50));
		assertEquals((Integer) 60, st.findElement(60));
	}
	
	/**
	 * Searching for a Node containing specific data.  The target Node does not exist, 
	 * so the expected value is null and the last Node found during search has been assigned to root.
	 * Right direction
	 * 
	 * {@link SplayTree#findElement(Comparable)}
	 */
	@Test
	void findElement2() {
		// Constructor and add Videos w/o splaying
		SplayTree<Integer> st = new SplayTree<Integer>(50);
		st.addBST(40);
		st.addBST(60);
		
		// 
		assertEquals(null, st.findElement(70));
		assertEquals((Integer) 60, st.getRoot());
	}
	
	/**
	 * Searching for a Node containing specific data.  The target Node does not exist, 
	 * so the expected value is null and the last Node found during search has been assigned to root.
	 * Left direction
	 * 
	 * {@link SplayTree#findElement(Comparable)}
	 */
	@Test
	void findElement3() {
		SplayTree<Integer> st = new SplayTree<Integer>(50);
		st.addBST(40);
		st.addBST(60);
		
		assertEquals(null, st.findElement(30));
	}
	
	/**
	 * Unique case where the SplayTree is empty and you are searching for specific data
	 * 
	 * {@link SplayTree#findElement(Comparable)}
	 */
	@Test
	void findElement4() {
		// Create empty splay tree
		SplayTree<Integer> st = new SplayTree<Integer>();
		
		assertEquals(null, st.findElement(10));
	}
	
	/**
	 * Unique case where the SplayTree is empty and you are searching for null data
	 * 
	 * {@link SplayTree#findElement(Comparable)}
	 */
	@Test
	void findElement5() {
		// Create empty splay tree
		SplayTree<Integer> st = new SplayTree<Integer>();
		
		assertEquals(null, st.findElement(null));
	}
	
	
	@Test
	void remove1() {
		SplayTree<Integer> st = new SplayTree<Integer>(8);
		st.addBST(4);
		st.addBST(2);
		st.addBST(6);
		st.addBST(1);
		st.addBST(3);
		st.addBST(5);
		st.addBST(7);
		
		String expected = "8" + 
						  "\n    4" +
						  "\n        2" +
						  "\n            1" +
						  "\n            3" +						  
						  "\n        6" +
						  "\n            5" +
						  "\n            7" +
						  "\n    null";
		
		assertEquals(expected, st.toString());
		
		assertTrue(st.remove(4));
		
		expected = "8" + 
				  "\n    3" +
				  "\n        2" +
				  "\n            1" +
				  "\n            null" +						  
				  "\n        6" +
				  "\n            5" +
				  "\n            7" +
				  "\n    null";
		
		assertEquals(expected, st.toString());
	}
	
	/**
	 * 
	 */
	@Test
	void remove2() {
//		SplayTree<Integer> st = new SplayTree<Integer>(48);
//		assertTrue(st.remove(48));
//		assertEquals(0, st.size());
//		assertEquals(null, st.getRoot());
	}
	
	/**
	 * Removing a leaf node
	 */
	@Test
	void remove3() {
		SplayTree<Integer> st = new SplayTree<Integer>(48);
		st.addBST(30);
		st.addBST(55);
		st.addBST(28);
		st.addBST(42);
		st.addBST(51);
		st.addBST(61);
		st.addBST(25);
		st.addBST(36);
		st.addBST(47);
		st.addBST(32);
		st.addBST(38);
		st.addBST(34);
		
		assertTrue(st.remove(25));
		
		// Output SplayTree.
		String actual = st.toString();
		
		// Visual output - optional
//				System.out.println(actual);
		
		String expected = "28" +
						  "\n    null" + 
						  "\n    30" + 
						  "\n        null" +
						  "\n        48" + 
		                  "\n            42" +
		                  "\n                36" +
		                  "\n                    32" +
		                  "\n                        null" +
		                  "\n                        34" +
		                  "\n                    38" +
		                  "\n                47" +
		                  "\n            55" +
		                  "\n                51" +
		                  "\n                61";

		                  
		assertEquals(expected, actual);
		assertEquals(12, st.size());
		assertEquals((Integer) 28, st.getRoot());
	}
	
	/**
	 * Removing null from empty tree
	 */
	@Test
	void remove4() {
		SplayTree<Integer> st = new SplayTree<Integer>();
		assertFalse(st.remove(null));
		assertEquals(0, st.size());
		assertEquals(null, st.getRoot());
	}
	
	/**
	 * Removing null from non-empty tree
	 */
	@Test
	void remove5() {
		SplayTree<Integer> st = new SplayTree<Integer>(50);
		assertFalse(st.remove(null));
		assertEquals(1, st.size());
		assertEquals((Integer) 50, st.getRoot());
	}
	
	
	/**
	 * Remove the root
	 */
	@Test
	void remove6() {
		SplayTree<Integer> st = new SplayTree<Integer>(48);
		assertTrue(st.remove(48));
		assertEquals(0, st.size());
		assertEquals(null, st.getRoot());
	}
	
	/**
	 * Removing root node when it has children
	 */
	@Test
	void remove7() {
		SplayTree<Integer> st = new SplayTree<Integer>(48);
		st.addBST(30);
		st.addBST(50);
		
		assertTrue(st.remove(48));
		
		String expected = "30" + 
						  "\n    null" + 
						  "\n    50";
		
		assertEquals(expected, st.toString());
	}
	
	/**
	 * Remove Node from a degenerate tree
	 */
	@Test
	void remove8() {
		SplayTree<Integer> st = new SplayTree<Integer>(48);
		st.addBST(30);
		st.addBST(28);
		st.addBST(25);
		
		String expected = "48" + 
				  		  "\n    30" +
				  		  "\n        28" +
				  		  "\n            25"+
				  		  "\n            null" +
				  		  "\n        null" +
				  		  "\n    null";
		
		assertEquals(expected, st.toString());
		
		assertTrue(st.remove(25));
		
		expected = "28" + 
				   "\n    null" +
		  		   "\n    30" +
		  		   "\n        null" +
				   "\n        48";

		assertEquals(expected, st.toString());
	}
	
	/**
	 * Remove Node that doesn't exist from a degenerate tree
	 */
	@Test
	void remove9() {
		SplayTree<Integer> st = new SplayTree<Integer>(48);
		st.addBST(30);
		st.addBST(28);
		st.addBST(25);
		
		String expected = "48" + 
				  		  "\n    30" +
				  		  "\n        28" +
				  		  "\n            25"+
				  		  "\n            null" +
				  		  "\n        null" +
				  		  "\n    null";
		
		assertEquals(expected, st.toString());
		
		assertFalse(st.remove(29));
		
		expected = "28" + 
				   "\n    25" +
				   "\n    30" +
				   "\n        null" +
				   "\n        48";
		  		   

		assertEquals(expected, st.toString());
	}
	
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
		                  "            62";
		                  
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
		                  "            62";
		                  
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
		                  "    null";
		                  
		assertEquals(expected, actual);
	}
}
