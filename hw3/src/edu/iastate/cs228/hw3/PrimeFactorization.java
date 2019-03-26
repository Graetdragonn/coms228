package edu.iastate.cs228.hw3;

/**
 *  
 * @author Brian Bates
 *
 */

import java.util.ListIterator;

public class PrimeFactorization implements Iterable<PrimeFactor> {
	private static final long OVERFLOW = -1;
	
	private long value; 	// the factored integer 
							// it is set to OVERFLOW when the number is greater than 2^63-1, the
						    // largest number representable by the type long. 
	
	/**
	 * Reference to dummy node at the head.
	 */
	private Node head;
	  
	/**
	 * Reference to dummy node at the tail.
	 */
	private Node tail;
	
	private int size;     	// number of distinct prime factors


	// ------------
	// Constructors 
	// ------------
	
    /**
	 *  Default constructor constructs an empty list to represent the number 1.
	 *  
	 *  Combined with the add() method, it can be used to create a prime factorization.  
	 */
	public PrimeFactorization() 
	{	 
		// TODO link head to tail
		head = null;
		
		
		tail = null;
		size = 0;
	}

	
	/** 
	 * Obtains the prime factorization of n and creates a doubly linked list to store the result.   
	 * Follows the direct search factorization algorithm in Section 1.2 of the project description. 
	 * 
	 * @param n a factored integer
	 * @throws IllegalArgumentException if n is less than 1
	 */
	public PrimeFactorization(long n) throws IllegalArgumentException 
	{
		// Call the empty constructor first to create an empty list
		// and set the size = 0;
		this();
		
		if (n < 1) throw new IllegalArgumentException();
		
		
		// Initial assignment of value.
		value = n;		
		long orig_value = n;
		
		int multiplicity = 0;
		
		for (long i = 2; (i * i) < orig_value; i++) {
			// Check if i is prime before attempting division
			if (isPrime(i)) {
				// Loop to figure out the number of times this prime can divide the represented value
				while(true) {
					if (value % i == 0) {
						// Update value
						value = value / i;
						
						multiplicity++;
					} else {
						break;
					}
					
				}
				
				// If multiplicity does not equal zero, create a new Node
				if (multiplicity != 0) {
					// Add this prime and its multiplicity to the list
					add((int) i, (int) multiplicity);			
					
					
					// FIXME remove this
					System.out.println("Value: " + value + ", Prime: " + i + ", Multiplicity: " + multiplicity);
				}
				
				// Reset multiplicity for the next prime
				multiplicity = 0;				
			}
		}
		
	}
	
	
	/**
	 * Copy constructor. It is unnecessary to verify the primality of the numbers in the list.
	 * 
	 * @param pf is a PrimeFactorization object
	 */
	public PrimeFactorization(PrimeFactorization pf)
	{
		// TODO
	}
	
	/**
	 * Constructs a factorization from an array of prime factors.  Useful when the number is 
	 * too large to be represented even as a long integer. 
	 * 
	 * @param pflist is a list of PrimeFactor objects
	 */
	public PrimeFactorization (PrimeFactor[] pfList)
	{
		// TODO 
	}
	
	

	@Override
	public PrimeFactorizationIterator iterator()
	{
	    return new PrimeFactorizationIterator();
	}
	
	
	/**
	 * Lecture 20 has the details of ListIterator
	 * @author Brina Bates
	 *
	 */
    private class PrimeFactorizationIterator implements ListIterator<PrimeFactor>
	{  	
	    // Class invariants: 
	    // 1) logical cursor position is always between cursor.previous and cursor
	    // 2) after a call to next(), cursor.previous refers to the node just returned 
	    // 3) after a call to previous() cursor refers to the node just returned 
	    // 4) index is always the logical index of node pointed to by cursor
	
	    private Node cursor = head.next;
	    private Node pending = null;    // node pending for removal
	    private int index = 0;      
	
		// other instance variables ... 
		  
	
	    /**
		 * Default constructor positions the cursor before the smallest prime factor.
		 */
		public PrimeFactorizationIterator()
		{
			// TODO do I need to do anything here?  It says "positions the cursor before the smallest prime factor
			// but that is index 0 by default
		}
	
