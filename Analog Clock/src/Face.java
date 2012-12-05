
/*
* Amy Denham
* CS161-01 Fall 2012
* Project 3
*
*/
import java.awt.*;
import javax.swing.*;

/**
 * Face class which draws all the graphics on the clock
 */
public class Face extends JPanel{

	final int C_X=400;	//center of circle x location
	final int C_Y=300;	//center of circle y location		
	final int radius = 200; //radius of circle
	private int second=0;
	private int minute=0;
	private int hour=0;

	/**
	 * Constructor class, starts clock off at 12:00
	 */
	Face(){
		setSecond(0);
		setMinute(0);
		setHour(12,0);
	}
	/**
	 * paint method that draws the face and hands using variables 
	 * determined by setSecond, setMinute, and setHour methods
	 */
	public void paint(Graphics g) {
		
		super.paint(g);
	    drawFace(g);		
		drawSecondHand(second,g);
		drawMinuteHand(minute,g);
		drawHourHand(hour,g);

	}
	/**
	 * set Second hand based on value passed, converting to correct degree
	 * @param s
	 */
	public void setSecond(int s){
		//second hand = number of seconds times 6 degrees minus 90 degrees 
		second=s*6-90;
	}
	
	/**
	 * Set Minute hand based on value passed, converting to correct degree
	 * @param m
	 */
	public void setMinute(int m){
		//minute hand = number of minutes times 6 degrees minus 90 degrees
		minute=m*6-90;
	}
	
	/**
	 * Set hour hand based on value passed, converting to correct degree	
	 * @param h
	 */
	public void setHour(int h, int i){
		
		//hour hand = number of hours times 30 degrees - 90 degrees plus interim passed by variable
		//interim is a multiple of six degrees, depending on what minute in the hour the clock starts
		hour=(h*30) - 90 + i;
	}
	
	/**
	 * Draw face of clock including outer circle, inner filled circles, tick markets, numbers
	 * @param g
	 */
	private void drawFace(Graphics g){
		
		//draw outer circle
		g.setColor(Color.black);
		drawCircle(C_X,C_Y,radius,g);
		
		//draw larger inner filled oval 1 pixel smaller than outer
		g.setColor(Color.orange);
		drawFilledCircle(C_X,C_Y,radius-1,g);
	    
	    //draw smaller inner filled oval 20 pixels smaller than outer
	    g.setColor(Color.yellow);
		drawFilledCircle(C_X,C_Y,radius-20,g);	    	   	    
	
		//draw tick marks
		drawTickMarks(g);
		
		//draw numbers
		drawNumbers(g);
		
	}
	
	/**
	 * Method to plot x axis at perimeter of circle
	 * @param degrees
	 * @param radius - to determine distance based on center coordinates
	 * @return
	 */	
	private int plotX(int degrees, int radius){
		double radians = 0;
		int O_X = 0;
		
		//calculate radians
		radians = degrees * Math.PI / 180;
		
		//calculate x using cosine function
	    O_X = C_X + (int)(radius * Math.cos(radians));
		
	    return O_X;
	}
		
	/**
	 * Method to plot y axis at perimeter of circle
	 * @param degrees
	 * @param radius - to determine distance based on center coordinates
	 * @return
	 */
	private int plotY(int degrees, int radius){
		double radians = 0;
		int O_Y = 0;
		
		//calculate radians
		radians = degrees * Math.PI / 180;
		
		//calculate y using sin function
	    O_Y = C_Y + (int)(radius * Math.sin(radians));
		
	    return O_Y;
	}

	/**
	 * Method to draw numbers along the perimeter of the clock face
	 * @param radius
	 * @param x center of circle x coordinate
	 * @param y center of circle y coordinate
	 * @param g Graphics variable
	 */
	private void drawNumbers(Graphics g){
		
		int degrees=0; //degree location ofnumber
		int L_X=0;	//x location of number
		int L_Y=0;	//y location of number
		
		//set font
		Font f = new Font("Dialog", Font.BOLD , 20);
		g.setFont(f);

		//add number labels to clock
		for(int k = 1; k<=12;k++){
			
			//get degrees
			degrees = 30*(k-3);
			
			//get x and y plots 
			//perimeter location, plus/minus half the size of the font for centering
			L_X = plotX(degrees, this.radius+50)-10;
			L_Y = plotY(degrees, this.radius+50)+10;
				
			//draw number
			g.drawString(String.valueOf(k),L_X,L_Y);
		}

	}
	
