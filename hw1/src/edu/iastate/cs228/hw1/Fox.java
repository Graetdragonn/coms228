package edu.iastate.cs228.hw1;

/**
 *  
 * @author Brian Bates
 *
 */

/**
 * A fox eats rabbits and competes against a badger. 
 */
public class Fox extends Animal 
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Fox (Plain p, int r, int c, int a) 
	{
		this.plain = p;
		this.row = r;
		this.column = c;
		this.age = a;
	}
		
	/**
	 * A fox occupies the square. 	 
	 */
	public State who()
	{
		return State.FOX; 
	}
	
	/**
	 * A fox dies of old age or hunger, or from attack by numerically superior badgers. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// Rule a - Return Empty if Fox is 6 years old 
		if (this.age == FOX_MAX_AGE) {
			Empty e = new Empty(pNew, this.row, this.column);
			return(e);
		}
		
		int[] population = {0, 0, 0, 0, 0};
		super.census(population);
		
		
		// Rule b = Return Badger if there are more Badgers than Foxes
		if (population[0] > population[2]) {
			Badger b = new Badger(pNew, this.row, this.column, 0);
			return(b);
		}
		
		// Rule c = Return Empty if number of Badgers and Foxes together is greater than number of Rabbits (Hunger)
		if ((population[0] + population[2]) > population[4]) {
			Empty e = new Empty(pNew, this.row, this.column);
			return(e);
		}
		
		// Rule d = Return older fox
		Fox f = new Fox(pNew, this.row, this.column, this.age + 1);
		return(f);
	}
}
