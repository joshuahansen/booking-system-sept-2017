import java.util.ArrayList;

import Users.business;
import Users.customer;
import Users.init_users;

public class driver {

	public static void main(String[] args) {
		//create array lists for booking system
		ArrayList<customer> customers = new ArrayList<>();
		ArrayList<business> businesses = new ArrayList<>();
		
		//create new init_users object
		init_users users = new init_users();
		//initialize both arrays
		users.init_customers(customers);
		users.init_businesses(businesses);
		
		//quick  random test to make sure arrays have elements
		System.out.println("First Name: " + customers.get(0).getFirstName());
		System.out.println("Address: " + businesses.get(0).getAddress());
	}

}
