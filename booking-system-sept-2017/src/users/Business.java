package users;

import java.time.LocalTime;
import java.util.ArrayList;

import javafx.scene.image.Image;
import main.AvailableTime;
import main.Booking;
import main.BookingType;

/**
 * A class for storing data about the businesses using the program.
 * @author SEPT-Team-6
 */
public class Business extends User
{
	Image businessLogo = null;
	/**
	 * Name of the business object.
	 */
	private String businessName;
	
	/**
	 * Array list of employees for the business object.
	 */
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	
	/**
	 * Array list of bookings for the current business object.
	 */
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	
	private ArrayList<AvailableTime> businessHours = new ArrayList<AvailableTime>();
	
	private ArrayList<BookingType> bookingTypes = new ArrayList<BookingType>();
	
	private ArrayList<Customer> customers = new ArrayList<Customer>();
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
		/**
		 * Passes common generic user values to the user superclass constructor.
		 */
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
	public ArrayList<Employee> getEmployees() 
	{ 
		return this.employees; 
	}
	
	/**
	 * Accessor method for the booking types for this business.
	 * @return bookingTypes the booking types for this business.
	 */
	public ArrayList<BookingType> getBookingTypes()
	{
		return this.bookingTypes;
	}
	
	/**
	 * Accessor method for the business hours for this business.
	 * @return businessHours the business hours for this business.
	 */
	public ArrayList<AvailableTime> getBusinessHours()
	{
		return this.businessHours;
	}
	
	/**
	 * Accessor method for customers of this business.
	 * @return customers the customers of this business.
	 */
	public ArrayList<Customer> getCustomers()
	{
		return this.customers;
	}
	
	/**
	 * Function to add business hours to this business.
	 * @param startTime Opening time for the business.
	 * @param endTime Closing time for the business.
	 * @param day The day for the start and end times.
	 * @return true if successful.
	 */
	public boolean addBusinessHours(LocalTime startTime, LocalTime endTime, String day)
	{
		AvailableTime newAvailTime = new AvailableTime(startTime, endTime, day);
		for(int i = 0; i < businessHours.size(); i++)
		{
			if(businessHours.get(i).equals(newAvailTime))
			{
				return false;
			}
			else if(businessHours.get(i).getDay().equalsIgnoreCase(newAvailTime.getDay()))
			{
				businessHours.get(i).setStartTime(newAvailTime.getStartTime());
				businessHours.get(i).setEndTime(newAvailTime.getEndTime());
				return true;
			}
		}
		businessHours.add(newAvailTime);
		return true;
	}
	
	/**
	 * Function to add a type of booking to this business.
	 * @param bookingType type of the booking to be added.
	 * @param bookingLength length of the booking type to be added.
	 * @return true if successful.
	 */
	public boolean addBookingType(String bookingType, int bookingLength)
	{
		BookingType newBookingType = new BookingType(bookingType, bookingLength);
		bookingTypes.add(newBookingType);
		return true;
	}
  
	/**
	 * Mutator method to set the business logo image.
	 * @param URL logo to be added.
	 */
	public void setBusinessLogo(String URL)
	{
		this.businessLogo = new Image(URL);
	}
	/**
	 * Accessor method to get the business logo for the business.
	 * @return business logo to be returned.
	 */
	public Image getBusinessLogo()
	{
		return this.businessLogo;
	}
}