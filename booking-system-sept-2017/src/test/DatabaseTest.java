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
		assertTrue(database.clearTables(database.getConnection()));
	}
	
	@Test
	public void initDatabaseTest()
	{
		database.clearTables(database.getConnection());
		assertTrue(database.initDatabase(database.getConnection()));
	}
	
	@Test
	public void defaultValuesTest()
	{
		database.initDatabase(database.getConnection());
		assertTrue(database.defaultValues(database.getConnection()));
	}

	@Test
	public void readCustDBTest()
	{
		database.initDatabase(database.getConnection());
		database.defaultValues(database.getConnection());
		assertTrue(database.readCustDB(customers, database.getConnection()));
	}
	
	@Test
	public void readBusDBTest()
	{
		database.initDatabase(database.getConnection());
		database.defaultValues(database.getConnection());
		assertTrue(database.readBusDB(businesses, database.getConnection()));
	}
	
	@Test
	public void readEmplyeeDB()
	{
		database.initDatabase(database.getConnection());
		database.defaultValues(database.getConnection());
		assertTrue(database.readEmplDB(employees, database.getConnection()));
	}
}