		@Override
		public boolean hasNext()
		{
			// TODO
			
			// FIXME
			return false; 
		}
	
		
		@Override
		public boolean hasPrevious()
		{
			// TODO
			
			// FIXME
			return false; 
		}
	
	
		@Override 
		public PrimeFactor next() 
		{
			// TODO
			
			
			// FIXME
			return null; 
		}
	
	
		@Override 
		public PrimeFactor previous() 
		{
			// TODO 
			
			// FIXME
			return null; 
		}
	
	
		/**
		 *  Removes the prime factor returned by next() or previous()
		 *  
		 *  @throws IllegalStateException if pending equals null 
		 */
		@Override
		public void remove() throws IllegalStateException
		{
			// TODO 
		}
	
	
		/**
		 * Adds a prime factor at the cursor position.  The cursor is at a wrong position 
		 * in either of the two situations below: 
		 * 
		 *    a) pf.prime < cursor.previous.pFactor.prime if cursor.previous != head. 
		 *    b) pf.prime > cursor.pFactor.prime if cursor != tail. 
		 * 
		 * Take into account the possibility that pf.prime == cursor.pFactor.prime. 
		 * 
		 * Precondition: pf.prime is a prime. 
		 * 
		 * @param pf is a PrimeFactor object
		 * @throws IllegalArgumentException if the cursor is at a wrong position. 
		 */
		@Override
	    public void add(PrimeFactor pf) throws IllegalArgumentException 
	    {
	    	// TODO 
	    }
	
	
		@Override
		public int nextIndex() 
		{
			return index;
		}
	
	
		@Override
		public int previousIndex() 
		{
			return index - 1;
		}
	
		@Deprecated
		@Override
		public void set(PrimeFactor pf) 
		{
			throw new UnsupportedOperationException(getClass().getSimpleName() + " does not support set method");
		}
	    
		// Other methods you may want to add or override that could possibly facilitate 
		// other operations, for instance, addition, access to the previous element, etc.
		// 
		// ...
		// 
	}


    // --------------
 	// Primality Test
 	// --------------
    
	/**
	 * Test if a number is a prime or not.  Check iteratively from 2 to the largest 
	 * integer not exceeding the square root of n to see if it divides n. 
	 * 
	 * This algorithm is inspired by "Cracking The Coding Interview (2015)"
	 * 
	 * @param n a factored integer
	 * @return true if n is a prime, false otherwise 
	 */
    public static boolean isPrime(long n) 
	{
	    // Since anything less than 1 is not a prime and by definition 1 is not a prime, return false
    	// Reference: Section 1 of the project documentation
    	if (n < 2) {
    		return false;
    	}
    	
    	// By definition, a prime number is only divisible by itself and 1, thus the core loop iterates
   	 	// from 2 through the sqrt(n).  If the division ever results in zero for a remainder, then n was
   	 	// divided evenly and thus is not a prime.
    	// 
    	// The documentation (1.2) asks you test d * d <= n, rather than d <= sqrt(n)
    	for (int i = 2; (i * i) <= n; i++) {
    		if (n % i == 0) {
    			return false;
    		}
    	}
    	
    	// If you made it this far, the number must have been a prime
		return true; 
	}   

   
	// ---------------------------
	// Multiplication and Division 
	// ---------------------------
	
	/**
	 * Multiplies the integer v represented by this object with another number n.  Note that v may 
	 * be too large (in which case this.value == OVERFLOW). You can do this in one loop: Factor n and 
	 * traverse the doubly linked list simultaneously. For details refer to Section 3.1 in the 
	 * project description. Store the prime factorization of the product. Update value and size. 
	 * 
	 * @param n a factored integer
	 * @throws IllegalArgumentException if n is less than 1
	 */
	public void multiply(long n) throws IllegalArgumentException 
	{
		// TODO
	}
	
