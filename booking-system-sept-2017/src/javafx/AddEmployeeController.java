package javafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import users.Business;

public class AddEmployeeController implements Initializable{
	@FXML Text addEmployeeActiontarget;
	@FXML TextField employeeNumberData;
	@FXML TextField employeeFirstNameData;
	@FXML TextField employeeLastNameData;
	
	ArrayList<Business> businesses;
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
		addEmployeeActiontarget.setText("Employee added");
	}
	
	public void handleClearButtonAction()
	{
		employeeNumberData.setText("");
		employeeFirstNameData.setText("");
		employeeLastNameData.setText("");
	}

}
