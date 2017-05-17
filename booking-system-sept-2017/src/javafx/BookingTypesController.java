package javafx;

import users.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.*;

public class BookingTypesController implements Initializable {
	Session session;
	Business business;
	
	@FXML Label eadingLabel;
	@FXML Label nameLabel;
	@FXML TextField nameText;
	@FXML Label hourLabel;
	@FXML ComboBox<Integer> hoursCombo;
	@FXML ComboBox<Integer> minsCombo;
	@FXML Button clearButton;
	@FXML Button confirmButton;
	
	private final ObservableList<Integer> hours = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
	private final ObservableList<Integer> minutes = FXCollections.observableArrayList(0, 15, 30, 45);
	
	public BookingTypesController(Session session, Business business)
	{
		this.session = session;
		this.business = business;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hoursCombo.setItems(hours);
		minsCombo.setItems(minutes);	
		hoursCombo.setValue(0);
		minsCombo.setValue(0);
	}
	
	@FXML protected void clearButtonAction(ActionEvent event)
	{
		nameText.setText("");
	}
	
	@FXML protected void confirmButtonAction(ActionEvent event)
	{
		//check if the booking time is within business hours.
		//if within business hours check name of booking
		//if name exists ask for confirmation to override current booking of that type.
		int selectedHours = hoursCombo.getValue();
		int selectedMins = minsCombo.getValue();
		String bookingName = nameText.getText();
		boolean match = false; 
		
		for(int i = 0; i < business.getBookingTypes().size(); i++)
		{
			if(business.getBookingTypes().get(i).getBookingType().equalsIgnoreCase(bookingName))
			{
				match = true;
				break;
			}
		}
		if(match)
		{
			//pop up confirmation
		}
		else
		{
			if(selectedHours != 0)
			{
				selectedMins = selectedMins + (selectedHours * 60);
			}
			business.addBookingType(bookingName, selectedMins);
		}
	}
}

