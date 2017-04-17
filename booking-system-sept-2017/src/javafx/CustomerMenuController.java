package javafx;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import main.*;
import users.*;

 
public class CustomerMenuController {
    @FXML private Button custDetailsButton;
    @FXML private Button custMakeBookingButton;
    @FXML private Button custViewBookingsButton;
    @FXML private Button custLogoutButton;
    
    @FXML private Text custDetailsUsernameData;
    @FXML private Text custDetailsFirstNameData;
    @FXML private Text custDetailsLastNameData;
    @FXML private Text custDetailsAddressData;
    @FXML private Text custDetailsPhoneData;
    
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
    
    @FXML protected void detailsAction(ActionEvent event) throws IOException
    { 
    	try {
    		custMenuContent.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDetails.fxml"));
    		CustomerDetailsController controller = new CustomerDetailsController();
    		loader.setController(controller);
    		
    		custMenuContent.getChildren().add(loader.load());
    		
    		//set customer values
    		controller.setUsername(customers.get(custPos).getUsername());
    		controller.setFirstName(customers.get(custPos).getFirstName());
    		controller.setLastName(customers.get(custPos).getLastName());
    		controller.setAddress(customers.get(custPos).getAddress());
    		controller.setPhone(customers.get(custPos).getContactNumber());	
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
    		custMenuContent.getChildren().clear();
    		custMenuContent.getChildren().add(FXMLLoader.load(getClass().getResource("Content2.fxml")));
    	}catch(IOException e)
    	{
    		System.out.println("Unable to load bookings");
    	}
    }
}