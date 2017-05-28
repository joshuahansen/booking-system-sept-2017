package javafx;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.AvailableTime;
import main.Booking;
import main.BookingType;
import main.Session;
import users.*;
/**
 * Controller for the customer make booking page. Handles displaying and updating the page with the correct information when the customer makes a booking.
 * @author SEPT Team 6
 *
 */
public class CustomerMakeBookingController implements Initializable{
    private Business business;
    private Customer customer;
    private Session session;
    private LocalTime midday = LocalTime.of(12, 00);
	private LocalTime evening = LocalTime.of(17, 00);
	private int smallestBooking;

    private final ObservableList<AvailableBookingTable> allAvailabilities = FXCollections.observableArrayList();
    private final ObservableList<AvailableBookingTable> displayedAvailabilities = FXCollections.observableArrayList();
    private final ObservableList<String> employeeList = FXCollections.observableArrayList();
    private final ObservableList<String> classType = FXCollections.observableArrayList();
    private final ObservableList<String> dayList = FXCollections.observableArrayList();
    private final ObservableList<String> timeList = FXCollections.observableArrayList();
   
    private String daysOfWeek[] = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    
    @FXML private ComboBox<String> classCombo = new ComboBox<String>();
    @FXML private ComboBox<String> employeeCombo = new ComboBox<String>();
    @FXML private ComboBox<String> timeCombo = new ComboBox<String>();
    @FXML private ComboBox<String> dayCombo = new ComboBox<String>();
    
    @FXML private Label addBookingLabel;
    		
    @FXML private TableView<AvailableBookingTable> custAvailableBookingTable;    
    /**
     * Constructor for the customer make booking controller
     * @param session for system runtime logging
     * @param business that the customer is making a booking with
     * @param customer customer that is making the booking
     */
    public CustomerMakeBookingController(Session session, Business business, Customer customer)
    {
    	this.business = business;
    	this.customer = customer;
    	this.session = session;
    }
    /**
     * object to display that available times for bookings
     * @author SEPT Team 6
     *
     */
    public static class AvailableBookingTable 
    {
    	private final SimpleStringProperty date;
    	
    	private final SimpleStringProperty employeeName;
    	private LocalDate localDate;
    	
