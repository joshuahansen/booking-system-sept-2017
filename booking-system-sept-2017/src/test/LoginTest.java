package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import users.Business;
import users.Customer;
import main.Login;

public class LoginTest 
{
	@Test
	public void customerSuccessTest() 
	{
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		
		Customer c1 = new Customer("Bob", "Marley", "1 High Street Melbourne", "97342356",
				"c_bMarley", "bMarley");
		
		customers.add(c1);
		
		String username = c1.getUsername();
		String password = c1.getPassword();
		
		Login loginTest = new Login(username, password);
		
		loginTest.login(customers, businesses);
		
		assertEquals(1, loginTest.login(customers, businesses));
	}
	
	@Test
	public void businessSuccessTest()
	{
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		
		Business b1 = new Business("St. George’s Hospital", "Henry", "Gray", "Blackshaw Road Melbourne", 
				"86721255", "b_StGeorges", "StGeorges");
		
		businesses.add(b1);
		
		String username = b1.getUsername();
		String password = b1.getPassword();
		
		Login loginTest = new Login(username, password);
		
		loginTest.login(customers, businesses);
		
		assertEquals(2, loginTest.login(customers, businesses));
	}
	
	@Test
	public void loginFailTest()
	{
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		
		Customer c1 = new Customer("Bob", "Marley", "1 High Street Melbourne", "97342356",
				"c_bMarley", "bMarley");
		
		customers.add(c1);
		
		String username = c1.getUsername();
		String password = "c_bMarley";
		
		Login loginTest = new Login(username, password);
		
		loginTest.login(customers, businesses);
		
		assertEquals(0, loginTest.login(customers, businesses));
	}
}