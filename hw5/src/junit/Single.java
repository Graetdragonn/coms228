package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw5.SplayTree;
import edu.iastate.cs228.hw5.Video;
import edu.iastate.cs228.hw5.VideoStore;

class Single {
	
	private Video v1 = new Video("Cowboy Bebop");
	private Video v2 = new Video("Ghost in the Shell");
	private Video v3 = new Video("Elysium");
	private Video v4 = new Video("Hackers");
	private Video v5 = new Video("Sneakers");
	private Video v6 = new Video("Antitrust");
	private Video v7 = new Video("The Matrix");
	private Video v8 = new Video("Aeon Flux");
	private Video v9 = new Video("Eagle Eye");
	private Video v10 = new Video("Chappie");
	private Video v11 = new Video("Wall-E");
	private Video v12 = new Video("Hogan's Heroes");
	private Video v13 = new Video("Xandar");
	private Video v14 = new Video("WarGames");
	private Video v15 = new Video("Moon");
	
	
	@Test
	void inventoryList2() throws FileNotFoundException {
//		VideoStore vs = new VideoStore("inventoryList.txt");
		VideoStore vs = new VideoStore("single.txt");
		
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
	

		

}