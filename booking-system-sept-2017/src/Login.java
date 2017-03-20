import java.util.ArrayList;
import java.util.Scanner;

public class Login 
{
	private String username = new String();
	private String password = new String();
	
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
	
	public Login getUsernamePassword()
	{
		Scanner userInput = new Scanner(System.in);
		
		String username = new String();
		String password = new String();
		
		System.out.println("\n");
		System.out.println("--- Register ---");
		System.out.println("\n");
		
		System.out.print("Username: ");
		username = userInput.next();
		System.out.print("Password: ");
		password = userInput.next();
		
		Login login = new Login(username, password);
		
		userInput.close();
		
		return login;
	}
	
	public int login(ArrayList<Customer> customers, ArrayList<Business> businesses, Login login)
	{
		int customersLength = customers.size();
		int businessesLength = businesses.size();
		int usernameIndex = 0;
		int counter = 0;
		
		boolean usernameFound = false;
		boolean passwordFound = false;
		boolean isCustomer = false;
		boolean isBusiness = false;
		
		String username = login.getUsername();
		String password = login.getPassword();
		
		for (counter = 0; counter < customersLength; counter++)
		{
			if ((customers.get(counter)).equals(username))
			{
				usernameFound = true;
				
				isCustomer = true;
				
				usernameIndex = counter;
				
				break;
			}
		}
		
		for (counter = 0; counter < businessesLength; counter++)
		{
			if (usernameFound == false)
			{
				if ((businesses.get(counter)).equals(username))
				{
					usernameFound = true;
					
					isBusiness = true;
					
					usernameIndex = counter;
					
					break;
				}
			}
			else
			{
				break;
			}
		}
		
		// check password against usernameIndex
		
		if (isCustomer == true && passwordFound == true)
		{
			return 1;
		}
		
		if (isBusiness == true && passwordFound == true)
		{
			return 2;
		}
		
		return 0;
	}
}
