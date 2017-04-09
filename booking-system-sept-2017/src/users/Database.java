package users;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.Booking;
//database class has all methods needed for program to interact with the database
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

	//connect to the database. return false if unable to connect
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
			return false;
		} catch (SQLException e) {
			System.out.println("ERROR: Could not load database");
			return false;
		}
	}
	
	//initialize database tables if there is no database found
	public boolean initDatabase()
	{
		//try creating tables return true if successful
		try{
			Statement stmt = connection.createStatement();
			
			//Customer table using username as the primary key
			String sql = "CREATE TABLE CUSTOMERS " +
			        "(CUST_UNAME     	VARCHAR(40) NOT NULL," +
			        " CUST_FNAME 		VARCHAR(40)     ," +
			        " CUST_LNAME		VARCHAR(40)		," +
			        " CUST_ADDRESS    	VARCHAR(40)     ," +
			        " CUST_PHONE      	VARCHAR(40)     ," +
			        " CUST_PASSWORD		VARCHAR(40)		," +
			        " PRIMARY KEY(CUST_UNAME))";
			stmt.executeUpdate(sql);
			
			//Business table using business username as the primary key
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
			
			//EMployee table using employee ID as the primary key
			sql = "CREATE TABLE EMPLOYEES " +
					"(EMP_ID		VARCHAR(40) NOT NULL," +
					" EMP_FNAME		VARCHAR(40)		," +
					" EMP_LNAME		VARCHAR(40)		," +
					" PRIMARY KEY(EMP_ID))";
			
			stmt.executeUpdate(sql);
			
			//Employee availability table using employee ID, available day and timeslot as the primary key
			//Employee ID is a foreign key
			sql = "CREATE TABLE EMP_AVAIL " +
					"(EMP_ID		VARCHAR(40)	NOT NULL," +
					" AVAIL_DAY		INT NOT NULL		," +
					" TIMESLOT		INT	NOT NULL		," +
					" BOOKED		VARCHAR(40)			," +
					" PRIMARY KEY(EMP_ID, AVAIL_DAY, TIMESLOT)" +
					" FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEES (EMP_ID))";
		
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE BOOKINGS " +
					"(BOOKING_ID		VARCHAR(40) NOT NULL," +
					" AVAIL_DAY			INT				," +
					" TIMESLOT			INT				," +
					" DATE				INT				," +
					" MONTH				INT				," +
					" YEAR				INT				," +
					" COMPLETED			BOOLEAN			," +
					" CUST_UNAME		VARCHAR(40)		," +
					" EMP_ID			VARCHAR(40)     ," +
					" PRIMARY KEY (BOOKING_ID)," +
					" FOREIGN KEY (CUST_UNAME) REFERENCES CUSTOMERS (CUST_UNAME)," +
					" FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEES (EMP_ID))";
			
			stmt.executeUpdate(sql);
			
			System.out.println("Database Initialised");
			return true;
			
		}catch(SQLException e)
		{
			//Catch any SQL exceptions, print message and return false 
			System.out.println("Database cannot be initialised");
			return false;
		}
	}
	
	//clear old database tables. return true when clears or false if unable to clear
	public boolean clearTables()
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
			
			sql = "drop talbe bookings";
			stmt.executeUpdate(sql);
			
			System.out.println("Database tables cleared");
			return true;
			
		}catch(SQLException e)
		{
			System.out.println("Can not delete tables");
			return false;
		}
	}
	
	//populate a new database with some default values
	public boolean defaultValues()
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
			
			sql = "INSERT INTO BUSINESSES VALUES('fit4purpose','Fit for Purpose', 'Henry', 'Gray', 'Blackshaw Road Melbourne', '86721255', 'superfit')";
			stmt.executeUpdate(sql);
			
			sql= "INSERT INTO EMPLOYEES VALUES('0001', 'Harry', 'Jones')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', '0', '0', 'no')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', '0', '1', 'no')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', '0', '2', 'yes')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKINGS VALUES('001', 0, 2, 3, 4, 2017, 'true', 'bMarley', '0001')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKINGS VALUES('002', 1, 0, 4, 4, 2017, 'true', 'VickiV', '0001')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKINGS VALUES('003', 2, 1, 5, 4, 2017, 'true', 'jd666', '0001')";
			stmt.executeUpdate(sql);
			
			System.out.println("Database set to defualt values");
			return true;
			
		}catch (SQLException e)
		{
			System.out.println("Could not set database values to default");
			
			return false;
		}
	}
	
	//read data from CUSTOMER table into customer array
	public boolean readCustDB(ArrayList<Customer> customers)
	{
		ResultSet resultSet = null;
		Customer newCust;
		
		try {
			resultSet = connection.createStatement().executeQuery("SELECT * FROM CUSTOMERS");
			
			//read one row at a time adding customers to array. loop until no rows left
		while(resultSet.next())		
		{			
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
	
	//read data from BUSINESS table into business array
	public boolean readBusDB(ArrayList<Business> businesses)
	{
		ResultSet resultSet = null;
		Business newBus;
	
		//read one row from business table create new business object. loop until table has no new rows
		try{
			resultSet = connection.createStatement().executeQuery("SELECT * FROM BUSINESSES");
			while(resultSet.next())
			{				
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
	
	//read data from EMPLOYEE table into employee array
	public boolean readEmplDB(ArrayList<Employee> employees)
	{
		ResultSet resultSet = null;
		Employee newEmpl;
		
		try{
			resultSet = connection.createStatement().executeQuery("SELECT * FROM EMPLOYEES");
			while(resultSet.next())
			{
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
	
	//read data from EMP_AVAIL table into correct employee availabilities array
	public boolean readAvailablityTimes(ArrayList<Employee> employees)
	{
		ResultSet resultSet = null;
		
		/*read availability from table. match employee ID in employee array to ID from emp_avail and 
		 * add availability to employees array*/
		try{
			resultSet = connection.createStatement().executeQuery("SELECT * FROM EMP_AVAIL");
			while(resultSet.next())
			{
				String employeeID = resultSet.getString("EMP_ID");
				int timeslot = resultSet.getInt("TIMESLOT");
				int day = resultSet.getInt("AVAIL_DAY");
				String booked = resultSet.getString("BOOKED");
				
				for(int i = 0; i < employees.size(); i++)
				{
					if(employees.get(i).getEmployeeID().equals(employeeID))
					{
						employees.get(i).setAvailableTime(timeslot, day, booked);
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
	
	public boolean readBookingsDB(ArrayList<Business> businesses, ArrayList<Customer> customers, ArrayList<Employee> employees)
	{
		ResultSet resultSet = null;
		Booking newBooking;

		try{
			resultSet = connection.createStatement().executeQuery("SELECT * FROM BOOKINGS");
			while(resultSet.next())
			{				
				String bookingID = resultSet.getString("BOOKING_ID");
				int day = resultSet.getInt("AVAIL_DAY");
				int timeslot = resultSet.getInt("TIMESLOT");
				int date = resultSet.getInt("DATE");
				int month = resultSet.getInt("MONTH");
				int year = resultSet.getInt("YEAR");
				boolean completed = resultSet.getBoolean("COMPLETED");
				String custUname = resultSet.getString("CUST_UNAME");
				String employeeID = resultSet.getString("EMP_ID");
				
				int custPos = 0, employeePos = 0;
				
				for(int i = 0; i < customers.size(); i++)
				{
					if(customers.get(i).getUsername().equals(custUname))
					{
						custPos = i;
					}
				}
				for(int i = 0; i < employees.size(); i++)
				{
					if(employees.get(i).getEmployeeID().equals(employeeID))
					{
						employeePos = i;
					}
				}
				
				LocalDate bookingDate = LocalDate.of(year, month, date);
				newBooking = new Booking(bookingID, day, timeslot, bookingDate, completed,  customers.get(custPos), employees.get(employeePos));
				businesses.get(0).bookings.add(newBooking);
			}
			return true;
		}catch (SQLException e)
		{
			System.out.println("Unable to load Bookings");
			return false;
		}
	}

	//write whole customer array to the database
 	public void writeCustDB(ArrayList<Customer> customers)
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
	
 	//only write last customer in array (New Customer) into database
	public boolean writeNewCustToDB(ArrayList<Customer> customers, int position)
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
	
	//write employee array into database including available times array
	public boolean writeEmplToDB(ArrayList<Employee> employees)
	{
		for(int i = 0; i < employees.size(); i++)
		{
			try{
				String sql;
				Statement stmt = connection.createStatement();
			    for(int timeslot = 0; timeslot < employees.get(i).availableTimes.length; timeslot++)
			    {
			    	for(int day = 0; day < employees.get(i).availableTimes[timeslot].length; day++)
			    	{
			    		int booked = employees.get(i).availableTimes[timeslot][day];
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
			    emplToString(employees, i);
				sql = "INSERT INTO EMPLOYEES VALUES(" + getEmplSQL() +")";
			    stmt.executeUpdate(sql);
			    
			}catch (SQLException ex) {
				System.out.println("Employee record already exists. No changes were made.");
			}
		}
		return true;
	}
	
	//convert objects to strings for SQL commands
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
		
		this.emplSQL = "'" + empID + "', '" + fName + "', '" + lName + "'"; 
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
	
	//close connection to the database
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
	
	//delete all records in a specified table keeping table 
	public boolean deleteAllRecords(String table)
	{
		try{
			Statement stmt = connection.createStatement();
			
			String sql = "DELETE FROM " + table;
			stmt.executeUpdate(sql);

		}catch (SQLException e)
		{
			System.out.println("Unable to clear records from " + table + " table.");
			
		}
		return true;
	}
}

