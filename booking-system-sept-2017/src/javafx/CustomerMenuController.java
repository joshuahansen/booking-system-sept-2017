package javafx;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import users.*;

 
public class CustomerMenuController {
    @FXML private Button custDetailsButton;
    @FXML private Button custMakeBookingButton;
    @FXML private Button custViewBookingsButton;
    @FXML private Button custLogoutButton;
    
    @FXML private Label custMenuHeading;
    
    @FXML private GridPane custMenuContent;
    
    ArrayList<Customer> customers;
    ArrayList<Business> businesses;
    int custPos;
    
    public CustomerMenuController(ArrayList<Customer> customers, ArrayList<Business> businesses, int custPos)
    {
    	this.customers = customers;
    	this.businesses = businesses;
    	this.custPos = custPos;
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
    	try {
    		custMenuContent.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDetails.fxml"));
    		CustomerDetailsController controller = new CustomerDetailsController(customers, custPos);
    		loader.setController(controller);
    		
    		custMenuContent.getChildren().add(loader.load());
    		
    		custMenuHeading.setText(customers.get(custPos).getUsername() + " Profile");
    	}catch(IOException e)
    	{
    		System.out.println("Unable to load Customer Details");
    	}
    }
    
    @FXML protected void makeBookingAction(ActionEvent event)
    {
    	
    }
    
    @FXML protected void viewBookingsAction(ActionEvent event)
    {
    	try {
    		custMenuHeading.setText(customers.get(custPos).getUsername() + " Future Bookings");
    		custMenuContent.getChildren().clear();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustViewBookings.fxml"));
    		CustViewBookingsController controller = new CustViewBookingsController(customers, businesses, custPos, 0);
    		loader.setController(controller);
    		
    		custMenuContent.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		System.out.println("Unable to load bookings");
    	}
    }
}