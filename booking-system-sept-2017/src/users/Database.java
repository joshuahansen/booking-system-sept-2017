package users;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {

	
	public Connection ConnectDatabase()
	{
		Connection connection = null;
		try{
			Class.forName("org.h2.Driver");
			String url = "jdbc:h2:./database;IFEXISTS=TRUE";
			String user = "sa";
			String password = "";
			connection = DriverManager.getConnection(url,user,password);
			
		}catch(ClassNotFoundException ex){
			System.out.println("ERROR: Class not found: " + ex.getMessage());
			System.exit(0);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not load database");
		}
		
		return connection;
	}
	
	public boolean readCustDB(Connection connection, ArrayList<Customer> customers)
	{
		ResultSet resultSet = null;
		Customer newCust;
		
		try {
			resultSet = connection.createStatement().executeQuery("SELECT * FROM CUSTOMERS");
		
			resultSet.next();
		
		do
		{
			System.out.println(resultSet);
			
			String username = resultSet.getString("CUST_UNAME");
			String fName = resultSet.getString("CUST_FNAME");
			String lName = resultSet.getString("CUST_LNAME");
			String address = resultSet.getString("CUST_ADDRESS");
			String phone = resultSet.getString("CUST_PHONE");
			String password = resultSet.getString("CUST_PASSWORD");

			newCust = new Customer(fName, lName, address, phone, username, password);
			customers.add(newCust);
		}while(resultSet.next());
		
		return true;
	}catch (SQLException e) {
		System.out.println("Unable to load Customer Database");
		return false;
	}
	
	
	}
	
	public boolean readBusDB(Connection connection, ArrayList<Business> businesses)
	{
		ResultSet resultSet = null;
		Business newBus;
		
		try{
			resultSet = connection.createStatement().executeQuery("SELECT * FROM BUSINESSES");
			resultSet.next();
			
			do
			{
				System.out.println(resultSet);
				
				String bName = resultSet.getString("BUS_BNAME");
				String username = resultSet.getString("BUS_UNAME");
				String fName = resultSet.getString("BUS_FNAME");
				String lName = resultSet.getString("BUS_LNAME");
				String address = resultSet.getString("BUS_ADDRESS");
				String phone = resultSet.getString("BUS_PHONE");
				String password = resultSet.getString("BUS_PASSWORD");
	
				newBus = new Business(bName, fName, lName, address, phone, username, password);
				businesses.add(newBus);
			}while(resultSet.next());
			
			return true;
		}catch (SQLException e) {
			System.out.println("Unable to load Business Database");
			return false;
		}
	}
}

