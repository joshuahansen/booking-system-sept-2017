package main;
import java.util.ArrayList;
import java.util.Scanner;

import users.Business;
import users.Customer;
import users.Employee;

public class Registration {
	
	private Business business;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String address;
	private String phone; 
	private String employeeID;
	private String businessName;
	
	String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	//This function will be used for when GUI is implemented, to replace the getValues function
	public boolean setValues(String firstName, String lastName, String address,
			String phone, String username, String password, Business business) {
		this.business = business;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.password = password;
		
		if(!validUsername(business)) {
			 return false; 
		}
		
		if(!validFirstName()) {
			 return false; 
		}
		
		if(!validLastName()) {
			 return false; 
		}
	
		if(!validAddress()){
			return false;
		}

		if(!validPhone()) {
			 return false; 
		}
		
		if(!validPassword()) {
			 return false; 
		}
		
		return true;
	}
	
	public boolean setBusinessValues(String businessName, String firstName, String lastName, String address,
			String phone, String username, String password, ArrayList<Business> businesses) {
			
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.password = password;
		this.businessName = businessName;
		
		if(!validBusinessName())
		{
			return false;
		}
		
		if(!validBusinessUsername(businesses)) {
			 return false; 
		}
		
		if(!validFirstName()) {
			 return false; 
		}
		
		if(!validLastName()) {
			 return false; 
		}
	
		if(!validAddress()){
			return false;
		}

		if(!validPhone()) {
			 return false; 
		}
		
		if(!validPassword()) {
			 return false; 
		}
		
		return true;
	}
	
	public boolean setEmployeeValues(String employeeID, String firstName, String lastName,
			ArrayList<Employee> empl) {
			
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		
		if(!validEmployeeID(empl)) {
			 return false; 
		}

		if(!validFirstName()) {
			 return false; 
		}

		if(!validLastName()) {
			 return false; 
		}
		
		return true;
	}

	//This function is used while the program is running on the command line
	public boolean getUserValues(Scanner userInput, Business business) {
		
		boolean valid = false;
		System.out.println("\n--- Register ---\n");
		
		while(valid == false) {
			System.out.print("Enter First Name: ");
			//prompts the user to enter their first name and assigns it to the first name variable
			this.firstName = userInput.nextLine();
			if(validFirstName()) {
				valid = true; 
			}
		}
		valid = false;
		while(valid == false) {
			//prompts the user to enter their last name and assigns it to the last name variable
			System.out.print("Enter Last Name: ");
			this.lastName = userInput.nextLine();
			if(validLastName()) {
				valid = true; 
			}
		}
		valid = false;
		while(valid == false) {
			//prompts the user to enter their username and assigns it to the username variable
			System.out.print("Enter Address: ");
			this.address = userInput.nextLine();
			if(validAddress()) {
				valid = true; 
			}
		}
		valid = false;
		while(valid == false) {
			//prompts the user to enter their password and assigns it to the password variable
			System.out.print("Enter Phone Number (Minimum 8 characters): ");
			this.phone = userInput.nextLine();
			if(validPhone()) {
				valid = true; 
			}
		} 
		valid = false;
		while(valid == false) {
			//prompts the user to enter their username and assigns it to the username variable
			System.out.print("Enter Username (Minimum 4 characters): ");
			this.username = userInput.nextLine();
			if(validUsername(business)) {
				valid = true; 
			}
		}
		valid = false;
		while(valid == false) {
			//prompts the user to enter their password and assigns it to the password variable
			System.out.print("Enter Password (Minimum 6 characters): ");
			this.password = userInput.nextLine();
			if(validPassword()) {
				valid = true; 
			}
		} 
			
		return true;
	}
	
	public boolean registerNewCust(Session session, Business busi) {
		/*Runs the checkValid function to see if all inputs provided by the user is valid for registration
		 *If one of the inputs is invalid, the function will return false, causing this function 
		 * to return false and send the user back to registration.
		 */
		
		//If all input is valid, the new customer is created, and then added to the array list.
		Customer newCust = new Customer(firstName, lastName, address, phone, username, password);
		session.addLog("Customer object created");
		busi.getCustomers().add(newCust);
		session.addLog("Customer added");
		
		System.out.println("\nRegistration success!");
		return true;		
	}
	
	public boolean registerNewCustGUI(Business busi, String firstName, String lastName, String address, String phone, String username, String password) {
		/*Runs the checkValid function to see if all inputs provided by the user is valid for registration
		 *If one of the inputs is invalid, the function will return false, causing this function 
		 * to return false and send the user back to registration.
		 */
		
		//If all input is valid, the new customer is created, and then added to the array list.
		Customer newCust = new Customer(firstName, lastName, address, phone, username, password);
		busi.getCustomers().add(newCust);
		
		System.out.println("\nRegistration success!");
		return true;		
	}
	
