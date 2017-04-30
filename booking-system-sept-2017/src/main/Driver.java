package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import users.*;

public class Driver {

	public static void main(String[] args) throws IOException 
	{
		//create array lists for booking system
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		ArrayList<Employee> employees = new ArrayList<>();
		
		Session session = new Session();
		
		session.addLog("New session: " + session.getSessionID());
		Menu menu = new Menu();
		session.addLog("Menu object created");
		Login login = new Login();
		session.addLog("Login object created");
		Registration registration = new Registration();
		session.addLog("Registration object created");
		Database database = new Database();
		session.addLog("Database object created");
		Scanner userInput = new Scanner(System.in);
		session.addLog("Scanner object created");

		String url = "jdbc:sqlite:./database.db";
		
		session.addLog("Database URL: " + url);
		
		if(database.connectDatabase(session, url) == true)
		{
			session.addLog("Database connection to " + url + " successful");
//			database.clearTables();
//			database.initDatabase();
//			database.defaultValues();
			
			if(database.readCustDB(session, customers) == true && database.readBusDB(session, businesses) == true)
			{
				System.out.println("Customer Database loaded");
				session.addLog("Customer Database loaded");
				System.out.println("Business Database loaded");
				session.addLog("Business Database loaded");
				if(database.readEmplDB(session, businesses) && database.readAvailablityTimes(session, businesses))
				{
					System.out.println("Employee Database loaded");
					session.addLog("Employee Database loaded");
					System.out.println("Employee available times loaded");
					session.addLog("Employee available times loaded");
				}
				else
				{
					System.out.println("Cannot load employee database");
					session.addLog("Cannot load employee database");
					System.out.println("Cannot load employee availibilities");
					session.addLog("Cannot load employee availibilities");
				}
				if(database.readBookingsDB(session, businesses, customers))
				{
					System.out.println("Booking Database loaded");
					session.addLog("Booking Database loaded");
				}
				else
				{
					System.out.println("Cannot load Bookings");
					session.addLog("Cannot load Bookings");
				}
			}
			else
			{
				database.clearTables(session);
				database.initDatabase(session);
				database.defaultValues(session);
				if(database.readCustDB(session, customers) == true && database.readBusDB(session, businesses) == true)
				{
					System.out.println("Customer Database loaded");
					session.addLog("Customer Database loaded");
					System.out.println("Business Database loaded");
					session.addLog("Business Database loaded");
					if(database.readEmplDB(session, businesses) && database.readAvailablityTimes(session, businesses))
					{
						System.out.println("Employee Database loaded");
						session.addLog("Employee Database loaded");
						System.out.println("Employee available times loaded\n");
						session.addLog("Employee available times loaded");
					}
					else
					{
						System.out.println("Cannot load employee database");
						session.addLog("Cannot load employee database");
						System.out.println("Can not load employee availabilities\n");
						session.addLog("Cannot load employee availabilities");
					}
					if(database.readBookingsDB(session, businesses, customers))
					{
						System.out.println("Booking Database loaded");
						session.addLog("Booking Database loaded");
					}
					else
					{
						System.out.println("Cannot load Bookings");
						session.addLog("Cannot load Bookings");
					}
				}
			}
			
		}
		
		menu.menuDriver(session, userInput, login, registration, customers, businesses, employees);
		database.writeCustDB(session, customers);
		session.addLog("Customer data written to database");
		database.writeEmplToDB(session, businesses);
		session.addLog("Business data written to database");
	
		database.closeConnection(session);
		session.addLog("Connection to database closed");
		userInput.close();
		session.addLog("Scanner closed");
		session.terminateSession();
	}
}
