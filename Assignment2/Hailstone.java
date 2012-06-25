/*
 * File: Hailstone.java
 * --------------------
 * This program is a stub for the Hailstone problem, which computes
 * Hailstone sequence described in Assignment #2.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {

	public void run() {
		int inputInt = readInt("Enter a non-zero integer: ");
		int calcInt;
		int count;
		if (inputInt == 0) println("You freaking FAIL!");
		else
		{
			for (count=0; inputInt!=1; count++)
			{
				if (inputInt % 2 != 0)
				{
					calcInt = (inputInt*3)+1;
					println(inputInt + " is odd, so I make 3n + 1: " + calcInt);
				}
				else
				{
					calcInt = inputInt/2;
					println(inputInt + " is even, so I take half: " + calcInt);
				}
				inputInt = calcInt;
			}
			println("The process took " + count + " steps to reach 1.");
		}
	}

}
