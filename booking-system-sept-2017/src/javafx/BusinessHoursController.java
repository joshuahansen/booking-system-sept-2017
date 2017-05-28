package javafx;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import main.*;
import users.*;

/**
 * Controller for business hours page. 
 * Allows business to update there open hours 
 * @author SEPT Team 6
 *
 */
public class BusinessHoursController implements Initializable {

	private Business business;
	private Session session;
	
	private final ObservableList<Integer> hours = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
	private final ObservableList<Integer> minutes = FXCollections.observableArrayList(0, 15, 30, 45);	
	@FXML ComboBox<Integer> mondayStartHour;
	@FXML ComboBox<Integer> mondayStartMin;
	@FXML ComboBox<Integer> mondayEndHour;
	@FXML ComboBox<Integer> mondayEndMin;
	
	@FXML ComboBox<Integer> tuesdayStartHour;
	@FXML ComboBox<Integer> tuesdayStartMin;
	@FXML ComboBox<Integer> tuesdayEndHour;
	@FXML ComboBox<Integer> tuesdayEndMin;
	
	@FXML ComboBox<Integer> wednesdayStartHour;
	@FXML ComboBox<Integer> wednesdayStartMin;
	@FXML ComboBox<Integer> wednesdayEndHour;
	@FXML ComboBox<Integer> wednesdayEndMin;
	
	@FXML ComboBox<Integer> thursdayStartHour;
	@FXML ComboBox<Integer> thursdayStartMin;
	@FXML ComboBox<Integer> thursdayEndHour;
	@FXML ComboBox<Integer> thursdayEndMin;
	
	@FXML ComboBox<Integer> fridayStartHour;
	@FXML ComboBox<Integer> fridayStartMin;
	@FXML ComboBox<Integer> fridayEndHour;
	@FXML ComboBox<Integer> fridayEndMin;
	
	@FXML ComboBox<Integer> saturdayStartHour;
	@FXML ComboBox<Integer> saturdayStartMin;
	@FXML ComboBox<Integer> saturdayEndHour;
	@FXML ComboBox<Integer> saturdayEndMin;
	
	@FXML ComboBox<Integer> sundayStartHour;
	@FXML ComboBox<Integer> sundayStartMin;
	@FXML ComboBox<Integer> sundayEndHour;
	@FXML ComboBox<Integer> sundayEndMin;
	
	@FXML Button openHours;
	@FXML Text openHoursNotify;
	
	@FXML CheckBox mondayClosed;
	@FXML CheckBox tuesdayClosed;
	@FXML CheckBox wednesdayClosed;
	@FXML CheckBox thursdayClosed;
	@FXML CheckBox fridayClosed;
	@FXML CheckBox saturdayClosed;
	@FXML CheckBox sundayClosed;
	
	@FXML Button mondayStartAmPmButton;
	@FXML Button mondayEndAmPmButton;
	@FXML Button tuesdayStartAmPmButton;
	@FXML Button tuesdayEndAmPmButton;
	@FXML Button wednesdayStartAmPmButton;
	@FXML Button wednesdayEndAmPmButton;
	@FXML Button thursdayStartAmPmButton;
	@FXML Button thursdayEndAmPmButton;
	@FXML Button fridayStartAmPmButton;
	@FXML Button fridayEndAmPmButton;
	@FXML Button saturdayStartAmPmButton;
	@FXML Button saturdayEndAmPmButton;
	@FXML Button sundayStartAmPmButton;
	@FXML Button sundayEndAmPmButton;
	
	/**
	 * Constructor for business hours controller.
	 * @param session used for session logging
	 * @param business current logged in business
	 */
	public BusinessHoursController(Session session, Business business)
	{
		this.business = business;
		this.session = session;
	}
	
