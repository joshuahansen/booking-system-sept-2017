package javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import users.*;

 
public class BusinessMenuController implements Initializable {
    @FXML private Button busLogout;
    @FXML private Label busMenuHeading;
    
    @FXML private GridPane businessDetails;
    @FXML private GridPane addEmployeeForm;
    @FXML private GridPane busViewBooking;
    @FXML private GridPane busEmployeeAvail;
    @FXML private GridPane busMakeBooking;
    @FXML private TabPane businessMenuTabPane;
    @FXML private Tab detailsTab;
    
    ArrayList<Customer> customers;
    ArrayList<Business> businesses;
    int busPos;
    
    public BusinessMenuController(ArrayList<Customer> customers, ArrayList<Business> businesses, int busPos)
    {
    	this.customers = customers;
    	this.businesses = businesses;
    	this.busPos = busPos;
    }
    
    @FXML protected void busLogoutAction(ActionEvent event)
    {
    	try {
	    	Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) busLogout.getScene().getWindow();
	      	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			LoginController controller = new LoginController(customers, businesses);
			loader.setController(controller);
			
			root = loader.load();
			
	    	Scene scene = new Scene(root, 860, 640);
	    	root.getStylesheets().add(getClass().getResource("businessMenu.css").toExternalForm());
	    	stage.setScene(scene);
	    	stage.show();
    	}catch(IOException e)
    	{
    		System.out.println("Unable to load login scene");
    	}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		busMenuHeading.setText("Welcome to The Booking System");
		try {
    		businessDetails.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessDetails.fxml"));
    		BusinessDetailsController controller = new BusinessDetailsController(businesses, busPos);
    		loader.setController(controller);
    		
    		businessDetails.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		System.out.println("Unable to load Business Details Tab");
    	}
	}

	@FXML protected void onSelectionMakeBooking(Event event)
	{
		try {
    		busMakeBooking.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessMakeBooking.fxml"));
    		BusinessMakeBookingController controller = new BusinessMakeBookingController(businesses, customers, busPos);
    		loader.setController(controller);
    		
    		busMakeBooking.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		System.out.println("Unable to load Make Bookings Tab");
    	}
	}

	@FXML protected void onSelectionDetails(Event event)
	{
		try {
    		businessDetails.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessDetails.fxml"));
    		BusinessDetailsController controller = new BusinessDetailsController(businesses, busPos);
    		loader.setController(controller);
    		
    		businessDetails.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		System.out.println("Unable to load Business Details Tab");
    	}
	}

	@FXML protected void onSelectionViewBookings(Event event)
	{
		try {
    		busViewBooking.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessViewBookings.fxml"));
    		BusinessViewBookingsController controller = new BusinessViewBookingsController(businesses, busPos);
    		loader.setController(controller);
    		
    		busViewBooking.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		System.out.println("Unable to load View Bookings Tab");
    	}
	}

	@FXML protected void onSelectionAddEmployee(Event event)
	{
		try {
    		addEmployeeForm.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmployees.fxml"));
    		AddEmployeeController controller = new AddEmployeeController(businesses, busPos);
    		loader.setController(controller);
    		
    		addEmployeeForm.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		System.out.println("Unable to load Add Employee Tab");
    	}
	}

	@FXML protected void onSelectionEmployeeAvailability(Event event)
	{
		try {
			busEmployeeAvail.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeAvailabilities.fxml"));
    		EmployeeAvailiabilitiesController controller = new EmployeeAvailiabilitiesController(businesses, busPos);
    		loader.setController(controller);
    		
    		busEmployeeAvail.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		System.out.println("Unable to load Employee Availabilities Tab");
    	}
	}
}