package test;

import main.Login;
import main.Menu;
import main.Registration;
import users.Business;
import users.Customer;
import users.Employee;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class MenuTest 
{
	@Test
	public void printMainMenuTest()
	{
		Menu menuTest = new Menu();
		
		menuTest.printMainMenu();
		
		assertEquals(true, menuTest.printMainMenu());
	}
	
	@Test
	public void printCustomerMenuTest()
	{
		Menu menuTest = new Menu();
		
		menuTest.printCustomerMenu();
		
		assertEquals(true, menuTest.printCustomerMenu());
	}
	
	@Test
	public void printBusinessMenuTest()
	{
		Menu menuTest = new Menu();
		
		menuTest.printBusinessMenu();
		
		assertEquals(true, menuTest.printBusinessMenu());
	}
	
	@Test
	public void mainMenuExitTest()
	{
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		ArrayList<Employee> employees = new ArrayList<>();
		
		Scanner userInput = new Scanner(System.in);
		
		Login login = new Login();
		
		Registration registration = new Registration();
		
		Menu menuTest = new Menu();
		
		assertEquals(true, menuTest.menuDriver(userInput, login, registration, 
				customers, businesses, employees));
		
		// enter 3 for successful test
	}
	
	@Test
	public void customerMenuExitTest()
	{
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		ArrayList<Employee> employees = new ArrayList<>();
		
		Scanner userInput = new Scanner(System.in);
		
		Registration registration = new Registration();
		
		Menu menuTest = new Menu();
		
		assertEquals(true, menuTest.customerMenu(userInput, registration, customers, businesses, employees));
		
		// enter 2 for successful test
	}
	
	@Test
	public void businessMenuExitTest()
	{
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		ArrayList<Employee> employees = new ArrayList<>();
		
		Scanner userInput = new Scanner(System.in);
		
		Registration registration = new Registration();
		
		Menu menuTest = new Menu();
		
		assertEquals(true, menuTest.businessMenu(userInput, registration, customers, businesses, employees));
		
		// enter 3 for successful test
	}
}
