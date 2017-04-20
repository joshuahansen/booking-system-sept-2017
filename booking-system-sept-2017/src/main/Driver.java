package main;

import java.util.ArrayList;
import java.util.Scanner;

import users.*;

public class Driver {

	public static void main(String[] args) 
	{
		//create array lists for booking system
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		ArrayList<Employee> employees = new ArrayList<>();
		
		Menu menu = new Menu();
		Login login = new Login();
		Registration registration = new Registration();
		Database database = new Database();
		Scanner userInput = new Scanner(System.in);

		String url = "jdbc:sqlite:./database.db";
		
		if(database.connectDatabase(url) == true)
		{
		
//			database.clearTables();
//			database.initDatabase();
//			database.defaultValues();
			
			if(database.readCustDB(customers) == true && database.readBusDB(businesses) == true)
			{
				System.out.println("Customer Database loaded");
				System.out.println("Business Database loaded");
				if(database.readEmplDB(businesses) && database.readAvailablityTimes(businesses))
				{
					System.out.println("Employee Database loaded");
					System.out.println("Employee availible times loaded");
				}
				else
				{
					System.out.println("Can not load employee database");
					System.out.println("Can not load employee availibilities");
				}
				if(database.readBookingsDB(businesses, customers))
				{
					System.out.println("Booking Databse loaded");
				}
				else
				{
					System.out.println("Can not load Bookings");
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
					if(database.readEmplDB(businesses) && database.readAvailablityTimes(businesses))
					{
						System.out.println("Employee Database loaded");
						System.out.println("Employee availible times loaded\n");
					}
					else
					{
						System.out.println("Can not load employee database");
						System.out.println("Can not load employee availibilities\n");
					}
					if(database.readBookingsDB(businesses, customers))
					{
						System.out.println("Booking Databse loaded");
					}
					else
					{
						System.out.println("Can not load Bookings");
					}
				}
			}
			
		}
		
		menu.menuDriver(userInput, login, registration, customers, businesses, employees);
		
		database.writeCustDB(customers);
		database.writeEmplToDB(businesses);
	
		database.closeConnection();
		userInput.close();

	}
}
