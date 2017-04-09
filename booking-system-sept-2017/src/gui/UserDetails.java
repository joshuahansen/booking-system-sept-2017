package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import users.Business;
import users.Customer;

public class UserDetails {
	private JLabel lblBusinessDetails;
	private JLabel lblBusinessName;
	private JLabel lblBusinessNameData;
	private JLabel lblBusinessFirstName;
	private JLabel lblBusinessFirstNameData;
	private JLabel lblBusinessLastName;
	private JLabel lblBusinessLastNameData;
	private JLabel lblBusinessUsername;
	private JLabel lblBusinessUsernameData;
	private JLabel lblBusinessAddress;
	private JLabel lblBusinessAddressData;
	private JLabel lblBusinessContactNumber;
	private JLabel lblBusinessContactNumberData;
	
	private JLabel lblCustomerDetails;
	private JLabel lblCustomerFirstName;
	private JLabel lblCustomerFirstNameData;
	private JLabel lblCustomerLastName;
	private JLabel lblCustomerLastNameData;
	private JLabel lblCustomerUsername;
	private JLabel lblCustomerUserNameData;
	private JLabel lblCustomerAddress;
	private JLabel lblCustomerAddressData;
	private JLabel lblCustomerContactNumber;
	private JLabel lblCustomerContactNumberData;
	
	/*Create labels for business details and add to business details layered pane*/
	public void businessDetails(JLayeredPane businessDetailsLP)
	{
		lblBusinessDetails = new JLabel("Business Details");
		lblBusinessDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblBusinessDetails.setForeground(new Color(30, 144, 255));
		lblBusinessDetails.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
		lblBusinessDetails.setBounds(50, 0, 700, 150);
		businessDetailsLP.add(lblBusinessDetails);
		
		lblBusinessName = new JLabel("Business Name:");
		lblBusinessName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBusinessName.setBounds(150, 170, 150, 40);
		businessDetailsLP.add(lblBusinessName);
		
		lblBusinessNameData = new JLabel("");
		lblBusinessNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBusinessNameData.setBounds(300, 170, 300, 40);
		businessDetailsLP.add(lblBusinessNameData);
		
		lblBusinessFirstName = new JLabel("First Name:");
		lblBusinessFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBusinessFirstName.setBounds(150, 210, 150, 40);
		businessDetailsLP.add(lblBusinessFirstName);
		
		lblBusinessFirstNameData = new JLabel("");
		lblBusinessFirstNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBusinessFirstNameData.setBounds(300, 210, 300, 40);
		businessDetailsLP.add(lblBusinessFirstNameData);
		
		lblBusinessLastName = new JLabel("Last Name:");
		lblBusinessLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBusinessLastName.setBounds(150, 250, 150, 40);
		businessDetailsLP.add(lblBusinessLastName);
		
		lblBusinessLastNameData = new JLabel("");
		lblBusinessLastNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBusinessLastNameData.setBounds(300, 250, 300, 40);
		businessDetailsLP.add(lblBusinessLastNameData);
		
		lblBusinessUsername = new JLabel("Username:");
		lblBusinessUsername.setBounds(150, 290, 150, 40);
		businessDetailsLP.add(lblBusinessUsername);
		lblBusinessUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
	
		lblBusinessUsernameData = new JLabel("");
		lblBusinessUsernameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBusinessUsernameData.setBounds(300, 290, 300, 40);
		businessDetailsLP.add(lblBusinessUsernameData);
		
		lblBusinessAddress = new JLabel("Address:");
		lblBusinessAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBusinessAddress.setBounds(150, 330, 150, 40);
		businessDetailsLP.add(lblBusinessAddress);
		
		lblBusinessAddressData = new JLabel("");
		lblBusinessAddressData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBusinessAddressData.setBounds(300, 330, 300, 40);
		businessDetailsLP.add(lblBusinessAddressData);
		
		lblBusinessContactNumber = new JLabel("Contact Number:");
		lblBusinessContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBusinessContactNumber.setBounds(150, 370, 150, 40);
		businessDetailsLP.add(lblBusinessContactNumber);
		
		lblBusinessContactNumberData = new JLabel("");
		lblBusinessContactNumberData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBusinessContactNumberData.setBounds(300, 370, 300, 40);
		businessDetailsLP.add(lblBusinessContactNumberData);
		
	}
	
