package junit;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.iastate.cs228.hw5.*;


public class Timothy 
{
	SplayTree<Integer> stringTest;
	SplayTree<Integer> addTest;
	String form;
	@Before
	public void setup()
	{
		stringTest = new SplayTree<Integer>();
		addTest = new SplayTree<Integer>();
		stringTest.addBST(14);
		stringTest.addBST(8);
		stringTest.addBST(6);
		stringTest.addBST(1);
		stringTest.addBST(7);
		stringTest.addBST(2);
		stringTest.addBST(4);
		stringTest.addBST(3);
		stringTest.addBST(5);
		stringTest.addBST(9);
		stringTest.addBST(11);
		stringTest.addBST(10);
		stringTest.addBST(18);
		stringTest.addBST(16);
		stringTest.addBST(23);
		
		form = "14"
		   + "\n    8"
		   + "\n        6"
		   + "\n            1"
		   + "\n                null"
		   + "\n                2"
		   + "\n                    null"
		   + "\n                    4"
		   + "\n                        3"
		   + "\n                        5"
		   + "\n            7"
		   + "\n        9"
		   + "\n            null"
		   + "\n            11"
		   + "\n                10"
		   + "\n                null"
		   + "\n    18"
		   + "\n        16"
		   + "\n        23";
	}
	
	@Test
	public void stringTest()
	{
		System.out.println("toString test");
		System.out.println(stringTest +"\n");
		System.out.println(form +"\n");
		assertTrue(stringTest.toString().equals(form));
		assertEquals(15, stringTest.size());
	}

	/**
	 * Not implemented yet
	 */
//	@Test
//	public void cloningTest()
//	{
//		SplayTree<Integer> clone = new SplayTree<Integer>(stringTest);
//		System.out.println("cloning test");
//		System.out.println("Actual:");
//		System.out.println(clone + "\n");
//		System.out.println("Expected:");
//		System.out.println(stringTest + "\n");
//		
//		assertTrue(stringTest.toString().equals(clone.toString()));
//	}
	
	@Test
	public void addBSTtest()
	{
		System.out.println("tests the addBST method");
		//add when root is null and argument is null
		assertFalse(addTest.add(null));
		//add when root == null
		assertTrue(addTest.toString().equals("null"));
		assertEquals(0, addTest.size());
		assertTrue(addTest.addBST(9));
		assertEquals(1, addTest.size());
		assertEquals(new Integer(9), addTest.getRoot());
		String form2 = "9";
		System.out.println("addBST test");
		System.out.println("Actual:");
		System.out.println(addTest + "\n");
		System.out.println("Expected:");
		System.out.println(form2 +"\n");
		assertTrue(addTest.toString().equals(form2));
		
		//add with null argument
		assertFalse(addTest.addBST(null));
		
		//standard add
		assertTrue(addTest.addBST(12));
		assertEquals(2, addTest.size());
		form2 += "\n    null"
				+"\n    12";
		System.out.println("Actual:");
		System.out.println(addTest + "\n");
		System.out.println("Expected:");
		System.out.println(form2 +"\n");
		assertTrue(addTest.toString().equals(form2));
		
		//add already present element
		assertFalse(stringTest.addBST(9));
		System.out.println("Actual:");
		System.out.println(stringTest + "\n");
		System.out.println("Expected:");
		System.out.println(form +"\n");
		assertTrue(stringTest.toString().equals(form));
		assertEquals(15, stringTest.size());
	}
	
