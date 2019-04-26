package edu.iastate.cs228.hw5;

/**
 * This is a superclass.
 * 
 * @author Brian Bates
 *
 */

public class Video implements Comparable<Video>
{
	private String film;           // film title for the video
	private int numCopies; 
	private int numRentedCopies; 
	
	/**
	 * 
	 * @param film
	 * @param n
	 * @throws IllegalArgumentException if copies <= 0
	 */
	public Video(String film, int n) throws IllegalArgumentException 
	{
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		
		this.film = film;
		this.numCopies = n;
		this.numRentedCopies = 0;		
	}
	
	/**
	 * Calls the other constructor, passing in numCopies = 1
	 * @param film
	 */
	public Video(String film)
	{
		this(film, 1); 
	}

	/**
	 * @return the name of the film
	 */
	public String getFilm()
	{
		return film; 
	}
	
	/**
	 * @return the number of copies of this film
	 */
	public int getNumCopies()
	{
		return numCopies; 
	}

	
	/**
	 * 
	 * @param n the number of copies to add
	 * @throws IllegalArgumentException if n <= 0
	 */
	public void addNumCopies(int n) throws IllegalArgumentException
	{
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		
		numCopies = numCopies + n;
	}
	
	/**
	 * @return returns the number of copies available to rent
	 */
	public int getNumAvailableCopies()
	{
		return numCopies - numRentedCopies; 
	}

	/**
	 * @return returns the number of rented copies
	 */
	public int getNumRentedCopies()
	{
		return numRentedCopies; 
	}

	
	/**
	 * Updates numRentedCopies.  If n + numRentedCopies > numCopies, sets numRentedCopies 
	 * to numCopies.  (In other words, rent out all the available copies.) 
	 * 
	 * @param n
	 * @throws IllegalArgumentException if n <= 0
	 * @throws AllCopiesRentedOutException if numRentedCopies == numCopies 
	 */
    public void rentCopies(int n) throws IllegalArgumentException, AllCopiesRentedOutException
    {
    	if (n <= 0) {
    		throw new IllegalArgumentException();
    	}
    	
    	if ((numRentedCopies + n) > numCopies) {
    		numRentedCopies = n;
    		throw new AllCopiesRentedOutException();
    	} else {
    		numRentedCopies = numRentedCopies + n;
    	}
    	
    }
    
    
    /**
     * Updates numRentedCopies.  If n > numRentedCopies, set numRentedCopies to zero.  
     * 
     * @param n
     * @throws IllegalArgumentException if n <= 0  
     */
    public void returnCopies(int n) throws IllegalArgumentException
    {
    	if (n <= 0) {
    		throw new IllegalArgumentException();
    	}
    	
    	if (n > numRentedCopies) {
    		numRentedCopies = 0;
    	} else {
    		// Update the number of rented copies
        	numRentedCopies = numRentedCopies - n;
    	}    	
    	
    }
	

    /**
	 * Compares two videos by name using string comparison.
	 * @param a Video object to compare this object's film to 
	 * @return -1 if this film is alphabetically before vd
	 *          0 if the two films have the same title
	 *          1 if alphabetically this film comes after vd
	 */
	public int compareTo(Video vd)
	{
		int returnValue = 0;
		
		String v1Title = this.film;
		String v2Title = vd.film;
		
		int v1Length = this.film.length();
		int v2Length = vd.film.length();
		
		// Add blank spaces to the end of the first film
		if (v1Length < v2Length) {
			int diff = v2Length - v1Length;
			v1Title = addSpacesToFilm(v1Title, diff);
		}
		
		// Add blank spaces to the end of the second film
		if (v1Length > v2Length) {
			int diff = v1Length - v2Length;
			v2Title = addSpacesToFilm(v2Title, diff);
		}
		
		// Reset the lengths (they should match at this point)
		v1Length = v1Title.length();
		v2Length = v2Title.length();
		
		for (int i = 0; i < v1Length; i++) {
			// First case, this film is alphabetically BEFORE compared film
			if (v1Title.charAt(i) < v2Title.charAt(i)) {
				returnValue = -1;
				break;
			} else if (v1Title.charAt(i) == v2Title.charAt(i)) {
				returnValue = 0;
			} else {
				returnValue = 1;
				break;
			}
		}
		
		return returnValue;
	}
	
	/**
	 * Public for testing.  
	 * 
	 * Takes a String title in and adds n number of blank spaces
	 * 
	 * @param t
	 * @param n
	 * @return
	 */
	public String addSpacesToFilm(String t, int n) {
		String returnString = t;
		
		for (int i = 0; i < n; i++) {
			returnString = returnString + " ";
		}
		
		return returnString;
	}
	
	
	/**
	 * Write in the format "<film> (<numCopies>:<numRentedCopies>)", where every substring 
	 * in the form of a variable name delimited by a pair of angle brackets is replaced with the 
	 * value of the variable. For example, if a Video object has its private instance variables 
	 * take on the values below: 
	 * 
	 * film == "Forrest Gump" 
	 * numCopies == 2
	 * numRentedCopies == 1 
	 *  
	 * then the method returns the string "Forrest Gump (2:1)". 
	 */
	@Override 
	public String toString()
	{
		return film + " (" + numCopies + ":" + numRentedCopies + ")";
		
	}
}
