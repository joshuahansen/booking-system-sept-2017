package javafx;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.AvailableTime;
import main.Booking;
import main.Session;
import users.*;

/**
 * BusinessMakeBookingController contains all the code used for the functionality of the BusinessMakeBooking.fxml file.
 * Any required values from previous controllers are passed in and set to local attributes to be used within the controller.
 * @author Josh
 *
 */
public class BusinessMakeBookingController implements Initializable{
    private ArrayList<Business> businesses;
    private ArrayList<Customer> customers;
    private int busPos;
    private Session session;
    
    private final ObservableList<AvailableBookingTable> allAvailabilities = FXCollections.observableArrayList();
    private final ObservableList<AvailableBookingTable> displayedAvailabilities = FXCollections.observableArrayList();
    private final ObservableList<String> employeeList = FXCollections.observableArrayList();
    private final ObservableList<String> classType = FXCollections.observableArrayList();
    private final ObservableList<String> dayList = FXCollections.observableArrayList();
    private final ObservableList<String> timeList = FXCollections.observableArrayList();
    private final ObservableList<String> customerList = FXCollections.observableArrayList();
    
    private String timesArray[] = new String[]{"8am - 9am", "9am - 10am", "10am - 11am", "11am - 12pm", "12pm - 1pm", "1pm - 2pm", "2pm - 3pm", "3pm - 4pm", "4pm - 5pm", "5pm - 6pm"};
    
    @FXML private TableView<AvailableBookingTable> busAvailableBookingTable;
    @FXML private ComboBox<String> classCombo = new ComboBox<String>();
    @FXML private ComboBox<String> employeeCombo = new ComboBox<String>();
    @FXML private ComboBox<String> timeCombo = new ComboBox<String>();
    @FXML private ComboBox<String> dayCombo = new ComboBox<String>();
    @FXML private ComboBox<String> customerCombo = new ComboBox<String>();
    
    @FXML private Label addBookingLabel;
    
    /**
     * Constructor for creating a new controller. Sets ArrayLists to local variables
     * @param businesses Passes reference to businesses array created in BookingSystem class
     * @param customers Passes reference to customers array created in BookingSystem class
     * @param busPos Passes value of logging in user and sets it to local variable
     */
    public BusinessMakeBookingController(Session session, ArrayList<Business> businesses, ArrayList<Customer> customers, int busPos)
    {
    	this.businesses = businesses;
    	this.customers = customers;
    	this.busPos = busPos;
    	this.session = session;
    }
    
    /**
     * Available bookings objects for the table view. Sets values to SimpleStringProperty to be displayed in TableView
     * @author Josh
     *
     */
    public static class AvailableBookingTable 
    {
    	private final SimpleStringProperty date;
    	
    	private final SimpleStringProperty employeeName;
    	private LocalDate localDate;
    	
    	private final AvailableTime availTime;
    	private final SimpleStringProperty day;
    	private final SimpleStringProperty time;
    	private final Employee employee;
    	/**
    	 * Constructor for new row in TableView
    	 * @param date passes LocalDate object and changes to String for displaying
    	 * @param day String of day of week
    	 * @param time String time slot for booking 
    	 * @param employeeName String of full employee's name
    	 */
    	private AvailableBookingTable(LocalDate date, LocalTime startTime, LocalTime endTime, String day, Employee employee)
    	{
    		this.localDate = date;
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
    		this.date = new SimpleStringProperty(date.format(formatter));
    		this.availTime = new AvailableTime(startTime, endTime, day);
    		this.day = new SimpleStringProperty(availTime.getDay());
    		this.time = new SimpleStringProperty(availTime.getTimeslotAsString());
    		this.employee = employee;
    		this.employeeName = new SimpleStringProperty(employee.getName());
    		
    	}
    	
    	/**
    	 * Getter for date value
    	 * @return Return objects date value
    	 */
    	public String getDate()
    	{
    		return date.get();
    	}
    	    	
    	/**
    	 * Getter for objects day
    	 * @return Return objects day value
    	 */
    	public String getDay()
    	{
    		return day.get();
    	}
    	
    	/**
    	 * Get time from object
    	 * @return Return objects time value of type String
    	 */
    	public String getTime()
    	{
    		return time.get();
    	}
    	
    	/**
    	 * Getter for employee name
    	 * @return Return objects employeeName value
    	 */
    	public String getEmployeeName()
    	{
    		return employeeName.get();
    	}
    	
    	public Employee getEmployee()
    	{
    		return employee;
    	}
    	/**
    	 * gets available time to make a booking
    	 * @return AvailableTime object
    	 */
    	public AvailableTime getAvailableTime()
    	{
    		return this.availTime;
    	}
    	
