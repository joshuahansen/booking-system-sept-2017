package javafx;

import users.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
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
	@FXML Text confirmText;
	
	private final ObservableList<Integer> hours = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6);
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
		hoursCombo.setValue(0);
		minsCombo.setValue(0);
	}
	
	@FXML protected void confirmButtonAction(ActionEvent event)
	{
		int selectedHours = hoursCombo.getValue();
		int selectedMins = minsCombo.getValue();
		String bookingName = nameText.getText().toUpperCase();
		boolean match = false; 
		BookingType currentBookingType = null;
		
		if(!bookingName.equals(""))
		{
			for(int i = 0; i < business.getBookingTypes().size(); i++)
			{
				if(business.getBookingTypes().get(i).getBookingType().equalsIgnoreCase(bookingName))
				{
					match = true;
					currentBookingType = business.getBookingTypes().get(i);
					break;
				}
			}
			if(match)
			{
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update Booking " + bookingName + " Confirmation");
		    	alert.setHeaderText("Update Booking");
		    	alert.setContentText("This booking type already exists. By selecting okay the booking " + bookingName + " will be changed to have a length of " 
		    			+ selectedHours + " hours and " + selectedMins + " minutes.");		
		    			    	
		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		        	business.getBookingTypes().remove(currentBookingType);
		        	if(selectedHours != 0)
					{
						selectedMins = selectedMins + (selectedHours * 60);
					}
					business.addBookingType(bookingName, selectedMins);
					confirmText.setText("Booking Type " + bookingName + " of a length " + selectedMins + " was created");
		        	session.addLog("Alert confirmed");
		    	} else {
		    	    // ... user chose CANCEL or closed the dialog
		    		session.addLog("alert cancled");
		    	}
			}
			else
			{
				if(selectedHours != 0)
				{
					selectedMins = selectedMins + (selectedHours * 60);
				}
				business.addBookingType(bookingName, selectedMins);
				confirmText.setText("Booking Type" + bookingName + " of a length " + selectedMins + " was created");
			}
		}
		else
		{
			confirmText.setText("Please enter a name for the booking type.");
		}
	}
}

