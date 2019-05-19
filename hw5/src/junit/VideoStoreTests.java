package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw5.AllCopiesRentedOutException;
import edu.iastate.cs228.hw5.FilmNotInInventoryException;
import edu.iastate.cs228.hw5.VideoStore;

class VideoStoreTests {

	/**
	 * Manually add a video and check the inventory data
	 */
	@Test
	void addVideo1() {
		VideoStore vs = new VideoStore();
		
		// Add a video
		vs.addVideo("WarGames", 3);
		
		assertEquals("WarGames (3:0)", vs.toString());
	}
	
	/**
	 * Manually enter a list of movies.  Note splaying of the last film added.
	 */
	@Test
	void addVideo2() throws FileNotFoundException {
		VideoStore vs = new VideoStore();
		vs.addVideo("WarGames", 3);
		vs.addVideo("Swordfish", 1);
		vs.addVideo("Hackers", 2);
				
				
		String actual = vs.toString();
		
		// Visual output
//		System.out.println(actual);
		
		String expected = "Hackers (2:0)\n" + 
				          "    null\n" +
						  "    Swordfish (1:0)\n" +
						  "        null\n" +
						  "        WarGames (3:0)";						  
						  
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Add a movie that already exists.  Updates the number of copies
	 */
	@Test
	void addVideo3() {
		VideoStore vs = new VideoStore();
		
		// Add a video
		vs.addVideo("WarGames", 3);
		vs.addVideo("WarGames", 3);
		
		assertEquals("WarGames (6:0)", vs.toString());
	}
	
	/**
	 * Add a movie with the method that doesn't have a numCopies argument.  Then add
	 * the movie again with the method that does. Updates the number of copies
	 */
	@Test
	void addVideo4() {
		VideoStore vs = new VideoStore();
		
		// Add a video
		vs.addVideo("WarGames");
		vs.addVideo("WarGames", 3);
		
		
		assertEquals("WarGames (4:0)", vs.toString());
	}
	
	@Test
	void addVideo5() {
		VideoStore vs = new VideoStore();
		vs.addVideo("");
		
		String expected = "Films in inventory:\n";
		
		assertEquals(expected, vs.inventoryList());
	}
	
	/**
	 * The video is not available in an empty store
	 */
	@Test
	void available1() {
		VideoStore vs = new VideoStore();
		
		assertFalse(vs.available("Happy Gilmore"));
	}
	
	/**
	 * The video is in the imported list
	 * @throws FileNotFoundException
	 */
	@Test
	void available2() throws FileNotFoundException {
		VideoStore vs = new VideoStore("small.txt");
		
		assertTrue(vs.available("Hackers"));
		assertTrue(vs.available("Swordfish"));
		assertTrue(vs.available("WarGames"));
	}
	
	/**
	 * The video is not in the imported list
	 */
	@Test
	void available3() throws FileNotFoundException {
		VideoStore vs = new VideoStore("small.txt");
		
		assertFalse(vs.available("Moon"));
	}
	
	/**
	 * Try to bulk import a file that does not exist
	 */
	@Test
	void bulkImport1() {
		VideoStore vs = new VideoStore();
		
		assertThrows(FileNotFoundException.class, () -> {
			vs.bulkImport("absc.txt");
		});
	}
	
	
	
	/**
	 * Tests the addition of new movies by bulkImport
	 * @throws FileNotFoundException
	 */
	@Test
	void bulkImport2() throws FileNotFoundException {
		VideoStore vs = new VideoStore("single.txt");  // Moon
		
		// Clears inventory before initializing
		vs.bulkImport("small.txt");
		
		String expected = "Moon (1:0)\n" +
						  "    Hackers (2:0)\n" + 
						  "    WarGames (3:0)\n" +
						  "        Swordfish (1:0)\n" +
						  "        null";
		
		assertEquals(expected, vs.toString());
	}
	
	/**
	 * FileNotFoundException thrown
	 */
	@Test
	void bulkRent1() {
		VideoStore vs = new VideoStore();
		
		assertThrows(FileNotFoundException.class, () -> {
			vs.bulkRent("abcs.txt");
		});
			
	}
	
	@Test
	void bulkRent2() throws FileNotFoundException, IllegalArgumentException, FilmNotInInventoryException, AllCopiesRentedOutException  {
		VideoStore vs = new VideoStore("inventoryList.txt");
		
		assertThrows(IllegalArgumentException.class, () -> {
			vs.bulkRent("batchRent1.txt");
		});
			
	}
	
	/**
	 * Gets the name of the film that exists in inventory
	 */
	@Test
	void findVideo1() {
		VideoStore vs = new VideoStore();
		vs.addVideo("WarGames", 6);
		
		assertEquals("WarGames (6:0)", vs.findVideo("WarGames").toString());
	}
	
	/**
	 * Searches for a film that does not exist in inventory
	 */
	@Test
	void findVideo2() {
		VideoStore vs = new VideoStore();
		vs.addVideo("WarGames");
		
		assertEquals(null, vs.findVideo("Cowboy Bebop"));
	}
	
	/**
	 * Video title is empty
	 */
	@Test
	void findVideo3() {
		VideoStore vs = new VideoStore();
		vs.addVideo("WarGames");
		
		assertEquals(null, vs.findVideo(""));
	}
	
	/**
	 * Video title is null
	 */
	@Test
	void findVideo4() {
		VideoStore vs = new VideoStore();
		vs.addVideo("WarGames");
		
		assertEquals(null, vs.findVideo(null));
	}
	
	/**
	 * Create a video store with no inventory and then print the inventory list
	 */
	@Test
	void inventoryList1() {
		VideoStore vs = new VideoStore();
		
		assertEquals("Films in inventory:\n", vs.inventoryList());
	}
	
	/**
	 * Expects the sample shown in the Javadoc
	 */
	@Test
	void inventoryList2() throws FileNotFoundException {
		VideoStore vs = new VideoStore("inventoryList.txt");
		
		String expected = "Films in inventory:\n" +				
				"\nA Streetcar Named Desire (1)" + 
				"\nBrokeback Mountain (1)" + 
				"\nForrest Gump (1)" + 
				"\nPsycho (1)" + 
				"\nSingin' in the Rain (2)" + 
				"\nSlumdog Millionaire (5)" + 
				"\nTaxi Driver (1)" + 
				"\nThe Godfather (1)";
		
		assertEquals(expected, vs.inventoryList());
	}
	
	/**
	 * Tests the printout when you load an empty file into inventory
	 * @throws FileNotFoundException
	 */
	@Test
	void intentoryList3() throws FileNotFoundException {
		VideoStore vs = new VideoStore("emptyList.txt");
		
		String expected = "Films in inventory:\n";
		assertEquals(expected, vs.inventoryList());
	}
	
	/**
	 * Load a list of videos into inventory and check output
	 */
	@Test
	void inventoryTest1() throws FileNotFoundException {
		VideoStore vs = new VideoStore("small.txt");
		
		String actual = vs.toString();
		
		// Visual output
//		System.out.println(actual);
		
		String expected = "Hackers (2:0)\n" + 
						  "    null\n" +
						  "    WarGames (3:0)\n" +
						  "        Swordfish (1:0)\n" +
						  "        null";
		
		assertEquals(expected, actual);
	}
	
	
	
	/**
	 * Same tests as the first one, but manually enters the movies rather than read from a file
	 */
	@Test
	void inventoryTest3() throws FileNotFoundException {
		VideoStore vs = new VideoStore("single.txt");
				
		String actual = vs.toString();
		
		// Visual output
//		System.out.println(actual);
		
		String expected = "Moon (1:0)";  
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Initializes inventory with one file and then calls setUpInventory with another.
	 * The contents of the original inventory should be gone.
	 * @throws FileNotFoundException
	 */
	@Test
	void inventoryTest4() throws FileNotFoundException {
		VideoStore vs = new VideoStore("single.txt");  // Moon
		
		// Clears inventory before initializing
		vs.setUpInventory("small.txt");
		
		String expected = "Hackers (2:0)\n" + 
						  "    null\n" +
						  "    WarGames (3:0)\n" +
						  "        Swordfish (1:0)\n" +
						  "        null";
		
		assertEquals(expected, vs.toString());
	}
	
	
	
	
//	@SuppressWarnings("static-access")
//	@Test
//	void parseLineDetails() {
//		VideoStore vs = new VideoStore();
//		
//		String movie = "Ratchet and Clank (10)";		
//		assertEquals("Ratchet and Clank", vs.parseFilmName(movie));
//		assertEquals(10, vs.parseNumCopies(movie));
//		
//		movie = "Ratchet and Clank";		
//		assertEquals("Ratchet and Clank", vs.parseFilmName(movie));
//		assertEquals(1, vs.parseNumCopies(movie));
//		
//		movie = "Ratchet and Clank (-1)";		
//		assertEquals("Ratchet and Clank -1", vs.parseFilmName(movie));
//		assertEquals(1, vs.parseNumCopies(movie));
//		
//		movie = "Ratchet and Clank (0)";		
//		assertEquals("Ratchet and Clank 1", vs.parseFilmName(movie));
//		assertEquals(1, vs.parseNumCopies(movie));
//		
//		movie = "Ratchet and Clank 1 (0)";		
//		assertEquals("Ratchet and Clank 1", vs.parseFilmName(movie));
//		assertEquals(1, vs.parseNumCopies(movie));
//		
//		movie = "Ratchet and Clank 1 (10)";		
//		assertEquals("Ratchet and Clank 1", vs.parseFilmName(movie));
//		assertEquals(10, vs.parseNumCopies(movie));
//		
//		movie = "Ratchet and Clank 2 (-2)";		
//		assertEquals("Ratchet and Clank -2", vs.parseFilmName(movie));
//		assertEquals(1, vs.parseNumCopies(movie));
//		
//		// Mistakenly put an extra space at the end of the title.  I did this on accident
//		// so I wanted to catch this just in case.
//		movie = "Ratchet and Clank ";		
//		assertEquals("Ratchet and Clank", vs.parseFilmName(movie));
//		assertEquals(1, vs.parseNumCopies(movie));
//		
//		movie = "Ratchet and Clank";		
//		assertEquals("Ratchet and Clank", vs.parseFilmName(movie));
//		assertEquals(1, vs.parseNumCopies(movie));
//	}
	
	
	
	/**
	 * Read in a missing file throws an error
	 */
	@Test
	void readFile1()  {
		assertThrows(FileNotFoundException.class, () -> {
			@SuppressWarnings("unused")
			VideoStore vs = new VideoStore("absc.txt");
		});
	}
	
	/**
	 * Return 0 or negative copies of a film
	 */
	@Test
	void returnVideo1() {
		VideoStore vs = new VideoStore();
		
		assertThrows(IllegalArgumentException.class, () -> {
			vs.videoReturn("Apollo 13", 0);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			vs.videoReturn("Apollo 13", -1);
		});
	}
	
	/**
	 * Return a film with no title
	 */
	@Test
	void returnVideo2() {
		VideoStore vs = new VideoStore();
		
		assertThrows(IllegalArgumentException.class, () -> {
			vs.videoReturn("", 1);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			vs.videoReturn(" ", 1);
		});
	}
	
	/**
	 * Return a video that is not in inventory
	 */
	@Test
	void returnVideo4() {
		VideoStore vs = new VideoStore();
		
		assertThrows(FilmNotInInventoryException.class, () -> {
			vs.videoReturn("Apollo 13", 3);
		});
	}
	
	/**
	 * Return more copies than were rented out
	 */
	@Test
	void returnVideo5() throws IllegalArgumentException, FilmNotInInventoryException, AllCopiesRentedOutException {
		VideoStore vs = new VideoStore();
		vs.addVideo("Moon", 5);
		vs.videoRent("Moon", 5);
		
		// Return more copies than were rented out
		vs.videoReturn("Moon", 6);
		
		// The number of available copies is the original number number of copies
		assertEquals(5, vs.findVideo("Moon").getNumAvailableCopies());
		assertEquals(0, vs.findVideo("Moon").getNumRentedCopies());
	}
	
	
	/**
	 * Return a few copies
	 */
	@Test
	void returnVideo6() throws IllegalArgumentException, FilmNotInInventoryException, AllCopiesRentedOutException {
		VideoStore vs = new VideoStore();
		vs.addVideo("Moon", 5);
		vs.videoRent("Moon", 5);
		
		// Return more copies than were rented out
		vs.videoReturn("Moon", 3);
		
		// The number of available copies is the original number number of copies
		assertEquals(3, vs.findVideo("Moon").getNumAvailableCopies());
		assertEquals(2, vs.findVideo("Moon").getNumRentedCopies());
	}
	
	/**
	 * Rent a negative or zero number of copies of a film
	 */
	@Test
	void rentVideo1() {
		VideoStore vs = new VideoStore();
		
		assertThrows(IllegalArgumentException.class, () -> {
			vs.videoRent("Aeon Flux", 0);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			vs.videoRent("Aeon Flux", -1);
		});
	}
	
	/**
	 * Rent a null film
	 */
	@Test
	void rentVideo2() {
		VideoStore vs = new VideoStore();
		
		assertThrows(IllegalArgumentException.class, () -> {
			vs.videoRent(null, 1);
		});
	}
	
	/**
	 * Rent a film without a title
	 */
	@Test
	void rentVideo3() {
		VideoStore vs = new VideoStore();
		
		assertThrows(IllegalArgumentException.class, () -> {
			vs.videoRent("", 1);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			vs.videoRent("  ", 1);
		});
	}
	
	/**
	 * Rent a video not in inventory
	 */
	@Test
	void rentVideo4() {
		VideoStore vs = new VideoStore();
		vs.addVideo("Apollo 18");
		
		assertThrows(FilmNotInInventoryException.class, () -> {
			vs.videoRent("Cowboy Bebop", 1);
		});
	}
	
	/**
	 * Rent a video that has no available copies
	 */
	@Test
	void rentVideo5() throws IllegalArgumentException, FilmNotInInventoryException, AllCopiesRentedOutException {
		VideoStore vs = new VideoStore();
		vs.addVideo("Cowboy Bebop", 3);
		vs.videoRent("Cowboy Bebop", 3);
		
		assertThrows(AllCopiesRentedOutException.class, () -> {
			vs.videoRent("Cowboy Bebop", 4);
		});
	}
	
	/**
	 * Rent all but one copy of a film then try and rent more than what's available
	 * 
	 * @throws IllegalArgumentException
	 * @throws FilmNotInInventoryException
	 * @throws AllCopiesRentedOutException
	 */
	@Test
	void rentVideo6() throws IllegalArgumentException, FilmNotInInventoryException, AllCopiesRentedOutException {
		VideoStore vs = new VideoStore();
		vs.addVideo("Cowboy Bebop", 3);
		
		// Rent two of the three copies
		vs.videoRent("Cowboy Bebop", 2);
		
		// Now try to rent another three copies (only 1 is available)
		vs.videoRent("Cowboy Bebop", 3);
		
		// The number of rented copies equals the total number of copies
		assertEquals(vs.findVideo("Cowboy Bebop").getNumCopies(), vs.findVideo("Cowboy Bebop").getNumRentedCopies());
		
		// Zero copies are available
		assertEquals(0, vs.findVideo("Cowboy Bebop").getNumAvailableCopies());
	}
	
	
	/**
	 * Transactions summary
	 */
	@Test
	void transactionSummary1() throws FileNotFoundException, IllegalArgumentException, FilmNotInInventoryException, AllCopiesRentedOutException {
		VideoStore vs = new VideoStore("small.txt");
		
		vs.videoRent("Hackers", 1);
		
		String expected = "Rented films:\n\n" +
						  "Hackers (1)\n\n" + 
						  "Films remaining in inventory:\n\n" + 
				          "Hackers (1)\n" + 
				          "Swordfish (1)\n"+
				          "WarGames (3)"; 
						  		
		assertEquals(expected, vs.transactionsSummary());
	}
	
	/**
	 * Transactions summary
	 */
//	@Test
//	void transactionSummary2() throws FileNotFoundException, IllegalArgumentException, FilmNotInInventoryException, AllCopiesRentedOutException {
//		VideoStore vs = new VideoStore("small.txt");
//		vs.videoRent("Hackers", 1);
//		vs.videoRent("Swordfish", 1);
//		vs.videoRent("Hackers", 1);
//		vs.videoReturn("Hackers", 2);
//		vs.videoReturn("Swordfish", 1);
//		vs.videoRent("Hackers", 1);
//				
//		String expected = "Rented films:\n\n" +
//						  "Hackers (1)\n\n" + 
//						  "Films remaining in inventory:\n\n" + 
//				          "Hackers (1)\n" + 
//				          "Swordfish (1)\n"+
//				          "WarGames (3)"; 
//						  		
//		assertEquals(expected, vs.transactionsSummary());
//	}
}
