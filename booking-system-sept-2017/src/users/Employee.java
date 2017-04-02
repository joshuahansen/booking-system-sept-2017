package users;


public class Employee
{
	private String employeeID;
	private String firstName;
	private String lastName;
	boolean avaMonday;
	boolean avaTuesday;
	boolean avaWednesday;
	boolean avaThursday;
	boolean avaFriday;
	boolean avaSaturday;
	boolean avaSunday;
	
//	ArrayList<String> availibleTimes = new ArrayList<String>();
	
	int availableTimes[][] = new int[10][5];
	//available for booking = 1
	//already booked = 2
	
	//creates a new employee with ID, and name
	public Employee(String employeeID, String firstName, String lastName) 
	{
		setEmployeeID(employeeID);
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	private void  setEmployeeID(String employeeID)
	{
		this.employeeID = employeeID;
	}
	private void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	private void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	//sets all available days for the employee 
	public void setAllAvailabily(boolean monday, boolean tuesday, boolean wednesday, boolean thursday,
								boolean friday, boolean saturday, boolean sunday)
	{
		this.avaMonday = monday;
		this.avaTuesday = tuesday;
		this.avaWednesday = wednesday;
		this.avaThursday = thursday;
		this.avaFriday = friday;
		this.avaSaturday = saturday;
		this.avaSaturday = saturday;
		this.avaSunday = sunday;
		
	}

	
	//returns each of the employee's attributes
 	public String getEmployeeID()
	{
		return employeeID;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	
	public String getName()
	{
		return firstName + " " + lastName;
	}
	
	public boolean setAvailableTime(int timeslot, int day, String booked)
	{
		if(booked.equalsIgnoreCase("yes"))
		{
			availableTimes[timeslot][day] = 2;
		}
		else if(booked.equalsIgnoreCase("no"))
		{
			availableTimes[timeslot][day] = 1;
		}
		return true;
	}
	
	public boolean removeAvailibleTime(int timeslot, int day)
	{
		availableTimes[timeslot][day] = 0;
		return true;
	}
	
	public boolean removeBooking(int timeslot, int day)
	{
		availableTimes[timeslot][day] = 1;
		return true;
	}
	
	public int getAvailableTime(int timeslot, int day)
	{
		return availableTimes[timeslot][day];
	}
}
