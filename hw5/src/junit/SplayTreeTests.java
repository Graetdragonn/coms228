package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw5.SplayTree;
import edu.iastate.cs228.hw5.Video;

class SplayTreeTests {

	private Video v1 = new Video("Cowboy Bebop");
	private Video v2 = new Video("Ghost in the Shell");
	private Video v3 = new Video("Elysium");
	private Video v4 = new Video("Hackers");
	private Video v5 = new Video("Sneakers");
	private Video v6 = new Video("Antitrust");
	private Video v7 = new Video("The Matrix");
	private Video v8 = new Video("Aeon Flux");
	private Video v9 = new Video("Eagle Eye");
	private Video v10 = new Video("Chappie");
	private Video v11 = new Video("Wall-E");
	private Video v12 = new Video("Hogan's Heroes");
	private Video v13 = new Video("Xandar");
	private Video v14 = new Video("WarGames");
	private Video v15 = new Video("Moon");
	private Video v16 = new Video("Interstellar");
	private Video v17 = new Video("Gatticca");
	private Video v18 = new Video("From the Earth to the Moon");
	private Video v19 = new Video("Gravity");
	
	/**
	 * Try to add null to a tree
	 */
	@Test
	void add1() {
		SplayTree<Video> st = new SplayTree<Video>(v5);
		assertFalse(st.add(null));
	}
	
	/**
	 * Add to an empty tree - new object becomes the root
	 */
	@Test
	void add2() {
		SplayTree<Video> st = new SplayTree<Video>();
		st.add(v5);
		
		assertEquals(v5, st.getRoot());
	}
	
	/**
	 * Check the size increase if adding the root element
	 */
	@Test
	void add3() {
		SplayTree<Video> st = new SplayTree<Video>();
		st.add(v5);
		
		assertEquals(1, st.size());
	}
	
		
	/**
	 * Add an element that already exists in the tree
	 */
	@Test
	void add4() {
		SplayTree<Video> st = new SplayTree<Video>(v5);
		
		// Element already exists
		assertFalse(st.add(v5));
	}
	
	/**
	 *  Add an element that already exists in the tree.  
	 *  Note: the sizes (number of copies) is the same here.
	 * 
	 *  Check Tree size.
	 */
	@Test
	void add5() {
		SplayTree<Video> st = new SplayTree<Video>(v5);
		
		assertFalse(st.add(v5));
		assertEquals(1, st.size());
	}
	
	/**
	 * Add an element that already exists in the tree, but with different sizes.
	 * Check size.
	 */
	@Test
	void add6() {
		SplayTree<Video> st = new SplayTree<Video>(v6); // Antitrust (1:0)
		Video v = new Video("Antitrust", 6);  // Different number of copies, but same title
		
		assertFalse(st.add(v));
		assertEquals(1, st.size());
	}
	
	/**
	 * Add a second element.  Splay causes a new root and there is a size increase.
	 */
//	@Test
//	void add7() {
//		SplayTree<Video> st = new SplayTree<Video>(v5);
//		assertTrue(st.add(v6));				
//		assertEquals(v6, st.getRoot());
//		assertEquals(2, st.size());
//	}
//	
//	/**
//	 * Tree display after a series of adds
//	 */
//	@Test
//	void add8() {
//		SplayTree<Video> st = new SplayTree<Video>(v5);  // Sneakers
//		st.add(v7); // The Matrix
//		
//		String step1 = "The Matrix (1:0)\n" +
//					   "    Sneakers (1:0)\n" +
//					   "    null";
//		
//		assertEquals(step1, st.toString());
//		
//		st.add(v9); // Eagle Eye
//		
//		String step2 = "Eagle Eye (1:0)\n" +
//					   "    null\n" +
//					   "    Sneakers (1:0)\n" +
//				       "        null\n" +
//				       "        The Matrix (1:0)";
//				       
//		
//		assertEquals(step2, st.toString());
//		assertEquals(v9, st.getRoot());
//	}
	
	
	/**
	 * Final example from pptx
	 */
	@Test
	void bigTest1() {
		SplayTree<Video> st = new SplayTree<Video>();
		st.addBST(v16);
		st.addBST(v4);
		st.addBST(v2);
		st.addBST(v6);
		st.addBST(v19);
		st.addBST(v17);
		st.addBST(v10);
		st.addBST(v1);
		st.addBST(v9);
		st.addBST(v3);
		
		String expected = "Interstellar (1:0)" +
						  "\n    Hackers (1:0)" +
						  "\n        Ghost in the Shell (1:0)" +
						  "\n            Antitrust (1:0)" +
						  "\n                null" +
						  "\n                Gatticca (1:0)" +
						  "\n                    Chappie (1:0)" +
						  "\n                        null" +
						  "\n                        Cowboy Bebop (1:0)" +
						  "\n                            null" +
						  "\n                            Eagle Eye (1:0)" +
						  "\n                                null" +
						  "\n                                Elysium (1:0)" +
						  "\n                    null" +
						  "\n            Gravity (1:0)" +
						  "\n        null" +
						  "\n    null";
		
		assertEquals(expected, st.toString());
		
		st.splay(v3);
		
		expected = "Elysium (1:0)" +
				   "\n    Antitrust (1:0)" +
				   "\n        null" +
				   "\n        Chappie (1:0)" +
				   "\n            null" +
				   "\n            Eagle Eye (1:0)" +
				   "\n                Cowboy Bebop (1:0)" +
				   "\n                null" +
				   "\n    Hackers (1:0)" +
				   "\n        Ghost in the Shell (1:0)" +
				   "\n            Gatticca (1:0)" +
				   "\n            Gravity (1:0)" +
				   "\n        Interstellar (1:0)";
		
		assertEquals(expected, st.toString());
	}
	
