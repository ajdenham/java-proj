/*
* Amy Denham
* CS161-01 Fall 2012
* Project 4
*
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *This class is responsible for the GUI (the frame and the graphics) and for running the recursive drawFractal() method. 
 */
public class Fractal extends JFrame implements ActionListener {

	final double RATIO = 0.45;	//rectangle ratio
			
	public Point point; 		//Point reference variable determined by application 
	public int width=0;			//width determined by application
	public int height=0;		//height determined by application
	public int level=0;			//current fractal level
	private JPanel panelNorth;	//north panel to hold buttons
	private JButton up;			//button to increase fractal level
	private JButton down;		//button to decrease fractal level
	private Center panelCenter;	//Center class used to draw fractals
	
	/**
	 * Constructor; takes four parameters to initialize the Point object, 
	 * width, height and level; calls the makeWindow() method
	 */
	Fractal(Point p, int w, int h, int l){

		//assign values to local variables
		point = p;
		level = l;
		width = w;
		height = h;

		//create window
		makeWindow();
	}
	
	/*
	 * Draws fractal rectangles based on values passed. For level 1, uses height and width
	 * so that entire square will be yellow. For levels > 1, uses a height and width based on
	 * a ratio and recursion to draw appropriate number of squares.
	 */
	private void drawFractal(Graphics g, Point p,int lev, int w, int h){
		
		//declare initial ratio height and width to show yellow rectangles only
		int ratioWidth = w;
		int ratioHeight = h;
		
		//set current level to match passed parameter
		int curLevel = lev;

		//if current level is > 1, set height and width of rectangles based on ratio
		if(curLevel>1){
			ratioWidth = (int) (w*RATIO);
			ratioHeight = (int) (h*RATIO);
		}
		
		//determine Point location of rectangles in upper right, lower left, lower right
		Point uR = new Point(p.x+w-ratioWidth, p.y);	
		Point lL = new Point(p.x,p.y+h-ratioHeight);
		Point lR = new Point(p.x+w-ratioWidth,p.y+h-ratioHeight);

		//draw rectangles while curLevel > 0
		if(curLevel>0){

			//draw background rectangle
			g.setColor(Color.blue);
			g.fillRect(p.x, p.y, w, h);
			
			//draw four yellow rectangles in four corners using height/width with ratio applied
			g.setColor(Color.yellow);
			g.fillRect(p.x, p.y, ratioWidth,ratioHeight);
			g.fillRect(uR.x,uR.y, ratioWidth, ratioHeight);
			g.fillRect(lL.x,lL.y,ratioWidth,ratioHeight);
			g.fillRect(lR.x, lR.y, ratioWidth, ratioHeight);

			//subtract one from curLevel for recursion purposes
			curLevel--;
			
			//call drawFractal for four new Point positions and new height/width
			drawFractal(g,p,curLevel,ratioWidth,ratioHeight);
			drawFractal(g,uR,curLevel,ratioWidth,ratioHeight);
			drawFractal(g,lL,curLevel,ratioWidth,ratioHeight);
			drawFractal(g,lR,curLevel,ratioWidth,ratioHeight);
		}			
	}

	/**
	 * sets the title of the whole frame showing the current level; instantiates a Center object 
	 * (inner class) and adds the object to the pane; calls setVisible()
	 */
	private void changeFractal(){
		
		//set title
		setTitle("THE NEW LEVEL IS " + level);
		
		//instantiates center panel and adds to frame
		panelCenter = new Center();
		add(panelCenter,BorderLayout.CENTER);

		//display the window
		setVisible(true);
	}

	/**
	 * builds and displays the frame without the colored fractal; 
	 * registers the buttons; sets size, location, close operation;
	 * calls changeFractal()
	 */
	private void makeWindow(){
				
		//set size and location
		setSize(740, 800);		
		setLocation(100,0);
		
		//specify close button action
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//instantiate panel
		panelNorth = new JPanel();
		
		//instantiate buttons and register action listener
		up = new JButton("level up");
		down = new JButton("level down");
		up.addActionListener(this);
		down.addActionListener(this);
		
		//set layout
		setLayout(new BorderLayout(10,10));
		
		//add panel to frame and buttons to panel
		add(panelNorth,BorderLayout.NORTH);
		panelNorth.add(up);
		panelNorth.add(down);
		
		//call changeFractal
		changeFractal();
	}
	
	/**
	 * adjusts the level value according to the button clicks and calls changeFractal( )
	 */
	public void actionPerformed(ActionEvent e) {
		
		//get action command and assign to string
		String action = e.getActionCommand();
		
		//increase or decrease level and call changeFractal()
		if(action.equals("level up")){
			level++;
			changeFractal();
		}
		else if(action.equals("level down")){
			level--;
			changeFractal();
		}
	}

	/**
	 * Center inner class represents the center panel and the graphics on it. 
	 * Implements the paint method which in turn calls the drawFractal() method.
	 */
	private class Center extends JPanel {

		/**
		 * constructor
		 */
		Center(){
			
		}
		/**
		 *paint class for drawing rectangles
		 */
		public void paint(Graphics g) {
			
			//call superclass
			super.paint(g);

			//draw Fractal
			drawFractal(g,point,level,width,height);

		}

	}
	
}
