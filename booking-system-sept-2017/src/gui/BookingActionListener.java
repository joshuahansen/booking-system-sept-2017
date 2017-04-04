package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import users.Employee;

public class BookingActionListener implements ActionListener {
	
	int timeslot;
	int day;
	int employeeNo;
	JButton[][] button;
	JOptionPane optionPane;
	ArrayList<Employee> employees;
	
	BookingActionListener(int timeslot, int day, JButton[][] button, JOptionPane optionPane, ArrayList<Employee> employees, int employeeNo)
	{
		this.timeslot = timeslot;
		this.day = day;
		this.employeeNo = employeeNo;
		this.optionPane = optionPane;
		this.employees = employees;
		this.button = button;
	}
	
	public void actionPerformed(ActionEvent e) {
		String buttonText =  button[timeslot][day].getText();
		if(buttonText.equals("Available"))
		{
			int confirmResult = JOptionPane.showInternalConfirmDialog(optionPane,
					"Confirm Booking", "Confirmation",
		             JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(confirmResult == JOptionPane.YES_OPTION)
			{
				//Set Booking for customer
			};
		}
		else if(buttonText.equals("Booked"))
		{
			JOptionPane.showMessageDialog(optionPane, "Employee is already booked at this time. Please select a time when they are available", "Alert", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(optionPane, "Employee not available at this time. Please select a time when they are available", "Alert", JOptionPane.ERROR_MESSAGE);
		}
	}
}