	public boolean registerNewBusiness(Session session, ArrayList<Business> busi) {
		/*Runs the checkValid function to see if all inputs provided by the user is valid for registration
		 *If one of the inputs is invalid, the function will return false, causing this function 
		 * to return false and send the user back to registration.
		 */
		
		//If all input is valid, the new customer is created, and then added to the array list.
		Business newBusiness = new Business(businessName, firstName, lastName, address, phone, username, password);
		session.addLog("Business object created");
		busi.add(newBusiness);
		session.addLog("Business added");
		
		System.out.println("\nRegistration success!");
		return true;		
	}
	
	public boolean validBusinessUsername(ArrayList<Business> businesses)
	{
		if(username == null)
		{
			return false;
		}
		for(int i = 0; i < businesses.size(); i++) {	
			if(this.username.equals(businesses.get(i).getUsername())) {			
				System.out.println("\nUsername already exists!");
				return false;
			}
			for(int j= 0; j < businesses.get(i).getCustomers().size(); j++) {	
				if(this.username.equals(businesses.get(i).getCustomers().get(j).getUsername())) {					
					System.out.println("\nUsername already exists!");
					return false;
				}
			}
		}
		//Validates the username length
		if(this.username.length() < 4) {
			System.out.println("\nUsername length must be at least 4 characters!");
			return false;
		}
		return true;
		
	}
	public boolean validUsername(Business business) {
		/*This runs through the customer and the business array list and compares the username of each index to the username 
		 * entered by the user, and if it matches with an already existing username, it will return false
		 */		
		if(username == null)
		{
			return false;
		}
//		for(int i = 0; i < cust.size(); i++) {	
//				if(this.username.equals(cust.get(i).getUsername())) {					
//					System.out.println("\nUsername already exists!");
//					return false;
//				}
//		}
		
//		for(int i = 0; i < businesses.size(); i++) {	
			if(this.username.equals(business.getUsername())) {			
				System.out.println("\nUsername already exists!");
				return false;
			}
			for(int j= 0; j < business.getCustomers().size(); j++) {	
				if(this.username.equals(business.getCustomers().get(j).getUsername())) {					
					System.out.println("\nUsername already exists!");
					return false;
				}
			}
//		}
		//Validates the username length
		if(this.username.length() < 4) {
			System.out.println("\nUsername length must be at least 4 characters!");
			return false;
		}
		return true;
	}
	
	public boolean validPassword() {
		//Validates the password length
		if(password == null)
		{
			return false;
		}
		if(this.password.length() < 6) {
			System.out.println("\nPassword length must be at least 6 characters");
			return false;
		}
		if(!this.password.matches(".*[a-zA-Z]+.*")) {
			System.out.println("\nPassword length must contain at least 1 letter!");
			return false;
		}
		if(!this.password.matches(".*[0-9]+.*")) {
			System.out.println("\nPassword length must contain at least 1 number!");
			return false;
		}
		return true;
	}
	
	public boolean validPhone() {
		//Validates the phone number length
		if(phone == null)
		{
			return false;
		}
		if(this.phone.length() < 8) {
			System.out.println("\nA valid phone number must contain at least 8 digits!");
			return false;
		}
		if(!this.phone.matches("[0-9]+")) {
			System.out.println("\nA valid phone number must contain only digits!");
			return false;
		}
		return true;
	}
	
	public boolean validFirstName() {
		//Validates the first name length
		if(firstName == null)
		{
			return false;
		}
		if(this.firstName.length() < 1) {
			System.out.println("\nFirst name must contain at least 1 character!");
			return false;
		}
		if (!this.firstName.matches("[a-zA-Z]+")) {
			System.out.println("\nFirst name can only contain alphabetical characters!");
			return false;
		}
		return true;
	}
	
	public boolean validBusinessName()
	{
		//Validates the business name length
				if(businessName == null)
				{
					return false;
				}
				if(this.businessName.length() < 1) {
					System.out.println("\nFirst name must contain at least 1 character!");
					return false;
				}
				if (!this.businessName.matches("[a-zA-Z]+")) {
					System.out.println("\nBusiness name can only contain alphabetical characters!");
					return false;
				}
				return true;
	}
	
	public boolean validLastName() {
		//Validates the last name length
		if(lastName == null)
		{
			return false;
		}
		if(this.lastName.length() < 1) {
			System.out.println("\nLast name must contain at least 1 character!");
			return false;
		}
		if (!this.lastName.matches("[a-zA-Z]+")) {
			System.out.println("\nLast name can only contain alphabetical characters!");
			return false;
		}
		return true;
	}
	
	public boolean validAddress() {
		if(address == null)
		{
			return false;
		}
		if(this.address.length() < 1) {
			System.out.println("\nAddress name must contain at least 1 character!");
			return false;
		}
		if(!this.address.matches("^[0-9 ]+[A-z ]+")) {
			System.out.println("\nAddress must contain a number followed by letters!");
			return false;
		}
		return true;
	}
	
