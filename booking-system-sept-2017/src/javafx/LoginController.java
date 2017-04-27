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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import main.*;
import users.*;

/**
 * Controller for the Login.fxml.
 * Arrays are passed into controller when it is created.
 * @author Josh
 *
 */
public class LoginController implements Initializable{
    @FXML private Text actiontarget;
    
    @FXML private TextField loginUsernameData;
    @FXML private PasswordField loginPasswordData;
    @FXML private Button registerButton;
    @FXML private Button cancelButton;
    @FXML private Button loginButton;
    @FXML private Button confirmButton;
    
    ArrayList<Customer> customers;
    ArrayList<Business> businesses;
    
    /**
     * Constructor for login controller
     * @param customers Customer array passed in from driver where it was created
     * @param businesses Business array passed in from driver where it was created
     */
    public LoginController(ArrayList<Customer> customers, ArrayList<Business> businesses)
    {
    	this.customers = customers;
    	this.businesses = businesses;
    }
    
    /**
     * Method used when Login button is pressed. Verifies if username and password is correct and sets scene to correct menu
     * for user. 
     */
    @FXML protected void handleLoginButtonAction(ActionEvent event)
    {
    	try {
	    	Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) loginButton.getScene().getWindow();
	    	
	      	Login newLogin = new Login(loginUsernameData.getText(), loginPasswordData.getText());
	      	int userType = newLogin.login(customers, businesses);
	      	int userPos = newLogin.getUserPosition();
	      	if( userType == 1)
	      	{
	      		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerMenu.fxml"));
	    		CustomerMenuController controller = new CustomerMenuController(customers, businesses, userPos);
	    		loader.setController(controller);
	    		
	    		root = loader.load();
	      		Scene scene = new Scene(root, 860, 640);
	      		root.getStylesheets().add(getClass().getResource("loginPage.css").toExternalForm());
	      		stage.setScene(scene);
	      		stage.show();
	      	}
	      	else if(userType == 2)
	      	{
	      		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessMenu.fxml"));
	    		BusinessMenuController controller = new BusinessMenuController(customers, businesses, userPos);
	    		loader.setController(controller);
	    		
	    		root = loader.load();
	      		Scene scene = new Scene(root, 860, 640);
	      		//scene.getStylesheets().add(getClass().getResource("loginPage.css").toExternalForm());
	      		stage.setScene(scene);
	      		stage.show();
	      	}
	      	else
	      	{
	      		actiontarget.setText("Username or password incorrect");
	      	}
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		System.out.println("Unable to load menu scenes");
    	}
    }
    
    /**
     * Handles when the register button is pressed and loads the registration page
     * @param event Gets action from when button is pressed
     */
    @FXML protected void handleRegisterButtonAction(ActionEvent event)
    {
    	try {
    		Stage stage;
        	Parent root;
        	
        	stage = (Stage) registerButton.getScene().getWindow();
          	
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
    		RegisterController controller = new RegisterController(customers, businesses);
    		loader.setController(controller);
    		
    		root = loader.load();
        	Scene scene = new Scene(root, 860, 640);
        	root.getStylesheets().add(getClass().getResource("loginPage.css").toExternalForm());
        	stage.setScene(scene);
        	stage.show();
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		System.out.println("Unable to load register scenes");
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}