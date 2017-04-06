package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import users.Employee;

// class for when a business selects an employee
public class BusSelectEmployeeActionListener implements ActionListener {
	int emplPos;
	JButton[] button;
	JLayeredPane employeeAvailabilityLP;
	JLayeredPane busSelectEmployeeLP;
	JPanel businessMenuPanel;
	ArrayList<Employee> employees;
	DisplayEmployeeAvailability displayAvail;

	//constructor for action listener
	BusSelectEmployeeActionListener(JButton[] button, int emplPos, JPanel businessMenuPanel, JLayeredPane employeeAvailabilityLP, JLayeredPane busSelectEmployeeLP, ArrayList<Employee> employees, DisplayEmployeeAvailability displayAvail)
	{
		this.button = button;
		this.emplPos = emplPos;
		this.businessMenuPanel = businessMenuPanel;
		this.employeeAvailabilityLP = employeeAvailabilityLP;
		this.busSelectEmployeeLP = busSelectEmployeeLP;	
		this.employees = employees;
		this.displayAvail = displayAvail;
	}
	
	//action performed when the employee button is selected
	public void actionPerformed(ActionEvent e) {
		busSelectEmployeeLP.setVisible(false);
		businessMenuPanel.setVisible(true);
		//remove all old employee availability buttons
		employeeAvailabilityLP.removeAll();
		employeeAvailabilityLP.setVisible(true); 
		//re-populate employee availability panel with correct employee availability buttons
		displayAvail.layout(employeeAvailabilityLP);
		displayAvail.displayBusEmployeeAvailability(employees, emplPos,  employeeAvailabilityLP, businessMenuPanel);	
	}
}