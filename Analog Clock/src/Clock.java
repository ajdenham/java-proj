
/*
* Amy Denham
* CS161-01 Fall 2012
* Project 3
*
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

/**
 * Clock class which displays clock face, runs clock, and manages action buttons
 *
 */
public class Clock extends JFrame implements ActionListener {

	private JFrame frame;
	private JPanel southPanel;
	private JPanel southPanelTop;
	private JPanel southPanelBottom;
	private JLabel labelMessage;
	private JTextField textBox;
	private JButton buttonRun;
	private JButton buttonStop;
	private JButton buttonReset;
	private JButton buttonSet;
	private Face clockFace;
	private int second=0;
	private int minute=0;
	private int hour=0;
	private int interim=0;
	private Timer timer;

	/**
	 * Constructor
	 */
	Clock(){
		
	}
	
	/**
	 * Build clock, set frame settings
	 */
	public void buildClock(){
		
		final int WINDOW_WIDTH = 800;
		final int WINDOW_HEIGHT = 700;
		
		//create new frame
		frame = new JFrame();
		
		//set title
		frame.setTitle("Amy's Clock");
		
		//set size and location
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);		
		frame.setLocation(100,50);
		
		//specify close button action
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add border layout to frame
		frame.setLayout(new BorderLayout(10,10));

		//build Panels
		buildClockFace();
		buildSouthPanel();
		
		//display the window
		frame.setVisible(true);
	}
	
	/**
	 * Build main panel containing clock face, use Face class
	 */
	private void buildClockFace(){
					
		//instantiate clock face
		clockFace = new Face();

		//add to frame
		frame.add(clockFace);

	}

	/**
	 * Build south panel including all buttons and labels
	 */
	private void buildSouthPanel(){
		//instantiate south panel and subpanels
		southPanel = new JPanel();
		southPanelTop = new JPanel();
		southPanelBottom = new JPanel();
		
		//instantiate fields/buttons
		labelMessage = new JLabel("Enter time, use format 15:45");
		textBox = new JTextField();
		buttonRun = new JButton("Run the Clock");
		buttonStop = new JButton("Stop the Clock");
		buttonReset = new JButton("Reset the Clock");
		buttonSet = new JButton("Set the Clock");
		textBox.setText("12:00");
		
		//add action listener
		buttonRun.addActionListener(this);
		buttonStop.addActionListener(this);
		buttonReset.addActionListener(this);
		buttonSet.addActionListener(this);
		
		//add graphics to panel and subpanels
		frame.add(southPanel,BorderLayout.SOUTH);
		southPanel.add(southPanelTop);
		southPanel.add(southPanelBottom);
		southPanelTop.add(buttonRun);
		southPanelTop.add(buttonStop);
		southPanelTop.add(buttonReset);
		southPanelBottom.add(labelMessage);
		southPanelBottom.add(textBox);
		southPanelBottom.add(buttonSet);
		southPanel.setLayout(new GridLayout(0,1));
		
	}
		
	/**
	 * Method to run clock, determining when seconds, minutes,and hours should change
	 * triggered once per second by timer task
	 */
	private void runClock(){
    	//increase one second or reset to 0
    	if(second<59)
    		second++;
    	else
    		second=0;
    	
    	//increase one minute for every 60 seconds, or reset to 0
    	if(minute<59 && second == 0)
    		minute++;
    	else if (minute==59 && second==0)
    		minute=0;
    	
    	//increase one hour every 60 minutes, or reset to 1
    	if(minute==0 && second==0 && hour < 24)
    		hour++;
    	else if (minute==0 && second==0 && hour==24)
    		hour = 1;
    	
    	//add six degrees to hour hand every 12 minutes for correct hour hand placement
    	if(minute==0 && second ==0)
    		interim=0;
    	else if(minute % 12==0 && second==0)
    		interim+=6;
    	
    	//reset hands and repaint
    	clockFace.setSecond(second);
    	clockFace.setMinute(minute);
    	clockFace.setHour(hour,interim);    	
    	clockFace.repaint();
	}
	/**
	 * Action Listener for buttons
	 */	
	public void actionPerformed(ActionEvent event) {
		//Get button clicked
		String command = event.getActionCommand();
		
		if(command.equals("Run the Clock")){			
			//call reminder task with schedule for once a second
			Reminder(1);
			//disable all buttons except stop button
			buttonRun.setEnabled(false);
			buttonSet.setEnabled(false);
			buttonReset.setEnabled(false);
		}
		else if(command.equals("Stop the Clock")){
			//stop scheduled reminder task
			timer.cancel();
			//reenable all buttons
			buttonRun.setEnabled(true);
			buttonSet.setEnabled(true);
			buttonReset.setEnabled(true);
		}
		else if(command.equals("Reset the Clock")){			
			resetClock();
		}
		else if(command.equals("Set the Clock")){			
			setClock(textBox.getText());
		}
	}

	/**
	 * Method to reset the clock to 12:00
	 */
	private void resetClock(){
		//reset second,minute to 0, hour to 12, interim to 0 and repaint
		hour=12;
		minute=0;
		second=0;
		interim=0;
		clockFace.setSecond(second);
		clockFace.setMinute(minute);
		clockFace.setHour(hour,interim);
		textBox.setText("12:00");
		clockFace.repaint();
	}
	/**
	 * Method to set clock time based on value in text box
	 */
	private void setClock(String s){
		int h;
		int m;
		//error when length <> 5
		if(s.length()!=5){
			clockError();
		}			
		//check to make sure first two and last two characters are digits, with a colon in the middle
		else if(s.charAt(2)==':' && Character.isDigit(s.charAt(0))
				&& Character.isDigit(s.charAt(1)) && Character.isDigit(s.charAt(3))
				&& Character.isDigit(s.charAt(4))){
			//convert first two chars to int
			h = Integer.parseInt(s.substring(0,2));
			//convert last two chars to int
			m = Integer.parseInt(s.substring(3,5));
			//if valid time, set clock time
			if(h<=23 && m <=59){
				//set hour and minute to current values
				hour = h;
				minute = m;
				second=0;
				//determine hour hand placement with interim variable
				//minutes divided by 12, ignore the remainder, multiply by six for correct degree
				interim = (m/12)*6;
				//reset clock
				clockFace.setSecond(second);
				clockFace.setMinute(minute);
				clockFace.setHour(hour, interim);
				clockFace.repaint();
			}
			else
				clockError();
			
		}
		else{
			clockError();
		}
			
	}
	
	/**
	 * Method to show error message when invalid time is entered
	 */
	private void clockError(){

		 //show error message
		 JOptionPane.showMessageDialog(null, "This is an invalid time.\n\nPlease try again using the format \'05:30\'",
				 "Error", JOptionPane.ERROR_MESSAGE);
		 //reset clock
		 resetClock();
	}
	
	/**
	 * Timer reminder method which instantiates timer and sets scheduled rate
	 */
	 public void Reminder(int seconds) {
		 //instantiate timer
		 timer = new Timer();
		 //create schedule rate for number of seconds passed as variable
		 timer.scheduleAtFixedRate(new RemindTask(), seconds * 1000, seconds * 1000);
	}

/**
 * Timer task which runs clock
 *
 */
	class RemindTask extends TimerTask {
		 public void run() {
			 runClock();
		 }
	}
}
