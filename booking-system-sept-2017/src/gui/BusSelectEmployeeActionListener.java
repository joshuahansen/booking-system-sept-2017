package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import users.Employee;

public class BusSelectEmployeeActionListener implements ActionListener {
	int emplPos;
	JButton[] button;
	JLayeredPane employeeAvailabilityLP;
	JLayeredPane busSelectEmployeeLP;
	JPanel businessMenuPanel;
	ArrayList<Employee> employees;
	DisplayEmployeeAvailability displayAvail ;

	
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
	
	public void actionPerformed(ActionEvent e) {
		busSelectEmployeeLP.setVisible(false);
		businessMenuPanel.setVisible(true);
		employeeAvailabilityLP.setVisible(true); 
		displayAvail.layout(employeeAvailabilityLP);
		displayAvail.displayBusEmployeeAvailability(employees, emplPos,  employeeAvailabilityLP, businessMenuPanel);
		employeeAvailabilityLP.revalidate();
		employeeAvailabilityLP.repaint();
		
	}
}