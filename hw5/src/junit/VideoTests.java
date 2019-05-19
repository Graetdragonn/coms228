package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw5.AllCopiesRentedOutException;
import edu.iastate.cs228.hw5.Video;

class VideoTests {
	
	/**
	 * Comparing alphabetical movie titles
	 */
	@Test
	void comparison1() {
		Video v1 = new Video("War Games");
		Video v2 = new Video("Eagle Eye");
		
		assertEquals(1, v1.compareTo(v2));
	
		v1 = new Video("Eagle Eye");
		v2 = new Video("War Games");
		
		assertEquals(-1, v1.compareTo(v2));
		
		v1 = new Video("Chappie");
		v2 = new Video("Xandar");
		
		assertEquals(-1, v1.compareTo(v2));
	}
	
	/**
	 * Tests that video quantity is irrelevant
	 */
	@Test
	void comparison2() {
		Video v1 = new Video("War Games", 1);
		Video v2 = new Video("War Games", 2);
		
		assertEquals(0, v1.compareTo(v2));
	}
	
	/**
	 * Special cases
	 */
	@Test
	void comparison3() {
		Video v1 = new Video("The Godfather");
		Video v2 = new Video("The Godfather 2");		
		assertEquals(-1, v1.compareTo(v2));
		assertEquals(1, v2.compareTo(v1));
		
		v1 = new Video("The Godfather");
		v2 = new Video("The Godtather II");
		assertEquals(-1, v1.compareTo(v2));
		assertEquals(1, v2.compareTo(v1));
		
		v1 = new Video("The Godfather");
		v2 = new Video("The Godfather");
		assertEquals(0, v1.compareTo(v2));
		assertEquals(0, v2.compareTo(v1));
	}
	
