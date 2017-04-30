package javafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.Registration;
import users.Business;

public class AddEmployeeController implements Initializable{
	@FXML Text addEmployeeActiontarget;
	@FXML TextField employeeNumberData;
	@FXML TextField employeeFirstNameData;
	@FXML TextField employeeLastNameData;
	
	@FXML private Text employeeIDFail;
	@FXML private Text firstNameFail;
	@FXML private Text lastNameFail;
	
	ArrayList<Business> businesses;
	Registration reg = new Registration();
	int busPos;

	 public AddEmployeeController(ArrayList<Business> businesses, int busPos)
	    {
	    	this.businesses = businesses;
	    	this.busPos = busPos;
	    }
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void handleAddEmployeeButtonAction()
	{
		if(reg.setEmployeeValues(employeeNumberData.getText(), 
				employeeFirstNameData.getText(), employeeLastNameData.getText(), businesses.get(busPos).employees)) {

			reg.addNewEmployeeGui(businesses.get(busPos).employees);
			addEmployeeActiontarget.setText("Employee added");
			employeeNumberData.setText("");
			employeeFirstNameData.setText("");
			employeeLastNameData.setText("");
		}
		else
		{
			if(!reg.validEmployeeID(businesses.get(busPos).employees))
			{
				employeeIDFail.setText("ID Not Valid");
			}
			else
			{
				employeeIDFail.setText("");
			}
			if(!reg.validFirstName())
			{
				firstNameFail.setText("Name Not Valid");
			}
			else
			{
				firstNameFail.setText("");
			}
			if(!reg.validLastName())
			{
				lastNameFail.setText("Last Name Not Valid");
			}
			else
			{
				lastNameFail.setText("");
			}
		}
	}
	
	public void handleClearButtonAction()
	{
		employeeNumberData.setText("");
		employeeFirstNameData.setText("");
		employeeLastNameData.setText("");
	}

}
