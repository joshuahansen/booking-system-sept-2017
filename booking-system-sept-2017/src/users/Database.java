package users;

import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.AvailableTime;
import main.Booking;
import main.Session;
//database class has all methods needed for program to interact with the database
public class Database {
	
	/**
	 * Connection to database and string SQL values.
	 */
	private Connection connection = null;
	private String custSQL;
	private String emplSQL;
	private String emplAvailSQL;
	private String bookingSQL;
	private String busiSQL;
	private String businessHourSQL;
	private String bookingTypeSQL;
	
	/**
	 * Accessor method for database connection.
	 * @return database connection
	 */
 	public Connection getConnection()
	{
		return connection;
	}
	
 	/**
	 * Accessor method for customer SQL.
	 * @return customer SQL
	 */
	public String getCustSQL()
	{
		return custSQL;
	}
	
	/**
	 * Accessor method for business SQL.
	 * @return business SQL
	 */
	public String getBusiSQL()
	{
		return busiSQL;
	}
	
	/**
	 * Accessor method for employee SQL.
	 * @return employee SQL
	 */
	public String getEmplSQL()
	{
		return emplSQL;
	}
	
	/**
	 * Accessor method for employee availability SQL.
	 * @return employee availability SQL
	 */
	public String getEmplAvailSQL()
	{
		return emplAvailSQL;
	}
	
	/**
	 * Accessor method for booking SQL.
	 * @return booking SQL
	 */
	public String getBookingSQL()
	{
		return bookingSQL;
	}
	
	/**
	 * Accessor method for business hours SQL.
	 * @return business hours SQL
	 */
	public String getBusinessHourSQL()
	{
		return businessHourSQL;
	}
	
	/**
	 * Accessor method for booking type SQL.
	 * @return booking type SQL
	 */
	public String getBookingTypeSQL()
	{
		return this.bookingTypeSQL;
	}
	
	/**
	 * Function to connect to the database on program startup.
	 * @param session object for this program session.
	 * @param url url for database connection.
	 * @return true if connection successful.
	 */
	public boolean connectDatabase(Session session, String url)
	{
		try{
			Class.forName("org.sqlite.JDBC");
			String user = "sa";
			String password = "";
			connection = DriverManager.getConnection(url,user,password);
			session.addLog("Connected to database");
			return true;
			
		}catch(ClassNotFoundException ex){
			System.out.println("ERROR: Class not found: " + ex.getMessage());
			session.addLog("ERROR: Class not found: " + ex.getMessage());
			return false;
		} catch (SQLException e) {
			session.addLog("ERROR: Could not load database");
			return false;
		}
	}
	
