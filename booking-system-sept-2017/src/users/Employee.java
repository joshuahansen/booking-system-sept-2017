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
	
	boolean availibleTimes[][] = new boolean[10][7];
	
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
	
	//sets individual available days
	public void setAvaMonday(boolean monday)
	{
		this.avaMonday = monday;
	}
	public void setAvaTuesday(boolean tuesday)
	{
		this.avaTuesday = tuesday;
	}
	public void setAvaWednesday(boolean wednesday)
	{
		this.avaWednesday = wednesday;
	}
	public void setAvaThursday(boolean thursday)
	{
		this.avaThursday = thursday;
	}
	public void setAvaFriday(boolean friday)
	{
		this.avaFriday = friday;
	}
	public void setAvaSaturday(boolean saturday)
	{
		this.avaSaturday = saturday;
	}
	public void setAvaSunday(boolean sunday)
	{
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
	
	public boolean getAvaMonday()
	{
		return avaMonday;
	}
	public boolean getAvaTuesday()
	{
		return avaTuesday;
	}
	public boolean getAvaWednesday()
	{
		return avaWednesday;
	}
	public boolean getAvaThursday()
	{
		return avaThursday;
	}
	public boolean getAvaFriday()
	{
		return avaFriday;
	}
	public boolean getAvaSaturday()
	{
		return avaSaturday;
	}
	public boolean getAvaSunday()
	{
		return avaSunday;
	}
	
	public boolean setAvailibleTime(int timeslot, int day)
	{
		if(availibleTimes[timeslot][day] != true)
		{
			availibleTimes[timeslot][day] = true;
		}
		return true;
	}
	
	public boolean removeAvailibleTime(int timeslot, int day)
	{
		if(availibleTimes[timeslot][day] != false)
		{
			availibleTimes[timeslot][day] = false;
		}
		return true;
	}
}
