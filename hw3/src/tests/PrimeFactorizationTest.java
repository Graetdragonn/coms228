package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ListIterator;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw3.PrimeFactor;
import edu.iastate.cs228.hw3.PrimeFactorization;


class PrimeFactorizationTest {

	/**
	 * Test to confirm you cannot add a prime with a multiplicity less than 1
	 * {@link PrimeFactorization#add(int, int)}
	 */
	@Test
	void addLowMultiplicity() {
		PrimeFactorization pf = new PrimeFactorization();
		assertFalse(pf.add(24, 0));
	}
	
	/**
	 * Test adding an existing prime to the list.
	 */
	@Test
	void addTest1() {
		PrimeFactorization pf = new PrimeFactorization(25480);
		pf.add(2, 3);
		
		// Adding the a prime that already exists, so size shouldn't change
		assertEquals(4, pf.size());
		
		// Adding a prime that already exists, so multiplicity of existing prime should increase by m
		assertEquals("2^6 * 5 * 7^2 * 13", pf.toString());
	}
	
	/**
	 * Test adding a new prime and multiplicity to the list.
	 */
	@Test
	void addTest2() {
		PrimeFactorization pf = new PrimeFactorization(25480);
		
		pf.add(1, 4);
		
		// Adding the a prime that already exists, so size shouldn't change
		assertEquals(5, pf.size());
		
		// Adding a prime that already exists, so multiplicity of existing prime should increase by m
		assertEquals("1^4 * 2^3 * 5 * 7^2 * 13", pf.toString());
	}
	
	@Test
	void addTest3() {
		PrimeFactorization pf = new PrimeFactorization();
		pf.add(2, 2);
		
		assertEquals(1, pf.size());
		assertEquals(4, pf.value());
		assertEquals("2^2", pf.toString());
	}
	
	@Test
	void addTest4() {
		PrimeFactorization pf = new PrimeFactorization();
		
		pf.add(2, 3);
		pf.add(5, 1);
		pf.add(7, 2);
		pf.add(13, 1);
		
		ListIterator<PrimeFactor> iter = pf.iterator();
		
		// Initially, the cursor points to (2, 3) and index (0), so it has no previous
		assertFalse(iter.hasPrevious());
		
		// Next() actually returns this element
		// cursor now points to (5, 1)
		// index is now (1)
		assertEquals(2, iter.next().prime);

		assertTrue(iter.hasPrevious());
	}
	
	
	/**
	 * Clear the list of all nodes except the dummy node head and tail
	 * {@link PrimeFactorization#clearList()}
	 */
	@Test
	void clearListTest1() {
		PrimeFactorization pf = new PrimeFactorization(25480);
		
		pf.clearList();
		
		// Confirm the nodes are no longer there - visually
		assertEquals("dummy", pf.toString());
	}
	
	
	@Test
	void clearListTest2() {
		PrimeFactorization pf = new PrimeFactorization(25480);
		pf.clearList();
		
		assertEquals(0, pf.size());
	}

	
	/**
	 * IllegalArgumentCheck
	 */
	@Test
	void dividedBy1() {
		PrimeFactorization pf = new PrimeFactorization();
		assertThrows(IllegalArgumentException.class, () -> {
			pf.dividedBy(-1);
		});
	}
	
	
	
	/**
	 * Tests whether the empty constructor instantiates the size to zero
	 * {@link PrimeFactorization#PrimeFactorization()}
	 */
	@Test
	void emptyConstructionListSize() {
		PrimeFactorization pf = new PrimeFactorization();
		
		if (pf.size() != 0) {
			fail("Empty constructor for PrimeFactorization did not instantiate the size variable to zero.");
		}
	}
	
	/**
	 * Test empty constructor {@link PrimeFactorization#PrimeFactorization()}
	 */
	@Test
	void emptyConstructionTest() {
		try {
			PrimeFactorization pf = new PrimeFactorization();
		} catch(Exception e) {
			fail("Failed to construct empty PrimeFactorization");
		}
	}
	
	/**
	 * A series of tests for the Euclidean algorithm
	 */
	@Test
	void euclideanTest() {
		PrimeFactorization pf = new PrimeFactorization();
		assertEquals(23, pf.Euclidean(184, 69));
		assertEquals(6, pf.Euclidean(12, 42));
	}
	
