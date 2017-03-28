package users;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
	private Connection connection = null;
	private String custSQL;
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public String getCustSQL()
	{
		return custSQL;
	}
	

	public boolean connectDatabase()
	{
		try{
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:./database.db";
			String user = "sa";
			String password = "";
			connection = DriverManager.getConnection(url,user,password);
			System.out.println("Connected to database");
			return true;
			
		}catch(ClassNotFoundException ex){
			System.out.println("ERROR: Class not found: " + ex.getMessage());
//			System.exit(0);
			return false;
		} catch (SQLException e) {
			System.out.println("ERROR: Could not load database");
//			System.exit(0);
			return false;
		}
	}
	
	public boolean initDatabase(Connection connection)
	{
		try{
			Statement stmt = connection.createStatement();
			
			String sql = "CREATE TABLE CUSTOMERS " +
			        "(CUST_UNAME     	VARCHAR(40) NOT NULL," +
			        " CUST_FNAME 		VARCHAR(40)     ," +
			        " CUST_LNAME		VARCHAR(40)		," +
			        " CUST_ADDRESS    	VARCHAR(40)     ," +
			        " CUST_PHONE      	VARCHAR(40)     ," +
			        " CUST_PASSWORD		VARCHAR(40)		," +
			        " PRIMARY KEY(CUST_UNAME))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE BUSINESSES " +
					" (BUS_UNAME     	VARCHAR(40)	NOT NULL," +
			        " BUS_BNAME			VARCHAR(40)		," +
			        " BUS_FNAME      	VARCHAR(40)     ," +
			        " BUS_LNAME			VARCHAR(40)		," +
			        " BUS_ADDRESS    	VARCHAR(40)     ," +
			        " BUS_PHONE      	VARCHAR(40)     ," +
			        " BUS_PASSWORD		VARCHAR(40)		," +
			        " PRIMARY KEY(BUS_UNAME))";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE EMPLOYEES " +
					"(EMP_ID		VARCHAR(40) NOT NULL," +
					" EMP_FNAME		VARCHAR(40)		," +
					" EMP_LNAME		VARCHAR(40)		," +
					" PRIMARY KEY(EMP_ID))";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE EMP_AVAIL " +
					"(EMP_ID		VARCHAR(40)	NOT NULL," +
					" AVAIL_DAY		VARCHAR(40)		," +
					" AVAIL_HOURS	INTEGER			," +
					" PRIMARY KEY(EMP_ID, AVAIL_DAY, AVAIL_HOURS)		," +
					" FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEES(EMP_ID))";
		
			stmt.executeUpdate(sql);
			
			System.out.println("Database Initialised");
			return true;
			
		}catch(SQLException e)
		{
			System.out.println("Database cannot be initialised");
			return false;
		}
	}
	
	public boolean clearTables(Connection connection)
	{
		try{
			Statement stmt = connection.createStatement();
			
			String sql = "drop table customers";
			stmt.executeUpdate(sql);
			
			sql = "drop table businesses";
			stmt.executeUpdate(sql);
			
			sql = "drop table employees";
			stmt.executeUpdate(sql);
			
			sql = "drop table emp_avail";
			stmt.executeUpdate(sql);
			
			System.out.println("Database tables cleared");
			return true;
			
		}catch(SQLException e)
		{
			System.out.println("Can not delete tables");
			return false;
		}
	}
	
	public boolean defaultValues(Connection connection)
	{
		try{
			Statement stmt = connection.createStatement();
			String sql;
			
			sql = "INSERT INTO CUSTOMERS VALUES('bMarley', 'Bob', 'Marley', '1 High Street Melbourne', '0423256754', 'bMarley')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO CUSTOMERS VALUES('VickiV', 'Vicki', 'Vale', '23 Batman Street Melbourne', '34232865', 'VickiV')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO CUSTOMERS VALUES('jd666', 'John', 'Doe', '6 Cemetery Drive Melbourne', '0423254323', 'jd666')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BUSINESSES VALUES('St Georges Hospitle', 'StGeorges', 'Henry', 'Gray', 'Blackshaw Road Melbourne', '86721255', 'StGeorges')";
			stmt.executeUpdate(sql);
			
			System.out.println("Database set to defualt values");
			return true;
			
		}catch (SQLException e)
		{
			System.out.println("Could not set database values to default");
			
			return false;
		}
	}
	
	public boolean readCustDB(ArrayList<Customer> customers, Connection connection)
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
	
	public boolean readBusDB(ArrayList<Business> businesses, Connection connection)
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

	public void writeCustDB(ArrayList<Customer> customers, Connection connection)
	{		
		for(int i = 0; i < customers.size(); i++)
		{
			try{
				String sql = "INSERT INTO CUSTOMERS VALUES(" + custToString(customers, i) +")";
			    Statement stmt = connection.createStatement();
			    stmt.executeUpdate(sql);
			    
			}catch (SQLException ex) {
				System.out.println("Customer record already exists. No changes were made.");
			}
		}
	}
	
	public boolean writeNewCustToDB(Connection connection)
	{
		System.out.println(getCustSQL());
		try{
			String sql = "INSERT INTO CUSTOMERS VALUES(" + getCustSQL() +")";
		    Statement stmt = connection.createStatement();
		    stmt.executeUpdate(sql);
		    System.out.println("Customer added to database");
		    return true;

		}catch (SQLException ex) {
			System.out.println("Customer record already exists. No changes were made.");
			
			return false;
		}
	}
	
	public boolean custToString(ArrayList<Customer> customers, int position)
	{
		String uName = customers.get(position).getUsername();
		String fName = customers.get(position).getFirstName();
		String lName = customers.get(position).getLastName();
		String address = customers.get(position).getAddress();
		String phone = customers.get(position).getContactNumber();
		String password = customers.get(position).getPassword();
		
		this.custSQL = "'"+ uName + "', '" + fName + "', '" + lName + "', '" + address + "', '" + phone + "', '" + password + "'";
		
		return true;
	}

	public boolean closeConnection()
	{
		try{
			connection.close();
			return true;
		}catch (SQLException e)
		{
			System.out.println("Can not close connection");
			return false;
		}
	}
}

