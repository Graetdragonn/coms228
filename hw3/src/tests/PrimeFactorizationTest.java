package tests;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw3.PrimeFactorization;

class PrimeFactorizationTest {

	/**
	 * Simply check a series of known prime numbers, if any fail to pass
	 * an error is thrown
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
	 * My primary test
	 */
	@Test
	void primeFactorizationLong() {
		// FIXME this entire test needs to be deleted after proper implementation
		PrimeFactorization pf = new PrimeFactorization(25480);
	}
}
