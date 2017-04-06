package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import users.Employee;

//Action listener for when the user is a customer and they wish to make a booking
public class BookingActionListener implements ActionListener {
	
	int timeslot;
	int day;
	int employeeNo;
	JButton[][] button;
	JOptionPane optionPane;
	ArrayList<Employee> employees;
	
	//constructor for the booking action listener
	BookingActionListener(int timeslot, int day, JButton[][] button, JOptionPane optionPane, ArrayList<Employee> employees, int employeeNo)
	{
		this.timeslot = timeslot;
		this.day = day;
		this.employeeNo = employeeNo;
		this.optionPane = optionPane;
		this.employees = employees;
		this.button = button;
	}
	
	//action performed when the button is pressed
	public void actionPerformed(ActionEvent e) {
		//get current setting of button
		String buttonText =  button[timeslot][day].getText();
		if(buttonText.equals("Available"))
		{
			//confirmation of making a booking pop up to ask user if they wish to confirm the booking
			int confirmResult = JOptionPane.showInternalConfirmDialog(optionPane,
					"Confirm Booking", "Confirmation",
		             JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			//if they select yes create booking
			if(confirmResult == JOptionPane.YES_OPTION)
			{
				//Set Booking for customer
			};
		}
		else if(buttonText.equals("Booked"))
		{
			//display alert if employee is already booked at this time.
			JOptionPane.showMessageDialog(optionPane, "Employee is already booked at this time. Please select a time when they are available", "Alert", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			//display alert if employee is not available at selected time
			JOptionPane.showMessageDialog(optionPane, "Employee not available at this time. Please select a time when they are available", "Alert", JOptionPane.ERROR_MESSAGE);
		}
	}
}
