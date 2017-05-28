package javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Login;
import main.Registration;
import main.Session;
import users.*;

/**
 * Administration login.
 * Can log in as any business and has same functionality
 * Can add a new business with business details. business will then be able to log in and add business hours and employees. 
 * @author SEPT Team 6
 *
 */
public class AdminMenuController implements  Initializable{
	private Session session;
	private ArrayList<Business> businesses;
	private Database database;

	@FXML private GridPane businessRegistration;

	@FXML private GridPane selectBusiness;
	@FXML private ComboBox<String> businessCombo;
	@FXML private Button adminLogoutButton;
	
	@FXML private Button confirmButton;
    @FXML private Button cancelButton;
    @FXML private Text registerActiontarget;
	@FXML private Button confirmSelectedBusinessButton;
	@FXML private GridPane selectBusinessLayout; 
	@FXML private Text selectBusinessAlert;
    
    @FXML private PasswordField registerPasswordField;
    @FXML private PasswordField registerPasswordConfirmField;
    
    @FXML private TextField registerBusinessNameData;
    @FXML private TextField registerUsernameData;
	@FXML private TextField registerFirstNameData;
	@FXML private TextField registerLastNameData;
	@FXML private TextField registerAddressData;
	@FXML private TextField registerPhoneData;
    
	@FXML private Text businessNameFail;
	@FXML private Text usernameFail;
	@FXML private Text firstNameFail;
	@FXML private Text lastNameFail;
	@FXML private Text addressFail;
	@FXML private Text phoneFail;
	@FXML private Text passwordFail;
	
	@FXML private Tooltip businessNameTooltip;
	@FXML private Tooltip usernameTooltip;
	@FXML private Tooltip firstNameTooltip;
	@FXML private Tooltip lastNameTooltip;
	@FXML private Tooltip addressTooltip;
	@FXML private Tooltip phoneTooltip;
	@FXML private Tooltip passwordTooltip;

	private final ObservableList<String> businessesComboList = FXCollections.observableArrayList();
/**
 * Constructor to create new admin menu controller
 * @param session used for system runtime logging
 * @param businesses array off all the businesses
 * @param database connection to database
 */
	public AdminMenuController(Session session, ArrayList<Business> businesses, Database database)
	{
//		this.customers = customers;
		this.businesses = businesses;
//		this.business = business;
		this.session = session;
		this.database = database;
	}
	
	/**
	 * Initialize admin page with tooltips for business registration
	 * add list of businesses admin can login as
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		session.addLog("Tooltips added to registration page");
		registerBusinessNameData.setTooltip(businessNameTooltip);
		registerUsernameData.setTooltip(usernameTooltip);
		registerFirstNameData.setTooltip(firstNameTooltip);
		registerLastNameData.setTooltip(lastNameTooltip);
		registerAddressData.setTooltip(addressTooltip);
		registerPhoneData.setTooltip(phoneTooltip);
		registerPasswordField.setTooltip(passwordTooltip);
		businessesComboList.clear();

		businessesComboList.clear();
		for(int i = 0; i < businesses.size(); i++)
		{
			businessesComboList.add(businesses.get(i).getBusinessName());
		}
		businessCombo.setItems(businessesComboList);
	}
	
	/**
	 * Log in as selected business.
	 * @param event
	 */
	public void handleBusinessLogin(ActionEvent event)
	{
		String username = null;
		String password = null;
		int userPos = 0;
		
		Stage stage;
    	Parent root;
    	
    	stage = (Stage) confirmSelectedBusinessButton.getScene().getWindow();
    	
    	if(businessCombo.getValue() == null)
    	{
    		selectBusinessAlert.setText("Please select a business");
    	}
    	else
    	{
			for(int i = 0; i < businesses.size(); i++)
			{
				if(businesses.get(i).getBusinessName().equalsIgnoreCase(businessCombo.getValue()))
				{
					username = businesses.get(i).getUsername();
					password = businesses.get(i).getPassword();
					userPos = i;
					break;
				}
			}
			if(username == null || password == null)
			{
				session.addLog("Selection was not valid");
			}
			try{
				Login newLogin = new Login(username, password);
				newLogin.login(businesses.get(userPos), database);
				session.addLog("Load business menu");
		  		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessMenu.fxml"));
				BusinessMenuController controller = new BusinessMenuController(session, businesses, businesses.get(userPos), database);
				loader.setController(controller);
				
				root = loader.load();
		  		Scene scene = new Scene(root, 860, 640);
		  		scene.getStylesheets().add(getClass().getResource("businessMenu.css").toExternalForm());
		  		stage.setScene(scene);
		  		stage.show();
			}catch(IOException e)
	    	{
	    		System.out.println(e);
	    		session.addLog("Unable to load menu scenes");
	    	}
    	}
	}
	
