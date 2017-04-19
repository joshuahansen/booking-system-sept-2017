package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import users.Employee;

//action for changing availability when logged in as a business
public class AvailTimesActionListener implements ActionListener {
	
	int timeslot;
	int day;
	int employeeNo;
	JButton[][] button;
	JOptionPane optionPane;
	ArrayList<Employee> employees;
	
	//constructor for new action listener
	AvailTimesActionListener(int timeslot, int day, JButton[][] button, JOptionPane optionPane, ArrayList<Employee> employees, int employeeNo)
	{
		this.timeslot = timeslot;
		this.day = day;
		this.employeeNo = employeeNo;
		this.optionPane = optionPane;
		this.employees = employees;
		this.button = button;
	}
	
	//action performed when availability button is selected
	public void actionPerformed(ActionEvent e) {
		//get current button setting (i.e text) and changes it according to possible values
		String buttonText =  button[timeslot][day].getText();
		if(buttonText.equals("Not Available"))
		{
			button[timeslot][day].setText("Available");
			button[timeslot][day].setForeground(Color.BLACK);
			button[timeslot][day].setBackground(Color.lightGray);
			employees.get(employeeNo).setAvailableTime(timeslot, day, "no");
	
		}
		else if(buttonText.equals("Booked"))
		{
			//creates pop up confirmation window to confirm the deletion of a booking
			int confirmResult = JOptionPane.showInternalConfirmDialog(optionPane,
					"Are you sure you want to cancel the booking", "Confirmation",
		             JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(confirmResult == JOptionPane.YES_OPTION)
			{
				button[timeslot][day].setText("Available");
				employees.get(employeeNo).removeBooking(timeslot, day);
				button[timeslot][day].setBackground(Color.lightGray);
				button[timeslot][day].setForeground(Color.BLACK);
			}
		}
		else
		{
			button[timeslot][day].setText("Not Available");
			button[timeslot][day].setForeground(Color.LIGHT_GRAY);
			button[timeslot][day].setBackground(Color.lightGray);
			employees.get(employeeNo).removeAvailibleTime(timeslot, day);
		}
	}
}