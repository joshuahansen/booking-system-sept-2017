package main;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import users.*;

public class Login 
{
	/**
	 * Login variables.
	 */
	private String username = new String();
	private String password = new String();
	private int userPosition = 0;

	/**
	 * Default login constructor.
	 */
	public Login()
	{
		// default constructor
	}
	
	/**
	 * Login constructor method.
	 * @param username
	 * @param password
	 */
	public Login(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Accessor for username.
	 * @return
	 */
	public String getUsername()
	{
		return this.username;
	}
  
	/**
	 * Mutator for username.
	 * @param username
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * Accessor for password.
	 * @return
	 */
	public String getPassword()
	{
		return this.password;
	}
  
	/**
	 * Mutator for password.
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	/**
	 * Accessor for user position.
	 * @return
	 */
	public int getUserPosition()
	{
		return this.userPosition;
	}
	
	/**
	 * Mutator for user position.
	 * @param userPosition
	 */
	public void setUserPosition(int userPosition)
	{
		this.userPosition = userPosition;
	}
	
	/**
	 * Mutator for username and password.
	 * @param username
	 * @param password
	 */
	public void setUsernamePassword(String username, String password)
	{
		setUsername(username);
		setPassword(password);
	}
	
	/**
	 * IO method for username and password.
	 * @param userInput
	 */
	public void getUsernamePassword(Scanner userInput)
	{
		System.out.println("\n--- Login ---\n");
		
		System.out.print("Username: ");
		this.username = userInput.next();
		System.out.print("Password: ");
		this.password = userInput.next();
	}
	
	/**
	 * Function to login the user.
	 * @param business
	 * @param database
	 * @return
	 */
	public int login(Business business, Database database)
	{
		
		int customersLength = business.getCustomers().size();
//		int businessesLength = businesses.size();
		int counter = 0;
		
		String username = this.username;
		String password = this.password;
		String correctPassword = new String();
		
		if (username.equals("") || password.equals(""))
		{
			return 0;
		}
		else
		{
			for (counter = 0; counter < customersLength; counter++)
			{
				String customerUsername = business.getCustomers().get(counter).getUsername();
				
				if (customerUsername.equals(username))
				{
					setUserPosition(counter);
					
					correctPassword = business.getCustomers().get(counter).getPassword();
					
					if (password.equals(correctPassword))
					{
						return 1;
					}
					else
					{
						return 0;
					}
				}
			}
			
//			for (counter = 0; counter < businessesLength; counter++)
//			{
				String businessUsername = business.getUsername();
				
				if (businessUsername.equals(username))
				{					
					correctPassword = business.getPassword();
					
					if (password.equals(correctPassword))
					{
						return 2;
					}
					else
					{
						return 0;
					}
				}
//			}
			try{
				ResultSet resultSet = database.getConnection().createStatement().executeQuery("SELECT * FROM ADMIN");
			
					String adminUsername = resultSet.getString("USERNAME");
					String adminPassword = resultSet.getString("PASSWORD");
					if(username.equalsIgnoreCase(adminUsername) && password.equalsIgnoreCase(adminPassword))
					{
						return 3;
					}
					else 
					{
						return 0;
					}
			}catch (SQLException e) {
//				session.addLog("Unable to load admin database");
				return 0;
			}
		}
	}
}
