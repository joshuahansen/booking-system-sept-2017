package javafx;
 
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Registration;
import main.Session;
import users.*;

public class RegisterCustomerController implements Initializable{

    @FXML private Button confirmButton;
    @FXML private Button cancelButton;
    @FXML private Text registerActiontarget;
    
    @FXML private PasswordField registerPasswordField;
    @FXML private PasswordField registerPasswordConfirmField;
    
    @FXML private TextField registerUsernameData;
	@FXML private TextField registerFirstNameData;
	@FXML private TextField registerLastNameData;
	@FXML private TextField registerAddressData;
	@FXML private TextField registerPhoneData;
    
	@FXML private Text usernameFail;
	@FXML private Text firstNameFail;
	@FXML private Text lastNameFail;
	@FXML private Text addressFail;
	@FXML private Text phoneFail;
	@FXML private Text passwordFail;
	
	@FXML private Tooltip usernameTooltip;
	@FXML private Tooltip firstNameTooltip;
	@FXML private Tooltip lastNameTooltip;
	@FXML private Tooltip addressTooltip;
	@FXML private Tooltip phoneTooltip;
	@FXML private Tooltip passwordTooltip;
	
	private ArrayList<Business> businesses;
	private Business business;
	private Registration reg = new Registration();
	private Session session;
	private Database database;
    
    public RegisterCustomerController(Session session, ArrayList<Business> businesses, Business business, Database database)
    {
    	this.businesses = businesses;
    	this.business = business;
    	this.session = session;
    	this.database = database;
    }
    
    @FXML protected void handleConfirmButtonAction(ActionEvent event)
    {
    	session.addLog("Confirm Button Pressed");
	    try {
    		Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) confirmButton.getScene().getWindow();
	      	
	    	if(!registerPasswordField.getText().equals(registerPasswordConfirmField.getText()) || registerPasswordField.getText().isEmpty())
	    	{
	    		session.addLog("passwords don't match");
	    		registerActiontarget.setText("Passwords do not match");
	    	}
	    	else
	    	{
	    		if(reg.setValues(registerFirstNameData.getText(), registerLastNameData.getText(),
	    				registerAddressData.getText(), registerPhoneData.getText(), registerUsernameData.getText(), registerPasswordField.getText(), business))
	    		{
	    			reg.registerNewCustGUI(business, registerFirstNameData.getText(), registerLastNameData.getText(),
		    				registerAddressData.getText(), registerPhoneData.getText(), registerUsernameData.getText(), registerPasswordField.getText());
	    			session.addLog("Registration Pass");
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
					LoginController controller = new LoginController(session, businesses, business, database);
					loader.setController(controller);
					
					root = loader.load();
					
			    	Scene scene = new Scene(root, 860, 640);
			    	stage.setScene(scene);
			    	stage.show();
	    		}
	    		else
	    		{
	    			session.addLog("Registration fail");
	    			registerActiontarget.setText("Registration failed");
	    			if(!reg.validUsername(business))
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
	    }catch(IOException e)
		{
	    	session.addLog("Unable to load login scene");
		}
    }
    
    @FXML protected void handleCancelButtonAction(ActionEvent event) 
    {
    	session.addLog("Cancle Button Pressed");
    	try {
	    	Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) cancelButton.getScene().getWindow();
	      	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			LoginController controller = new LoginController(session, businesses, business, database);
			loader.setController(controller);
			
			root = loader.load();
			
	    	Scene scene = new Scene(root, 860, 640);
	    	stage.setScene(scene);
	    	stage.show();
    	}catch(IOException e)
    	{
    		session.addLog("Unable to load login scene");
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		session.addLog("Tooltips added to registration page");
		registerUsernameData.setTooltip(usernameTooltip);
		registerFirstNameData.setTooltip(firstNameTooltip);
		registerLastNameData.setTooltip(lastNameTooltip);
		registerAddressData.setTooltip(addressTooltip);
		registerPhoneData.setTooltip(phoneTooltip);
		registerPasswordField.setTooltip(passwordTooltip);
	}
}
