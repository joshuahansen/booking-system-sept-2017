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
	Database database;
	
	@Before
	public void setup()
	{
		customers = new ArrayList<Customer>();
		businesses = new ArrayList<Business>();
		database = new Database();
		database.connectDatabase();
	}
	
	@Test
	public void databaseConnection() {
		assertTrue(database.connectDatabase());
	}
	
	@Test
	public void clearTablesTest()
	{
		assertTrue(database.clearTables(database.getConnection()));
	}
	
	@Test
	public void initDatabaseTest()
	{
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
		assertTrue(database.readCustDB(customers, database.getConnection()));
	}
}