	/**
	 * Get the GCD of a long number without having a PrimeFactorization of a number yet.
	 * This is done by calling the empty PrimeFactorization constructor without calling
	 * the constructor on a number.
	 */
	@Test
	void gcdLong1() {
//		PrimeFactorization pf = new PrimeFactorization();
//		assertThrows(IllegalArgumentException.class, () -> {
//			pf.gcd(24);
//		});
	}
	
	/**
	 * Tests the GCD output.  The greatest common divisor of 12 and 42 is 6, thus
	 * creating a PrimeFactorization(42) and finding its greatest common divisor with 12,
	 * should output the same string as PrimeFactorization(6).
	 * 
	 */
	@Test
	void gcdLong2() {
		PrimeFactorization pf = new PrimeFactorization(42);		
		assertEquals(new PrimeFactorization(6).toString(), pf.gcd(12).toString());
	}
	
	/**
	 * Confirms an IllegalArgumentException when attempting to find the GCD with zero
	 * as the divisor.
	 */
	@Test
	void gcdLong3() {
		PrimeFactorization pf = new PrimeFactorization(42);
		assertThrows(IllegalArgumentException.class, () -> {
			pf.gcd(0);
		});
	}
	
	
	@Test
	void insertionTest() {
		PrimeFactorization pf = new PrimeFactorization();
		pf.add(2, 2);
//		System.out.println(pf.toString());
//		assertEquals("2^2", pf.toString());
		
		assertEquals(1, pf.size());
	}
	
	
	
	/**
	 * Simply check a series of known prime numbers, if any fail to pass
	 * an error is thrown
	 * {@link PrimeFactorization#isPrime(long)}
	 */
	@Test
	void isPrime1() {
		// Short array of known prime numbers
		int[] knowns = {2, 3, 5, 7, 11, 13, 17, 19};
		
		for (int i = 0; i < knowns.length; i++) {
			assertTrue(PrimeFactorization.isPrime(knowns[i]));
		}
	}
	
	@Test
	void isPrime2() {
		assertTrue(PrimeFactorization.isPrime(99999990000001L));
		assertTrue(PrimeFactorization.isPrime(1999L));
	}
	
	/**
	 * Test for IllegalArgument when adding a PrimeFactor with a lower Prime than
	 * the Node at the cursor position
	 */
	@Test
	void iteratorAdd1() {
		PrimeFactorization pf = new PrimeFactorization();
		ListIterator<PrimeFactor> iter = pf.iterator();
		
		// Add a single PrimeFactor to the list
		iter.add(new PrimeFactor(7, 2));
		
		// Now attempt to add a PrimeFactor with a smaller prime
		assertThrows(IllegalArgumentException.class, () -> {
			iter.add(new PrimeFactor(2, 3));
		});
	}
	
	

	
	/**
	 * Tests the first two multiplier methods together since multiplier(long)
	 * calls multiplier(PrimeFactorization)
	 */
	@Test
	void multiplier1() {
		PrimeFactorization pf = new PrimeFactorization(25480);
		pf.multiply(405);
		
		assertEquals(405L*25480L, pf.value());
		assertEquals("2^3 * 3^4 * 5^2 * 7^2 * 13", pf.toString());
	}
	
	/**
	 * Multiply two prime numbers together should increase the multiplicity to 1
	 */
	@Test
	void multiplier2() {
		PrimeFactorization pf = new PrimeFactorization(13);
		pf.multiply(13);
		
		assertEquals("13^2", pf.toString());
	}
	
	/**
	 * Tests the value of two prime numbers being multiplied together.
	 * Meaning - the value was updated correctly
	 */
	@Test
	void multiplier3() {
		PrimeFactorization pf = new PrimeFactorization(13);
		pf.multiply(13);
		
		assertEquals(169, pf.value());
	}
	
	/**
	 * Tests multiplying two integers represented as PrimeFactorizations.
	 * One easy way to test this is to multiply two known primes, as PrimeFactorizations,
	 * together and compare the output.
	 */
	@Test
	void multiplier4() {
		PrimeFactorization pf = new PrimeFactorization();
		
		PrimeFactorization pf1 = new PrimeFactorization(13);
		PrimeFactorization pf2 = new PrimeFactorization(13);
		
		// Representing 13^2
		PrimeFactorization pf3 = new PrimeFactorization(169);
		
		assertEquals(pf3.toString(), pf.multiply(pf1, pf2).toString());
	}
	
