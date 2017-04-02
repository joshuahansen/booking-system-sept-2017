package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AvailTimesActionListener implements ActionListener {
	
	int timeslot;
	int day;
	JButton[][] button;
	
	AvailTimesActionListener(int timeslot, int day, JButton[][] button)
	{
		this.timeslot = timeslot;
		this.day = day;
		System.out.println(this.timeslot + " " + this.day);
		
		this.button = button;
	}
	
	public void actionPerformed(ActionEvent e) {
		String buttonText =  button[timeslot][day].getText();
		if(buttonText.equals("Not Available"))
		{
			button[timeslot][day].setText("Available");
		}
		else if(buttonText.equals("Booked"))
		{
			button[timeslot][day].setText("Available");
		}
		else
		{
			button[timeslot][day].setText("Not Available");
		}
	}
}