	/**
	 * Clears the SplayTree.  Size is zero.
	 */
	@Test
	void clear1() {
		SplayTree<Video> st = new SplayTree<Video>(v14);
		st.clear();
		
		assertEquals(0, st.size());
	}
	
	/**
	 * Clears the SplayTree.  Root is null
	 */
	@Test
	void clear2() {
		SplayTree<Video> st = new SplayTree<Video>(v14);
		st.clear();
		
		assertEquals(null, st.getRoot());
	}
	
	/**
	 * Clears the SplayTree.  Constructed with a video, clears the tree and then
	 * fails to find the same video it was constructed with.
	 */
	@Test
	void clear3() {
		SplayTree<Video> st = new SplayTree<Video>(v14);
		st.clear();
		
		assertEquals(null, st.findElement(v14));
	}
	
	/**
	 * The SplayTree contains the element
	 */
	@Test
	void contains1() {
		SplayTree<Video> st = new SplayTree<Video>(v11); // Wall-E
		assertTrue(st.contains(v11));
	}
	
	/**
	 * The SplayTree contains the element.  After finding the element, it should
	 * have splayed the tree (i.e., new root)
	 */
	@Test
	void contains2() {
		SplayTree<Video> st = new SplayTree<Video>(v11); // Wall-E
		assertTrue(st.contains(v11));
		assertEquals(v11, st.getRoot());
	}
	
	/**
	 * Test the empty tree returns false when searching for an element
	 */
	@Test
	void contains3() {
		SplayTree<Video> st = new SplayTree<Video>();

		// Empty tree does not contain video
		assertFalse(st.contains(v11));
	}

	/**
	 * Non-empty tree does not contain this video
	 */
	@Test
	void contains4() {
		SplayTree<Video> st = new SplayTree<Video>();
		
		st.addBST(v11);
		assertFalse(st.contains(v1));
	}
	
	/**
	 * Tree returns false when searching for null
	 */
	@Test
	void contains5() {
		SplayTree<Video> st = new SplayTree<Video>();
		// Tree can't find "null"
		assertFalse(st.contains(null));
	}
	
	/**
	 * Tree returns false when searching for null.   Root is not changed (no splaying took place).
	 */
	@Test
	void contains6() {
		SplayTree<Video> st = new SplayTree<Video>(v11);
		
		assertFalse(st.contains(null));
		assertEquals(v11, st.getRoot());
	}
	
	
	/**
	 * Construct the SplayTree by copying an existing splay tree.  Constructor test.
	 */
	@Test
	void copyTree1() {
		// Create the original splay tree
		SplayTree<String> stOrig = new SplayTree<String>("A");
		
		// Copy the original splay tree
		SplayTree<String> stNew = new SplayTree<String>(stOrig);
		
		// Roots should be the same
		assertEquals(stOrig.getRoot(), stNew.getRoot());
		
		// Sizes should be the same
		assertEquals(stOrig.size(), stNew.size());
	}
	
