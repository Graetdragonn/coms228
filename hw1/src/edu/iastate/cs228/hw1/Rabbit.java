package edu.iastate.cs228.hw1;

import java.util.ArrayList;

/**
 *  
 * @author Brian Bates
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	
	
	/**
	 * Creates a Rabbit object.
	 * @param p: plain  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (Plain p, int r, int c, int a) 
	{
		// TODO
		this.plain = p;
		this.row = r;
		this.column = c;
		this.age = a;
	}
		
	// Rabbit occupies the square.
	public State who()
	{
		// TODO  
		return State.RABBIT; 
	}
	
	/**
	 * A rabbit dies of old age or hunger. It may also be eaten by a badger or a fox.  
	 * @param pNew     plain of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Plain pNew)
	{
		// Rule a - Return Empty if Rabbit is 3 years old 
		if (this.age == RABBIT_MAX_AGE) {
			Empty e = new Empty(pNew, this.row, this.column);
			return(e);
		}
		
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
		
		// Rule b = Return Empty if no Grass in neighborhood
		if (grassCount == 0) {
			Empty e = new Empty(pNew, this.row, this.column);
			return(e);
		}
		
		// Rule c
		if (((foxCount + badgerCount) >= rabbitCount) && (foxCount > badgerCount)) {
			Fox f = new Fox(pNew, this.row, this.column, 0);
			return(f);
		}
		
//		// Rule d
//		if (((foxCount + badgerCount) > rabbitCount) && (badgerCount > foxCount)) {
//			Badger b = new Badger(pNew, this.row, this.column, 0);
//			return(b);
//		}
		if (badgerCount > rabbitCount) {
			Badger b = new Badger(pNew, this.row, this.column, 0);
			return(b);
		}
		
		
		// Rule e = Return older rabbit
		Rabbit r = new Rabbit(pNew, this.row, this.column, this.age + 1);
		return(r);
	}
}
