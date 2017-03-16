package Users;

import java.util.ArrayList;

public class init_users {
	//pass in Array List of type customer and assign new customer objects to array
	public void init_customers(ArrayList<customer> customers)
	{
		customer c1 = new customer("Bob", "Marley", "1 High Street Melbourne", "97342356",
										"bMarley", "bMarley");
		customer c2 = new customer("Vicki", "Vale", "23 Batman Street Melbourne", "34232687", 
									"VickiV", "VickiV");
		customer c3 = new customer("John", "Doe", "6 Cemetery Drive Melbourne", "97346756", 
									"jd666", "jd666");
		
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
	}
	//pass in Array List of type business and assign business objects to array
	public void init_businesses(ArrayList<business> businesses)
	{
		business b1 = new business("St. George’s Hospital", "Henry", "Gray", "Blackshaw Road Melbourne", 
									"86721255", "StGeorges", "StGeorges");
		
		businesses.add(b1);
	}
}
