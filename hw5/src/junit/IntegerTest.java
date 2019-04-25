package junit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw5.SplayTree;

class IntegerTest {

	/**
	 * These tests involve the SplayTree using Integers as displayed in the handout.
	 * SplayTree is supposed to be generic, so it should work on this data just as easily.
	 */
	@Test
	void sizeTest() {
		SplayTree<Integer> st = new SplayTree<Integer>(50);
		
		assertEquals(1, st.size());
	}
	
	
	@Test
	void stringTest() {
		SplayTree<Integer> st = new SplayTree<Integer>(50);
		st.addBST(30);
		st.addBST(55);
		st.addBST(25);
		st.addBST(35);
		st.addBST(53);
		st.addBST(60);
		st.addBST(62);
		st.addBST(10);
		st.addBST(31);
		st.addBST(37);
		st.addBST(37);
		st.addBST(20);
		
		// Output SplayTree.
		String actual = st.toString();
		
		// Visual output
		System.out.println(actual);
		
		
		String[] lines = {"50\n",
						  "    30\n",
						  "    55\n"};
		
		String expected = "";
		for (int i = 0; i < lines.length; i++) {
			expected = expected + lines[i];
		}
		
		assertEquals(expected, actual);
		
	}
}