	/**
	 * Initializes business hours page.
	 * Sets combo box items.
	 * Loads current business hours for user to view and update
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mondayStartHour.setItems(hours);
		mondayStartMin.setItems(minutes);
		mondayEndHour.setItems(hours);
		mondayEndMin.setItems(minutes);
		
		tuesdayStartHour.setItems(hours);
		tuesdayStartMin.setItems(minutes);
		tuesdayEndHour.setItems(hours);
		tuesdayEndMin.setItems(minutes);
		
		wednesdayStartHour.setItems(hours);
		wednesdayStartMin.setItems(minutes);
		wednesdayEndHour.setItems(hours);
		wednesdayEndMin.setItems(minutes);
		
		thursdayStartHour.setItems(hours);
		thursdayStartMin.setItems(minutes);
		thursdayEndHour.setItems(hours);
		thursdayEndMin.setItems(minutes);
		
		fridayStartHour.setItems(hours);
		fridayStartMin.setItems(minutes);
		fridayEndHour.setItems(hours);
		fridayEndMin.setItems(minutes);
		
		saturdayStartHour.setItems(hours);
		saturdayStartMin.setItems(minutes);
		saturdayEndHour.setItems(hours);
		saturdayEndMin.setItems(minutes);
		
		sundayStartHour.setItems(hours);
		sundayStartMin.setItems(minutes);;
		sundayEndHour.setItems(hours);
		sundayEndMin.setItems(minutes);
		
		boolean mondaySet = false, tuesdaySet = false, wednesdaySet = false, thursdaySet = false, fridaySet = false,
				saturdaySet = false, sundaySet = false;
		if(!business.getBusinessHours().isEmpty())
		{
			for(int i = 0; i < business.getBusinessHours().size(); i++)
			{
				AvailableTime businessHour = business.getBusinessHours().get(i);
				if(businessHour.getDay().equalsIgnoreCase("MONDAY"))
				{
					mondayStartHour.setValue(businessHour.getStartTime().getHour());
					mondayStartMin.setValue(businessHour.getStartTime().getMinute());
					if(businessHour.getStartTime().getHour() > 11)
						mondayStartAmPmButton.setText("PM");
					mondayEndHour.setValue(businessHour.getEndTime().getHour());
					mondayEndMin.setValue(businessHour.getEndTime().getMinute());
					if(businessHour.getEndTime().getHour() < 12)
						mondayEndAmPmButton.setText("AM");
					mondaySet = true;
				}
				if(businessHour.getDay().equalsIgnoreCase("TUESDAY"))
				{
					tuesdayStartHour.setValue(businessHour.getStartTime().getHour());
					tuesdayStartMin.setValue(businessHour.getStartTime().getMinute());
					if(businessHour.getStartTime().getHour() > 11)
						tuesdayStartAmPmButton.setText("PM");
					tuesdayEndHour.setValue(businessHour.getEndTime().getHour());
					tuesdayEndMin.setValue(businessHour.getEndTime().getMinute());
					if(businessHour.getEndTime().getHour() < 12)
						tuesdayEndAmPmButton.setText("AM");
					tuesdaySet = true;
				}
				if(businessHour.getDay().equalsIgnoreCase("WEDNESDAY"))
				{
					wednesdayStartHour.setValue(businessHour.getStartTime().getHour());
					wednesdayStartMin.setValue(businessHour.getStartTime().getMinute());
					if(businessHour.getStartTime().getHour() > 11)
						wednesdayStartAmPmButton.setText("PM");
					wednesdayEndHour.setValue(businessHour.getEndTime().getHour());
					wednesdayEndMin.setValue(businessHour.getEndTime().getMinute());
					if(businessHour.getEndTime().getHour() < 12)
						wednesdayEndAmPmButton.setText("AM");
					wednesdaySet = true;
				}
				if(businessHour.getDay().equalsIgnoreCase("THURSDAY"))
				{
					thursdayStartHour.setValue(businessHour.getStartTime().getHour());
					thursdayStartMin.setValue(businessHour.getStartTime().getMinute());
					if(businessHour.getStartTime().getHour() > 11)
						thursdayStartAmPmButton.setText("PM");
					thursdayEndHour.setValue(businessHour.getEndTime().getHour());
					thursdayEndMin.setValue(businessHour.getEndTime().getMinute());
					if(businessHour.getEndTime().getHour() < 12)
						thursdayEndAmPmButton.setText("AM");
					thursdaySet = true;
				}
				if(businessHour.getDay().equalsIgnoreCase("FRIDAY"))
				{
					fridayStartHour.setValue(businessHour.getStartTime().getHour());
					fridayStartMin.setValue(businessHour.getStartTime().getMinute());
					if(businessHour.getStartTime().getHour() > 11)
						fridayStartAmPmButton.setText("PM");
					fridayEndHour.setValue(businessHour.getEndTime().getHour());
					fridayEndMin.setValue(businessHour.getEndTime().getMinute());
					if(businessHour.getEndTime().getHour() < 12)
						fridayEndAmPmButton.setText("AM");
					fridaySet = true;
				}
				if(businessHour.getDay().equalsIgnoreCase("SATURDAY"))
				{
					saturdayStartHour.setValue(businessHour.getStartTime().getHour());
					saturdayStartMin.setValue(businessHour.getStartTime().getMinute());
					if(businessHour.getStartTime().getHour() > 11)
						saturdayStartAmPmButton.setText("PM");
					saturdayEndHour.setValue(businessHour.getEndTime().getHour());
					saturdayEndMin.setValue(businessHour.getEndTime().getMinute());
					if(businessHour.getEndTime().getHour() < 12)
						saturdayEndAmPmButton.setText("AM");
					saturdaySet = true;
				}
				if(businessHour.getDay().equalsIgnoreCase("SUNDAY"))
				{
					sundayStartHour.setValue(businessHour.getStartTime().getHour());
					sundayStartMin.setValue(businessHour.getStartTime().getMinute());
					if(businessHour.getStartTime().getHour() > 11)
						sundayStartAmPmButton.setText("PM");
					sundayEndHour.setValue(businessHour.getEndTime().getHour());
					sundayEndMin.setValue(businessHour.getEndTime().getMinute());
					if(businessHour.getEndTime().getHour() < 12)
						sundayEndAmPmButton.setText("AM");
					sundaySet = true;
				}
			}
		}
			if(!mondaySet)
			{
				mondayClosed.setSelected(true);
				disableRow(mondayStartHour, mondayStartMin, mondayStartAmPmButton, mondayEndHour, mondayEndMin, mondayEndAmPmButton, mondayClosed);	
			}
			if(!tuesdaySet)
			{
				tuesdayClosed.setSelected(true);
				disableRow(tuesdayStartHour, tuesdayStartMin, tuesdayStartAmPmButton, tuesdayEndHour, tuesdayEndMin, tuesdayEndAmPmButton, tuesdayClosed);
			}
			if(!wednesdaySet)
			{
				wednesdayClosed.setSelected(true);
				disableRow(wednesdayStartHour, wednesdayStartMin, wednesdayStartAmPmButton, wednesdayEndHour, wednesdayEndMin, wednesdayEndAmPmButton, wednesdayClosed);
			}
			if(!thursdaySet)
			{
				thursdayClosed.setSelected(true);
				disableRow(thursdayStartHour, thursdayStartMin, thursdayStartAmPmButton, thursdayEndHour, thursdayEndMin, thursdayEndAmPmButton, thursdayClosed);
			}
			if(!fridaySet)
			{
				fridayClosed.setSelected(true);
				disableRow(fridayStartHour, fridayStartMin, fridayStartAmPmButton, fridayEndHour, fridayEndMin, fridayEndAmPmButton, fridayClosed);
			}
			if(!saturdaySet)
			{
				saturdayClosed.setSelected(true);
				disableRow(saturdayStartHour, saturdayStartMin, saturdayStartAmPmButton, saturdayEndHour, saturdayEndMin, saturdayEndAmPmButton, saturdayClosed);
			}
			if(!sundaySet)
			{
				sundayClosed.setSelected(true);
				disableRow(sundayStartHour, sundayStartMin, sundayStartAmPmButton, sundayEndHour, sundayEndMin, sundayEndAmPmButton, sundayClosed);
			}
	}

	/**
	 * Gets all values from the fields and updates the business hours.
	 * @param event
	 */
 	public void handleOpenHoursButtonAction(ActionEvent event)
	{
		getMondayTime();
		getTuesdayTime();
		getWednesdayTime();
		getThursdayTime();
		getFridayTime();
		getSaturdayTime();
		getSundayTime();
		
		openHoursNotify.setText("Business Hours Updated");
	}
 	
