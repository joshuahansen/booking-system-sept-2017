package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.Registration;
import users.Employee;

public class AddEmployee {
	private JTextField EmployeeNumberData;
	private JTextField EmployeeFNameData;
	private JTextField EmployeeLNameData;

	public void addEmployee(JLayeredPane addEmployeeLP, Registration register, ArrayList<Employee> employees)
	{
		JLabel lblAddEmployee = new JLabel("Add Employee");
		lblAddEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddEmployee.setForeground(new Color(30, 144, 255));
		lblAddEmployee.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
		lblAddEmployee.setBounds(50, 0, 700, 150);
		addEmployeeLP.add(lblAddEmployee);
		
		JLabel lblEmployeeNumber = new JLabel("Employee Number:");
		lblEmployeeNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmployeeNumber.setBounds(100, 150, 150, 40);
		addEmployeeLP.add(lblEmployeeNumber);
		
		EmployeeNumberData = new JTextField();
		EmployeeNumberData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		EmployeeNumberData.setBounds(300, 150, 300, 40);
		addEmployeeLP.add(EmployeeNumberData);
		EmployeeNumberData.setColumns(10);
		
		JLabel lblEmployeeFName = new JLabel("First Name:");
		lblEmployeeFName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmployeeFName.setBounds(100, 240, 150, 40);
		addEmployeeLP.add(lblEmployeeFName);
		
		EmployeeFNameData = new JTextField();
		EmployeeFNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		EmployeeFNameData.setText("");
		EmployeeFNameData.setBounds(300, 240, 300, 40);
		addEmployeeLP.add(EmployeeFNameData);
		EmployeeFNameData.setColumns(10);
		
		JLabel lblEmployeeLName = new JLabel("Last Name:");
		lblEmployeeLName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmployeeLName.setBounds(100, 330, 150, 40);
		addEmployeeLP.add(lblEmployeeLName);
		
		EmployeeLNameData = new JTextField();
		EmployeeLNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		EmployeeLNameData.setText("");
		EmployeeLNameData.setBounds(300, 330, 300, 40);
		addEmployeeLP.add(EmployeeLNameData);
		EmployeeLNameData.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancel.setBounds(200, 500, 130, 50);
		addEmployeeLP.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmployeeNumberData.setText("");
				EmployeeFNameData.setText("");
				EmployeeLNameData.setText("");
				addEmployeeLP.setVisible(false);
			}
		});
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnConfirm.setBounds(550, 500, 130, 50);
		addEmployeeLP.add(btnConfirm);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				register.setEmployeeValues(EmployeeNumberData.getText(), EmployeeFNameData.getText(), EmployeeLNameData.getText(), employees);
				register.addNewEmployeeGui(employees);
				EmployeeNumberData.setText("");
				EmployeeFNameData.setText("");
				EmployeeLNameData.setText("");
				addEmployeeLP.setVisible(false);
			}
		});
	}
}
