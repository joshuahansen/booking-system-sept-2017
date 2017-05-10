package users;

/**
 * A class for storing data about the customers using the program.
 * @author SEPT-Team-6
 */
public class Customer extends User {

	/**
	 * Constructor to make Customer object.
	 * @param firstName First name of the customer.
	 * @param lastName Last name of the customer.
	 * @param address Home address of the customer.
	 * @param contactNumber Phone number of the customer.
	 * @param username Username of the customer.
	 * @param password Password of the customer.
	 */
	public Customer(String firstName, String lastName, String address, String contactNumber,
					String username, String password)
	{
		super(firstName, lastName, address, contactNumber, username, password);
	}

}
