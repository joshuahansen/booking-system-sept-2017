package javafx;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.CustomerMakeBookingController.AvailableBookingTable;
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
import users.*;

public class BusinessViewBookingsController implements Initializable{
    private ArrayList<Business> businesses;
    private int busPos;
    
//    private final ObservableList<TableViewBooking> bookings = FXCollections.observableArrayList();
    private final ObservableList<TableViewBooking> pastBookings = FXCollections.observableArrayList();
    private final ObservableList<TableViewBooking> futureBookings = FXCollections.observableArrayList();
    private final ObservableList<TableViewBooking> todaysBookings = FXCollections.observableArrayList();
    		
    @FXML private TableView<TableViewBooking> busBookingTable;    
    
    public BusinessViewBookingsController(ArrayList<Business> businesses, int busPos)
    {
    	this.businesses = businesses;
    	this.busPos = busPos;
    }
    
    public static class TableViewBooking 
    {
    	private final SimpleStringProperty bookingId;
    	private final SimpleStringProperty sessionType;
    	private final SimpleStringProperty date;
    	private final SimpleStringProperty customerName;
    	private final SimpleStringProperty day;
    	private final SimpleStringProperty time;
    	private final SimpleStringProperty employeeName;
    	
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
    	
    	public String getBookingId()
    	{
    		return bookingId.get();
    	}
    	
    	public String getSessionType()
    	{
    		return sessionType.get();
    	}
    	
    	public String getDate()
    	{
    		return date.get();
    	}
    	
    	public String getCustomerName()
    	{
    		return customerName.get();
    	}
    	
    	public String getDay()
    	{
    		return day.get();
    	}
    	
    	public String getTime()
    	{
    		return time.get();
    	}
    	
    	public String getEmployeeName()
    	{
    		return employeeName.get();
    	}
    }
    
    public void handlePastBookingsButtonAction(ActionEvent event)
    {	
    	pastBookings.clear();
    	LocalDate today = LocalDate.now();
    	for(int i = 0; i < businesses.get(busPos).bookings.size(); i ++)
		{
    		if(businesses.get(busPos).bookings.get(i).getDate().isBefore(today))
				pastBookings.add(new TableViewBooking(businesses.get(busPos).bookings.get(i).getBookingID(), businesses.get(busPos).bookings.get(i).getSessionType(),
						businesses.get(busPos).bookings.get(i).getDate(), businesses.get(busPos).bookings.get(i).getCustomerName(),
						businesses.get(busPos).bookings.get(i).getDayAsString(), businesses.get(busPos).bookings.get(i).getTimeslotAsString(),
						businesses.get(busPos).bookings.get(i).getEmployeeName()));
		}
		busBookingTable.setItems(pastBookings);
    }
    
    public void handleFutureBookingsButtonAction(ActionEvent event)
    {	
    	futureBookings.clear();
    	LocalDate today = LocalDate.now();
    	for(int i = 0; i < businesses.get(busPos).bookings.size(); i ++)
		{
    		if(businesses.get(busPos).bookings.get(i).getDate().isAfter(today))
				futureBookings.add(new TableViewBooking(businesses.get(busPos).bookings.get(i).getBookingID(), businesses.get(busPos).bookings.get(i).getSessionType(),
						businesses.get(busPos).bookings.get(i).getDate(), businesses.get(busPos).bookings.get(i).getCustomerName(),
						businesses.get(busPos).bookings.get(i).getDayAsString(), businesses.get(busPos).bookings.get(i).getTimeslotAsString(),
						businesses.get(busPos).bookings.get(i).getEmployeeName()));
		}
		busBookingTable.setItems(futureBookings);
    }
    
    public void handleTodaysBookingsButtonAction(ActionEvent event)
    {
    	todaysBookings.clear();
    	LocalDate today = LocalDate.now();
    	for(int i = 0; i < businesses.get(busPos).bookings.size(); i ++)
		{
    		if(businesses.get(busPos).bookings.get(i).getDate().equals(today))
				todaysBookings.add(new TableViewBooking(businesses.get(busPos).bookings.get(i).getBookingID(), businesses.get(busPos).bookings.get(i).getSessionType(),
						businesses.get(busPos).bookings.get(i).getDate(), businesses.get(busPos).bookings.get(i).getCustomerName(),
						businesses.get(busPos).bookings.get(i).getDayAsString(), businesses.get(busPos).bookings.get(i).getTimeslotAsString(),
						businesses.get(busPos).bookings.get(i).getEmployeeName()));
		}
    	busBookingTable.setItems(todaysBookings);
    }
    
    public void handleRemoveBookingButtonAction(ActionEvent event)
    {
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
	        	for(int i = 0; i < businesses.get(busPos).bookings.size(); i++)
	        	{
	        		if(businesses.get(busPos).bookings.get(i).getBookingID().equalsIgnoreCase(bookingID))
	        		{
	        			businesses.get(busPos).bookings.remove(businesses.get(busPos).bookings.get(i));
	        			busBookingTable.getItems().remove(newSelection);
	        		}
	        	}
	    	} else {
	    	    // ... user chose CANCEL or closed the dialog
	    	}
	    }
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		LocalDate today = LocalDate.now();
    	
    	for(int i = 0; i < businesses.get(busPos).bookings.size(); i ++)
		{
    		if(businesses.get(busPos).bookings.get(i).getDate().equals(today))
    		{
				todaysBookings.add(new TableViewBooking(businesses.get(busPos).bookings.get(i).getBookingID(), businesses.get(busPos).bookings.get(i).getSessionType(),
						businesses.get(busPos).bookings.get(i).getDate(), businesses.get(busPos).bookings.get(i).getCustomerName(),
						businesses.get(busPos).bookings.get(i).getDayAsString(), businesses.get(busPos).bookings.get(i).getTimeslotAsString(),
						businesses.get(busPos).bookings.get(i).getEmployeeName()));
    		}
		}
		
		busBookingTable.setItems(todaysBookings);
	}
}
