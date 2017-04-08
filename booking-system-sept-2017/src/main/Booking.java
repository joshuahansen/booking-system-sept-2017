package main;

import java.util.Date;

public class Booking {
	
	private String bookingID;
	private Boolean completed;
	private int day;
	private int timeslot;
	private Date date;

	public Booking(String bookingID, int day, int timeslot, Date date, Boolean completed) 
	{
		setBookingID(bookingID);
		setDay(day);
		setTimeslot(timeslot);
		setDate(date);
		setCompleted(completed);
	}
	
	private void setBookingID(String bookingID)
	{
		this.bookingID = bookingID;
	}
	
	private void setDay(int day)
	{
		this.day = day;
	}
	
	private void setDate(Date date)
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
	
	public Date getDate()
	{
		return this.date;
	}
	
	public String getBookingID()
	{
		return bookingID;
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
	
}
