package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import users.Employee;

public class SelectEmployeeActionListener implements ActionListener {
	int emplPos;
	JButton[] button;
	JLayeredPane availableTimesLP;
	JLayeredPane custSelectEmployeeLP;
	JPanel custMenuPanel;
	DisplayEmployeeAvailability displayAvail;
	ArrayList<Employee> employees;
	
	SelectEmployeeActionListener(JButton[] button, int emplPos, JPanel custMenuPanel, JLayeredPane availableTimesLP, JLayeredPane custSelectEmployeeLP, ArrayList<Employee> employees, DisplayEmployeeAvailability displayAvail)
	{
		this.button = button;
		this.emplPos = emplPos;
		this.custMenuPanel = custMenuPanel;
		this.availableTimesLP = availableTimesLP;
		this.custSelectEmployeeLP = custSelectEmployeeLP;
		this.employees = employees;
		this.displayAvail = displayAvail;
	}
	
	public void actionPerformed(ActionEvent e) {
		custSelectEmployeeLP.setVisible(false);
		custMenuPanel.setVisible(true);
		availableTimesLP.setVisible(true);
		availableTimesLP.revalidate();
		displayAvail.layout(availableTimesLP);
		displayAvail.displayEmployeeAvailability(employees, emplPos, availableTimesLP, custMenuPanel);
	}
}