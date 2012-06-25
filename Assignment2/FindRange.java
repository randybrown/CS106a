/*
 * File: FindRange.java
 * --------------------
 * This program is a stub for the FindRange problem, which finds the
 * smallest and largest values in a list of integers.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	public void run() {
		int inputInt;
		int maxInt;
		int minInt;
		println("This program finds the largest and smallest numbers. Enter 0 to stop.");
		inputInt = readInt("? ");
		if (inputInt == 0)
		{
			println("You didn't enter any numbers yet.");
		}
		else
		{
			maxInt = inputInt;
			minInt = inputInt;
			while (inputInt != 0)
			{
				inputInt = readInt("? ");
				if (inputInt == 0) break;
				else if (inputInt > maxInt) maxInt = inputInt;
				else if (inputInt < minInt) minInt = inputInt;
			}
			println("The highest number you entered is: " + maxInt);
			println("The lowest number you entered is: " + minInt);
		}
	}

}
