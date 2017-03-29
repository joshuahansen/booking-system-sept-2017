package main;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import users.*;

public class Menu 
{
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
	private void menuDisplay() {
		System.out.println("\nWelcome to the Booking System.\n");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.print("\nPlease enter your choice: ");
	}
	
	private void printCustomerMenu()
	{
		printMenuLineSingle(15);
		System.out.println("");
		System.out.println("Customer Menu");
		printMenuLineSingle(15);
		System.out.println("");
		System.out.println("1. View Booking Timetable");
		System.out.println("2. Logout");
	}
	
	private void printBusinessMenu()
	{
		printMenuLineSingle(15);
		System.out.println("");
		System.out.println("Business Menu");
		printMenuLineSingle(15);
		System.out.println("");
		System.out.println("1. View Booking Timetable");
		System.out.println("2. Add Employee");
		System.out.println("3. Remove Employee");
		System.out.println("4. Logout");
	}
	
	public void menuInput(Scanner userInput, ArrayList<Customer> customers, ArrayList<Business> businesses, ArrayList<Employee> employees) 
	{
		//create objects for functions
		//Scanner keyInput = new Scanner(System.in);
		Registration register = new Registration();
		
		Login login = new Login();
		
		int input;
		int loginReturnValue = 0;
		boolean cond = false;
		
		do {
			cond = false;
			menuDisplay();
		try {
			input = userInput.nextInt();
		
			switch (input) {
			case 1: 
				login.getUsernamePassword(userInput);
				
				loginReturnValue = login.login(customers, businesses);
				
				if(loginReturnValue == 0)
				{
					System.out.println("Login failed. Incorrect username or password.");
				}
				else if (loginReturnValue == 1)
				{
					System.out.println("Login successful. Printing customer menu.");
					System.out.println("");
					cond = true;
					
					printCustomerMenu();
					
					input = userInput.nextInt();
					
					if (input == 1)
					{
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
					else if (input == 2)
					{
						cond = false;
						break;
					}
				}
				else if (loginReturnValue == 2)
				{
					System.out.println("Login successful. Printing business menu.");
					System.out.println("");
					cond = true;
					
					printBusinessMenu();
					
					if (input == 1)
					{
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
					else if (input == 2)
					{
						register.addNewEmployee(employees);
					}
					else if (input == 3)
					{
						System.out.println("Under construction.");
						cond = false;
						break;
					}
					else if (input == 4)
					{
						cond = false;
						break;
					}
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
				System.out.println("Invalid input, please try again.");
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
	
	private void printMenuLineSingle(int numberOfLines)
	{
		int counter = 0;
		
		for (counter = 0; counter < numberOfLines; counter++)
		{
			System.out.print("-");
		}
	}
	
	private void printMenuLineDouble(int numberOfLines)
	{
		int counter = 0;
		
		for (counter = 0; counter < numberOfLines; counter++)
		{
			System.out.print("=");
		}
	}
}


