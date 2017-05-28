package javafx;

import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import users.*;
import main.*;

/**
 * Main Business Menu controller. Has tabs for all the different business options, tabs are separate controllers and fxml files for easier changes and modifications 
 * @author SEPT Team 6
 *
 */
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
    @FXML private ImageView busImageView;
    @FXML private Button addImageButton;
    
    private ArrayList<Business> businesses;
    private Business business;
    private Session session;
    private Database database;
    
    /**
     * Constructor for business menu
     * @param session for logging system runtime
     * @param businesses array of business
     * @param business current logged in businesses
     * @param database database connection for saving and retrieving data
     */
    public BusinessMenuController(Session session, ArrayList<Business> businesses, Business business, Database database)
    {
    	this.business = business;
    	this.businesses = businesses;
//    	this.busPos = busPos;
    	this.session = session;
    	this.database = database;
    }
    /**
     * Logout button. Takes user back to login page
     * @param event
     */
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
    /**
     * Loads business details tab when business user logs in
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
    		businessDetails.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessDetails.fxml"));
    		busMenuHeading.setText(business.getBusinessName());
    		BusinessDetailsController controller = new BusinessDetailsController(session, business);
    		loader.setController(controller);
    		businessDetails.getChildren().add(loader.load());
    		busImageView.setImage(business.getBusinessLogo());	
    		
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		session.addLog("Unable to load Business Details Tab");
    	}
	}

	/**
	 * on selection make booking tab is loaded and refreshed
	 * @param event
	 */
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

	/**
	 * on selection business details are loaded and refreshed
	 * @param event
	 */
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

	/**
	 * on selection view bookings tab is loaded and refreshed
	 * @param event
	 */
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
	/**
	 * on selection Add employee tab loaded and refreshed 
	 * @param event
	 */
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
	/**
	 * on selection employee availabilities tab loaded and refreshed
	 * @param event
	 */
	@FXML protected void onSelectionEmployeeAvailability(Event event)
	{
		try {
			busEmployeeAvail.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeAvailabilities.fxml"));
    		EmployeeAvailabilitiesController controller = new EmployeeAvailabilitiesController(session, business);
    		loader.setController(controller);
    		
    		busEmployeeAvail.getChildren().add(loader.load());
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		session.addLog("Unable to load Employee Availabilities Tab");
    	}
	}
	/**
	 * on selection business hours tabn loaded and refreshed
	 * @param event
	 */
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
	/**
	 * on selection business booking types tab loaded and refreshed
	 * @param event
	 */
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
	/**
	 * Handle button action for Add image. 
	 * Business can add a custom image to the booking system allowing them to make it custom for their customers
	 * @param event
	 */
	@FXML protected void handleAddImageButtonAction (Event event) {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(new Stage());
		 
		if(fc != null) {
			fc.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("All Images", "*.*"),
		    	    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
		    	    new FileChooser.ExtensionFilter("PNG", "*.png")
					);
		    if(selectedFile != null) {
		    	String filepath = selectedFile.toURI().toString();
		    	Image busImage = new Image(filepath);
		    	business.setBusinessLogo(filepath);
		    	busImageView.setImage(busImage);	
		    }
		}
	}
}
