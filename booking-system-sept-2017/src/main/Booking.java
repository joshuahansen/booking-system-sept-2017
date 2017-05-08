package main;

import java.time.LocalDate;
import java.time.LocalTime;

//import java.time.LocalTime;
import users.Customer;
import users.Employee;

public class Booking {
	
	private String bookingID;
//	private Boolean completed;
	private String sessionType;
	private LocalDate date;
//	private LocalTime time;
	private Customer customer;
	private Employee employee;
	
	private LocalTime startTime;
	private LocalTime endTime;
	private String day;
//	private int day;
//	private int timeslot;
//	private int endTime;

//	public Booking(String bookingID, String sessionType, int day, int timeslot, LocalDate date, Boolean completed, Customer customer, Employee employee) 
	public Booking(String bookingID, String sessionType, String day, LocalTime startTime, LocalTime endTime, LocalDate date, Customer customer, Employee employee)
	{
		setBookingID(bookingID);
		setSessionType(sessionType);
		setDay(day);
		setStartTime(startTime);
		setEndTime(endTime);
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
	
	private void setBookingID(String bookingID)
	{
		this.bookingID = bookingID;
	}
	
	private void setSessionType(String sessionType)
	{
		this.sessionType = sessionType;
	}
	
	private void setDay(String day)
	{
		this.day = day;
	}
	
	private void setStartTime(LocalTime startTime)
	{
		this.startTime = startTime;
	}
	
	private void setEndTime(LocalTime endTime)
	{
		this.endTime = endTime;
	}
	
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
//	
	private void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	private void setEmployee(Employee employee)
	{
		this.employee = employee;
	}
	
	public LocalDate getDate()
	{
		return this.date;
	}
	
	public String getBookingID()
	{
		return bookingID;
	}
	
	public String getSessionType()
	{
		return this.sessionType;
	}
	
	public String getDay()
	{
		return day;
	}
	
//	public boolean getCompleted()
//	{
//		return this.completed;
//	}
//	
//	public int getTimeslot()
//	{
//		return timeslot;
//	}
	public LocalTime getStartTime()
	{
		return this.startTime;
	}
	
	public LocalTime getEndTime()
	{
		return this.endTime;
	}
	
	public String getCustomerName()
	{
		String name = customer.getFirstName() + " " + customer.getLastName(); 
		
		return name;
	}
	
	public String getEmployeeName()
	{
		String name = employee.getFirstName() + " " + employee.getLastName();
		
		return name;
	}
	public String getEmployeeID()
	{
		String ID = employee.getEmployeeID();
		return ID;
	}
	
	public String getCustUsername()
	{
		return customer.getUsername();
	}
		
	public String getTimeslotAsString()
	{
		String stringTimeslot = getStartTime().toString() + " - " + getEndTime().toString();
		
		return stringTimeslot;
	}
	
	public String toString()
	{
		return bookingID + "\t" + date + "\t" + getCustomerName() + "\t    " + getDay() + "\t" + getTimeslotAsString() + "\t" + getEmployeeName();
	}
}