	/**
	 * Function to initialise the database tables.
	 * @param session database session object.
	 * @return true if successful
	 */
	public boolean initDatabase(Session session)
	{
		session.addLog("Initialising database");
		//try creating tables return true if successful
		try{
			Statement stmt = connection.createStatement();
			
			//Customer table using username as the primary key
			String sql = "CREATE TABLE CUSTOMERS " +
			        "(CUST_UNAME     	VARCHAR(40) NOT NULL,"	+
			        " CUST_FNAME 		VARCHAR(40)     ," 		+
			        " CUST_LNAME		VARCHAR(40)		," 		+
			        " CUST_ADDRESS    	VARCHAR(40)     ," 		+
			        " CUST_PHONE      	VARCHAR(40)     ," 		+
			        " CUST_PASSWORD		VARCHAR(40)		," 		+
			        " BUS_UNAME			VARCHAR(40) NOT NULL," 	+
			        " PRIMARY KEY(CUST_UNAME, BUS_UNAME),"		+
			        " FOREIGN KEY (BUS_UNAME) REFERENCES BUSINESSES (BUS_UNAME))";
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
					" EMP_FNAME		VARCHAR(40)		," 	+
					" EMP_LNAME		VARCHAR(40)		," 	+
					" BUS_UNAME		VARCHAR(40)		," 	+
					" PRIMARY KEY(EMP_ID, BUS_UNAME),"	+
					" FOREIGN KEY (BUS_UNAME) REFERENCES BUSINESSES (BUS_UNAME))";
			stmt.executeUpdate(sql);
			
			//Employee availability table using employee ID, available day and timeslot as the primary key
			//Employee ID is a foreign key
//			sql = "CREATE TABLE EMP_AVAIL " +
//					"(EMP_ID		VARCHAR(40)	NOT NULL," +
//					" AVAIL_DAY		INT NOT NULL		," +
//					" TIMESLOT		INT	NOT NULL		," +
//					" BOOKED		VARCHAR(40)			," +
//					" BUS_UNAME		VARCHAR(40)			," +
//					" PRIMARY KEY(EMP_ID, AVAIL_DAY, TIMESLOT, BUS_UNAME)" +
//					" FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEES (EMP_ID) " +
//					" FOREIGN KEY (BUS_UNAME) REFERENCES BUSINESSES (BUS_UNAME))";
//			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE EMP_AVAIL " +
					"(EMP_ID			VARCHAR(40)	NOT NULL," +
					" BUS_UNAME			VARCHAR(40)	NOT NULL," +
					" START_TIME_HOUR	INT NOT NULL		," +
					" START_TIME_MIN	INT	NOT NULL		," +
					" END_TIME_HOUR		INT NOT NULL		," +
					" END_TIME_MIN		INT	NOT NULL		," +
					" DAY				VARCHAR(40)	NOT NULL," +
					" PRIMARY KEY(EMP_ID, BUS_UNAME, DAY, START_TIME_HOUR)"	+
					" FOREIGN KEY (EMP_ID, BUS_UNAME) REFERENCES EMPLOYEES (EMP_ID, BUS_UNAME))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE BUSINESS_HOURS " +
					"(BUS_UNAME			VARCHAR(40)	NOT NULL," +
					" START_TIME_HOUR	INT NOT NULL		," +
					" START_TIME_MIN	INT	NOT NULL		," +
					" END_TIME_HOUR		INT NOT NULL		," +
					" END_TIME_MIN		INT	NOT NULL		," +
					" DAY				VARCHAR(40)			," +
					" PRIMARY KEY(BUS_UNAME, DAY)," +
					" FOREIGN KEY (BUS_UNAME) REFERENCES BUSINESSES (BUS_UNAME))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE BOOKINGS " +
					"(BOOKING_ID		VARCHAR(40) NOT NULL," +
					" SESSIONTYPE		VARCHAR(40)		," +	
					" START_TIME_HOUR	INT NOT NULL	," +
					" START_TIME_MIN	INT	NOT NULL	," +
					" END_TIME_HOUR		INT NOT NULL	," +
					" END_TIME_MIN		INT	NOT NULL	," +
					" DAY				VARCHAR(40)		," +
					" DATE				INT				," +
					" MONTH				INT				," +
					" YEAR				INT				," +
					" CUST_UNAME		VARCHAR(40)		," +
					" EMP_ID			VARCHAR(40)     ," +
					" BUS_UNAME			VARCHAR(40)     ," +
					" PRIMARY KEY (BOOKING_ID, BUS_UNAME)," +
					" FOREIGN KEY (CUST_UNAME) REFERENCES CUSTOMERS (CUST_UNAME)," +
					" FOREIGN KEY (EMP_ID, BUS_UNAME) REFERENCES EMPLOYEES (EMP_ID, BUS_UNAME))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE ADMIN " +
					"(USERNAME		VARCHAR(40) NOT NULL," +
					" PASSWORD		VARCHAR(40) NOT NULL," +
					" PRIMARY KEY (USERNAME))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE BOOKING_TYPE " +
					"(BUS_UNAME			VARCHAR(40) NOT NULL," 	+
					" BOOKING_TYPE		VARCHAR(40)	NOT NULL," 	+
					" BOOKING_LENGTH	INT			NOT NULL," 	+
					" PRIMARY KEY (BUS_UNAME, BOOKING_TYPE)," 	+
					" FOREIGN KEY (BUS_UNAME) REFERENCES BUSINESSES (BUS_UNAME))";
			stmt.executeUpdate(sql);
			
			
			session.addLog("Database initalised");
			return true;
			
		}catch(SQLException e)
		{
			//Catch any SQL exceptions, print message and return false 
			session.addLog("Database could not be initialised");
			return false;
		}
	}
	
	/**
	 * Function to clear old database tables.
	 * @param session database session object.
	 * @return true if successful
	 */
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
			
