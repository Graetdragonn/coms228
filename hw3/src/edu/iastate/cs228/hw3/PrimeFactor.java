package edu.iastate.cs228.hw3;

/**
 *  
 * @author Brian Bates
 *
 */

public class PrimeFactor 
{
	public int prime; 		 		// prime factor
	public int multiplicity; 		// number of times the prime factor appears in a factorization

	/**
	 * Precondition: p is a prime number.  
	 * 
	 * @param p	 prime
	 * @param m  multiplicity
	 * @throws IllegalArgumentException if m is less than 1 
	 */
	public PrimeFactor(int p, int m) throws IllegalArgumentException
	{
		// Throw exception if multiplicity is less than one
		if (m < 1) {
			throw new IllegalArgumentException();
		}
		
		this.prime = p;
		this.multiplicity = m;
 
	}

	/**
	 * Make a clone of the prime factor
	 */
	@Override
	public PrimeFactor clone() 
	{
		return new PrimeFactor(prime, multiplicity);
	}

	/**
	 * Prints out, for instance "2^3" if prime == 2 and multiplicity == 3, or "5" if 
	 * prime == 5 and multiplicity == 1.
	 */
	@Override
	public String toString() 
	{
		if (multiplicity == 1) {
			return(Integer.toString(prime));
		} else {
			return(Integer.toString(prime) + "^" + Integer.toString(multiplicity));
		}
	}
	
	/**
	 * Compare a PrimeFactor object with this one
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
		
		PrimeFactor pf = (PrimeFactor) obj;
		return(pf.prime == this .prime && pf.multiplicity == this.multiplicity);
		
	}
}
