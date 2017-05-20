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
import main.*;

 
public class BusinessMenuController implements Initializable {
    @FXML private Button busLogout;
    @FXML private Label busMenuHeading;
    
    @FXML private GridPane businessDetails;
    @FXML private GridPane addEmployeeForm;
    @FXML private GridPane busViewBooking;
    @FXML private GridPane busEmployeeAvail;
    @FXML private GridPane busMakeBooking;
    @FXML private GridPane businessHours;
    @FXML private GridPane bookingType;
    @FXML private TabPane businessMenuTabPane;
    @FXML private Tab detailsTab;
    
//    private ArrayList<Customer> customers;
    private ArrayList<Business> businesses;
//    private int busPos;
    Business business;
    private Session session;
    private Database database;
    
    public BusinessMenuController(Session session, ArrayList<Business> businesses, Business business, Database database)
    {
//    	this.customers = customers;
    	this.business = business;
    	this.businesses = businesses;
//    	this.busPos = busPos;
    	this.session = session;
    	this.database = database;
    }
    
    @FXML protected void busLogoutAction(ActionEvent event)
    {
    	try {
	    	Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) busLogout.getScene().getWindow();
	      	
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
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
    		businessDetails.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessDetails.fxml"));
    		BusinessDetailsController controller = new BusinessDetailsController(session, business);
    		loader.setController(controller);
    		
    		businessDetails.getChildren().add(loader.load());
    		
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		session.addLog("Unable to load Business Details Tab");
    	}
	}

	@FXML protected void onSelectionMakeBooking(Event event)
	{
		try {
    		busMakeBooking.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessMakeBooking.fxml"));
    		BusinessMakeBookingController controller = new BusinessMakeBookingController(session, business);
    		loader.setController(controller);
    		
    		busMakeBooking.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		session.addLog("Unable to load Make Bookings Tab");
    	}
	}

	@FXML protected void onSelectionDetails(Event event)
	{
		try {
    		businessDetails.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessDetails.fxml"));
    		BusinessDetailsController controller = new BusinessDetailsController(session, business);
    		loader.setController(controller);
    		
    		businessDetails.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		session.addLog("Unable to load Business Details Tab");
    	}
	}

	@FXML protected void onSelectionViewBookings(Event event)
	{
		try {
    		busViewBooking.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessViewBookings.fxml"));
    		BusinessViewBookingsController controller = new BusinessViewBookingsController(session, business);
    		loader.setController(controller);
    		
    		busViewBooking.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		session.addLog("Unable to load View Bookings Tab");
    	}
	}

	@FXML protected void onSelectionAddEmployee(Event event)
	{
		try {
    		addEmployeeForm.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmployees.fxml"));
    		AddEmployeeController controller = new AddEmployeeController(session, business);
    		loader.setController(controller);
    		
    		addEmployeeForm.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		session.addLog("Unable to load Add Employee Tab");
    	}
	}

	@FXML protected void onSelectionEmployeeAvailability(Event event)
	{
		try {
			busEmployeeAvail.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeAvailabilities.fxml"));
    		EmployeeAvailiabilitiesController controller = new EmployeeAvailiabilitiesController(session, business);
    		loader.setController(controller);
    		
    		busEmployeeAvail.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		session.addLog("Unable to load Employee Availabilities Tab");
    	}
	}
	
	@FXML protected void onSelectionBusinessHours(Event event)
	{
		try {
				businessHours.getChildren().clear();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessHours.fxml"));
				BusinessHoursController controller = new BusinessHoursController(session, business);
				loader.setController(controller);
				
				businessHours.getChildren().add(loader.load());
		}catch(IOException e)
		{
			System.out.println(e);
			session.addLog("Unable to load Business Hours tab");
		}
	}
	
	@FXML protected void onSelectionBookingTypes(Event event)
	{
		
		try {
			bookingType.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingTypes.fxml"));
			BookingTypesController controller = new BookingTypesController(session, business);
			loader.setController(controller);
			
			bookingType.getChildren().add(loader.load());
	}catch(IOException e)
	{
		System.out.println(e);
		session.addLog("Unable to load Booking types tab");
	}
	}
}