 	/**
 	 * Generic method for changing the button text.
 	 * @param button passed into method to change text. 
 	 */
	public void changeButtonText(Button button)
	{
		if(button.getText().equalsIgnoreCase("AM"))
			button.setText("PM");
		else
			button.setText("AM");
	}
	/**
	 * Change Monday start time button text
	 * @param event
	 */
 	public void changeMondayStartAmPm(ActionEvent event)
 	{
 		changeButtonText(mondayStartAmPmButton);
 	}
 	/**
 	 * Change Monday end time button text
 	 * @param event
 	 */
 	public void changeMondayEndAmPm(ActionEvent event)
 	{
 		changeButtonText(mondayEndAmPmButton);
 	}
 	/**
 	 * change Tuesday start time button text
 	 * @param event
 	 */
 	public void changeTuesdayStartAmPm(ActionEvent event)
 	{
 		changeButtonText(tuesdayStartAmPmButton);
 	}
 	/**
 	 * change Tuesday end time button text
 	 * @param event
 	 */
 	public void changeTuesdayEndAmPm(ActionEvent event)
 	{
 		changeButtonText(tuesdayEndAmPmButton);
 	}
 	/**
 	 * change Wednesday start time button text
 	 * @param event
 	 */
 	public void changeWednesdayStartAmPm(ActionEvent event)
 	{
 		changeButtonText(wednesdayStartAmPmButton);
 	}
 	/**
 	 * change Wednesday end time button text
 	 * @param event
 	 */
 	public void changeWednesdayEndAmPm(ActionEvent event)
 	{
 		changeButtonText(wednesdayEndAmPmButton);
 	}
 	/**
 	 * change Thursday start time button text.
 	 * @param event
 	 */
 	public void changeThursdayStartAmPm(ActionEvent event)
 	{
 		changeButtonText(thursdayStartAmPmButton);
 	}
 	/**
 	 * change Thursday end time button text
 	 * @param event
 	 */
 	public void changeThursdayEndAmPm(ActionEvent event)
 	{
 		changeButtonText(thursdayEndAmPmButton);
 	}
 	/**
 	 * change Friday start time button text
 	 * @param event
 	 */
 	public void changeFridayStartAmPm(ActionEvent event)
 	{
 		changeButtonText(fridayStartAmPmButton);
 	}
 	/**
 	 * change Friday end time button text
 	 * @param event
 	 */
 	public void changeFridayEndAmPm(ActionEvent event)
 	{
 		changeButtonText(fridayEndAmPmButton);
 	}
 	/**
 	 * change Saturday start time button text
 	 * @param event
 	 */
 	public void changeSaturdayStartAmPm(ActionEvent event)
 	{
 		changeButtonText(saturdayStartAmPmButton);
 	}
 	/**
 	 * change Saturday end time button text
 	 * @param event
 	 */
 	public void changeSaturdayEndAmPm(ActionEvent event)
 	{
 		changeButtonText(saturdayEndAmPmButton);
 	}
 	/**
 	 * change Sunday start time button text
 	 * @param event
 	 */
 	public void changeSundayStartAmPm(ActionEvent event)
 	{
 		changeButtonText(sundayStartAmPmButton);
 	}
 	/**
 	 * change Sunday end time button text
 	 * @param event
 	 */
 	public void changeSundayEndAmPm(ActionEvent event)
 	{
 		changeButtonText(sundayEndAmPmButton);
 	}
 	/**
 	 * Finds the available times from the given day
 	 * @param day Passes in string of day and finds matching Available time object
 	 * @return available time object of inputed day
 	 */
	public AvailableTime findDay(String day)
	{
		for(int i = 0; i < business.getBusinessHours().size(); i++)
		{
			if(business.getBusinessHours().get(i).getDay().equalsIgnoreCase(day))
				return business.getBusinessHours().get(i);	
		}
		return null;
	}
	
