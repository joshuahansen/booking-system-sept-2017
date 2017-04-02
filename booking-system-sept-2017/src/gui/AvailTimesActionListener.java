package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import users.Employee;

public class AvailTimesActionListener implements ActionListener {
	
	int timeslot;
	int day;
	int employeeNo;
	JButton[][] button;
	JOptionPane optionPane;
	ArrayList<Employee> employees;
	
	AvailTimesActionListener(int timeslot, int day, JButton[][] button, JOptionPane optionPane, ArrayList<Employee> employees, int employeeNo)
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
		if(buttonText.equals("Not Available"))
		{
			button[timeslot][day].setText("Available");
			employees.get(employeeNo).setAvailableTime(timeslot, day, "no");
	
		}
		else if(buttonText.equals("Booked"))
		{
			int confirmResult = JOptionPane.showInternalConfirmDialog(optionPane,
					"Are you sure you want to cancel the booking", "Confirmation",
		             JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(confirmResult == JOptionPane.YES_OPTION)
			{
				button[timeslot][day].setText("Available");
				employees.get(employeeNo).removeBooking(timeslot, day);
			}
		}
		else
		{
			button[timeslot][day].setText("Not Available");
			employees.get(employeeNo).removeAvailibleTime(timeslot, day);
		}
	}
}