    	/**
    	 * Get Date of booking as a LocalDate object 
    	 * @return Return LocalDate object
    	 */
    	public LocalDate getLocalDate()
    	{
    		return localDate;
    	}
    }
    
    /**
     * Initialize page and populate dropdown menus.
     * Also load availabilities into an array to be displayed in the TableView
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		session.addLog("Initialize Make booking page");
		LocalDate today = LocalDate.now();
		
		classType.add("All");
		for(int classPos = 0; classPos < businesses.get(busPos).bookingTypes.size(); classPos++)
		{
			classType.add(businesses.get(busPos).bookingTypes.get(classPos).getBookingType());
		}
		classCombo.setItems(classType);
		classCombo.setValue("All");
		
		employeeList.add("All");
		for(int empNo = 0; empNo < businesses.get(busPos).employees.size(); empNo++)
		{
			employeeList.add(businesses.get(busPos).employees.get(empNo).getName());
		}
		employeeCombo.setItems(employeeList);
		employeeCombo.setValue("All");
		
		timeList.add("All");
		timeList.add("Morning");
		timeList.add("Afternoon");
		timeList.add("Evening");
		timeCombo.setItems(timeList);
		timeCombo.setValue("All");
		
		dayList.add("All");
		for(int pos = 0; pos < businesses.get(busPos).businessHours.size(); pos++)
		{
			dayList.add(businesses.get(busPos).businessHours.get(pos).getDay());
		}
		dayCombo.setItems(dayList);
		dayCombo.setValue("All");
		
		for(int i = 0; i < customers.size(); i++)
		{
			customerList.add(customers.get(i).getUsername() + " " + customers.get(i).getFullName());
		}
		customerCombo.setItems(customerList);
		
    	for(int empPos = 0; empPos < businesses.get(busPos).employees.size(); empPos ++)
		{
    		int smallestBooking = 0;
			for(int i = 0; i < businesses.get(busPos).bookingTypes.size(); i++)
			{
				smallestBooking = businesses.get(busPos).bookingTypes.get(i).getBookingLength();
				if(businesses.get(busPos).bookingTypes.get(i).getBookingLength() < smallestBooking)
				{
					smallestBooking = businesses.get(busPos).bookingTypes.get(i).getBookingLength();
				}
			}
			
    		Employee currentEmployee = businesses.get(busPos).employees.get(empPos);
    		for(int availTime = 0; availTime < currentEmployee.availableTimes.size(); availTime++)
    		{    
    			AvailableBookingTable newBooking = new AvailableBookingTable(today, 
    					currentEmployee.availableTimes.get(availTime).getStartTime(), currentEmployee.availableTimes.get(availTime).getStartTime().plusMinutes(smallestBooking), 
    					currentEmployee.availableTimes.get(availTime).getDay(), currentEmployee);
    			allAvailabilities.add(newBooking);
    			
    			while(newBooking.availTime.getEndTime().isBefore(currentEmployee.availableTimes.get(availTime).getEndTime()) )
    			{
    				newBooking = new AvailableBookingTable(today, 
    						newBooking.availTime.getEndTime(), newBooking.availTime.getEndTime().plusMinutes(smallestBooking), 
    					currentEmployee.availableTimes.get(availTime).getDay(), currentEmployee);
    				allAvailabilities.add(newBooking);
    			}
    		}
    		for(int availPos = 0; availPos < allAvailabilities.size(); availPos++)
    		{
    			AvailableBookingTable currentTime = allAvailabilities.get(availPos);
    			for(int bookingPos = 0; bookingPos < businesses.get(busPos).bookings.size(); bookingPos++)
    			{
    				Booking currentBooking = businesses.get(busPos).bookings.get(bookingPos);
    				if(currentBooking.getBookingTime().getDay().equals(currentTime.getAvailableTime().getDay()) &&
    						currentBooking.getDate().equals(currentTime.getLocalDate()))
    				{
	    				if(currentBooking.getBookingTime().getStartTime().isBefore(currentTime.getAvailableTime().getStartTime()) &&
	    						currentBooking.getBookingTime().getEndTime().isAfter(currentTime.getAvailableTime().getStartTime()))
	    				{
	    					allAvailabilities.remove(currentTime);
	    					availPos--;
	    					break;
	    				}
	    				else if(currentBooking.getBookingTime().equals(currentTime.getAvailableTime()))
	    				{
	    					allAvailabilities.remove(currentTime);
	    					availPos--;
	    					break;
	    				}
	    				else if((currentBooking.getBookingTime().getStartTime().equals(currentTime.getAvailableTime().getStartTime()) || 
	    						currentBooking.getBookingTime().getStartTime().isAfter(currentTime.getAvailableTime().getStartTime())) && 
	    						currentBooking.getBookingTime().getStartTime().isBefore(currentTime.getAvailableTime().getEndTime()))
	    				{
	    					allAvailabilities.remove(currentTime);
	    					availPos--;
	    					break;
	    				}
    				}
    			}
    		}
		}	
    	busAvailableBookingTable.setItems(allAvailabilities);
    	session.addLog("Display availabilities");
	}

	/**
	 * Each time a Combo box is changes in the GUI the availabilities displayed are changed 
	 * @param event Triggered when ComboBox is changed
	 */
	public void handleSortAvailability(ActionEvent event)
	{
		session.addLog("Sort availabilities");
		String classType = classCombo.getValue();
		String personalTrainer = employeeCombo.getValue();
		String time = timeCombo.getValue();
		String day = dayCombo.getValue();
				
		//clear current displayed list
		displayedAvailabilities.clear();
		for(int count = 0; count < allAvailabilities.size(); count++)
		{
			displayedAvailabilities.add(allAvailabilities.get(count));
		}
		
		//for each item in list check to see if it should still be in the list
			for(int count = displayedAvailabilities.size() - 1; count >= 0 ; count--)
				{
//					boolean doubleTimeslot = false;	
				
					String timeslot = "All";
					LocalTime midday = LocalTime.of(12, 00);
					LocalTime evening = LocalTime.of(17, 30);

					if(displayedAvailabilities.get(count).getAvailableTime().getEndTime().isBefore(midday) || displayedAvailabilities.get(count).getAvailableTime().getEndTime().equals(midday))
					{
						timeslot = "Morning";
					}
					else if((displayedAvailabilities.get(count).getAvailableTime().getStartTime().isAfter(midday) || displayedAvailabilities.get(count).getAvailableTime().getStartTime().equals(midday))
							&& (displayedAvailabilities.get(count).getAvailableTime().getEndTime().isBefore(evening) || displayedAvailabilities.get(count).getAvailableTime().getEndTime().equals(evening)))
					{
						timeslot = "Afternoon";
					}
					else if(displayedAvailabilities.get(count).getAvailableTime().getEndTime().isAfter(evening))
					{
						timeslot = "Evening";
					}
					
					if(!personalTrainer.equalsIgnoreCase("All") && !personalTrainer.equalsIgnoreCase(displayedAvailabilities.get(count).getEmployeeName()))
					{
						displayedAvailabilities.remove(displayedAvailabilities.get(count));
					}
					else if(!time.equalsIgnoreCase("All") && !time.equalsIgnoreCase(timeslot))
					{
						displayedAvailabilities.remove(allAvailabilities.get(count));
					}
					else if(!day.equalsIgnoreCase("All") && !day.equalsIgnoreCase(allAvailabilities.get(count).getDay()))
					{
						displayedAvailabilities.remove(allAvailabilities.get(count));
					}
//					else if(!classType.equalsIgnoreCase("All"))
//					else if(!classType.equalsIgnoreCase("All") && (classType.equalsIgnoreCase("CROSSFIT 2HR") || classType.equalsIgnoreCase("CARDIO 2HR")))
//					{
//						try{
//							if(allAvailabilities.get(count).getEmployeeName().equalsIgnoreCase(allAvailabilities.get(count + 1).getEmployeeName()) 
//									&& allAvailabilities.get(count).getDay().equalsIgnoreCase(allAvailabilities.get(count + 1).getDay()))
//							{
//								for(int i = 0; i < timesArray.length; i++)
//								{
//									if(timesArray[i].equalsIgnoreCase(allAvailabilities.get(count).getTime()) && timesArray[i + 1].equalsIgnoreCase(allAvailabilities.get(count + 1).getTime()))
//									{
//										doubleTimeslot = true;
//										break;
//									}
//								}
//							}
//							}catch(IndexOutOfBoundsException e)
//							{
//								doubleTimeslot = false;
//							}
//						if(!doubleTimeslot)
//						{
//							displayedAvailabilities.remove(allAvailabilities.get(count));
//						}
//					}
				}
			busAvailableBookingTable.setItems(displayedAvailabilities);
	}
	
