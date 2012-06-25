/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	/**
	 * Runs the game by first initializing variables, choosing the number of players, and setting up the canvas.
	 */
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players (no more than " + MAX_PLAYERS +").");
		while (nPlayers > MAX_PLAYERS){
			nPlayers = dialog.readInt("That's more than " +MAX_PLAYERS + "! Try again.");
		}
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i-1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		currentPlayer = 0;
		dice = new int[N_DICE];
		for (int i=0; i>dice.length; i++){
			dice[i] = 0;
		}
		scoreCard = new int[nPlayers][N_CATEGORIES+1];
		initScoreCard();
		playGame();
	}
	/**
	 * Places the value "-1" in every hole in the array. -1 is a good placeholder since it is NOT a valid score (but 0 is).
	 */
	private void initScoreCard(){
		for (int i = 0; i<nPlayers; i++){
			for (int j=0; j< N_CATEGORIES; j++){
				scoreCard[i][j] = -1;
			}
		}
			
	}
	/**
	 * Allows the current player to roll the dice and input their score, then advances to the next player until all players have
	 * filled in their score card.
	 */
	private void playGame() {
		for(currentTurn = 0; currentTurn<(nPlayers*N_SCORING_CATEGORIES); currentTurn++){
			display.printMessage("Your Turn, " + playerNames[currentPlayer] + "!" + " Click \"Roll Dice\" to play.");
			rollDice();
			rollTwoAndThree();
			checkSelection();
			currentPlayer = nextPlayersTurn(currentPlayer);
		}
		gameOver();
	}
	/**
	 * Allows the player to roll the dice for their first roll.
	 */
	private void rollDice(){
		display.waitForPlayerToClickRoll(currentPlayer+1);
		for (int i=0; i<dice.length; i++){
			dice[i] = rgen.nextInt(1,6);
		}
		display.displayDice(dice);
	}
	/**
	 * Allows the player to select which dice to re-roll, then roll them. Repeats.
	 */
	private void rollTwoAndThree(){
		for(int i=0; i<2; i++){
			display.printMessage("Select the dice you wish to re-roll and press \"Roll Again.\"");
			display.waitForPlayerToSelectDice();
			for (int j=0; j<N_DICE; j++){
				if (display.isDieSelected(j))dice[j] = rgen.nextInt(1,6);
			}
			display.displayDice(dice);
		}
	}
	/**
	 * Checks whether the selection is valid (i.e. hasn't been used already), and then determines if it meets the criteria for receiving
	 * any points.
	 */
	private void checkSelection(){
		while (true){
			int selection = display.waitForPlayerToSelectCategory();
			if (scoreCard[currentPlayer][selection] == -1){
				if (!YahtzeeMagicStub.checkCategory(dice, selection)){
				//if (!checkCategory(dice, selection)){	
					display.updateScorecard(selection, currentPlayer+1,0);
					scoreCard[currentPlayer][selection] = 0;
				}
				else assignScore(selection);
				break;
			}
			else display.printMessage("You've already used that category. Please Try Again.");
		}
	}
	/** Replaces the YahtzeeMagicStub.checkCategory to determine whether points will be awarded for the box.
	 * 
	 * @param dice The array of dice from the final roll.
	 * @param category The selected category to test. 
	 */
	private boolean checkCategory(int[] dice, int category){
		return false;
	}
	
	private void assignScore(int category){
		int score = 0;
		switch (category){
			case ONES:
			for (int i=0; i<dice.length; i++){
				if (dice[i]==1) score += dice[i];
			}
			break;
			case TWOS:
			for (int i=0; i<dice.length; i++){
				if (dice[i]==2) score += dice[i];
			}
			break;
			case THREES:
			for (int i=0; i<dice.length; i++){
				if (dice[i]==3) score += dice[i];
			}
			break;
			case FOURS:
			for (int i=0; i<dice.length; i++){
				if (dice[i]==4) score += dice[i];
			}
			break;
			case FIVES:
			for (int i=0; i<dice.length; i++){
				if (dice[i]==5) score += dice[i];
			}
			break;
			case SIXES:
			for (int i=0; i<dice.length; i++){
				if (dice[i]==6) score += dice[i];
			}
			break;
			case THREE_OF_A_KIND:
			for (int i=0; i<dice.length; i++){
				score += dice[i];
			}
			break;
			case FOUR_OF_A_KIND:
			for (int i=0; i<dice.length; i++){
				score += dice[i];
			}
			break;
			case FULL_HOUSE:
			score = 25;
			break;
			case SMALL_STRAIGHT:
			score = 30;
			break;
			case LARGE_STRAIGHT:
			score = 40;
			break;
			case YAHTZEE:
			score = 50;
			break;
			case CHANCE:
			for (int i=0; i<dice.length; i++){
				score += dice[i];
			}
			break;
		}
		display.updateScorecard(category, currentPlayer+1,score);
		scoreCard[currentPlayer][category] = score;
		updateTotals();
	}
	
	private void updateTotals(){
		int lowerScore = 0;
		int upperScore = 0;
		for (int i = 1; i<=6; i++){
			if (scoreCard[currentPlayer][i] != -1) upperScore += scoreCard[currentPlayer][i];
		}
		for (int j = 9; j<=15; j++){
			if (scoreCard[currentPlayer][j] != -1) lowerScore += scoreCard[currentPlayer][j];
		}
		scoreCard[currentPlayer][UPPER_SCORE] = upperScore;
		scoreCard[currentPlayer][LOWER_SCORE] = lowerScore;
		if (upperScore >= 63){
			scoreCard[currentPlayer][UPPER_BONUS] = 35;
			scoreCard[currentPlayer][UPPER_SCORE] += 35;
			display.updateScorecard(UPPER_BONUS, currentPlayer+1, 35);
		}
		scoreCard[currentPlayer][TOTAL] = upperScore + lowerScore;
		display.updateScorecard(UPPER_SCORE, currentPlayer+1, upperScore);
		display.updateScorecard(LOWER_SCORE, currentPlayer+1, lowerScore);
		display.updateScorecard(TOTAL, currentPlayer+1, upperScore + lowerScore);
	}
	
	private int nextPlayersTurn(int player){
		player++;
		if (player >= (nPlayers)) player = 0;
		return player;
	}
	
	private void gameOver(){
		int winningPlayer = 0;
		int testScore = 0;
		int highScore = 0;
		for (int i = 0; i<nPlayers; i++){
			testScore = scoreCard[i][TOTAL];
			if (testScore > highScore){
				highScore = testScore;
				winningPlayer = i;
			}
		}
		display.printMessage("Congratulations " + playerNames[winningPlayer] + "! You Win with a score of " + highScore + "!");
	}
	
/* Private instance variables */
	private int nPlayers;
	private int currentPlayer;
	private int currentTurn;
	private int[] dice;
	private int[][] scoreCard;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = RandomGenerator.getInstance();

}
