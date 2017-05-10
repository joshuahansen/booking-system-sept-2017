//package test;
//
//import main.Login;
//import main.Menu;
//import main.Registration;
//import main.Session;
//import users.Business;
//import users.Customer;
//import users.Database;
//import users.Employee;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import org.junit.Before;
//import org.junit.Test;
//
//public class MenuTest 
//{
//	Database database;
//	String url = "jdbc:sqlite:./databaseTest.db";
//	Session session;
//	
//	@Before
//	public void setup()
//	{
//		database = new Database();
//		database.connectDatabase(session, url);
//	}
//	
//	@Test
//	public void printMainMenuTest()
//	{
//		Menu menuTest = new Menu();
//		
//		menuTest.printMainMenu();
//		
//		assertEquals(true, menuTest.printMainMenu());
//	}
//	
//	@Test
//	public void printCustomerMenuTest()
//	{
//		Menu menuTest = new Menu();
//		
//		menuTest.printCustomerMenu();
//		
//		assertEquals(true, menuTest.printCustomerMenu());
//	}
//	
//	@Test
//	public void printBusinessMenuTest()
//	{
//		Menu menuTest = new Menu();
//		
//		menuTest.printBusinessMenu();
//		
//		assertEquals(true, menuTest.printBusinessMenu());
//	}
//	
//	@Test
//	public void mainMenuExitTest(Session session)
//	{
//		ArrayList<Customer> customers = new ArrayList<>();
//		ArrayList<Business> businesses = new ArrayList<>();
//		ArrayList<Employee> employees = new ArrayList<>();
//		
//		Scanner userInput = new Scanner(System.in);
//		
//		Login login = new Login();
//		
//		Registration registration = new Registration();
//		
//		Menu menuTest = new Menu();
//		
//		assertEquals(true, menuTest.menuDriver(session, userInput, login, registration, 
//				customers, businesses, employees, database));
//		
//		// enter 3 for successful test
//	}
//	
//	@Test
//	public void customerMenuExitTest(Session session)
//	{
//		ArrayList<Customer> customers = new ArrayList<>();
//		ArrayList<Business> businesses = new ArrayList<>();
//		ArrayList<Employee> employees = new ArrayList<>();
//		
//		Scanner userInput = new Scanner(System.in);
//		
//		Registration registration = new Registration();
//
//		Login login = new Login();
//		
//		Menu menuTest = new Menu();
//		
//		assertEquals(true, menuTest.customerMenu(session, userInput, login, registration, customers, businesses, employees));
//		
//		// enter 2 for successful test
//	}
//	
//	@Test
//	public void businessMenuExitTest(Session session)
//	{
//		ArrayList<Customer> customers = new ArrayList<>();
//		ArrayList<Business> businesses = new ArrayList<>();
//		ArrayList<Employee> employees = new ArrayList<>();
//		
//		Scanner userInput = new Scanner(System.in);
//		
//		Registration registration = new Registration();
//		
//		Login login = new Login();
//		
//		Menu menuTest = new Menu();
//		
//		assertEquals(true, menuTest.businessMenu(session, userInput, login, registration, customers, businesses, employees));
//		
//		// enter 3 for successful test
//	}
//}
