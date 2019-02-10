package edu.iastate.cs228.hw1;

import java.util.ArrayList;

/**
 *  
 * @author Brian Bates
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (Plain p, int r, int c, int a) 
	{
		this.plain = p;
		this.row = r;
		this.column = c;
		this.age = a;
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		// TODO 
		return State.BADGER; 
	}
	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// Rule a - Return Empty if Badger is 4 years old 
		if (this.age == BADGER_MAX_AGE) {
			Empty e = new Empty(pNew, this.row, this.column);
			return(e);
		}
		
		int[] population = {0, 0, 0, 0, 0};
		super.census(population);
				
		// Rule b = Return Fox if there is only one Badger in the neighborhood, but more than one Fox
		if ((population[0] == 1) && (population[2] > population[0])) {
			Fox f = new Fox(pNew, this.row, this.column, 0);
			return(f);			
		}
		
		
		// Rule c = Return Empty if number of Badgers and Foxes together is greater than number of Rabbits (Hunger)
		if ((population[2] + population[0]) > population[4]) {
			Empty e = new Empty(pNew, this.row, this.column);
			return(e);
		}
		
		
		// Rule d = Return older badger
		Badger b = new Badger(pNew, this.row, this.column, this.age + 1);
		return(b);
		
	}
}
