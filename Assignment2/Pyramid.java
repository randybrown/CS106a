/*
 * File: Pyramid.java
 * ------------------
 * This program is a stub for the Pyramid problem, which draws
 * a brick pyramid.
 */

import acm.program.*;
import acm.graphics.*;

public class Pyramid extends GraphicsProgram
{
/* Pyramid is a GraphicsProgram which draws a pyramid using bricks. The logic behind this program,
 * explained further at each loop, is that it determines where the corner brick for each level is
 * to be placed, based on the number of bricks on the bottom level and the width of the bricks. Next,
 * it starts to build that row of bricks, using the number of bricks remaining to determine how far
 * to offset the next brick placement. Once a row is complete, the next row follows the same procedure.
 */	
	
/* Declare and set constants for application.
 * Brick width and height are self explanatory, and in_base sets the number
 * of bricks at the bottom of the tower.	
 */
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 500;
	private static final int BRICK_WIDTH = 30;
	private static final int BRICK_HEIGHT = 12;
	private static final int BRICKS_IN_BASE = 20;
	
	public void run()
	{
		/* This first loop is for the pyramid as a whole, taken without the second loop
		 * all it does is determine where the corner brick should be placed. It determines
		 * the height by subtracting the number of bricks-1 (because GRect determines the TOP corner)
		 * from the height of the window. It determines the width by subtracting the total width of the
		 * pyramid row (width of 1 brick * number of bricks in the row) from the width of the window,
		 * then divides by 2 to center the row on the window.
		 */
		for (int bricks=BRICKS_IN_BASE; bricks>0; bricks--)
		{
			int widthOfPyramidLevel = bricks*BRICK_WIDTH;
			int heightOfPyramidLevel = ((BRICKS_IN_BASE-bricks)*BRICK_HEIGHT)+BRICK_HEIGHT;
			int leftCornerOfPyramidLevel = (APPLICATION_WIDTH-widthOfPyramidLevel)/2;
			int bottomOfPyramidLevel = APPLICATION_HEIGHT-heightOfPyramidLevel;
			/* The second loop draws the bricks, starting with the corner of a row and working left
			 * until all of the bricks for the row have been placed.
			 */
			for(int bricksInRow = bricks; bricksInRow>0; bricksInRow--)
			{
				int brickPlaceOffset = (bricks-bricksInRow)*BRICK_WIDTH;
				GRect brick = new GRect (leftCornerOfPyramidLevel+brickPlaceOffset, bottomOfPyramidLevel , BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
			}
		}
		
	}
}
