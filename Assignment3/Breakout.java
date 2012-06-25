/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

//import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 50;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Game Speed */
	private static final int GAME_SPEED = 8;

/* Method: run() */
/** Runs the program. */
	public void run() {
		setupBoard();
		addMouseListeners();
		waitForClick();
		pause(GAME_SPEED);
		while (!gameOver){
			checkForCollision();
			moveBall();
			pause(GAME_SPEED);
			if (bricksRemaining == 0) gameOver=true;
			if (theBall == null){
				turnsRemaining--;
				turnsLabel.setLabel("Lives Remaining: " + turnsRemaining);
				if (turnsRemaining == 0) gameOver=true;
				else {
					waitForClick();
					pause(GAME_SPEED);
				}
			}
		}	
		gameOver();
	}

	//setupBoard, drawRowOfBricks, drawPaddle... are methods used to draw the board.
	
	private void setupBoard(){
		gameOver = false;
		turnsRemaining = NTURNS;
		bricksRemaining = 0;
		score = 0;
		for (int row=NBRICK_ROWS; row >0 ; row--){
			drawRowOfBricks(row);
		}
		drawPaddle();
		scoreLabel = new GLabel("Score: " + score);
		scoreLabel.setLocation(10,HEIGHT-(scoreLabel.getHeight()*2));
		add(scoreLabel);
		turnsLabel = new GLabel("Lives Remaining: " + turnsRemaining);
		turnsLabel.setLocation(WIDTH-turnsLabel.getWidth(), HEIGHT-(turnsLabel.getHeight()*2));
		add(turnsLabel);
	}
	
	private void drawRowOfBricks(int row){
		for (int bricksInRow=NBRICKS_PER_ROW; bricksInRow >0; bricksInRow--){
			int brickPlacementOffset = ((NBRICKS_PER_ROW-bricksInRow)*BRICK_WIDTH)+(BRICK_SEP*(NBRICKS_PER_ROW-bricksInRow));
			brick = new GRect ((BRICK_SEP/2)+brickPlacementOffset,(((NBRICK_ROWS-row)*BRICK_HEIGHT)+((NBRICK_ROWS-row)*BRICK_SEP))+BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT);
			switch (row){
			case 10: case 9:
				brick.setColor(Color.RED);
				break;
			case 8: case 7:
				brick.setColor(Color.ORANGE);
				break;
			case 6: case 5:
				brick.setColor(Color.YELLOW);
				break;
			case 4: case 3:
				brick.setColor(Color.GREEN);
				break;
			case 2: case 1:
				brick.setColor(Color.CYAN);
				break;
			}
			brick.setFilled(true);
			add(brick);
			bricksRemaining++;
		}
	}
	
	private void drawPaddle(){
		thePaddle = new GRect (0,HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT,PADDLE_WIDTH,PADDLE_HEIGHT);
		thePaddle.setFilled(true);
		add(thePaddle);
	}
	public void mouseMoved(MouseEvent e){
		if (e.getX()<=WIDTH-PADDLE_WIDTH && e.getX()>=0){
			double lastX = thePaddle.getX();
			thePaddle.move(e.getX()-lastX,0);
		}
	}
	public void mouseReleased(MouseEvent e){
		if (theBall==null && turnsRemaining >0){
			int xOffset = rgen.nextInt(15,50);
			if (rgen.nextBoolean()) xOffset = -xOffset;
			theBall = new GOval ((WIDTH/2)+xOffset, HEIGHT/2, BALL_RADIUS*2, BALL_RADIUS*2);
			velocityY = 3.0;
			velocityX = rgen.nextDouble(1.0, 3.0);
			if (rgen.nextBoolean()) velocityX = -velocityX;
			theBall.setFilled(true);
			add(theBall);
			theBall.sendToBack();
		}
	}
	private void moveBall(){
		if (theBall.getX()+(BALL_RADIUS*2)>= WIDTH){
			velocityX = -velocityX;
		}
		if (theBall.getX() <= 0){
			velocityX = -velocityX;
		}
		if (theBall.getY()<= 0){
			velocityY = -velocityY;
		} 
		theBall.move(velocityX, velocityY);
		if (theBall.getY()+(BALL_RADIUS*2)>= HEIGHT-PADDLE_Y_OFFSET+(BALL_RADIUS*2)){
			remove(theBall);
			theBall = null;
		}
	}
	private void checkForCollision(){
			GObject collider = getCollidingObject();
			if (collider == null){}
			else if (collider == thePaddle){
				velocityY = -velocityY;
				if (ballHitOnPaddleX <= PADDLE_WIDTH/4.0){
					velocityX = rgen.nextDouble(-1.0,-3.0);
				}
				else if (ballHitOnPaddleX >PADDLE_WIDTH/4.0 && ballHitOnPaddleX < PADDLE_WIDTH/4.0*3){
				}
				else if (ballHitOnPaddleX >= PADDLE_WIDTH/4.2*3){
					velocityX = rgen.nextDouble(1.0,3.0);
				}
				if (velocityX <1.0 && velocityX >-1.0){
					velocityX = rgen.nextDouble(1.0,3.0);
				}
			}
			else if (collider == scoreLabel || collider == turnsLabel){}
			else {
				Color brickColor = collider.getColor();
				String colorCode = brickColor.toString();
				if (colorCode.equals("java.awt.Color[r=255,g=0,b=0]")) score += 5;
				else if (colorCode.equals("java.awt.Color[r=255,g=200,b=0]")) score += 4;
				else if (colorCode.equals("java.awt.Color[r=255,g=255,b=0]")) score += 3;
				else if (colorCode.equals("java.awt.Color[r=0,g=255,b=0]")) score += 2;
				else if (colorCode.equals("java.awt.Color[r=0,g=255,b=255]")) score += 1;
				remove(collider);
				velocityY = -velocityY;
				scoreLabel.setLabel("Score: " + score);
				bricksRemaining--;				
			}
			
	}
	
	private GObject getCollidingObject(){
		GObject object;
        object = getElementAt(theBall.getX(), theBall.getY());
        
        if (object != null) {
            return object;
        }
        else {
            object = getElementAt(theBall.getX() + 2 * BALL_RADIUS, theBall.getY());
            if(object != null) {
                return object;
            }
            else {
                object = getElementAt(theBall.getX() + 2 * BALL_RADIUS, theBall.getY() + 2 * BALL_RADIUS);
                if(object != null) {
                    ballHitOnPaddleX = thePaddle.getX() + PADDLE_WIDTH - theBall.getX() + BALL_RADIUS;
                    return object;
                }
                else {
                    object = getElementAt(theBall.getX(), theBall.getY() + 2 * BALL_RADIUS);
                    if(object != null) {
                        ballHitOnPaddleX = thePaddle.getX() + PADDLE_WIDTH - theBall.getX() + BALL_RADIUS;
                    }
                    return object;
                }
            }
        }
	}
	
	private void gameOver(){
		if (bricksRemaining == 0){
			GLabel label = new GLabel ("You Win! Yay!");
			label.setLocation((WIDTH-label.getWidth())/2, (HEIGHT-label.getAscent())/2);
			add(label);
		}
		else if (turnsRemaining == 0){
			GLabel label = new GLabel ("You Freaking Fail!");
			label.setLocation((WIDTH-label.getWidth())/2, (HEIGHT-label.getAscent())/2);
			add(label);
		}
	}	
	//Instance Variables
	private boolean gameOver;
	private int turnsRemaining;
	private int bricksRemaining;
	private int score;
	private GLabel scoreLabel;
	private GLabel turnsLabel;
	private GRect thePaddle;
	private GOval theBall;
	private GRect brick;
	private double velocityX;
	private double velocityY;
	private double ballHitOnPaddleX;
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
