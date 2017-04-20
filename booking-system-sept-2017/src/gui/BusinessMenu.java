package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.Registration;
import users.Business;
import users.Database;
import users.Employee;

public class BusinessMenu {
	
	private JPanel businessMenuPanel;
	private JLayeredPane busSelectEmployeeLP;
	private JLayeredPane businessDetailsLP;
	private JLayeredPane employeeAvailabilityLP;
	private JLayeredPane addEmployeeLP;
	private JLayeredPane bookingSummaryLP;
	
	public void initBusinessMenu(JFrame frmBookingSystem, UserDetails userDetails,
			Database database, ArrayList<Employee> employees, ArrayList<Business> businesses,
			AddEmployee addEmployee, Registration reg, SelectEmployee selection, DisplayEmployeeAvailability displayAvail, int userPos)
	{
		businessMenuPanel = new JPanel();
		businessMenuPanel.setBounds(0, 0, 1074, 691);
		frmBookingSystem.getContentPane().add(businessMenuPanel);
		businessMenuPanel.setLayout(null);
		
		busSelectEmployeeLP = new JLayeredPane();
		busSelectEmployeeLP.setBounds(0, 0, 800, 691);
		businessMenuPanel.add(busSelectEmployeeLP);
		
		businessDetailsLP = new JLayeredPane();
		businessDetailsLP.setBounds(0, 0, 800, 691);
		busSelectEmployeeLP.add(businessDetailsLP);
		userDetails.businessDetails(businessDetailsLP);
		
		employeeAvailabilityLP = new JLayeredPane();
		employeeAvailabilityLP.setBounds(0, 0, 800, 691);
		businessMenuPanel.add(employeeAvailabilityLP);
		
		addEmployeeLP = new JLayeredPane();
		addEmployeeLP.setBounds(0, 0, 800, 691);
		businessMenuPanel.add(addEmployeeLP);
		
		bookingSummaryLP = new JLayeredPane();
		bookingSummaryLP.setBounds(0, 0, 800, 691);
		businessMenuPanel.add(bookingSummaryLP);
		
			JButton busBtnLogout = new JButton("Logout");
			//busBtnLogout.setIcon(new ImageIcon(logoutImg));
			busBtnLogout.setFont(new Font("Tahoma", Font.BOLD, 24));
			busBtnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					database.deleteAllRecords("EMP_AVAIL");
					database.writeEmplToDB(employees);
//					setAllVisibleFalse();
//					setTextNull();
//					loginPanel.setVisible(true);
					frmBookingSystem.setTitle("Booking System - Login");
				}
			});
			busBtnLogout.setBounds(904, 10, 160, 40);
			businessMenuPanel.add(busBtnLogout);
			
			JButton btnViewDetails = new JButton("View Details");
			btnViewDetails.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					setAllVisibleFalse();
					userDetails.loadBusinessDetails(businesses, userPos);
					businessMenuPanel.setVisible(true);
					businessDetailsLP.setVisible(true);
				}
			});
			btnViewDetails.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnViewDetails.setBounds(904, 108, 160, 80);
			businessMenuPanel.add(btnViewDetails);
			
			JButton btnAddEmployee = new JButton("Add Employee");
			btnAddEmployee.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					setAllVisibleFalse();
					addEmployee.addEmployee(addEmployeeLP, reg, employees);
					businessMenuPanel.setVisible(true);
					addEmployeeLP.setVisible(true);
				}
			});
			btnAddEmployee.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnAddEmployee.setBounds(904, 188, 160, 80);
			businessMenuPanel.add(btnAddEmployee);
			
			JButton btnBookingSummary = new JButton("Booking Summary");
			btnBookingSummary.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					setAllVisibleFalse();
					businessMenuPanel.setVisible(true);
					bookingSummaryLP.setVisible(true);
				}
			});
			btnBookingSummary.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnBookingSummary.setBounds(904, 348, 160, 80);
			businessMenuPanel.add(btnBookingSummary);
			
			JButton btnEmployeeAvailability = new JButton("Employee Availability");
			btnEmployeeAvailability.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					setAllVisibleFalse();
					businessMenuPanel.setVisible(true);
					selection.selectEmployee(busSelectEmployeeLP, employeeAvailabilityLP, businessMenuPanel, employees, displayAvail);
					busSelectEmployeeLP.setVisible(true);
				}
			});
			btnEmployeeAvailability.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnEmployeeAvailability.setBounds(904, 268, 160, 80);
			businessMenuPanel.add(btnEmployeeAvailability);
			
			JLabel lblBookingSummary = new JLabel("Booking Summary");
			lblBookingSummary.setForeground(new Color(30, 144, 255));
			lblBookingSummary.setHorizontalAlignment(SwingConstants.CENTER);
			lblBookingSummary.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
			lblBookingSummary.setBounds(50, 0, 700, 150);
			bookingSummaryLP.add(lblBookingSummary);
	}

}
