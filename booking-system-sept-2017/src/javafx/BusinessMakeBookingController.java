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
import main.Booking;
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
    
    private final ObservableList<AvailableBookingTable> allAvailabilities = FXCollections.observableArrayList();
    private final ObservableList<AvailableBookingTable> displayedAvailabilities = FXCollections.observableArrayList();
    private final ObservableList<String> personalTrainer = FXCollections.observableArrayList();
    private final ObservableList<String> classType = FXCollections.observableArrayList();
    private final ObservableList<String> dayList = FXCollections.observableArrayList();
    private final ObservableList<String> timeList = FXCollections.observableArrayList();
    private final ObservableList<String> customerList = FXCollections.observableArrayList();
    
    private String timesArray[] = new String[]{"8am - 9am", "9am - 10am", "10am - 11am", "11am - 12pm", "12pm - 1pm", "1pm - 2pm", "2pm - 3pm", "3pm - 4pm", "4pm - 5pm", "5pm - 6pm"};
    
    @FXML private TableView<AvailableBookingTable> busAvailableBookingTable;
    @FXML private ComboBox<String> classCombo = new ComboBox<String>();
    @FXML private ComboBox<String> personalTrainerCombo = new ComboBox<String>();
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
    public BusinessMakeBookingController(ArrayList<Business> businesses, ArrayList<Customer> customers, int busPos)
    {
    	this.businesses = businesses;
    	this.customers = customers;
    	this.busPos = busPos;
    }
    
    /**
     * Available bookings objects for the table view. Sets values to SimpleStringProperty to be displayed in TableView
     * @author Josh
     *
     */
    public static class AvailableBookingTable 
    {
    	private final SimpleStringProperty date;
    	private final SimpleStringProperty day;
    	private final SimpleStringProperty time;
    	private final SimpleStringProperty employeeName;
    	private LocalTime bookingStartTime;
    	private LocalDate localDate;
    	int hour;
    	int minute = 0; 
    	
    	/**
    	 * Constructor for new row in TableView
    	 * @param date passes LocalDate object and changes to String for displaying
    	 * @param day String of day of week
    	 * @param time String time slot for booking 
    	 * @param employeeName String of full employee's name
    	 */
    	private AvailableBookingTable(LocalDate date, String day, String time, String employeeName)
    	{
    		this.localDate = date;
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
    		this.date = new SimpleStringProperty(date.format(formatter));
    		this.day = new SimpleStringProperty(day);
    		this.time = new SimpleStringProperty(time);
    		this.employeeName = new SimpleStringProperty(employeeName);
    		
    		Matcher matcher = Pattern.compile("\\d+").matcher(time);
    		matcher.find();
    		this.hour = Integer.valueOf(matcher.group());
    		if(hour > 12)
    		{
    			hour += 12;
    		}
    		this.bookingStartTime = LocalTime.of(hour, minute);
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
    	
    	/**
    	 * get start time of the booking
    	 * @return Return objects start time
    	 */
    	public LocalTime getStartTime()
    	{
    		return bookingStartTime;
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
		classType.add("CROSSFIT 2HR");
		classType.add("WEIGHTS");
		classType.add("SPIN");
		classType.add("CARDIO 2HR");
		classCombo.setItems(classType);
		classCombo.setValue("All");
		
		personalTrainer.add("All");
		for(int empNo = 0; empNo < businesses.get(busPos).employees.size(); empNo++)
		{
			personalTrainer.add(businesses.get(busPos).employees.get(empNo).getName());
		}
		personalTrainerCombo.setItems(personalTrainer);
		personalTrainerCombo.setValue("All");
		
		timeList.add("All");
		timeList.add("Morning");
		timeList.add("Midday");
		timeList.add("Afternoon");
		timeList.add("Evening");
		timeCombo.setItems(timeList);
		timeCombo.setValue("All");
		
		dayList.add("All");
		dayList.add("Monday");
		dayList.add("Tuesday");
		dayList.add("Wednesday");
		dayList.add("Thursday");
		dayList.add("Friday");
		dayCombo.setItems(dayList);
		dayCombo.setValue("All");
		
		for(int i = 0; i < customers.size(); i++)
		{
			customerList.add(customers.get(i).getUsername() + " " + customers.get(i).getFullName());
		}
		customerCombo.setItems(customerList);
		
    	for(int empPos = 0; empPos < businesses.get(busPos).employees.size(); empPos ++)
		{
    		for(int day = 0; day < businesses.get(busPos).employees.get(empPos).availableTimes.length; day++)
    		{    			
    			for(int timeslot = 0; timeslot < businesses.get(busPos).employees.get(empPos).availableTimes[day].length; timeslot++)
    			{
    				if(businesses.get(busPos).employees.get(empPos).availableTimes[day][timeslot] == 1)
    				{
    					//add availabilities
    					//check what day it is today and there for add right amount of days to todays date to make
    					//availabilities match with employee availabilities.
    					String dayString[] = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    					long daysToAdd = 0;
    					
    					//find out how many days to add to current date
    					if(today.getDayOfWeek().name().equalsIgnoreCase(dayString[0]))
    					{
    						daysToAdd = day;
    					}
    					else if(today.getDayOfWeek().name().equalsIgnoreCase(dayString[1]))
    					{
    						if(day < 1)
    						{
    							daysToAdd = 6 + day;
    						}
    						else
    						{
    							daysToAdd = day - 1;
    						}
    					}
    					else if(today.getDayOfWeek().name().equalsIgnoreCase(dayString[2]))
    					{
    						if(day < 2)
    						{
    							daysToAdd = 5 + day;
    						}
    						else
    						{
    							daysToAdd = day - 2;
    						}
    					}
    					else if(today.getDayOfWeek().name().equalsIgnoreCase(dayString[3]))
    					{
    						if(day < 3)
    						{
    							daysToAdd = 4 + day;
    						}
    						else
    						{
    							daysToAdd = day - 3;
    						}
    					}
    					else if(today.getDayOfWeek().name().equalsIgnoreCase(dayString[4]))
    					{
    						if(day < 4)
    						{
    							daysToAdd = 3 + day;
    						}
    						else
    						{
    							daysToAdd = day - 4;
    						}
    					}
    					else if(today.getDayOfWeek().name().equalsIgnoreCase(dayString[5]))
    					{
    						daysToAdd = 2 + day;
    					}
    					else if(today.getDayOfWeek().name().equalsIgnoreCase(dayString[6]))
    					{
    						daysToAdd = 1 + day;
    					}
    					else
    					{
    						continue;
    					}
    					boolean booked = false;
    					//check if current time has a booking
    					for(int i = 0; i < businesses.get(busPos).bookings.size(); i++)
    					{
    						int startTime = businesses.get(busPos).bookings.get(i).getTimeslot();
    						int endTime = businesses.get(busPos).bookings.get(i).getEndTime();

    						String EmployeeName = businesses.get(busPos).bookings.get(i).getEmployeeName();
    						LocalDate bookingDate = businesses.get(busPos).bookings.get(i).getDate();
    						
    						if(timeslot == startTime && businesses.get(busPos).employees.get(empPos).getName().equalsIgnoreCase(EmployeeName) &&
    								today.plusDays(daysToAdd).equals(bookingDate))
    						{
    							booked = true;
    							if(endTime == timeslot + 1)
    							{
    								timeslot++;
    							}
    						}
    					}
    					if(!booked)
    					{
    						LocalTime timeNow = LocalTime.now();
    						AvailableBookingTable newBooking = new AvailableBookingTable(today.plusDays(daysToAdd), dayString[day], 
									businesses.get(busPos).employees.get(empPos).getTimeSlotAsString(timeslot),
									businesses.get(busPos).employees.get(empPos).getName());
    						
    						if(newBooking.getLocalDate().equals(today) && newBooking.getStartTime().isAfter(timeNow))
    						{
    							allAvailabilities.add(newBooking);
    						}
    						else if(newBooking.getLocalDate().isAfter(today))
    						{
    							allAvailabilities.add(newBooking);
    						}
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
		String personalTrainer = personalTrainerCombo.getValue();
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
					boolean doubleTimeslot = false;	
				
					String timeslot = "All";
					if(displayedAvailabilities.get(count).getTime().equalsIgnoreCase(timesArray[0]) || displayedAvailabilities.get(count).getTime().equalsIgnoreCase(timesArray[1])
							|| displayedAvailabilities.get(count).getTime().equalsIgnoreCase(timesArray[2]))
					{
						timeslot = "Morning";
					}
					else if(displayedAvailabilities.get(count).getTime().equalsIgnoreCase(timesArray[3]) || displayedAvailabilities.get(count).getTime().equalsIgnoreCase(timesArray[4]))
					{
						timeslot = "Midday";
					}
					else if(displayedAvailabilities.get(count).getTime().equalsIgnoreCase(timesArray[5]) || displayedAvailabilities.get(count).getTime().equalsIgnoreCase(timesArray[6])
							|| displayedAvailabilities.get(count).getTime().equalsIgnoreCase(timesArray[7]))
					{
						timeslot = "Afternoon";
					}
					else if(displayedAvailabilities.get(count).getTime().equalsIgnoreCase(timesArray[8]) || displayedAvailabilities.get(count).getTime().equalsIgnoreCase(timesArray[9]))
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
					else if(!classType.equalsIgnoreCase("All") && (classType.equalsIgnoreCase("CROSSFIT 2HR") || classType.equalsIgnoreCase("CARDIO 2HR")))
					{
						try{
							if(allAvailabilities.get(count).getEmployeeName().equalsIgnoreCase(allAvailabilities.get(count + 1).getEmployeeName()) 
									&& allAvailabilities.get(count).getDay().equalsIgnoreCase(allAvailabilities.get(count + 1).getDay()))
							{
								for(int i = 0; i < timesArray.length; i++)
								{
									if(timesArray[i].equalsIgnoreCase(allAvailabilities.get(count).getTime()) && timesArray[i + 1].equalsIgnoreCase(allAvailabilities.get(count + 1).getTime()))
									{
										doubleTimeslot = true;
										break;
									}
								}
							}
							}catch(IndexOutOfBoundsException e)
							{
								doubleTimeslot = false;
							}
						if(!doubleTimeslot)
						{
							displayedAvailabilities.remove(allAvailabilities.get(count));
						}
					}
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
		int day = 0, timeslot = 0;
		LocalDate date;
		boolean completed = false;
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
			String tokens[] = strDate.split("/");
			int dateDay = Integer.valueOf(tokens[0]);
			int dateMonth = Integer.valueOf(tokens[1]);
			int dateYear = Integer.valueOf(tokens[2]);
			date = LocalDate.of(dateYear, dateMonth, dateDay);
			
			for(int count = 0; count < timesArray.length; count++)
			{
				if(timesArray[count].equalsIgnoreCase(newSelection.getTime()))
				{
					timeslot = count;
				}
			}
			
			String dayArray[] = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
			for(int count = 0; count < dayArray.length; count++)
			{
				if(dayArray[count].equalsIgnoreCase(newSelection.getDay()))
				{
					day = count;
				}
			}
			
				Booking newBooking = new Booking(generateBookingID(), classType, day, timeslot, date, completed, selectedCustomer, employee);
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
		int nextBookingId = Integer.valueOf(businesses.get(busPos).bookings.get(lastBooking).getBookingID());
		nextBookingId++;
		
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
