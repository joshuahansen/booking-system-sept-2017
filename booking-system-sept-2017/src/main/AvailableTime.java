package main;

import java.time.LocalTime;

public class AvailableTime {
	LocalTime startTime;
	LocalTime endTime;
	String day;
	
	/**
	 * Constructor for available time object.
	 * @param startTime start time for the available time.
	 * @param endTime end time for the available time.
	 * @param day day of available time.
	 */
	public AvailableTime(LocalTime startTime, LocalTime endTime, String day)
	{
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
	}
	
	/**
	 * Mutator method for start time.
	 * @param startTime to be set.
	 */
	public void setStartTime(LocalTime startTime)
	{
		this.startTime = startTime;
	}
	
	/**
	 * Mutator method for 
	 * @param endTime end time to be set.
	 */
	public void setEndTime(LocalTime endTime)
	{
		this.endTime = endTime;
	}
	
	/**
	 * Mutator method for day.
	 * @param day day to be set.
	 */
	public void setDay(String day)
	{
		this.day = day;
	}
	
	/**
	 * Accessor method for start time.
	 * @return start time
	 */
	public LocalTime getStartTime()
	{
		return this.startTime;
	}
	
	/**
	 * Accessor method for end time.
	 * @return end time
	 */
	public LocalTime getEndTime()
	{
		return this.endTime;
	}
	
	/**
	 * Accessor method for day.
	 * @return day
	 */
	public String getDay()
	{
		return this.day;
	}
	
	/**
	 * Accessor method for timeslot as string.
	 * @return timeslot as string.
	 */
	public String getTimeslotAsString()
	{
		String stringTimeslot = getStartTime().toString() + "-" + getEndTime().toString();
		
		return stringTimeslot;
	}
}
