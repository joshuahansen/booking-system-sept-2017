package test;

import static org.junit.Assert.*;

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
	}
	

	@Test
	public void databaseConnect() {
		assertEquals(null, database.connectDatabase());
	}

}
