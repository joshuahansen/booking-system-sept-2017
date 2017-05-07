package users;

import java.util.ArrayList;

import main.Booking;

/**
 * A class for storing data about the businesses using the program.
 * @author SEPT-Team-6
 */
public class Business extends User
{
	/**
	 * Name of the business object.
	 */
	private String businessName;
	
	/**
	 * Array list of employees for the business object.
	 */
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	
	/**
	 * Array list of bookings for the current business object.
	 */
	public ArrayList<Booking> bookings = new ArrayList<Booking>();
	
	ArrayList<String> availableTimes = new ArrayList<String>();
	
	public ArrayList<String> bookingTypes = new ArrayList<String>();
	
	/**
	 * Constructor to make business object.
	 * @param businessName Name of the business.
	 * @param firstName First name of the business owner.
	 * @param lastName Last name of the business owner.
	 * @param address Home address of the business owner.
	 * @param contactNumber Phone number of the business owner.
	 * @param username Username of the business.
	 * @param password Password of the business.
	 */
	public Business(String businessName, String firstName, String lastName, String address, 
					String contactNumber, String username, String password) 
	{
		super(firstName, lastName, address, contactNumber, username, password);
		setBusinessName(businessName);
	}
	
	/**
	 * Mutator method for business name.
	 * @param businessName New name of the business, to be set.
	 */
	private void setBusinessName(String businessName)
	{
		this.businessName = businessName;
	}
	
	/**
	 * Accessor method for business name.
	 * @return businessName Returns the current name of the business object.
	 */
	public String getBusinessName()
	{
		return this.businessName;
	}
	
	/**
	 * Accessor method for the bookings array.
	 * @return bookings Returns the bookings array for the current business object.
	 */
	public ArrayList<Booking> getBookings()
	{
		return this.bookings;
	}
	
	/**
	 * Accessor method for the employees array.
	 * @return employees Returns the employees array for the current business object.
	 */
	public ArrayList<Employee> getEmployeeList() 
	{ 
		return this.employees; 
	}
}
