package javafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;

import main.*;
import users.*;

public class AddBookingBusinessController
{
	ArrayList<Customer> customers;
	ArrayList<Business> businesses;
	
//	@FXML private Dropdown selectCustomer;
//	@FXML private Dropdown filterByEmployee;
//	@FXML private Dropdown filterByClassType;
//	@FXML private Dropdown filterByDate;
//	@FXML private Dropdown filterbyTime;
	@FXML private Button clearFilters;
	@FXML private Button makeBooking;
	@FXML private Button cancelBooking;
	
    public AddBookingBusinessController(ArrayList<Customer> customers, ArrayList<Business> businesses)
    {
    	this.customers = customers;
    	this.businesses = businesses;
    }

//	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
	}
	
	public boolean createList(String list, String employee, String classType, String date, String time)
	{
		if (!employee.equals("Select employee"))
		{
			// filter list by employee
		}
		
		if (!classType.equals("Select class type"))
		{
			// filter list by class
		}
		
		if (!date.equals("Select date"))
		{
			// filter list by date
		}
		
		if (!time.equals("Select time"))
		{
			// filter list by time
		}
		
		// return list
		return true;
	}
	
	public boolean createBooking()
	{
		// some code
		
		return false;
	}
	
	public String generateBookingID()
	{
		String bookingID = new String();
		
		// some code
		
		return bookingID;
	}
	
	public boolean addBooking(Business business, Booking booking)
	{
		boolean bookingFound = false;
		
		int numberOfBookings = business.bookings.size();
		int counter = 0;
		
		for (counter = 0; counter < numberOfBookings; counter++)
		{
			if (business.bookings.get(counter).equals(booking))
			{
				bookingFound = true;
			}
		}
		
		if (bookingFound == true)
		{
			return false;
		}
		else
		{
			business.bookings.add(booking);
			
			return true;
		}
	}
	
	public boolean removeBooking(Business business, Booking booking)
	{
		int numberOfBookings = business.bookings.size();
		int counter = 0;
		
		for (counter = 0; counter < numberOfBookings; counter++)
		{
			if (business.bookings.get(counter).equals(booking))
			{
				business.bookings.remove(counter);
				
				return true;
			}
		}
		
		return false;
	}
}