package users;

import java.util.ArrayList;

import main.Booking;

public class Business extends User
{
	private String businessName;
	/* Each business has an array of employees */
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	
	/*Each business has an array of bookings to keep track of past and future bookings*/
	public ArrayList<Booking> bookings = new ArrayList<Booking>();
	
	ArrayList<String> availibleTimes = new ArrayList<String>();
	
	//constructor for business
	public Business(String businessName, String firstName, String lastName, String address, 
					String contactNumber, String username, String password) 
	{
		super(firstName, lastName, address, contactNumber, username, password);
		setBusinessName(businessName);
	}
//setter for business name
	private void setBusinessName(String businessName)
	{
		this.businessName = businessName;
	}
	//getter for business name
	public String getBusinessName()
	{
		return businessName;
	}
	
	public ArrayList<Booking> getBookings()
	{
		return this.bookings;
	}
}