	@Test
	public void addTest()
	{
		String step1 = "50";
		String step2 = "30"
				   + "\n    null"
				   + "\n    50";
		String step3 = "60"
				   + "\n    50"
				   + "\n        30"
				   + "\n        null"
				   + "\n    null";
		String step4 = "70"
				   + "\n    60"
				   + "\n        50"
				   + "\n            30"
				   + "\n            null"
				   + "\n        null"
				   + "\n    null";
		String step5 = "55"
				   + "\n    50"
				   + "\n        30"
				   + "\n        null"
				   + "\n    70"
				   + "\n        60"
				   + "\n        null";
		String step6 = "52"
				   + "\n    50"
				   + "\n        30"
				   + "\n        null"
				   + "\n    55"
				   + "\n        null"
				   + "\n        70"
				   + "\n            60"
				   + "\n            null";
		String step7 = "54"
				   + "\n    52"
				   + "\n        50"
				   + "\n            30"
				   + "\n            null"
				   + "\n        null"
				   + "\n    55"
				   + "\n        null"
				   + "\n        70"
				   + "\n            60"
				   + "\n            null";
		String step8 = "40"
				   + "\n    30"
				   + "\n    52"
				   + "\n        50"
				   + "\n        54"
				   + "\n            null"
				   + "\n            55"
				   + "\n                null"
				   + "\n                70"
				   + "\n                    60"
				   + "\n                    null";
		String step9 = "20"
				   + "\n    null"
				   + "\n    30"
				   + "\n        null"
				   + "\n        40"
				   + "\n            null"
				   + "\n            52"
				   + "\n                50"
				   + "\n                54"
				   + "\n                    null"
				   + "\n                    55"
				   + "\n                        null"
				   + "\n                        70"
				   + "\n                            60"
				   + "\n                            null";
		String step10 = "35"
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
		String step11 = "33"
				    + "\n    30"
				    + "\n        20"
				    + "\n        null"
				    + "\n    35"
				    + "\n        null"
				    + "\n        40"
				    + "\n            null"
				    + "\n            52"
				    + "\n                50"
				    + "\n                54"
				    + "\n                    null"
				    + "\n                    55"
				    + "\n                        null"
				    + "\n                        70"
				    + "\n                            60"
				    + "\n                            null";
		String step12 = "25"
				    + "\n    20"
				    + "\n    33"
				    + "\n        30"
				    + "\n        35"
				    + "\n            null"
				    + "\n            40"
				    + "\n                null"
				    + "\n                52"
				    + "\n                    50"
				    + "\n                    54"
				    + "\n                        null"
				    + "\n                        55"
				    + "\n                            null"
				    + "\n                            70"
				    + "\n                                60"
				    + "\n                                null";
		String step13 = "27"
				    + "\n    25"
				    + "\n        20"
				    + "\n        null"
				    + "\n    30"
				    + "\n        null"
				    + "\n        33"
				    + "\n            null"
				    + "\n            35"
				    + "\n                null"
				    + "\n                40"
				    + "\n                    null"
				    + "\n                    52"
				    + "\n                        50"
				    + "\n                        54"
				    + "\n                            null"
				    + "\n                            55"
				    + "\n                                null"
				    + "\n                                70"
				    + "\n                                    60"
				    + "\n                                    null";
		String step14 = "10"
				    + "\n    null"
				    + "\n    27"
				    + "\n        20"
				    + "\n            null"
				    + "\n            25"
				    + "\n        30"
				    + "\n            null"
				    + "\n            33"
				    + "\n                null"
				    + "\n                35"
				    + "\n                    null"
				    + "\n                    40"
				    + "\n                        null"
				    + "\n                        52"
				    + "\n                            50"
				    + "\n                            54"
				    + "\n                                null"
				    + "\n                                55"
				    + "\n                                    null"
				    + "\n                                    70"
				    + "\n                                        60"
				    + "\n                                        null";
		String step15 = "33"
				    + "\n    10"
				    + "\n        null"
				    + "\n        30"
				    + "\n            27"
				    + "\n                20"
				    + "\n                    null"
				    + "\n                    25"
				    + "\n                null"
				    + "\n            null"
				    + "\n    35"
				    + "\n        null"
				    + "\n        40"
				    + "\n            null"
				    + "\n            52"
				    + "\n                50"
				    + "\n                54"
				    + "\n                    null"
				    + "\n                    55"
				    + "\n                        null"
				    + "\n                        70"
				    + "\n                            60"
				    + "\n                            null";
		String step16 = "27"
				    + "\n    10"
				    + "\n        null"
				    + "\n        20"
				    + "\n            null"
				    + "\n            25"
				    + "\n    33"
				    + "\n        30"
				    + "\n        35"
				    + "\n            null"
				    + "\n            40"
				    + "\n                null"
				    + "\n                52"
				    + "\n                    50"
				    + "\n                    54"
				    + "\n                        null"
				    + "\n                        55"
				    + "\n                            null"
				    + "\n                            70"
				    + "\n                                60"
				    + "\n                                null";
		//add when root == null and input is null
		assertFalse(addTest.add(null));
		//add when root == null
		assertTrue(addTest.add(50));
		assertEquals(1, addTest.size());
		assertEquals(new Integer(50), addTest.getRoot());
		System.out.println("regular add test");
		System.out.println("\nAdd 50");
		System.out.println("Actual:");
		System.out.println(addTest);
		System.out.println();
		System.out.println(step1);
		assertTrue(addTest.toString().equals(step1));
		
		//standard add when input is null
		assertFalse(addTest.add(null));
		
		//standard adds, test various rotations and size and root adjustment
		assertTrue(addTest.add(30));
		assertEquals(2, addTest.size());
		assertEquals(new Integer(30), addTest.getRoot());
		System.out.println("\nAdd 30");
		System.out.println("Actual:");
		System.out.println(addTest);
		System.out.println();
		System.out.println("Expected:");
		System.out.println(step2);
		assertTrue(addTest.toString().equals(step2));
		
		assertTrue(addTest.add(60));
		assertEquals(3, addTest.size());
		assertEquals(new Integer(60), addTest.getRoot());
		System.out.println("\nAdd 60");
		System.out.println("Actual:");
		System.out.println(addTest);
		System.out.println();
		System.out.println("Expected:");
		System.out.println(step3);
		assertTrue(addTest.toString().equals(step3));
		
		assertTrue(addTest.add(70));
		assertEquals(4, addTest.size());
		assertEquals(new Integer(70), addTest.getRoot());
		System.out.println("\nAdd 70");
		System.out.println("Actual:");
		System.out.println(addTest);
		System.out.println();
		System.out.println("Expected:");
		System.out.println(step4);
		assertTrue(addTest.toString().equals(step4));
		
		assertTrue(addTest.add(55));
		assertEquals(5, addTest.size());
		assertEquals(new Integer(55), addTest.getRoot());
		System.out.println("\nAdd 55");
		System.out.println("Actual:");
		System.out.println(addTest);
		System.out.println();
		System.out.println("Expected:");
		System.out.println(step5);
		assertTrue(addTest.toString().equals(step5));
		
		assertTrue(addTest.add(52));
		assertEquals(6, addTest.size());
		assertEquals(new Integer(52), addTest.getRoot());
		System.out.println("\nAdd 52");
		System.out.println("Actual:");
		System.out.println(addTest);
		System.out.println();
		System.out.println("Expected:");
		System.out.println(step6);
		assertTrue(addTest.toString().equals(step6));
		
		assertTrue(addTest.add(54));
		assertEquals(7, addTest.size());
		assertEquals(new Integer(54), addTest.getRoot());
		System.out.println("\nAdd 54");
		System.out.println("Actual:");
		System.out.println(addTest);
		System.out.println();
		System.out.println("Expected:");
		System.out.println(step7);
		assertTrue(addTest.toString().equals(step7));
		
		assertTrue(addTest.add(40));
		assertEquals(8, addTest.size());
		assertEquals(new Integer(40), addTest.getRoot());
		System.out.println("\nAdd 40");
		System.out.println("Actual:");
		System.out.println(addTest);
		System.out.println();
		System.out.println("Expected:");
		System.out.println(step8);
		assertTrue(addTest.toString().equals(step8));
		
		assertTrue(addTest.add(20));
		assertEquals(9, addTest.size());
		assertEquals(new Integer(20), addTest.getRoot());
		System.out.println("\nAdd 20");
		System.out.println("Actual:");
		System.out.println(addTest);
		System.out.println();
		System.out.println("Expected:");
		System.out.println(step9);
		assertTrue(addTest.toString().equals(step9));
		
		assertTrue(addTest.add(35));
		assertEquals(10, addTest.size());
		assertEquals(new Integer(35), addTest.getRoot());
		System.out.println("\nAdd 35");
		System.out.println("Actual:");
		System.out.println(addTest);
		System.out.println();
		System.out.println("Expected:");
		System.out.println(step10);
		assertTrue(addTest.toString().equals(step10));
		
		
		
		assertTrue(addTest.add(33));
//		assertEquals(11, addTest.size());
//		assertEquals(new Integer(33), addTest.getRoot());
//		System.out.println("\nAdd 33");
//		System.out.println("Actual:");
//		System.out.println(addTest);
//		System.out.println();
//		System.out.println("Expected:");
//		System.out.println(step11);
//		assertTrue(addTest.toString().equals(step11));
		
		
		
//		assertTrue(addTest.add(25));
//		assertEquals(12, addTest.size());
//		assertEquals(new Integer(25), addTest.getRoot());
//		System.out.println("\nAdd 25");
//		System.out.println("Actual:");
//		System.out.println(addTest);
//		System.out.println();
//		System.out.println("Expected:");
//		System.out.println(step12);
//		assertTrue(addTest.toString().equals(step12));
//		
//		assertTrue(addTest.add(27));
//		assertEquals(13, addTest.size());
//		assertEquals(new Integer(27), addTest.getRoot());
//		System.out.println("\nAdd 27");
//		System.out.println("Actual:");
//		System.out.println(addTest);
//		System.out.println();
//		System.out.println("Expected:");
//		System.out.println(step13);
//		assertTrue(addTest.toString().equals(step13));
//		
//		assertTrue(addTest.add(10));
//		assertEquals(14, addTest.size());
//		assertEquals(new Integer(10), addTest.getRoot());
//		System.out.println("\nAdd 10");
//		System.out.println("Actual:");
//		System.out.println(addTest);
//		System.out.println();
//		System.out.println("Expected:");
//		System.out.println(step14);
//		assertTrue(addTest.toString().equals(step14));
//		
//		assertFalse(addTest.add(33));
//		assertEquals(14, addTest.size());
//		assertEquals(new Integer(33), addTest.getRoot());
//		System.out.println("\nAdd 33");
//		System.out.println("Actual:");
//		System.out.println(addTest);
//		System.out.println();
//		System.out.println("Expected:");
//		System.out.println(step15);
//		assertTrue(addTest.toString().equals(step15));
//		
//		assertFalse(addTest.add(27));
//		assertEquals(14, addTest.size());
//		assertEquals(new Integer(27), addTest.getRoot());
//		System.out.println("\nAdd 27");
//		System.out.println("Actual:");
//		System.out.println(addTest);
//		System.out.println();
//		System.out.println("Expected:");
//		System.out.println(step16);
//		assertTrue(addTest.toString().equals(step16));
		
	}
	
//	@Test
//	public void containsTest()
//	{
//		System.out.println("Tests the 'contains(E data)' method and, by extension, the splay(E data) method");
//		
//		//test when root is null and input is null
//		assertFalse(addTest.contains(null));
//		
//		//test when root is null
//		assertFalse(addTest.contains(9));
//		
//		//test when input is null
//		assertFalse(stringTest.contains(null));
////		assertEquals(form, stringTest.toString());
//		
//		System.out.println("Before");
//		System.out.println(stringTest);
//		System.out.println();
//		
//		System.out.println("contains 2");
//		assertTrue(stringTest.contains(2));
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		String form2 = "2"
//				   + "\n    1"
//				   + "\n    8"
//				   + "\n        6"
//				   + "\n            4"
//				   + "\n                3"
//				   + "\n                5"
//				   + "\n            7"
//				   + "\n        14"
//				   + "\n            9"
//				   + "\n                null"
//				   + "\n                11"
//				   + "\n                    10"
//				   + "\n                    null"
//				   + "\n            18"
//				   + "\n                16"
//				   + "\n                23";
//		System.out.println("Expected:");
//		System.out.println(form2 + "\n");
////		assertTrue(stringTest.toString().equals(form2));
//		
//		System.out.println("contains 13");
//		assertFalse(stringTest.contains(13));
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form2 = "11"
//				   + "\n    8"
//				   + "\n        2"
//				   + "\n            1"
//				   + "\n            6"
//				   + "\n                4"
//				   + "\n                    3"
//				   + "\n                    5"
//				   + "\n                7"
//				   + "\n        9"
//				   + "\n            null"
//				   + "\n            10"
//				   + "\n    14"
//				   + "\n        null"
//				   + "\n        18"
//				   + "\n            16"
//				   + "\n            23";
//		System.out.println("Expected:");
//		System.out.println(form2 + "\n");
////		assertTrue(stringTest.toString().equals(form2));
//		
//	}
//	
//	@Test
//	public void removeTest()
//	{
//		System.out.println("testing the class's standard 'remove(E data)' method");
//		
//		//test when root and input are null
//		assertFalse(addTest.remove(null));
//		
//		//test when root is null
//		assertFalse(addTest.remove(9));
//		
//		//test when input is null
//		assertFalse(stringTest.remove(null));
//		assertEquals(form, stringTest.toString());
//		
//		assertTrue(stringTest.remove(14));
//		assertEquals(14, stringTest.size());
//		System.out.println("remove 14");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form = "11"
//				   + "\n    9"
//				   + "\n        8"
//				   + "\n            6"
//				   + "\n                1"
//				   + "\n                    null"
//				   + "\n                    2"
//				   + "\n                        null"
//				   + "\n                        4"
//				   + "\n                            3"
//				   + "\n                            5"
//				   + "\n                7"
//				   + "\n            null"
//				   + "\n        10"
//				   + "\n    18"
//				   + "\n        16"
//				   + "\n        23";
//		
//		System.out.println("Expected:");
//		System.out.println(form + "\n");
//		assertTrue(stringTest.toString().equals(form));
//		
//		assertTrue(stringTest.remove(8));
//		assertEquals(13, stringTest.size());
//		System.out.println("remove 8");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form = "9"
//				+ "\n    7"
//				+ "\n        6"
//				+ "\n            1"
//				+ "\n                null"
//				+ "\n                2"
//				+ "\n                    null"
//				+ "\n                    4"
//				+ "\n                        3"
//				+ "\n                        5"
//				+ "\n            null"
//				+ "\n        null"
//				+ "\n    11"
//				+ "\n        10"
//				+ "\n        18"
//				+ "\n            16"
//				+ "\n            23";
//		System.out.println("Expected:");
//		System.out.println(form + "\n");
//		assertTrue(stringTest.toString().equals(form));
//		
//		System.out.println("Expected:");
//		System.out.println(form + "\n");
//		assertTrue(stringTest.toString().equals(form));
//		//next
//		assertTrue(stringTest.remove(1));
//		assertEquals(12, stringTest.size());
//		System.out.println("remove 1");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form = "6"
//				+ "\n    2"
//				+ "\n        null"
//				+ "\n        4"
//				+ "\n            3"
//				+ "\n            5"
//				+ "\n    7"
//				+ "\n        null"
//				+ "\n        9"
//				+ "\n            null"
//				+ "\n            11"
//				+ "\n                10"
//				+ "\n                18"
//				+ "\n                    16"
//				+ "\n                    23";
//		System.out.println("Expected:");
//		System.out.println(form + "\n");
//		assertTrue(stringTest.toString().equals(form));
//		
//		//next
//		
//		
//		assertTrue(stringTest.remove(9));
//		assertEquals(11, stringTest.size());
//		System.out.println("remove 9");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form = "7"
//				+ "\n    6"
//				+ "\n        2"
//				+ "\n            null"
//				+ "\n            4"
//				+ "\n                3"
//				+ "\n                5"
//				+ "\n        null"
//				+ "\n    11"
//				+ "\n        10"
//				+ "\n        18"
//				+ "\n            16"
//				+ "\n            23";
//		System.out.println("Expected:");
//		System.out.println(form + "\n");
//		assertTrue(stringTest.toString().equals(form));
//		
//		//next
//		
//		
//		assertTrue(stringTest.remove(3));
//		assertEquals(10, stringTest.size());
//		System.out.println("remove 3");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form = "4"
//				+ "\n    2"
//				+ "\n    7"
//				+ "\n        6"
//				+ "\n            5"
//				+ "\n            null"
//				+ "\n        11"
//				+ "\n            10"
//				+ "\n            18"
//				+ "\n                16"
//				+ "\n                23";
//		System.out.println("Expected:");
//		System.out.println(form + "\n");
//		assertTrue(stringTest.toString().equals(form));
//		
//		//next
//		
//		
//		assertTrue(stringTest.remove(23));
//		assertEquals(9, stringTest.size());
//		System.out.println("remove 23");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form = "18"
//				+ "\n    4"
//				+ "\n        2"
//				+ "\n        11"
//				+ "\n            7"
//				+ "\n                6"
//				+ "\n                    5"
//				+ "\n                    null"
//				+ "\n                10"
//				+ "\n            16"
//				+ "\n    null";
//		System.out.println("Expected:");
//		System.out.println(form + "\n");
//		assertTrue(stringTest.toString().equals(form));
//		
//		stringTest.addBST(8);
//		System.out.println("BST add of 8");
//		System.out.println(stringTest + "\n");
//		
//		
//		
//		assertTrue(stringTest.remove(10));
//		assertEquals(9, stringTest.size());
//		System.out.println("remove 10");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form = "7"
//				+ "\n    4"
//				+ "\n        2"
//				+ "\n        6"
//				+ "\n            5"
//				+ "\n            null"
//				+ "\n    18"
//				+ "\n        11"
//				+ "\n            8"
//				+ "\n            16"
//				+ "\n        null";
//		System.out.println("Expected:");
//		System.out.println(form + "\n");
//		assertTrue(stringTest.toString().equals(form));
//		
//		//next
//		
//		
//		assertFalse(stringTest.remove(14));
//		assertEquals(9, stringTest.size());
//		System.out.println("remove 14");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form = "16"
//				+ "\n    7"
//				+ "\n        4"
//				+ "\n            2"
//				+ "\n            6"
//				+ "\n                5"
//				+ "\n                null"
//				+ "\n        11"
//				+ "\n            8"
//				+ "\n            null"
//				+ "\n    18";
//		System.out.println("Expected:");
//		System.out.println(form + "\n");
//		assertTrue(stringTest.toString().equals(form));
//		
//		//next
//		
//		
//		assertTrue(stringTest.remove(4));
//		assertEquals(8, stringTest.size());
//		System.out.println("remove 4");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form = "7"
//				+ "\n    2"
//				+ "\n        null"
//				+ "\n        6"
//				+ "\n            5"
//				+ "\n            null"
//				+ "\n    16"
//				+ "\n        11"
//				+ "\n            8"
//				+ "\n            null"
//				+ "\n        18";
//		System.out.println("Expected:");
//		System.out.println(form + "\n");
//		assertTrue(stringTest.toString().equals(form));
//		
//		assertFalse(addTest.remove(9));
//		addTest.add(9);
//		assertEquals(1, addTest.size());
//		assertEquals(new Integer(9), addTest.getRoot());
//		assertTrue(addTest.remove(9));
//		assertEquals(0, addTest.size());
//		try
//		{
//			assertTrue(addTest.getRoot() == null);
//			assertTrue(false);
//		}
//		catch(NullPointerException e)
//		{
//			assertTrue(true);
//		}
//	}
//	
//	@Test
//	public void findElementTest()
//	{
//		System.out.println("tests the 'findElement(E data)' method");
//		
//		//test when input and root are null
//		assertEquals(null, addTest.findElement(null));
//		
//		//test when root is null
//		assertEquals(null, addTest.findElement(9));
//		
//		//test when input is null
//		assertEquals(null, stringTest.findElement(null));
//		assertEquals(form, stringTest.toString());
//		
//		System.out.println("Before");
//		System.out.println(stringTest + "\n");
//		
//		assertEquals(null, stringTest.findElement(12));
//		System.out.println("find 12");
//		System.out.println("Actual");
//		System.out.println(stringTest + "\n");
//		form = "11"
//				+ "\n    9"
//				+ "\n        8"
//				+ "\n            6"
//				+ "\n                1"
//				+ "\n                    null"
//				+ "\n                    2"
//				+ "\n                        null"
//				+ "\n                        4"
//				+ "\n                            3"
//				+ "\n                            5"
//				+ "\n                7"
//				+ "\n            null"
//				+ "\n        10"
//				+ "\n    14"
//				+ "\n        null"
//				+ "\n        18"
//				+ "\n            16"
//				+ "\n            23";
//		System.out.println("Expected");
//		System.out.println(form + "\n");
//		assertTrue(stringTest.toString().equals(form));
//		
//		assertEquals(new Integer(18), stringTest.findElement(18));
//		System.out.println("find 18");
//		System.out.println("Actual");
//		System.out.println(stringTest + "\n");
//		form = "18"
//				+ "\n    14"
//				+ "\n        11"
//				+ "\n            9"
//				+ "\n                8"
//				+ "\n                    6"
//				+ "\n                        1"
//				+ "\n                            null"
//				+ "\n                            2"
//				+ "\n                                null"
//				+ "\n                                4"
//				+ "\n                                    3"
//				+ "\n                                    5"
//				+ "\n                        7"
//				+ "\n                    null"
//				+ "\n                10"
//				+ "\n            null"
//				+ "\n        16"
//				+ "\n    23";
//		System.out.println("Expected");
//		System.out.println(form + "\n");
//		assertTrue(stringTest.toString().equals(form));
//		
//	}
//	
//	@Test
//	public void iteratorTests()
//	{
//		System.out.println("tests the methods of the iterator");
//		Iterator<Integer> iter = addTest.iterator();
//		assertFalse(iter.hasNext());
//		try
//		{
//			iter.remove();
//			assertTrue(false);
//		}
//		catch(IllegalStateException e)
//		{
//			assertTrue(true);
//		}
//		try
//		{
//			iter.next();
//			assertTrue(false);
//		}
//		catch(NoSuchElementException e)
//		{
//			assertTrue(true);
//		}
//		
//		iter = stringTest.iterator();
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(1), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(2), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(3), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(4), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(5), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(6), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(7), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(8), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(9), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(10), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(11), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(14), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(16), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(18), iter.next());
//		
//		assertTrue(iter.hasNext());
//		assertEquals(new Integer(23), iter.next());
//		
//		assertFalse(iter.hasNext());
//		try
//		{
//			iter.next();
//			assertTrue(false);
//		}
//		catch(NoSuchElementException e)
//		{
//			assertTrue(true);
//		}
//		
//		addTest.add(9);
//		assertEquals(1, addTest.size());
//		assertEquals("9", addTest.toString());
//		
//		iter = addTest.iterator();
//		assertEquals(new Integer(9), iter.next());
//		iter.remove();
//		assertEquals(0, addTest.size());
//		assertEquals("null", addTest.toString());
//		
//		addTest.add(9);
//		addTest.addBST(7);
//		iter = addTest.iterator();
//		assertEquals(new Integer(7), iter.next());
//		iter.remove();
//		try
//		{
//			iter.remove();
//			assertTrue(false);
//		}
//		catch(IllegalStateException e)
//		{
//			assertTrue(true);
//		}
//		assertEquals(1, addTest.size());
//		assertEquals("9", addTest.toString());
//		
//		SplayTree<Integer> stringTest2 = new SplayTree<Integer>(stringTest);
//		iter = stringTest.iterator();
//		iter.next();
//		System.out.println("Before");
//		System.out.println(stringTest + "\n");
//		iter.remove();
//		System.out.println("iterator remove at 1");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		String form2 = "14"
//				   + "\n    8"
//				   + "\n        6"
//				   + "\n            2"
//				   + "\n                null"
//				   + "\n                4"
//				   + "\n                    3"
//				   + "\n                    5"
//				   + "\n            7"
//				   + "\n        9"
//				   + "\n            null"
//				   + "\n            11"
//				   + "\n                10"
//				   + "\n                null"
//				   + "\n    18"
//				   + "\n        16"
//				   + "\n        23";
//		System.out.println("Expected:");
//		System.out.println(form2 +"\n");
//		assertEquals(14, stringTest.size());
//		assertEquals(form2, stringTest.toString());
//		
//		while(iter.next() != 3)
//		{
//			
//		}
//		iter.remove();
//		System.out.println("iterator remove at 3");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form2 = "14"
//				   + "\n    8"
//				   + "\n        6"
//				   + "\n            2"
//				   + "\n                null"
//				   + "\n                4"
//				   + "\n                    null"
//				   + "\n                    5"
//				   + "\n            7"
//				   + "\n        9"
//				   + "\n            null"
//				   + "\n            11"
//				   + "\n                10"
//				   + "\n                null"
//				   + "\n    18"
//				   + "\n        16"
//				   + "\n        23";
//		System.out.println("Expected:");
//		System.out.println(form2 + "\n");
//		assertEquals(13, stringTest.size());
//		assertEquals(form2, stringTest.toString());
//		
//		iter.next();
//		iter.remove();
//		System.out.println("iterator remove at 4");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form2 = "14"
//				   + "\n    8"
//				   + "\n        6"
//				   + "\n            2"
//				   + "\n                null"
//				   + "\n                5"
//				   + "\n            7"
//				   + "\n        9"
//				   + "\n            null"
//				   + "\n            11"
//				   + "\n                10"
//				   + "\n                null"
//				   + "\n    18"
//				   + "\n        16"
//				   + "\n        23";
//		System.out.println("Expected:");
//		System.out.println(form2 + "\n");
//		assertEquals(12, stringTest.size());
//		assertEquals(form2, stringTest.toString());
//		
//		while(iter.next() != 9);
//		{
//			
//		}
//		iter.remove();
//		System.out.println("iterator remove at 9");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form2 = "14"
//				   + "\n    8"
//				   + "\n        6"
//				   + "\n            2"
//				   + "\n                null"
//				   + "\n                5"
//				   + "\n            7"
//				   + "\n        11"
//				   + "\n            10"
//				   + "\n            null"
//				   + "\n    18"
//				   + "\n        16"
//				   + "\n        23";
//		System.out.println("Expected:");
//		System.out.println(form2 + "\n");
//		assertEquals(11, stringTest.size());
//		assertEquals(form2, stringTest.toString());
//		
//		iter.next();
//		iter.next();
//		iter.remove();
//		System.out.println("iterator remove at 11");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form2 = "14"
//				   + "\n    8"
//				   + "\n        6"
//				   + "\n            2"
//				   + "\n                null"
//				   + "\n                5"
//				   + "\n            7"
//				   + "\n        10"
//				   + "\n    18"
//				   + "\n        16"
//				   + "\n        23";
//		System.out.println("Expected:");
//		System.out.println(form2 + "\n");
//		assertEquals(10, stringTest.size());
//		assertEquals(form2, stringTest.toString());
//		
//		stringTest = stringTest2;
//		stringTest2 = new SplayTree<Integer>(stringTest);
//		iter = stringTest.iterator();
//		stringTest.addBST(17);
//		System.out.println("the splay tree has been reset and received a BST add of 17");
//		System.out.println(stringTest + "\n");
//		form2 = "14"
//				   + "\n    8"
//				   + "\n        6"
//				   + "\n            1"
//				   + "\n                null"
//				   + "\n                2"
//				   + "\n                    null"
//				   + "\n                    4"
//				   + "\n                        3"
//				   + "\n                        5"
//				   + "\n            7"
//				   + "\n        9"
//				   + "\n            null"
//				   + "\n            11"
//				   + "\n                10"
//				   + "\n                null"
//				   + "\n    18"
//				   + "\n        16"
//				   + "\n            null"
//				   + "\n            17"
//				   + "\n        23";
//		assertEquals(form2, stringTest.toString());
//		
//		
//		while(iter.next() != 14)
//		{
//			
//		}
//		iter.remove();
//		System.out.println("iterator remove at 11");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form2 = "16"
//				   + "\n    8"
//				   + "\n        6"
//				   + "\n            1"
//				   + "\n                null"
//				   + "\n                2"
//				   + "\n                    null"
//				   + "\n                    4"
//				   + "\n                        3"
//				   + "\n                        5"
//				   + "\n            7"
//				   + "\n        9"
//				   + "\n            null"
//				   + "\n            11"
//				   + "\n                10"
//				   + "\n                null"
//				   + "\n    18"
//				   + "\n        17"
//				   + "\n        23";
//		System.out.println("Expected:");
//		System.out.println(form2 + "\n");
//		assertEquals(15, stringTest.size());
//		assertEquals(form2, stringTest.toString());
//		
//		stringTest = stringTest2;
//		stringTest2 = new SplayTree<Integer>(stringTest);
//		iter = stringTest.iterator();
//		System.out.println("the splay tree has been reset and the entire left subtree removed");
//		while(iter.next() != 14)
//		{
//			iter.remove();
//		}
//		System.out.println(stringTest + "\n");
//		form2 = "14"
//				   + "\n    null"
//				   + "\n    18"
//				   + "\n        16"
//				   + "\n        23";
//		assertEquals(form2, stringTest.toString());
//		iter = stringTest.iterator();
//		iter.next();
//		
//		iter.remove();
//		System.out.println("iterator remove at 14");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form2 = "18"
//				+ "\n    16"
//				+ "\n    23";
//		System.out.println("Expected:");
//		System.out.println(form2 + "\n");
//		assertEquals(3, stringTest.size());
//		assertEquals(form2, stringTest.toString());
//		
//		stringTest = stringTest2;
//		stringTest2 = new SplayTree<Integer>(stringTest);
//		iter = stringTest.iterator();
//		System.out.println("the splay tree has been reset and the entire right subtree removed");
//		while(iter.next() != 16)
//		{
//			
//		}
//		iter.remove();
//		iter.next();
//		iter.remove();
//		iter.next();
//		iter.remove();
//		System.out.println(stringTest + "\n");
//		form2 = "14"
//				   + "\n    8"
//				   + "\n        6"
//				   + "\n            1"
//				   + "\n                null"
//				   + "\n                2"
//				   + "\n                    null"
//				   + "\n                    4"
//				   + "\n                        3"
//				   + "\n                        5"
//				   + "\n            7"
//				   + "\n        9"
//				   + "\n            null"
//				   + "\n            11"
//				   + "\n                10"
//				   + "\n                null"
//				   + "\n    null";
//		assertEquals(form2, stringTest.toString());
//		iter = stringTest.iterator();
//		while(iter.next() != 14)
//		{
//			
//		}
//		
//		iter.remove();
//		System.out.println("iterator remove at 14");
//		System.out.println("Actual:");
//		System.out.println(stringTest + "\n");
//		form2 = "8"
//				   + "\n    6"
//				   + "\n        1"
//				   + "\n            null"
//				   + "\n            2"
//				   + "\n                null"
//				   + "\n                4"
//				   + "\n                    3"
//				   + "\n                    5"
//				   + "\n        7"
//				   + "\n    9"
//				   + "\n        null"
//				   + "\n        11"
//				   + "\n            10"
//				   + "\n            null";
//		System.out.println("Expected:");
//		System.out.println(form2 + "\n");
//		assertEquals(11, stringTest.size());
//		assertEquals(form2, stringTest.toString());
//		
//	}
	

}
