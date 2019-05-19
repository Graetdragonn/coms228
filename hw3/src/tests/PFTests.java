package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ListIterator;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw3.PrimeFactor;
import edu.iastate.cs228.hw3.PrimeFactorization;

class PFTests {
	private static final long OVERFLOW = -1;

	@Test
	void testEmpty() {
		PrimeFactorization pf = new PrimeFactorization();
		assertTrue(pf.value() == 1);
		assertTrue(pf.size() == 0);
	}

	@Test
	void testLong() {
		PrimeFactorization pf = new PrimeFactorization(6);
		assertTrue(pf.value() == 6);
		assertTrue(pf.size() == 2);
		
		pf = new PrimeFactorization(25480);
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
	@Test
	void testPF() {
		PrimeFactorization pf = new PrimeFactorization(25480);
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
	@Test
	void testArray() {
		PrimeFactorization pf = new PrimeFactorization(25480);
		pf = new PrimeFactorization(pf.toArray());
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

	@Test
	void testIsPrime() {
		assertTrue(PrimeFactorization.isPrime(9999999900000001L));
		assertFalse(PrimeFactorization.isPrime(99999990000001L));
		assertTrue(PrimeFactorization.isPrime(2));
		assertFalse(PrimeFactorization.isPrime(1));
	}

	@Test
	void testMultiplyLong() {
		PrimeFactorization pf = new PrimeFactorization(420L);
		pf.multiply(69L);
		assertEquals(420L*69L, pf.value());
		pf.multiply(Long.MAX_VALUE);
		assertEquals(OVERFLOW,pf.value());
	}

	@Test
	void testMultiplyPrimeFactorization() {
		PrimeFactorization pf = new PrimeFactorization(420L);
		pf.multiply(new PrimeFactorization(69L));
		assertEquals(420L*69L, pf.value());
		pf.multiply(new PrimeFactorization(Long.MAX_VALUE));
		assertEquals(OVERFLOW,pf.value());
	}

	@Test
	void testMultiplyPrimeFactorizationPrimeFactorization() {
		PrimeFactorization pf = new PrimeFactorization(420L);
		PrimeFactorization pf2 = new PrimeFactorization(69L);
		assertEquals(420L * 69L, PrimeFactorization.multiply(pf, pf2).value());

		pf = new PrimeFactorization(4294967295L);
		pf2 = new PrimeFactorization(4294967295L);
		assertEquals(OVERFLOW, PrimeFactorization.multiply(pf, pf2).value());
	}

	@Test
	void testDividedByLong() {
		PrimeFactorization pf = new PrimeFactorization(420L);
		assertTrue(pf.dividedBy(420L));
		assertEquals(1, pf.value());
		assertFalse(pf.dividedBy(2L));
		pf = new PrimeFactorization(69L);
		assertTrue(pf.dividedBy(3L));
		assertEquals(23, pf.value());
		assertFalse(pf.dividedBy(3L));
		assertTrue(pf.dividedBy(23));
		assertEquals(1, pf.value());
		assertFalse(pf.dividedBy(2L));
		pf = new PrimeFactorization(420L);
		assertFalse(pf.dividedBy(80));
		assertEquals(420L, pf.value());
	}

	@Test
	void testDividedByPrimeFactorization() {
		PrimeFactorization pf = new PrimeFactorization(420L);
		assertTrue(pf.dividedBy(pf));
		assertEquals(1, pf.value());
		assertFalse(pf.dividedBy(new PrimeFactorization(2L)));
		pf = new PrimeFactorization(69L);
		assertTrue(pf.dividedBy(new PrimeFactorization(3L)));
		assertEquals(23, pf.value());
		assertFalse(pf.dividedBy(new PrimeFactorization(3L)));
		assertTrue(pf.dividedBy(new PrimeFactorization(23)));
		assertEquals(1, pf.value());
		assertFalse(pf.dividedBy(new PrimeFactorization(2L)));
		pf = new PrimeFactorization(420L);
		assertFalse(pf.dividedBy(new PrimeFactorization(80)));
		assertEquals(420L, pf.value());
	}

	@Test
	void testDividedByPrimeFactorizationPrimeFactorization() {
		PrimeFactorization pf = new PrimeFactorization(420L);
		assertEquals(1L, PrimeFactorization.dividedBy(pf, pf).value());
		assertNull(PrimeFactorization.dividedBy(pf, new PrimeFactorization(80)));
		PrimeFactorization pf2 = new PrimeFactorization(20L);
		assertEquals(21L, PrimeFactorization.dividedBy(pf, pf2).value());
		assertNull(PrimeFactorization.dividedBy(PrimeFactorization.dividedBy(pf, pf2), pf2));
		assertNull(PrimeFactorization.dividedBy(pf2, pf));
	}

	@Test
	void testGcdLong() {
		PrimeFactorization pf = new PrimeFactorization(420);
		PrimeFactorization pf1 = new PrimeFactorization(999);
		assertEquals(420, pf.gcd(420).value());
		assertEquals(1, pf1.gcd(3001934).value());
		assertEquals(27, pf1.gcd(3001941).value());
	}

	@Test
	void testEuclidean() {
		assertEquals(420, PrimeFactorization.Euclidean(420, 420*69));
		assertEquals(1, PrimeFactorization.Euclidean(999, 3001934));
		assertEquals(27, PrimeFactorization.Euclidean(999, 3001941));
		assertEquals(1, PrimeFactorization.Euclidean(1, 1));
	}

	@Test
	void testGcdPrimeFactorization() {
		PrimeFactorization pf = new PrimeFactorization(420);
		PrimeFactorization pf1 = new PrimeFactorization(999);
		PrimeFactorization pf2 = new PrimeFactorization(3001934);
		PrimeFactorization pf3 = new PrimeFactorization(3001941);
		assertEquals(420, pf.gcd(pf).value());
		assertEquals(1, pf1.gcd(pf2).value());
		assertEquals(27, pf1.gcd(pf3).value());
	}

	@Test
	void testGcdPrimeFactorizationPrimeFactorization() {
		PrimeFactorization pf = new PrimeFactorization(420);
		PrimeFactorization pf1 = new PrimeFactorization(999);
		PrimeFactorization pf2 = new PrimeFactorization(3001934);
		PrimeFactorization pf3 = new PrimeFactorization(3001941);
		assertEquals(420, PrimeFactorization.gcd(pf, pf).value());
		assertEquals(1, PrimeFactorization.gcd(pf1, pf2).value());
		assertEquals(27, PrimeFactorization.gcd(pf1, pf3).value());
	}

	@Test
	void testLcmPrimeFactorization() {
		PrimeFactorization pf = new PrimeFactorization(420);
		PrimeFactorization pf2 = new PrimeFactorization(30);
		PrimeFactorization pf3 = new PrimeFactorization(27);
		PrimeFactorization pf4 = new PrimeFactorization(2);
		PrimeFactorization pf5 = new PrimeFactorization(69);
		assertEquals(420, pf.lcm(pf2).value());
		assertEquals(420, pf.lcm(pf).value());
		assertEquals(690, pf2.lcm(pf5).value());
		assertEquals(3780, pf3.lcm(pf).value());
		assertEquals(9660, pf5.lcm(pf).value());
		assertEquals(138, pf4.lcm(pf5).value());
		assertEquals(138, pf5.lcm(pf4).value());
		assertEquals(2, pf4.lcm(pf4).value());
	}

	@Test
	void testLcmLong() {
		PrimeFactorization pf = new PrimeFactorization(420);
		PrimeFactorization pf2 = new PrimeFactorization(30);
		PrimeFactorization pf3 = new PrimeFactorization(27);
		PrimeFactorization pf4 = new PrimeFactorization(2);
		PrimeFactorization pf5 = new PrimeFactorization(69);
		assertEquals(420, pf.lcm(30).value());
		assertEquals(420, pf.lcm(420).value());
		assertEquals(690, pf2.lcm(69).value());
		assertEquals(3780, pf3.lcm(420).value());
		assertEquals(9660, pf5.lcm(420).value());
		assertEquals(138, pf5.lcm(2).value());
		assertEquals(138, pf4.lcm(69).value());
		assertEquals(2, pf4.lcm(2).value());
	}

	@Test
	void testLcmPrimeFactorizationPrimeFactorization() {
		PrimeFactorization pf = new PrimeFactorization(420);
		PrimeFactorization pf2 = new PrimeFactorization(30);
		PrimeFactorization pf3 = new PrimeFactorization(27);
		PrimeFactorization pf4 = new PrimeFactorization(2);
		PrimeFactorization pf5 = new PrimeFactorization(69);
		assertEquals(420, PrimeFactorization.lcm(pf2, pf).value());
		assertEquals(420, PrimeFactorization.lcm(pf, pf).value());
		assertEquals(690, PrimeFactorization.lcm(pf5, pf2).value());
		assertEquals(3780, PrimeFactorization.lcm(pf, pf3).value());
		assertEquals(9660, PrimeFactorization.lcm(pf, pf5).value());
		assertEquals(138, PrimeFactorization.lcm(pf5, pf4).value());
		assertEquals(138, PrimeFactorization.lcm(pf4, pf5).value());
		assertEquals(2, PrimeFactorization.lcm(pf4, pf4).value());
	}

	@Test
	void testContainsPrimeFactor() {//precondition p is a prime
		PrimeFactorization pf = new PrimeFactorization(27);
		PrimeFactorization pf2 = new PrimeFactorization(69);
		assertFalse(pf.containsPrimeFactor(420));
		assertFalse(pf.containsPrimeFactor(2));
		assertFalse(pf.containsPrimeFactor(0));
		assertTrue(pf.containsPrimeFactor(3));
		assertTrue(pf2.containsPrimeFactor(3));
		assertTrue(pf2.containsPrimeFactor(23));
	}

	@Test
	void testAdd() {//PRECONDITIOn, number to add is prime
		PrimeFactorization pf = new PrimeFactorization(21);
		assertTrue(pf.add(3, 1));
		assertEquals(63, pf.value());
		assertFalse(pf.add(3, -1));
	}

	@Test
	void testRemove() {//true if removed, false otherwise
		PrimeFactorization pf = new PrimeFactorization(21);
		assertTrue(pf.remove(7, 1));
		assertEquals(pf.value(), 3);
		assertFalse(pf.remove(7, 1));
	}

	@Test
	void testSize() {
		PrimeFactorization pf = new PrimeFactorization(1999L);
		assertEquals(1, pf.size());
		pf.add(1999, 2);
		assertEquals(1, pf.size());
		pf.add(3, 5);
		pf.add(5, 50);
		assertEquals(3, pf.size());
		pf.clearList();
		assertEquals(0, pf.size());
	}

	@Test
	void testToString() {
		PrimeFactorization pf = new PrimeFactorization(21);
		assertEquals(pf.toString(), "3 * 7");
		pf.add(5, 9);
		assertEquals(pf.toString(), "3 * 5^9 * 7");
	}

	@Test
	void testValue() {
		PrimeFactorization pf = new PrimeFactorization(1999L);
		assertEquals(1999L, pf.value());
		pf.add(1999, 2);
		assertEquals(7988005999L, pf.value());
		pf.add(3, 5);
		pf.add(5, 50);
		assertEquals(-1, pf.value());
		pf.clearList();
		assertEquals(1, pf.value());
	}

	@Test
	void testClearList() {
		PrimeFactorization pf = new PrimeFactorization(21);
		pf.clearList();
		assertEquals(1, pf.value());
		assertEquals(0, pf.size());
	}

}
