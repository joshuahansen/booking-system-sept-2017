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
import main.Session;
import users.*;

 
public class CustomerMenuController implements Initializable{
    @FXML private Button custDetailsButton;
    @FXML private Button custMakeBookingButton;
    @FXML private Button custViewBookingsButton;
    @FXML private Button custLogoutButton;
    
    @FXML private GridPane custMenuContent;
    
//    private ArrayList<Customer> customers;
//    private ArrayList<Business> businesses;
//    int custPos;
    private Session session;
    private Database database;
    private Customer customer;
    
    public CustomerMenuController(Session session, Customer customer, Business business, Database database)
    {
//    	this.customers = customers;
    	this.customer = customer;
//    	this.businesses = businesses;
//    	this.custPos = custPos;
    	this.session = session;
    	this.database = database;
    }
    //when logout is pressed load the login screen
    @FXML protected void logoutAction(ActionEvent event)
    {
    	session.addLog("Logout Button Pressed");
    	try {
	    	Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) custLogoutButton.getScene().getWindow();
	      	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			LoginController controller = new LoginController(session, businesses, database);
			loader.setController(controller);
			
			root = loader.load();
			
	    	Scene scene = new Scene(root, 860, 640);
	    	root.getStylesheets().add(getClass().getResource("customerMenu.css").toExternalForm());
	    	stage.setScene(scene);
	    	stage.show();
    	}catch(IOException e)
    	{
    		session.addLog("Unable to load login scene");
    	}
    }
    //load the customer details when the details button is pressed
    @FXML protected void detailsAction(ActionEvent event)
    { 
    	session.addLog("Details Button Pressed");
    	try {
    		custMenuContent.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDetails.fxml"));
    		CustomerDetailsController controller = new CustomerDetailsController(session, customer);
    		loader.setController(controller);
    		
    		custMenuContent.getChildren().add(loader.load());
    		
    	}catch(IOException e)
    	{
    		session.addLog("Unable to load Customer Details");
    	}
    }
    //when the make booking button is pressed load and create a controller
    @FXML protected void makeBookingAction(ActionEvent event)
    {
    	session.addLog("Make Booking Button Pressed");
    	try {
    		custMenuContent.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerMakeBooking.fxml"));
    		CustomerMakeBookingController controller = new CustomerMakeBookingController(session, businesses, customer);
    		loader.setController(controller);
    		
    		custMenuContent.getChildren().add(loader.load());
    		
    	}catch(IOException e)
    	{
    		session.addLog("Unable to Make a booking");
    	}
    }
    //when the view booking button is pressed load the fxml file and create a new controller for it.
    @FXML protected void viewBookingsAction(ActionEvent event)
    {
    	session.addLog("View Bookings Button Pressed");
    	try {
    		custMenuContent.getChildren().clear();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustViewBookings.fxml"));
    		CustViewBookingsController controller = new CustViewBookingsController(session, customer, businesses, 0);
    		loader.setController(controller);
    		
    		custMenuContent.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		session.addLog("Unable to load bookings");
    	}
    }
    //initialize the customer menu with the customer details loaded.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
    		custMenuContent.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDetails.fxml"));
    		CustomerDetailsController controller = new CustomerDetailsController(session, customer);
    		loader.setController(controller);
    		session.addLog("load customer details");
    		custMenuContent.getChildren().add(loader.load());
    		
    	}catch(IOException e)
    	{
    		session.addLog("Unable to load Customer Details");
    	}
		
	}
}