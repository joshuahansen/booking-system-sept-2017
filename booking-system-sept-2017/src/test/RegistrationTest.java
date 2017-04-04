//package test;
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import main.Registration;
//import users.Business;
//import users.Customer;
//import users.Employee;
//
//public class RegistrationTest {
//
//	@Before
//	public void setUp() throws Exception {
//			
//	}
//
//	@Test
//	public void registerNewCustTest() {
//		Registration reg = new Registration();
//		ArrayList<Customer> customers = new ArrayList<>();
//		ArrayList<Business> businesses = new ArrayList<>();		
//		
//		System.out.println("\nregisterNewCustTest:");
//
//		reg.setValues("John", "Smith", "21 Test Drive", "97235334", "jsmith", "pass123", customers, businesses);
//		assertEquals(true, reg.registerNewCust(customers, businesses));
//	}
//
//	@Test
//	public void invalidUniqueUsernameTest() {
//		Registration reg = new Registration();
//		ArrayList<Customer> customers = new ArrayList<>();
//		ArrayList<Business> businesses = new ArrayList<>();	
//		
//		Customer c1 = new Customer("Bob", "Marley", "1 High Street Melbourne", "97342356",
//				"c_bMarley", "bMarley");
//		customers.add(c1);
//		
//		System.out.println("\ninvalidUniqueUsernameTest:");
//		
//		assertEquals(false, reg.setValues("Bill", "Marley", "22 Test Drive", "97235234", "c_bMarley", "bMarley", customers, businesses));
//
//	}
//	
//	@Test
//	public void invalidPasswordTest() {
//		Registration reg = new Registration();
//		ArrayList<Customer> customers = new ArrayList<>();
//		ArrayList<Business> businesses = new ArrayList<>();	
//		
//		System.out.println("\ninvalidPasswordTest:");
//
//		assertEquals(false, reg.setValues("Jimmy", "Taylor", "23 Test Drive", "97922356", "jtaylor", "pass", customers, businesses));
//	}
//	
//	@Test
//	public void invalidUsernameTest() {
//		Registration reg = new Registration();
//		ArrayList<Customer> customers = new ArrayList<>();
//		ArrayList<Business> businesses = new ArrayList<>();	
//		
//		System.out.println("\ninvalidUsernameTest:");
//		
//		assertEquals(false, reg.setValues("Kim", "Web", "24 Test Drive", "97112356", "Ki", "P@ssW0rd", customers, businesses));
//	}
//	
//	@Test
//	public void invalidNameTest() {
//		Registration reg = new Registration();
//		ArrayList<Customer> customers = new ArrayList<>();
//		ArrayList<Business> businesses = new ArrayList<>();	
//		
//		System.out.println("\ninvalidNameTest:");
//		
//		assertEquals(false, reg.setValues("", "", "25 Test Drive", "92562356", "Blank", "ilovecats", customers, businesses));
//	}
//	
//	@Test
//	public void invalidPhoneTest() {
//		Registration reg = new Registration();
//		ArrayList<Customer> customers = new ArrayList<>();
//		ArrayList<Business> businesses = new ArrayList<>();	
//		
//		System.out.println("\ninvalidPhoneTest:");
//		
//		assertEquals(false, reg.setValues("Harry", "Klein", "26 Test Drive", "953", "hklein", "pword1234", customers, businesses));
//	}
//	
//	//Below are the tests for the functions for adding a new employee to the system
//	
//	@Test
//	public void addNewEmployeeTest() {
//		Registration reg = new Registration();
//		ArrayList<Employee> employees = new ArrayList<>();
//		
//		System.out.println("\naddNewEmployee:");
//
//		reg.setEmployeeValues("e00001", "John", "Smith", employees);
//		assertEquals(true, reg.addNewEmployee(employees));
//	}
//
//	@Test
//	public void invalidIDTest() {
//		Registration reg = new Registration();
//		ArrayList<Employee> employees = new ArrayList<>();
//		
//		Employee c1 = new Employee("e00001", "John", "Smith");
//		employees.add(c1);
//		
//		System.out.println("\ninvalidIDTest:");
//		
//		reg.setEmployeeValues("e00001", "Bill", "George", employees);
//		assertEquals(true, reg.addNewEmployee(employees));
//
//	}
//	
//	@Test
//	public void invalidFirstNameTest() {
//		Registration reg = new Registration();
//		ArrayList<Employee> employees = new ArrayList<>();
//		
//		System.out.println("\ninvalidNameTest:");
//		
//		reg.setEmployeeValues("e00002", "", "Smith", employees);
//		assertEquals(true, reg.addNewEmployee(employees));
//
//	}
//	
//	@Test
//	public void invalidLastNameTest() {
//		Registration reg = new Registration();
//		ArrayList<Employee> employees = new ArrayList<>();
//		
//		System.out.println("\ninvalidNameTest:");
//		
//		reg.setEmployeeValues("e00002", "Jim", "", employees);
//		assertEquals(true, reg.addNewEmployee(employees));
//
//	}
//	
//}