	public boolean getEmployeeValues(Scanner userInput, ArrayList<Employee> empl) {
			
		boolean valid = false;
		System.out.println("\n--- Add Employee ---\n");
		
		valid = false;
		while(valid == false) {
			//prompts the user to enter the employee's id and assigns it to the employeeID variable
			System.out.print("Enter Employee ID: ");
			this.employeeID = userInput.nextLine();
			if(validEmployeeID(empl)) {
				valid = true; 
			}
		}
		
		valid = false;

		while(valid == false) {
			System.out.print("Enter First Name: ");
			//prompts the user to enter the employees first name and assigns it to the first name variable
			this.firstName = userInput.nextLine();
			if(validFirstName()) {
				valid = true; 
			}
		}
		valid = false;
		while(valid == false) {
			//prompts the user to enter the employee last name and assigns it to the last name variable
			System.out.print("Enter Last Name: ");
			this.lastName = userInput.nextLine();
			if(validLastName()) {
				valid = true; 
			}
		}

		return true;
	} 
	
//	public boolean addNewEmployee(Session session, Scanner userInput, ArrayList<Employee> empl) {
//		//If all input is valid, the new employee is added, and then added to the array list.
//		session.addLog("Employee object created");
//		Employee newEmpl = new Employee(employeeID, firstName, lastName);
//		
//		int selection = 0;
//		
//		for(int i = 0; i <= days.length; i++) {
//
//			System.out.println("Availiable " + days[i] + "?");
//
//			System.out.println("	1. Yes");
//			System.out.println("	2. No\n");
//			System.out.print("Enter your choice: ");
//			selection = userInput.nextInt();
//			boolean availDay = false;
//
//			switch(selection) {
//				case 1: availDay = true;
//				break;
//				case 2: availDay = false;
//				break;
//			}
//
//			selection = 0;
//
//			if(availDay == true) {
//
//				System.out.println("\nAvailiable Mornings?");
//
//				System.out.println("	1. Yes");
//				System.out.println("	2. No");
//				System.out.print("Enter your choice: ");
//				selection = userInput.nextInt();
//				
//				switch(selection) {
//					case 1: 
//						newEmpl.setAvailableTime(0, i, "no");
//						newEmpl.setAvailableTime(1, i, "no");
//						newEmpl.setAvailableTime(2, i, "no");
//						newEmpl.setAvailableTime(3, i, "no");
//						break;
//					case 2: 
//						break;
//				}
//
//				selection = 0;
//
//				System.out.println("\nAvailiable Midday?");
//
//				System.out.println("	1. Yes");
//				System.out.println("	2. No");
//				System.out.print("Enter your choice: ");
//				selection = userInput.nextInt();
//				
//				switch(selection) {
//					case 1: 
//						newEmpl.setAvailableTime(4, i, "no");
//						newEmpl.setAvailableTime(5, i, "no");
//						newEmpl.setAvailableTime(6, i, "no");
//						break;
//					case 2: 
//						break;
//					
//				}
//
//				selection = 0;
//
//				System.out.println("\nAvailiable Afternoon?");
//
//				System.out.println("	1. Yes");
//				System.out.println("	2. No");
//				System.out.print("Enter your choice: ");
//				selection = userInput.nextInt();
//				
//				switch(selection) {
//					case 1: 
//						newEmpl.setAvailableTime(7, i, "no");
//						newEmpl.setAvailableTime(8, i, "no");
//						newEmpl.setAvailableTime(9, i, "no");
//						break;
//					case 2: 
//						break;
//				}
//
//			}
//			
//			else {
//				break;
//			}
//			
//		}
//		session.addLog("Employee added");
//		empl.add(newEmpl);
//			
//		System.out.println("\nEmployee Added! Success!");
//		return true;		
//	}
	
	public boolean validEmployeeID(ArrayList<Employee> empl) {
		
		if(employeeID == null)
		{
			return false;
		}
		if(employeeID.length() < 1)
		{
			System.out.println("EmployeeID to short");
			return false;
		}
		if(!employeeID.matches("[0-9]+")) {
			System.out.println("\nA valid ID must contain only digits!");
			return false;
		}
		for(int i = 0; i < empl.size(); i++) {	
				if(this.employeeID.equals(empl.get(i).getEmployeeID())) {					
					System.out.println("\nID already exists!");
					return false;
				}
		}
		return true;
	}

	public boolean addNewEmployeeGui(ArrayList<Employee> empl) {
		//If all input is valid, the new employee is added, and then added to the array list.
		Employee newEmpl = new Employee(this.employeeID, this.firstName, this.lastName);
		empl.add(newEmpl);
		
		System.out.println("\nEmployee Added! Success!");
		return true;		
	}
}