	/**
	 * Multiplies the represented integer v with another number in the factorization form.  Traverse both 
	 * linked lists and store the result in this list object.  See Section 3.1 in the project description 
	 * for details of algorithm. 
	 * 
	 * @param pf is a PrimeFactor object
	 */
	public void multiply(PrimeFactorization pf)
	{
		// TODO
	}
	
	
	/**
	 * Multiplies the integers represented by two PrimeFactorization objects.  
	 * 
	 * @param pf1 is a PrimeFactor object
	 * @param pf2 is a PrimeFactor object
	 * @return object of PrimeFactorization to represent the product 
	 */
	public static PrimeFactorization multiply(PrimeFactorization pf1, PrimeFactorization pf2)
	{
		// TODO 
		return null; 
	}

	
	/**
	 * Divides the represented integer v by n.  Make updates to the list, value, size if divisible.  
	 * No update otherwise. Refer to Section 3.2 in the project description for details. 
	 *  
	 * @param n a factored integer
	 * @return  true if divisible 
	 *          false if not divisible 
	 * @throws IllegalArgumentException if n is less than or equal to 0
	 */
	public boolean dividedBy(long n) throws IllegalArgumentException
	{
		if (n <= 0) throw new IllegalArgumentException();
		
		// Per Section 3.2
		if (!(this.value < n)) {
			
			
			
		}
		// TODO 
		return false; 
	}

	
	/**
	 * Division where the divisor is represented in the factorization form.  Update the linked 
	 * list of this object accordingly by removing those nodes housing prime factors that disappear  
	 * after the division.  No update if this number is not divisible by pf. Algorithm details are 
	 * given in Section 3.2. 
	 * 
	 * @param pf is a PrimeFactor object
	 * @return	true if divisible by pf false otherwise
	 */
	public boolean dividedBy(PrimeFactorization pf)
	{
		// TODO 
		return false; 
	}

	
	/**
	 * Divide the integer represented by the object pf1 by that represented by the object pf2. 
	 * Return a new object representing the quotient if divisible. Do not make changes to pf1 and 
	 * pf2. No update if the first number is not divisible by the second one. 
	 *  
	 * @param pf1 is a PrimeFactor object
	 * @param pf2 is a PrimeFactor object
	 * @return quotient as a new PrimeFactorization object if divisible
	 *         null otherwise 
	 */
	public static PrimeFactorization dividedBy(PrimeFactorization pf1, PrimeFactorization pf2)
	{
		// TODO 
		return null; 
	}

	
	// -------------------------------------------------
	// Greatest Common Divisor and Least Common Multiple 
	// -------------------------------------------------

	/**
	 * Computes the greatest common divisor (gcd) of the represented integer v and an input integer n.
	 * Returns the result as a PrimeFactor object.  Calls the method Euclidean() if 
	 * this.value != OVERFLOW.
	 *     
	 * It is more efficient to factorize the gcd than n, which can be much greater. 
	 *     
	 * @param n a factored integer
	 * @return prime factorization of gcd
	 * @throws IllegalArgumentException if n is less than 1
	 */
	public PrimeFactorization gcd(long n) throws IllegalArgumentException
	{
		if (n < 1) throw new IllegalArgumentException();
		
		// TODO 
		return null; 
	}
	

