package users;

/**
 * Employee --- A class for storing data about the employees of a business.
 * @author SEPT-Team-6
 */
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
	
	public int availableTimes[][] = new int[5][10];
	
	/**
	 * Constructor to make Employee object.
	 * @param employeeID Unique ID of the employee.
	 * @param firstName First name of the employee.
	 * @param lastName Last name of the employee.
	 */
	public Employee(String employeeID, String firstName, String lastName) 
	{
		setEmployeeID(employeeID);
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	/**
	 * Mutator method for Employee ID.
	 * @param employeeID New ID of the employee, to be set.
	 */
	public void setEmployeeID(String employeeID)
	{
		this.employeeID = employeeID;
	}
	
	/**
	 * Mutator method for first name.
	 * @param firstName First name of the employee, to be set.
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * Mutator method for last name.
	 * @param lastName Last name of the employee, to be set.
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	/**
	 * Sets the availability of each day for an employee.
	 * @param monday True if they can work, false if they can't.
	 * @param tuesday True if they can work, false if they can't.
	 * @param wednesday True if they can work, false if they can't.
	 * @param thursday True if they can work, false if they can't.
	 * @param friday True if they can work, false if they can't.
	 * @param saturday True if they can work, false if they can't.
	 * @param sunday True if they can work, false if they can't.
	 */
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

	/**
	 * Accessor method for employee ID.
	 * @return employeeID The ID of the employee.
	 */
 	public String getEmployeeID()
	{
		return employeeID;
	}
 	
 	/**
 	 * Accessor method for first name.
 	 * @return firstName First name of the employee.
 	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Accessor method for last name.
	 * @return lastName Last name of the employee.
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Accessor method for full name of the employee.
	 * @return Concatenation of firstName and lastName.
	 */
	public String getName()
	{
		return firstName + " " + lastName;
	}
	
	/**
	 * Sets a given timeslot to booked or not based on the booked string.
	 * @param day The day of the timeslot.
	 * @param timeslot The timeslot to be set.
	 * @param booked Whether to make that timeslot booked or not.
	 * @return
	 */
	public boolean setAvailableTime(int day, int timeslot, String booked)
	{
		if(booked.equalsIgnoreCase("yes"))
		{
			availableTimes[day][timeslot] = 2;
		}
		else if(booked.equalsIgnoreCase("no"))
		{
			availableTimes[day][timeslot] = 1;
		}
		return true;
	}
	
	/**
	 * Sets a timeslot to be unavailable.
	 * @param day The day of the timeslot.
	 * @param timeslot The timeslot to be made unavailable.
	 * @return
	 */
	public boolean removeAvailableTime(int day, int timeslot)
	{
		availableTimes[day][timeslot] = 0;
		return true;
	}
	
	/**
	 * Remove booking from employee available times array.
	 * @param timeslot Timeslot of booking to be removed.
	 * @param day Day of timeslot.
	 * @return
	 */
	public boolean removeBooking(int timeslot, int day)
	{
		availableTimes[day][timeslot] = 1;
		return true;
	}
	
	/**
	 * Check if a given timeslot is available, not available or booked.
	 * @param timeslot Timeslot to check.
	 * @param day Day of timeslot.
	 * @return 0 for not available, 1 for available, 2 for booked.
	 */
	public int getAvailableTime(int timeslot, int day)
	{
		return availableTimes[day][timeslot];
	}
	
	/**
	 * Returns a timeslot as a string value.
	 * @param timeslot Timeslot to be converted to string.
	 * @return Timeslot as string.
	 */
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
