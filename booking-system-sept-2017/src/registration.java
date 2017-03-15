import java.util.Scanner;

public class registration {

	private Scanner keyboard = new Scanner(System.in);
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String address;
	private String phone;

	public void register() {
		
		System.out.println("\n");
		System.out.println("--- Register ---");
		System.out.println("\n");
		
		System.out.print("Enter First Name: ");
		//prompts the user to enter their first name and assigns it to the first name variable
		firstName = keyboard.next();
		//prompts the user to enter their last name and assigns it to the last name variable
		System.out.print("Enter Last Name: ");
		lastName = keyboard.next();
		//prompts the user to enter their username and assigns it to the username variable
		System.out.print("Enter Username: ");
		username = keyboard.next();
		//prompts the user to enter their password and assigns it to the password variable
		System.out.print("Enter Password: ");
		password = keyboard.next();
		//prompts the user to enter their username and assigns it to the username variable
		System.out.print("Enter Address: ");
		address = keyboard.next();
		//prompts the user to enter their password and assigns it to the password variable
		System.out.print("Enter Phone Number: ");
		phone = keyboard.next();
		
	}
	
}

