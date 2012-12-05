
/*
 * Amy Denham
 * CS161-01 Fall 2012
 * Lab 9
 *
 */ 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShadeSelectionMenu extends JFrame implements ActionListener {

	private JPanel panel;
	private JPanel westPanel;
	private JPanel centerPanel;
	private JPanel eastPanel;
	private JPanel southPanel;
	private JMenu sizeMenu;
	private JMenu styleMenu;
	private JMenu colorMenu;
	private JMenuBar menuBar;
	private JTextField textStyle;
	private JTextField textSize;
	private JTextField textColor;
	private JLabel labelSelect;
	private JLabel labelColor;
	private JLabel labelPrice;
	private JTextField textPrice;
	private int stylePrice=0;
	private int sizePrice=0;
	private int colorPrice=0;
	
	/**
	 * Constructor
	 */
	ShadeSelectionMenu(){
		
	}

	/**
	 * Builds menu system using three parameter String arrays for menu items for each menu. 
	 * Instantiates the menus and for each it calls makeMenu() passing the array 
	 * and the menu as parameters.
	 */
	public void buildMenuSystem(String[] styles, String[] sizes, String[] colors ){
		
		//instantiate menu bar menus
		menuBar = new JMenuBar();
		styleMenu = new JMenu("Styles");
		sizeMenu = new JMenu("Sizes");
		colorMenu = new JMenu("Colors");
		
		//build menus using arrays passed as parameters
		makeMenu(styles, styleMenu);
		makeMenu(sizes, sizeMenu);
		makeMenu(colors, colorMenu);
		
		//add menus to menubar
		menuBar.add(styleMenu);
		menuBar.add(sizeMenu);
		menuBar.add(colorMenu);
		
		//set menu bar
		setJMenuBar(menuBar);

		//build window
		buildFrame();
	}

	/**
	 * Builds window. 
	 */
	private void buildFrame(){

		//instantiate panel and add to window
	    panel = new JPanel();
	    add(panel);
	    
	    //set panel layout
	    panel.setLayout(new BorderLayout());
	    
	    //instantiate west panel and text fields
	    westPanel = new JPanel();
	    textStyle = new JTextField(15);
	    textColor = new JTextField(15);
	    textSize = new JTextField(15);
	    
	    //make text fields non-editable
	    textStyle.setEditable(false);
	    textColor.setEditable(false);
	    textSize.setEditable(false);

	    //add text fields to west panel	    
	    westPanel.add(textStyle);
	    westPanel.add(textSize);
	    westPanel.add(textColor);

	    //add west panel to panel and set gridlayout and border
	    westPanel.setLayout(new GridLayout(3,0,2,2));
	    westPanel.setBorder(BorderFactory.createTitledBorder("Selections"));
	    panel.add(westPanel,BorderLayout.WEST);
	    	    
	    //add center panel and components
	    centerPanel = new JPanel();
	    labelSelect = new JLabel("Select your shade!");
	    centerPanel.add(labelSelect);
	    panel.add(centerPanel,BorderLayout.CENTER);
	    
	    //add east panel and components
	    eastPanel = new JPanel();
	    labelColor = new JLabel("Selected Color");
	    eastPanel.add(labelColor);
	    panel.add(eastPanel,BorderLayout.EAST);
	    
	    //add south panel and components
	    southPanel = new JPanel();
	    labelPrice = new JLabel("Total Price:");
	    textPrice = new JTextField(15);
	    southPanel.add(labelPrice);
	    southPanel.add(textPrice);
	    textPrice.setEditable(false);
	    panel.add(southPanel,BorderLayout.SOUTH);
	    
	    //set window size, visibility, title, and close operation
	    setSize(500,200);
	    setTitle("SHADE SELECTION APPLICATION");
	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}

	/**
	 * Method takes an array of menu items and a JMenu object. 
	 * It instantiates a JMenuItem item for each array entry,
	 * registers the item with the listener, and adds item to the parameter menu. 
	 */
	private void makeMenu(String[] array, JMenu menu){
		JMenuItem item;
		for(int a=0; a<array.length;a++){
			item = new JMenuItem(array[a]);
			item.addActionListener(this);
			menu.add(item);
		}
	}
	
	/**
	 * Contains the selection logic and controls the events following each selection: 
	 * display on the grid, display the price, display the color if applicable. 
	 * Suggested color components for the color array are
	 */
	public void actionPerformed(ActionEvent e) {
		//get menu item selected
		String selection = e.getActionCommand();
		//based on selection, change text fields, set price, display color, and show total price
		switch (selection){
		case "regular shades":
			textStyle.setText(selection);
			stylePrice = 0;
			textPrice.setText("$" + String.valueOf(50 + stylePrice + sizePrice + colorPrice));
			break;
		case "folding shades":
			textStyle.setText(selection);
			stylePrice = 10;
			textPrice.setText("$" + String.valueOf(50 + stylePrice + sizePrice + colorPrice));
			break;
		case "roman shades":
			textStyle.setText(selection);
			stylePrice = 15;
			textPrice.setText("$" + String.valueOf(50 + stylePrice + sizePrice + colorPrice));
			break;
		case "25 inches":
			textSize.setText(selection);
			sizePrice = 0;
			textPrice.setText("$" + String.valueOf(50 + stylePrice + sizePrice + colorPrice));
			break;
		case "27 inches":
			textSize.setText(selection);
			sizePrice = 2;
			textPrice.setText("$" + String.valueOf(50 + stylePrice + sizePrice + colorPrice));
			break;
		case "32 inches":
			textSize.setText(selection);
			sizePrice = 4;
			textPrice.setText("$" + String.valueOf(50 + stylePrice + sizePrice + colorPrice));
			break;
		case "40 inches":
			textSize.setText(selection);
			sizePrice = 6;
			textPrice.setText("$" + String.valueOf(50 + stylePrice + sizePrice + colorPrice));
			break;
		case "natural":
			textColor.setText(selection);
			colorPrice = 5;
			textPrice.setText("$" + String.valueOf(50 + stylePrice + sizePrice + colorPrice));
			eastPanel.setBackground(new Color (200,125,75));
			break;
		case "sea-mist blue":
			textColor.setText(selection);
			colorPrice = 0;
			textPrice.setText("$" + String.valueOf(50 + stylePrice + sizePrice + colorPrice));
			eastPanel.setBackground(new Color (0,170,220));
			break;
		case "moondust red":
			textColor.setText(selection);
			colorPrice = 0;
			textPrice.setText("$" + String.valueOf(50 + stylePrice + sizePrice + colorPrice));
			eastPanel.setBackground(new Color (250,50,25));
			break;
		case "prairie green":
			textColor.setText(selection);
			colorPrice = 0;
			textPrice.setText("$" + String.valueOf(50 + stylePrice + sizePrice + colorPrice));
			eastPanel.setBackground(new Color (100,200,0)); 
			break;
		}
	}
}
