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
import users.*;

public class RegisterController implements Initializable{

    @FXML private Button confirmButton;
    @FXML private Button cancelButton;
    @FXML private Text registerActiontarget;
    
    @FXML private PasswordField registerPasswordField;
    @FXML private PasswordField registerPasswordConfirmField;
    
    @FXML TextField registerUsernameData;
	@FXML TextField registerFirstNameData;
	@FXML TextField registerLastNameData;
	@FXML TextField registerAddressData;
	@FXML TextField registerPhoneData;
    
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
	
    ArrayList<Customer> customers;
    ArrayList<Business> businesses;
    Registration reg = new Registration();
    
    public RegisterController(ArrayList<Customer> customers, ArrayList<Business> businesses)
    {
    	this.customers = customers;
    	this.businesses = businesses;
    }
    
    @FXML protected void handleConfirmButtonAction(ActionEvent event)
    {
	    try {
    		Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) confirmButton.getScene().getWindow();
	      	
	    	if(!registerPasswordField.getText().equals(registerPasswordConfirmField.getText()) || registerPasswordField.getText().isEmpty())
	    	{
	    		registerActiontarget.setText("Passwords do not match");
	    	}
	    	else
	    	{
	    		if(reg.setValues(registerFirstNameData.getText(), registerLastNameData.getText(),
	    				registerAddressData.getText(), registerPhoneData.getText(), registerUsernameData.getText(), registerPasswordField.getText(), customers, businesses))
	    		{
	    			reg.registerNewCustGUI(customers, businesses, registerFirstNameData.getText(), registerLastNameData.getText(),
		    				registerAddressData.getText(), registerPhoneData.getText(), registerUsernameData.getText(), registerPasswordField.getText());
	    			
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
					LoginController controller = new LoginController(session, customers, businesses);
					loader.setController(controller);
					
					root = loader.load();
					
			    	Scene scene = new Scene(root, 860, 640);
			    	stage.setScene(scene);
			    	stage.show();
	    		}
	    		else
	    		{
	    			registerActiontarget.setText("Registration failed");
	    			if(!reg.validUsername(customers, businesses))
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
			System.out.println("Unable to load login scene");
		}
    }
    
    @FXML protected void handleCancelButtonAction(ActionEvent event) 
    {
    	try {
	    	Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) cancelButton.getScene().getWindow();
	      	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			LoginController controller = new LoginController(customers, businesses);
			loader.setController(controller);
			
			root = loader.load();
			
	    	Scene scene = new Scene(root, 860, 640);
	    	stage.setScene(scene);
	    	stage.show();
    	}catch(IOException e)
    	{
    		System.out.println("Unable to load login scene");
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		registerUsernameData.setTooltip(usernameTooltip);
		registerFirstNameData.setTooltip(firstNameTooltip);
		registerLastNameData.setTooltip(lastNameTooltip);
		registerAddressData.setTooltip(addressTooltip);
		registerPhoneData.setTooltip(phoneTooltip);
		registerPasswordField.setTooltip(passwordTooltip);
	}
}
