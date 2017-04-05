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
import javax.swing.UIManager.LookAndFeelInfo;

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
import javax.swing.UIManager;

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
//	private JTextField EmployeeNumberData;
//	private JTextField EmployeeNameData;
//	private JTextField EmployeeLNameData;
	
//	public static final int INFORMATION_MESSAGE;
//	public static final int YES_NO_OPTION = 0;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
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
		
			
		UserDetails userDetails = new UserDetails();
		SelectEmployee selection = new SelectEmployee();
		DisplayEmployeeAvailability displayAvail = new DisplayEmployeeAvailability();
		AddEmployee addEmployee = new AddEmployee();
		Registration reg = new Registration();
		
		

		//Login panel
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
		
	//Register panel and elements
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
		
		
	//Customer Menu panel and elements
		custMenuPanel = new JPanel();
		custMenuPanel.setBounds(0, 0, 1074, 691);
		frmBookingSystem.getContentPane().add(custMenuPanel);
		custMenuPanel.setLayout(null);
	
		availableTimesLP = new JLayeredPane();
		availableTimesLP.setBounds(0, 0, 800, 691);
		custMenuPanel.add(availableTimesLP);
		availableTimesLP.setLayout(null);	
		
		customerDetailsLP = new JLayeredPane();
		customerDetailsLP.setBounds(0, 0, 800, 691);
		customerDetailsLP.setAlignmentX(Component.LEFT_ALIGNMENT);
		custMenuPanel.add(customerDetailsLP);
		customerDetailsLP.setLayout(null);
		userDetails.customerDetails(customerDetailsLP);
		
		custSelectEmployeeLP = new JLayeredPane();
		custSelectEmployeeLP.setBounds(0, 0, 800, 691);
		custMenuPanel.add(custSelectEmployeeLP);
		
		JButton btnAvailableTimes = new JButton("Available Times");
		btnAvailableTimes.setBounds(904, 190, 160, 80);
		custMenuPanel.add(btnAvailableTimes);
		btnAvailableTimes.setFont(new Font("Tahoma", Font.PLAIN, 18));
	
		JButton btnViewCustomer = new JButton("Vew Details");
		btnViewCustomer.setBounds(904, 110, 160, 80);
		custMenuPanel.add(btnViewCustomer);
		btnViewCustomer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnViewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setAllVisibleFalse();
				userDetails.loadCustomerDetails(customers, userPos);
				custMenuPanel.setVisible(true);
				customerDetailsLP.setVisible(true);
			}
		});

		btnAvailableTimes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setAllVisibleFalse();
				custMenuPanel.setVisible(true);
				selection.selectPersonalTrainer(custSelectEmployeeLP, availableTimesLP, custMenuPanel, employees, displayAvail);
				custSelectEmployeeLP.setVisible(true);
			}
		});

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(904, 10, 160, 40);
		custMenuPanel.add(btnLogout);
		btnLogout.setIcon(new ImageIcon(logoutImg));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setAllVisibleFalse();
				loginPanel.setVisible(true);
				setTextNull();
				frmBookingSystem.setTitle("Booking System - Login");
			}
		});
				
			
//	Business menu and panels
		businessMenuPanel = new JPanel();
		businessMenuPanel.setBounds(0, 0, 1074, 691);
		frmBookingSystem.getContentPane().add(businessMenuPanel);
		businessMenuPanel.setLayout(null);
		
		busSelectEmployeeLP = new JLayeredPane();
		busSelectEmployeeLP.setBounds(0, 0, 800, 691);
		businessMenuPanel.add(busSelectEmployeeLP);
		
		businessDetailsLP = new JLayeredPane();
		businessDetailsLP.setBounds(0, 0, 800, 691);
		businessMenuPanel.add(businessDetailsLP);
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
					setAllVisibleFalse();
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
