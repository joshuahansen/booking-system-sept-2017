package main;

//import main.*;
//import users.*;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Session 
{
	private ArrayList<String> sessionLogs;
	private String sessionID;
	private File sessionFolder;
	private File sessionLog;
	private FileWriter sessionLogger;
	
//	Login login;
//	Registration registration;
	
	public Session() throws IOException
	{
		this.sessionLogs = new ArrayList<>();

		this.sessionID = generateSessionID();
		this.sessionFolder = new File("./sessionLogs");
		this.sessionFolder.mkdir();
		this.sessionLog = new File("./sessionLogs/" + this.sessionID + ".log");
		this.sessionLog.createNewFile();

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
		this.addLog("Session terminated.");
		this.writeWholeLog();
		this.sessionLogger.close();
	}
	
	public String generateSessionID()
	{
		String sessionID = new String();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
		
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		
		String dateString = date.toString();
		String timeString = formatter.format(time);
		
		timeString = this.parseTime(timeString);
		
		sessionID = dateString + " " + timeString;
		
		return sessionID;
	}
	
	public void addLog(String log)
	{
		log = this.generateSessionID() + ": " + log + "\n";
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
	
	public String parseTime(String time)
	{
		String parsedTime = new String();
		
		parsedTime = time.replaceAll(":", "-");
		
		return parsedTime;
	}
}