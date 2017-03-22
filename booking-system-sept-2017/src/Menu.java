import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import users.*;


public class Menu {

	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
	private void menuDisplay() {
		System.out.println("\nWelcome to the Booking System.\n");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.print("\nPlease enter your choice: ");
	}
	
	public void menuInput(Scanner userInput, ArrayList<Customer> customers, ArrayList<Business> businesses) {
	//create objects for functions
	//Scanner keyInput = new Scanner(System.in);
	Registration register = new Registration();
	
	Login login = new Login();
	
	int input;
	boolean cond = false;
	
	do {
		cond = false;
		menuDisplay();
	try {
		input = userInput.nextInt();
	
		switch (input) {
		case 1: 
			login.getUsernamePassword(userInput);
			login.login(customers, businesses);
			System.out.println("Login Successful");
			break;
		case 2:
			while (cond == false) {
				register.getValues();
				if (register.registerNewCust(customers, businesses)) {
					//code for when register is successful(returns true)
					//messages should be in functions?
					System.out.println("Your account has been created!");
					cond = true;
				} else {
					//code for when register is unsuccessful(returns false)
					//messages should be in functions?
					System.out.println("Please re-enter your details.");
				}
			}
			break;
		default:
			System.out.println("Invalid Input try again.");
			//set condition to true to continue the loop
			cond = true;
			break;
		} 
	} catch (InputMismatchException e) {
		// display exception message when user inputs a string value
		System.out.println("Please enter a numerical value.");
		// clears buffer
		userInput.nextLine();
		cond = true;
	}}
 	while (cond == true); 
	
	}
	/* @Override
	public String toString() {
		return "[" + firstName + lastName + address + phone + username + password + "]";
	}
	 */
}


