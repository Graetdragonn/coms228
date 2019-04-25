package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw5.SplayTree;
import edu.iastate.cs228.hw5.Video;

class SplayTreeTests {

	/**
	 * Basic constructor test that shows the constructor creates
	 * an empty splay tree
	 */
	@Test
	void constructorTest1() {
		SplayTree<Video> st = new SplayTree<Video>();
		assertEquals(0, st.size());
	}

	/**
	 * Very early test of constructing videostore and adding a single video
	 */
	@Test
	void constructorTest2() {
		SplayTree<Video> st = new SplayTree<Video>(new Video("Hogan's Heroes"));
		
		// The size should now be 1
		assertEquals(1, st.size());
	}
	
	/**
	 * This tests the console output of the SplayTree.toString() method and compares it against
	 * an array of expected line values.
	 */
	@Test
	void toStringTest1() {
		SplayTree<Video> st = new SplayTree<Video>(new Video("Hogan's Heroes"));
		st.addBST(new Video("Chappie"));
		st.addBST(new Video("Xandar"));
		
		// Output SplayTree.
		String actual = st.toString();
		
		// Visual output
		System.out.println(actual);
		
		
		String[] lines = {"Hogan's Heroes (1:0)\n",
						  "    Chappie (1:0)\n",
						  "    Xandar (1:0)\n"};
		
		String expected = "";
		for (int i = 0; i < lines.length; i++) {
			expected = expected + lines[i];
		}
		
		assertEquals(expected, actual);
	}
}