	/**
	 * Generic method for disabling row of business hours when closed 
	 * @param startHour Combo box for start hours 
	 * @param startMin Combo box for start minutes
	 * @param startAmPm Start time Am/Pm button
	 * @param endHour Combo box end hours
	 * @param endMin Combo box end minutes
	 * @param endAmPm End time Am/Pm button
	 * @param closed Checkbox for Closed
	 */
	public void disableRow(ComboBox<Integer> startHour, ComboBox<Integer> startMin, Button startAmPm, ComboBox<Integer> endHour,
			ComboBox<Integer> endMin, Button endAmPm, CheckBox closed)
	{
		if(closed.isSelected())
		{
			startHour.setDisable(true);
			startMin.setDisable(true);
			startAmPm.setDisable(true);
			endHour.setDisable(true);
			endMin.setDisable(true);
			endAmPm.setDisable(true);
		}
		else
		{
			startHour.setDisable(false);
			startMin.setDisable(false);
			startAmPm.setDisable(false);
			endHour.setDisable(false);
			endMin.setDisable(false);
			endAmPm.setDisable(false);
		}
	}
	/**
	 * Disable Monday row when closed
	 * @param event
	 */
	public void disableMondayRow(ActionEvent event)
	{
		disableRow(mondayStartHour, mondayStartMin, mondayStartAmPmButton, mondayEndHour, mondayEndMin, mondayEndAmPmButton, mondayClosed);		
	}
	/**
	 * Disable Tuesday row when closed
	 * @param event
	 */
	public void disableTuesdayRow(ActionEvent event)
	{
		disableRow(tuesdayStartHour, tuesdayStartMin, tuesdayStartAmPmButton, tuesdayEndHour, tuesdayEndMin, tuesdayEndAmPmButton, tuesdayClosed);	
	}
	/**
	 * Disable Wednesday row when closed
	 * @param event
	 */
	public void disableWednesdayRow(ActionEvent event)
	{
		disableRow(wednesdayStartHour, wednesdayStartMin, wednesdayStartAmPmButton, wednesdayEndHour, wednesdayEndMin, wednesdayEndAmPmButton, wednesdayClosed);	
	}
	/**
	 * Disable Thursday row when closed
	 * @param event
	 */
	public void disableThursdayRow(ActionEvent event)
	{
		disableRow(thursdayStartHour, thursdayStartMin, thursdayStartAmPmButton, thursdayEndHour, thursdayEndMin, thursdayEndAmPmButton, thursdayClosed);	
	}
	/**
	 * Disable Friday row when closed
	 * @param event
	 */
	public void disableFridayRow(ActionEvent event)
	{
		disableRow(fridayStartHour, fridayStartMin, fridayStartAmPmButton, fridayEndHour, fridayEndMin, fridayEndAmPmButton, fridayClosed);	
	}
	/**
	 * Disable Saturday row when closed
	 * @param event
	 */
	public void disableSaturdayRow(ActionEvent event)
	{
		disableRow(saturdayStartHour, saturdayStartMin, saturdayStartAmPmButton, saturdayEndHour, saturdayEndMin, saturdayEndAmPmButton, saturdayClosed);	
	}
	/**
	 * Disable Sunday row when closed
	 * @param event
	 */
	public void disableSundayRow(ActionEvent event)
	{
		disableRow(sundayStartHour, sundayStartMin, sundayStartAmPmButton, sundayEndHour, sundayEndMin, sundayEndAmPmButton, sundayClosed);	
	}

