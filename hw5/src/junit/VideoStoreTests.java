package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw5.Video;
import edu.iastate.cs228.hw5.VideoStore;

class VideoStoreTests {

	
	
	/**
	 * Read in a missing file throws an error
	 */
	@Test
	void readFile1()  {
		assertThrows(FileNotFoundException.class, () -> {
			VideoStore vs = new VideoStore("absc.txt");
		});
	}
	
	/**
	 * Load a list of videos into inventory and check output
	 */
	@Test
	void inventoryTest1() throws FileNotFoundException {
		VideoStore vs = new VideoStore("small.txt");
		
		String actual = vs.toString();
		
		// Visual output
		System.out.println(actual);
		
		String expected = "Hackers (2:0)\n" + 
						  "    null\n" +
						  "    War Games (3:0)\n" +
						  "        Swordfish (1:0)\n" +
						  "        null\n";
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Same tests as the first one, but manually enters the movies rather than read from a file
	 */
	@Test
	void inventoryTest2() throws FileNotFoundException {
		VideoStore vs = new VideoStore();
		vs.addVideo("War Games", 3);
		vs.addVideo("Swordfish", 1);
		vs.addVideo("Hackers", 2);
				
				
		String actual = vs.toString();
		
		// Visual output
		System.out.println(actual);
		
		String expected = "War Games (3:0)\n" + 
						  "    Swordfish (1:0)\n" +
						  "        Hackers (2:0)\n" +
						  "        null\n" +
						  "    null\n";
		
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
		
		String expected = "Hackers (1:0)\n";  
		
		assertEquals(expected, actual);
	}
	
	@SuppressWarnings("static-access")
	@Test
	void parseLineDetails() {
		VideoStore vs = new VideoStore();
		
		String movie = "Ratchet and Clank (10)";		
		assertEquals("Ratchet and Clank", vs.parseFilmName(movie));
		assertEquals(10, vs.parseNumCopies(movie));
		
		movie = "Ratchet and Clank";		
		assertEquals("Ratchet and Clank", vs.parseFilmName(movie));
		assertEquals(1, vs.parseNumCopies(movie));
		
		movie = "Ratchet and Clank (-1)";		
		assertEquals("Ratchet and Clank", vs.parseFilmName(movie));
		assertEquals(1, vs.parseNumCopies(movie));
		
		movie = "Ratchet and Clank (0)";		
		assertEquals("Ratchet and Clank", vs.parseFilmName(movie));
		assertEquals(1, vs.parseNumCopies(movie));
		
		movie = "Ratchet and Clank 1 (0)";		
		assertEquals("Ratchet and Clank 1", vs.parseFilmName(movie));
		assertEquals(1, vs.parseNumCopies(movie));
		
		movie = "Ratchet and Clank 1 (10)";		
		assertEquals("Ratchet and Clank 1", vs.parseFilmName(movie));
		assertEquals(10, vs.parseNumCopies(movie));
		
		movie = "Ratchet and Clank 2 (-2)";		
		assertEquals("Ratchet and Clank 2", vs.parseFilmName(movie));
		assertEquals(1, vs.parseNumCopies(movie));
		
		// Mistakenly put an extra space at the end of the title.  I did this on accident
		// so I wanted to catch this just in case.
		movie = "Ratchet and Clank ";		
		assertEquals("Ratchet and Clank", vs.parseFilmName(movie));
		assertEquals(1, vs.parseNumCopies(movie));
		
		movie = "Ratchet and Clank\n";		
		assertEquals("Ratchet and Clank", vs.parseFilmName(movie));
		assertEquals(1, vs.parseNumCopies(movie));
	}
	
	
}
