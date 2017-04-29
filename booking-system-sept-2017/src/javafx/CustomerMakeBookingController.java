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

public class CustomerMakeBookingController implements Initializable{
    private ArrayList<Business> businesses;
    private ArrayList<Customer> customers;
    private int custPos;
    private int busPos;
    
//    private final ObservableList<TableViewBooking> bookings = FXCollections.observableArrayList();
    private final ObservableList<AvailableBookingTable> allAvailabilities = FXCollections.observableArrayList();
    private final ObservableList<AvailableBookingTable> displayedAvailabilities = FXCollections.observableArrayList();
    private final ObservableList<String> personalTrainer = FXCollections.observableArrayList();
    private final ObservableList<String> classType = FXCollections.observableArrayList();
    private final ObservableList<String> dayList = FXCollections.observableArrayList();
    private final ObservableList<String> timeList = FXCollections.observableArrayList();
   
    private String timesArray[] = new String[]{"8am - 9am", "9am - 10am", "10am - 11am", "11am - 12pm", "12pm - 1pm", "1pm - 2pm", "2pm - 3pm", "3pm - 4pm", "4pm - 5pm", "5pm - 6pm"};
    
    @FXML private ComboBox<String> classCombo = new ComboBox<String>();
    @FXML private ComboBox<String> personalTrainerCombo = new ComboBox<String>();
    @FXML private ComboBox<String> timeCombo = new ComboBox<String>();
    @FXML private ComboBox<String> dayCombo = new ComboBox<String>();
    
    @FXML private Label addBookingLabel;
    		
    @FXML private TableView<AvailableBookingTable> custAvailableBookingTable;    
    
    public CustomerMakeBookingController(ArrayList<Business> businesses, ArrayList<Customer> customers, int custPos)
    {
    	this.businesses = businesses;
    	this.customers = customers;
    	this.custPos = custPos;
    }
    
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
    	
    	public String getDate()
    	{
    		return date.get();
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

    	public LocalTime getStartTime()
    	{
    		return bookingStartTime;
    	}
    	
    	public LocalDate getLocalDate()
    	{
    		return localDate;
    	}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
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

    	for(int empPos = 0; empPos < businesses.get(busPos).employees.size(); empPos ++)
		{
    		for(int day = 0; day < businesses.get(busPos).employees.get(empPos).availableTimes.length; day++)
    		{    			
    			for(int timeslot = 0; timeslot < businesses.get(busPos).employees.get(empPos).availableTimes[day].length; timeslot++)
    			{
    				System.out.println("day " + day + " timeslot " + timeslot);
    				System.out.println(businesses.get(busPos).employees.get(empPos).availableTimes[day][timeslot]);
    				if(businesses.get(busPos).employees.get(empPos).availableTimes[day][timeslot] == 1)
    				{
    					
    					String dayString[] = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    					long daysToAdd = 0;
    					
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
    	custAvailableBookingTable.setItems(allAvailabilities);
	}
	
	public void handleSortAvailability(ActionEvent event)
	{
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
										System.out.println("first: " + allAvailabilities.get(count).getTime() + " Second " + allAvailabilities.get(count + 1).getTime());
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
			custAvailableBookingTable.setItems(displayedAvailabilities);
	}
	
	public void handleMakeBookingButtonAction(ActionEvent event)
	{
		AvailableBookingTable newSelection = custAvailableBookingTable.getSelectionModel().getSelectedItem();
		int day = 0, timeslot = 0;
		LocalDate date;
		boolean completed = false;
		Employee employee = null;
		String classType = classCombo.getValue();
		
		//if user does not select a class type it is set to general by default
		if(classType.equalsIgnoreCase("All"))
		{
			classType = "GENERAL";
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
		Booking newBooking = new Booking(generateBookingID(), classType, day, timeslot, date, completed, customers.get(custPos), employee);
		addBooking(businesses.get(busPos), newBooking);
		allAvailabilities.remove(newSelection);
		displayedAvailabilities.remove(newSelection);
		addBookingLabel.setText("Booked with " + employee.getName() + " at " + newSelection.getTime() + " on the " + date);
	}
	
	public String generateBookingID()
	{
		String bookingID = new String();
		int lastBooking = businesses.get(busPos).bookings.size() - 1;
		int nextBookingId = Integer.valueOf(businesses.get(busPos).bookings.get(lastBooking).getBookingID());
		nextBookingId++;
		
		bookingID = String.valueOf(nextBookingId);
		return bookingID;
	}
	
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
