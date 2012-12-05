/*
 * Amy Denham
 * CS161-01 Fall 2012
 * Lab 9
 *
 */ 

/**
/* This class contains the main method, declares and instantiates the String arrays of menu items for each menu
 * instantiates a ShadeSelectionMenu object, and calls the buildMenuSystem( ) method which takes the 
 * three arrays for parameters
 */
public class ShadeApplications {
	
	/**
	 * Main method that declares arrays and calls shade application
	 */
	public static void main(String[] args) {
		
		//declare menu arrays
		String[] styles = {"regular shades", "folding shades", "roman shades"};
		String[] sizes = {"25 inches","27 inches","32 inches","40 inches"};
		String[] colors = {"natural", "sea-mist blue", "moondust red", "prairie green"};
		
		//declare and instantiate shade application
		ShadeSelectionMenu shades = new ShadeSelectionMenu();

		//build menu
		shades.buildMenuSystem(styles, sizes, colors);
	}

}