    	private final AvailableTime availTime;
    	private final SimpleStringProperty day;
    	private SimpleStringProperty time;
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
    	 * set start time and end time of the available bookings
    	 * @param startTime LocalTime object
    	 * @param endTime LocalTime object
    	 */
    	public void setTime(LocalTime startTime, LocalTime endTime)
    	{
    		this.time = new SimpleStringProperty(startTime.toString() + "-" + endTime.toString());
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
    	 * get the employee that is available at this time
    	 * @return employee object
    	 */
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
     * initialize the table to display all available bookings
     * initialize combo boxes with the options to sort the available times
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		session.addLog("Initialize Make booking page");
		LocalDate today = LocalDate.now();
		
		classType.add("All");
		for(int classPos = 0; classPos < business.getBookingTypes().size(); classPos++)
		{
			classType.add(business.getBookingTypes().get(classPos).getBookingType());
		}
		classCombo.setItems(classType);
		classCombo.setValue("All");
		
		employeeList.add("All");
		for(int empNo = 0; empNo < business.getEmployees().size(); empNo++)
		{
			employeeList.add(business.getEmployees().get(empNo).getName());
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
		for(int pos = 0; pos < business.getBusinessHours().size(); pos++)
		{
			dayList.add(business.getBusinessHours().get(pos).getDay());
		}
		dayCombo.setItems(dayList);
		dayCombo.setValue("All");
		
		for(int empPos = 0; empPos < business.getEmployees().size(); empPos ++)
		{
    		smallestBooking = 0;
			for(int i = 0; i < business.getBookingTypes().size(); i++)
			{
				smallestBooking = business.getBookingTypes().get(i).getBookingLength();
				if(business.getBookingTypes().get(i).getBookingLength() < smallestBooking)
				{
					smallestBooking = business.getBookingTypes().get(i).getBookingLength();
				}
			}
			
    		Employee currentEmployee = business.getEmployees().get(empPos);
    		addAvailableTime(currentEmployee, today, smallestBooking);
   		
    		removeBookedTimes();

		}	
    	custAvailableBookingTable.setItems(allAvailabilities);
    	session.addLog("Display availabilities");
	}
	/**
	 * Handle sort availabilities action. 
	 * Sort the available times depending on what the user has selected with the drop down combo boxes
	 * @param event
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
					String timeslot = "All";

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
					else if(!classType.equalsIgnoreCase("All"))
					{
						BookingType newBookingType = null;
						for(int i = 0; i < business.getBookingTypes().size(); i++)
						{
							if(classType.equalsIgnoreCase(business.getBookingTypes().get(i).getBookingType()))
							{
								newBookingType = business.getBookingTypes().get(i);
								break;
							}
						}
						if(newBookingType != null)
						{	
							int ratio = newBookingType.getBookingLength()/smallestBooking;
							ratio -= 1;
							if(ratio == 0)
							{
								continue;
							}
			
							if(count + ratio >= allAvailabilities.size())
							{
								displayedAvailabilities.remove(allAvailabilities.get(count));
							}
							else if(displayedAvailabilities.get(count).getAvailableTime().getDay().equalsIgnoreCase(allAvailabilities.get(count + ratio).getAvailableTime().getDay()) &&
									displayedAvailabilities.get(count).getEmployee().equals(allAvailabilities.get(count + ratio).getEmployee()))
							{
								if(displayedAvailabilities.get(count).getAvailableTime().getStartTime().plusMinutes(newBookingType.getBookingLength())
										.equals(allAvailabilities.get(count + ratio).getAvailableTime().getEndTime()))
								{
									//change the time displayed to the full length of booking. when lists is cleared does not return to default value
//									displayedAvailabilities.get(count).setTime(displayedAvailabilities.get(count).getAvailableTime().getStartTime(), 
//											displayedAvailabilities.get(count).getAvailableTime().getStartTime().plusMinutes(newBookingType.getBookingLength()));
								}
								else
									displayedAvailabilities.remove(allAvailabilities.get(count));
							}
							else
							{
								displayedAvailabilities.remove(allAvailabilities.get(count));
							}
								
						}
							
					}
				}
			custAvailableBookingTable.setItems(displayedAvailabilities);
	}
	/**
	 * Handle the make booking action button
	 * Gets the selected booking time and create a booking with the selected values
	 * @param event
	 */
	public void handleMakeBookingButtonAction(ActionEvent event)
	{
		session.addLog("Make Booking Button Pressed");
		AvailableBookingTable newSelection = custAvailableBookingTable.getSelectionModel().getSelectedItem();
		LocalDate date;
		Employee employee = null;
		String classType = classCombo.getValue();

		//if user does not select a class type it is set to general by default
		if(classType.equalsIgnoreCase("All"))
		{
			classType = "GENERAL";
		}
		else if(newSelection == null)
		{
			addBookingLabel.setText("Please select a Booking from the table");
		}
		//otherwise create a booking
		else
		{		
			for(int empPos = 0; empPos < business.getEmployees().size(); empPos++)
			{
				if(business.getEmployees().get(empPos).getName().equalsIgnoreCase(newSelection.getEmployeeName()))
				{
					employee = business.getEmployees().get(empPos);
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
			
				Booking newBooking = new Booking(generateBookingID(), classType, day, startTime, endTime, date, customer, employee);
				addBooking(business, newBooking);
				allAvailabilities.remove(newSelection);
				displayedAvailabilities.remove(newSelection);
				addBookingLabel.setText("Booked with " + employee.getName() + " at " + newSelection.getTime() + " on the " + date);
		}
	}
	/**
	 * generate a new booking Id for the new booking
	 * @return String of the booking Id that was generated
	 */
	public String generateBookingID()
	{
		String bookingID = new String();
		int nextBookingId;
		int lastBooking;
		
		if(business.getBookings().size() != 0)
		{
			lastBooking = business.getBookings().size() - 1;
			nextBookingId = Integer.valueOf(business.getBookings().get(lastBooking).getBookingID());
			nextBookingId++;
		}
		else
		{
			nextBookingId = 1;
		}
		bookingID = String.valueOf(nextBookingId);
		return bookingID;
	}
	/**
	 * add the booking to the business
	 * @param business business the booking was made with
	 * @param booking object that is being added
	 * @return true if booking was added else false
	 */
	public boolean addBooking(Business business, Booking booking)
	{
		boolean bookingFound = false;
		
		int numberOfBookings = business.getBookings().size();
		int counter = 0;
		
		for (counter = 0; counter < numberOfBookings; counter++)
		{
			if (business.getBookings().get(counter).equals(booking))
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
			business.getBookings().add(booking);
			
			return true;
		}
	}
	/**
	 * remove a booking
	 * @param business whose booking is being removed 
	 * @param booking that is being removed
	 * @return return true if booking was removed
	 */
	public boolean removeBooking(Business business, Booking booking)
	{
		int numberOfBookings = business.getBookings().size();
		int counter = 0;
		
		for (counter = 0; counter < numberOfBookings; counter++)
		{
			if (business.getBookings().get(counter).equals(booking))
			{
				business.getBookings().remove(counter);
				
				return true;
			}
		}
		
		return false;
	}
	/**
	 * get number of days to add to current date
	 * @param today current date
	 * @param availTime available time which date needs to be calculated for
	 * @return integer of number of days needed to be added
	 */
	public int getDaysToAdd(LocalDate today, AvailableTime availTime)
	{
		int dayNumber, currentDay;
		for(currentDay = 0; currentDay < daysOfWeek.length; currentDay++)
		{
			if(daysOfWeek[currentDay].equalsIgnoreCase(today.getDayOfWeek().toString()))
			{	
				break;
			}
		}
		for(dayNumber = 0; 0 < daysOfWeek.length; dayNumber++)
		{
			if(availTime.getDay().equalsIgnoreCase(daysOfWeek[dayNumber]))
				break;
		}

		int addDays = dayNumber - currentDay;
		if(addDays < 0)
		{
			addDays += 7;
		}
		return addDays;
	}
	/**
	 * remove a booking time from the table
	 * used when a new booking is made to avoid double bookings 
	 */
	public void removeBookedTimes()
	{
		//remove times if there is a booking during this time
		for(int availPos = 0; availPos < allAvailabilities.size(); availPos++)
		{
			AvailableBookingTable currentTime = allAvailabilities.get(availPos);
			for(int bookingPos = 0; bookingPos < business.getBookings().size(); bookingPos++)
			{
				Booking currentBooking = business.getBookings().get(bookingPos);
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
	/**
	 * add a booking availabilities to be displayed
	 * @param currentEmployee that the times are getting availabilities of
	 * @param today todays date
	 * @param smallestBooking smallest booking type of business
	 */
	public void addAvailableTime(Employee currentEmployee, LocalDate today, int smallestBooking)
	{
		for(int availTime = 0; availTime < currentEmployee.availableTimes.size(); availTime++)
		{   
			int addDays = getDaysToAdd(today, currentEmployee.availableTimes.get(availTime));

			AvailableBookingTable newBooking = new AvailableBookingTable(today.plusDays(addDays), 
					currentEmployee.availableTimes.get(availTime).getStartTime(), currentEmployee.availableTimes.get(availTime).getStartTime().plusMinutes(smallestBooking), 
					currentEmployee.availableTimes.get(availTime).getDay(), currentEmployee);
			allAvailabilities.add(newBooking);
				
			while(newBooking.availTime.getEndTime().isBefore(currentEmployee.availableTimes.get(availTime).getEndTime()) )
			{
				if(newBooking.availTime.getEndTime().plusMinutes(smallestBooking).isAfter(currentEmployee.availableTimes.get(availTime).getEndTime()))
				{
					break;
				}
				else
				{
					newBooking = new AvailableBookingTable(today.plusDays(addDays), 
						newBooking.availTime.getEndTime(), newBooking.availTime.getEndTime().plusMinutes(smallestBooking), 
						currentEmployee.availableTimes.get(availTime).getDay(), currentEmployee);
					allAvailabilities.add(newBooking);
				}
			}
		}
	}
}
