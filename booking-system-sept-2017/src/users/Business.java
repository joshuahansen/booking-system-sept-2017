package users;

public class Business extends User
{
	private String businessName;
	//constructor for business
	public Business(String businessName, String firstName, String lastName, String address, 
					String contactNumber, String username, String password) 
	{
		super(firstName, lastName, address, contactNumber, username, password);
		setBusinessName(businessName);
	}
//setter for business name
	private void setBusinessName(String businessName)
	{
		this.businessName = businessName;
	}
	//getter for business name
	public String getBusinessName()
	{
		return businessName;
	}
}
