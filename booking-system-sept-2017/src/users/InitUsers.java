package users;

import java.util.ArrayList;

public class InitUsers {
	//pass in Array List of type customer and assign new customer objects to array
	public boolean init_customers(ArrayList<Customer> customers)
	{
		Customer c1 = new Customer("Bob", "Marley", "1 High Street Melbourne", "97342356",
										"c_bMarley", "bMarley");
		Customer c2 = new Customer("Vicki", "Vale", "23 Batman Street Melbourne", "34232687", 
									"c_VickiV", "VickiV");
		Customer c3 = new Customer("John", "Doe", "6 Cemetery Drive Melbourne", "97346756", 
									"c_jd666", "jd666");
		
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
		
		return true;
	}
	//pass in Array List of type business and assign business objects to array
	public boolean init_businesses(ArrayList<Business> businesses)
	{
		Business b1 = new Business("St. George’s Hospital", "Henry", "Gray", "Blackshaw Road Melbourne", 
									"86721255", "b_StGeorges", "StGeorges");
		
		businesses.add(b1);
		
		return true;
	}
}
