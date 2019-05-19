package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw5.AllCopiesRentedOutException;
import edu.iastate.cs228.hw5.FilmNotInInventoryException;
import edu.iastate.cs228.hw5.SplayTree;
import edu.iastate.cs228.hw5.VideoStore;
import edu.iastate.cs228.hw5.Video;

class Simulations {

//	@Test
//	void simulation1() throws FileNotFoundException, IllegalArgumentException, FilmNotInInventoryException, AllCopiesRentedOutException {
//		SplayTree<Video> st = new SplayTree<Video>();
//		st.addBST(new Video("Hackers", 2)); 
//		st.addBST(new Video("WarGames", 3));
//		st.addBST(new Video("Swordfish"));
//		
//		System.out.println(st.toString());
//		System.out.println();
//		
//		Video v = st.findElement(new Video("Swordfish", 1));
//		v.rentCopies(1);
//		
//		System.out.println(st.toString());
//		System.out.println();
//		
//		v = st.findElement(new Video("Hackers"));
//		v.rentCopies(1);
//		
//		System.out.println(st.toString());
//		System.out.println();
//	}

	
	@Test
	void simulation2() throws FileNotFoundException, IllegalArgumentException, FilmNotInInventoryException, AllCopiesRentedOutException {
		VideoStore vs = new VideoStore("small.txt");
		
		System.out.println(vs.getRoot());
		System.out.println();
		
		System.out.println(vs.inventoryList());
		System.out.println();
		
		vs.videoRent("Swordfish", 1);
		
		System.out.println(vs.getRoot());
		System.out.println();
		
		System.out.println(vs.inventoryList());
		System.out.println();
		
//		System.out.println(st.toString());
//		System.out.println();
//		
//		v = st.findElement(new Video("Hackers"));
//		v.rentCopies(1);
//		
//		System.out.println(st.toString());
//		System.out.println();
	}
}