	/*Create labels for customer details and add them to customerDetails layered pane*/
	public void customerDetails(JLayeredPane customerDetailsLP)
	{
		lblCustomerDetails = new JLabel("Customer Details");
		lblCustomerDetails.setBounds(50, 0, 700, 150);
		lblCustomerDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerDetails.setForeground(new Color(30, 144, 255));
		lblCustomerDetails.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
		customerDetailsLP.add(lblCustomerDetails);
		
		lblCustomerFirstName = new JLabel("First Name:");
		lblCustomerFirstName.setBounds(150, 170, 150, 40);
		lblCustomerFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		customerDetailsLP.add(lblCustomerFirstName);
		
		lblCustomerFirstNameData = new JLabel("");
		lblCustomerFirstNameData.setBounds(300, 170, 300, 40);
		lblCustomerFirstNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		customerDetailsLP.add(lblCustomerFirstNameData);
		
		lblCustomerLastName = new JLabel("Last Name:");
		lblCustomerLastName.setBounds(150, 210, 150, 40);
		lblCustomerLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		customerDetailsLP.add(lblCustomerLastName);
		
		lblCustomerLastNameData = new JLabel("");
		lblCustomerLastNameData.setBounds(300, 210, 300, 40);
		lblCustomerLastNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		customerDetailsLP.add(lblCustomerLastNameData);
		
		lblCustomerUsername = new JLabel("Username:");
		lblCustomerUsername.setBounds(150, 250, 150, 40);
		lblCustomerUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		customerDetailsLP.add(lblCustomerUsername);
		
		lblCustomerUserNameData = new JLabel("");
		lblCustomerUserNameData.setBounds(300, 250, 300, 40);
		lblCustomerUserNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		customerDetailsLP.add(lblCustomerUserNameData);
		
		lblCustomerAddress = new JLabel("Address:");
		lblCustomerAddress.setBounds(150, 290, 150, 40);
		lblCustomerAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		customerDetailsLP.add(lblCustomerAddress);
		
		lblCustomerAddressData = new JLabel("");
		lblCustomerAddressData.setBounds(300, 290, 300, 40);
		lblCustomerAddressData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		customerDetailsLP.add(lblCustomerAddressData);
		
		lblCustomerContactNumber = new JLabel("Contact Number:");
		lblCustomerContactNumber.setBounds(150, 330, 150, 40);
		lblCustomerContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		customerDetailsLP.add(lblCustomerContactNumber);
		
		lblCustomerContactNumberData = new JLabel("");
		lblCustomerContactNumberData.setBounds(300, 330, 300, 40);
		lblCustomerContactNumberData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		customerDetailsLP.add(lblCustomerContactNumberData);
	}
	/* load customer details from logged in user into labels */
	public void loadCustomerDetails(ArrayList<Customer> customers, int userPos)
	{
		lblCustomerFirstNameData.setText(customers.get(userPos).getFirstName());
		lblCustomerLastNameData.setText(customers.get(userPos).getLastName());
		lblCustomerUserNameData.setText(customers.get(userPos).getUsername());
		lblCustomerAddressData.setText(customers.get(userPos).getAddress());
		lblCustomerContactNumberData.setText(customers.get(userPos).getContactNumber());
	}

	/*Load business details from logged in user to labels*/
	public void loadBusinessDetails(ArrayList<Business> businesses, int userPos)
	{
		lblBusinessNameData.setText(businesses.get(userPos).getBusinessName());
		lblBusinessFirstNameData.setText(businesses.get(userPos).getFirstName());
		lblBusinessLastNameData.setText(businesses.get(userPos).getLastName());
		lblBusinessUsernameData.setText(businesses.get(userPos).getUsername());
		lblBusinessAddressData.setText(businesses.get(userPos).getAddress());
		lblBusinessContactNumberData.setText(businesses.get(userPos).getContactNumber());
	}
}
