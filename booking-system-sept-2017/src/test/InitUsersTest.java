package test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import users.*;


public class InitUsersTest {
	ArrayList<Customer> customers;
	InitUsers initUser;
	ArrayList<Business> businesses;
	
	@Before
	public void setup()
	{
		customers = new ArrayList<Customer>();
		businesses = new ArrayList<Business>();
		initUser = new InitUsers();
	}

	@Test
	public void initCustomerTest() {
		assertTrue(initUser.init_customers(customers));
	}
	
	@Test
	public void initBusinessesTest() {
		assertTrue(initUser.init_businesses(businesses));
	}

}
