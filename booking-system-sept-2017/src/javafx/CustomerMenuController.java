package javafx;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import main.*;
import users.*;

 
public class CustomerMenuController {
    @FXML private Button custDetailsButton;
    @FXML private Button custMakeBookingButton;
    @FXML private Button custViewBookingsButton;
    @FXML private Button custLogoutButton;
    
    ArrayList<Customer> customers;
    ArrayList<Business> businesses;
    
    public CustomerMenuController(ArrayList<Customer> customers, ArrayList<Business> businesses)
    {
    	this.customers = customers;
    	this.businesses = businesses;
    }
    
    @FXML protected void logoutAction(ActionEvent event)
    {
    	try {
	    	Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) custLogoutButton.getScene().getWindow();
	      	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			LoginController controller = new LoginController(customers, businesses);
			loader.setController(controller);
			
			root = loader.load();
			
	    	Scene scene = new Scene(root, 1080, 720);
	    	stage.setScene(scene);
	    	stage.show();
    	}catch(IOException e)
    	{
    		System.out.println("Unable to load login scene");
    	}
    }
    
    @FXML protected void detailsAction(ActionEvent event)
    {
    	
    }
    
    @FXML protected void makeBookingAction(ActionEvent event)
    {
    	
    }
    
    @FXML protected void viewBookingsAction(ActionEvent event)
    {
    	
    }
}