package main;

//import main.*;
//import users.*;

import java.io.*;
import java.time.*;
import java.util.ArrayList;

public class Session 
{
	private ArrayList<String> sessionLogs;
	private String sessionID;
	private File sessionLog;
	private FileWriter sessionLogger;
	
//	Login login;
//	Registration registration;
	
	public Session() throws IOException
	{
		this.sessionLogs = new ArrayList<>();
		this.sessionID = generateFileNameSessionID();
		this.sessionLog = new File(sessionID+".txt");
		sessionLog.createNewFile();
		this.sessionLogger = new FileWriter(sessionLog);
	}
	

	
	public ArrayList<String> getSessionLogs()
	{
		return this.sessionLogs;
	}
	
	public void setSessionLogs(ArrayList<String> sessionLogs)
	{
		this.sessionLogs = sessionLogs;
	}
	
	public String getSessionID()
	{
		return this.sessionID;
	}
	
	public void setSessionID(String sessionID)
	{
		this.sessionID = sessionID;
	}
	
	public File getSessionLog()
	{
		return this.sessionLog;
	}
	
	public void setSessionLog(File sessionLog)
	{
		this.sessionLog = sessionLog;
	}
	
	public FileWriter getSessionLogger()
	{
		return this.sessionLogger;
	}
	
	public void setSessionLogger(FileWriter sessionLogger)
	{
		this.sessionLogger = sessionLogger;
	}
	
	public void terminateSession() throws IOException
	{
		this.addLog("End of session. Terminated at " + this.generateSessionID());
		this.writeWholeLog();
		this.sessionLogger.close();
	}
	
	public String generateFileNameSessionID()
	{
		String sessionID = new String();
		
		LocalDate date = LocalDate.now();
		
		String dateString = date.toString();
		
		sessionID = dateString;
		
		return sessionID;
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
		log = this.generateSessionID() + ": " + log + "\n";
		this.sessionLogs.add(log);
	}
	
	public void writeToFile(String log) throws IOException
	{
		log = log + "\n";
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