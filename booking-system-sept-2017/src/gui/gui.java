package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.FlowLayout;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.FormSpecs;
//import com.jgoodies.forms.layout.RowSpec;

import users.*;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTable;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

import main.Login;
import main.Registration;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import java.awt.Component;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class gui {

	private JFrame frmBookingSystem;
	private JTextField loginUsernameText;
	private JPasswordField loginPasswordText;
	private JTextField fNameText;
	private JTextField lNameText;
	private JTextField addressText;
	private JTextField usernameText;
	private JTextField phoneText;
	private JPasswordField passwordText;
	private JPasswordField passwordConfirmText;
	private JPanel loginPanel;
	private JPanel registerPanel;
	private JPanel custMenuPanel;
	private JPanel businessMenuPanel;
	private JLayeredPane customerDetailsLP;
	private JLayeredPane availableTimesLP;
	private JLayeredPane businessDetailsLP;
	private JLayeredPane addEmployeeLP;
	private JLayeredPane addOpenHoursLP;
	private JLayeredPane bookingSummaryLP;
	private JLayeredPane employeeAvailabilityLP;
	private final Action action = new SwingAction();
	private int userPos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//create array lists for booking system
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		ArrayList<Employee> employees = new ArrayList<>();
		
		String url = "jdbc:sqlite:./database.db";
		
		//create new init_users object
//		InitUsers users = new InitUsers();
		//initialize both arrays
//		users.init_customers(customers);
//		users.init_businesses(businesses);

		Database database = new Database();
		if(database.connectDatabase(url) == true)
		{
		
//			database.clearTables(database.getConnection());
//			database.initDatabase(database.getConnection());
//			database.defaultValues(database.getConnection());
			
			if(database.readCustDB(customers, database.getConnection()) == true && database.readBusDB(businesses, database.getConnection()) == true)
			{
				System.out.println("Customer Database loaded");
				System.out.println("Business Database loaded");
				if(database.readEmplDB(employees, database.getConnection()) && database.readAvailablityTimes(employees, database.getConnection()))
				{
					System.out.println("Employee Database loaded");
					System.out.println("Employee availible times loaded");
				}
				else
				{
					System.out.println("Can not load employee database");
					System.out.println("Can not load employee availibilities");
				}
			}
			else
			{
				database.clearTables(database.getConnection());
				database.initDatabase(database.getConnection());
				database.defaultValues(database.getConnection());
				if(database.readCustDB(customers, database.getConnection()) == true && database.readBusDB(businesses, database.getConnection()) == true)
				{
					System.out.println("Customer Database loaded");
					System.out.println("Business Database loaded");
					if(database.readEmplDB(employees, database.getConnection()) && database.readAvailablityTimes(employees, database.getConnection()))
					{
						System.out.println("Employee Database loaded");
						System.out.println("Employee available times loaded");
					}
					else
					{
						System.out.println("Can not load employee database");
						System.out.println("Can not load employee availabilities");
					}
				}
			}
			
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui(customers, businesses, employees, database.getConnection(), database);
					window.frmBookingSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//		if(connection != null)
//		{
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				System.out.println("ERROR: Can Not Disconnect Database");
//			}
//		}

	}

	/**
	 * Create the application.
	 */
	public gui(ArrayList<Customer> customers, ArrayList<Business> businesses, ArrayList<Employee> employees, Connection connection, Database database) {
		initialize(customers, businesses, employees, connection, database);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<Customer> customers, ArrayList<Business> businesses, ArrayList<Employee> employees, Connection connection, Database database) {
		frmBookingSystem = new JFrame();
		frmBookingSystem.setResizable(false);
		frmBookingSystem.getContentPane().setBackground(new Color(204, 204, 204));
		frmBookingSystem.getContentPane().setForeground(new Color(204, 204, 204));
		frmBookingSystem.setTitle("Booking System - Login");
		frmBookingSystem.setBounds(100, 100, 1080, 720);
		frmBookingSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBookingSystem.getContentPane().setLayout(null);
		Image okImg = new ImageIcon(this.getClass().getResource("/Ok.png")).getImage();
		Image userImg = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		Image timeImg = new ImageIcon(this.getClass().getResource("/time.png")).getImage();
		Image logoutImg = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
//		Image homeImg = new ImageIcon(this.getClass().getResource("/home.png")).getImage();
		
			
			businessMenuPanel = new JPanel();
			businessMenuPanel.setBounds(0, 0, 1074, 691);
			frmBookingSystem.getContentPane().add(businessMenuPanel);
			businessMenuPanel.setLayout(null);
			
					JButton busBtnLogout = new JButton("Logout");
					busBtnLogout.setIcon(new ImageIcon(logoutImg));
					busBtnLogout.setFont(new Font("Tahoma", Font.BOLD, 24));
					busBtnLogout.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							setAllVisibleFalse();
							setTextNull();
							loginPanel.setVisible(true);
							frmBookingSystem.setTitle("Booking System - Login");
						}
					});
					busBtnLogout.setBounds(904, 10, 160, 40);
					businessMenuPanel.add(busBtnLogout);
					
					JButton btnViewDetails = new JButton("View Details");
					btnViewDetails.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setAllVisibleFalse();
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
							setAllVisibleFalse();
							businessMenuPanel.setVisible(true);
							addEmployeeLP.setVisible(true);
						}
					});
					btnAddEmployee.setFont(new Font("Tahoma", Font.PLAIN, 18));
					btnAddEmployee.setBounds(904, 188, 160, 80);
					businessMenuPanel.add(btnAddEmployee);
					
					JButton btnOpenHours = new JButton("Open Hours");
					btnOpenHours.setFont(new Font("Tahoma", Font.PLAIN, 18));
					btnOpenHours.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setAllVisibleFalse();
							businessMenuPanel.setVisible(true);
							addOpenHoursLP.setVisible(true);
						}
					});
					btnOpenHours.setBounds(904, 268, 160, 80);
					businessMenuPanel.add(btnOpenHours);
					
					JButton btnBookingSummary = new JButton("Booking Summary");
					btnBookingSummary.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setAllVisibleFalse();
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
							setAllVisibleFalse();
							businessMenuPanel.setVisible(true);
							employeeAvailabilityLP.setVisible(true);
						}
					});
					btnEmployeeAvailability.setFont(new Font("Tahoma", Font.PLAIN, 18));
					btnEmployeeAvailability.setBounds(904, 428, 160, 80);
					businessMenuPanel.add(btnEmployeeAvailability);
					
					int timeslots = 10;
					int days = 5;
					int x = 110;
					int y = 150;
					
					employeeAvailabilityLP = new JLayeredPane();
					employeeAvailabilityLP.setBounds(0, 0, 800, 691);
					businessMenuPanel.add(employeeAvailabilityLP);
					
					JButton button[][]=new JButton[timeslots][days];
					for(int timeslot = 0; timeslot < button.length; timeslot++)
					{
						for(int day = 0; day < button[timeslot].length; day++)
						{
							button[timeslot][day] = new JButton();
							int empPos = 0;
							int availTime = employees.get(empPos).getAvailableTime(timeslot, day);
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
							button[timeslot][day].addActionListener(new AvailTimesActionListener(timeslot, day, button) {
							});
							employeeAvailabilityLP.add(button[timeslot][day]);
							x = x+135;
						}
						x = 110;
						y = y+45;
					}			
					
					businessDetailsLP = new JLayeredPane();
					businessDetailsLP.setBounds(0, 0, 800, 691);
					businessMenuPanel.add(businessDetailsLP);
					
					JLabel lblBusinessDetails = new JLabel("Business Details");
					lblBusinessDetails.setHorizontalAlignment(SwingConstants.CENTER);
					lblBusinessDetails.setForeground(new Color(30, 144, 255));
					lblBusinessDetails.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
					lblBusinessDetails.setBounds(50, 0, 700, 150);
					businessDetailsLP.add(lblBusinessDetails);
					
					JLabel lblBusinessName = new JLabel("Business Name:");
					lblBusinessName.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblBusinessName.setBounds(150, 170, 150, 40);
					businessDetailsLP.add(lblBusinessName);
					
					JLabel lblBusinessNameData = new JLabel("");
					lblBusinessNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblBusinessNameData.setBounds(300, 170, 300, 40);
					businessDetailsLP.add(lblBusinessNameData);
					lblBusinessNameData.setText(businesses.get(userPos).getBusinessName());
					
					JLabel lblBusinessFirstName = new JLabel("First Name:");
					lblBusinessFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblBusinessFirstName.setBounds(150, 210, 150, 40);
					businessDetailsLP.add(lblBusinessFirstName);
					
					JLabel lblBusinessFirstNameData = new JLabel("");
					lblBusinessFirstNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblBusinessFirstNameData.setBounds(300, 210, 300, 40);
					businessDetailsLP.add(lblBusinessFirstNameData);
					lblBusinessFirstNameData.setText(businesses.get(userPos).getFirstName());
					
					JLabel lblBusinessLastName = new JLabel("Last Name:");
					lblBusinessLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblBusinessLastName.setBounds(150, 250, 150, 40);
					businessDetailsLP.add(lblBusinessLastName);
					
					JLabel lblBusinessLastNameData = new JLabel("");
					lblBusinessLastNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblBusinessLastNameData.setBounds(300, 250, 300, 40);
					businessDetailsLP.add(lblBusinessLastNameData);
					lblBusinessLastNameData.setText(businesses.get(userPos).getLastName());
					
					JLabel lblBusinessUsername = new JLabel("Username:");
					lblBusinessUsername.setBounds(150, 290, 150, 40);
					businessDetailsLP.add(lblBusinessUsername);
					lblBusinessUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
					
					JLabel lblBusinessUsernameData = new JLabel("");
					lblBusinessUsernameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblBusinessUsernameData.setBounds(300, 290, 300, 40);
					businessDetailsLP.add(lblBusinessUsernameData);
					lblBusinessUsernameData.setText(businesses.get(userPos).getUsername());
					
					JLabel lblBusinessAddress = new JLabel("Address:");
					lblBusinessAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblBusinessAddress.setBounds(150, 330, 150, 40);
					businessDetailsLP.add(lblBusinessAddress);
					
					JLabel lblBusinessAddressData = new JLabel("");
					lblBusinessAddressData.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblBusinessAddressData.setBounds(300, 330, 300, 40);
					businessDetailsLP.add(lblBusinessAddressData);
					lblBusinessAddressData.setText(businesses.get(userPos).getAddress());
					
					JLabel lblBusinessContactNumber = new JLabel("Contact Number:");
					lblBusinessContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblBusinessContactNumber.setBounds(150, 370, 150, 40);
					businessDetailsLP.add(lblBusinessContactNumber);
					
					JLabel lblBusinessContactNumberData = new JLabel("");
					lblBusinessContactNumberData.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblBusinessContactNumberData.setBounds(300, 370, 300, 40);
					businessDetailsLP.add(lblBusinessContactNumberData);
					lblBusinessContactNumberData.setText(businesses.get(userPos).getContactNumber());
					
					addEmployeeLP = new JLayeredPane();
					addEmployeeLP.setBounds(0, 0, 800, 691);
					businessMenuPanel.add(addEmployeeLP);
					
					JLabel lblAddEmployee = new JLabel("Add Employee");
					lblAddEmployee.setHorizontalAlignment(SwingConstants.CENTER);
					lblAddEmployee.setForeground(new Color(30, 144, 255));
					lblAddEmployee.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
					lblAddEmployee.setBounds(50, 0, 700, 150);
					addEmployeeLP.add(lblAddEmployee);
					
					addOpenHoursLP = new JLayeredPane();
					addOpenHoursLP.setBounds(0, 0, 800, 691);
					businessMenuPanel.add(addOpenHoursLP);
					
					JLabel lblOpenHours = new JLabel("Open Hours");
					lblOpenHours.setHorizontalAlignment(SwingConstants.CENTER);
					lblOpenHours.setForeground(new Color(30, 144, 255));
					lblOpenHours.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
					lblOpenHours.setBounds(50, 0, 700, 150);
					addOpenHoursLP.add(lblOpenHours);
					
					bookingSummaryLP = new JLayeredPane();
					bookingSummaryLP.setBounds(0, 0, 800, 691);
					businessMenuPanel.add(bookingSummaryLP);
					
					JLabel lblBookingSummary = new JLabel("Booking Summary");
					lblBookingSummary.setForeground(new Color(30, 144, 255));
					lblBookingSummary.setHorizontalAlignment(SwingConstants.CENTER);
					lblBookingSummary.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
					lblBookingSummary.setBounds(50, 0, 700, 150);
					bookingSummaryLP.add(lblBookingSummary);
		
		//set all pages visibility to false