	/**
	  * Implements the Euclidean algorithm to compute the gcd of two natural numbers m and n. 
	  * The algorithm is described in Section 4.1 of the project description. 
	  * 
	  * @param m
	  * @param n
	  * @return gcd of m and n. 
	  * @throws IllegalArgumentException if m is less than 1 or n is less than 1
	  */
 	public static long Euclidean(long m, long n) throws IllegalArgumentException
	{
 		if (m < 1 || n < 1) throw new IllegalArgumentException();
 		
 		// TODO 
 		return 0; 
	}

 	
	/**
	 * Computes the gcd of the values represented by this object and pf by traversing the two lists.  No 
	 * direct computation involving value and pf.value. Refer to Section 4.2 in the project description 
	 * on how to proceed.  
	 * 
	 * @param  pf
	 * @return prime factorization of the gcd
	 * @throws IllegalArgumentException if pf equals null
	 */
	public PrimeFactorization gcd(PrimeFactorization pf) throws IllegalArgumentException
	{
		if (pf == null) throw new IllegalArgumentException();
		
		// TODO 
		return null; 
	}
	
	
	/**
	 * 
	 * @param pf1
	 * @param pf2
	 * @return prime factorization of the gcd of two numbers represented by pf1 and pf2
	 * @throws IllegalArgumentException if pf1 equals null or pf2 equals null
	 */
	public static PrimeFactorization gcd(PrimeFactorization pf1, PrimeFactorization pf2) throws IllegalArgumentException
	{
		if (pf1 == null || pf2 == null) throw new IllegalArgumentException();
		
		// TODO 
		return null; 
	}

	
	/**
	 * Computes the least common multiple (lcm) of the two integers represented by this object 
	 * and pf.  The list-based algorithm is given in Section 4.3 in the project description. 
	 * 
	 * @param pf  
	 * @return factored least common multiple  
	 * @throws IllegalArgumentException if pf equals null
	 */
	public PrimeFactorization lcm(PrimeFactorization pf) throws IllegalArgumentException
	{
		if (pf == null) throw new IllegalArgumentException();
		
		// TODO 
		return null; 
	}

	
	/**
	 * Computes the least common multiple of the represented integer v and an integer n. Construct a 
	 * PrimeFactors object using n and then call the lcm() method above.  Calls the first lcm() method. 
	 * 
	 * @param n
	 * @return factored least common multiple 
	 * @throws IllegalArgumentException if n is less than 1
	 */
	public PrimeFactorization lcm(long n) throws IllegalArgumentException
	{
		if (n < 1) throw new IllegalArgumentException();
		
		// TODO 
		return null; 
	}

	/**
	 * Computes the least common multiple of the integers represented by pf1 and pf2. 
	 * 
	 * @param pf1
	 * @param pf2
	 * @return prime factorization of the lcm of two numbers represented by pf1 and pf2
	 * @throws IllegalArgumentException if pf1 equals null or pf2 equals null
	 */
	public static PrimeFactorization lcm(PrimeFactorization pf1, PrimeFactorization pf2) throws IllegalArgumentException
	{
		if (pf1 == null || pf2 == null) throw new IllegalArgumentException();
		
		// TODO 
		return null; 
	}

	
	// ------------
	// List Methods
	// ------------
	
	/**
	 * Traverses the list to determine if p is a prime factor. 
	 * 
	 * Precondition: p is a prime. 
	 * 
	 * @param p  
	 * @return true  if p is a prime factor of the number v represented by this linked list
	 *         false otherwise 
	 */
	public boolean containsPrimeFactor(int p) 
	{
		// TODO 
		return false; 
	}
	
	// The next two methods ought to be private but are made public for testing purpose. Keep
	// them public 
	
	/**
	 * Adds a prime factor p of multiplicity m.  Search for p in the linked list.  If p is found at 
	 * a node N, add m to N.multiplicity.  Otherwise, create a new node to store p and m. 
	 *  
	 * Precondition: p is a prime. 
	 * 
	 * @param p  prime 
	 * @param m  multiplicity
	 * @return   true  if m >= 1
	 *           false if m < 1   
	 */
    public boolean add(int p, int m) 
    {
    	// TODO: search for p in the linked list - containsPrimeFactor(int p)
    	if (containsPrimeFactor(p)) {
    		// TODO p is found at a node N, add to N.multiplicity
    	} else {
    		// TODO otherwise, create a new node to store p and m
    		// Construct a new PrimeFactor
    		PrimeFactor pf = new PrimeFactor(p, m);
    		
    		// Construct a new Node with the PrimeFactor as the Data
    		Node temp = new Node();
    		temp.pFactor = pf;
    		
    		// Add to the list
    		
    	}
    	
    	
    	// FIXME: fix this to return true when the method works
    	return false;
    	
    	// TODO: testing
    	// 1) Test when the list already contains the prime and that the multiplicity is iterated
    	// 2) Test that a new Node is created when the prime has not been found
    }

	    
    /**
     * Removes m from the multiplicity of a prime p on the linked list.  It starts by searching 
     * for p.  Returns false if p is not found, and true if p is found. In the latter case, let 
     * N be the node that stores p. If N.multiplicity > m, subtracts m from N.multiplicity.  
     * If N.multiplicity <= m, removes the node N.  
     * 
     * Precondition: p is a prime. 
     * 
     * @param p
     * @param m
     * @return true  when p is found. 
     *         false when p is not found. 
     * @throws IllegalArgumentException if m is less than 1
     */
    public boolean remove(int p, int m) throws IllegalArgumentException
    {
    	// TODO 
    	return false; 
    }


