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
	int employeeNo;
	JButton[] button;
	JLayeredPane availableTimesLP;
	JLayeredPane custSelectEmployeeLP;
	JPanel custMenuPanel;

	
	SelectEmployeeActionListener(JButton[] button, int employeeNo, JPanel custMenuPanel, JLayeredPane availableTimesLP, JLayeredPane custSelectEmployeeLP)
	{
		this.button = button;
		this.employeeNo = employeeNo;
		this.custMenuPanel = custMenuPanel;
		this.availableTimesLP = availableTimesLP;
		this.custSelectEmployeeLP = custSelectEmployeeLP;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		custSelectEmployeeLP.setVisible(false);
		custMenuPanel.setVisible(true);
		availableTimesLP.setVisible(true);

	}
}