	/**
	 * Construct the SplayTree by copying an existing empty splay tree.  Constructor test of empty second tree.
	 */
	@Test
	void copyTree2() {
		// Create the original splay tree
		SplayTree<String> stOrig = new SplayTree<String>();
		
		// Copy the original splay tree
		SplayTree<String> stNew = new SplayTree<String>(stOrig);
		
		// Roots should be the same
		assertEquals(stOrig.getRoot(), stNew.getRoot());
		
		// Sizes should be the same
		assertEquals(stOrig.size(), stNew.size());
	}
	
	
	/**
	 * Copy existing splay tree.  Make an addition to the original and check if the copy has been affected.
	 */
	@Test
	void copyTree3() {
		// Create the original splay tree
		SplayTree<String> stOrig = new SplayTree<String>("A");
		
		// Copy the original splay tree
		SplayTree<String> stNew = new SplayTree<String>(stOrig);
		
		// Make an addition to the original
		stOrig.add("B");
		
		// Roots should not be the same because of splaying "B" into the original tree
		assertFalse(stOrig.getRoot().equals(stNew.getRoot()));
		
		// Sizes should not be the same
		assertFalse(stOrig.size() == stNew.size());
	}
	
	
	
	
	/**
	 * Unique case where the SplayTree is empty and you are searching for specific data
	 * 
	 * {@link SplayTree#findElement(Comparable)}
	 */
	@Test
	void findElementVideoTest4() {
		// Create empty splay tree
		SplayTree<Video> st = new SplayTree<Video>();
		
		assertEquals(null, st.findElement(v1));
	}
	
