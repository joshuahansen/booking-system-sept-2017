package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import users.Employee;

public class DisplayEmployeeAvailability {

	private int timeslots = 10;
	private int days = 5;
	private int x;
	private int y;
	
	public void displayBusEmployeeAvailability(ArrayList<Employee> employees, int emplPos, JLayeredPane employeeAvailabilityLP, JPanel businessMenuPanel)
	{
		System.out.println("Employee position: " + emplPos);
		x = 110;
		y = 150;
		JLabel lblEmployeeAvailability = new JLabel("Employee Availability");
		lblEmployeeAvailability.setForeground(new Color(30, 144, 255));
		lblEmployeeAvailability.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeAvailability.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
		lblEmployeeAvailability.setBounds(50, 0, 700, 100);
		employeeAvailabilityLP.add(lblEmployeeAvailability);
		
		JOptionPane optionPane = new JOptionPane();
		optionPane.setVisible(false);
		optionPane.setBounds(262, 232, 262, 90);
		employeeAvailabilityLP.add(optionPane);
		
		
		JButton button[][]=new JButton[timeslots][days];
		for(int timeslot = 0; timeslot < button.length; timeslot++)
		{
			for(int day = 0; day < button[timeslot].length; day++)
			{
				button[timeslot][day] = new JButton();

				int availTime = employees.get(emplPos).getAvailableTime(timeslot, day);
				System.out.println("available time " + availTime);
				if(availTime == 1)
				{
				button[timeslot][day].setText("Available");
				}
				else if(availTime == 2)
				{
					button[timeslot][day].setText("Booked");
				}
				else
				{
					button[timeslot][day].setText("Not Available");
				}
				button[timeslot][day].setFont(new Font("Tahoma", Font.PLAIN, 16));
				button[timeslot][day].setBounds(x, y, 130, 40);
				button[timeslot][day].addActionListener(new AvailTimesActionListener(timeslot, day, button, optionPane, employees, emplPos) {
				});
				employeeAvailabilityLP.add(button[timeslot][day]);
				x = x+135;
				employeeAvailabilityLP.revalidate();
				employeeAvailabilityLP.repaint();
			}
			x = 110;
			y = y+45;
		}			
	}

	public void displayEmployeeAvailability(ArrayList<Employee> employees, int emplPos, JLayeredPane availableTimesLP, JPanel custMenuPanel)
	{	
		x = 110;
		y = 150;
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
				availableTimesLP.revalidate();
				availableTimesLP.repaint();
				bookingButton[timeslot][day].revalidate();
				bookingButton[timeslot][day].repaint();
				
				x = x+135;
			}
			x = 110;
			y = y+45;
		}	
	}
	
	public void layout(JLayeredPane dayTimes)
	{
		JLabel lblCustMonday = new JLabel("Monday");
		lblCustMonday.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustMonday.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCustMonday.setBounds(110, 120, 120, 20);
		dayTimes.add(lblCustMonday);
		
		JLabel lblCustTuesday = new JLabel("Tuesday");
		lblCustTuesday.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustTuesday.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCustTuesday.setBounds(245, 120, 120, 20);
		dayTimes.add(lblCustTuesday);
		
		JLabel lblCustWednesday = new JLabel("Wednesday");
		lblCustWednesday.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustWednesday.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCustWednesday.setBounds(380, 120, 120, 20);
		dayTimes.add(lblCustWednesday);
		
		JLabel lblCustThursday = new JLabel("Thursday");
		lblCustThursday.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustThursday.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCustThursday.setBounds(515, 120, 120, 20);
		dayTimes.add(lblCustThursday);
		
		JLabel lblCustFriday = new JLabel("Friday");
		lblCustFriday.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustFriday.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCustFriday.setBounds(650, 120, 120, 20);
		dayTimes.add(lblCustFriday);
		
		JLabel lblCustTimeslot1 = new JLabel("8am - 9am");
		lblCustTimeslot1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCustTimeslot1.setBounds(10, 155, 90, 20);
		dayTimes.add(lblCustTimeslot1);
		
		JLabel lblCustTimeslot2 = new JLabel("9am - 10am");
		lblCustTimeslot2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCustTimeslot2.setBounds(10, 200, 100, 20);
		dayTimes.add(lblCustTimeslot2);
		
		JLabel lblCustTimeslot3 = new JLabel("10am - 11am");
		lblCustTimeslot3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCustTimeslot3.setBounds(10, 245, 100, 20);
		dayTimes.add(lblCustTimeslot3);
		
		JLabel lblCustTimeslot4 = new JLabel("11am - 12pm");
		lblCustTimeslot4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCustTimeslot4.setBounds(10, 290, 100, 20);
		dayTimes.add(lblCustTimeslot4);
		
		JLabel lblCustTimeslot5 = new JLabel("12pm - 1pm");
		lblCustTimeslot5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCustTimeslot5.setBounds(10, 335, 100, 20);
		dayTimes.add(lblCustTimeslot5);
		
		JLabel lblCustTimeslot6 = new JLabel("1pm - 2pm");
		lblCustTimeslot6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCustTimeslot6.setBounds(10, 380, 100, 20);
		dayTimes.add(lblCustTimeslot6);
		
		JLabel lblCustTimeslot7 = new JLabel("2pm - 3pm");
		lblCustTimeslot7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCustTimeslot7.setBounds(10, 425, 100, 20);
		dayTimes.add(lblCustTimeslot7);
		
		JLabel lblCustTimeslot8 = new JLabel("3pm - 4pm");
		lblCustTimeslot8.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCustTimeslot8.setBounds(10, 470, 100, 20);
		dayTimes.add(lblCustTimeslot8);
		
		JLabel lblCustTimeslot9 = new JLabel("4pm - 5pm");
		lblCustTimeslot9.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCustTimeslot9.setBounds(10, 515, 100, 20);
		dayTimes.add(lblCustTimeslot9);
		
		JLabel lblCustTimeslot10 = new JLabel("5pm - 6pm");
		lblCustTimeslot10.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCustTimeslot10.setBounds(10, 560, 100, 20);
		dayTimes.add(lblCustTimeslot10);
	}
}
