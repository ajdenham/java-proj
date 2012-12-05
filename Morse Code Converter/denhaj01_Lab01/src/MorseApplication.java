/*
 * Amy Denham
 * CS161-01 Fall 2012
 * Lab 1-01
 *
 */ 

import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 * 
 * This class is the application method for the morse conversion class. 
 * It takes an input text, converts it to morse, and prints the result to the console. 
 * It then uses a string tokenizer to convert the morse back to text and compares 
 * the result with the original input using the regionMatches method.
 *
 */
public class MorseApplication {

	public static void main(String[] args) {

		//Declare variables
		String input=""; 	//holds input text
		String output=""; 	//holds output morse
		String text = "";	//holds morse to text conversion
		String[] morse; 	//morse code array
		
		//Get text to translate
		input = JOptionPane.showInputDialog("Enter a string to convert to Morse code.");
		
		//Translate and print to console
		morse = MorseConversions.textToMorse(input);
				
		output = MorseConversions.morseToText(morse);
		
		System.out.println("Original text: "+ input);
		System.out.println("Morse translation: " + output);
		
		//Convert morse translation back to text
		//Use a tokenizer to break down the morse code string
		StringTokenizer strTokenizer = new StringTokenizer(output," ");
		
		while (strTokenizer.hasMoreTokens()){

			text = text + MorseConversions.ConvertMorseToText((strTokenizer.nextToken()));
		}
		
		//Print out morse to text transalation
		System.out.println("Morse to text translation: " + text);
		
		//Check if original text and translated morse match
		if (text.regionMatches(true, 0, input, 0, text.length())){
			System.out.println("The original text and the morse to text translation match.");
		}
		else{
			System.out.println("The original text and the morse to text translation do not match.");
		}
				
	}

}
