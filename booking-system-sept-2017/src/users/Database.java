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
	private String emplSQL;
	private String emplAvailSQL;
	
 	public Connection getConnection()
	{
		return connection;
	}
	
	public String getCustSQL()
	{
		return custSQL;
	}
	
	public String getEmplSQL()
	{
		return emplSQL;
	}
	
	public String getEmplAvailSQL()
	{
		return emplAvailSQL;
	}

	public boolean connectDatabase(String url)
	{
		try{
			Class.forName("org.sqlite.JDBC");
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
					" AVAIL_DAY		INT NOT NULL		," +
					" TIMESLOT		INT	NOT NULL		," +
					" BOOKED		VARCHAR(40)			," +
					" PRIMARY KEY(EMP_ID, AVAIL_DAY, TIMESLOT)" +
					" FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEES (EMP_ID))";
		
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
			
			sql = "INSERT INTO BUSINESSES VALUES('StGeorges','St Georges', 'Henry', 'Gray', 'Blackshaw Road Melbourne', '86721255', 'StGeorges')";
			stmt.executeUpdate(sql);
			
			sql= "INSERT INTO EMPLOYEES VALUES('0001', 'Harry', 'Jones')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', '0', '0', 'no')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', '0', '1', 'no')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', '0', '2', 'yes')";
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
	
	public boolean readEmplDB(ArrayList<Employee> employees, Connection connection)
	{
		ResultSet resultSet = null;
		Employee newEmpl;
		
		try{
			resultSet = connection.createStatement().executeQuery("SELECT * FROM EMPLOYEES");
			while(resultSet.next())
			{
				System.out.println(resultSet);
				
				String employeeID = resultSet.getString("EMP_ID");
				String fName = resultSet.getString("EMP_FNAME");
				String lName = resultSet.getString("EMP_LNAME");
				
				newEmpl = new Employee(employeeID, fName, lName);
				employees.add(newEmpl);
			}
			return true;
		}catch (SQLException e)
		{
			System.out.println("Unable to load Employees");
			return false;
		}
	}
	
	public boolean readAvailablityTimes(ArrayList<Employee> employees, Connection connection)
	{
		ResultSet resultSet = null;
		
		try{
			resultSet = connection.createStatement().executeQuery("SELECT * FROM EMP_AVAIL");
			while(resultSet.next())
			{
				System.out.println(resultSet);
				
				String employeeID = resultSet.getString("EMP_ID");
				int timeslot = resultSet.getInt("TIMESLOT");
				int day = resultSet.getInt("AVAIL_DAY");
				String booked = resultSet.getString("BOOKED");
				
				for(int i = 0; i < employees.size(); i++)
				{
					if(employees.get(i).getEmployeeID().equals(employeeID))
					{
						employees.get(i).setAvailibleTime(timeslot, day, booked);
						break;
					}
				}
			}
			return true;
				
		}catch (SQLException e)
		{
			System.out.println("Unable to load employee availible times");
			return false;
		}
	}

 	public void writeCustDB(ArrayList<Customer> customers, Connection connection)
	{		
		for(int i = 0; i < customers.size(); i++)
		{
			try{
				custToString(customers, i);
				String sql = "INSERT INTO CUSTOMERS VALUES(" + getCustSQL() +")";
			    Statement stmt = connection.createStatement();
			    stmt.executeUpdate(sql);
			    
			}catch (SQLException ex) {
				System.out.println("Customer record already exists. No changes were made.");
			}
		}
	}
	
	public boolean writeNewCustToDB(ArrayList<Customer> customers, int position, Connection connection)
	{
		try{
			custToString(customers, position);
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
	
	public boolean writeEmplToDB(ArrayList<Employee> employees , Connection connection)
	{
		for(int i = 0; i < employees.size(); i++)
		{
			try{
				String sql;
				Statement stmt = connection.createStatement();
			    for(int timeslot = 0; timeslot < employees.get(i).availibleTimes.length; timeslot++)
			    {
			    	for(int day = 0; day < employees.get(i).availibleTimes[timeslot].length; day++)
			    	{
			    		int booked = employees.get(i).availibleTimes[timeslot][day];
			    		if(booked == 1 || booked == 2)
			    		{
			    			try{
			    			emplAvailToString(employees.get(i).getEmployeeID(), day, timeslot, booked);
							sql = "INSERT INTO EMP_AVAIL VALUES(" + getEmplAvailSQL() +")";
						    stmt.executeUpdate(sql);
			    			}catch (SQLException e)
			    			{
			    				System.out.println("Employee already availible that timeslot");
			    			}
			    		}
			    	}
			    }
				sql = "INSERT INTO EMPLOYEES VALUES(" + getEmplSQL() +")";
			    stmt.executeUpdate(sql);
			    
			}catch (SQLException ex) {
				System.out.println("Employee record already exists. No changes were made.");
			}
		}
		return true;
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
	
	public boolean emplToString(ArrayList<Employee> employees, int position)
	{
		String empID = employees.get(position).getEmployeeID();
		String fName = employees.get(position).getFirstName();
		String lName = employees.get(position).getLastName();
		
		this.emplSQL = "'" + empID + ", '" + fName + "', '" + lName + "'"; 
		return true;
	}

	public boolean emplAvailToString(String emplID, int day, int timeslot, int booked)
	{
		String isBooked;
		if(booked == 2)
		{
			isBooked = "yes";
		}
		else
		{
			isBooked = "no";
		}
			
		this.emplAvailSQL = "'"+ emplID + "', " + day + ", " + timeslot + ", '" + isBooked + "'";
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

