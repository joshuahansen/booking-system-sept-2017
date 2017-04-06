package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		availableTimesLP.repaint();
		displayAvail.layout(availableTimesLP);
		//displayAvail.displayEmployeeAvailability(employees, emplPos, availableTimesLP, custMenuPanel);
		
		int x = 110;
		int y = 150;
		int timeslots = 10;
		int days = 5; 
		
		System.out.println("Employee position: " + emplPos);
		JLabel lblAvailableTimes = new JLabel("Available Times");
		lblAvailableTimes.setBounds(50, 0, 700, 150);
		availableTimesLP.add(lblAvailableTimes);
		lblAvailableTimes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvailableTimes.setForeground(new Color(30, 144, 255));
		lblAvailableTimes.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
		
		JOptionPane bookingOptionPane = new JOptionPane();
		bookingOptionPane.setVisible(false);
		bookingOptionPane.setBounds(262, 232, 262, 90);
		availableTimesLP.add(bookingOptionPane);
		
		JButton bookingButton[][]=new JButton[timeslots][days];
		for(int timeslot = 0; timeslot < bookingButton.length; timeslot++)
		{	
			for(int day = 0; day < bookingButton[timeslot].length; day++)
			{
				bookingButton[timeslot][day] = new JButton();
				int availTime = employees.get(emplPos).getAvailableTime(timeslot, day);
				System.out.println("available time " + availTime);
				if(availTime == 1)
				{
				bookingButton[timeslot][day].setText("Available");
				}
				else if(availTime == 2)
				{
					bookingButton[timeslot][day].setText("Booked");
				}
				else
				{
					bookingButton[timeslot][day].setText("Not Available");
				}
				bookingButton[timeslot][day].setFont(new Font("Tahoma", Font.PLAIN, 16));
				bookingButton[timeslot][day].setBounds(x, y, 130, 40);
	
				bookingButton[timeslot][day].addActionListener(new BookingActionListener(timeslot, day, bookingButton, bookingOptionPane, employees, emplPos) {
				});
				
				availableTimesLP.add(bookingButton[timeslot][day]);
				System.out.println("Button added " + bookingButton[timeslot][day].getText());
				availableTimesLP.revalidate();
				availableTimesLP.repaint();
				x = x+135;
			}
			x = 110;
			y = y+45;
		}
		
	}
}