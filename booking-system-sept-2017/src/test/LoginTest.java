package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import users.Business;
import users.Customer;
import users.Database;
import main.Login;
import main.Session;

public class LoginTest 
{
	Database database;
	String url = "jdbc:sqlite:./databaseTest.db";
	Session session;
	
	@Before
	public void setup()
	{
		database = new Database();
		database.connectDatabase(session, url);
	}
	
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
		
		loginTest.login(businesses.get(0), database);
		
		assertEquals(1, loginTest.login( businesses.get(0), database));
	}
	
	@Test
	public void businessSuccessTest()
	{
		ArrayList<Business> businesses = new ArrayList<>();
		
		Business b1 = new Business("St. George’s Hospital", "Henry", "Gray", "Blackshaw Road Melbourne", 
				"86721255", "b_StGeorges", "StGeorges");
		
		businesses.add(b1);
		
		String username = b1.getUsername();
		String password = b1.getPassword();
		
		Login loginTest = new Login(username, password);
		
		loginTest.login( businesses.get(0), database);
		
		assertEquals(2, loginTest.login(businesses.get(0), database));
	}
	
	@Test
	public void incorrectPasswordTest()
	{
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		
		Customer c1 = new Customer("Bob", "Marley", "1 High Street Melbourne", "97342356",
				"c_bMarley", "bMarley");
		
		customers.add(c1);
		
		String username = c1.getUsername();
		String password = "c_bMarley";
		
		Login loginTest = new Login(username, password);
		
		loginTest.login(businesses.get(0), database);
		
		assertEquals(0, loginTest.login(businesses.get(0), database));
	}
	
	@Test
	public void incorrectUsernameTest()
	{
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		
		Customer c1 = new Customer("Bob", "Marley", "1 High Street Melbourne", "97342356",
				"c_bMarley", "bMarley");
		
		customers.add(c1);
		
		String username = "bMarley";
		String password = c1.getPassword();
		
		Login loginTest = new Login(username, password);
		
		loginTest.login(businesses.get(0), database);
		
		assertEquals(0, loginTest.login(businesses.get(0), database));
	}
	
	@Test
	public void passwordNullTest()
	{
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		
		Customer c1 = new Customer("Bob", "Marley", "1 High Street Melbourne", "97342356",
				"c_bMarley", "bMarley");
		
		customers.add(c1);
		
		String username = c1.getUsername();
		String password = "";
		
		Login loginTest = new Login(username, password);
		
		loginTest.login(businesses.get(0), database);
		
		assertEquals(0, loginTest.login(businesses.get(0), database));
	}
	
	@Test
	public void usernameNullTest()
	{
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		
		Customer c1 = new Customer("Bob", "Marley", "1 High Street Melbourne", "97342356",
				"c_bMarley", "bMarley");
		
		customers.add(c1);
		
		String username = "";
		String password = c1.getPassword();
		
		Login loginTest = new Login(username, password);
		
		loginTest.login(businesses.get(0), database);
		
		assertEquals(0, loginTest.login(businesses.get(0), database));
	}
}