    /**
     * Accessor method to get the size of the doubly linked list
     * @return size of the list
     */
	public int size() 
	{
		return size; 
	}

	
	/**
	 * Writes out the list as a factorization in the form of a product. Represents exponentiation 
	 * by a caret.  For example, if the number is 5814, the returned string would be printed out 
	 * as "2 * 3^2 * 17 * 19". 
	 */
	@Override 
	public String toString()
	{
		// TODO: I think this should iterate over the list, calling the Node.toString() method
		//       and if there isNext(), add "*" 
		return null; 
	}

	
	// The next three methods are for testing, but you may use them as you like.  

	/**
	 * @return true if this PrimeFactorization is representing a value that is too large to be within 
	 *              long's range. e.g. 999^999. false otherwise.
	 */
	public boolean valueOverflow() {
		return value == OVERFLOW;
	}

	/**
	 * @return value represented by this PrimeFactorization, or -1 if valueOverflow()
	 */
	public long value() {
		// FIXME: unsure of this code.  Did I change this?
		return value;
	}

	/**
	 * Wow, thank you people who don't comment your code
	 * @return
	 */
	public PrimeFactor[] toArray() {
		PrimeFactor[] arr = new PrimeFactor[size];
		int i = 0;
		for (PrimeFactor pf : this)
			arr[i++] = pf;
		return arr;
	}


	
	
	
	
	/**
	 * Doubly-linked node type for this class.
	 */
    private class Node 
    {
		public PrimeFactor pFactor;			// prime factor 
		public Node next;
		public Node previous;
		
		/**
		 * Default constructor for creating a dummy node.
		 */
		public Node()
		{
			// TODO 
		}
	    
		/**
		 * Precondition: p is a prime
		 * 
		 * @param p	 prime number 
		 * @param m  multiplicity 
		 * @throws IllegalArgumentException if m is less than 1 
		 */
		public Node(int p, int m) throws IllegalArgumentException 
		{	
			// TODO 
			if (m < 1) {
				throw new IllegalArgumentException();
			}
		}   

		
		/**
		 * Constructs a node over a provided PrimeFactor object. 
		 * 
		 * @param pf is a PrimeFactor object
		 * @throws IllegalArgumentException
		 */
		public Node(PrimeFactor pf)  
		{
			// TODO 
		}


		/**
		 * Printed out in the form: prime + "^" + multiplicity.  For instance "2^3". 
		 * Also, deal with the case pFactor == null in which a string "dummy" is 
		 * returned instead.  
		 */
		@Override
		public String toString() 
		{
			if (this.pFactor == null) {
				return "dummy";
			} else {
				return(this.pFactor.toString());
			}
			
			
			
		}
    }

    
    

    
    // --------------
    // Helper methods 
    // -------------- 
    
    /**
     * Inserts Node toAdd into the list after current without updating size.
     * 
     * Precondition: current != null, toAdd != null
     */
    private void link(Node current, Node toAdd)
    {
    	// TODO 
    }

	 
    /**
     * Removes Node toRemove from the list without updating size.
     */
    private void unlink(Node toRemove)
    {
    	// TODO 
    }


    /**
	  * Remove all the nodes in the linked list except the two dummy nodes. 
	  * 
	  * Made public for testing purpose.  Ought to be private otherwise. 
	  * 
	  * The easy way to do this is to link head to tail (first unlink the linked nodes, 
	  * then link head to tail.  Garbage will delete the other nodes
	  */
	public void clearList()
	{
		// TODO  
	}	
	
	/**
	 * Multiply the prime factors (with multiplicities) out to obtain the represented integer.  
	 * Use Math.multiply(). If an exception is thrown, assign OVERFLOW to the instance variable value.  
	 * Otherwise, assign the multiplication result to the variable. 
	 * 
	 * I guess this method i
	 * 
	 */
	private void updateValue()
	{
		// TODO: I guess this method is used to factor the factorization?
		
		try {		
			// TODO		
		} 
			
		catch (ArithmeticException e) 
		{
			value = OVERFLOW;
		}
		
	}
}
