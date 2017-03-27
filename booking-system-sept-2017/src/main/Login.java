package main;
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
  
	private void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return this.password;
	}
  
	private void setPassword(String password)
	{
		this.password = password;
	}
	
	public int getUserPosition()
	{
		return this.userPosition;
	}
	
	private void setUserPosition(int userPosition)
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
		System.out.println("\n");
		System.out.println("--- Login ---");
		System.out.println("\n");
		
		System.out.print("Username: ");
		this.username = userInput.next();
		System.out.print("Password: ");
		this.password = userInput.next();
	}
	
	public int login(ArrayList<Customer> customers, ArrayList<Business> businesses)
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
					this.userPosition = counter;
					
					correctPassword = (customers.get(this.userPosition)).getPassword();
					
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
					this.userPosition = counter;
					
					correctPassword = (businesses.get(this.userPosition)).getPassword();
					
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
		}
		
		return 0;
	}
}