	/**
	 * Test multiplication on two numbers that, when multiplied, would exceed a
	 * value captured by long
	 */
	@Test
	void multiplier5() {
		PrimeFactorization pf = new PrimeFactorization((long) Math.pow(2, 62));
		pf.multiply((long) Math.pow(2, 62));
		
		assertEquals("2^124", pf.toString());
	}
	
	/**
	 * Test multiplication on two numbers that, when multiplied, would exceed a
	 * value captured by long.  This should cause value to be set to -1
	 */
	@Test
	void multiplier6() {
		PrimeFactorization pf = new PrimeFactorization((long) Math.pow(2, 62));
		pf.multiply((long) Math.pow(2, 62));
		
		assertEquals(-1, pf.value());
	}
	
	/**
	 * Factorization tests on a series of numbers both prime and not
	 * {@link PrimeFactorization#PrimeFactorization(long)}
	 */
	@Test
	void primeFactorizationLong() {
		PrimeFactorization pf = new PrimeFactorization(6);
		assertEquals("2 * 3", pf.toString());
		
		// 31 is a prime number, which is divisible only by itself and 1.
		pf = new PrimeFactorization(31);
		assertEquals("31", pf.toString());
		
		pf = new PrimeFactorization(25480);		
		assertEquals("2^3 * 5 * 7^2 * 13", pf.toString());
		
		pf = new PrimeFactorization(5814);
		assertEquals( "2 * 3^2 * 17 * 19", pf.toString());
		
		pf = new PrimeFactorization(405);
		assertEquals( "3^4 * 5", pf.toString());
	}
	
	@Test
	void primeFactorizationLong1() {
		PrimeFactorization pf = new PrimeFactorization(25480);
		
		// Copy Constructor
		pf = new PrimeFactorization(pf);
		assertTrue(pf.value() == 25480);
		assertTrue(pf.size() == 4);
		
		ListIterator<PrimeFactor> iter = pf.iterator();
		PrimeFactor temp;
		temp = iter.next();
		assertTrue(temp.prime == 2 && temp.multiplicity == 3);
		temp = iter.next();
		assertTrue(temp.prime == 5 && temp.multiplicity == 1);
		temp = iter.next();
		assertTrue(temp.prime == 7 && temp.multiplicity == 2);
		temp = iter.next();
		assertTrue(temp.prime == 13 && temp.multiplicity == 1);
	}
	
//	@Test
//	void iteratePrimeFactorization() {
//		PrimeFactorization pf = new PrimeFactorization(25480);	
//		ListIterator<PrimeFactor> iter = pf.iterator();
//		
//		while(iter.hasNext()) {
//			System.out.println(iter.next().prime);
//		}
//		
//	}
	
	/**
	 * Tests a known PrimeFactorization that contains the given prime.
	 * Search for the prime in the list and confirm it is found
	 * {@link PrimeFactorization#containsPrimeFactor(int)}
	 */
	@Test
	void primeFactorizationContainsTest() {
		// TODO
	}
	
	
	
	
	/**
	 * Tests creation of a PrimeFactorization from an array.
	 * {@link PrimeFactorization#PrimeFactorization(PrimeFactor[])}
	 */
	@Test
	void primeFactorizationFromArray() {
		PrimeFactor[] pfList = new PrimeFactor[4];
		pfList[0] = new PrimeFactor(2, 3);
		pfList[1] = new PrimeFactor(5, 1);
		pfList[2] = new PrimeFactor(7, 2);
		pfList[3] = new PrimeFactor(13, 1);
		
		PrimeFactorization pf = new PrimeFactorization(pfList);
		
		// Test the string output is correct
		assertEquals( "2^3 * 5 * 7^2 * 13", pf.toString());
		
		// Try to get the original array back and do element-by-element comparison
		PrimeFactor[] returnArray = pf.toArray();
		
		for (int i = 0; i < pfList.length; i++) {
			if (!pfList[i].equals(returnArray[i])) {
				fail("Array from Prime Factorization's toArray() method did not yield the original array.");
			}
		}
		
	}
	
	
	/**
	 * 
	 */
	@Test
	void primeFactorizationClone() {
		// Create the first factorization
		PrimeFactorization pf1 = new PrimeFactorization(25480);
		
		// Clone the first factorization
		PrimeFactorization pf2 = new PrimeFactorization(pf1);
		
		// Not a great way, but first check their toString() method output
		assertEquals(pf2.toString(), pf1.toString());
	}
	