//		setAllVisibleFalse();

			loginPanel = new JPanel();
			loginPanel.setBounds(0, 0, 1074, 691);
			frmBookingSystem.getContentPane().add(loginPanel);
			loginPanel.setLayout(null);
			
			JLabel lblErrorMsg = new JLabel("");
			lblErrorMsg.setBounds(300, 250, 800, 40);
			loginPanel.add(lblErrorMsg);
			lblErrorMsg.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lblErrorMsg.setForeground(Color.red);
			
			JButton btnLogin = new JButton("Login");
			btnLogin.setBounds(450, 450, 140, 40);
			loginPanel.add(btnLogin);
			btnLogin.setIcon(new ImageIcon(okImg));
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int login = 0;
					Login newLogin = new Login();
					String password = new String(loginPasswordText.getPassword());
					String username = loginUsernameText.getText();
					if(!username.equals(""))
					{
						newLogin.setUsernamePassword(username, password);
						login = newLogin.login(customers, businesses);
						userPos = newLogin.getUserPosition();
						if(login == 1)
						{
							setAllVisibleFalse();
							custMenuPanel.setVisible(true);
							frmBookingSystem.setTitle("Booking System - Customer Menu");
						}
						else if(login == 2)
						{
							setAllVisibleFalse();
							businessMenuPanel.setVisible(true);
							frmBookingSystem.setTitle("Booking System - Business Menu");
						}
						else
						{
							lblErrorMsg.setText("Username or Password incorrect. Please Try Again.");
						}
					}
					else
					{
						lblErrorMsg.setText("Username or Password incorrect. Please Try Again.");
					}
				}
			});
			btnLogin.setForeground(new Color(0, 0, 0));
			btnLogin.setBackground(UIManager.getColor("Button.background"));
			btnLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
			
			JLabel lblUsername = new JLabel("Username:");
			lblUsername.setBounds(320, 290, 120, 40);
			loginPanel.add(lblUsername);
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 24));
			
			loginUsernameText = new JTextField();
			loginUsernameText.setBounds(450, 290, 380, 40);
			loginPanel.add(loginUsernameText);
			loginUsernameText.setFont(new Font("Tahoma", Font.PLAIN, 24));
			loginUsernameText.setColumns(10);
			loginUsernameText.addActionListener(new ActionListener() {
				   @Override
				    public void actionPerformed(ActionEvent e) {
				      loginPasswordText.requestFocusInWindow();    
				    }
				}); 
			
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setBounds(325, 370, 120, 40);
			loginPanel.add(lblPassword);
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
			
			loginPasswordText = new JPasswordField();
			loginPasswordText.setBounds(450, 370, 380, 40);
			loginPanel.add(loginPasswordText);
			loginPasswordText.setFont(new Font("Tahoma", Font.PLAIN, 24));
			loginPasswordText.addActionListener(new ActionListener() {
				   @Override
				    public void actionPerformed(ActionEvent e) {
				      btnLogin.requestFocusInWindow();    
				    }
				}); 
						
			JButton btnRegister = new JButton("Register");
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					registerPanel.setVisible(true);
					loginPanel.setVisible(false);
					frmBookingSystem.setTitle("Booking System - Register");	
					
				}
			});
			btnRegister.setBounds(668, 450, 162, 40);
			loginPanel.add(btnRegister);
			btnRegister.setIcon(new ImageIcon(userImg));
			btnRegister.setBackground(UIManager.getColor("Button.background"));
			btnRegister.setFont(new Font("Tahoma", Font.BOLD, 24));
			
			JLabel lblBooking = new JLabel("Booking System");
			lblBooking.setBounds(190, 0, 700, 150);
			loginPanel.add(lblBooking);
			lblBooking.setForeground(new Color(30, 144, 255));
			lblBooking.setHorizontalAlignment(SwingConstants.CENTER);
			lblBooking.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 54));
			
			JLabel label = new JLabel("");
			label.setBounds(130, 130, 130, 150);
			loginPanel.add(label);
			label.setIcon(new ImageIcon(timeImg));
		
		
		registerPanel = new JPanel();
		registerPanel.setBounds(0, 0, 1074, 691);
		frmBookingSystem.getContentPane().add(registerPanel);
		registerPanel.setLayout(null);
		
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblFirstName.setBounds(94, 170, 150, 40);
		registerPanel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLastName.setBounds(94, 230, 150, 40);
		registerPanel.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAddress.setBounds(94, 290, 150, 40);
		registerPanel.add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPhone.setBounds(94, 410, 150, 40);
		registerPanel.add(lblPhone);
		
		JLabel lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUsername_1.setBounds(94, 350, 150, 40);
		registerPanel.add(lblUsername_1);
		
		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPassword_1.setBounds(94, 470, 150, 40);
		registerPanel.add(lblPassword_1);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblConfirmPassword.setBounds(94, 530, 200, 40);
		registerPanel.add(lblConfirmPassword);
		
		JLabel lblNewUserAccount = new JLabel("New User Account");
		lblNewUserAccount.setForeground(new Color(30, 144, 255));
		lblNewUserAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewUserAccount.setFont(new Font("Serif", Font.PLAIN, 54));
		lblNewUserAccount.setBounds(237, 50, 600, 90);
		registerPanel.add(lblNewUserAccount);
		
		fNameText = new JTextField();
		fNameText.setFont(new Font("Tahoma", Font.PLAIN, 24));
		fNameText.setBounds(325, 170, 350, 40);
		registerPanel.add(fNameText);
		fNameText.setColumns(10);
		fNameText.addActionListener(new ActionListener() {
			   @Override
			    public void actionPerformed(ActionEvent e) {
			      lNameText.requestFocusInWindow();    
			    }
			});
		
		JLabel lblfNameError = new JLabel("");
		lblfNameError.setBounds(675, 170, 350, 40);
		registerPanel.add(lblfNameError);
		lblfNameError.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblfNameError.setForeground(Color.red);
		
		lNameText = new JTextField();
		lNameText.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lNameText.setBounds(325, 230, 350, 40);
		registerPanel.add(lNameText);
		lNameText.setColumns(10);
		lNameText.addActionListener(new ActionListener() {
			   @Override
			    public void actionPerformed(ActionEvent e) {
			      addressText.requestFocusInWindow();    
			    }
			});
		JLabel lbllNameError = new JLabel("");
		lbllNameError.setBounds(675, 230, 350, 40);
		registerPanel.add(lbllNameError);
		lbllNameError.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbllNameError.setForeground(Color.red);
		
		addressText = new JTextField();
		addressText.setFont(new Font("Tahoma", Font.PLAIN, 24));
		addressText.setBounds(325, 290, 350, 40);
		registerPanel.add(addressText);
		addressText.setColumns(10);
		addressText.addActionListener(new ActionListener() {
			   @Override
			    public void actionPerformed(ActionEvent e) {
			      usernameText.requestFocusInWindow();    
			    }
			});
		
		JLabel lblAddressError = new JLabel("");
		lblAddressError.setBounds(675, 290, 350, 40);
		registerPanel.add(lblAddressError);
		lblAddressError.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAddressError.setForeground(Color.red);
		
		usernameText = new JTextField();
		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 24));
		usernameText.setBounds(325, 350, 350, 40);
		registerPanel.add(usernameText);
		usernameText.setColumns(10);
		usernameText.addActionListener(new ActionListener() {
			   @Override
			    public void actionPerformed(ActionEvent e) {
			      phoneText.requestFocusInWindow();    
			    }
			});
		JLabel lblUsernameError = new JLabel("");
		lblUsernameError.setBounds(675, 350, 350, 40);
		registerPanel.add(lblUsernameError);
		lblUsernameError.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUsernameError.setForeground(Color.red);
		
		phoneText = new JTextField();
		phoneText.setFont(new Font("Tahoma", Font.PLAIN, 24));
		phoneText.setBounds(325, 410, 350, 40);
		registerPanel.add(phoneText);
		phoneText.setColumns(10);
		phoneText.addActionListener(new ActionListener() {
			   @Override
			    public void actionPerformed(ActionEvent e) {
			      passwordText.requestFocusInWindow();    
			    }
			});
		JLabel lblPhoneError = new JLabel("");
		lblPhoneError.setBounds(675, 410, 350, 40);
		registerPanel.add(lblPhoneError);
		lblPhoneError.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPhoneError.setForeground(Color.red);
		
		passwordText = new JPasswordField();
		passwordText.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordText.setBounds(325, 470, 350, 40);
		registerPanel.add(passwordText);
		passwordText.addActionListener(new ActionListener() {
			   @Override
			    public void actionPerformed(ActionEvent e) {
			      passwordConfirmText.requestFocusInWindow();    
			    }
			});
		
		passwordConfirmText = new JPasswordField();
		passwordConfirmText.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordConfirmText.setBounds(325, 530, 350, 40);
		registerPanel.add(passwordConfirmText);
		
		JLabel lblPasswordMatchError = new JLabel("");
		lblPasswordMatchError.setBounds(675, 530, 350, 40);
		registerPanel.add(lblPasswordMatchError);
		lblPasswordMatchError.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPasswordMatchError.setForeground(Color.red);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password = new String(passwordText.getPassword());
				String confPassword = new String(passwordConfirmText.getPassword());
				if(password.equals(confPassword))
				{
					Registration reg = new Registration();
					reg.setValues(fNameText.getText(), lNameText.getText(), addressText.getText(), phoneText.getText(), usernameText.getText(), password,customers, businesses);
					if(reg.registerNewCust(customers, businesses) == true)
					{
						setAllVisibleFalse();
						loginPanel.setVisible(true);
						
						database.custToString(customers, customers.size()-1);
						database.writeNewCustToDB(customers, customers.size()-1, connection);
					}
					else
					{
						lblUsernameError.setText("Username must be longer than 6");
						lblfNameError.setText("Invalid First Name");
						lbllNameError.setText("Invalid Last Name");
						lblAddressError.setText("Invalid Address");
						lblPhoneError.setText("Invalid Phone Number");
					}
				}
				else
				{
					lblPasswordMatchError.setText("Passwords do not match.");
					passwordText.setText("");
					passwordConfirmText.setText("");
				}
				
			}
		});
		btnConfirm.setBounds(525, 598, 150, 40);
		registerPanel.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerPanel.setVisible(false);
				setTextNull();
				loginPanel.setVisible(true);
				frmBookingSystem.setTitle("Booking System - Login");
				lblUsernameError.setText("");
				lblfNameError.setText("");
				lbllNameError.setText("");
				lblAddressError.setText("");
				lblPhoneError.setText("");
				lblErrorMsg.setText("");
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnCancel.setBounds(325, 598, 130, 40);
		registerPanel.add(btnCancel);
		
		custMenuPanel = new JPanel();
		custMenuPanel.setBounds(0, 0, 1074, 691);
		frmBookingSystem.getContentPane().add(custMenuPanel);
		custMenuPanel.setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(904, 10, 160, 40);
		custMenuPanel.add(btnLogout);
		btnLogout.setIcon(new ImageIcon(logoutImg));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 24));
				
				
				JButton btnAvailableTimes = new JButton("Available Times");
				btnAvailableTimes.setBounds(904, 190, 160, 80);
				custMenuPanel.add(btnAvailableTimes);
				btnAvailableTimes.setFont(new Font("Tahoma", Font.PLAIN, 18));
				
				availableTimesLP = new JLayeredPane();
				availableTimesLP.setBounds(0, 0, 800, 691);
				custMenuPanel.add(availableTimesLP);
				availableTimesLP.setLayout(null);				
				
				JLabel lblAvailableTimes = new JLabel("Available Times");
				lblAvailableTimes.setBounds(50, 0, 700, 150);
				availableTimesLP.add(lblAvailableTimes);
				lblAvailableTimes.setHorizontalAlignment(SwingConstants.CENTER);
				lblAvailableTimes.setForeground(new Color(30, 144, 255));
				lblAvailableTimes.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
				
				customerDetailsLP = new JLayeredPane();
				customerDetailsLP.setBounds(0, 0, 800, 691);
				customerDetailsLP.setAlignmentX(Component.LEFT_ALIGNMENT);
				custMenuPanel.add(customerDetailsLP);
				customerDetailsLP.setLayout(null);
				
				JLabel lblCustomerDetails = new JLabel("Customer Details");
				lblCustomerDetails.setBounds(50, 0, 700, 150);
				lblCustomerDetails.setHorizontalAlignment(SwingConstants.CENTER);
				lblCustomerDetails.setForeground(new Color(30, 144, 255));
				lblCustomerDetails.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
				customerDetailsLP.add(lblCustomerDetails);
				
				JLabel lblCustomerFirstName = new JLabel("First Name:");
				lblCustomerFirstName.setBounds(150, 170, 150, 40);
				lblCustomerFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
				customerDetailsLP.add(lblCustomerFirstName);
				
				JLabel lblCustomerFirstNameData = new JLabel("");
				lblCustomerFirstNameData.setBounds(300, 170, 300, 40);
				lblCustomerFirstNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
				customerDetailsLP.add(lblCustomerFirstNameData);
				
				
				JLabel lblCustomerLastName = new JLabel("Last Name:");
				lblCustomerLastName.setBounds(150, 210, 150, 40);
				lblCustomerLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
				customerDetailsLP.add(lblCustomerLastName);
				
				JLabel lblCustomerLastNameData = new JLabel("");
				lblCustomerLastNameData.setBounds(300, 210, 300, 40);
				lblCustomerLastNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
				customerDetailsLP.add(lblCustomerLastNameData);
				
				
				JLabel lblCustomerUsername = new JLabel("Username:");
				lblCustomerUsername.setBounds(150, 250, 150, 40);
				lblCustomerUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
				customerDetailsLP.add(lblCustomerUsername);
				
				JLabel lblCustomerUserNameData = new JLabel("");
				lblCustomerUserNameData.setBounds(300, 250, 300, 40);
				lblCustomerUserNameData.setFont(new Font("Tahoma", Font.PLAIN, 18));
				customerDetailsLP.add(lblCustomerUserNameData);
				
				JLabel lblCustomerAddress = new JLabel("Address:");
				lblCustomerAddress.setBounds(150, 290, 150, 40);
				lblCustomerAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
				customerDetailsLP.add(lblCustomerAddress);
				
				JLabel lblCustomerAddressData = new JLabel("");
				lblCustomerAddressData.setBounds(300, 290, 300, 40);
				lblCustomerAddressData.setFont(new Font("Tahoma", Font.PLAIN, 18));
				customerDetailsLP.add(lblCustomerAddressData);
				
				
				JLabel lblCustomerContactNumber = new JLabel("Contact Number:");
				lblCustomerContactNumber.setBounds(150, 330, 150, 40);
				lblCustomerContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
				customerDetailsLP.add(lblCustomerContactNumber);
				
				JLabel lblCustomerContactNumberData = new JLabel("");
				lblCustomerContactNumberData.setBounds(300, 330, 300, 40);
				lblCustomerContactNumberData.setFont(new Font("Tahoma", Font.PLAIN, 18));
				customerDetailsLP.add(lblCustomerContactNumberData);
				
				JButton btnViewCustomer = new JButton("Vew Details");
				btnViewCustomer.setBounds(904, 110, 160, 80);
				custMenuPanel.add(btnViewCustomer);
				btnViewCustomer.setFont(new Font("Tahoma", Font.PLAIN, 18));
				btnViewCustomer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						lblCustomerFirstNameData.setText(customers.get(userPos).getFirstName());
						lblCustomerLastNameData.setText(customers.get(userPos).getLastName());
						lblCustomerUserNameData.setText(customers.get(userPos).getUsername());
						lblCustomerAddressData.setText(customers.get(userPos).getAddress());
						lblCustomerContactNumberData.setText(customers.get(userPos).getContactNumber());
						setAllVisibleFalse();
						custMenuPanel.setVisible(true);
						customerDetailsLP.setVisible(true);
					}
				});
		
				btnAvailableTimes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setAllVisibleFalse();
						custMenuPanel.setVisible(true);
						availableTimesLP.setVisible(true);
					}
				});

				btnLogout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setAllVisibleFalse();
						loginPanel.setVisible(true);
						setTextNull();
						frmBookingSystem.setTitle("Booking System - Login");
						lblErrorMsg.setText("");
					}
				});
	
				//set all pages visibility to false
				setAllVisibleFalse();
				loginPanel.setVisible(true);
	}

	private void setAllVisibleFalse()
	{
		loginPanel.setVisible(false);
		registerPanel.setVisible(false);
		custMenuPanel.setVisible(false);
		customerDetailsLP.setVisible(false);
		availableTimesLP.setVisible(false);
		businessMenuPanel.setVisible(false);
		businessDetailsLP.setVisible(false);
		addEmployeeLP.setVisible(false);
		addOpenHoursLP.setVisible(false);
		bookingSummaryLP.setVisible(false);
		employeeAvailabilityLP.setVisible(false);
	}
	private void setTextNull()
	{
		loginUsernameText.setText("");
		loginPasswordText.setText("");		
	}
