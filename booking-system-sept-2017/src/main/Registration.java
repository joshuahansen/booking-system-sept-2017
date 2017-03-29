package main;
import java.util.ArrayList;
import java.util.Scanner;

import users.Business;
import users.Customer;
import users.Employee;

public class Registration {

	private Scanner keyboard = new Scanner(System.in);
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String address;
	private String phone; 
	private String employeeID;
	private boolean avaMonday;
	private boolean avaTuesday;
	private boolean avaWednesday;
	private boolean avaThursday;
	private boolean avaFriday;
	private boolean avaSaturday;
	private boolean avaSunday;
	
	//This function will be used for when GUI is implemented, to replace the getValues function
	public boolean setValues(String firstName, String lastName, String address,
			String phone, String username, String password, ArrayList<Customer> cust, ArrayList<Business> busi) {
			
		this.firstName = firstName;
		if(!validFirstName()) {
			 return false; 
		}
		
		this.lastName = lastName;
		if(!validLastName()) {
			 return false; 
		}
		
		this.address = address;
		
		this.phone = phone;
		if(!validPhone()) {
			 return false; 
		}
		
		this.username = username;
		if(!validUsername(cust, busi)) {
			 return false; 
		}
		
		this.password = password;
		if(!validPassword()) {
			 return false; 
		}
		
		
		return true;
	}
	
	public boolean setEmployeeValues(String employeeID, String firstName, String lastName,
			ArrayList<Employee> empl) {
			
		this.employeeID = employeeID;
		if(!validEmployeeID(empl)) {
			 return false; 
		}
		
		this.firstName = firstName;
		if(!validFirstName()) {
			 return false; 
		}
		
		this.lastName = lastName;
		if(!validLastName()) {
			 return false; 
		}
		
		return true;
	}

	//This function is used while the program is running on the command line
	public boolean getUserValues(ArrayList<Customer> cust, ArrayList<Business> busi) {
		
		boolean valid = false;
		System.out.println("\n--- Register ---\n");
		
		while(valid == false) {
			System.out.print("Enter First Name: ");
			//prompts the user to enter their first name and assigns it to the first name variable
			this.firstName = keyboard.nextLine();
			if(validFirstName()) {
				valid = true; 
			}
		}
		valid = false;
		while(valid == false) {
			//prompts the user to enter their last name and assigns it to the last name variable
			System.out.print("Enter Last Name: ");
			this.lastName = keyboard.nextLine();
			if(validLastName()) {
				valid = true; 
			}
		}
		valid = false;
		while(valid == false) {
			//prompts the user to enter their username and assigns it to the username variable
			System.out.print("Enter Address: ");
			this.address = keyboard.nextLine();
			valid = true; 
		}
		valid = false;
		while(valid == false) {
			//prompts the user to enter their password and assigns it to the password variable
			System.out.print("Enter Phone Number (Minimum 8 characters): ");
			this.phone = keyboard.nextLine();
			if(validPhone()) {
				valid = true; 
			}
		} 
		valid = false;
		while(valid == false) {
			//prompts the user to enter their username and assigns it to the username variable
			System.out.print("Enter Username (Minimum 4 characters): ");
			this.username = keyboard.nextLine();
			if(validUsername(cust, busi)) {
				valid = true; 
			}
		}
		valid = false;
		while(valid == false) {
			//prompts the user to enter their password and assigns it to the password variable
			System.out.print("Enter Password (Minimum 6 characters): ");
			this.password = keyboard.nextLine();
			if(validPassword()) {
				valid = true; 
			}
		} 
			
		return true;
	}
	
	public boolean registerNewCust(ArrayList<Customer> cust, ArrayList<Business> busi) {
		/*Runs the checkValid function to see if all inputs provided by the user is valid for registration
		 *If one of the inputs is invalid, the function will return false, causing this function 
		 * to return false and send the user back to registration.
		 */
		
		//If all input is valid, the new customer is created, and then added to the array list.
		Customer newCust = new Customer(firstName, lastName, address, phone, username, password);
		cust.add(newCust);
		
		System.out.println("\nRegistration success!");
		return true;		
	}
	
	private boolean validUsername(ArrayList<Customer> cust, ArrayList<Business> busi) {
		/*This runs through the customer and the business array list and compares the username of each index to the username 
		 * entered by the user, and if it matches with an already existing username, it will return false
		 */		
		for(int i = 0; i < cust.size(); i++) {	
				if(this.username.equals(cust.get(i).getUsername())) {					
					System.out.println("\nUsername already exists!");
					return false;
				}
		}
		
		for(int i = 0; i < busi.size(); i++) {	
			if(this.username.equals(busi.get(i).getUsername())) {			
				System.out.println("\nUsername already exists!");
				return false;
			}
		}
		//Validates the username length
		if(this.username.length() < 4) {
			System.out.println("\nUsername length must be at least 4 characters!");
			return false;
		}
		return true;
	}
	
