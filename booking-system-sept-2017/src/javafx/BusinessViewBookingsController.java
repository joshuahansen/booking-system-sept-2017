package javafx;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

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
    	private final SimpleStringProperty date;
    	private final SimpleStringProperty customerName;
    	private final SimpleStringProperty day;
    	private final SimpleStringProperty time;
    	private final SimpleStringProperty employeeName;
    	
    	private TableViewBooking(String bookingId, LocalDate date, String customerName, String day, String time, String employeeName)
    	{
    		this.bookingId = new SimpleStringProperty(bookingId);
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
		busBookingTable.setItems(pastBookings);
    }
    
    public void handleFutureBookingsButtonAction(ActionEvent event)
    {	
		busBookingTable.setItems(futureBookings);
    }
    
    public void handleTodaysBookingsButtonAction(ActionEvent event)
    {
    	busBookingTable.setItems(todaysBookings);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		for(int i = 0; i < businesses.get(busPos).bookings.size(); i ++)
//		{
//				bookings.add(new TableViewBooking(businesses.get(busPos).bookings.get(i).getBookingID(), businesses.get(busPos).bookings.get(i).getDate(),
//						businesses.get(busPos).bookings.get(i).getCustomerName(), businesses.get(busPos).bookings.get(i).getDayAsString(), businesses.get(busPos).bookings.get(i).getTimeslotAsString(),
//						businesses.get(busPos).bookings.get(i).getEmployeeName()));
//		}
		
		LocalDate today = LocalDate.now();
    	for(int i = 0; i < businesses.get(busPos).bookings.size(); i ++)
		{
    		if(businesses.get(busPos).bookings.get(i).getDate().isAfter(today))
				futureBookings.add(new TableViewBooking(businesses.get(busPos).bookings.get(i).getBookingID(), businesses.get(busPos).bookings.get(i).getDate(),
						businesses.get(busPos).bookings.get(i).getCustomerName(), businesses.get(busPos).bookings.get(i).getDayAsString(), businesses.get(busPos).bookings.get(i).getTimeslotAsString(),
						businesses.get(busPos).bookings.get(i).getEmployeeName()));
		}
    	
    	for(int i = 0; i < businesses.get(busPos).bookings.size(); i ++)
		{
    		if(businesses.get(busPos).bookings.get(i).getDate().isBefore(today))
				pastBookings.add(new TableViewBooking(businesses.get(busPos).bookings.get(i).getBookingID(), businesses.get(busPos).bookings.get(i).getDate(),
						businesses.get(busPos).bookings.get(i).getCustomerName(), businesses.get(busPos).bookings.get(i).getDayAsString(), businesses.get(busPos).bookings.get(i).getTimeslotAsString(),
						businesses.get(busPos).bookings.get(i).getEmployeeName()));
		}
    	
    	for(int i = 0; i < businesses.get(busPos).bookings.size(); i ++)
		{
    		if(businesses.get(busPos).bookings.get(i).getDate().equals(today))
				todaysBookings.add(new TableViewBooking(businesses.get(busPos).bookings.get(i).getBookingID(), businesses.get(busPos).bookings.get(i).getDate(),
						businesses.get(busPos).bookings.get(i).getCustomerName(), businesses.get(busPos).bookings.get(i).getDayAsString(), businesses.get(busPos).bookings.get(i).getTimeslotAsString(),
						businesses.get(busPos).bookings.get(i).getEmployeeName()));
		}
		
		busBookingTable.setItems(todaysBookings);
	}
}
