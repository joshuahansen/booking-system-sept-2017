package test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.Session;
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
	public void initCustomerTest(Session session) {
		assertTrue(initUser.init_customers(session, customers));
	}
	
	@Test
	public void initBusinessesTest(Session session) {
		assertTrue(initUser.init_businesses(session, businesses));
	}

}
