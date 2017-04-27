package javafx;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.BusinessMakeBookingController.AvailableBookingTable;
import javafx.BusinessViewBookingsController.TableViewBooking;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import users.*;

public class CustomerMakeBookingController implements Initializable{
    private ArrayList<Business> businesses;
    private ArrayList<Customer> customers;
    private int busPos;
    
//    private final ObservableList<TableViewBooking> bookings = FXCollections.observableArrayList();
    private final ObservableList<AvailableBookingTable> allAvailabilities = FXCollections.observableArrayList();
    private final ObservableList<AvailableBookingTable> displayedAvailabilities = FXCollections.observableArrayList();
    private final ObservableList<String> personalTrainer = FXCollections.observableArrayList();
    private final ObservableList<String> classType = FXCollections.observableArrayList();
    private final ObservableList<String> dayList = FXCollections.observableArrayList();
    private final ObservableList<String> timeList = FXCollections.observableArrayList();
    
    @FXML private ComboBox<String> classCombo = new ComboBox<String>();
    @FXML private ComboBox<String> personalTrainerCombo = new ComboBox<String>();
    @FXML private ComboBox<String> timeCombo = new ComboBox<String>();
    @FXML private ComboBox<String> dayCombo = new ComboBox<String>();
    		
    @FXML private TableView<AvailableBookingTable> custAvailableBookingTable;    
    
    public CustomerMakeBookingController(ArrayList<Business> businesses, ArrayList<Customer> customers, int custPos)
    {
    	this.businesses = businesses;
    	this.customers = customers;
    	this.busPos = busPos;
    }
    
    public static class AvailableBookingTable 
    {
    	private final SimpleStringProperty date;
    	private final SimpleStringProperty day;
    	private final SimpleStringProperty time;
    	private final SimpleStringProperty employeeName;
    	
    	private AvailableBookingTable(LocalDate date, String day, String time, String employeeName)
    	{
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
    		this.date = new SimpleStringProperty(date.format(formatter));
    		this.day = new SimpleStringProperty(day);
    		this.time = new SimpleStringProperty(time);
    		this.employeeName = new SimpleStringProperty(employeeName);
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
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		LocalDate today = LocalDate.now();
		
		classType.add("All");
		classType.add("CROSSFIT");
		classType.add("WEIGHTS");
		classType.add("SPIN");
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
    		for(int timeslot = 0; timeslot < businesses.get(busPos).employees.get(empPos).availableTimes.length; timeslot++)
    		{    			
    			for(int day = 0; day < businesses.get(busPos).employees.get(empPos).availableTimes[timeslot].length; day++)
    			{
    				if(businesses.get(busPos).employees.get(empPos).availableTimes[timeslot][day] == 1)
    				{
    					//add availabilities
    					//check what day it is today and there for add right amount of days to todays date to make
    					//availabilities match with employee availabilities.
    					String dayString[] = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    					long daysToAdd = 0;
    					
    					if(today.getDayOfWeek().name().equalsIgnoreCase("MONDAY"))
    					{
    						daysToAdd = day;
    					}
    					else if(today.getDayOfWeek().name().equalsIgnoreCase("TUESDAY"))
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
    					else if(today.getDayOfWeek().name().equalsIgnoreCase("WEDNESDAY"))
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
    					else if(today.getDayOfWeek().name().equalsIgnoreCase("THURSDAY"))
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
    					else if(today.getDayOfWeek().name().equalsIgnoreCase("FRIDAY"))
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
    					else
    					{
    						continue;
    					}
						allAvailabilities.add(new AvailableBookingTable(today.plusDays(daysToAdd), dayString[day], 
									businesses.get(busPos).employees.get(empPos).getTimeSlotAsString(timeslot),
									businesses.get(busPos).employees.get(empPos).getName()));
    				}
    			}
    		}
//    		if(businesses.get(busPos).bookings.get(i).getDate().isAfter(today))
//				availabilities.add(new AvailableBookingTable(businesses.get(busPos).bookings.get(i).getBookingID(), businesses.get(busPos).bookings.get(i).getDate(),
//						businesses.get(busPos).bookings.get(i).getCustomerName(), businesses.get(busPos).bookings.get(i).getDayAsString(), businesses.get(busPos).bookings.get(i).getTimeslotAsString(),
//						businesses.get(busPos).bookings.get(i).getEmployeeName()));
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
		String timesArray[] = new String[]{"8am - 9am", "9am - 10am", "10am - 11am", "11am - 12pm", "12pm - 1pm", "1pm - 2pm", "2pm - 3pm", "3pm - 4pm", "4pm - 5pm", "5pm - 6pm"};
		for(int count = 0; count < allAvailabilities.size(); count++)
		{
			displayedAvailabilities.add(allAvailabilities.get(count));
		}
		
		//for each item in list check to see if it should still be in the list
			for(int count = displayedAvailabilities.size() - 1; count >= 0 ; count--)
				{
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
				}
			custAvailableBookingTable.setItems(displayedAvailabilities);
	}
}
