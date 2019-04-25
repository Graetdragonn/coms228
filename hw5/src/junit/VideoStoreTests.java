package junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class VideoStoreTests {

	
	
	/**
	 * Read in a file
	 */
	@Test
	void readFile1() throws FileNotFoundException {
		Scanner read = new Scanner(new File("videoList1.txt"));
		
		while (read.hasNextLine()) {
			System.out.println(read.nextLine());
		}
	}
}
