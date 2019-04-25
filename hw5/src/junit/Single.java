package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw5.AllCopiesRentedOutException;
import edu.iastate.cs228.hw5.Video;

class Single {
/**
	 * Returning more copies than are currently out sets the numCopiesRented = 0
	 */
	@Test
	void comparison3() {
		Video v1 = new Video("r");
		Video v2 = new Video("r 2");
		
		assertEquals(-1, v1.compareTo(v2));
	}
}