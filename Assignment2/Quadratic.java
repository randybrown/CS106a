/*
 * File: Quadratic.java
 * --------------------
 * This program is a stub for the Pythagorean Theorem, which finds the
 * length of the hypotenuse of a right triangle.
 */

import acm.program.*;

public class Quadratic extends ConsoleProgram {

	public void run() {
		println("Enter values to compute the Pythagorean Theorem.");
		double a = readDouble("a:");
		double b = readDouble("b:");
		double c = Math.sqrt((a*a)+(b*b));
		println("c = " + c);
		
	}

}