	/**
	 * Method to draw tick marks along the perimeter of the clock face
	 * @param radius
	 * @param x center of circle x coordinate
	 * @param y center of circle y coordinate
	 * @param g Graphics variable
	 */
	private void drawTickMarks(Graphics g){

		int P_X=0; //x on the perimeter
		int P_Y=0; //y on the perimeter
		int O_X=0; //outer x of tick mark
		int O_Y=0; //outer y of tick mark
		
		//set color of tick marks
	    g.setColor(Color.black);
	    
	    //draw short tick marks every six degrees
	    for(int d=0;d<360;d+=6){
	    	//get x,y on perimeter
	    	O_X=plotX(d,radius);
	    	O_Y=plotY(d,radius);
	    	//get x,y 10 pixels past perimeter
	    	P_X=plotX(d,radius+10);
	    	P_Y=plotY(d,radius+10);
	    	//draw tick marks
	    	g.drawLine(O_X,O_Y,P_X,P_Y);
	    }

	    //draw long tick marks every 30 degrees
	    for(int d=0;d<360;d+=30){
	    	//get x,y on perimeter
	    	O_X=plotX(d,radius);
	    	O_Y=plotY(d,radius);
	    	//get x,y 10 pixels past perimeter
	    	P_X=plotX(d,radius+20);
	    	P_Y=plotY(d,radius+20);
	    	//draw tick marks
	    	g.drawLine(O_X,O_Y,P_X,P_Y);
	    }

	}

	/**
	 * Method to draw a second hand
	 * @param degrees, to determine second placement
	 * @param g Graphics variable
	 */
	private void drawSecondHand(int degrees, Graphics g){
		
		int P_X=0; //x on the perimeter
		int P_Y=0; //y on the perimeter

		//set color of hand
	    g.setColor(Color.red);

	    //get x,y locations on perimeter
    	P_X=plotX(degrees,radius-5);
    	P_Y=plotY(degrees,radius-5);

    	//draw line
	    g.drawLine(C_X, C_Y, P_X, P_Y);

	}
	
	/**
	 * Method to draw a minute hand
	 * @param degrees, to determine minute placement
	 * @param g Graphics variable
	 */
	private void drawMinuteHand(int degrees, Graphics g){
		
		int P_X=0; //x on the perimeter
		int P_Y=0; //y on the perimeter

		//set color of hand
	    g.setColor(Color.blue);

	    //get x,y locations on perimeter
    	P_X=plotX(degrees,radius-20);
    	P_Y=plotY(degrees,radius-20);

    	//draw line
	    g.drawLine(C_X, C_Y, P_X, P_Y);

	}

	/**
	 * Method to draw an hour hand
	 * @param degrees, to determine hour placement
	 * @param g Graphics variable
	 */
	private void drawHourHand(int degrees, Graphics g){
		
		int P_X=0; //x on the perimeter
		int P_Y=0; //y on the perimeter

		//set color of hand
	    g.setColor(Color.blue);

	    //get x,y locations on perimeter
    	P_X=plotX(degrees,radius-40);
    	P_Y=plotY(degrees,radius-40);
    	
    	//draw three lines next to each other to make it stand out
	    g.drawLine(C_X, C_Y, P_X, P_Y);
	    g.drawLine(C_X+1, C_Y-1, P_X+1, P_Y-1);
	    g.drawLine(C_X+2, C_Y-2, P_X+2, P_Y-2);

	}

	/**
	 * Method to draw the outline of a circle
	 * @param x center of circle x coordinate
	 * @param y center of circle y coordinate
	 * @param radius
	 * @param g
	 */
	private void drawCircle(int x, int y, int radius, Graphics g){
		g.drawOval(x - radius, y - radius, radius*2, radius*2);
	}

	/**
	 * Method to draw a filled circle
	 * @param x center of circle x coordinate
	 * @param y center of circle y coordinate
	 * @param radius
	 * @param g
	 */
	private void drawFilledCircle(int x, int y, int radius, Graphics g){
		g.fillOval(x - radius, y - radius, radius*2, radius*2);
	}

}
