package users;

/**
 * An abstract class for users of the program, extended by Customer and Business.
 * @author SEPT-Team-6
 */
public abstract class User {
	
	/**
	 * Common user values: name, address, phone number, username and password.
	 */
	private String firstName;
	private String lastName;
	private String address;
	private String contactNumber;
	private String username;
	private String password;
	
	/**
	 * Constructor for the User object, called via super() from extending subclasses i.e. Customer and Business.
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param contactNumber
	 * @param username
	 * @param password
	 */
	public User(String firstName, String lastName, String address, String contactNumber,
					String username, String password)
	{
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setContactNumber(contactNumber);
		setUsername(username);
		setPassword(password);
	}

	/**
	 * Mutator method for first name.
	 * @param firstName First name of the user, to be set.
	 */
	private void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * Mutator method for last name.
	 * @param lastName Last name of the user, to be set.
	 */
	private void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	/**
	 * Mutator method for address.
	 * @param address Address of the user, to be set.
	 */
	private void setAddress(String address)
	{
		this.address = address;
	}
	
	/**
	 * Mutator method for contact number.
	 * @param contactNumber Contact number of the user, to be set.
	 */
	private void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}
	
	/**
	 * Mutator method for username.
	 * @param username Username of the user, to be set.
	 */
	private void setUsername(String username)
	{
		this.username = username;
	}
	
	/**
	 * Mutator method for password.
	 * @param password Password of the user, to be set.
	 */
	private void setPassword(String password)
	{
		this.password = password;
	}
	
	/**
	 * Accessor method for first name.
	 * @return Returns first name.
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Accessor method for last name.
	 * @return Returns last name.
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Accessor method for full name.
	 * @return Concatenation of first name and last name.
	 */
	public String getFullName()
	{
		return getFirstName() + " " + getLastName();
	}
	
	/**
	 * Accessor method for address.
	 * @return Returns address.
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * Accessor method for contact number.
	 * @return Returns contact number.
	 */
	public String getContactNumber()
	{
		return contactNumber;
	}
	
	/**
	 * Accessor method for username.
	 * @return Returns username.
	 */
	public String getUsername()
	{
		return username;
	}
	
	/**
	 * Accessor method for password.
	 * @return Returns password.
	 */
	public String getPassword()
	{
		return password;
	}
}
