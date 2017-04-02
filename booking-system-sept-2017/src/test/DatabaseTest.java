package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import users.*;

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
		
	}
}
