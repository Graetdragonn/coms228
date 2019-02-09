package edu.iastate.cs228.hw1;

import java.util.ArrayList;

/**
 *  
 * @author
 *
 */

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	public Empty (Plain p, int r, int c) 
	{
		// TODO  
		this.plain = p;
		this.row = r;
		this.column = c;
	}
	
	public State who()
	{
		// TODO 
		return State.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or remain empty. 
	 * @param pNew     plain of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(Plain pNew)
	{
		// Get the neighborhood
		ArrayList<Living> neighborhood = new ArrayList<Living>();
		neighborhood = this.plain.getNeighborhood(this.row, this.column);
		
		
		int rabbitCount = 0;
		int foxCount = 0;
		int badgerCount = 0;
		int grassCount = 0;
		
		// Get the counts
		for (int i = 0; i < neighborhood.size(); i++) {
			if (neighborhood.get(i).who() == State.BADGER) {
				badgerCount += 1;
			}
			
			if (neighborhood.get(i).who() == State.FOX) {
				foxCount += 1;
			}
			
			if (neighborhood.get(i).who() == State.RABBIT) {
				rabbitCount += 1;
			}
			
			if (neighborhood.get(i).who() == State.GRASS) {
				grassCount += 1;
			}
		}
		
		// Rule a - Return Rabbit if more than one neighboring Rabbit
		if (rabbitCount > 1) {
			Rabbit r = new Rabbit(pNew, this.row, this.column, 0);
			return(r);
		}
		
		// Rule b - Return Fox if more than one neighboring Fox
		if (foxCount > 1) {
			Fox f = new Fox(pNew, this.row, this.column, 0);
			return(f);
		}
		
		// Rule c - Return Badger if more than one neighboring Badger
		if (badgerCount > 1) {
			Badger b = new Badger(pNew, this.row, this.column, 0);
			return(b);
		}
		
		// Rule d - Return Grass if at least one neighboring Grass
		if (grassCount >= 1) {
			Grass g = new Grass(pNew, this.row, this.column);
			return(g);
		}
		
		// Rule e - Return Empty
		Empty e = new Empty(pNew, this.row, this.column);
		return(e);
	}
}