	private boolean validPassword() {
		//Validates the password length
		if(this.password.length() < 5) {
			System.out.println("\nPassword length must be at least 6 characters!");
			return false;
		}
		return true;
	}
	
	private boolean validPhone() {
		//Validates the phone number length
		if(this.phone.length() < 7) {
			System.out.println("\nA valid phone number must contain at least 8 digits!");
			return false;
		}
		return true;
	}
	
	private boolean validFirstName() {
		//Validates the first name length
		if(this.firstName.length() < 1) {
			System.out.println("\nFirst name must contain at least 1 character!");
			return false;
		}
		return true;
	}
	
	private boolean validLastName() {
		//Validates the last name length
		if(this.lastName.length() < 1) {
			System.out.println("\nLast name must contain at least 1 character!");
			return false;
		}
		return true;
	}
	
	public boolean getEmployeeValues(ArrayList<Employee> empl) {
			
		boolean valid = false;
		System.out.println("\n--- Add Employee ---\n");
		
		valid = false;
		while(valid == false) {
			//prompts the user to enter the employee's id and assigns it to the employeeID variable
			System.out.print("Enter Employee ID: ");
			this.employeeID = keyboard.nextLine();
			if(validEmployeeID(empl)) {
				valid = true; 
			}
		}
		
		valid = false;

		while(valid == false) {
			System.out.print("Enter First Name: ");
			//prompts the user to enter the employees first name and assigns it to the first name variable
			this.firstName = keyboard.nextLine();
			if(validFirstName()) {
				valid = true; 
			}
		}
		valid = false;
		while(valid == false) {
			//prompts the user to enter the employee last name and assigns it to the last name variable
			System.out.print("Enter Last Name: ");
			this.lastName = keyboard.nextLine();
			if(validLastName()) {
				valid = true; 
			}
		}
		
		int selection = 0;

		System.out.println("\n--- Add Employee ---\n");
		System.out.println("Availiable Mondays?");
		System.out.println("	1. Yes");
		System.out.println("	2. No");
		
		switch(selection) {
			case 1: avaMonday = true;
			break;
			case 2: avaMonday = false;
			break;
		}
		
		selection = 0;
		
		System.out.println("\nAvailiable Tuesdays?");
		System.out.println("	1. Yes");
		System.out.println("	2. No");
		
		switch(selection) {
			case 1: avaTuesday = true;
			break;
			case 2: avaTuesday = false;
			break;
		}

		selection = 0;
		
		System.out.println("\nAvailiable Wednesdays?");
		System.out.println("	1. Yes");
		System.out.println("	2. No");
		
		switch(selection) {
			case 1: avaWednesday = true;
			break;
			case 2: avaWednesday = false;
			break;
		}

		selection = 0;
		
		System.out.println("\nAvailiable Thursdays?");
		System.out.println("	1. Yes");
		System.out.println("	2. No");
		
		switch(selection) {
			case 1: avaThursday = true;
			break;
			case 2: avaThursday = false;
			break;
		}

		selection = 0;
		
		System.out.println("\nAvailiable Fridays?");
		System.out.println("	1. Yes");
		System.out.println("	2. No");
		
		switch(selection) {
			case 1: avaFriday = true;
			break;
			case 2: avaFriday = false;
			break;
		}

		selection = 0;
		
		System.out.println("\nAvailiable Saturdays?");
		System.out.println("	1. Yes");
		System.out.println("	2. No");
		
		switch(selection) {
			case 1: avaSaturday = true;
			break;
			case 2: avaSaturday = false;
			break;
		}

		selection = 0;
		
		System.out.println("\nAvailiable Sundays?");
		System.out.println("	1. Yes");
		System.out.println("	2. No");
		
		switch(selection) {
			case 1: avaSunday = true;
			break;
			case 2: avaSunday = false;
			break;
		}

		return true;
	} 
	
	public boolean addNewEmployee(ArrayList<Employee> empl) {
		getEmployeeValues(empl);
		//If all input is valid, the new employee is added, and then added to the array list.
		Employee newEmpl = new Employee(employeeID, firstName, lastName);
		newEmpl.setAllAvailabily(avaMonday, avaTuesday, avaWednesday, avaThursday, avaFriday, avaSaturday, avaSunday);
		empl.add(newEmpl);
		
		System.out.println("\nEmployee Added! Success!");
		return true;		
	}
	
	private boolean validEmployeeID(ArrayList<Employee> empl) {

		for(int i = 0; i < empl.size(); i++) {	
				if(this.employeeID.equals(empl.get(i).getEmployeeID())) {					
					System.out.println("\nUsername already exists!");
					return false;
				}
		}
		
		//Validates the id length
		if(this.employeeID.length() < 4) {
			System.out.println("\nUsername length must be at least 4 characters!");
			return false;
		}
		return true;
	}
}