			session.addLog("Database tables cleared");
			return true;
			
		}catch(SQLException e)
		{
			session.addLog("Cannot delete tables");
			return false;
		}
	}
	
	/**
	 * Function to populate a new database with some default values.
	 * @param session database session object.
	 * @return true if successful
	 */
	public boolean defaultValues(Session session)
	{
		session.addLog("Populating new database with default values");
		
		try{
			Statement stmt = connection.createStatement();
			String sql;
			
			sql = "INSERT INTO CUSTOMERS VALUES('bMarley', 'Bob', 'Marley', '1 High Street Melbourne', '0423256754', 'bMarley', 'fit4purpose')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO CUSTOMERS VALUES('VickiV', 'Vicki', 'Vale', '23 Batman Street Melbourne', '34232865', 'VickiV', 'fit4purpose')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO CUSTOMERS VALUES('jd666', 'John', 'Doe', '6 Cemetery Drive Melbourne', '0423254323', 'jd666', 'fit4purpose')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BUSINESSES VALUES('fit4purpose','Fit for Purpose', 'Henry', 'Gray', 'Blackshaw Road Melbourne', '86721255', 'superfit')";
			stmt.executeUpdate(sql);
			
			sql= "INSERT INTO EMPLOYEES VALUES('0001', 'Harry', 'Jones', 'fit4purpose')";
			stmt.executeUpdate(sql);
			
			sql= "INSERT INTO EMPLOYEES VALUES('0002', 'Sally', 'Swing', 'fit4purpose')";
			stmt.executeUpdate(sql);
			
			sql= "INSERT INTO EMPLOYEES VALUES('0003', 'Bob', 'Jane', 'fit4purpose')";
			stmt.executeUpdate(sql);
			
//			sql = "INSERT INTO EMP_AVAIL VALUES('0001', '0', '0', 'no', 'fit4purpose')";
//			stmt.executeUpdate(sql);
//			
//			sql = "INSERT INTO EMP_AVAIL VALUES('0001', '0', '1', 'no', 'fit4purpose')";
//			stmt.executeUpdate(sql);
//			
//			sql = "INSERT INTO EMP_AVAIL VALUES('0001', '0', '2', 'no', 'fit4purpose')";
//			stmt.executeUpdate(sql);
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', 'fit4purpose', 9, 0, 12, 00, 'Monday')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', 'fit4purpose', 9, 0, 12, 00, 'Tuesday')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', 'fit4purpose', 12, 0, 17, 00, 'Wednesday')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', 'fit4purpose', 12, 0, 17, 00, 'Thursday')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO EMP_AVAIL VALUES('0001', 'fit4purpose', 17, 30, 20, 00, 'Friday')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BUSINESS_HOURS VALUES('fit4purpose', 9, 0, 17, 00, 'Monday')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BUSINESS_HOURS VALUES('fit4purpose', 9, 0, 17, 00, 'Tuesday')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BUSINESS_HOURS VALUES('fit4purpose', 9, 0, 17, 00, 'Wednesday')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BUSINESS_HOURS VALUES('fit4purpose', 9, 0, 17, 00, 'Thursday')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BUSINESS_HOURS VALUES('fit4purpose', 9, 0, 20, 00, 'Friday')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BUSINESS_HOURS VALUES('fit4purpose', 9, 0, 17, 00, 'Saturday')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BUSINESS_HOURS VALUES('fit4purpose', 9, 0, 17, 00, 'Sunday')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKINGS VALUES('001', 'CROSSFIT', 9, 0, 11, 0, 'Monday', 3, 4, 2017, 'bMarley', '0001', 'fit4purpose')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKINGS VALUES('002', 'WEIGHTS', 11, 0, 12, 0, 'Tuesday', 4, 4, 2017, 'VickiV', '0001', 'fit4purpose')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKINGS VALUES('003', 'SPIN', 13, 0, 14, 0, 'Wednesday', 5, 4, 2017, 'jd666', '0001', 'fit4purpose')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKING_TYPE VALUES('fit4purpose', 'CROSSFIT', 120)";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKING_TYPE VALUES('fit4purpose', 'CARDIO', 120)";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKING_TYPE VALUES('fit4purpose', 'WEIGHTS', 60)";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKING_TYPE VALUES('fit4purpose', 'SPIN', 60)";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKING_TYPE VALUES('fit4purpose', 'GENERAL', 60)";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO ADMIN VALUES('admin', 'admin')";
			stmt.executeUpdate(sql);

			session.addLog("Database set to default values");
			return true;
			
		}catch (SQLException e)
		{
			session.addLog("Could not set database values to default");
			
			return false;
		}
	}
	
	/**
	 * Function to read data from customer table into customer array.
	 * @param session database session object
	 * @param businesses business to access customers
	 * @return true if successful
	 */
	public boolean readCustDB(Session session, ArrayList<Business> businesses)
	{
		ResultSet resultSet = null;
		Customer newCust;
		
		try {
			resultSet = connection.createStatement().executeQuery("SELECT * FROM CUSTOMERS");
		/**
		 * Read one row at a time adding customers to array. Loop until no rows left.
		 */
		while(resultSet.next())		
		{			
			String username = resultSet.getString("CUST_UNAME");
			String fName = resultSet.getString("CUST_FNAME");
			String lName = resultSet.getString("CUST_LNAME");
			String address = resultSet.getString("CUST_ADDRESS");
			String phone = resultSet.getString("CUST_PHONE");
			String password = resultSet.getString("CUST_PASSWORD");
			String busUname = resultSet.getString("BUS_UNAME");

			newCust = new Customer(fName, lName, address, phone, username, password);
			for(int busPos = 0; busPos < businesses.size(); busPos++)
			{
				if(businesses.get(busPos).getUsername().equals(busUname))
				{
					businesses.get(busPos).getCustomers().add(newCust);
				}
			}
		}
		return true;
		
		}catch (SQLException e) {
			session.addLog("Unable to load Customer Database");
			return false;
		}
	}
	
	/**
	 * Function to read data from business table into business array.
	 * @param session database session object.
	 * @param businesses business to read values into.
	 * @return true if successful
	 */
	public boolean readBusDB(Session session, ArrayList<Business> businesses)
	{
		ResultSet resultSet = null;
		Business newBus;
		
		/**
		 * Read one row from business table create new business object. Loop until table has no new rows.
		 */
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
			session.addLog("Unable to load Business Database");
			return false;
		}
	}
	
	/**
	 * Function to read data from employee table into employee array.
	 * @param session database session object.
	 * @param businesses business to get employee from.
	 * @return true if successful
	 */
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
						businesses.get(i).getEmployees().add(newEmpl);
					}
				}
			}
			return true;
		}catch (SQLException e)
		{
			session.addLog("Unable to load employees");
			return false;
		}
	}
	
	/**
	 * Function to read data from EMP_AVAIL table into correct employee availabilities array.
	 * @param session database session object.
	 * @param businesses business to access employees through.
	 * @return true if successful
	 */
	public boolean readAvailablityTimes(Session session, ArrayList<Business> businesses)
	{
		ResultSet resultSet = null;
		
		/**
		 * Read availability from table. Match employee ID in employee array to ID from EMP_AVAIL
		 * and add availability to employees array.
		 */
		try{
			resultSet = connection.createStatement().executeQuery("SELECT * FROM EMP_AVAIL");
			while(resultSet.next())
			{
				String employeeID = resultSet.getString("EMP_ID");
				String businessUsername = resultSet.getString("BUS_UNAME");
				int startHour = resultSet.getInt("START_TIME_HOUR");
				int startMin = resultSet.getInt("START_TIME_MIN");
				int endHour = resultSet.getInt("END_TIME_HOUR");
				int endMin = resultSet.getInt("END_TIME_MIN");
				String day = resultSet.getString("DAY");
				
				LocalTime startTime = LocalTime.of(startHour, startMin);
				LocalTime endTime = LocalTime.of(endHour, endMin);
				
				for(int busNo = 0; busNo < businesses.size(); busNo++)
				{
					if(businesses.get(busNo).getUsername().equalsIgnoreCase(businessUsername))
					{
						for(int i = 0; i < businesses.get(busNo).getEmployees().size(); i++)
						{
							if(businesses.get(busNo).getEmployees().get(i).getEmployeeID().equals(employeeID))
							{
								businesses.get(busNo).getEmployees().get(i).setAvailableTime(startTime, endTime, day);
								break;
							}
						}
					}
				}
			}
			return true;
				
		}catch (SQLException e)
		{
			session.addLog("Unable to load employee available times");
			return false;
		}
	}
	
	/**
	 * Function to read business hours from business table into business array.
	 * @param session database session object.
	 * @param businesses businesses to set business hours for.
	 * @return true if successful
	 */
	public boolean readBusinessHoursDB(Session session, ArrayList<Business> businesses)
	{
		ResultSet resultSet = null;
		
		try{
			resultSet = connection.createStatement().executeQuery("SELECT * FROM BUSINESS_HOURS");
			while(resultSet.next())
			{
				String businessUsername = resultSet.getString("BUS_UNAME");
				int startHour = resultSet.getInt("START_TIME_HOUR");
				int startMin = resultSet.getInt("START_TIME_MIN");
				int endHour = resultSet.getInt("END_TIME_HOUR");
				int endMin = resultSet.getInt("END_TIME_MIN");
				String day = resultSet.getString("DAY");
				
				LocalTime startTime = LocalTime.of(startHour, startMin);
				LocalTime endTime = LocalTime.of(endHour, endMin);
				
				for(int busPos = 0; busPos < businesses.size(); busPos++)
				{
					if(businesses.get(busPos).getUsername().equals(businessUsername))
					{
						businesses.get(busPos).addBusinessHours(startTime, endTime, day);
					}
				}
			}
			return true;
		}catch (SQLException e)
		{
			session.addLog("Unable to load Business Hours");
			return false;
		}
	}
	
	/**
	 * Function to read booking types from database into business array.
	 * @param session database object.
	 * @param businesses business array to access business through.
	 * @return true if successful
	 */
	public boolean readBookingTypesDB(Session session, ArrayList<Business> businesses)
	{
		ResultSet resultSet = null;
		
		try{
			resultSet = connection.createStatement().executeQuery("SELECT * FROM BOOKING_TYPE");
			while(resultSet.next())
			{
				String businessUsername = resultSet.getString("BUS_UNAME");
				String bookingType = resultSet.getString("BOOKING_TYPE");
				int bookingLength = resultSet.getInt("BOOKING_LENGTH");
							
				for(int busPos = 0; busPos < businesses.size(); busPos++)
				{
					if(businesses.get(busPos).getUsername().equals(businessUsername))
					{
						businesses.get(busPos).addBookingType(bookingType, bookingLength);
					}
				}
			}
			return true;
		}catch (SQLException e)
		{
			session.addLog("Unable to load Business Hours");
			return false;
		}
	}
	
	/**
	 * Function to read booking values from database to bookings array.
	 * @param session database session object.
	 * @param businesses business array to access bookings array.
	 * @return true if successful
	 */
	public boolean readBookingsDB(Session session, ArrayList<Business> businesses)
	{
		ResultSet resultSet = null;
		Booking newBooking;

		try{
			resultSet = connection.createStatement().executeQuery("SELECT * FROM BOOKINGS");
			while(resultSet.next())
			{				
				String bookingID = resultSet.getString("BOOKING_ID");
				String sessionType = resultSet.getString("SESSIONTYPE");
				String day = resultSet.getString("DAY");
				int startHour = resultSet.getInt("START_TIME_HOUR");
				int startMin = resultSet.getInt("START_TIME_MIN");
				int endHour = resultSet.getInt("END_TIME_HOUR");
				int endMin = resultSet.getInt("END_TIME_MIN");
				int date = resultSet.getInt("DATE");
				int month = resultSet.getInt("MONTH");
				int year = resultSet.getInt("YEAR");
				String custUname = resultSet.getString("CUST_UNAME");
				String employeeID = resultSet.getString("EMP_ID");
				String busUname = resultSet.getString("BUS_UNAME");
				
				int custPos = 0, employeePos = 0, businessPos = 0;
				
			
				for(int busNo = 0; busNo < businesses.size(); busNo++)
				{
					if(businesses.get(busNo).getUsername().equals(busUname))
					{
						for(int i = 0; i < businesses.get(busNo).getEmployees().size(); i++)
						{
							if(businesses.get(busNo).getEmployees().get(i).getEmployeeID().equals(employeeID))
							{
								employeePos = i;
								businessPos = busNo;
								break;
							}
						}
					}
					for(int i = 0; i < businesses.get(busNo).getCustomers().size(); i++)
					{
						if(businesses.get(busNo).getCustomers().get(i).getUsername().equals(custUname))
						{
							custPos = i;
						}
					}
				}
				Business business = businesses.get(businessPos);
				LocalDate bookingDate = LocalDate.of(year, month, date);
				LocalTime startTime = LocalTime.of(startHour, startMin);
				LocalTime endTime = LocalTime.of(endHour, endMin);
				newBooking = new Booking(bookingID, sessionType, day, startTime, endTime, bookingDate, business.getCustomers().get(custPos), business.getEmployees().get(employeePos));
				businesses.get(0).getBookings().add(newBooking);
			}
			return true;
		}catch (SQLException e)
		{
			session.addLog("Unable to load bookings");
			return false;
		}
	}

	/**
	 * Function to write whole customer array to database.
	 * @param session database session object
	 * @param businesses businesses array to access customers through.
	 */
 	public void writeCustDB(Session session, ArrayList<Business> businesses)
	{	
 		for(int busPos = 0; busPos < businesses.size(); busPos++)
 		{
			for(int i = 0; i < businesses.get(busPos).getCustomers().size(); i++)
			{
				try{
					custToString(businesses.get(busPos).getCustomers().get(i), businesses.get(busPos));
					String sql = "INSERT INTO CUSTOMERS VALUES(" + getCustSQL() +")";
				    Statement stmt = connection.createStatement();
				    stmt.executeUpdate(sql);
				    
				}catch (SQLException ex) {
					session.addLog("Customer record already exists. No changes were made.");
				}
			}
 		}
	}
 	
 	/**
 	 * Function to write businesses to database.
 	 * @param session database session object.
 	 * @param businesses array of businesses to write.
 	 */
 	public void writeBusinessDB(Session session, ArrayList<Business> businesses)
	{		
		for(int i = 0; i < businesses.size(); i++)
		{
			try{
				businessToString(businesses.get(i));
				String sql = "INSERT INTO BUSINESSES VALUES(" + getBusiSQL() +")";
			    Statement stmt = connection.createStatement();
			    stmt.executeUpdate(sql);
			    
			}catch (SQLException ex) {
				session.addLog("Business record already exists. No changes were made.");
			}
		}
	}
		
	/**
 	 * Function to write employees to database.
 	 * @param session database session object.
 	 * @param businesses array of businesses to access employees through.
 	 * @return true if successful
 	 */
	public boolean writeEmplToDB(Session session, ArrayList<Business> businesses)
	{
		for(int busNo = 0; busNo < businesses.size(); busNo++)
		{
			for(int empPos = 0; empPos < businesses.get(busNo).getEmployees().size(); empPos++)
			{
				try{
					String sql;
					Statement stmt = connection.createStatement();
					
					for(int timePos = 0; timePos < businesses.get(busNo).getEmployees().get(empPos).availableTimes.size(); timePos++)
					{
						try{
							emplAvailToString(businesses.get(busNo), businesses.get(busNo).getEmployees().get(empPos),
											businesses.get(busNo).getEmployees().get(empPos).availableTimes.get(timePos));
							sql = "INSERT INTO EMP_AVAIL VALUES(" + getEmplAvailSQL() +")";
							stmt.executeUpdate(sql);
						}catch (SQLException ex) {
							session.addLog("Employee already at that time. No changes were made.");
						}
				    }
				    emplToString(businesses.get(busNo), businesses.get(busNo).getEmployees().get(empPos));
					sql = "INSERT INTO EMPLOYEES VALUES(" + getEmplSQL() +")";
				    stmt.executeUpdate(sql);
				    
				}catch (SQLException ex) {
					session.addLog("Employee record already exists. No changes were made.");
				}
			}
		}
		return true;
	}
	
	/**
	 * Function to write bookings array into database.
	 * @param session database session object.
	 * @param businesses array of businesses to access bookings through.
	 * @return true if successful
	 */
	public boolean writeBookingToDB(Session session, ArrayList<Business> businesses)
	{
		try{
			String sql;
			Statement stmt = connection.createStatement();
	
			sql = "DELETE FROM BOOKINGS";
			stmt.executeUpdate(sql);
					
			for(int busNo = 0; busNo < businesses.size(); busNo++)
			{
				for(int i = 0; i < businesses.get(busNo).getBookings().size(); i++)
				{
				    bookingToString(businesses.get(busNo).getBookings().get(i), businesses.get(busNo));
					sql = "INSERT INTO BOOKINGS VALUES(" + getBookingSQL() +")";
					stmt.executeUpdate(sql);
				}
			}
			}catch (SQLException ex) {
				session.addLog("Booking record already exists. No changes were made.");
			}
		return true;
	}
	
	/**
	 * Function to write booking types to database.
	 * @param session database session object.
	 * @param businesses array of businesses to access booking types through.
	 * @return true if successful
	 */
	public boolean writeBookingTypeToDB(Session session, ArrayList<Business> businesses)
	{
		try{
			String sql;
			Statement stmt = connection.createStatement();
					
			for(int busNo = 0; busNo < businesses.size(); busNo++)
			{
				
				for(int i = 0; i < businesses.get(busNo).getBookingTypes().size(); i++)
				{
					try{
					    bookingTypesToString(businesses.get(busNo).getUsername(), businesses.get(busNo).getBookingTypes().get(i).getBookingType(), 
					    		businesses.get(busNo).getBookingTypes().get(i).getBookingLength());
						sql = "INSERT INTO BOOKING_TYPE VALUES(" + getBookingTypeSQL() +")";
						stmt.executeUpdate(sql);
					}catch (SQLException ex) {
						session.addLog("Booking type record already exists. No changes were made.");
					}
				}
			}
			}catch (SQLException ex) {
				session.addLog("Statement cannot connect");
			}
		return true;
	}
	
	/**
	 * Function to write business hours to database.
	 * @param session database session object.
	 * @param businesses array of businesses to access business hours through.
	 * @return true if successful
	 */
	public boolean writeBusinessHoursToDB(Session session, ArrayList<Business> businesses)
	{
		try{
			String sql;
			Statement stmt = connection.createStatement();
	
			sql = "DELETE FROM BUSINESS_HOURS";
			stmt.executeUpdate(sql);
					
			for(int busNo = 0; busNo < businesses.size(); busNo++)
			{
				for(int i = 0; i < businesses.get(busNo).getBusinessHours().size(); i++)
				{
				    businessHoursToString(businesses.get(busNo), businesses.get(busNo).getBusinessHours().get(i));
					sql = "INSERT INTO BUSINESS_HOURS VALUES(" + getBusinessHourSQL() +")";
					stmt.executeUpdate(sql);
				}
			}
			}catch (SQLException ex) {
				session.addLog("Business Hours record already exists. No changes were made.");
			}
		return true;
	}
	
	/**
	 * Function to convert objects into strings.
	 * @param customer customer to write to string.
	 * @param business business to write to string.
	 * @return true if false.
	 */
	public boolean custToString(Customer customer, Business business)
	{
		String uName = customer.getUsername();
		String fName = customer.getFirstName();
		String lName = customer.getLastName();
		String address = customer.getAddress();
		String phone = customer.getContactNumber();
		String password = customer.getPassword();
		String businessName = business.getUsername();
		
		this.custSQL = "'"+ uName + "', '" + fName + "', '" + lName + "', '" + address + "', '" + phone + "', '" + password + "', '" + businessName + "'";
		return true;
	}
	
	/**
	 * Function to convert objects to strings for SQL commands.
	 * @param business business to write to string.
	 * @return true if successful
	 */
		public boolean businessToString(Business business)
		{
			String businessName = business.getBusinessName();
			String uName = business.getUsername();
			String fName = business.getFirstName();
			String lName = business.getLastName();
			String address = business.getAddress();
			String phone = business.getContactNumber();
			String password = business.getPassword();
			
			this.busiSQL = "'"  + uName + "', '" + businessName + "', '" + fName + "', '" + lName + "', '" + address + "', '" + phone + "', '" + password + "'";
			return true;
		}
	
		/**
		 * Function to write employee to string.
		 * @param business business to access employee through.
		 * @param employee employee to write to string.
		 * @return true if successful
		 */
	public boolean emplToString(Business business, Employee employee)
	{
		String empID = employee.getEmployeeID();
		String fName = employee.getFirstName();
		String lName = employee.getLastName();
		
		this.emplSQL = "'" + empID + "', '" + fName + "', '" + lName + "', '" + business.getUsername() + "'"; 
		return true;
	}

	/**
	 * Function to write employee availabilities to string.
	 * @param business business to access employee availabilities through.
	 * @param employee employee to access availabilities through.
	 * @param availTime availTime to write to string.
	 * @return true if successful
	 */
	private boolean emplAvailToString(Business business, Employee employee, AvailableTime availTime)
	{
		String day = availTime.getDay();
		int startHour = availTime.getStartTime().getHour();
		int startMin = availTime.getStartTime().getMinute();
		int endHour = availTime.getEndTime().getHour();
		int endMin = availTime.getEndTime().getMinute();
		
		this.emplAvailSQL = "'" + employee.getEmployeeID() + "', '" + business.getUsername() + "', "+ startHour + ", " + startMin 
				+ ", " + endHour + ", " + endMin + ", '" + day + "'";
		return true;
	}
	
	/**
	 * Function to write business hours to string.
	 * @param business business to access hours through.
	 * @param businessHour business hour to write to string.
	 * @return true if successful
	 */
	private boolean businessHoursToString(Business business, AvailableTime businessHour)
	{
		String businessUname = business.getUsername();
		String day = businessHour.getDay();
		int startHour = businessHour.getStartTime().getHour();
		int startMin = businessHour.getStartTime().getMinute();
		int endHour = businessHour.getEndTime().getHour();
		int endMin = businessHour.getEndTime().getMinute();
		
		this.businessHourSQL = "'" + businessUname + "', " + startHour + ", " + startMin + ", " + endHour + ", " + endMin + ", '" + day + "'";
		return true;
	}
	
	/**
	 * Function to write booking types to string.
	 * @param name name of booking type.
	 * @param type type of booking type.
	 * @param time time of booking type.
	 * @return true if successful
	 */
	private boolean bookingTypesToString(String name, String type, int time)
	{
		this.bookingTypeSQL = "'" + name + "', '" + type + "', " + time;
		return true;
	}
	
	/**
	 * Function to write bookings to string.
	 * @param booking booking to write.
	 * @param business business to access bookings through.
	 * @return true if successful
	 */
	private boolean bookingToString(Booking booking, Business business)
	{
		String bookingId = booking.getBookingID();
		String sessionType = booking.getSessionType();
		String day = booking.getBookingTime().getDay();
		int startHour = booking.getBookingTime().getStartTime().getHour();
		int startMin = booking.getBookingTime().getStartTime().getMinute();
		int endHour = booking.getBookingTime().getEndTime().getHour();		
		int endMin = booking.getBookingTime().getEndTime().getMinute();
		LocalDate bookingDate = booking.getDate();
		int date = bookingDate.getDayOfMonth();
		int month = bookingDate.getMonthValue();
		int year = bookingDate.getYear();
		String custUname = booking.getCustUsername();
		String employeeID = booking.getEmployeeID();
		this.bookingSQL = "'"+ bookingId + "', '" + sessionType + "', " + startHour + ", " + startMin + ", " + endHour + ", " + endMin + ", '" + day + "', "
					+ date + ", " + month + ", " + year + ", '" + custUname + "', '" + employeeID + "', '" + business.getUsername() + "'";
		return true;				
	}
	
	/**
	 * Function to close connection at program termination.
	 * @param session session to close.
	 * @return true if successful
	 */
	public boolean closeConnection(Session session)
	{
		try{
			connection.close();
			session.addLog("Connection to database closed.");
			return true;
		}catch (SQLException e)
		{
			session.addLog("Cannot close connection to database.");
			return false;
		}
	}
	
	/**
	 * Function to delete all records
	 * @param session database session object.
	 * @param table table to clear.
	 * @return true if successful
	 */
	public boolean deleteAllRecords(Session session, String table)
	{
		try{
			Statement stmt = connection.createStatement();
			
			String sql = "DELETE FROM " + table;
			stmt.executeUpdate(sql);

		}catch (SQLException e)
		{
			session.addLog("Unable to clear records from " + table + " table.");
		}
		return true;
	}
}

