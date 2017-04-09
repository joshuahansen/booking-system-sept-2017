package main;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Date;
import users.*;

public class Menu 
{
	public Menu()
	{
		// default constructor
	}
	
	public boolean menuDriver(Scanner userInput, Login login, Registration registration, 
			ArrayList<Customer> customers, ArrayList<Business> businesses, ArrayList<Employee> employees)
	{
		int menuInput = 0;
		int returnValue = 0;
		
		while (menuInput != 3)
		{
			printMainMenu();
			
			menuInput = userInput.nextInt();
			
			switch(menuInput)
			{
				case 1:
				{
					login.getUsernamePassword(userInput);
					returnValue = login.login(customers, businesses);
					
					if (returnValue == 0)
					{
						System.out.println("\nLogin failed: Incorrect username or password.");
						login.setUsernamePassword(null, null);
					}
					else if (returnValue == 1)
					{
						System.out.println("\nLogin successful: Printing customer menu.");
						customerMenu(userInput, login, registration, customers, businesses, employees);
					}
					else if (returnValue == 2)
					{
						System.out.println("\nLogin successful: Printing business menu.");
						businessMenu(userInput, login, registration, customers, businesses, employees);
					}
					break;
				}
				case 2:
				{
					registration.getUserValues(userInput, customers, businesses);
					
					registration.registerNewCust(customers, businesses);
					break;
				}
				case 3:
				{
					System.out.println("\nSystem exiting.\n");
					break;
				}
				default:
				{
					System.out.println("\nInvalid user input. Please try again.");
					break;
				}
			}
		}
		
		return true;
	}
	
	public boolean customerMenu(Scanner userInput, Login login, Registration registration,  
			ArrayList<Customer> customers, ArrayList<Business> businesses, ArrayList<Employee> employees)
	{
		int menuInput = 0;
		
		while (menuInput != 2)
		{
			printCustomerMenu();
			
			menuInput = userInput.nextInt();
			
			switch(menuInput)
			{
				case 1:
				{
					printAvailabilities(userInput, customers, businesses, employees);
					break;
				}
				case 2:
				{
					System.out.println("\nReturning to main menu.\n");
					break;
				}
				default:
				{
					System.out.println("\nInvalid user input. Please try again.");
					break;
				}
			}
		}
		
		return true;
	}
	
	public boolean businessMenu(Scanner userInput, Login login, Registration registration, 
			ArrayList<Customer> customers, ArrayList<Business> businesses, ArrayList<Employee> employees)
	{
		int menuInput = 0;
		
		while (menuInput != 4)
		{
			printBusinessMenu();
			
			menuInput = userInput.nextInt();
			
			switch(menuInput)
			{
				case 1:
				{
					printAvailabilities(userInput, customers, businesses, employees);
					break;
				}
				case 2:
				{
					int businessPosition = login.getUserPosition();
					int numberOfBookings = businesses.get(businessPosition).bookings.size();
					int counter = 0;
					
					if (numberOfBookings < 1)
					{
						System.out.println("There are no previous bookings to display.");
					}
					else
					{
						for (counter = 0; counter < numberOfBookings; counter++)
						{
//							if (!businesses.get(businessPosition).bookings.get(counter).getDate().plusDays(7).isAfter(LocalDate.now()))
//							{
								System.out.println(businesses.get(businessPosition).bookings.get(counter).toString());
//							}
						}
					}
					
					break;
				}
				case 3:
				{
					registration.getEmployeeValues(userInput, employees);
					registration.addNewEmployee(userInput, employees);
					break;
				}
				case 4:
				{
					System.out.println("\nReturning to main menu.\n");
					break;
				}
				default:
				{
					System.out.println("\nInvalid user input. Please try again.");
					break;
				}
			}
		}
		
		return true;
	}
	
	public boolean printMainMenu() 
	{
		printMenuLineDouble(30);
		System.out.println("Welcome to the Booking System.");
		printMenuLineDouble(30);
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Exit");
		System.out.print("\nPlease enter your choice: ");
		
		return true;
	}
	
	public boolean printCustomerMenu()
	{
		printMenuLineSingle(15);
		System.out.println("Customer Menu");
		printMenuLineSingle(15);
		System.out.println("1. View Booking Timetable");
		System.out.println("2. Logout");
		System.out.print("\nPlease enter your choice: ");
		
		return true;
	}
	
	public boolean printBusinessMenu()
	{
		printMenuLineSingle(15);
		System.out.println("Business Menu");
		printMenuLineSingle(15);
		System.out.println("1. View Booking Timetable");
		System.out.println("2. View Previous Bookings");
		System.out.println("3. Add Employee");
		System.out.println("4. Logout");
		System.out.print("\nPlease enter your choice: ");
		
		return true;
	}
	
	public boolean printAvailabilities(Scanner userInput, ArrayList<Customer> customers, 
			ArrayList<Business> businesses, ArrayList<Employee> employees)
	{
		int menuInput = 0;
		ViewBusiAvail table = new ViewBusiAvail();
		
		while (menuInput != 1)
		{
			
			table.viewAvailabilities(customers, businesses);
			
			System.out.println("\n1. Exit");
			System.out.print("\nPlease enter your choice: ");
			menuInput = userInput.nextInt();
			
			if (menuInput == 1)
			{
				break;
			}
			else
			{
				System.out.println("\nInvalid user input. Please try again.");
			}
		}
		
		return true;
	}
	
	public boolean printMenuLineSingle(int numberOfLines)
	{
		int counter = 0;
		
		for (counter = 0; counter < numberOfLines; counter++)
		{
			System.out.print("-");
		}
		
		System.out.println("");
		
		return true;
	}
	
	public boolean printMenuLineDouble(int numberOfLines)
	{
		int counter = 0;
		
		for (counter = 0; counter < numberOfLines; counter++)
		{
			System.out.print("=");
		}
		
		System.out.println("");
		
		return true;
	}
}


