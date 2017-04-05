package main;
import java.util.ArrayList;
import java.util.Scanner;

import users.Business;
import users.Customer;
import users.Employee;

public class Registration {
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String address;
	private String phone; 
	private String employeeID;
	
	String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	
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
	public boolean getUserValues(Scanner userInput, ArrayList<Customer> cust, ArrayList<Business> busi) {
		
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
			valid = true; 
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
			if(validUsername(cust, busi)) {
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
	
	public boolean registerNewCustGUI(ArrayList<Customer> cust, ArrayList<Business> busi, String firstName, String lastName, String address, String phone, String username, String password) {
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
	
	public boolean addNewEmployee(Scanner userInput, ArrayList<Employee> empl) {
		getEmployeeValues(userInput, empl);
		//If all input is valid, the new employee is added, and then added to the array list.
		Employee newEmpl = new Employee(employeeID, firstName, lastName);
		
		int selection = 0;
		
		for(int i = 0; i < days.length; i++) {

			System.out.println("Availiable " + days[i] + "?");

			System.out.println("	1. Yes");
			System.out.println("	2. No\n");
			System.out.print("Enter your choice: ");
			selection = userInput.nextInt();
			boolean availDay = false;

			switch(selection) {
				case 1: availDay = true;
				break;
				case 2: availDay = false;
				break;
			}

			selection = 0;

			if(availDay == true) {

				System.out.println("Availiable Mornings?");

				System.out.println("	1. Yes");
				System.out.println("	2. No");
				System.out.print("Enter your choice: ");
				selection = userInput.nextInt();
				
				switch(selection) {
					case 1: 
						newEmpl.setAvailableTime(0, i, "no");
						newEmpl.setAvailableTime(1, i, "no");
						newEmpl.setAvailableTime(2, i, "no");
						newEmpl.setAvailableTime(3, i, "no");
						break;
					case 2: 
						break;
				}

				selection = 0;

				System.out.println("Availiable Midday?");

				System.out.println("	1. Yes");
				System.out.println("	2. No");
				System.out.print("Enter your choice: ");
				selection = userInput.nextInt();
				
				switch(selection) {
					case 1: 
						newEmpl.setAvailableTime(4, i, "no");
						newEmpl.setAvailableTime(5, i, "no");
						newEmpl.setAvailableTime(6, i, "no");
						break;
					case 2: 
						break;
					
				}

				selection = 0;

				System.out.println("Availiable Afternoon?");

				System.out.println("	1. Yes");
				System.out.println("	2. No");
				System.out.print("Enter your choice: ");
				selection = userInput.nextInt();
				
				switch(selection) {
					case 1: 
						newEmpl.setAvailableTime(7, i, "no");
						newEmpl.setAvailableTime(8, i, "no");
						newEmpl.setAvailableTime(9, i, "no");
						break;
					case 2: 
						break;
				}

			}
			
			else {
				break;
			}
			
		}
		
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