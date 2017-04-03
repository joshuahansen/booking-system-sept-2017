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
import javax.swing.JOptionPane;

public class Gui {

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
	private JLayeredPane custSelectEmployeeLP;
	private JLayeredPane busSelectEmployeeLP;
	private final Action action = new SwingAction();
	private int userPos;
	private int empPos = 0;
	
//	public static final int INFORMATION_MESSAGE;
//	public static final int YES_NO_OPTION = 0;
	
	
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
		
//			database.clearTables();
//			database.initDatabase();
//			database.defaultValues();
			
			if(database.readCustDB(customers) == true && database.readBusDB(businesses) == true)
			{
				System.out.println("Customer Database loaded");
				System.out.println("Business Database loaded");
				if(database.readEmplDB(employees) && database.readAvailablityTimes(employees))
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
				database.clearTables();
				database.initDatabase();
				database.defaultValues();
				if(database.readCustDB(customers) == true && database.readBusDB(businesses) == true)
				{
					System.out.println("Customer Database loaded");
					System.out.println("Business Database loaded");
					if(database.readEmplDB(employees) && database.readAvailablityTimes(employees))
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
					Gui window = new Gui(customers, businesses, employees, database.getConnection(), database);
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
	public Gui(ArrayList<Customer> customers, ArrayList<Business> businesses, ArrayList<Employee> employees, Connection connection, Database database) {
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
							database.deleteAllRecords("EMP_AVAIL");
							database.writeEmplToDB(employees);
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
							busSelectEmployeeLP.setVisible(true);
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
					
					JLabel lblEmployeeAvailability = new JLabel("Employee Availability");
					lblEmployeeAvailability.setForeground(new Color(30, 144, 255));
					lblEmployeeAvailability.setHorizontalAlignment(SwingConstants.CENTER);
					lblEmployeeAvailability.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
					lblEmployeeAvailability.setBounds(50, 0, 700, 100);
					employeeAvailabilityLP.add(lblEmployeeAvailability);
					
					JLabel lblMonday = new JLabel("Monday");
					lblMonday.setHorizontalAlignment(SwingConstants.CENTER);
					lblMonday.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblMonday.setBounds(110, 120, 120, 20);
					employeeAvailabilityLP.add(lblMonday);
					
					JLabel lblTuesday = new JLabel("Tuesday");
					lblTuesday.setHorizontalAlignment(SwingConstants.CENTER);
					lblTuesday.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblTuesday.setBounds(245, 120, 120, 20);
					employeeAvailabilityLP.add(lblTuesday);
					
					JLabel lblWednesday = new JLabel("Wednesday");
					lblWednesday.setHorizontalAlignment(SwingConstants.CENTER);
					lblWednesday.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblWednesday.setBounds(380, 120, 120, 20);
					employeeAvailabilityLP.add(lblWednesday);
					
					JLabel lblThursday = new JLabel("Thursday");
					lblThursday.setHorizontalAlignment(SwingConstants.CENTER);
					lblThursday.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblThursday.setBounds(515, 120, 120, 20);
					employeeAvailabilityLP.add(lblThursday);
					
					JLabel lblFriday = new JLabel("Friday");
					lblFriday.setHorizontalAlignment(SwingConstants.CENTER);
					lblFriday.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblFriday.setBounds(650, 120, 120, 20);
					employeeAvailabilityLP.add(lblFriday);
					
					JLabel lblTimeslot1 = new JLabel("8am - 9am");
					lblTimeslot1.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblTimeslot1.setBounds(10, 155, 90, 20);
					employeeAvailabilityLP.add(lblTimeslot1);
					
					JLabel lblTimeslot2 = new JLabel("9am - 10am");
					lblTimeslot2.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblTimeslot2.setBounds(10, 200, 100, 20);
					employeeAvailabilityLP.add(lblTimeslot2);
					
					JLabel lblTimeslot3 = new JLabel("10am - 11am");
					lblTimeslot3.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblTimeslot3.setBounds(10, 245, 100, 20);
					employeeAvailabilityLP.add(lblTimeslot3);
					
					JLabel lblTimeslot4 = new JLabel("11am - 12pm");
					lblTimeslot4.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblTimeslot4.setBounds(10, 290, 100, 20);
					employeeAvailabilityLP.add(lblTimeslot4);
					
					JLabel lblTimeslot5 = new JLabel("12pm - 1pm");
					lblTimeslot5.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblTimeslot5.setBounds(10, 335, 100, 20);
					employeeAvailabilityLP.add(lblTimeslot5);
					
					JLabel lblTimeslot6 = new JLabel("1pm - 2pm");
					lblTimeslot6.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblTimeslot6.setBounds(10, 380, 100, 20);
					employeeAvailabilityLP.add(lblTimeslot6);
					
					JLabel lblTimeslot7 = new JLabel("2pm - 3pm");
					lblTimeslot7.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblTimeslot7.setBounds(10, 425, 100, 20);
					employeeAvailabilityLP.add(lblTimeslot7);
					
					JLabel lblpmpm = new JLabel("3pm - 4pm");
					lblpmpm.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblpmpm.setBounds(10, 470, 100, 20);
					employeeAvailabilityLP.add(lblpmpm);
					
					JLabel lblpmpm_1 = new JLabel("4pm - 5pm");
					lblpmpm_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblpmpm_1.setBounds(10, 515, 100, 20);
					employeeAvailabilityLP.add(lblpmpm_1);
					
					JLabel lblpmpm_2 = new JLabel("5pm - 6pm");
					lblpmpm_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblpmpm_2.setBounds(10, 560, 100, 20);
					employeeAvailabilityLP.add(lblpmpm_2);
					
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
							button[timeslot][day].addActionListener(new AvailTimesActionListener(timeslot, day, button, optionPane, employees, empPos) {
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
			
			JOptionPane loginErrorPane = new JOptionPane();
			loginErrorPane.setVisible(false);
			loginErrorPane.setBounds(150, 150, 262, 90);
			loginPanel.add(loginErrorPane);
			
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
							JOptionPane.showMessageDialog(loginErrorPane, "Username or Password incorrect. Please Try Again.", "Alert", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(loginErrorPane, "Username or Password incorrect. Please Try Again.", "Alert", JOptionPane.ERROR_MESSAGE);
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
						database.writeNewCustToDB(customers, customers.size()-1);
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
				
				x = 110;
				y = 150;
				
				JLabel lblCustMonday = new JLabel("Monday");
				lblCustMonday.setHorizontalAlignment(SwingConstants.CENTER);
				lblCustMonday.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblCustMonday.setBounds(110, 120, 120, 20);
				availableTimesLP.add(lblCustMonday);
				
				JLabel lblCustTuesday = new JLabel("Tuesday");
				lblCustTuesday.setHorizontalAlignment(SwingConstants.CENTER);
				lblCustTuesday.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblCustTuesday.setBounds(245, 120, 120, 20);
				availableTimesLP.add(lblCustTuesday);
				
				JLabel lblCustWednesday = new JLabel("Wednesday");
				lblCustWednesday.setHorizontalAlignment(SwingConstants.CENTER);
				lblCustWednesday.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblCustWednesday.setBounds(380, 120, 120, 20);
				availableTimesLP.add(lblCustWednesday);
				
				JLabel lblCustThursday = new JLabel("Thursday");
				lblCustThursday.setHorizontalAlignment(SwingConstants.CENTER);
				lblCustThursday.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblCustThursday.setBounds(515, 120, 120, 20);
				availableTimesLP.add(lblCustThursday);
				
				JLabel lblCustFriday = new JLabel("Friday");
				lblCustFriday.setHorizontalAlignment(SwingConstants.CENTER);
				lblCustFriday.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblCustFriday.setBounds(650, 120, 120, 20);
				availableTimesLP.add(lblCustFriday);
				
				JLabel lblCustTimeslot1 = new JLabel("8am - 9am");
				lblCustTimeslot1.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblCustTimeslot1.setBounds(10, 155, 90, 20);
				availableTimesLP.add(lblCustTimeslot1);
				
				JLabel lblCustTimeslot2 = new JLabel("9am - 10am");
				lblCustTimeslot2.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblCustTimeslot2.setBounds(10, 200, 100, 20);
				availableTimesLP.add(lblCustTimeslot2);
				
				JLabel lblCustTimeslot3 = new JLabel("10am - 11am");
				lblCustTimeslot3.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblCustTimeslot3.setBounds(10, 245, 100, 20);
				availableTimesLP.add(lblCustTimeslot3);
				
				JLabel lblCustTimeslot4 = new JLabel("11am - 12pm");
				lblCustTimeslot4.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblCustTimeslot4.setBounds(10, 290, 100, 20);
				availableTimesLP.add(lblCustTimeslot4);
				
				JLabel lblCustTimeslot5 = new JLabel("12pm - 1pm");
				lblCustTimeslot5.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblCustTimeslot5.setBounds(10, 335, 100, 20);
				availableTimesLP.add(lblCustTimeslot5);
				
				JLabel lblCustTimeslot6 = new JLabel("1pm - 2pm");
				lblCustTimeslot6.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblCustTimeslot6.setBounds(10, 380, 100, 20);
				availableTimesLP.add(lblCustTimeslot6);
				
				JLabel lblCustTimeslot7 = new JLabel("2pm - 3pm");
				lblCustTimeslot7.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblCustTimeslot7.setBounds(10, 425, 100, 20);
				availableTimesLP.add(lblCustTimeslot7);
				
				JLabel lblCustTimeslot8 = new JLabel("3pm - 4pm");
				lblCustTimeslot8.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblCustTimeslot8.setBounds(10, 470, 100, 20);
				availableTimesLP.add(lblCustTimeslot8);
				
				JLabel lblCustTimeslot9 = new JLabel("4pm - 5pm");
				lblCustTimeslot9.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblCustTimeslot9.setBounds(10, 515, 100, 20);
				availableTimesLP.add(lblCustTimeslot9);
				
				JLabel lblCustTimeslot10 = new JLabel("5pm - 6pm");
				lblCustTimeslot10.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblCustTimeslot10.setBounds(10, 560, 100, 20);
				availableTimesLP.add(lblCustTimeslot10);
				
				JOptionPane bookingOptionPane = new JOptionPane();
				bookingOptionPane.setVisible(false);
				bookingOptionPane.setBounds(262, 232, 262, 90);
				availableTimesLP.add(bookingOptionPane);
				
				JButton bookingButton[][]=new JButton[timeslots][days];
				for(int timeslot = 0; timeslot < button.length; timeslot++)
				{
					for(int day = 0; day < button[timeslot].length; day++)
					{
						bookingButton[timeslot][day] = new JButton();
						int availTime = employees.get(empPos).getAvailableTime(timeslot, day);
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
						bookingButton[timeslot][day].addActionListener(new BookingActionListener(timeslot, day, button, bookingOptionPane, employees, empPos) {
						});
						availableTimesLP.add(bookingButton[timeslot][day]);
						x = x+135;
					}
					x = 110;
					y = y+45;
				}	
				
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
				
				x = 110;
				y = 150;
				
				custSelectEmployeeLP = new JLayeredPane();
				custSelectEmployeeLP.setBounds(0, 0, 800, 691);
				custMenuPanel.add(custSelectEmployeeLP);
				
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
					if(emplNo == 2 || emplNo == 5 || emplNo == 8 || emplNo == 11) 
					{
						x = 110;
						y = y+85;
					}
					
					employeeButton[emplNo].addActionListener(new SelectEmployeeActionListener(employeeButton, emplNo, custMenuPanel, availableTimesLP, custSelectEmployeeLP) {
					});
				}
	
				btnAvailableTimes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setAllVisibleFalse();
						custMenuPanel.setVisible(true);
						custSelectEmployeeLP.setVisible(true);
					}
				});

				btnLogout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setAllVisibleFalse();
						loginPanel.setVisible(true);
						setTextNull();
						frmBookingSystem.setTitle("Booking System - Login");
					}
				});
				
				x = 110;
				y = 150;
				
				busSelectEmployeeLP = new JLayeredPane();
				busSelectEmployeeLP.setBounds(0, 0, 800, 691);
				businessMenuPanel.add(busSelectEmployeeLP);
				
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
					
					busEmployeeButton[emplNo].addActionListener(new BusSelectEmployeeActionListener(busEmployeeButton, emplNo, businessMenuPanel, employeeAvailabilityLP, busSelectEmployeeLP) {			
					
					});
				}
	
	
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
		custSelectEmployeeLP.setVisible(false);
		busSelectEmployeeLP.setVisible(false);
	}
	private void setTextNull()
	{
		loginUsernameText.setText("");
		loginPasswordText.setText("");		
	}

	
 	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
