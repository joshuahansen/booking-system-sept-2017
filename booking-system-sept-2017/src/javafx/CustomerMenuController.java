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

/**
 *  Customer menu Controller. Controls all the buttons of the customer. 
 * @author SEPT Team 6
 *
 */
public class CustomerMenuController implements Initializable{
    @FXML private Button custDetailsButton;
    @FXML private Button custMakeBookingButton;
    @FXML private Button custViewBookingsButton;
    @FXML private Button custLogoutButton;
    
    @FXML private GridPane custMenuContent;
    
    private ArrayList<Business> businesses;
    private Business business;
    private Session session;
    private Database database;
    private Customer customer;
    /**
     * Constructor for Customer menu controller
     * @param session for system runtime logging
     * @param customer customer that is logged in
     * @param businesses array of businesses
     * @param business business that customer belongs to
     * @param database database connection
     */
    public CustomerMenuController(Session session, Customer customer, ArrayList<Business> businesses, Business business, Database database)
    {
    	this.customer = customer;
    	this.businesses = businesses;
    	this.business = business;
    	this.session = session;
    	this.database = database;
    }
    /**
     * Handle logout button action
     * returns user to login page
     * @param event
     */
    @FXML protected void logoutAction(ActionEvent event)
    {
    	session.addLog("Logout Button Pressed");
    	try {
	    	Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) custLogoutButton.getScene().getWindow();
	      	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			LoginController controller = new LoginController(session, businesses, business, database);
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
    /**
     * load Customer details page when button is pressed
     * @param event
     */
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
    /**
     * Handle make booking button. Load and refresh make booking page
     * @param event
     */
    @FXML protected void makeBookingAction(ActionEvent event)
    {
    	session.addLog("Make Booking Button Pressed");
    	try {
    		custMenuContent.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerMakeBooking.fxml"));
    		CustomerMakeBookingController controller = new CustomerMakeBookingController(session, business, customer);
    		loader.setController(controller);
    		
    		custMenuContent.getChildren().add(loader.load());
    		
    	}catch(IOException e)
    	{
    		session.addLog("Unable to Make a booking");
    	}
    }
    /**
     * Handle view booking button action. Load customer bookings page
     * @param event
     */
    @FXML protected void viewBookingsAction(ActionEvent event)
    {
    	session.addLog("View Bookings Button Pressed");
    	try {
    		custMenuContent.getChildren().clear();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustViewBookings.fxml"));
    		CustViewBookingsController controller = new CustViewBookingsController(session, customer, business);
    		loader.setController(controller);
    		
    		custMenuContent.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		session.addLog("Unable to load bookings");
    	}
    }
    /**
     * Initialize the customer menu page with the customer details page loaded
     */
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