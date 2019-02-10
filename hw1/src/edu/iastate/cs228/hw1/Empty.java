package edu.iastate.cs228.hw1;

/**
 *  
 * @author Brian Bates
 *
 */

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	public Empty (Plain p, int r, int c) 
	{
		this.plain = p;
		this.row = r;
		this.column = c;
	}
	
	public State who()
	{
		return State.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or remain empty. 
	 * @param pNew     plain of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(Plain pNew)
	{
		int[] population = {0, 0, 0, 0, 0};
		super.census(population);
		
		// Rule a - Return Rabbit if more than one neighboring Rabbit
		if (population[4] > 1) {
			Rabbit r = new Rabbit(pNew, this.row, this.column, 0);
			return(r);
		}
		
		// Rule b - Return Fox if more than one neighboring Fox
		if (population[2] > 1) {
			Fox f = new Fox(pNew, this.row, this.column, 0);
			return(f);
		}
		
		// Rule c - Return Badger if more than one neighboring Badger
		if (population[0] > 1) {
			Badger b = new Badger(pNew, this.row, this.column, 0);
			return(b);
		}
		
		// Rule d - Return Grass if at least one neighboring Grass
		if (population[3] >= 1) {
			Grass g = new Grass(pNew, this.row, this.column);
			return(g);
		}
		
		// Rule e - Return Empty
		Empty e = new Empty(pNew, this.row, this.column);
		return(e);
	}
}
