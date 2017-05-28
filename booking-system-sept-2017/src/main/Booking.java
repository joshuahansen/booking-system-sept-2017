package main;

import java.time.LocalDate;
import java.time.LocalTime;

//import java.time.LocalTime;
import users.Customer;
import users.Employee;

public class Booking {
	
	/**
	 * Booking variables.
	 */
	private String bookingID;
//	private Boolean completed;
	private String sessionType;
	private LocalDate date;
//	private LocalTime time;
	private Customer customer;
	private Employee employee;
	
	private AvailableTime bookingTime;
	private BookingType bookingType;
//	private int day;
//	private int timeslot;
//	private int endTime;

//	public Booking(String bookingID, String sessionType, int day, int timeslot, LocalDate date, Boolean completed, Customer customer, Employee employee) 
	/**
	 * Constructor for booking object.
	 * @param bookingID
	 * @param sessionType
	 * @param day
	 * @param startTime
	 * @param endTime
	 * @param date
	 * @param customer
	 * @param employee
	 */
	public Booking(String bookingID, String sessionType, String day, LocalTime startTime, LocalTime endTime, LocalDate date, Customer customer, Employee employee)
	{
		setBookingID(bookingID);
		setSessionType(sessionType);
		setBookingTime(startTime, endTime, day);
		setDate(date);
		setCustomer(customer);
		setEmployee(employee);
//		if(sessionType.equalsIgnoreCase("CROSSFIT 2HR") || sessionType.equalsIgnoreCase("CARDIO 2HR"))
//		{
//			endTime = timeslot + 1;
//		}
//		else
//		{
//			endTime = timeslot;
//		}
	}
	
	/**
	 * Mutator for booking ID.
	 * @param bookingID
	 */
	private void setBookingID(String bookingID)
	{
		this.bookingID = bookingID;
	}
	
	/**
	 * Mutator for session type.
	 * @param sessionType
	 */
	private void setSessionType(String sessionType)
	{
		this.sessionType = sessionType;
	}
	
	/**
	 * Mutator for booking time.
	 * @param startTime
	 * @param endTime
	 * @param day
	 */
	private void setBookingTime(LocalTime startTime, LocalTime endTime, String day)
	{
		this.bookingTime = new AvailableTime(startTime, endTime, day);
	}
	
	/**
	 * Mutator for date.
	 * @param date
	 */
	private void setDate(LocalDate date)
	{
		this.date = date;
	}
	
//	private void setCompleted(Boolean completed)
//	{
//		this.completed = completed;
//	}
//	
//	private void setTimeslot(int timeslot)
//	{
//		this.timeslot = timeslot;
//	}
	
	/**
	 * Mutator for customer.
	 * @param customer
	 */
	private void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	/**
	 * Mutator for employee.
	 * @param employee
	 */
	private void setEmployee(Employee employee)
	{
		this.employee = employee;
	}
	
	/**
	 * Accessor for date.
	 * @return
	 */
	public LocalDate getDate()
	{
		return this.date;
	}
	
	/**
	 * Accessor for booking ID.
	 * @return
	 */
	public String getBookingID()
	{
		return bookingID;
	}
	
	/**
	 * Accessor for session type.
	 * @return
	 */
	public String getSessionType()
	{
		return this.sessionType;
	}
	
	/**
	 * Accessor for booking time.
	 * @return
	 */
	public AvailableTime getBookingTime()
	{
		return this.bookingTime;
	}
	
	/**
	 * Accessor for booking type.
	 * @return
	 */
	public BookingType getBookingType()
	{
		return this.bookingType;
	}
	
	/**
	 * Accessor for customer name.
	 * @return
	 */
	public String getCustomerName()
	{
		String name = customer.getFirstName() + " " + customer.getLastName(); 
		
		return name;
	}
	
	/**
	 * Accessor for employee name.
	 * @return
	 */
	public String getEmployeeName()
	{
		String name = employee.getFirstName() + " " + employee.getLastName();
		
		return name;
	}
	
	/**
	 * Accessor for employee ID.
	 * @return
	 */
	public String getEmployeeID()
	{
		String ID = employee.getEmployeeID();
		return ID;
	}
	
	/**
	 * Accessor for customer username.
	 * @return
	 */
	public String getCustUsername()
	{
		return customer.getUsername();
	}
		
//	public String getTimeslotAsString()
//	{
//		String stringTimeslot = bookingTime.getStartTime().toString() + "-" + bookingTime.getEndTime().toString();
//		
//		return stringTimeslot;
//	}
	
	/**
	 * To string method.
	 */
	public String toString()
	{
		return bookingID + "\t" + date + "\t" + getCustomerName() + "\t    " + bookingTime.getDay() + "\t" + bookingTime.getTimeslotAsString() + "\t" + getEmployeeName();
	}
}
