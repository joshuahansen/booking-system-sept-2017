package javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import users.*;

 
public class CustomerMenuController implements Initializable{
    @FXML private Button custDetailsButton;
    @FXML private Button custMakeBookingButton;
    @FXML private Button custViewBookingsButton;
    @FXML private Button custLogoutButton;
    
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
    //when logout is pressed load the login screen
    @FXML protected void logoutAction(ActionEvent event)
    {
    	try {
	    	Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) custLogoutButton.getScene().getWindow();
	      	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			LoginController controller = new LoginController(session, customers, businesses);
			loader.setController(controller);
			
			root = loader.load();
			
	    	Scene scene = new Scene(root, 860, 640);
	    	root.getStylesheets().add(getClass().getResource("customerMenu.css").toExternalForm());
	    	stage.setScene(scene);
	    	stage.show();
    	}catch(IOException e)
    	{
    		System.out.println("Unable to load login scene");
    	}
    }
    //load the customer details when the details button is pressed
    @FXML protected void detailsAction(ActionEvent event)
    { 
    	try {
    		custMenuContent.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDetails.fxml"));
    		CustomerDetailsController controller = new CustomerDetailsController(customers, custPos);
    		loader.setController(controller);
    		
    		custMenuContent.getChildren().add(loader.load());
    		
    	}catch(IOException e)
    	{
    		System.out.println("Unable to load Customer Details");
    	}
    }
    //when the make booking button is pressed load and create a controller
    @FXML protected void makeBookingAction(ActionEvent event)
    {
    	try {
    		custMenuContent.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerMakeBooking.fxml"));
    		CustomerMakeBookingController controller = new CustomerMakeBookingController(businesses, customers, custPos);
    		loader.setController(controller);
    		
    		custMenuContent.getChildren().add(loader.load());
    		
    	}catch(IOException e)
    	{
    		System.out.println("Unable to Make a booking");
    	}
    }
    //when the view booking button is pressed load the fxml file and create a new controller for it.
    @FXML protected void viewBookingsAction(ActionEvent event)
    {
    	try {
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
    //initialize the customer menu with the customer details loaded.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
    		custMenuContent.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDetails.fxml"));
    		CustomerDetailsController controller = new CustomerDetailsController(customers, custPos);
    		loader.setController(controller);
    		
    		custMenuContent.getChildren().add(loader.load());
    		
    	}catch(IOException e)
    	{
    		System.out.println("Unable to load Customer Details");
    	}
		
	}
}