	/**
	 * Controls what happens when the Make Booking Button is pressed.
	 * Gets values from the GUI that is selected by the user and sets them as the new booking values
	 * @param event Event from button being pressed
	 */
	public void handleMakeBookingButtonAction(ActionEvent event)
	{
		session.addLog("Make Booking Button Pressed");
		AvailableBookingTable newSelection = busAvailableBookingTable.getSelectionModel().getSelectedItem();
		LocalDate date;
		Employee employee = null;
		Customer selectedCustomer = null; 
		String classType = classCombo.getValue();

		//if user does not select a class type it is set to general by default
		if(classType.equalsIgnoreCase("All"))
		{
			classType = "GENERAL";
		}
		//if no customer is selected prompt user
		if(customerCombo.getValue() == null)
		{
			addBookingLabel.setText("Please select a Customer");
			
		}
		else if(newSelection == null)
		{
			addBookingLabel.setText("Please select a Booking from the table");
		}
		//otherwise create a booking
		else
		{
			String selectedCustomersValues[] = customerCombo.getValue().split(" ");
			for(int custPos = 0; custPos < customers.size(); custPos++)
			{
				if(customers.get(custPos).getUsername().equalsIgnoreCase(selectedCustomersValues[0]))
				{
					selectedCustomer = customers.get(custPos);
				}
			}
			
			for(int empPos = 0; empPos < businesses.get(busPos).employees.size(); empPos++)
			{
				if(businesses.get(busPos).employees.get(empPos).getName().equalsIgnoreCase(newSelection.getEmployeeName()))
				{
					employee = businesses.get(busPos).employees.get(empPos);
				}
			}
			
			String strDate = newSelection.getDate();
			String dateTokens[] = strDate.split("/");
			int dateDay = Integer.valueOf(dateTokens[0]);
			int dateMonth = Integer.valueOf(dateTokens[1]);
			int dateYear = Integer.valueOf(dateTokens[2]);
			date = LocalDate.of(dateYear, dateMonth, dateDay);
			
			String strTime = newSelection.getTime();
			String timeTokens[] = strTime.split("-");
			
			String startTimeTokens[] = timeTokens[0].split(":");
			int startHour = Integer.valueOf(startTimeTokens[0]);
			int startMin = Integer.valueOf(startTimeTokens[1]);
			LocalTime startTime = LocalTime.of(startHour, startMin);
			
			String endTimeTokens[] = timeTokens[1].split(":");
			int endHour = Integer.valueOf(endTimeTokens[0]);
			int endMin = Integer.valueOf(endTimeTokens[1]);
			LocalTime endTime = LocalTime.of(endHour, endMin);
					
			String day = newSelection.getDay();
			
				Booking newBooking = new Booking(generateBookingID(), classType, day, startTime, endTime, date, selectedCustomer, employee);
				addBooking(businesses.get(busPos), newBooking);
				allAvailabilities.remove(newSelection);
				displayedAvailabilities.remove(newSelection);
				addBookingLabel.setText("Booked with " + employee.getName() + " at " + newSelection.getTime() + " on the " + date);
		}
	}
	
