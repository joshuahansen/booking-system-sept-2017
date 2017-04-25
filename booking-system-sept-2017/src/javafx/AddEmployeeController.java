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
import users.Employee;

public class AddEmployeeController implements Initializable{
	@FXML Text addEmployeeActiontarget;
	@FXML TextField employeeNumberData;
	@FXML TextField employeeFirstNameData;
	@FXML TextField employeeLastNameData;
	
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
			
		}
	}
	
	public void handleClearButtonAction()
	{
		employeeNumberData.setText("");
		employeeFirstNameData.setText("");
		employeeLastNameData.setText("");
	}

}