	/**
	 * Get all values from Monday row and set values.
	 * If day already has business hours, update Monday with new business hours.
 	 */
	public void getMondayTime()
	{
		LocalTime startTime;
		LocalTime endTime;
		AvailableTime currentDay = findDay("Monday");;
		
		if(mondayClosed.isSelected() && currentDay != null)
		{
			business.getBusinessHours().remove(currentDay);
		}
		else if(mondayStartHour.getValue() != null && mondayStartMin.getValue() != null && mondayEndHour.getValue() != null && mondayEndMin != null)
		{
			if(currentDay != null)
			{
				startTime = LocalTime.of(mondayStartHour.getValue(), mondayStartMin.getValue());
				endTime = LocalTime.of(mondayEndHour.getValue(), mondayEndMin.getValue());
				currentDay.setStartTime(startTime);
				currentDay.setEndTime(endTime);
			}	
			else
			{
				startTime = LocalTime.of(mondayStartHour.getValue(), mondayStartMin.getValue());
				endTime = LocalTime.of(mondayEndHour.getValue(), mondayEndMin.getValue());
				String day = "Monday";
				AvailableTime newDay = new AvailableTime(startTime, endTime, day);
				business.getBusinessHours().add(newDay);
			}
		}
		else
		{
			openHoursNotify.setText("Please select Monday start and end time hours and minutes");
		}
	}
	
