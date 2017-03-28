package main;
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
			if(login.login(customers, businesses) == 0)
			{
				System.out.println("Login Fail");
			}
			else
			{
				System.out.println("Login Successful");
				cond = true;
				
				//SIMULATING
				int service;
				
				System.out.println("1. Workout");
				System.out.println("2. Dance Class");
				System.out.println("3. Consulation");
				System.out.print("Enter service to view:");
				service = userInput.nextInt();
				ViewBusiAvail v = new ViewBusiAvail();
				v.viewAvailabilities(customers, businesses, service);
				//END OF SIM
			}
		
			break;
		case 2:
				register.getUserValues(customers, businesses);
				
				if (register.registerNewCust(customers, businesses)) {
					//code for when register is successful(returns true)
				} else {
					//code for when register is unsuccessful(returns false)
				}
			break;
		default:
			System.out.println("Invalid Input try again.");
			break;
		} 
	} catch (InputMismatchException e) {
		// display exception message when user inputs a string value
		System.out.println("Please enter a numerical value.");
		// clears buffer
		userInput.nextLine();
	}}
 	while (cond == false); 
	
	}
	
}


