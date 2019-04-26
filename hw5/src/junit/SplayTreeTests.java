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
	 * Checks getting the data of the root.  The method addBST does not splay. 
	 */
	@Test
	void getRootTest1() {
		SplayTree<Video> st = new SplayTree<Video>(new Video("Aeon Flux"));
		assertEquals("Aeon Flux (1:0)", st.getRoot().toString());
		
		st.addBST(new Video("Sneakers"));
		assertEquals("Aeon Flux (1:0)", st.getRoot().toString());
		
		st.addBST(new Video("Antitrust"));
		assertEquals("Aeon Flux (1:0)", st.getRoot().toString());
	}
	
	/**
	 * This tests the output of the SplayTree.toString() method and compares it against
	 * an array of expected line values.
	 */
	@Test
	void toStringTest1() {
		SplayTree<Video> st = new SplayTree<Video>(new Video("Chappie"));
		
		// Using addBST method (no splaying).  Note, no copy data is present, which is different
		// from toStringTest2()
		st.addBST(new Video("Hogan's Heroes"));
		st.addBST(new Video("Xandar"));
		
		// Output SplayTree.
		String actual = st.toString();
		
		// Visual output
		System.out.println(actual);
		
		String expected = "Chappie (1:0)\n" +
						  "    null\n" +
						  "    Hogan's Heroes (1:0)\n" +
						  "        null\n" +
						  "        Xandar (1:0)\n"; 
		
		assertEquals(expected, actual);
	}
	
	/**
	 * This tests the output of the SplayTree.toString() method and compares it against
	 * an array of expected line values.
	 */
//	@Test
//	void toStringTest2() {
//		SplayTree<Video> st = new SplayTree<Video>(new Video("Xandar", 1));
//		
//		// Using addBST method to prevent splaying.  Note, no copy data is present, which is different
//		// from toStringTest2()
//		st.addBST(new Video("Chappie", 1));
//		st.addBST(new Video("Hogan's Heroes", 1));
//		
//		// Output SplayTree.
//		String actual = st.toString();
//		
//		// Visual output
//		System.out.println(actual);
//		
//		String expected = "Hogan's Heroes (1:0)\n" + 
//						  "    Chappie (1:0)\n" +
//						  "    Xandar (1:0)\n";
//		
//		assertEquals(expected, actual);
//	}
	
	/**
	 * This tests the output of the SplayTree.toString() method and compares it against
	 * an array of expected line values.
	 */
//	@Test
//	void toStringTest3() {
//		SplayTree<Video> st = new SplayTree<Video>(new Video("Hogan's Heroes"));
//		
//		// Using addBST method to prevent splaying.  Note, no copy data is present, which is different
//		// from toStringTest2()
//		st.addBST(new Video("Chappie", 2));
//		st.addBST(new Video("Xandar", 3));
//		
//		// Output SplayTree.
//		String actual = st.toString();
//		
//		// Visual output
//		System.out.println(actual);
//		
//		String expected = "Hogan's Heroes (1:0)\n" + 
//						  "    Chappie (2:0)\n" +
//						  "    Xandar (3:0)\n";
//		
//		assertEquals(expected, actual);
//	}
}
