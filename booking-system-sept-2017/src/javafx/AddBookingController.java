package javafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

public class AddBookingController //implements Initializable
{
	ArrayList<Customer> customers;
	ArrayList<Employee> employees;
	ArrayList<Business> businesses;
	
    public AddBookingController(ArrayList<Customer> customers, 
    		ArrayList<Employee> employees, ArrayList<Business> businesses)
    {
    	this.customers = customers;
    	this.employees = employees;
    	this.businesses = businesses;
    }

//	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
	}
}