import java.util.ArrayList;

import Users.business;
import Users.customer;
import Users.init_users;

public class driver {

	public static void main(String[] args) {
		ArrayList<customer> customers = new ArrayList<>();
		ArrayList<business> businesses = new ArrayList<>();
		
		init_users users = new init_users();
		users.init_customers(customers);
		users.init_businesses(businesses);
		
		System.out.println("First Name: " + customers.get(0).getFirstName());
		System.out.println("Address: " + businesses.get(0).getAddress());
	}

}
