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
		
		int[] population = {0, 0, 0, 0, 0};
		super.census(population);
		
		// Rule b = Return Empty if no Grass in neighborhood
		if (population[3] == 0) {
			Empty e = new Empty(pNew, this.row, this.column);
			return(e);
		}
		
		// Rule c
		if (((population[0] + population[2]) >= population[4]) && (population[2] > population[0])) {
			Fox f = new Fox(pNew, this.row, this.column, 0);
			return(f);
		}
		
		// Rule d
		if (population[0] > population[4]) {
			Badger b = new Badger(pNew, this.row, this.column, 0);
			return(b);
		}
		
		
		// Rule e = Return older rabbit
		Rabbit r = new Rabbit(pNew, this.row, this.column, this.age + 1);
		return(r);
	}
}
