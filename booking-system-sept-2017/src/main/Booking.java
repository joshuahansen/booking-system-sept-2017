package main;

import java.time.LocalDate;
import java.time.LocalTime;
import users.Customer;
import users.Employee;
import users.Business;

public class Booking {
	
	private String bookingID;
	private Boolean completed;
	private String sessionType;
	private LocalDate date;
	private LocalTime time;
	private Customer customer;
	private Employee employee;
	
	private int day;
	private int timeslot;

	public Booking(String bookingID, String sessionType, int day, int timeslot, LocalDate date, Boolean completed, Customer customer, Employee employee) 
	{
		setBookingID(bookingID);
		setSessionType(sessionType);
		setDay(day);
		setTimeslot(timeslot);
		setDate(date);
		setCompleted(completed);
		setCustomer(customer);
		setEmployee(employee);
	}
	
	private void setBookingID(String bookingID)
	{
		this.bookingID = bookingID;
	}
	
	private void setSessionType(String sessionType)
	{
		this.sessionType = sessionType;
	}
	
	private void setDay(int day)
	{
		this.day = day;
	}
	
	private void setDate(LocalDate date)
	{
		this.date = date;
	}
	
	private void setCompleted(Boolean completed)
	{
		this.completed = completed;
	}
	
	private void setTimeslot(int timeslot)
	{
		this.timeslot = timeslot;
	}
	
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
	
	public int getDay()
	{
		return day;
	}
	
	public boolean getCompleted()
	{
		return this.completed;
	}
	
	public int getTimeslot()
	{
		return timeslot;
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
	
	public String getCustUsername()
	{
		return customer.getUsername();
	}
	
 	public String getDayAsString()
	{
		String stringDay;
		if(day == 0)
		{
			stringDay = "Monday";
		}
		else if( day == 1)
		{
			stringDay = "Tuesday";
		}
		else if(day == 2)
		{
			stringDay = "Wednesday";
		}
		else if(day == 3)
		{
			stringDay = "Thursday";
		}
		else
		{
			stringDay = "Friday";
		}
		return stringDay;
	}
	
	public String getTimeslotAsString()
	{
		String stringTimeslot;
		if(timeslot == 0)
		{
			stringTimeslot = "8am - 9am";
		}
		else if(timeslot == 1)
		{
			stringTimeslot = "9am - 10am";
		}
		else if(timeslot == 2)
		{
			stringTimeslot = "10am - 11am";
		}
		else if(timeslot == 3)
		{
			stringTimeslot = "11am - 12pm";
		}
		else if(timeslot == 4)
		{
			stringTimeslot = "12pm - 1pm";
		}
		else if(timeslot == 5)
		{
			stringTimeslot = "1pm - 2pm";
		}
		else if(timeslot == 6)
		{
			stringTimeslot = "2pm - 3pm";
		}
		else if(timeslot == 7)
		{
			stringTimeslot = "3pm - 4pm";
		}
		else if(timeslot == 8)
		{
			stringTimeslot = "4pm - 5pm";
		}
		else
		{
			stringTimeslot = "5pm - 6pm";
		}
		return stringTimeslot;
	}
	
	public String toString()
	{
		return bookingID + "\t" + date + "\t" + getCustomerName() + "\t    " + getDayAsString() + "\t" + getTimeslotAsString() + "\t" + getEmployeeName();
	}
}
