package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw3.PrimeFactor;

class PrimeFactorTest {

	/**
	 * Test that the IllegalArgumentException is thrown for a multiplicity < 1
	 * {@link PrimeFactor#PrimeFactor(int, int)}
	 */
	@Test
	void testIllegalArgument() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PrimeFactor(5, 0));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PrimeFactor(5, -1));
	}

	
	/**
	 * Test output of toString() on a PrimeFactor with multiplicity of 1
	 * {@link PrimeFactor#toString()}
	 */
	@Test
	void toStringTest() {
		PrimeFactor pf = new PrimeFactor(5, 1);
		Assertions.assertEquals("5", pf.toString(), "A PrimeFactor with multiplicity of 1 should only output the prime factor when toString() is called.");
	}
	
	/**
	 * Test output of toString() with multiplicity > 1
	 * {@link PrimeFactor#toString()}
	 */
	@Test
	void toStringTest1() {
		PrimeFactor pf = new PrimeFactor(5, 2);
		Assertions.assertEquals("5^2", pf.toString(), "A PrimeFactor of 5 multiplicity 2 should output 5^2.");
	}
}
