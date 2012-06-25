/*
 * File: GraphicsHierarchy.java
 * ----------------------------
 * This program is a stub for the GraphicsHierarchy problem, which
 * draws a partial diagram of the acm.graphics hierarchy.
 */

import acm.program.*;
import acm.graphics.*;

public class GraphicsHierarchy extends GraphicsProgram {
/* This program creates a chart detailing the hierarchy of the acm.program.*
 */
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 500;
	private static final int BOX_WIDTH = 200;
	private static final int BOX_HEIGHT = APPLICATION_HEIGHT/5;
	
	public void run()
	{
		//Draw the top box and add the label.
		GRect topRectangle = new GRect((APPLICATION_WIDTH-BOX_WIDTH)/2 ,BOX_HEIGHT , BOX_WIDTH, BOX_HEIGHT);
		GLabel topLabel = new GLabel("Program");
		double topLabelX = topRectangle.getX()+((BOX_WIDTH+topLabel.getWidth())/2)-topLabel.getWidth();
		double topLabelY = topRectangle.getY()+(BOX_HEIGHT+topLabel.getAscent())/2;
		topLabel.setLocation(topLabelX, topLabelY);
		add(topRectangle);
		add(topLabel);
		
		//draw the three bottom boxes and add their respective labels.
		
		//draw bottomLeft and Label
		GRect bottomLeftRectangle = new GRect(((APPLICATION_WIDTH-BOX_WIDTH)/2)-(BOX_WIDTH*1.2) ,BOX_HEIGHT*3 , BOX_WIDTH, BOX_HEIGHT);
		GLabel bottomLeftLabel = new GLabel("GraphicsProgram");
		double bottomLeftLabelX = bottomLeftRectangle.getX()+((BOX_WIDTH+bottomLeftLabel.getWidth())/2)-bottomLeftLabel.getWidth();
		double bottomLeftLabelY = bottomLeftRectangle.getY()+(BOX_HEIGHT+bottomLeftLabel.getAscent())/2;
		bottomLeftLabel.setLocation(bottomLeftLabelX, bottomLeftLabelY);
		add(bottomLeftRectangle);
		add(bottomLeftLabel);
		
		//draw bottomMiddle and label.
		GRect bottomMiddleRectangle = new GRect((APPLICATION_WIDTH-BOX_WIDTH)/2 ,BOX_HEIGHT*3 , BOX_WIDTH, BOX_HEIGHT);
		GLabel bottomMiddleLabel = new GLabel("ConsoleProgram");
		double bottomMiddleLabelX = bottomMiddleRectangle.getX()+((BOX_WIDTH+bottomMiddleLabel.getWidth())/2)-bottomMiddleLabel.getWidth();
		double bottomMiddleLabelY = bottomMiddleRectangle.getY()+(BOX_HEIGHT+bottomMiddleLabel.getAscent())/2;
		bottomMiddleLabel.setLocation(bottomMiddleLabelX, bottomMiddleLabelY);
		add(bottomMiddleRectangle);
		add(bottomMiddleLabel);
		
		//draw bottomRight and label
		GRect bottomRightRectangle = new GRect(((APPLICATION_WIDTH-BOX_WIDTH)/2)+(BOX_WIDTH*1.2) ,BOX_HEIGHT*3 , BOX_WIDTH, BOX_HEIGHT);
		GLabel bottomRightLabel = new GLabel("DialogProgram");
		double bottomRightLabelX = bottomRightRectangle.getX()+((BOX_WIDTH+bottomRightLabel.getWidth())/2)-bottomRightLabel.getWidth();
		double bottomRightLabelY = bottomRightRectangle.getY()+(BOX_HEIGHT+bottomRightLabel.getAscent())/2;
		bottomRightLabel.setLocation(bottomRightLabelX, bottomRightLabelY);
		add(bottomRightRectangle);
		add(bottomRightLabel);
		
		//Draw the 3 lines connecting the top box to the bottom boxes.
		double topRectanglePointX = (topRectangle.getX()+(BOX_WIDTH/2));
		double topRectanglePointY = (topRectangle.getY()+(BOX_HEIGHT));
		double bottomLeftRectanglePointX = (bottomLeftRectangle.getX()+(BOX_WIDTH/2));
		double bottomLeftRectanglePointY = bottomLeftRectangle.getY();
		double bottomMiddleRectanglePointX = (bottomMiddleRectangle.getX()+(BOX_WIDTH/2));
		double bottomMiddleRectanglePointY = bottomMiddleRectangle.getY();
		double bottomRightRectanglePointX = (bottomRightRectangle.getX()+(BOX_WIDTH/2));
		double bottomRightRectanglePointY = bottomMiddleRectangle.getY();
		add(new GLine(bottomLeftRectanglePointX,bottomLeftRectanglePointY,topRectanglePointX,topRectanglePointY));
		add(new GLine(bottomMiddleRectanglePointX,bottomMiddleRectanglePointY,topRectanglePointX,topRectanglePointY));
		add(new GLine(bottomRightRectanglePointX,bottomRightRectanglePointY,topRectanglePointX,topRectanglePointY));
	}

}
