package javafx;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import main.Session;
import users.*;

/**
 * Business able to View all booking that have been made. 
 * Can sort bookings into past future and todays bookings for easier viewing
 * @author SEPT Team 6
 *
 */
public class BusinessViewBookingsController implements Initializable{
    private Business business;
    
    private final ObservableList<TableViewBooking> pastBookings = FXCollections.observableArrayList();
    private final ObservableList<TableViewBooking> futureBookings = FXCollections.observableArrayList();
    private final ObservableList<TableViewBooking> todaysBookings = FXCollections.observableArrayList();
    		
    @FXML private TableView<TableViewBooking> busBookingTable;    
    private Session session;
    
    public BusinessViewBookingsController(Session session, Business business)
    {
    	this.business = business;
    	this.session = session;
    }
    
    /**
     * Object to display bookings details in a table.
     * @author SEPT Team 6
     *
     */
    public static class TableViewBooking 
    {
    	private final SimpleStringProperty bookingId;
    	private final SimpleStringProperty sessionType;
    	private final SimpleStringProperty date;
    	private final SimpleStringProperty customerName;
    	private final SimpleStringProperty day;
    	private final SimpleStringProperty time;
    	private final SimpleStringProperty employeeName;
    	/**
    	 * Constructor to create a booking to display 
    	 * @param bookingId unique ID used to identify a specific booking
    	 * @param sessionType type of booking
    	 * @param date of the given booking
    	 * @param customerName customer that made the booking
    	 * @param day the booking is on
    	 * @param time time slot of the booking
    	 * @param employeeName employee that is taking the booking
    	 */
    	private TableViewBooking(String bookingId, String sessionType, LocalDate date, String customerName, String day, String time, String employeeName)
    	{
    		this.bookingId = new SimpleStringProperty(bookingId);
    		this.sessionType = new SimpleStringProperty(sessionType);
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
    		this.date = new SimpleStringProperty(date.format(formatter));
    		this.customerName = new SimpleStringProperty(customerName);
    		this.day = new SimpleStringProperty(day);
    		this.time = new SimpleStringProperty(time);
    		this.employeeName = new SimpleStringProperty(employeeName);
    	}
    	/**
    	 * gets the booking id
    	 * @return bookingId of booking
    	 */
    	public String getBookingId()
    	{
    		return bookingId.get();
    	}
    	/**
    	 * get the session type
    	 * @return SessionType of booking
    	 */
    	public String getSessionType()
    	{
    		return sessionType.get();
    	}
    	/**
    	 * get the date
    	 * @return date of the booking
    	 */
    	public String getDate()
    	{
    		return date.get();
    	}
    	/**
    	 * get the customer's name
    	 * @return customerName of the booking
    	 */
    	public String getCustomerName()
    	{
    		return customerName.get();
    	}
    	/**
    	 * Get day as a String
    	 * @return Day String of the booking
    	 */
    	public String getDay()
    	{
    		return day.get();
    	}
    	/**
    	 * get the time
    	 * @return time String of the booking
    	 */
    	public String getTime()
    	{
    		return time.get();
    	}
    	/**
    	 * get the employee name
    	 * @return employee name String of the booking
    	 */
    	public String getEmployeeName()
    	{
    		return employeeName.get();
    	}
    }
    /**
     * Handle past booking button action
     * Sort the bookings to get all bookings before the current day
     * @param event
     */
    public void handlePastBookingsButtonAction(ActionEvent event)
    {	
    	session.addLog("Past bookings Button Pressed");
    	pastBookings.clear();
    	LocalDate today = LocalDate.now();
    	for(int i = 0; i < business.getBookings().size(); i ++)
		{
    		if(business.getBookings().get(i).getDate().isBefore(today))
				pastBookings.add(new TableViewBooking(business.getBookings().get(i).getBookingID(), business.getBookings().get(i).getSessionType(),
						business.getBookings().get(i).getDate(), business.getBookings().get(i).getCustomerName(),
						business.getBookings().get(i).getBookingTime().getDay(), business.getBookings().get(i).getBookingTime().getTimeslotAsString(),
						business.getBookings().get(i).getEmployeeName()));
		}
		busBookingTable.setItems(pastBookings);
    }
    
