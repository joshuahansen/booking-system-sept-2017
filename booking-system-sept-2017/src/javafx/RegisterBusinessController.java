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
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.*;
import users.*;

public class RegisterBusinessController implements Initializable {
	private ArrayList<Customer> customers;
	private ArrayList<Business> businesses;
	private Session session;
	
    @FXML private Button confirmButton;
    @FXML private Button cancelButton;
    @FXML private Text registerActiontarget;
    
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
	
	public RegisterBusinessController(Session session, ArrayList<Customer> customers, ArrayList<Business> businesses)
	{
		this.customers = customers;
		this.businesses = businesses;
		this.session = session;
	}
	
    
    @FXML protected void handleConfirmButtonAction(ActionEvent event)
    {
    	registerActiontarget.setText("Not available at this time");
    }
    
    @FXML protected void handleCancelButtonAction(ActionEvent event) 
    {
    	session.addLog("Cancle Button Pressed");
    	try {
	    	Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) cancelButton.getScene().getWindow();
	      	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			LoginController controller = new LoginController(session, customers, businesses);
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
		registerBusinessNameData.setTooltip(businessNameTooltip);
		registerUsernameData.setTooltip(usernameTooltip);
		registerFirstNameData.setTooltip(firstNameTooltip);
		registerLastNameData.setTooltip(lastNameTooltip);
		registerAddressData.setTooltip(addressTooltip);
		registerPhoneData.setTooltip(phoneTooltip);
		registerPasswordField.setTooltip(passwordTooltip);
	}
}
