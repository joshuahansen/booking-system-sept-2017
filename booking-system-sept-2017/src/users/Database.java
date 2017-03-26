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
	
	public void readDB(Connection connection, ArrayList<Customer> customers) throws Exception
	{
		ResultSet resultSet = null;
		Customer newCust;
		
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
		
		 System.out.println(customers.get(0).getUsername() + " " + customers.get(0).getPassword());
		 System.out.println(customers.get(1).getUsername() + " " + customers.get(1).getPassword());
		 System.out.println(customers.get(2).getUsername() + " " + customers.get(2).getPassword());
	}

}

