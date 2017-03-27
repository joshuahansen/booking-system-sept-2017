package users;

import java.sql.Statement;
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
			System.exit(0);
		}
		
		return connection;
	}
	
	public boolean readCustDB(Connection connection, ArrayList<Customer> customers)
	{
		ResultSet resultSet = null;
		Customer newCust;
		
		try {
			resultSet = connection.createStatement().executeQuery("SELECT * FROM CUSTOMERS");
		
		while(resultSet.next())		
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
		}
		
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
			while(resultSet.next())
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
			}
			
			return true;
		}catch (SQLException e) {
			System.out.println("Unable to load Business Database");
			return false;
		}
	}

	public void writeCustDB(Connection connection, ArrayList<Customer> customers)
	{
		Statement stmt = null;
			
		for(int i = 0; i < customers.size(); i++)
		{
			try{
				String sql = "INSERT INTO CUSTOMERS VALUES(" + custToString(customers, i) +")";
			    stmt = connection.createStatement();
			    stmt.executeUpdate(sql);
			}catch (SQLException ex) {
				System.out.println("Customer record already exists. No changes were made.");
			}
		}
	}
	
	public void writeNewCustToDB(Connection connection, ArrayList<Customer> customers, int position)
	{
		Statement stmt = null;
			
			try{
				String sql = "INSERT INTO CUSTOMERS VALUES(" + custToString(customers, position) +")";
			    stmt = connection.createStatement();
			    stmt.executeUpdate(sql);
			}catch (SQLException ex) {
				System.out.println("Customer record already exists. No changes were made.");
			}
	}
	
	private String custToString(ArrayList<Customer> customers, int position)
	{
		String uName = customers.get(position).getUsername();
		String fName = customers.get(position).getFirstName();
		String lName = customers.get(position).getLastName();
		String address = customers.get(position).getAddress();
		String phone = customers.get(position).getContactNumber();
		String password = customers.get(position).getPassword();
		
		return "'"+ uName + "', '" + fName + "', '" + lName + "', '" + address + "', '" + phone + "', '" + password + "'";
	}

}

