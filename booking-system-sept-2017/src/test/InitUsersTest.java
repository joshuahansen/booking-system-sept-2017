package test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import users.*;


public class InitUsersTest {

	@Test
	public void InitCustomerTest() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		InitUsers initUser = new InitUsers();
		assertEquals(true, initUser.init_customers(customers));
	}
	
	@Test
	public void initBusinessesTest() {
		ArrayList<Business> businesses = new ArrayList<Business>();
		InitUsers initUser = new InitUsers();
		assertEquals(true, initUser.init_businesses(businesses));
	}

}
