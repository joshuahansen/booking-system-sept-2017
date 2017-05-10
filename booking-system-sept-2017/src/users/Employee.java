package users;

/**
 * Employee --- A class for storing data about the employees of a business.
 */
import java.time.LocalTime;
import java.util.ArrayList;
import main.AvailableTime;

public class Employee
{
	private String employeeID;
	private String firstName;
	private String lastName;
	
	public ArrayList<AvailableTime> availableTimes = new ArrayList<AvailableTime>();
	
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
	 * 
	 * @param startTime
	 * @param endTime
	 * @param day
	 * @return
	 */
	public boolean setAvailableTime(LocalTime startTime, LocalTime endTime, String day)
	{
		AvailableTime newAvailTime = new AvailableTime(startTime, endTime, day);
		for(int i = 0; i < availableTimes.size(); i++)
		{
			if(availableTimes.get(i).equals(newAvailTime))
			{
				return false;
			}
		}
		availableTimes.add(newAvailTime);
		return true;
	}
	
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param day
	 * @return
	 */
	public boolean removeAvailableTime(LocalTime startTime, LocalTime endTime, String day)
	{
		for(int i = 0; i < availableTimes.size(); i++)
		{
			if(availableTimes.get(i).getStartTime().equals(startTime) && availableTimes.get(i).getEndTime().equals(endTime)
					&& availableTimes.get(i).getDay().equalsIgnoreCase(day))
			{
				availableTimes.remove(i);
			}
		}
		return true;
	}
}
