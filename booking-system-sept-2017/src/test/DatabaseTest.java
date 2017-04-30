package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import users.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Session;


public class DatabaseTest {
	ArrayList<Customer> customers;
	ArrayList<Business> businesses;
	Database database;
	String url = "jdbc:sqlite:./databaseTest.db";
	Session session;
	
	@Before
	public void setup()
	{
		customers = new ArrayList<Customer>();
		businesses = new ArrayList<Business>();
		database = new Database();
		database.connectDatabase(session, url);
	}
	
	@Test
	public void databaseConnection() {
		assertTrue(database.connectDatabase(session, url));
	}
	
	@Test
	public void clearTablesTest()
	{
		database.initDatabase(session);
		assertTrue(database.clearTables(session));
	}
	
	@Test
	public void initDatabaseTest()
	{
		database.clearTables(session);
		assertTrue(database.initDatabase(session));
	}
	
	@Test
	public void defaultValuesTest()
	{
		database.clearTables(session);
		database.initDatabase(session);
		assertTrue(database.defaultValues(session));
	}

	@Test
	public void readCustDBTest()
	{
		database.initDatabase(session);
		database.defaultValues(session);
		assertTrue(database.readCustDB(session, customers));
	}
	
	@Test
	public void readBusDBTest()
	{
		database.initDatabase(session);
		database.defaultValues(session);
		assertTrue(database.readBusDB(session, businesses));
	}
	
	@Test
	public void readEmplyeeDB()
	{
		database.initDatabase(session);
		database.defaultValues(session);
		assertTrue(database.readEmplDB(session, businesses));
	}
	
	@Test
	public void readEmplAvailDB()
	{
		database.initDatabase(session);
		database.defaultValues(session);
		assertTrue(database.readAvailablityTimes(session, businesses));
	}

	@Test
	public void writeNewCustToDBTest()
	{
		database.initDatabase(session);
		Customer newCust = new Customer("test", "dummy", "ANCAP PO Box 4041 Manuka ACT 2603", "62320232", "ancapdummy", "crashtest");
		customers.add(newCust);
		int position = customers.size()-1;
		
		assertTrue(database.writeNewCustToDB(session, customers, position));
	}
	
	@Test
	public void writeEmplToDB()
	{
		database.clearTables(session);
		database.initDatabase(session);
		database.defaultValues(session);
		database.readBusDB(session, businesses);
		Employee newEmp = new Employee("0004", "Buster", "Mythbusters");
		businesses.get(0).employees.add(newEmp);
		
		assertTrue(database.writeEmplToDB(session, businesses));
	}
	
	@Test
	public void deleteAllRecordsTest()
	{
		database.initDatabase(session);
		database.defaultValues(session);
		String table = "CUSTOMERS";
		
		assertTrue(database.deleteAllRecords(session, table));
		
	}
	
	@After
	public void closeDatabase()
	{
		database.clearTables(session);
		database.closeConnection(session);
	}
}