	/**
	 * Throws an IllegalArgumentException because the number of copies is <= 0
	 */
	@Test
	void constructor1() {
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Video v = new Video("Hackers", 0);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Video v = new Video("Hackers", -1);
		});
	}
	
	/**
	 * Checks the constructor properly sets the film name and the number of copies to 1.
	 */
	@Test
	void constructor2() {
		Video v = new Video("Wall-E");
		assertEquals("Wall-E", v.getFilm());
		assertEquals(1, v.getNumCopies());
		assertEquals(1, v.getNumAvailableCopies());
	}
	
	/**
	 * Checks the constructed video object returns the proper name
	 */
	@Test
	void getFilmTest() {
		Video v = new Video("Antitrust", 1);
		assertEquals("Antitrust", v.getFilm());
	}
	
	/**
	 * Checks the constructed video object returns the proper number of total copies
	 */
	@Test
	void getNumCopiesTest1() {
		Video v = new Video("Sneakers", 10);
		assertEquals(10, v.getNumCopies());
	}

	/**
	 * Tests the number of total copies after adding to total
	 */
	@Test
	void getNumCopiesTest2() {
		Video v = new Video("Sneakers", 5);
		
		// Add to the number of total copies
		v.addNumCopies(5);
		assertEquals(10, v.getNumCopies());
	}
	
	/**
	 * Expects an IllegalArgumentException is thrown for trying to add n <= 0 copies
	 */
	@Test
	void getNumCopiesTest3() {
		Video v = new Video("Sneakers", 5);
		
		assertThrows(IllegalArgumentException.class, () -> {
			v.addNumCopies(0);
		});
				
	}
	
	/**
	 * Tests that the number of available copies before renting any is equal to the number
	 * set by the constructor.  Almost a duplicate of constructor2()
	 */
	@Test
	void getNumCopiesTest4() {
		Video v = new Video("Sneakers", 5);
		assertEquals(5, v.getNumCopies());
		assertEquals(5, v.getNumAvailableCopies());
		assertEquals(0, v.getNumRentedCopies());
	}
	
	/**
	 * Throws IllegalArgumentException because you're trying to rent n <= 0 number of copies
	 */
	@Test
	void rentCopies1() {
		Video v = new Video("The Italian Job");
		
		assertThrows(IllegalArgumentException.class, () -> {
			v.rentCopies(0);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			v.rentCopies(-1);
		});
	}
	
	/**
	 * Throws exception because the default constructor only provides 1 copy of the film
	 */
	@Test
	void rentCopies2() {
		Video v = new Video("The Italian Job");
		
		assertThrows(AllCopiesRentedOutException.class, () -> {
			v.rentCopies(2);
		});
	}
	
	/**
	 * Tests that the number of copies is decreased by one when renting one copy
	 * @throws AllCopiesRentedOutException if renting more copies than available
	 */
	@Test
	void rentCopies3() throws AllCopiesRentedOutException {
		Video v = new Video("The Italian Job");
		
		// Rent the only available copy
		v.rentCopies(1);
		
		// Should not have any copies available now
		assertEquals(0, v.getNumAvailableCopies());		
	}
	
	/**
	 * Duplicate test of rentCopies3() except more copies are initially availble
	 * @throws AllCopiesRentedOutException
	 */
	@Test
	void rentCopies4() throws AllCopiesRentedOutException {
		Video v = new Video("The Italian Job", 5);
		
		// Rent all copies again
		v.rentCopies(5);
		
		// Should not have any copies available now
		assertEquals(0, v.getNumAvailableCopies());	
	}
	
	/**
	 * An exception is thrown for trying to return n <= 0 copies
	 */
	@Test
	void returnCopies1() throws IllegalArgumentException, AllCopiesRentedOutException {
		Video v = new Video("Live Free or Die Hard");
		v.rentCopies(1);
		
		assertThrows(IllegalArgumentException.class, () -> {
			v.returnCopies(0);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			v.returnCopies(-1);
		});
	}
	
	/**
	 * Returning more copies than are currently out sets the numCopiesRented = 0
	 */
	@Test
	void returnCopies2() throws AllCopiesRentedOutException {
		Video v = new Video("Live Free or Die Hard");
		v.rentCopies(1);
		assertEquals(1, v.getNumRentedCopies());
		assertEquals(0, v.getNumAvailableCopies());
		
		v.returnCopies(2);		
		assertEquals(0, v.getNumRentedCopies());
		assertEquals(1, v.getNumAvailableCopies());
	}
	
	
	
	/**
	 * Basic test of output, no rental or return operations performed
	 */
	@Test
	void toString1() {
		Video v = new Video("Swordfish");
		assertEquals("Swordfish (1:0)", v.toString());
	}
	
	/**
	 * Testing string output after a series of operations
	 */
	@Test
	void toString2() throws AllCopiesRentedOutException {
		Video v = new Video("Swordfish");
		v.addNumCopies(10);  // Total is 11
		assertEquals(11, v.getNumCopies());
		assertEquals(11, v.getNumAvailableCopies());
		assertEquals(0, v.getNumRentedCopies());
		assertEquals("Swordfish (11:0)", v.toString());
		
		v.rentCopies(10);
		assertEquals(11, v.getNumCopies());             // Doesn't change
		assertEquals(1, v.getNumAvailableCopies());	
		assertEquals(10, v.getNumRentedCopies());
		assertEquals("Swordfish (11:10)", v.toString());
		
		v.rentCopies(1);
		assertEquals(11, v.getNumCopies());             // Doesn't change
		assertEquals(0, v.getNumAvailableCopies());	
		assertEquals(11, v.getNumRentedCopies());
		assertEquals("Swordfish (11:11)", v.toString());
		
		v.returnCopies(10);
		assertEquals(11, v.getNumCopies());             // Doesn't change
		assertEquals(10, v.getNumAvailableCopies());	
		assertEquals(1, v.getNumRentedCopies());
		assertEquals("Swordfish (11:1)", v.toString());
		
		// Return too many copies
		v.returnCopies(2);
		assertEquals(11, v.getNumCopies());             // Doesn't change
		assertEquals(11, v.getNumAvailableCopies());	
		assertEquals(0, v.getNumRentedCopies());
		assertEquals("Swordfish (11:0)", v.toString());
	}
	
	
}