	@Test
	void primeFactorizationClone1() {
		// Create the first factorization
		PrimeFactorization pf1 = new PrimeFactorization(25480);
		
		// Clone the first factorization
		PrimeFactorization pf2 = new PrimeFactorization(pf1);
		
		// Not a great way, but first check their toString() method output
		assertEquals(pf1.value(), pf2.value());
	}
	
	/**
	 * Tests ability to call add to a list post-clear
	 */
//	@Test
//	void clearListThenAdd() {
//		// New PrimeFactorization
//		PrimeFactorization pf = new PrimeFactorization(25480);
//		
//		// Clear the list
//		pf.clearList();
//		
//		// Attempt to add
//		pf.add(2, 2);
//		
//		assertEquals(pf.toString(), "2^2");
//	}
	
	
	
	
	/**
	 * Tests that a new iterator is constructed with index of zero
	 */
	@Test
	void primeFactorizationIteratorConstruction() {
		PrimeFactorization pf = new PrimeFactorization();
		ListIterator<PrimeFactor> iter = pf.iterator();
		
		assertEquals(0, iter.nextIndex());
	}
	
	
	/**
	 * Tests remove a PrimeFactor from the doubly linked list
	 */
	@Test
	void removeTest1() {
		// Construct a doubly-linked list for n
		long n = 25480;
		PrimeFactorization pf = new PrimeFactorization(n);
		
		// The list should be
		// 2^3 * 5 * 7^2 * 13
		
		// Remove a 7^2
		pf.remove(7, 2);
		
		assertEquals("2^3 * 5 * 13", pf.toString());
		
		// Repeat for another number
		n = 5814;
		pf = new PrimeFactorization(n);
		pf.remove(3, 2);
		assertEquals("2 * 17 * 19", pf.toString());
	}
	
	
	@Test
	void removeTest2() {
		long n = 25480;
		PrimeFactorization pf = new PrimeFactorization(n);
		pf.remove(7, 2);
		
		// Original list: 2^3 * 5 * 7^2 * 13
		// New list     : 2^3 * 5 * 13
		
		// Check the new size of the list
		assertEquals(3, pf.size());
	}
	
	@Test
	void removeTest3() {
		PrimeFactorization pf = new PrimeFactorization(21);
		assertTrue(pf.remove(7, 1));
		assertEquals(3, pf.value());
		assertFalse(pf.remove(7, 1));
	}
	
	/**
	 * Uses helper method to check if the algorithm for updateValue() works
	 */
	@Test
	void updatedValueTest1() {
		// Constructor makes the doubly-linked list.
		long n = 25480;
		PrimeFactorization pf = new PrimeFactorization(n);
		
		// With the list in place, calling updateValue() and then value() should
		// return the same number you started with
		pf.checkUpdateValue();
		assertEquals(n, pf.value());
	}
	
	@Test
	void updatedValueTest2() {
		PrimeFactorization pf = new PrimeFactorization();
		pf.add(1, 2);
		
	}
	
//	/**
//	 *
//	 */
////	@Test
////	void updatedValueTest2() {
////		PrimeFactorization pf = new PrimeFactorization(1999L);
////		assertEquals("1999", pf.toString());		
////		assertEquals(1999L, pf.value());
////		assertTrue(pf.add(1999, 2));
////		
////		assertEquals(7988005999L, pf.value());
////		assertEquals("1999^3", pf.toString());
////		
////		// This is where things go wrong
////		assertTrue(pf.add(3, 5));	
////		assertEquals("3^5 * 1999^3", pf.toString());
////		System.out.println(pf.toString());
////		
//////		assertTrue(pf.add(5, 50));
//////		assertEquals(-1, pf.value());
////		
////		
////	}
}
