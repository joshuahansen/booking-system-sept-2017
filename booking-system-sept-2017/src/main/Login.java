package main;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import users.*;

public class Login 
{
	private String username = new String();
	private String password = new String();
	private int userPosition = 0;

	public Login()
	{
		// default constructor
	}
	
	public Login(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public String getUsername()
	{
		return this.username;
  }
  
	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return this.password;
	}
  
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public int getUserPosition()
	{
		return this.userPosition;
	}
	
	public void setUserPosition(int userPosition)
	{
		this.userPosition = userPosition;
	}
	
	public void setUsernamePassword(String username, String password)
	{
		setUsername(username);
		setPassword(password);
	}
	
	public void getUsernamePassword(Scanner userInput)
	{
		System.out.println("\n--- Login ---\n");
		
		System.out.print("Username: ");
		this.username = userInput.next();
		System.out.print("Password: ");
		this.password = userInput.next();
	}
	
	public int login(ArrayList<Customer> customers, ArrayList<Business> businesses, Database database)
	{
		int customersLength = customers.size();
		int businessesLength = businesses.size();
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
				String customerUsername = (customers.get(counter)).getUsername();
				
				if (customerUsername.equals(username))
				{
					setUserPosition(counter);
					
					correctPassword = (customers.get(getUserPosition())).getPassword();
					
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
			
			for (counter = 0; counter < businessesLength; counter++)
			{
				String businessUsername = (businesses.get(counter)).getUsername();
				
				if (businessUsername.equals(username))
				{
					setUserPosition(counter);
					
					correctPassword = (businesses.get(getUserPosition())).getPassword();
					
					if (password.equals(correctPassword))
					{
						return 2;
					}
					else
					{
						return 0;
					}
				}
			}
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