//	private void resetErrorMsg()
//	{
//		lblUsernameError.setText("");
//		lblfNameError.setText("");
//		lbllNameError.setText("");
//		lblAddressError.setText("");
//		lblPhoneError.setText("");
//		lblErrorMsg.setText("");
//	}
	
//	private void employeeAvailiblity()
//	{	
//		JLabel lblEmployeeAvailability = new JLabel("Employee Availability");
//		lblEmployeeAvailability.setForeground(new Color(30, 144, 255));
//		lblEmployeeAvailability.setHorizontalAlignment(SwingConstants.CENTER);
//		lblEmployeeAvailability.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
//		lblEmployeeAvailability.setBounds(50, 0, 700, 100);
//		employeeAvailabilityLP.add(lblEmployeeAvailability);
//		
//		JLabel lblMonday = new JLabel("Monday");
//		lblMonday.setHorizontalAlignment(SwingConstants.CENTER);
//		lblMonday.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblMonday.setBounds(125, 100, 120, 20);
//		employeeAvailabilityLP.add(lblMonday);
//		
//		JLabel lblTuesday = new JLabel("Tuesday");
//		lblTuesday.setHorizontalAlignment(SwingConstants.CENTER);
//		lblTuesday.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblTuesday.setBounds(260, 100, 120, 20);
//		employeeAvailabilityLP.add(lblTuesday);
//		
//		JLabel lblWednesday = new JLabel("Wednesday");
//		lblWednesday.setHorizontalAlignment(SwingConstants.CENTER);
//		lblWednesday.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblWednesday.setBounds(395, 100, 120, 20);
//		employeeAvailabilityLP.add(lblWednesday);
//		
//		JLabel lblThursday = new JLabel("Thursday");
//		lblThursday.setHorizontalAlignment(SwingConstants.CENTER);
//		lblThursday.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblThursday.setBounds(530, 100, 120, 20);
//		employeeAvailabilityLP.add(lblThursday);
//		
//		JLabel lblFriday = new JLabel("Friday");
//		lblFriday.setHorizontalAlignment(SwingConstants.CENTER);
//		lblFriday.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblFriday.setBounds(665, 100, 120, 20);
//		employeeAvailabilityLP.add(lblFriday);
//		
//		JLabel lblTimeslot1 = new JLabel("8am - 9am");
//		lblTimeslot1.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblTimeslot1.setBounds(10, 135, 90, 20);
//		employeeAvailabilityLP.add(lblTimeslot1);
//		
//		JLabel lblTimeslot2 = new JLabel("9am - 10am");
//		lblTimeslot2.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblTimeslot2.setBounds(10, 185, 100, 20);
//		employeeAvailabilityLP.add(lblTimeslot2);
//		
//		JLabel lblTimeslot3 = new JLabel("10am - 11am");
//		lblTimeslot3.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblTimeslot3.setBounds(10, 235, 100, 20);
//		employeeAvailabilityLP.add(lblTimeslot3);
//		
//		JLabel lblTimeslot4 = new JLabel("11am - 12pm");
//		lblTimeslot4.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblTimeslot4.setBounds(10, 285, 100, 20);
//		employeeAvailabilityLP.add(lblTimeslot4);
//		
//		JLabel lblTimeslot5 = new JLabel("12pm - 1pm");
//		lblTimeslot5.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblTimeslot5.setBounds(10, 335, 100, 20);
//		employeeAvailabilityLP.add(lblTimeslot5);
//		
//		JLabel lblTimeslot6 = new JLabel("1pm - 2pm");
//		lblTimeslot6.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblTimeslot6.setBounds(10, 385, 100, 20);
//		employeeAvailabilityLP.add(lblTimeslot6);
//		
//		JLabel lblTimeslot7 = new JLabel("2pm - 3pm");
//		lblTimeslot7.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblTimeslot7.setBounds(10, 435, 100, 20);
//		employeeAvailabilityLP.add(lblTimeslot7);
//		
//		JLabel lblpmpm = new JLabel("3pm - 4pm");
//		lblpmpm.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblpmpm.setBounds(10, 485, 100, 20);
//		employeeAvailabilityLP.add(lblpmpm);
//		
//		JLabel lblpmpm_1 = new JLabel("4pm - 5pm");
//		lblpmpm_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblpmpm_1.setBounds(10, 535, 100, 20);
//		employeeAvailabilityLP.add(lblpmpm_1);
//		
//		JLabel lblpmpm_2 = new JLabel("5pm - 6pm");
//		lblpmpm_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblpmpm_2.setBounds(10, 585, 100, 20);
//		employeeAvailabilityLP.add(lblpmpm_2);
//		
//		JRadioButton rdbtnNotAvailable = new JRadioButton("Not Available");
//		rdbtnNotAvailable.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable.setSelected(true);
//		rdbtnNotAvailable.setBounds(125, 135, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable);
//		
//		JRadioButton rdbtnNewRadioButton = new JRadioButton("Not Available");
//		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNewRadioButton.setBounds(260, 135, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNewRadioButton);
//		
//		JRadioButton rdbtnNotAvailable_1 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_1.setBounds(395, 135, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_1);
//		
//		JRadioButton rdbtnNotAvailable_2 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_2.setBounds(530, 135, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_2);
//		
//		JRadioButton rdbtnNotAvailable_3 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_3.setBounds(665, 135, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_3);
//		
//		JRadioButton rdbtnNotAvailable_4 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_4.setBounds(125, 185, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_4);
//		
//		JRadioButton rdbtnNotAvailable_5 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_5.setBounds(260, 185, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_5);
//		
//		JRadioButton rdbtnNotAvailable_6 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_6.setBounds(395, 185, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_6);
//		
//		JRadioButton rdbtnNotAvailable_7 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_7.setBounds(530, 185, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_7);
//		
//		JRadioButton rdbtnNotAvailable_8 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_8.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_8.setBounds(665, 185, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_8);
//		
//		JRadioButton rdbtnNotAvailable_9 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_9.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_9.setBounds(125, 235, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_9);
//		
//		JRadioButton rdbtnNotAvailable_10 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_10.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_10.setBounds(260, 235, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_10);
//		
//		JRadioButton rdbtnNotAvailable_11 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_11.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_11.setBounds(395, 235, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_11);
//		
//		JRadioButton rdbtnNotAvailable_12 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_12.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_12.setBounds(530, 235, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_12);
//		
//		JRadioButton rdbtnNotAvailable_13 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_13.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_13.setBounds(665, 235, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_13);
//		
//		JRadioButton rdbtnNotAvailable_14 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_14.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_14.setBounds(125, 285, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_14);
//		
//		JRadioButton rdbtnNotAvailable_15 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_15.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_15.setBounds(260, 285, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_15);
//		
//		JRadioButton rdbtnNotAvailable_16 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_16.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_16.setBounds(395, 285, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_16);
//		
//		JRadioButton rdbtnNotAvailable_17 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_17.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_17.setBounds(530, 285, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_17);
//		
//		JRadioButton rdbtnNotAvailable_18 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_18.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_18.setBounds(665, 285, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_18);
//		
//		JRadioButton rdbtnNotAvailable_19 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_19.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_19.setBounds(125, 335, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_19);
//		
//		JRadioButton rdbtnNotAvailable_20 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_20.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_20.setBounds(260, 335, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_20);
//		
//		JRadioButton rdbtnNotAvailible_21 = new JRadioButton("Not Availible");
//		rdbtnNotAvailible_21.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailible_21.setBounds(395, 335, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailible_21);
//		
//		JRadioButton rdbtnNotAvailible_22 = new JRadioButton("Not Availible");
//		rdbtnNotAvailible_22.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailible_22.setBounds(530, 335, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailible_22);
//		
//		JRadioButton rdbtnNotAvailible_23 = new JRadioButton("Not Availible");
//		rdbtnNotAvailible_23.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailible_23.setBounds(665, 335, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailible_23);
//		
//		JRadioButton rdbtnNotAvailible_24 = new JRadioButton("Not Availible");
//		rdbtnNotAvailible_24.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailible_24.setBounds(125, 385, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailible_24);
//		
//		JRadioButton rdbtnNotAvailible_25 = new JRadioButton("Not Availible");
//		rdbtnNotAvailible_25.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailible_25.setBounds(260, 385, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailible_25);
//		
//		JRadioButton rdbtnNotAvailible_26 = new JRadioButton("Not Availible");
//		rdbtnNotAvailible_26.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailible_26.setBounds(395, 385, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailible_26);
//		
//		JRadioButton rdbtnNotAvailible_27 = new JRadioButton("Not Availible");
//		rdbtnNotAvailible_27.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailible_27.setBounds(530, 385, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailible_27);
//		
//		JRadioButton rdbtnNotAvailible_28 = new JRadioButton("Not Availible");
//		rdbtnNotAvailible_28.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailible_28.setBounds(665, 385, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailible_28);
//		
//		JRadioButton rdbtnNotAvailible_29 = new JRadioButton("Not Availible");
//		rdbtnNotAvailible_29.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailible_29.setBounds(125, 435, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailible_29);
//		
//		JRadioButton rdbtnNotAvailible_30 = new JRadioButton("Not Availible");
//		rdbtnNotAvailible_30.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailible_30.setBounds(260, 435, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailible_30);
//		
//		JRadioButton rdbtnNotAvailible_31 = new JRadioButton("Not Availible");
//		rdbtnNotAvailible_31.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailible_31.setBounds(395, 435, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailible_31);
//		
//		JRadioButton rdbtnNotAvailible_32 = new JRadioButton("Not Availible");
//		rdbtnNotAvailible_32.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailible_32.setBounds(530, 435, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailible_32);
//		
//		JRadioButton rdbtnNotAvailable_33 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_33.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_33.setBounds(665, 435, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_33);
//		
//		JRadioButton rdbtnNotAvailable_34 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_34.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_34.setBounds(125, 485, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_34);
//		
//		JRadioButton rdbtnNotAvailable_35 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_35.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_35.setBounds(260, 485, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_35);
//		
//		JRadioButton rdbtnNotAvailable_36 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_36.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_36.setBounds(395, 485, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_36);
//		
//		JRadioButton rdbtnNotAvailable_37 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_37.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_37.setBounds(530, 485, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_37);
//		
//		JRadioButton rdbtnNotAvailable_38 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_38.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_38.setBounds(665, 485, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_38);
//		
//		JRadioButton rdbtnNotAvailable_39 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_39.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_39.setBounds(125, 535, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_39);
//		
//		JRadioButton rdbtnNotAvailable_40 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_40.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_40.setBounds(260, 535, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_40);
//		
//		JRadioButton rdbtnNotAvailable_41 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_41.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_41.setBounds(395, 535, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_41);
//		
//		JRadioButton rdbtnNotAvailable_42 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_42.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_42.setBounds(530, 535, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_42);
//		
//		JRadioButton rdbtnNotAvailable_43 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_43.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_43.setBounds(665, 535, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_43);
//		
//		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Not Available");
//		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNewRadioButton_1.setBounds(125, 585, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNewRadioButton_1);
//		
//		JRadioButton rdbtnNotAvailable_44 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_44.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_44.setBounds(260, 585, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_44);
//		
//		JRadioButton rdbtnNotAvailable_45 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_45.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_45.setBounds(395, 585, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_45);
//		
//		JRadioButton rdbtnNotAvailable_46 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_46.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_46.setBounds(530, 585, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_46);
//		
//		JRadioButton rdbtnNotAvailable_47 = new JRadioButton("Not Available");
//		rdbtnNotAvailable_47.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNotAvailable_47.setBounds(665, 585, 120, 20);
//		employeeAvailabilityLP.add(rdbtnNotAvailable_47);
//		
//		JButton btnChangeAvailability = new JButton("Change Availability");
//		btnChangeAvailability.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		btnChangeAvailability.setBounds(280, 620, 180, 60);
//		employeeAvailabilityLP.add(btnChangeAvailability);
//	}
	
	
 	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
