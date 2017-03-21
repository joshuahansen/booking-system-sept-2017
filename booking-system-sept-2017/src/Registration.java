import java.util.ArrayList;
import java.util.Scanner;

import users.Business;
import users.Customer;

public class Registration {

	private Scanner keyboard = new Scanner(System.in);
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String address;
	private String phone; 
	
	//This function will be used for when GUI is implemented, to replace the getValues function
	public void setValues(String firstName, String lastName, String address,
			String phone, String username, String password) {
			
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.username = username;
		this.password = password;	
	}

	//This function is used while the program is running on the command line
	public boolean getValues() {
		
		System.out.println("\n--- Register ---\n");
		
		System.out.print("Enter First Name: ");
		//prompts the user to enter their first name and assigns it to the first name variable
		this.firstName = keyboard.nextLine();
		//prompts the user to enter their last name and assigns it to the last name variable
		System.out.print("Enter Last Name: ");
		this.lastName = keyboard.nextLine();
		//prompts the user to enter their username and assigns it to the username variable
		System.out.print("Enter Address: ");
		this.address = keyboard.nextLine();
		//prompts the user to enter their password and assigns it to the password variable
		System.out.print("Enter Phone Number: ");
		this.phone = keyboard.nextLine();
		//prompts the user to enter their username and assigns it to the username variable
		System.out.print("Enter Username: ");
		this.username = keyboard.nextLine();
		//prompts the user to enter their password and assigns it to the password variable
		System.out.print("Enter Password: ");
		this.password = keyboard.nextLine();
		
		return true;
	}
	
	public boolean registerNewCust(ArrayList<Customer> cust, ArrayList<Business> busi) {
		/*Runs the checkValid function to see if all inputs provided by the user is valid for registration
		 *If one of the inputs is invalid, the function will return false, causing this function 
		 * to return false and send the user back to registration.
		 */
		if(!checkValid(cust, busi)) {
			return false;
		}	
		//If all input is valid, the new customer is created, and then added to the array list.
		Customer newCust = new Customer(firstName, lastName, address, phone, username, password);
		cust.add(newCust);
		
		System.out.println("\nRegistration success!");
		return true;		
	}
	
	private boolean checkValid(ArrayList<Customer> cust, ArrayList<Business> busi) {
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
		if(this.username.length() < 3) {
			System.out.println("\nUsername length must be at least 4 characters!");
			return false;
		}
		//Validates the password length
		if(this.password.length() < 5) {
			System.out.println("\nPassword length must be at least 6 characters!");
			return false;
		}
		//Validates the phone number length
		if(this.phone.length() < 7) {
			System.out.println("\nA valid phone number must contain at least 8 digits!");
			return false;
		}
		//Validates the first name length
		if(this.firstName.length() < 1) {
			System.out.println("\nFirst name must contain at least 1 character!");
			return false;
		}
		//Validates the last name length
		if(this.lastName.length() < 1) {
			System.out.println("\nLast name must contain at least 1 character!");
			return false;
		}
		
		return true;
	}
}