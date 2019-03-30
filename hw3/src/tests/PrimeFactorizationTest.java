package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

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
	 * Clear the list of all nodes except the dummy node head and tail
	 * {@link PrimeFactorization#clearList()}
	 */
	@Test
	void clearListTest() {
		PrimeFactorization pf = new PrimeFactorization(25480);
		
		pf.clearList();
		
		// Confirm the nodes are no longer there - visually
		assertEquals(pf.toString(), "");
		
		// Confirm the size of the list is now zero
		assertEquals(pf.size(), 0);		
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
		assertEquals(pf.Euclidean(184, 69), 23);
		assertEquals(pf.Euclidean(12, 42), 6);
	}
	
	
	/**
	 * Simply check a series of known prime numbers, if any fail to pass
	 * an error is thrown
	 * {@link PrimeFactorization#isPrime(long)}
	 */
	@Test
	void isPrimeTest() {
		// Array of known prime numbers
		int[] knowns = {2, 3, 5, 7, 11, 13, 17, 19};
		
		for (int i = 0; i < knowns.length; i++) {
			for (int j = 2; j < Math.sqrt(knowns[i]); j++) {
				if (knowns[i] % j == 0) {
					fail("isPrimeTest failed to confirm " + knowns[j] + " is a prime.");
				}
			}
		}
	}
	
	/**
	 * Factorization test of 25480 and 5814
	 * {@link PrimeFactorization#PrimeFactorization(long)}
	 */
	@Test
	void primeFactorizationLong() {
		// FIXME this entire test needs to be adjusted to confirm it creates 
		// the known PrimeFactorization of 25480
		PrimeFactorization pf = new PrimeFactorization(25480);		
		assertEquals(pf.toString(), "2^3 * 5 * 7^2 * 13");
		
		pf = new PrimeFactorization(5814);
		assertEquals(pf.toString(), "2 * 3^2 * 17 * 19");
	}
	
	
	@Test
	void iteratePrimeFactorization() {
		PrimeFactorization pf = new PrimeFactorization(25480);	
		ListIterator<PrimeFactor> iter = pf.iterator();
		
		while(iter.hasNext()) {
			System.out.println(iter.next().prime);
		}
		
	}
	
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
		assertEquals(pf.toString(), "2^3 * 5 * 7^2 * 13");
		
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
		assertEquals(pf1.toString(), pf2.toString());
		
		
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
		
		assertEquals(iter.nextIndex(), 0);
	}
	
	
	
}
