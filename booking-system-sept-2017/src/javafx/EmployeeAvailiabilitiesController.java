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

public class EmployeeAvailiabilitiesController implements Initializable{
	
	ArrayList<Business> businesses;
	Registration reg = new Registration();
	int busPos;

	 public EmployeeAvailiabilitiesController(ArrayList<Business> businesses, int busPos)
	    {
	    	this.businesses = businesses;
	    	this.busPos = busPos;
	    }
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	

}
