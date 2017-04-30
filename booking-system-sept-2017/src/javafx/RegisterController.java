package javafx;
 
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Registration;
import users.*;

public class RegisterController {

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
					LoginController controller = new LoginController(customers, businesses);
					loader.setController(controller);
					
					root = loader.load();
					
			    	Scene scene = new Scene(root, 860, 640);
			    	stage.setScene(scene);
			    	stage.show();
	    		}
	    		else
	    		{
	    			System.out.println("Run ");
	    			registerActiontarget.setText("Registration failed");
	    			if(!reg.validUsername(customers, businesses))
	    			{
	    				System.out.println("\t\tUsername fail");
	    				usernameFail.setText("Username Not Valid");
	    			}
	    			else
	    			{
	    				System.out.println("\t\tUsername pass");
	    				usernameFail.setText("");
	    			}
	    			if(!reg.validFirstName())
	    			{
	    				System.out.println("\t\tName fail");
	    				firstNameFail.setText("Name Not Valid");
	    			}
	    			else
	    			{
	    				System.out.println("\t\tName pass");
	    				firstNameFail.setText("");
	    			}
	    			if(!reg.validLastName())
	    			{
	    				System.out.println("\t\tLast Name fail");
	    				lastNameFail.setText("Last Name Not Valid");
	    			}
	    			else
	    			{
	    				System.out.println("\t\tLast Name pass");
	    				lastNameFail.setText("");
	    			}
	    			if(!reg.validAddress())
	    			{
	    				System.out.println("\t\taddress fail");
	    				addressFail.setText("Address not Valid");
	    			}
	    			else
	    			{
	    				System.out.println("\t\tAddress Pass");
	    				addressFail.setText("");
	    			}
	    			if(!reg.validPhone())
	    			{
	    				System.out.println("\t\tphone fail");
	    				phoneFail.setText("Phone Not Valid");
	    			}
	    			else
	    			{
	    				System.out.println("\t\tphone pass");
	    				phoneFail.setText("");
	    			}
	    			if(!reg.validPassword())
	    			{
	    				System.out.println("\t\tpassword fail");
	    				passwordFail.setText("Password Not Valid");
	    			}
	    			else
	    			{
	    				System.out.println("\t\tpassword pass");
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
}