	/**
	 * Generates the next BookingID using the last bookingID in the booking array  
	 * @return returns a String which is the bookingID
	 */
	public String generateBookingID()
	{
		String bookingID = new String();
		int lastBooking = businesses.get(busPos).bookings.size() - 1;
		int nextBookingId;
		if(lastBooking < 0)
		{
			nextBookingId = 1;
		}
		else
		{
			nextBookingId = Integer.valueOf(businesses.get(busPos).bookings.get(lastBooking).getBookingID());
			nextBookingId++;
		}
		
		bookingID = String.valueOf(nextBookingId);
		return bookingID;
	}
	
	/**
	 * Adds booking to the business's booking array
	 * @param business The business the booking is for.
	 * @param booking the booking being added
	 * @return true if booking is added otherwise return false
	 */
	public boolean addBooking(Business business, Booking booking)
	{
		boolean bookingFound = false;
		
		int numberOfBookings = business.bookings.size();
		int counter = 0;
		
		for (counter = 0; counter < numberOfBookings; counter++)
		{
			if (business.bookings.get(counter).equals(booking))
			{
				bookingFound = true;
			}
		}
		
		if (bookingFound == true)
		{
			return false;
		}
		else
		{
			business.bookings.add(booking);
			
			return true;
		}
	}
	
	/**
	 * Removes a booking from the business's booking array
	 * @param business Business from which the booking is being removed
	 * @param booking Booking that is being removed
	 * @return Return true if booking is removed else return false
	 */
	public boolean removeBooking(Business business, Booking booking)
	{
		int numberOfBookings = business.bookings.size();
		int counter = 0;
		
		for (counter = 0; counter < numberOfBookings; counter++)
		{
			if (business.bookings.get(counter).equals(booking))
			{
				business.bookings.remove(counter);
				
				return true;
			}
		}
		
		return false;
	}

}
