package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import users.Employee;
//class to print employee button for each employee in the array
public class SelectEmployee {

	private int x;
	private int y;
	
	//creates buttons for each employee and displays them for the customer
	public void selectPersonalTrainer(JLayeredPane custSelectEmployeeLP, JLayeredPane availableTimesLP, JPanel custMenuPanel, ArrayList<Employee> employees, DisplayEmployeeAvailability displayAvail)
	{
		x = 110;
		y = 150;
		
		JLabel lblSelectEmployee = new JLabel("Select a Personal Trainer");
		lblSelectEmployee.setBounds(50, 0, 700, 150);
		lblSelectEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectEmployee.setForeground(new Color(30, 144, 255));
		lblSelectEmployee.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
		custSelectEmployeeLP.add(lblSelectEmployee);
		
		JButton employeeButton[] = new JButton[employees.size()];
		for(int emplNo = 0; emplNo < employeeButton.length; emplNo++)
		{
			employeeButton[emplNo] = new JButton();
	
			employeeButton[emplNo].setText(employees.get(emplNo).getName());
			
			employeeButton[emplNo].setFont(new Font("Tahoma", Font.PLAIN, 16));
			employeeButton[emplNo].setBounds(x, y, 180, 80);
			custSelectEmployeeLP.add(employeeButton[emplNo]);
			
			x = x+185;
			//puts button on next line to display all employees
			if(emplNo == 2 || emplNo == 5 || emplNo == 8 || emplNo == 11) 
			{
				x = 110;
				y = y+85;
			}
			
			employeeButton[emplNo].addActionListener(new SelectEmployeeActionListener(employeeButton, emplNo, custMenuPanel, availableTimesLP, custSelectEmployeeLP, employees, displayAvail) {
			});;
		}
	}
	//creates buttons for each employee and displays them for the business user
	public void selectEmployee(JLayeredPane busSelectEmployeeLP, JLayeredPane employeeAvailabilityLP, JPanel businessMenuPanel, ArrayList<Employee> employees, DisplayEmployeeAvailability displayAvail)
	{
		x = 110;
		y = 150;
		
		JLabel lblBusSelectEmployee = new JLabel("Select an Employee");
		lblBusSelectEmployee.setBounds(50, 0, 700, 150);
		lblBusSelectEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblBusSelectEmployee.setForeground(new Color(30, 144, 255));
		lblBusSelectEmployee.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
		busSelectEmployeeLP.add(lblBusSelectEmployee);
		
		JButton busEmployeeButton[] = new JButton[employees.size()];
		for(int emplNo = 0; emplNo < busEmployeeButton.length; emplNo++)
		{
			busEmployeeButton[emplNo] = new JButton();

			busEmployeeButton[emplNo].setText(employees.get(emplNo).getName());
			
			busEmployeeButton[emplNo].setFont(new Font("Tahoma", Font.PLAIN, 16));
			busEmployeeButton[emplNo].setBounds(x, y, 180, 80);
			busSelectEmployeeLP.add(busEmployeeButton[emplNo]);

			x = x+185;
			if(emplNo == 2 || emplNo == 5 || emplNo == 8 || emplNo == 11) 
			{
				x = 110;
				y = y+85;
			}
			
			busEmployeeButton[emplNo].addActionListener(new BusSelectEmployeeActionListener(busEmployeeButton, emplNo, businessMenuPanel, employeeAvailabilityLP, busSelectEmployeeLP, employees, displayAvail) {			
			
			});
		}
	}
}
