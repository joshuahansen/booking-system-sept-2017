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
	int employeeNo;
	JButton[] button;
	JLayeredPane employeeAvailabilityLP;
	JLayeredPane busSelectEmployeeLP;
	JPanel businessMenuPanel;

	
	BusSelectEmployeeActionListener(JButton[] button, int employeeNo, JPanel businessMenuPanel, JLayeredPane employeeAvailabilityLP, JLayeredPane busSelectEmployeeLP)
	{
		this.button = button;
		this.employeeNo = employeeNo;
		this.businessMenuPanel = businessMenuPanel;
		this.employeeAvailabilityLP = employeeAvailabilityLP;
		this.busSelectEmployeeLP = busSelectEmployeeLP;	
	}
	
	public void actionPerformed(ActionEvent e) {
		busSelectEmployeeLP.setVisible(false);
		businessMenuPanel.setVisible(true);
		employeeAvailabilityLP.setVisible(true);
	}
}