/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */


import acm.program.*;
//import acm.graphics.*;
//import java.awt.*;
import acm.util.*;


public class Hangman extends ConsoleProgram {
	
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 600;
	private static int NUMBER_OF_GUESSES = 8;
	
	public void init(){
		canvas = new HangmanCanvas();
		add(canvas);
	}
	
	public void run() {
    	setupGame();
		println("Welcome to Hangman!");
		//println(theWord); //Comment out in production. Used for testing game play (cheaters never prosper).
		//println(lexicon.getWordCount()); //Used for debugging.
		while (guessesRemaining > 0 &! theWord.equals(theHiddenWord)){
			canvas.displayWord(theHiddenWord);
			String userInput = readLine("Your Guess, please: ");
			char guess = getUpperCaseLetter(userInput);
			theHiddenWord = testGuess(guess);	
			}
		canvas.displayWord(theHiddenWord);
		println("The word was: " +theWord);
		if (guessesRemaining == 0){
			println("You're Hung (not like Chuck Norris)!. Game Over.");
		}
		else {
			println("You Win! You had " + guessesRemaining + " guesses left when you figured it out!");
		}
		}	
/*
 * Gets a random word from the lexicon, creates an additional string of -'s for the letters in
 * the word, and resets the canvas
 */
    private void setupGame(){
    	int length = lexicon.getWordCount();
    	int random = rgen.nextInt(0, length-1);
    	theWord = lexicon.getWord(random,length);
    	theHiddenWord = hideTheWord(theWord);
    	guessesRemaining = NUMBER_OF_GUESSES;
    	canvas.reset();
    	canvas.displayWord(theHiddenWord);
    }
/*
 * Takes a word and replaces each letter with a hyphen "-".    
 */
    private String hideTheWord(String word){
    	String result = "";
    	for (int i=0; i< word.length(); i++){
    		result += "-";
    	}
    	return result;
    }
/*
 * Takes the player's guess, determines whether it is a single letter (re-prompts if
 * the player fails to follow instructions) and then converts that string to an upper case char.    
 */
    private char getUpperCaseLetter(String letter){
    	char ch = letter.charAt(0);
    	while (letter.length() != 1 |! Character.isLetter(ch)){;
    			println("I'm sorry, that's NOT a letter, please guess again.");
    			letter = readLine("Your Guess, please: ");
    			ch = letter.charAt(0);
    		}
    	ch = Character.toUpperCase(ch);
		return ch;
    }
/*
 * Determines whether the letter guessed exists in theWord. If present, adds the guess to theHiddenWord wherever it occurs.
 * Otherwise, the incorrect guess is noted on the canvas, and the player loses a turn.
 */
    private String testGuess(char guess){
    	String result = "";
    	boolean loseTurn = true;
    	for (int i=0; i< theWord.length(); i++){
    		char ch = theWord.charAt(i);
    		if (guess == ch){
    			result =  theHiddenWord.substring(0,i) + guess + theHiddenWord.substring(i+1);
    			loseTurn = false;
    			theHiddenWord = result;
    		}		
    	}
    	if(loseTurn){
    		guessesRemaining--;
    		canvas.noteIncorrectGuess(guess, guessesRemaining);
    	}
    	return theHiddenWord;
    }
    
private HangmanLexicon lexicon = new HangmanLexicon();
private HangmanCanvas canvas;

private int guessesRemaining;
private String theWord;
private String theHiddenWord;
private RandomGenerator rgen = RandomGenerator.getInstance();
}