package javafx;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import main.Session;
import users.*;
/**
 * Controller to display customer bookings
 * @author SEPT Team 6
 *
 */
public class CustViewBookingsController implements Initializable{
    private Customer customer;
    private Business business;
    private Session session;
    
    private final ObservableList<TableViewBooking> allBookings = FXCollections.observableArrayList();
    private final ObservableList<TableViewBooking> displayBookings = FXCollections.observableArrayList();
    private final ObservableList<String> bookingTime = FXCollections.observableArrayList();
    		
    @FXML private TableView<TableViewBooking> custBookingsTable;    
    @FXML private ComboBox<String> bookingCombo = new ComboBox<String>();
    
    /**
     * Constructor for Customer view bookings controller
     * @param session for system runtime logging
     * @param customer log in customer
     * @param business customer is making a booking with
     */
    public CustViewBookingsController(Session session, Customer customer, Business business)
    {
    	this.customer = customer;
    	this.business = business;;
    	this.session = session;
    }
    /**
     * Objects of bookings that are displaying in the table
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
    	private LocalDate localDate;
    	
    	/**
    	 * Constructor for bookings
    	 * @param bookingId unique booking Id
    	 * @param sessionType booking type
    	 * @param date of booking
    	 * @param customerName of customer the booking is for
    	 * @param day of the booking
    	 * @param time of the booking
    	 * @param employeeName the booking is with
    	 */
    	private TableViewBooking(String bookingId, String sessionType, LocalDate date, String customerName, String day, String time, String employeeName)
    	{
    		this.bookingId = new SimpleStringProperty(bookingId);
    		this.sessionType = new SimpleStringProperty(sessionType);
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
    		this.date = new SimpleStringProperty(date.format(formatter));
    		this.localDate = date;
    		this.customerName = new SimpleStringProperty(customerName);
    		this.day = new SimpleStringProperty(day);
    		this.time = new SimpleStringProperty(time);
    		this.employeeName = new SimpleStringProperty(employeeName);
    	}
    	/**
    	 * get booking id
    	 * @return bookingId as String
    	 */
    	public String getBookingId()
    	{
    		return bookingId.get();
    	}
    	/**
    	 * get booking type
    	 * @return sessionType as String
    	 */
    	public String getSessionType()
    	{
    		return sessionType.get();
    	}
    	/**
    	 * get date of booking
    	 * @return date as String
    	 */
    	public String getDate()
    	{
    		return date.get();
    	}
    	/**
    	 * get customer name
    	 * @return customerName as String
    	 */
    	public String getCustomerName()
    	{
    		return customerName.get();
    	}
    	/**
    	 * get day of booking
    	 * @return day as String
    	 */
    	public String getDay()
    	{
    		return day.get();
    	}
    	/**
    	 * get time of booking
    	 * @return time as String
    	 */
    	public String getTime()
    	{
    		return time.get();
    	}
    	/**
    	 * get Employee name from booking
    	 * @return employeeName as String
    	 */
    	public String getEmployeeName()
    	{
    		return employeeName.get();
    	}
    	/**
    	 * get local date of the booking
    	 * @return localDate as LocalDate
    	 */
    	public LocalDate getLocalDate()
    	{
    		return localDate;
    	}
    }
    /**
     * initialize table with bookings from current day.
     * initialize combo box with past today and future booking options to sort customers bookings
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		session.addLog("Custoemr view bookings");
		for(int i = 0; i < business.getBookings().size(); i ++)
		{
			if(business.getBookings().get(i).getCustUsername().equals(customer.getUsername()))
			{
				allBookings.add(new TableViewBooking(business.getBookings().get(i).getBookingID(), business.getBookings().get(i).getSessionType(),
						business.getBookings().get(i).getDate(), business.getBookings().get(i).getCustomerName(), 
						business.getBookings().get(i).getBookingTime().getDay(), business.getBookings().get(i).getBookingTime().getTimeslotAsString(),
						business.getBookings().get(i).getEmployeeName()));
			}
		}
		
		LocalDate today = LocalDate.now();
		
		for(int i = 0; i < allBookings.size(); i ++)
		{
    		if(allBookings.get(i).getLocalDate().equals(today))
    		{
				displayBookings.add(allBookings.get(i));
    		}
		}
		
	    custBookingsTable.setItems(displayBookings);
	    
	    bookingTime.add("All");
		bookingTime.add("Past");
		bookingTime.add("Today");
		bookingTime.add("Future");
		bookingCombo.setItems(bookingTime);
		bookingCombo.setValue("Today");
	}
	/**
	 * Handle the sorting of the bookings when the user selects an option from the drop down
	 * @param event
	 */
	@FXML protected void handleSortBookings(ActionEvent event)
	{
		session.addLog("sort bookings");
		displayBookings.clear();
		String bookingTime = bookingCombo.getValue();
		session.addLog(bookingTime);
		LocalDate today = LocalDate.now();
    	
		if(bookingTime.equalsIgnoreCase("Today"))
		{
			for(int i = 0; i < allBookings.size(); i ++)
			{
	    		if(allBookings.get(i).getLocalDate().equals(today))
	    		{
					displayBookings.add(allBookings.get(i));
	    		}
			}
		}
		else if(bookingTime.equalsIgnoreCase("Past"))
		{
			for(int i = 0; i < allBookings.size(); i ++)
			{
	    		if(allBookings.get(i).getLocalDate().isBefore(today))
	    		{
					displayBookings.add(allBookings.get(i));
	    		}
			}
		}
		else if(bookingTime.equalsIgnoreCase("Future"))
		{
			for(int i = 0; i < allBookings.size(); i ++)
			{
	    		if(allBookings.get(i).getLocalDate().isAfter(today))
	    		{
					displayBookings.add(allBookings.get(i));
	    		}
			}
		}
		else
		{
			for(int i = 0; i < allBookings.size(); i++)
			{
				displayBookings.add(allBookings.get(i));
			}
		}
		
		custBookingsTable.setItems(displayBookings);
	}
}