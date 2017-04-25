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
	
	public int availableTimes[][] = new int[10][5];
	
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
	
	//Sets time to available given the timeslot, day and if it's booked or not
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
	
	//remove available time from employee availability array
	public boolean removeAvailibleTime(int timeslot, int day)
	{
		availableTimes[timeslot][day] = 0;
		return true;
	}
	
	//remove booking from employee availability array at given timeslot and day
	public boolean removeBooking(int timeslot, int day)
	{
		availableTimes[timeslot][day] = 1;
		return true;
	}
	
	/*return value of a given sport in the employee availability array
	 * 0 for not available
	 * 1 for available
	 * 2 for booked
	 */
	public int getAvailableTime(int timeslot, int day)
	{
		return availableTimes[timeslot][day];
	}
	
	public String getTimeSlotAsString(int timeslot)
	{
		if(timeslot == 0)
		{
			return "8am - 9am";
		}
		else if(timeslot == 1)
		{
			return "9am - 10am";
		}
		else if(timeslot == 2)
		{
			return "10am - 11am";
		}
		else if(timeslot == 3)
		{
			return "11am - 12pm";
		}
		else if(timeslot == 4)
		{
			return "12pm - 1pm";
		}
		else if(timeslot == 5)
		{
			return "1pm -2pm";
		}
		else if(timeslot == 6)
		{
			return "2pm - 3pm";
		}
		else if(timeslot == 7)
		{
			return "3pm - 4pm";
		}
		else if(timeslot == 8)
		{
			return "4pm - 5pm";
		}
		else
		{
			return "5pm - 6pm";
		}
	}
}
