package test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.Registration;
import users.Business;
import users.Customer;

public class RegistrationTest {

	@Before
	public void setUp() throws Exception {
			
	}

	@Test
	public void registerNewCustTest() {
		Registration reg = new Registration();
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();		
		
		System.out.println("\nregisterNewCustTest:");

		reg.setValues("John", "Smith", "21 Test Drive", "97235334", "jsmith", "pass123");
		assertEquals(true, reg.registerNewCust(customers, businesses));
	}

	@Test
	public void invalidUniqueUsernameTest() {
		Registration reg = new Registration();
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();	
		
		Customer c1 = new Customer("Bob", "Marley", "1 High Street Melbourne", "97342356",
				"c_bMarley", "bMarley");
		customers.add(c1);
		
		System.out.println("\ninvalidUniqueUsernameTest:");
		
		reg.setValues("Bill", "Marley", "22 Test Drive", "97235234", "c_bMarley", "bMarley");
		assertEquals(false, reg.registerNewCust(customers, businesses));

	}
	
	@Test
	public void invalidPasswordTest() {
		Registration reg = new Registration();
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();	
		
		System.out.println("\ninvalidPasswordTest:");

		reg.setValues("Jimmy", "Taylor", "23 Test Drive", "97922356", "jtaylor", "pass");
		assertEquals(false, reg.registerNewCust(customers, businesses));
	}
	
	@Test
	public void invalidUsernameTest() {
		Registration reg = new Registration();
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();	
		
		System.out.println("\ninvalidUsernameTest:");
		
		reg.setValues("Kim", "Web", "24 Test Drive", "97112356", "Ki", "P@ssW0rd");
		assertEquals(false, reg.registerNewCust(customers, businesses));
	}
	
	@Test
	public void invalidNameTest() {
		Registration reg = new Registration();
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();	
		
		System.out.println("\ninvalidNameTest:");
		
		reg.setValues("", "", "25 Test Drive", "92562356", "Blank", "ilovecats");
		assertEquals(false, reg.registerNewCust(customers, businesses));
	}
	
	@Test
	public void invalidPhoneTest() {
		Registration reg = new Registration();
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();	
		
		System.out.println("\ninvalidPhoneTest:");
		
		reg.setValues("Harry", "Klein", "26 Test Drive", "9573", "hklein", "pword1234");
		assertEquals(false, reg.registerNewCust(customers, businesses));
	}
}
