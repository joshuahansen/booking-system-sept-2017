package javafx;

import java.awt.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.Registration;
import users.Business;
import users.Employee;

public class EmployeeAvailiabilitiesController implements Initializable{
	
	ArrayList<Business> businesses;
	Button[][] btn;
	Registration reg = new Registration();
	int busPos;
	
	@FXML private ComboBox<String> employeeCombo;
	@FXML private MenuButton employeeDropdown;
    private final ObservableList<String> employeeList = FXCollections.observableArrayList();

	private ArrayList<MenuItem> menuButtons;

	 public EmployeeAvailiabilitiesController(ArrayList<Business> businesses, int busPos)
	 {
	    	this.businesses = businesses;
	    	this.busPos = busPos;
	 }
	 
	 public void addEmployeeDropdown() 
	 {
		 
		 for (int i = 0; i <= businesses.get(busPos).employees.size(); i++)
		 {
			 MenuItem menuItem = new MenuItem(businesses.get(busPos).employees.get(i).getFirstName());
			 menuButtons.add(menuItem);
			 employeeDropdown.getItems().add(menuItem);
		 }
	 }
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		for(int empNo = 0; empNo < businesses.get(busPos).employees.size(); empNo++)
		{
			employeeList.add(businesses.get(busPos).employees.get(empNo).getName());
		}
		employeeCombo.setItems(employeeList);
	}
	
	public void updateBoard(ActionEvent event) 
	{
		String employeeSelected = employeeCombo.getValue();
		
		for(int count = businesses.get(busPos).employees.size() - 1; count >= 0 ; count--)
			if(!employeeSelected.equalsIgnoreCase("Select"))
			{
				setButtons(businesses.get(busPos).employees.get(count));
			}
	}

	public void setButtons(Employee empl) 
	{
		for(int i = 0; i < empl.availableTimes.length; i++)
		{
			
		}
	}
}