	/**
	 * Get all values from Tuesday row and set values.
	 * If day already has business hours, update Tuesday with new business hours.
 	*/
	public void getTuesdayTime()
	{
		LocalTime startTime;
		LocalTime endTime;
		AvailableTime currentDay = findDay("Tuesday");
		
		if(tuesdayClosed.isSelected() && currentDay != null)
		{
			business.getBusinessHours().remove(currentDay);
		}
		else if(tuesdayStartHour.getValue() != null && tuesdayStartMin.getValue() != null && tuesdayEndHour.getValue() != null && tuesdayEndMin != null)
		{
			if(currentDay != null)
			{
				startTime = LocalTime.of(tuesdayStartHour.getValue(), tuesdayStartMin.getValue());
				endTime = LocalTime.of(tuesdayEndHour.getValue(), tuesdayEndMin.getValue());
				currentDay.setStartTime(startTime);
				currentDay.setEndTime(endTime);
			}	
			else
			{
				startTime = LocalTime.of(tuesdayStartHour.getValue(), tuesdayStartMin.getValue());
				endTime = LocalTime.of(tuesdayEndHour.getValue(), tuesdayEndMin.getValue());
				String day = "Tuesday";
				AvailableTime newDay = new AvailableTime(startTime, endTime, day);
				business.getBusinessHours().add(newDay);
			}
		}
		else
		{
			openHoursNotify.setText("Please select Tuesday start and end time hours and minutes");
		}
	}
	/**
	 * Get all values from Wednesday row and set values.
	 * If day already has business hours, update Wednesday with new business hours.
 	 */
	public void getWednesdayTime()
	{
		LocalTime startTime;
		LocalTime endTime;
		AvailableTime currentDay = findDay("Wednesday");
		
		if(wednesdayClosed.isSelected() && currentDay != null)
		{
			business.getBusinessHours().remove(currentDay);
		}
		else if(wednesdayStartHour.getValue() != null && wednesdayStartMin.getValue() != null && wednesdayEndHour.getValue() != null && wednesdayEndMin != null)
		{
			if(currentDay != null)
			{
				startTime = LocalTime.of(wednesdayStartHour.getValue(), wednesdayStartMin.getValue());
				endTime = LocalTime.of(wednesdayEndHour.getValue(), wednesdayEndMin.getValue());
				currentDay.setStartTime(startTime);
				currentDay.setEndTime(endTime);
			}	
			else
			{
				startTime = LocalTime.of(wednesdayStartHour.getValue(), wednesdayStartMin.getValue());
				endTime = LocalTime.of(wednesdayEndHour.getValue(), wednesdayEndMin.getValue());
				String day = "Wednesday";
				AvailableTime newDay = new AvailableTime(startTime, endTime, day);
				business.getBusinessHours().add(newDay);
			}
		}
		else
		{
			openHoursNotify.setText("Please select Wednesday start and end time hours and minutes");
		}
	}
	/**
	 * Get all values from Thursday row and set values.
	 * If day already has business hours, update Thursday with new business hours.
 	 */
	public void getThursdayTime()
	{
		LocalTime startTime;
		LocalTime endTime;
		AvailableTime currentDay = findDay("Thursday");
		
		if(thursdayClosed.isSelected() && currentDay != null)
		{
			business.getBusinessHours().remove(currentDay);
		}
		else if(thursdayStartHour.getValue() != null && thursdayStartMin.getValue() != null && thursdayEndHour.getValue() != null && thursdayEndMin != null)
		{
			if(currentDay != null)
			{
				startTime = LocalTime.of(thursdayStartHour.getValue(), thursdayStartMin.getValue());
				endTime = LocalTime.of(thursdayEndHour.getValue(), thursdayEndMin.getValue());
				currentDay.setStartTime(startTime);
				currentDay.setEndTime(endTime);
			}	
			else
			{
				startTime = LocalTime.of(thursdayStartHour.getValue(), thursdayStartMin.getValue());
				endTime = LocalTime.of(thursdayEndHour.getValue(), thursdayEndMin.getValue());
				String day = "Thursday";
				AvailableTime newDay = new AvailableTime(startTime, endTime, day);
				business.getBusinessHours().add(newDay);
			}
		}
		else
		{
			openHoursNotify.setText("Please select Thursday start and end time hours and minutes");
		}
	}
	/**
	 * Get all values from Friday row and set values.
	 * If day already has business hours, update Friday with new business hours.
 	 */
	public void getFridayTime()
	{
		LocalTime startTime;
		LocalTime endTime;
		AvailableTime currentDay = findDay("Friday");
		
		if(fridayClosed.isSelected() && currentDay != null)
		{
			business.getBusinessHours().remove(currentDay);
		}
		else if(fridayStartHour.getValue() != null && fridayStartMin.getValue() != null && fridayEndHour.getValue() != null && fridayEndMin != null)
		{
			if(currentDay != null)
			{
				startTime = LocalTime.of(fridayStartHour.getValue(), fridayStartMin.getValue());
				endTime = LocalTime.of(fridayEndHour.getValue(), fridayEndMin.getValue());
				currentDay.setStartTime(startTime);
				currentDay.setEndTime(endTime);
			}
			else
			{
				startTime = LocalTime.of(fridayStartHour.getValue(), fridayStartMin.getValue());
				endTime = LocalTime.of(fridayEndHour.getValue(), fridayEndMin.getValue());
				String day = "Friday";
				AvailableTime newDay = new AvailableTime(startTime, endTime, day);
				business.getBusinessHours().add(newDay);
			}
		}
		else
		{
			openHoursNotify.setText("Please select Friday start and end time hours and minutes");
		}
	}
	/**
	 * Get all values from Saturday row and set values.
	 * If day already has business hours, update Saturday with new business hours.
 	 */
	public void getSaturdayTime()
	{
		LocalTime startTime;
		LocalTime endTime;
		AvailableTime currentDay = findDay("Saturday");
		
		if(saturdayClosed.isSelected() && currentDay != null)
		{
			business.getBusinessHours().remove(currentDay);
		}
		else if(saturdayStartHour.getValue() != null && saturdayStartMin.getValue() != null && saturdayEndHour.getValue() != null && saturdayEndMin != null)
		{
			if(currentDay != null)
			{
				startTime = LocalTime.of(saturdayStartHour.getValue(), saturdayStartMin.getValue());
				endTime = LocalTime.of(saturdayEndHour.getValue(), saturdayEndMin.getValue());
				currentDay.setStartTime(startTime);
				currentDay.setEndTime(endTime);
			}	
			else
			{
				startTime = LocalTime.of(saturdayStartHour.getValue(), saturdayStartMin.getValue());
				endTime = LocalTime.of(saturdayEndHour.getValue(), saturdayEndMin.getValue());
				String day = "Saturday";
				AvailableTime newDay = new AvailableTime(startTime, endTime, day);
				business.getBusinessHours().add(newDay);
			}
		}
		else
		{
			openHoursNotify.setText("Please select Saturday start and end time hours and minutes");
		}
	}
	/**
	 * Get all values from Sunday row and set values.
	 * If day already has business hours, update Sunday with new business hours.
 	 */
	public void getSundayTime()
	{
		LocalTime startTime;
		LocalTime endTime;
		AvailableTime currentDay = findDay("Sunday");
		
		if(sundayStartHour.getValue() != null && sundayStartMin.getValue() != null && sundayEndHour.getValue() != null && sundayEndMin != null)
		{
			if(currentDay != null)
			{
				startTime = LocalTime.of(sundayStartHour.getValue(), sundayStartMin.getValue());
				endTime = LocalTime.of(sundayEndHour.getValue(), sundayEndMin.getValue());
				currentDay.setStartTime(startTime);
				currentDay.setEndTime(endTime);
			}	
			else
			{
				startTime = LocalTime.of(sundayStartHour.getValue(), sundayStartMin.getValue());
				endTime = LocalTime.of(sundayEndHour.getValue(), sundayEndMin.getValue());
				String day = "Sunday";
				AvailableTime newDay = new AvailableTime(startTime, endTime, day);
				business.getBusinessHours().add(newDay);
			}
		}
		else if(sundayClosed.isSelected() && currentDay != null)
		{
			business.getBusinessHours().remove(currentDay);
		}
		else
		{
			openHoursNotify.setText("Please select Sunday start and end time hours and minutes");
		}
	}
}