    /**
     * Handle Future booking button action
     * Sort bookings to get all bookings after todays date
     * @param event
     */
    public void handleFutureBookingsButtonAction(ActionEvent event)
    {	
    	session.addLog("Future bookings Button Pressed");
    	futureBookings.clear();
    	LocalDate today = LocalDate.now();
    	for(int i = 0; i < business.getBookings().size(); i ++)
		{
    		if(business.getBookings().get(i).getDate().isAfter(today))
				futureBookings.add(new TableViewBooking(business.getBookings().get(i).getBookingID(), business.getBookings().get(i).getSessionType(),
						business.getBookings().get(i).getDate(), business.getBookings().get(i).getCustomerName(),
						business.getBookings().get(i).getBookingTime().getDay(), business.getBookings().get(i).getBookingTime().getTimeslotAsString(),
						business.getBookings().get(i).getEmployeeName()));
		}
		busBookingTable.setItems(futureBookings);
    }
    /**
     * Handle Today booking button action
     * Sort all bookings to get booking on the current day
     * @param event
     */
    public void handleTodaysBookingsButtonAction(ActionEvent event)
    {
    	session.addLog("todays bookings Button Pressed");
    	todaysBookings.clear();
    	LocalDate today = LocalDate.now();
    	for(int i = 0; i < business.getBookings().size(); i ++)
		{
    		if(business.getBookings().get(i).getDate().equals(today))
				todaysBookings.add(new TableViewBooking(business.getBookings().get(i).getBookingID(), business.getBookings().get(i).getSessionType(),
						business.getBookings().get(i).getDate(), business.getBookings().get(i).getCustomerName(),
						business.getBookings().get(i).getBookingTime().getDay(), business.getBookings().get(i).getBookingTime().getTimeslotAsString(),
						business.getBookings().get(i).getEmployeeName()));
		}
    	busBookingTable.setItems(todaysBookings);
    }
    /**
     * Handle remove booking button action
     * Remove a booking from the database if it was canceled
     * Alert with booking details to get confirmation from user to delete the booking
     * @param event
     */
    public void handleRemoveBookingButtonAction(ActionEvent event)
    {
    	session.addLog("Remove Booking Button Pressed");
    	TableViewBooking newSelection = busBookingTable.getSelectionModel().getSelectedItem();
    	
    	if(newSelection != null)
    	{
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Delete Booking Confirmation");
	    	alert.setHeaderText("Delete Booking Confirmation");
	    	alert.setContentText("Are you sure you want to delete this booking?");
	
	    	String exceptionText = "Booking ID: " + newSelection.getBookingId() + "\nCustomer Name: " + newSelection.getCustomerName() + 
	    			"\nDate: " + newSelection.getDate() + "\nEmployee: " + newSelection.getEmployeeName();
	    			
	    			
	    	Label label = new Label("The booking details are:");
	
	    	TextArea textArea = new TextArea(exceptionText);
	    	textArea.setEditable(false);
	    	textArea.setWrapText(true);
	
	    	textArea.setMaxWidth(Double.MAX_VALUE);
	    	textArea.setMaxHeight(Double.MAX_VALUE);
	    	GridPane.setVgrow(textArea, Priority.ALWAYS);
	    	GridPane.setHgrow(textArea, Priority.ALWAYS);
	
	    	GridPane expContent = new GridPane();
	    	expContent.setMaxWidth(Double.MAX_VALUE);
	    	expContent.add(label, 0, 0);
	    	expContent.add(textArea, 0, 1);
	    	alert.getDialogPane().setExpandableContent(expContent);
	    	
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	        	String bookingID = newSelection.getBookingId();
	        	for(int i = 0; i < business.getBookings().size(); i++)
	        	{
	        		if(business.getBookings().get(i).getBookingID().equalsIgnoreCase(bookingID))
	        		{
	        			business.getBookings().remove(business.getBookings().get(i));
	        			busBookingTable.getItems().remove(newSelection);
	        		}
	        	}
	        	session.addLog("Alert confirmed");
	    	} else {
	    	    // ... user chose CANCEL or closed the dialog
	    		session.addLog("alert cancled");
	    	}
	    }
    }
    /**
     * initialize the page with todays bookings displayed in the table.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		session.addLog("Initialize with today's bookings");
		LocalDate today = LocalDate.now();
    	
    	for(int i = 0; i < business.getBookings().size(); i ++)
		{
    		if(business.getBookings().get(i).getDate().equals(today))
    		{
				todaysBookings.add(new TableViewBooking(business.getBookings().get(i).getBookingID(), business.getBookings().get(i).getSessionType(),
						business.getBookings().get(i).getDate(), business.getBookings().get(i).getCustomerName(),
						business.getBookings().get(i).getBookingTime().getDay(), business.getBookings().get(i).getBookingTime().getTimeslotAsString(),
						business.getBookings().get(i).getEmployeeName()));
    		}
		}
		
		busBookingTable.setItems(todaysBookings);
	}
}
