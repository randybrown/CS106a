/*
 * File: Rainbow.java
 * ------------------
 * This program is a stub for the Rainbow problem, which displays
 * a rainbow by adding consecutively smaller circles to the canvas.
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class Rainbow extends GraphicsProgram {
/* This is actually the "Target" assignment, but I downloaded the file from a different site and this
 * was the only variation from the SEE assignment page, so I didn't want to start over. This program
 * adds 3 circles to the center of the page in the form of a Red-and-white target...
 * just like the corporate logo.
 */
	
	//given information (radiuses of the circles and PPI)
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 500;
	private static final int PIXELS_PER_INCH = 72;
	private static final double RADIUS_OF_CIRCLE1_IN_INCHES = 1.0;
	private static final double RADIUS_OF_CIRCLE2_IN_INCHES = 0.65;
	private static final double RADIUS_OF_CIRCLE3_IN_INCHES = 0.3;
		
	public void run() {
		//determine circle sizes (radius * 2 * PPI)
		double widthOfCircle1 = RADIUS_OF_CIRCLE1_IN_INCHES*PIXELS_PER_INCH*2;
		double widthOfCircle2 = RADIUS_OF_CIRCLE2_IN_INCHES*PIXELS_PER_INCH*2;
		double widthOfCircle3 = RADIUS_OF_CIRCLE3_IN_INCHES*PIXELS_PER_INCH*2;
		//determine where to place each circle based on the page dimensions and circle sizes.
		double leftOfCircle1 = (APPLICATION_WIDTH-widthOfCircle1)/2;
		double leftOfCircle2 = (APPLICATION_WIDTH-widthOfCircle2)/2;
		double leftOfCircle3 = (APPLICATION_WIDTH-widthOfCircle3)/2;
		double topOfCircle1 = (APPLICATION_HEIGHT-widthOfCircle1)/2;
		double topOfCircle2 = (APPLICATION_HEIGHT-widthOfCircle2)/2;
		double topOfCircle3 = (APPLICATION_HEIGHT-widthOfCircle3)/2;
		//draw circle1
		GOval circle1 = new GOval(leftOfCircle1,topOfCircle1,widthOfCircle1,widthOfCircle1);
		circle1.setFilled(true);
		circle1.setColor(Color.RED);
		//draw circle2
		GOval circle2 = new GOval(leftOfCircle2,topOfCircle2,widthOfCircle2,widthOfCircle2);
		circle2.setFilled(true);
		circle2.setColor(Color.WHITE);
		//draw circle3
		GOval circle3 = new GOval(leftOfCircle3,topOfCircle3,widthOfCircle3,widthOfCircle3);
		circle3.setFilled(true);
		circle3.setColor(Color.RED);
		//place circles on the canvas
		add(circle1);
		add(circle2);
		add(circle3);
	}

}
