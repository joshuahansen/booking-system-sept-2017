import java.util.InputMismatchException;
import java.util.Scanner;
import users.*;


public class Menu {

	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
	private void menuDisplay() {
		System.out.println("\nWelcome to the Booking System.\n");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.print("\nPlease enter your choice: ");
	}
	
	public void menuInput() {
	//create objects for functions
	Registration register = new Registration();
	Login login = new Login();
	
	Scanner key_Input = new Scanner(System.in);
	int input;
	boolean cond = false;
	
	do {
		cond = false;
		menuDisplay();
	try {
		input = key_Input.nextInt();
	
		switch (input) {
		case 1: 
			
			break;
		case 2:
			
			break;
		default:
			System.out.println("Invalid Input try again.");
			//set condition to true to continue the loop
			cond = true;
			break;
		} 
	} catch (InputMismatchException e) {
		// display exception message when user inputs a string value
		System.out.println("Please enter a numerical value.");
		// clears buffer
		key_Input.nextLine();
		cond = true;
	}}
 	while (cond == true); 
	
	}
 
}