	/**
	 * When Logout is selected program returns to front page with 
	 * @param event
	 */
	public void handleAdminLogout(ActionEvent event)
	{
    	try {
    	Stage stage;
    	Parent root;
    	
    	stage = (Stage) adminLogoutButton.getScene().getWindow();
      	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectABusiness.fxml"));
		SelectABusinessController controller = new SelectABusinessController(session, businesses, database);
		loader.setController(controller);
		
		root = loader.load();
		Scene scene = new Scene(root, 860, 640);        
		scene.getStylesheets().add(getClass().getResource("bookingSystem.css").toExternalForm());

    	stage.setScene(scene);
    	stage.show();
		}catch(IOException e)
		{
			session.addLog("Unable to load login scene");
		}
	}
	/**
	 * Confirm button checks if business details are valid and creates a new business instance.  
	 * @param event
	 */
	@FXML protected void handleConfirmButtonAction(ActionEvent event)
    {
    	
    	Registration reg = new Registration();
    	session.addLog("Confirm Button Pressed");

    	if(!registerPasswordField.getText().equals(registerPasswordConfirmField.getText()) || registerPasswordField.getText().isEmpty())
    	{
    		session.addLog("passwords don't match");
    		registerActiontarget.setText("Passwords do not match");
    	}
    	else
    	{
    		if(reg.setBusinessValues(registerBusinessNameData.getText(), registerFirstNameData.getText(), registerLastNameData.getText(),
    				registerAddressData.getText(), registerPhoneData.getText(), registerUsernameData.getText(), registerPasswordField.getText(), businesses))
    		{
    			reg.registerNewBusiness(session, businesses);
    			session.addLog("Registration Pass");
    			registerActiontarget.setText("Business Added please select the business to login and add times and employees");

    	    	registerBusinessNameData.setText("");
    	    	registerUsernameData.setText("");
    	    	registerFirstNameData.setText("");
    	    	registerLastNameData.setText("");
    	    	registerAddressData.setText("");
    	    	registerPhoneData.setText("");
    	    	registerPasswordConfirmField.setText("");
    	    	registerPasswordField.setText("");
    	    	
    	    	businessesComboList.clear();
    	    	for(int i = 0; i < businesses.size(); i++)
    			{
    				businessesComboList.add(businesses.get(i).getBusinessName());
    			}
    			businessCombo.setItems(businessesComboList);
    		}
    		else
    		{
    			session.addLog("Registration fail");
    			registerActiontarget.setText("Registration failed");
    			if(!reg.validBusinessName())
    			{
    				businessNameFail.setText("Business Name Not Valid");
    			}
    			else
    			{
    				businessNameFail.setText("");
    			}
    			if(!reg.validBusinessUsername(businesses))
    			{
    				usernameFail.setText("Username Not Valid");
    			}
    			else
    			{
    				usernameFail.setText("");
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
    			if(!reg.validAddress())
    			{
    				addressFail.setText("Address not Valid");
    			}
    			else
    			{
    				addressFail.setText("");
    			}
    			if(!reg.validPhone())
    			{
    				phoneFail.setText("Phone Not Valid");
    			}
    			else
    			{
    				phoneFail.setText("");
    			}
    			if(!reg.validPassword())
    			{
    				passwordFail.setText("Password Not Valid");
    			}
    			else
    			{
    				passwordFail.setText("");
    			}
    		}
    	}
    }
    
	/**
	 * Clear button clears all fields in the registration field.
	 * @param event
	 */
    @FXML protected void handleClearButtonAction(ActionEvent event) 
    {
    	session.addLog("Clear Button Pressed");
    	registerBusinessNameData.setText("");
    	registerUsernameData.setText("");
    	registerFirstNameData.setText("");
    	registerLastNameData.setText("");
    	registerAddressData.setText("");
    	registerPhoneData.setText("");
    	registerPasswordConfirmField.setText("");
    	registerPasswordField.setText("");
    }
}
