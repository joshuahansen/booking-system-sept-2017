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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import users.*;

public class RegisterController {

    @FXML private Button confirmButton;
    @FXML private Button cancelButton;
    @FXML private Text registerActiontarget;
    
    @FXML private PasswordField registerPasswordField;
    @FXML private PasswordField registerPasswordConfirmField;
    
    ArrayList<Customer> customers;
    ArrayList<Business> businesses;
    
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
		    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
				LoginController controller = new LoginController(customers, businesses);
				loader.setController(controller);
				
				root = loader.load();
				
		    	Scene scene = new Scene(root, 1080, 720);
		    	stage.setScene(scene);
		    	stage.show();
		    	
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
			
	    	Scene scene = new Scene(root, 1080, 720);
	    	stage.setScene(scene);
	    	stage.show();
    	}catch(IOException e)
    	{
    		System.out.println("Unable to load login scene");
    	}
    }
}
