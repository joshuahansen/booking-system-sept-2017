package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import users.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DatabaseTest {
	ArrayList<Customer> customers;
	ArrayList<Business> businesses;
	ArrayList<Employee> employees;
	Database database;
	String url = "jdbc:sqlite:./databaseTest.db";
	
	@Before
	public void setup()
	{
		customers = new ArrayList<Customer>();
		businesses = new ArrayList<Business>();
		employees = new ArrayList<Employee>();
		database = new Database();
		database.connectDatabase(url);
	}
	
	@Test
	public void databaseConnection() {
		assertTrue(database.connectDatabase(url));
	}
	
	@Test
	public void clearTablesTest()
	{
		database.initDatabase();
		assertTrue(database.clearTables());
	}
	
	@Test
	public void initDatabaseTest()
	{
		database.clearTables();
		assertTrue(database.initDatabase());
	}
	
	@Test
	public void defaultValuesTest()
	{
		database.initDatabase();
		assertTrue(database.defaultValues());
	}

	@Test
	public void readCustDBTest()
	{
		database.initDatabase();
		database.defaultValues();
		assertTrue(database.readCustDB(customers));
	}
	
	@Test
	public void readBusDBTest()
	{
		database.initDatabase();
		database.defaultValues();
		assertTrue(database.readBusDB(businesses));
	}
	
	@Test
	public void readEmplyeeDB()
	{
		database.initDatabase();
		database.defaultValues();
		assertTrue(database.readEmplDB(employees));
	}
	
	@Test
	public void readEmplAvailDB()
	{
		database.initDatabase();
		database.defaultValues();
		assertTrue(database.readAvailablityTimes(employees));
	}

	@Test
	public void writeNewCustToDBTest()
	{
		database.initDatabase();
		Customer newCust = new Customer("test", "dummy", "ANCAP PO Box 4041 Manuka ACT 2603", "62320232", "ancapdummy", "crashtest");
		customers.add(newCust);
		int position = customers.size()-1;
		
		assertTrue(database.writeNewCustToDB(customers, position));
	}
	
	@Test
	public void writeEmplToDB()
	{
		database.initDatabase();
		Employee newEmp = new Employee("0001", "Buster", "Mythbusters");
		employees.add(newEmp);
		
		assertTrue(database.writeEmplToDB(employees));
	}
	
	@Test
	public void deleteAllRecordsTest()
	{
		database.initDatabase();
		database.defaultValues();
		String table = "CUSTOMERS";
		
		assertTrue(database.deleteAllRecords(table));
		
	}
	
	@After
	public void closeDatabase()
	{
		database.clearTables();
		database.closeConnection();
	}
}
