/*
 * Amy Denham
 * CS161-01 Fall 2012
 * Lab 1-01
 *
 */

/**
 * This class converts text to morse code and back again utilizing parallel arrays.
 */

public class MorseConversions {

	static String[] textToConvert = {" ",",",".","?","0","1","2","3","4","5","6",
			"7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M",
			"N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	static String[] morseCode = {"space","--.--",".-.-.-","..--..","-----",".----",
			"..---","...--","....-",".....","-....","--...","---..","----.",
			".-","-...","-.-.","-..",".","..-.","--.","....","..",
			".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...",
			"-","..-","...-",".--","-..-","-.--","--.."};

	/**
	 * Loops through input text character by character. 
	 * Compares current character to textToConvert array and finds location
	 * Then assigns same location of morseCode array to output array
	 */
	public static String[] textToMorse(String text) {
		
		String currentChar;	//Current character being converted
		String[] getMorse;	//holds array of text to morse conversions

		getMorse = new String[text.length()];

		//Loop through text array to get morse code character
		for(int n=0;n<text.length();n++){
			currentChar = Character.toString(Character.toUpperCase(text.charAt(n)));
					
			for(int k=0; k<textToConvert.length; k++){
				if(currentChar.equals(textToConvert[k])){					
					getMorse[n] = morseCode[k];
				}
			}
		}	
		return getMorse;
	}
	
	/**
	 * Converts morse code array to string by looping through array 
	 * and appending each character to a string.
	 * 
	 */
	public static String morseToText(String[] morse) {
		
		String message = "";
		
		for(int a=0;a<morse.length;a++){
			message = message + morse[a]+ " ";
		}
		
		return message;
		
	}

	/**
	 * Gets a morse code character that is passed by calling object.
	 * Loops through array to find correct position, then assigns
	 * matching text character to returning string.
	 * 
	 */
	public static String ConvertMorseToText(String MorseCharacter) {
		
		String textCharacter = "";	//gets text character matching morse string

		//Loop through morseCode array to find matching text character
		for(int z=0; z<morseCode.length; z++){
			if(MorseCharacter.equals(morseCode[z])){					
				textCharacter = textToConvert[z];
			}
		}

		return textCharacter;
	}
}
