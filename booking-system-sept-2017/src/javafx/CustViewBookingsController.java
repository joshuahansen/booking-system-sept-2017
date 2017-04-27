package javafx;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import users.*;

public class CustViewBookingsController implements Initializable{
    private ArrayList<Customer> customers;
    private ArrayList<Business> businesses;
    private int custPos;
    private int busPos;
    
    private final ObservableList<TableViewBooking> bookings = FXCollections.observableArrayList();
    		
    @FXML private TableView<TableViewBooking> custBookingsTable;    
    
    public CustViewBookingsController(ArrayList<Customer> customers, ArrayList<Business> businesses, int custPos, int busPos)
    {
    	this.customers = customers;
    	this.businesses = businesses;
    	this.custPos = custPos;
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
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(int i = 0; i < businesses.get(busPos).bookings.size(); i ++)
		{
			if(businesses.get(busPos).bookings.get(i).getCustUsername().equals(customers.get(custPos).getUsername()))
			{
				bookings.add(new TableViewBooking(businesses.get(busPos).bookings.get(i).getBookingID(), businesses.get(busPos).bookings.get(i).getSessionType(),
						businesses.get(busPos).bookings.get(i).getDate(), businesses.get(busPos).bookings.get(i).getCustomerName(), 
						businesses.get(busPos).bookings.get(i).getDayAsString(), businesses.get(busPos).bookings.get(i).getTimeslotAsString(),
						businesses.get(busPos).bookings.get(i).getEmployeeName()));
			}
		}
		
	    custBookingsTable.setItems(bookings);
	}
}
