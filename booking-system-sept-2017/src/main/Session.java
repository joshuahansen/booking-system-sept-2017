package main;

//import main.*;
//import users.*;

import java.io.*;
import java.time.*;
import java.util.ArrayList;

public class Session 
{
	ArrayList<String> sessionLogs;
	String sessionID;
	File sessionLog;
	FileWriter sessionLogger;
	
//	Login login;
//	Registration registration;
	
	public Session() throws IOException
	{
		this.sessionLogs = new ArrayList<>();
		this.sessionID = generateSessionID();
		this.sessionLog = new File(sessionID);
		this.sessionLogger = new FileWriter(sessionLog);
	}
	
	public String generateSessionID()
	{
		String sessionID = new String();
		
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		
		String dateString = date.toString();
		String timeString = time.toString();
		
		sessionID = dateString + "-" + timeString;
		
		return sessionID;
	}
	
	public void addLog(String log)
	{
		this.sessionLogs.add(log);
	}
	
	public void writeToFile(String log) throws IOException
	{
		this.sessionLogger.append(log);
	}
	
	public void writeWholeLog() throws IOException
	{
		String currentLog = new String();
		
		int numberOfLogs = this.sessionLogs.size();
		int counter = 0;
		
		for (counter = 0; counter < numberOfLogs; counter++)
		{
			if (!this.sessionLogs.get(counter).equals(null))
			{
				currentLog = this.sessionLogs.get(counter);
				
				this.sessionLogger.append(currentLog);
			}
			else
			{
				continue;
			}
		}
	}
}