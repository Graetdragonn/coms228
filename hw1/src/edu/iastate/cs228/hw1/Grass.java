package edu.iastate.cs228.hw1;

import java.util.ArrayList;

/**
 *  
 * @author
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	public Grass (Plain p, int r, int c) 
	{
		// TODO 
		this.plain = p;
		this.row = r;
		this.column = c;
	}
	
	public State who()
	{
		// TODO  
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
	 */
	public Living next(Plain pNew)
	{
		// Get the neighborhood
		ArrayList<Living> neighborhood = new ArrayList<Living>();
		neighborhood = this.plain.getNeighborhood(this.row, this.column);
		
		int grassCount = 0;
		int rabbitCount = 0;
		
		// Get the count of foxes and badgers
		for (int i = 0; i < neighborhood.size(); i++) {
			if (neighborhood.get(i).who() == State.GRASS) {
				grassCount += 1;
			}
			
			if (neighborhood.get(i).who() == State.RABBIT) {
				rabbitCount += 1;
			}
		}
		
		// Rule a - Return Empty if there are three times as many Rabbits as Grasses in the neighborhood
		if (rabbitCount >= (3 * grassCount)) {
			Empty e = new Empty(pNew, this.row, this.column);
			return(e);
		}
		
		// Rule b - Return Rabbit if there are three or more Rabbits in the neighborhood
		if (rabbitCount >= 3) {
			Rabbit r = new Rabbit(pNew, this.row, this.column, 0);
			return(r);
		}
		
		// Rule c - Return Grass
		Grass g = new Grass(pNew, this.row, this.column);
		return(g);
	}
}