	/**
	 * Unique case where the SplayTree is empty and you are searching for null data
	 * 
	 * {@link SplayTree#findElement(Comparable)}
	 */
	@Test
	void findElementVideoTest5() {
		// Create empty splay tree
		SplayTree<Video> st = new SplayTree<Video>();
		
		assertEquals(null, st.findElement(null));
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
	
	@Test
	void getRootTest2() {
		SplayTree<Video> st = new SplayTree<Video>();
		
		assertEquals(null, st.getRoot());
	}
	
	/**
	 * Throws exception calling next() before calling hasNext()
	 */
	@Test
	void iteratorException1() {
		// TODO
		
	}
	
	/**
	 * Throws illegalstateexception on a call to remove when pending==null
	 */
	@Test
	void iteratorException2() {
		//TODO
	}
	
	
	/**
	 * Uses the iterator to remove a left leaf
	 */
	@Test
	void iteratorRemove1() {
		SplayTree<Video> st = new SplayTree<Video>(v6); // Antitrust
		st.addBST(v15); // Moon
		st.addBST(v4);  // Hackers
		st.addBST(v11); // Wall-E
		
		Iterator<Video> iter = st.iterator();
		
		String expected = "Antitrust (1:0)\n" +
						  "    null\n" +
						  "    Moon (1:0)\n" +
						  "        Hackers (1:0)\n" +
						  "        Wall-E (1:0)";
		
		// Quick check the setup was correct
		assertEquals(expected, st.toString());
		
		// Delete the leaf Node Hackers
		while(iter.hasNext()) {
			Video v = iter.next();
			if (v.compareTo(v4) == 0) {
				iter.remove();
			}
		}
		
		expected = "Antitrust (1:0)\n" +
				   "    null\n" +
				   "    Moon (1:0)\n" +
				   "        null\n" +
				   "        Wall-E (1:0)";
		
		// Check the new structure
		assertEquals(expected, st.toString());
		
		// Check the root (Iterator remove does not splay).  Redundant check
		assertEquals(v6, st.getRoot());
	}
	
	
	/**
	 * Uses the iterator to remove a right leaf
	 */
	@Test
	void iteratorRemove2() {
		SplayTree<Video> st = new SplayTree<Video>(v6); // Antitrust
		st.addBST(v15); // Moon
		st.addBST(v4);  // Hackers
		st.addBST(v11); // Wall-E
		
		Iterator<Video> iter = st.iterator();
		
		String expected = "Antitrust (1:0)\n" +
						  "    null\n" +
						  "    Moon (1:0)\n" +
						  "        Hackers (1:0)\n" +
						  "        Wall-E (1:0)";
		
		// Quick check the setup was correct
		assertEquals(expected, st.toString());
		
		// Delete the left-leaf Node Wall-E
		while(iter.hasNext()) {
			Video v = iter.next();
			if (v.compareTo(v11) == 0) {
				iter.remove();
			}
		}
		
		
		expected = "Antitrust (1:0)\n" +
				   "    null\n" +
				   "    Moon (1:0)\n" +
				   "        Hackers (1:0)\n" +
				   "        null";
		
		// Check the new structure
		assertEquals(expected, st.toString());
	}
	
	
	/**
	 * Uses the iterator to remove a Node with left-tree
	 */
	@Test
	void iteratorRemove3() {
		SplayTree<Video> st = new SplayTree<Video>(v6); // Antitrust
		st.addBST(v15); // Moon
		st.addBST(v4);  // Hackers
		st.addBST(v11); // Wall-E
		
		Iterator<Video> iter = st.iterator();
		
		String expected = "Antitrust (1:0)\n" +
						  "    null\n" +
						  "    Moon (1:0)\n" +
						  "        Hackers (1:0)\n" +
						  "        Wall-E (1:0)";
		
		// Quick check the setup was correct
		assertEquals(expected, st.toString());
		
		// Delete the Node 
		while(iter.hasNext()) {
			Video v = iter.next();
			if (v.compareTo(v15) == 0) {
				iter.remove();
			}
		}
		
		expected = "Antitrust (1:0)\n" +
				   "    null\n" +
				   "    Wall-E (1:0)\n" +
				   "        Hackers (1:0)\n" +
				   "        null";
		
		// Check the new structure
		assertEquals(expected, st.toString());
	}
	
	/**
	 * Uses the iterator to remove a Node with right-tree
	 */
	@Test
	void iteratorRemove4() {
		// TODO
		SplayTree<Video> st = new SplayTree<Video>(v2); // Ghost in the Shell
		st.addBST(v8);  // Aeon Flux
		st.addBST(v4);  // Hackers
		st.addBST(v1);  // Cowboy Bebop
		st.addBST(v14); // WarGames
		st.addBST(v10); // Chappie
		
		Iterator<Video> iter = st.iterator();
		
		String expected = "Ghost in the Shell (1:0)\n" +
						  "    Aeon Flux (1:0)\n" +
						  "        null\n" +
						  "        Cowboy Bebop (1:0)\n" +
						  "            Chappie (1:0)\n" +
						  "            null\n" +
						  "    Hackers (1:0)\n" +
						  "        null\n" +
						  "        WarGames (1:0)";
		
		// Quick check the setup was correct
		assertEquals(expected, st.toString());
		
		// Delete the Node 
		while(iter.hasNext()) {
			Video v = iter.next();
			if (v.compareTo(v1) == 0) {
				iter.remove();
			}
		}
		
		expected = "Ghost in the Shell (1:0)\n" +
				   "    Aeon Flux (1:0)\n" +
				   "        null\n" +
			       "        Chappie (1:0)\n" +
				   "    Hackers (1:0)\n" +
				   "        null\n" +
				   "        WarGames (1:0)";
		
		// Check the new structure
		assertEquals(expected, st.toString());
	}
	
	/**
	 * Uses the iterator to remove a Node that is not in the tree.  This causes a splay at the last
	 * Node in the search path
	 */
	@Test
	void iteratorRemove5() {
		// TODO
	}
	
	/**
	 * Uses the iterator to remove the root Node.
	 */
	@Test
	void iteratorRemove6() {
		// TODO
	}
	
	/**
	 * Traverse the list using the iterator twice
	 */
	@Test
	void iteratorTraversal1() {
		SplayTree<Video> st = new SplayTree<Video>();
		st.addBST(v2);
		st.addBST(v8);
		st.addBST(v4);
		st.addBST(v1);
		st.addBST(v14);
		st.addBST(v10);
		
		Iterator<Video> iter = st.iterator();
		
		String s = "";
		while(iter.hasNext()) {
			s = s + "\n" + iter.next().getFilm();
		}
		
		String expected = "\nAeon Flux" +
						  "\nChappie" + 
						  "\nCowboy Bebop" + 
						  "\nGhost in the Shell" +
						  "\nHackers" + 
						  "\nWarGames";
		
		assertEquals(expected, s);
		
		//////////////// DO IT TWICE //////////////////////
		
		iter = st.iterator();
		
		s = "";
		while(iter.hasNext()) {
			s = s + "\n" + iter.next().getFilm();
		}
		
		expected = "\nAeon Flux" +
						  "\nChappie" + 
						  "\nCowboy Bebop" + 
						  "\nGhost in the Shell" +
						  "\nHackers" + 
						  "\nWarGames";
		
		assertEquals(expected, s);
	}
	
	/**
	 * Traverse the list, findElement, then traverse the list again.
	 */
	@Test
	void iteratorTraversal2() {
		SplayTree<Video> st = new SplayTree<Video>();
		st.addBST(v4);  // Hackers
		st.addBST(v14); // WarGames
		st.addBST(v5);  // Sneakers
		
		Iterator<Video> iter = st.iterator();
		
		String s = "";
		while(iter.hasNext()) {
			s = s + "\n" + iter.next().getFilm();
		}
		
		String expected = "\nHackers" +
						  "\nSneakers" +
						  "\nWarGames";
		
		assertEquals(expected, s);
		
		// Splays Sneakers to Root
		st.findElement(v5);
		
		// New iterator
		iter = st.iterator();
		
		s = "";
		while(iter.hasNext()) {
			s = s + "\n" + iter.next().getFilm();
		}
		
		assertEquals(expected, s);
	}
	
	/**
	 * Traverse the list, findElement, then traverse the list again.
	 */
//	@Test
//	void iteratorTraversal3() {
//		SplayTree<Video> st = new SplayTree<Video>();
//		st.addBST(v2);
//		st.addBST(v8);
//		st.addBST(v4);
//		st.addBST(v1);
//		st.addBST(v14);
//		st.addBST(v10);
//		
//		Iterator<Video> iter = st.iterator();
//		
//		String s = "";
//		while(iter.hasNext()) {
//			s = s + "\n" + iter.next().getFilm();
//		}
//		
//		String expected = "\nAeon Flux" +
//						  "\nChappie" + 
//						  "\nCowboy Bebop" + 
//						  "\nGhost in the Shell" +
//						  "\nHackers" + 
//						  "\nWarGames";
//		
//		assertEquals(expected, s);
//		
//		// Find element.  This causes splaying.
//		Video v = st.findElement(v1);
//		assertEquals(v1, st.getRoot());
//		
//		
//		// Traverse the list again.  Output should be the same
//		iter = st.iterator();
//		
//		s = "";
//		while(iter.hasNext()) {
//			s = s + "\n" + iter.next().getFilm();
//		}
//		
//		expected = "\nAeon Flux" +
//						  "\nChappie" + 
//						  "\nCowboy Bebop" + 
//						  "\nGhost in the Shell" +
//						  "\nHackers" + 
//						  "\nWarGames";
//		
//		assertEquals(expected, s);
//	}
	
	
	/**
	 * Trying to remove null from an empty tree
	 */
	@Test
	void remove1() {
		SplayTree<Video> st = new SplayTree<Video>();
		assertFalse(st.remove(null));
	}
	
	/**
	 * Removal of root Node from a tree with only a root node
	 */
	@Test
	void remove2() {
		SplayTree<Video> st = new SplayTree<Video>(v1);
		assertTrue(st.remove(v1));
		assertEquals(0, st.size());
		assertEquals(null, st.getRoot());
	}
	
	/**
	 * Removal of null from a tree with only a root node
	 */
	@Test
	void remove3() {
		SplayTree<Video> st = new SplayTree<Video>(v1);
		assertFalse(st.remove(null));
		assertEquals(1, st.size());
		assertEquals(v1, st.getRoot());
	}
	
	/**
	 * The node you are trying to remove does not exist in the tree
	 */
	@Test
	void remove4() {
		SplayTree<Video> st = new SplayTree<Video>(v2); // Ghost in the Shell
		st.addBST(v8);  // Aeon Flux
		st.addBST(v4);  // Hackers
		st.addBST(v1);  // Cowboy Bebop
		st.addBST(v14); // WarGames
		st.addBST(v10); // Chappie
		
		String expected = "Ghost in the Shell (1:0)\n" +
				   "    Aeon Flux (1:0)\n" +
				   "        null\n" +
				   "        Cowboy Bebop (1:0)\n" +
				   "            Chappie (1:0)\n" +
				   "            null\n" +
				   "    Hackers (1:0)\n" +
				   "        null\n" +
				   "        WarGames (1:0)"; 
		
		// Quick check the setup was correct
		assertEquals(expected, st.toString());
		
		// Attempt to remove a Node that does not exist in the tree
		assertFalse(st.remove(v5)); // Sneakers
		
		expected = "WarGames (1:0)\n" +
				 "    Hackers (1:0)\n" +
				 "        Ghost in the Shell (1:0)\n" +
				 "            Aeon Flux (1:0)\n" +
				 "                null\n" +
				 "                Cowboy Bebop (1:0)\n" +
				 "                    Chappie (1:0)\n" +
				 "                    null\n" +
				 "            null\n" +
				 "        null\n" +
				 "    null";

		// Quick check the setup was correct
		assertEquals(expected, st.toString());
	}
	
	/**
	 * Removal of a left-leaf Node.  Splay at parent.
	 */
//	@Test
//	void remove1() {
//		SplayTree<Video> st = new SplayTree<Video>(v6); // Antitrust
//		st.addBST(v15); // Moon
//		st.addBST(v4);  // Hackers
//		st.addBST(v11); // Wall-E
//		
//		String expected = "Antitrust (1:0)\n" +
//						  "    null\n" +
//						  "    Moon (1:0)\n" +
//						  "        Hackers (1:0)\n" +
//						  "        Wall-E (1:0)";
//		
//		// Quick check the setup was correct
//		assertEquals(expected, st.toString());
//		
//		// Delete the leaf Node Hackers
//		st.remove(v4);
//		
//		expected = "Moon (1:0)\n" +
//				   "    Antitrust (1:0)\n" +
//				   "    Wall-E (1:0)";
//		
//		// Check the new structure
//		assertEquals(expected, st.toString());
//	}
	
	/**
	 * Removal of a right-leaf Node
	 */
//	@Test
//	void remove2() {
//		SplayTree<Video> st = new SplayTree<Video>(v6); // Antitrust
//		st.addBST(v15); // Moon
//		st.addBST(v4);  // Hackers
//		st.addBST(v11); // Wall-E
//		
//		String expected = "Antitrust (1:0)\n" +
//						  "    null\n" +
//						  "    Moon (1:0)\n" +
//						  "        Hackers (1:0)\n" +
//						  "        Wall-E (1:0)";
//		
//		// Quick check the setup was correct
//		assertEquals(expected, st.toString());
//		
//		// Delete the left-leaf Node Wall-E
//		st.remove(v11);
//		
//		expected = "Moon (1:0)\n" +
//				   "    Antitrust (1:0)\n" +
//				   "        null\n" +
//				   "        Hackers (1:0)\n" +
//				   "    null";
//		
//		// Check the new structure
//		assertEquals(expected, st.toString());
//	}
	
	/**
	 * Removal of a Node.  Case 1 - Target Node has a right subtree
	 */
//	@Test
//	void remove3() {
//		SplayTree<Video> st = new SplayTree<Video>(v6); // Antitrust
//		st.addBST(v15); // Moon
//		st.addBST(v4);  // Hackers
//		st.addBST(v11); // Wall-E
//		
//		String expected = "Antitrust (1:0)\n" +
//						  "    null\n" +
//						  "    Moon (1:0)\n" +
//						  "        Hackers (1:0)\n" +
//						  "        Wall-E (1:0)";
//		
//		// Quick check the setup was correct
//		assertEquals(expected, st.toString());
//		
//		// TODO
//		// Delete the Node 
//		st.remove(v15); // Moon
//		
//		expected = "Antitrust (1:0)\n" +
//				   "    null\n" +
//				   "    Hackers (1:0)\n" +				   
//				   "        null\n" +
//				   "        Wall-E (1:0)";
//		
//		// Check the new structure
//		assertEquals(expected, st.toString());
//	}
	
	
	
	
	/**
	 * Removing a Node.  Case 2 - Target Node does not have a right subtree.
	 */
//	@Test
//	void remove4() {
//		SplayTree<Video> st = new SplayTree<Video>(v2); // Ghost in the Shell
//		st.addBST(v8);  // Aeon Flux
//		st.addBST(v4);  // Hackers
//		st.addBST(v1);  // Cowboy Bebop
//		st.addBST(v14); // WarGames
//		st.addBST(v10); // Chappie
//		
//		String expected = "Ghost in the Shell (1:0)\n" +
//						  "    Aeon Flux (1:0)\n" +
//						  "        null\n" +
//						  "        Cowboy Bebop (1:0)\n" +
//						  "            Chappie (1:0)\n" +
//						  "            null\n" +
//						  "    Hackers (1:0)\n" +
//						  "        null\n" +
//						  "        WarGames (1:0)";
//		
//		// Quick check the setup was correct
//		assertEquals(expected, st.toString());
//		
//		// Delete the Node 
//		st.remove(v1); // Cowboy Bebop
//		
//		expected = "Ghost in the Shell (1:0)\n" +
//				   "    Aeon Flux (1:0)\n" +
//				   "        null\n" +
//			       "        Chappie (1:0)\n" +
//				   "    Hackers (1:0)\n" +
//				   "        null\n" +
//				   "        WarGames (1:0)";
//		
//		// Check the new structure
//		assertEquals(expected, st.toString());
//	}
	
	
	
	/**
	 * The node you are trying to remove does not exist in the tree, splaying takes place on the 
	 * last element in the tree.  This requires a zig-zig or zig-zag operation.
	 */
//	@Test
//	void remove6() {
//		SplayTree<Video> st = new SplayTree<Video>(v2); // Ghost in the Shell
//		st.addBST(v8);  // Aeon Flux
//		st.addBST(v4);  // Hackers
//		st.addBST(v1);  // Cowboy Bebop
//		st.addBST(v14); // WarGames
//		st.addBST(v10); // Chappie
//		
//		String expected = "Ghost in the Shell (1:0)\n" +
//						  "    Aeon Flux (1:0)\n" +
//						  "        null\n" +
//						  "        Cowboy Bebop (1:0)\n" +
//						  "            Chappie (1:0)\n" +
//						  "            null\n" +
//						  "    Hackers (1:0)\n" +
//						  "        null\n" +
//						  "        WarGames (1:0)";
//		
//		// Quick check the setup was correct
//		assertEquals(expected, st.toString());
//		
//		// Attempt to remove a Node that does not exist in the tree
//		assertFalse(st.remove(v5)); // Sneakers
//		assertEquals(v14, st.getRoot());
//	}
	
	
		
	/**
	 * Basic size test that shows the constructor creates
	 * an empty splay tree
	 */
	@Test
	void sizeTest1() {
		SplayTree<Video> st = new SplayTree<Video>();
		assertEquals(0, st.size());
	}

	/**
	 * Very early test of constructing videostore and adding a single video
	 */
	@Test
	void sizeTest2() {
		SplayTree<Video> st = new SplayTree<Video>(new Video("Hogan's Heroes"));
		
		// The size should now be 1
		assertEquals(1, st.size());
	}
	
	/**
	 * Silly test, but a start.  SplayTree only has one Video, so the test here is really not 
	 * to error out somehow.  The secondary consequence is that the root doesn't change
	 */
	@Test
	void splayTest1() {
		SplayTree<Video> st = new SplayTree<Video>(v1);
		
		st.splay(v1);
		
		assertEquals(v1, st.getRoot());
	}
	
	
	
	/**
	 * This tests the output of the SplayTree.toString() method and compares it against
	 * an array of expected line values.
	 */
	@Test
	void toStringTest1() {
		SplayTree<Video> st = new SplayTree<Video>(v10); // Chappie
		
		// Using addBST method (no splaying).  Note, no copy data is present, which is different
		// from toStringTest2()
		st.addBST(v12); // Hogan's Heroes
		st.addBST(v13); // Xandar
		
		// Output SplayTree.
		String actual = st.toString();
		
		// Visual output
//		System.out.println(actual);
		
		String expected = "Chappie (1:0)\n" +
						  "    null\n" +
						  "    Hogan's Heroes (1:0)\n" +
						  "        null\n" +
						  "        Xandar (1:0)"; 
		
		assertEquals(expected, actual);
	}
	
	
	
	/**
	 * This tests the output of the SplayTree.toString() method and compares it against
	 * an array of expected line values.
	 */
	@Test
	void toStringTest2() {
		SplayTree<Video> st = new SplayTree<Video>(new Video("Xandar", 1));
		
		// Using addBST method to prevent splaying.  Note, no copy data is present, which is different
		// from toStringTest2()
		st.addBST(new Video("Chappie", 1));
		st.addBST(new Video("Hogan's Heroes", 1));
		
		// Output SplayTree.
		String actual = st.toString();
		
		// Visual output
//		System.out.println(actual);
		
		String expected = "Xandar (1:0)\n" + 
						  "    Chappie (1:0)\n" +
						  "        null\n" +
						  "        Hogan's Heroes (1:0)\n" +
						  "    null";
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Test toString() method on empty Tree
	 */
	@Test
	void toStringTest3() {
		SplayTree<Video> st = new SplayTree<Video>();
		assertEquals("null", st.toString());
	}
	
	/**
	 * Test toString() method on empty Tree
	 */
	@Test
	void toStringTest4() {
		SplayTree<Video> st = new SplayTree<Video>();
		st.addBST(null);
		assertEquals("null", st.toString());
	}
	
	/**
	 * Rotate right
	 */
	@Test
	void zigAlphabet1() {
		SplayTree<String> st = new SplayTree<String>("B");
		st.addBST("A");
		st.addBST("C");
		
		String expected = "B" +
						  "\n    A" +
						  "\n    C";
		
		assertEquals(expected, st.toString());
		
		st.splay("A");
		
		expected = "A" +
				   "\n    null" +
				   "\n    B" +
				   "\n        null" +
				   "\n        C";
		
		assertEquals(expected, st.toString());
	}
	
	
	/**
	 * Find a Node containing specific data.  Expecting to find the Node and only
	 * comparing the Node's data to the expected value
	 * 
	 * {@link SplayTree#findElement(Comparable)}
	 */
	@Test
	void zigVideoTest1() {
		// Constructor and add Videos w/o splaying
		SplayTree<Video> st = new SplayTree<Video>(v1);  // Cowboy Bebop
		st.addBST(v2); // Ghost in the Shell
		st.addBST(v3); // Elysium
		
		// Zig -> leftRotate
		assertEquals(v2, st.findElement(v2));
		assertEquals(v2, st.getRoot());
		
		// Zig -> rightRotate
		assertEquals(v1, st.findElement(v1));
		assertEquals(v1, st.getRoot());
	}
	
	/**
	 * Searching for a Node containing specific data.  The target Node does not exist, 
	 * so the expected value is the last Node found during search
	 * 
	 * {@link SplayTree#findElement(Comparable)}
	 */
	@Test
	void zigVideoTest2() {
		// Constructor and add Videos w/o splaying
		SplayTree<Video> st = new SplayTree<Video>(v1);  // Cowboy Bebop
		st.addBST(v2);  // Ghost in the Shell
		st.addBST(v3);  // Elysium
		
		// Expecting Ghost in the Shell, looking for Hackers (not present).  Since Hacker's isn't present
		// findElement() -> findEntry() returns Ghost in the Shell (the last in the search path) which isn't
		// Hackers.  Thus, findElement() returns null, but Ghost in the Shell should be the new root
		assertEquals(null, st.findElement(v4));		
		// Ghost in the Shell is the last returned, thus it should be the new root
		assertEquals(v2, st.getRoot());
		
		// Expecting Cowboy Bebop, looking for Antitrust.  Effectively sets the SplayTree
		// back to its original form
		assertEquals(null, st.findElement(v6));
		assertEquals(v1, st.getRoot());
	}
	
	/**
	 * Searching for a Node containing specific data.  findElement splays at the Node returned, 
	 * regardless if this is the target Node or not.  Check the root.
	 * 
	 * {@link SplayTree#findElement(Comparable)}
	 */
	@Test
	void zigVideoTest3() {
		// Constructor and add Videos w/o splaying
		SplayTree<Video> st = new SplayTree<Video>(v4); // Hackers (root)
		st.addBST(v6); // Antitrust 
		st.addBST(v1); // Cowboy Bebop
		
		// Looking for Aeon Flux (not present), should return Antitrust and thus splays
		// Antitrust (to root)
		st.findElement(v8);
		assertEquals(v6, st.getRoot());				
	}
	
	/**
	 * Testing zig operation on trigonal setup
	 */
	@Test
	void zigVideoTest4() {
		SplayTree<Video> st = new SplayTree<Video>(v1);
		st.addBST(v8);
		st.addBST(v2);
		
		//
		assertEquals(v8, st.findElement(v8));		
		assertEquals(v8, st.getRoot());		
		
		String expected = "Aeon Flux (1:0)\n" +
						  "    null\n" +
						  "    Cowboy Bebop (1:0)\n" +
						  "        null\n" +
						  "        Ghost in the Shell (1:0)";
						  
		
		String actual = st.toString();
	
		assertEquals(expected, actual);						  
	}
	
	/**
	 * 
	 */
	@Test
	void zigVideoTest5() {
		SplayTree<Video> st = new SplayTree<Video>(v1);
		st.addBST(v2);
		
		// Finding Ghost in the Shell sets it to the root
		st.findElement(v2);
		
		String expected = "Ghost in the Shell (1:0)\n" + 
						  "    Cowboy Bebop (1:0)\n" +
						  "    null";
		
		String actual = st.toString();
		assertEquals(expected, actual);
	}
	
	/**
	 * Examples used from pptx
	 */
	@Test
	void zigzagAlphabet1() {
		SplayTree<String> st = new SplayTree<String>();
		st.addBST("m");
		st.addBST("g");
		st.addBST("s");
		st.addBST("b");
		st.addBST("i");
		st.addBST("t");
		st.addBST("a");
		st.addBST("c");
		st.addBST("h");
		st.addBST("k");
		
		String expected = "m" +
						  "\n    g" +
						  "\n        b" +
						  "\n            a" +
						  "\n            c" +
						  "\n        i" +
						  "\n            h" +
						  "\n            k" +
						  "\n    s" +
						  "\n        null" +
						  "\n        t";
		
		assertEquals(expected, st.toString());
		
		st.splay("i");
		
		expected = "i" +
				   "\n    g" +
				   "\n        b" +
				   "\n            a" +
				   "\n            c" +
				   "\n        h" +
				   "\n    m" +
				   "\n        k" +
				   "\n        s" +
				   "\n            null" +
				   "\n            t";
		
		assertEquals(expected, st.toString());
	}
	
	
	
	/**
	 * Examples used from pptx
	 */
	@Test
	void zigzigAlphabet1() {
		SplayTree<String> st = new SplayTree<String>();
		st.addBST("m");
		st.addBST("g");
		st.addBST("z");
		st.addBST("d");
		st.addBST("h");
		st.addBST("a");
		st.addBST("f");
		
		String expected = "m" +
						  "\n    g" +
						  "\n        d" +
						  "\n            a" +
						  "\n            f" +
						  "\n        h" +
						  "\n    z";
		
		assertEquals(expected, st.toString());
		
		// Try splaying d
		st.splay("d");
		
		expected = "d" +
				   "\n    a" +
				   "\n    g" +
				   "\n        f" +
				   "\n        m" +
				   "\n            h" +
				   "\n            z";
		
		assertEquals(expected, st.toString());
	}
	
	
}
