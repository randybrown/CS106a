/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import java.awt.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		drawScaffold();
		badGuesses = "";
		badGuessLabel = new GLabel(badGuesses);
		badGuessLabel.setLocation((getWidth()/8),getHeight()-(getHeight()/8));
		add(badGuessLabel);
		GLabel label1 = new GLabel("Bad Guesses:", badGuessLabel.getX()-20, badGuessLabel.getY()-20);
		add(label1);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		if (wordLabel != null) remove(wordLabel);
		wordLabel = new GLabel(word);
		wordLabel.setFont("monospaced-PLAIN-48");
		wordLabel.setLocation((getWidth()/2)-(wordLabel.getWidth()/2), getHeight()-(getHeight()/4));
		add(wordLabel);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int guessesRemaining) {
		drawNext(guessesRemaining);
		badGuesses += letter+ " ";
		badGuessLabel.setLabel(badGuesses);
	}

/**
 * Draws the vertical post, the horizontal beam, and the rope, and adds them to the canvas.
 */
	
	private void drawScaffold(){
		post = new GLine((getWidth()/2)-BEAM_LENGTH, getHeight()-(getHeight()/3), (getWidth()/2)-BEAM_LENGTH, getHeight()-(getHeight()/3)-SCAFFOLD_HEIGHT);
		add(post);
		beam = new GLine((getWidth()/2)-BEAM_LENGTH, getHeight()-(getHeight()/3)-SCAFFOLD_HEIGHT, (getWidth()/2), getHeight()-(getHeight()/3)-SCAFFOLD_HEIGHT);
		add(beam);
		rope = new GLine((getWidth()/2), getHeight()-(getHeight()/3)-SCAFFOLD_HEIGHT, (getWidth()/2), getHeight()-(getHeight()/3)-SCAFFOLD_HEIGHT+ROPE_LENGTH);
		add(rope);
	}
/**
 * Takes the int guessesRemaining and chooses which piece of the hanging man to draw.	
 */
	private void drawNext(int guessesRemaining){
		switch (guessesRemaining) {
		case 7:{
			head = new GOval((getWidth()/2)-HEAD_RADIUS, getHeight()-(getHeight()/3)-SCAFFOLD_HEIGHT+ROPE_LENGTH, HEAD_RADIUS*2, HEAD_RADIUS*2);
			add(head);
			break;
		}
		case 6:{
			body = new GLine(getWidth()/2,head.getY()+(HEAD_RADIUS*2),getWidth()/2,head.getY()+(HEAD_RADIUS*2)+BODY_LENGTH);
			add(body);
			break;
		}
		case 5:{
			arms = new GLine((getWidth()/2)-UPPER_ARM_LENGTH,head.getY()+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD,(getWidth()/2)+UPPER_ARM_LENGTH,head.getY()+(HEAD_RADIUS*2)+ARM_OFFSET_FROM_HEAD);
			add(arms);
			break;
		}
		case 4:{
			leftHand = new GLine ((getWidth()/2)-UPPER_ARM_LENGTH,arms.getY(),getWidth()/2-UPPER_ARM_LENGTH,arms.getY()+LOWER_ARM_LENGTH);
			rightHand = new GLine ((getWidth()/2)+UPPER_ARM_LENGTH,arms.getY(),getWidth()/2+UPPER_ARM_LENGTH,arms.getY()+LOWER_ARM_LENGTH);
			add(leftHand);
			add(rightHand);
			break;
		}
		case 3:{
			pelvis = new GLine((getWidth()/2)-HIP_WIDTH,head.getY()+(HEAD_RADIUS*2)+BODY_LENGTH,(getWidth()/2)+HIP_WIDTH,head.getY()+(HEAD_RADIUS*2)+BODY_LENGTH);
			add(pelvis);
			break;
		}
		case 2:{
			leftLeg = new GLine ((getWidth()/2)-HIP_WIDTH,pelvis.getY(),(getWidth()/2)-HIP_WIDTH,pelvis.getY()+LEG_LENGTH);
			rightLeg = new GLine ((getWidth()/2)+HIP_WIDTH,pelvis.getY(),(getWidth()/2)+HIP_WIDTH,pelvis.getY()+LEG_LENGTH);
			add(leftLeg);
			add(rightLeg);
			break;
		}
		case 1:{
			leftFoot = new GLine (leftLeg.getX(),pelvis.getY()+LEG_LENGTH,leftLeg.getX()-FOOT_LENGTH,pelvis.getY()+LEG_LENGTH);
			rightFoot = new GLine (rightLeg.getX(),pelvis.getY()+LEG_LENGTH,rightLeg.getX()+FOOT_LENGTH,pelvis.getY()+LEG_LENGTH);
			add(leftFoot);
			add(rightFoot);
			break;
		}
		case 0:{
			blood = new GOval((getWidth()/2)-BLOOD_RADIUS,leftFoot.getY()-(BLOOD_RADIUS/6),BLOOD_RADIUS*2,BLOOD_RADIUS/2);
			blood.setColor(Color.RED);
			blood.setFilled(true);
			add(blood);
			blood.sendToBack();
			break;
		}
		default:;
		}
	}
	
	private GLine post;
	private GLine beam;
	private GLine rope;
	private GOval head;
	private GLine body;
	private GLine arms;
	private GLine rightHand;
	private GLine leftHand;
	private GLine pelvis;
	private GLine leftLeg;
	private GLine rightLeg;
	private GLine leftFoot;
	private GLine rightFoot;
	private GOval blood;
	
	private GLabel badGuessLabel;
	private String badGuesses;
	
	private GLabel wordLabel;
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int BLOOD_RADIUS = 100;

}
