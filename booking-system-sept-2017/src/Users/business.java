package Users;

public class business extends user
{
	private String businessName;
	
	public business(String businessName, String firstName, String lastName, String address, 
					String contactNumber, String username, String password) 
	{
		super(firstName, lastName, address, contactNumber, username, password);
		setBusinessName(businessName);
	}

	private void setBusinessName(String businessName)
	{
		this.businessName = businessName;
	}
	
	public String getBusinessName()
	{
		return businessName;
	}
}
