package main;

//import main.*;
//import users.*;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Session 
{
	/**
	 * Session log variables.
	 */
	private ArrayList<String> sessionLogs;
	private String sessionID;
	private File sessionFolder;
	private File sessionLog;
	private FileWriter sessionLogger;
	
//	Login login;
//	Registration registration;
	
	/**
	 * Constructor for session object.
	 * @throws IOException
	 */
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
	
	/**
	 * Accessor for session logs.
	 * @return
	 */
	public ArrayList<String> getSessionLogs()
	{
		return this.sessionLogs;
	}
	
	/**
	 * Mutator for session logs.
	 * @param sessionLogs
	 */
	public void setSessionLogs(ArrayList<String> sessionLogs)
	{
		this.sessionLogs = sessionLogs;
	}
	
	/**
	 * Accessor for session ID.
	 * @return
	 */
	public String getSessionID()
	{
		return this.sessionID;
	}
	
	/**
	 * Mutator for session ID.
	 * @param sessionID
	 */
	public void setSessionID(String sessionID)
	{
		this.sessionID = sessionID;
	}
	
	/**
	 * Accessor for session log.
	 * @return
	 */
	public File getSessionLog()
	{
		return this.sessionLog;
	}
	
	/**
	 * Mutator for session log.
	 * @param sessionLog
	 */
	public void setSessionLog(File sessionLog)
	{
		this.sessionLog = sessionLog;
	}
	
	/**
	 * Accessor for session logger.
	 * @return
	 */
	public FileWriter getSessionLogger()
	{
		return this.sessionLogger;
	}
	
	/**
	 * Mutator for session logger.
	 * @param sessionLogger
	 */
	public void setSessionLogger(FileWriter sessionLogger)
	{
		this.sessionLogger = sessionLogger;
	}
	
	/**
	 * Function to terminate session.
	 * @throws IOException
	 */
	public void terminateSession() throws IOException
	{
		this.addLog("Session terminated.");
		this.writeWholeLog();
		this.sessionLogger.close();
	}
	
	/**
	 * Function to generate session ID.
	 * @return
	 */
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
	
	/**
	 * Function to add log to array.
	 * @param log
	 */
	public void addLog(String log)
	{
		log = this.generateSessionID() + ": " + log + "\n";
		this.sessionLogs.add(log);
	}
	
	/**
	 * Function to write specific log to file.
	 * @param log
	 * @throws IOException
	 */
	public void writeToFile(String log) throws IOException
	{
		this.sessionLogger.append(log);
	}
	
	/**
	 * Function to write whole array to file.
	 * @throws IOException
	 */
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
	
	/**
	 * Function to remove : from session log.
	 * @param time
	 * @return
	 */
	public String parseTime(String time)
	{
		String parsedTime = new String();
		
		parsedTime = time.replaceAll(":", "-");
		
		return parsedTime;
	}
}