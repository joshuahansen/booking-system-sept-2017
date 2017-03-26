package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Scanner;

import users.*;

public class Driver {

	public static void main(String[] args) throws Exception {
		//create array lists for booking system
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		
		Scanner userInput = new Scanner(System.in);
		
		Database database = new Database();
		
		Connection connection = database.ConnectDatabase();
		database.readDB(connection, customers);
		
		
		Login login = new Login();
		
		//create new init_users object

//		InitUsers users = new InitUsers();

		//initialize both arrays
//		users.init_customers(customers);
//		users.init_businesses(businesses);
		
		/* quick random test to make sure arrays have elements
		System.out.println("First Name: " + customers.get(0).getFirstName());
		System.out.println("Address: " + businesses.get(0).getAddress());
		
		System.out.println("");

		System.out.print("Number of tests: ");
		
		int testInput = userInput.nextInt();
		
		int testCounter = 0;
		
		System.out.println("Running " + testInput + " test(s).");
		
		for (testCounter = 0; testCounter <= testInput; testCounter++)
		{
			System.out.println("Begin Test " + (testCounter + 1));
			login.getUsernamePassword(userInput);
			int returnValue = login.login(customers, businesses);
			System.out.println("returnValue = " + returnValue);
			System.out.println("End Test " + (testCounter + 1));
		}x
		*/
		
		//calls menu
		Menu sys = new Menu();
		sys.menuInput(userInput, customers, businesses);
		if(connection != null)
			{
			connection.close();
			}

		userInput.close();

	}
}
