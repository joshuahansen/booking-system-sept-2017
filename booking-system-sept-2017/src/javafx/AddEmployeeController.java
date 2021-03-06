package javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import main.Registration;
import main.Session;
import users.Business;

/**
 * Controller for AddEmployee.fxml
 * Adds functionality to the page
 * @author SEPT Team 6
 *
 */
public class AddEmployeeController implements Initializable{
	@FXML Text addEmployeeActiontarget;
	@FXML TextField employeeNumberData;
	@FXML TextField employeeFirstNameData;
	@FXML TextField employeeLastNameData;
	
	@FXML private Text employeeIDFail;
	@FXML private Text firstNameFail;
	@FXML private Text lastNameFail;
	
	@FXML private Tooltip employeeTooltip;
	@FXML private Tooltip firstNameTooltip;
	@FXML private Tooltip lastNameTooltip;
	
	private Business business;
	private Registration reg = new Registration();
	private Session session;

	/**
	 * Constructor for the AddEmployeeController class
	 * @param business business employee is being added to
	 * @param session for system runtime logging
	 */
	 public AddEmployeeController(Session session, Business business)
	    {
	    	this.business = business;
	    	this.session = session;
	    }
	 
	 /**
	  * Initialize page with tool tips on each TextField
	  */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		employeeNumberData.setTooltip(employeeTooltip);
		employeeFirstNameData.setTooltip(firstNameTooltip);
		employeeLastNameData.setTooltip(lastNameTooltip);
	}
	
	/**
	 * Handle add employee button.
	 * Get input from TextFields and pass to Registration object. 
	 * If registration passes add employee.
	 * If registration fails display a prompt to user for fields failed
	 */
	public void handleAddEmployeeButtonAction()
	{
		session.addLog("Add Employee Button Pressed");
		if(reg.setEmployeeValues(employeeNumberData.getText(), 
				employeeFirstNameData.getText(), employeeLastNameData.getText(), business.getEmployees())) {

			reg.addNewEmployeeGui(business.getEmployees());
			addEmployeeActiontarget.setText("Employee added");
			employeeNumberData.setText("");
			employeeFirstNameData.setText("");
			employeeLastNameData.setText("");
			session.addLog("Employee added");
		}
		else
		{
			if(!reg.validEmployeeID(business.getEmployees()))
			{
				session.addLog("Invalid employee ID");
				employeeIDFail.setText("ID Not Valid");
			}
			else
			{
				session.addLog("Valid employee ID");
				employeeIDFail.setText("");
			}
			if(!reg.validFirstName())
			{
				session.addLog("Invalid first name");
				firstNameFail.setText("Name Not Valid");
			}
			else
			{
				session.addLog("Valid first name");
				firstNameFail.setText("");
			}
			if(!reg.validLastName())
			{
				session.addLog("Invalid last name");
				lastNameFail.setText("Last Name Not Valid");
			}
			else
			{
				session.addLog("Valid last name");
				lastNameFail.setText("");
			}
		}
	}
	
	/**
	 * Handle Clear button action. Clear text fields
	 */
	public void handleClearButtonAction()
	{
		session.addLog("Clear Button Pressed");
		employeeNumberData.setText("");
		employeeFirstNameData.setText("");
		employeeLastNameData.setText("");
	}

}
