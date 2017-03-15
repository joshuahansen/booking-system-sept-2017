package Users;

public abstract class user {
	private String firstName;
	private String lastName;
	private String address;
	private String contactNumber;
	private String username;
	private String password;
	
	public user(String firstName, String lastName, String address, String contactNumber,
					String username, String password)
	{
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setContactNumber(contactNumber);
		setUsername(username);
		setPassword(password);
	}

	private void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	private void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	private void setAddress(String address)
	{
		this.address = address;
	}
	private void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}
	private void setUsername(String username)
	{
		this.username = username;
	}
	private void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getAddress()
	{
		return address;
	}
	public String getContactNumber()
	{
		return contactNumber;
	}
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
}
