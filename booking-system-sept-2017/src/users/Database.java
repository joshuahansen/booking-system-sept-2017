package users;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.Booking;
import main.Session;
//database class has all methods needed for program to interact with the database
public class Database {
	private Connection connection = null;
	private String custSQL;
	private String emplSQL;
	private String emplAvailSQL;
	private String bookingSQL;
	
	
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
	
	public String getBookingSQL()
	{
		return bookingSQL;
	}

	//connect to the database. return false if unable to connect
	public boolean connectDatabase(Session session, String url)
	{
		try{
			Class.forName("org.sqlite.JDBC");
			String user = "sa";
			String password = "";
			connection = DriverManager.getConnection(url,user,password);
			System.out.println("Connected to database");
			session.addLog("Connected to database");
			return true;
			
		}catch(ClassNotFoundException ex){
			System.out.println("ERROR: Class not found: " + ex.getMessage());
			session.addLog("ERROR: Class not found: " + ex.getMessage());
			return false;
		} catch (SQLException e) {
			System.out.println("ERROR: Could not load database");
			session.addLog("ERROR: Could not load database");
			return false;
		}
	}
	
	//initialize database tables if there is no database found
	public boolean initDatabase(Session session)
	{
		session.addLog("Initialising database");
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
					" BUS_UNAME		VARCHAR(40)		," +
					" PRIMARY KEY(EMP_ID)"				+
					" FOREIGN KEY (BUS_UNAME) REFERENCES BUSINESSES (BUS_UNAME))";
			
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
					" SESSIONTYPE		VARCHAR(40)		," +	
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
			session.addLog("Database initalised");
			return true;
			
		}catch(SQLException e)
		{
			//Catch any SQL exceptions, print message and return false 
			System.out.println("Database cannot be initialised");
			session.addLog("Database could not be initialised");
			return false;
		}
	}
	
	//clear old database tables. return true when clears or false if unable to clear
	public boolean clearTables(Session session)
	{
		session.addLog("Clearing old database tables");
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
			
			sql = "drop table bookings";
			stmt.executeUpdate(sql);
			
			System.out.println("Database tables cleared");
			session.addLog("Database tables cleared");
			return true;
			
		}catch(SQLException e)
		{
			System.out.println("Can not delete tables");
			session.addLog("Cannot delete tables");
			return false;
		}
	}
	
	//populate a new database with some default values
	public boolean defaultValues(Session session)
	{
		session.addLog("Populating new database with default values");
		
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
			
			sql= "INSERT INTO EMPLOYEES VALUES('0001', 'Harry', 'Jones', 'fit4purpose')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', '0', '0', 'no')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', '0', '1', 'no')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', '0', '1', 'no')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKINGS VALUES('001', 'CROSSFIT', 0, 2, 3, 4, 2017, 'true', 'bMarley', '0001')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKINGS VALUES('002', 'WEIGHTS', 1, 0, 4, 4, 2017, 'true', 'VickiV', '0001')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKINGS VALUES('003', 'SPIN', 2, 1, 5, 4, 2017, 'true', 'jd666', '0001')";
			stmt.executeUpdate(sql);
			
			System.out.println("Database set to defualt values");
			session.addLog("Database set to default values");
			return true;
			
		}catch (SQLException e)
		{
			System.out.println("Could not set database values to default");
			session.addLog("Could not set database values to default");
			
			return false;
		}
	}
	
	//read data from CUSTOMER table into customer array
	public boolean readCustDB(Session session, ArrayList<Customer> customers)
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
			session.addLog("Unable to load Customer Databse");
			return false;
		}
	}
	
	//read data from BUSINESS table into business array
	public boolean readBusDB(Session session, ArrayList<Business> businesses)
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
			session.addLog("Unable to load Business Database");
			return false;
		}
	}
	
	//read data from EMPLOYEE table into employee array
	public boolean readEmplDB(Session session, ArrayList<Business> businesses)
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
				String busUname = resultSet.getString("BUS_UNAME");
				
				newEmpl = new Employee(employeeID, fName, lName);
				
				for(int i = 0; i < businesses.size(); i++)
				{
					if(businesses.get(i).getUsername().equals(busUname))
					{
						businesses.get(i).employees.add(newEmpl);
					}
				}
			}
			return true;
		}catch (SQLException e)
		{
			System.out.println("Unable to load Employees");
			session.addLog("Unable to load employees");
			return false;
		}
	}
	
	//read data from EMP_AVAIL table into correct employee availabilities array
	public boolean readAvailablityTimes(Session session, ArrayList<Business> businesses)
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
				
				for(int busNo = 0; busNo < businesses.size(); busNo++)
				{
					for(int i = 0; i < businesses.get(busNo).employees.size(); i++)
					{
						if(businesses.get(busNo).employees.get(i).getEmployeeID().equals(employeeID))
						{
							businesses.get(busNo).employees.get(i).setAvailableTime(day, timeslot, booked);
							break;
						}
					}
				}
			}
			return true;
				
		}catch (SQLException e)
		{
			System.out.println("Unable to load employee availible times");
			session.addLog("Unable to load employee available times");
			return false;
		}
	}
	
	public boolean readBookingsDB(Session session, ArrayList<Business> businesses, ArrayList<Customer> customers)
	{
		ResultSet resultSet = null;
		Booking newBooking;

		try{
			resultSet = connection.createStatement().executeQuery("SELECT * FROM BOOKINGS");
			while(resultSet.next())
			{				
				String bookingID = resultSet.getString("BOOKING_ID");
				String sessionType = resultSet.getString("SESSIONTYPE");
				int day = resultSet.getInt("AVAIL_DAY");
				int timeslot = resultSet.getInt("TIMESLOT");
				int date = resultSet.getInt("DATE");
				int month = resultSet.getInt("MONTH");
				int year = resultSet.getInt("YEAR");
				boolean completed = resultSet.getBoolean("COMPLETED");
				String custUname = resultSet.getString("CUST_UNAME");
				String employeeID = resultSet.getString("EMP_ID");
				
				int custPos = 0, employeePos = 0, businessPos = 0;
				
				for(int i = 0; i < customers.size(); i++)
				{
					if(customers.get(i).getUsername().equals(custUname))
					{
						custPos = i;
					}
				}
				for(int busNo = 0; busNo < businesses.size(); busNo++)
				{

					for(int i = 0; i < businesses.get(busNo).employees.size(); i++)
					{
						if(businesses.get(busNo).employees.get(i).getEmployeeID().equals(employeeID))
						{
							employeePos = i;
							businessPos = busNo;
							break;
						}
					}
				}
				
				LocalDate bookingDate = LocalDate.of(year, month, date);
				newBooking = new Booking(bookingID, sessionType, day, timeslot, bookingDate, completed,  customers.get(custPos), businesses.get(businessPos).employees.get(employeePos));
				businesses.get(0).bookings.add(newBooking);
			}
			return true;
		}catch (SQLException e)
		{
			System.out.println("Unable to load Bookings");
			session.addLog("Unable to load bookings");
			return false;
		}
	}

	//write whole customer array to the database
 	public void writeCustDB(Session session, ArrayList<Customer> customers)
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
				session.addLog("Customer record already exists. No changes were made.");
			}
		}
	}
	
 	//only write last customer in array (New Customer) into database
	public boolean writeNewCustToDB(Session session, ArrayList<Customer> customers, int position)
	{
		try{
			custToString(customers, position);
			String sql = "INSERT INTO CUSTOMERS VALUES(" + getCustSQL() +")";
		    Statement stmt = connection.createStatement();
		    stmt.executeUpdate(sql);
		    System.out.println("Customer added to database");
		    session.addLog("Customer added to database.");
		    return true;

		}catch (SQLException ex) {
			System.out.println("Customer record already exists. No changes were made.");
			session.addLog("Customer record already exists. No changes were made.");
			
			return false;
		}
	}
	
	//write employee array into database including available times array
	public boolean writeEmplToDB(Session session, ArrayList<Business> businesses)
	{
		for(int busNo = 0; busNo < businesses.size(); busNo++)
		{
			for(int i = 0; i < businesses.get(busNo).employees.size(); i++)
			{
				try{
					String sql;
					Statement stmt = connection.createStatement();
				    for(int day = 0; day < businesses.get(busNo).employees.get(i).availableTimes.length; day++)
				    {
				    	for(int timeslot = 0; timeslot < businesses.get(busNo).employees.get(i).availableTimes[day].length; timeslot++)
				    	{
				    		int booked = businesses.get(busNo).employees.get(i).availableTimes[day][timeslot];
				    		if(booked == 1 || booked == 2)
				    		{
				    			try{
					    			emplAvailToString(businesses.get(busNo).employees.get(i).getEmployeeID(), day, timeslot, booked);
									sql = "INSERT INTO EMP_AVAIL VALUES(" + getEmplAvailSQL() +")";
								    stmt.executeUpdate(sql);
				    			}catch (SQLException e)
				    			{
				    				System.out.println("Employee already availible that timeslot");
				    				session.addLog("Employee already available in that timeslot.");
				    			}
				    		}
				    	}
				    }
				    emplToString(businesses.get(busNo).employees, i);
					sql = "INSERT INTO EMPLOYEES VALUES(" + getEmplSQL() +")";
				    stmt.executeUpdate(sql);
				    
				}catch (SQLException ex) {
					System.out.println("Employee record already exists. No changes were made.");
					session.addLog("Employee record already exists. No changes were made.");
				}
			}
		}
		return true;
	}
	
	//write Bookings array into database
	public boolean writeBookingToDB(Session session, ArrayList<Business> businesses)
	{
		try{
			String sql;
			Statement stmt = connection.createStatement();
	
			sql = "DELETE FROM BOOKINGS";
			stmt.executeUpdate(sql);
					
			for(int busNo = 0; busNo < businesses.size(); busNo++)
			{
				for(int i = 0; i < businesses.get(busNo).bookings.size(); i++)
				{
				    bookingToString(businesses.get(busNo).bookings.get(i));
					sql = "INSERT INTO BOOKINGS VALUES(" + getBookingSQL() +")";
					stmt.executeUpdate(sql);
				}
			}
			}catch (SQLException ex) {
				System.out.println("Booking record already exists. No changes were made.");
				session.addLog("Booking record already exists. No changes were made.");
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
	
	private boolean bookingToString(Booking booking)
	{
		String bookingId = booking.getBookingID();
		String sessionType = booking.getSessionType();
		int avail_day = booking.getDay();
		int timeslot = booking.getTimeslot();
		LocalDate bookingDate = booking.getDate();
		int date = bookingDate.getDayOfMonth();
		int month = bookingDate.getMonthValue();
		int year = bookingDate.getYear();
		boolean completed = booking.getCompleted();
		String custUname = booking.getCustUsername();
		String employeeID = booking.getEmployeeID();
		this.bookingSQL = "'"+ bookingId + "', '" + sessionType + "', " + avail_day + ", " + timeslot + ", "  + date + ", " + month + ", " + year + ", '" + completed + "', '" 
				+ custUname + "', '" + employeeID + "'";
		return true;				
	}
	
	//close connection to the database
	public boolean closeConnection(Session session)
	{
		try{
			connection.close();
			session.addLog("Connection to database closed.");
			return true;
		}catch (SQLException e)
		{
			System.out.println("Can not close connection");
			session.addLog("Cannot close connection to database.");
			return false;
		}
	}
	
	//delete all records in a specified table keeping table 
	public boolean deleteAllRecords(Session session, String table)
	{
		try{
			Statement stmt = connection.createStatement();
			
			String sql = "DELETE FROM " + table;
			stmt.executeUpdate(sql);

		}catch (SQLException e)
		{
			System.out.println("Unable to clear records from " + table + " table.");
			session.addLog("Unable to clear records from " + table + " table.");
		}
		return true;
	}
}

