package main;

import java.time.LocalTime;

public class AvailableTime {
	LocalTime startTime;
	LocalTime endTime;
	String day;
	
	public AvailableTime(LocalTime startTime, LocalTime endTime, String day)
	{
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
	}
	
	public void setStartTime(LocalTime startTime)
	{
		this.startTime = startTime;
	}
	
	public void setEndTime(LocalTime endTime)
	{
		this.endTime = endTime;
	}
	
	public void setDay(String day)
	{
		this.day = day;
	}
	
	public LocalTime getStartTime()
	{
		return this.startTime;
	}
	
	public LocalTime getEndTime()
	{
		return this.endTime;
	}
	
	public String getDay()
	{
		return this.day;
	}
}
