import java.util.ArrayList;
import java.util.Scanner;
import Users.*;

import Users.business;
import Users.customer;
import Users.init_users;

public class driver {

	public static void main(String[] args) {
		//create array lists for booking system
		ArrayList<customer> customers = new ArrayList<>();
		ArrayList<business> businesses = new ArrayList<>();
		
		Scanner userInput = new Scanner(System.in);
		
		Login login = new Login();
		
		//create new init_users object
		init_users users = new init_users();
		//initialize both arrays
		users.init_customers(customers);
		users.init_businesses(businesses);
		
		//quick  random test to make sure arrays have elements
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
		}
		
		//calls menu
		Menu sys = new Menu();
		sys.menu_input();
		userInput.close();
	}
}
