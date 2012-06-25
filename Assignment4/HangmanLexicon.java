/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;

public class HangmanLexicon {

	private static final String LEXICON_FILE = "HangmanLexicon.txt";
	
/** Returns the number of words in the lexicon file.
 * Opens a bufferedReader file in the constant LEXICON_FILE, counts the number of non-null lines, and returns that number after
 * closing the file. */
	public int getWordCount() {
		BufferedReader rd = null;
		int lexiconLength = 0;
		//Open the file
		try{	
		rd = new BufferedReader(new FileReader(LEXICON_FILE));
		}
		catch (FileNotFoundException ex){
			ex.printStackTrace();
		}
		String str = "";
		//Read the file
		try{
			while(true){
			str = rd.readLine();
			if (str == null) break;
			lexiconLength++;
			}
		}
		catch (IOException ex){
			throw new ErrorException(ex);
		}
		//Close the File
		try{
			rd.close();
		}
		catch (IOException ex){
			throw new ErrorException(ex);
		}
		return lexiconLength;
	}

/** Returns the word at the specified index by creating an array of the length of the reader file, populating it with the word list,
 * then returning the word at the specified index. */
	public String getWord(int index, int length) {
		String[] array = new String[length];
		String word = "NoWord";
		BufferedReader rd = null;
		//Open the file
		try{	
		rd = new BufferedReader(new FileReader(LEXICON_FILE));
		}
		catch (IOException ex){
			throw new ErrorException(ex);
		}
		
		//Read the file
		try{
			int i = 0;
			while (true){
			array[i++] = rd.readLine();
			if (i == length) break;
			}
		}
		catch (IOException ex){
			throw new ErrorException(ex);
		}
		
		//Close the File
		
		try{
			rd.close();
		}
		catch (IOException ex){
			throw new ErrorException(ex);
		}
		
		word = array[index];
		return word;
	}
}
