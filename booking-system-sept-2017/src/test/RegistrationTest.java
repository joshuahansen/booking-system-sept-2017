package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import main.Registration;
import users.Business;
import users.Customer;
import users.Employee;

public class RegistrationTest {

	Scanner userInput = new Scanner(System.in);
	
	@Before
	public void setUp() throws Exception {
			
	}

	@Test
	public void registerNewCustTest() {
		Registration reg = new Registration();
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();		
		
		System.out.println("\nregisterNewCustTest:");

		reg.setValues("John", "Smith", "21 Test Drive", "97235334", "jsmith", "pass123", customers, businesses);
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
		
		assertEquals(false, reg.setValues("Bill", "Marley", "22 Test Drive", "97235234", "c_bMarley", "bMarley", customers, businesses));
		
		//unique user name is case sensitive 
		assertEquals(false, reg.setValues("Bill", "Marley", "22 Test Drive", "97235234", "C_BMARLEY", "bMarley", customers, businesses));
	}
	
	@Test
	public void invalidPasswordTest() {
		Registration reg = new Registration();
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();	
		
		System.out.println("\ninvalidPasswordTest:");

		assertEquals(false, reg.setValues("Jimmy", "Taylor", "23 Test Drive", "97922356", "jtaylor", "pass", customers, businesses));
		
		//password length should be >= 6 and should return false otherwise.
		assertEquals(false, reg.setValues("Jimmy", "Taylor", "23 Test Drive", "97922356", "jtaylor", "12345", customers, businesses));
	}
	
	@Test
	public void invalidUsernameTest() {
		Registration reg = new Registration();
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();	
		
		System.out.println("\ninvalidUsernameTest:");
		
		assertEquals(false, reg.setValues("Kim", "Web", "24 Test Drive", "97112356", "Ki", "P@ssW0rd", customers, businesses));
	}
	
	@Test
	public void invalidNameTest() {
		Registration reg = new Registration();
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();	
		
		System.out.println("\ninvalidNameTest:");
		
		assertEquals(false, reg.setValues("", "", "25 Test Drive", "92562356", "Blank", "ilovecats", customers, businesses));
	}
	
	@Test
	public void invalidPhoneTest() {
		Registration reg = new Registration();
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();	
		
		System.out.println("\ninvalidPhoneTest:");
		
		assertEquals(false, reg.setValues("Harry", "Klein", "26 Test Drive", "953", "hklein", "pword1234", customers, businesses));
		
		//8 characters in phone should return false.
		assertEquals(false, reg.setValues("Harry", "Klein", "26 Test Drive", "abcdefgh", "hklein", "pword1234", customers, businesses));
		
		//7 numbers in phone should return false.
		assertEquals(false, reg.setValues("Harry", "Klein", "26 Test Drive", "1234567", "hklein", "pword1234", customers, businesses));
		
		/*max length check for phone number >= 30
		assertEquals(false, reg.setValues("leBron", "James", "23 Trenton Road", "12345678910111213141516171819"+ "", "user01", "pass01", customers, businesses));
		*/
	}
	
	//Below are the tests for the functions for adding a new employee to the system
	
	@Test
	public void addNewEmployeeTest() {
		Registration reg = new Registration();
		ArrayList<Employee> employees = new ArrayList<>();
		
		System.out.println("\naddNewEmployee:");

		assertEquals(true, reg.setEmployeeValues("e00001", "John", "Smith", employees));
	}

	@Test
	public void invalidIDTest() {
		Registration reg = new Registration();
		ArrayList<Employee> employees = new ArrayList<>();
		
		Employee c1 = new Employee("e00001", "John", "Smith");
		employees.add(c1);
		
		System.out.println("\ninvalidIDTest:");
		
		assertEquals(false, reg.setEmployeeValues("e00001", "Bill", "George", employees));

	}
	
	@Test
	public void invalidFirstNameTest() {
		Registration reg = new Registration();
		ArrayList<Employee> employees = new ArrayList<>();
		
		System.out.println("\ninvalidNameTest:");
		
		assertEquals(false, reg.setEmployeeValues("e00002", "", "Smith", employees));
		
		//first name passes integers and doubles
		assertEquals(false, reg.setEmployeeValues("e00002", "555", "Smith", employees));
		assertEquals(false, reg.setEmployeeValues("e00002", "23.45", "Smith", employees));
		
		/*first name passes special characters
		assertEquals(false, reg.setEmployeeValues("e00002", ",,,,,,", "Smith", employees));
		assertEquals(false, reg.setEmployeeValues("e00002", "[][][][]", "Smith", employees));
		assertEquals(false, reg.setEmployeeValues("e00002", "!@#$%^&*()[];'.,-=", "Smith", employees));
		*/
	}
	
	@Test
	public void invalidLastNameTest() {
		Registration reg = new Registration();
		ArrayList<Employee> employees = new ArrayList<>();
		
		System.out.println("\ninvalidNameTest:");
		
		assertEquals(false, reg.setEmployeeValues("e00002", "Jim", "", employees));
		
		//last name passes integers and doubles
	    assertEquals(false, reg.setEmployeeValues("e00002", "Jim", "101", employees));
		assertEquals(false, reg.setEmployeeValues("e00002", "Jim", "2.001", employees));
		
		//last name passes special characters
		assertEquals(false, reg.setEmployeeValues("e00002", "Jim", ",,,,,,", employees));
		assertEquals(false, reg.setEmployeeValues("e00002", "Jim", "[][][][]", employees));
		assertEquals(false, reg.setEmployeeValues("e00002", "Jim", "!@#$%^&*()[];'.,-=", employees));
	}
	@Test
	public void invalidAddressTest() {
		
		Registration reg = new Registration();
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		
		System.out.println("\ninvalidAddressTest:");
		
		//address will pass on empty string
		assertEquals(false, reg.setValues("leBron", "James", "", "12345678", "user01", "pass01", customers, businesses));
		
		//address needs to check if there is both a number and street on the input. - seems kinda hard to code
		assertEquals(false, reg.setValues("leBron", "James", "cleveland st", "12345678", "user01", "pass01", customers, businesses));
		
	}

	
}