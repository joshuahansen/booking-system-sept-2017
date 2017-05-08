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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    @FXML private Hyperlink registerButton;
    @FXML private Button cancelButton;
    @FXML private Button loginButton;
    @FXML private Button confirmButton;
    
    private ArrayList<Customer> customers;
    private ArrayList<Business> businesses;
    private Database database;
    
    private Session session;
    
    /**
     * Constructor for login controller
     * @param customers Customer array passed in from driver where it was created
     * @param businesses Business array passed in from driver where it was created
     */
    public LoginController(Session session, ArrayList<Customer> customers, ArrayList<Business> businesses, Database database)
    {
    	this.session = session;
    	this.customers = customers;
    	this.businesses = businesses;
    	this.database = database;
    }
    
    /**
     * Method used when Login button is pressed. Verifies if username and password is correct and sets scene to correct menu
     * for user. 
     */
    @FXML protected void handleLoginButtonAction(ActionEvent event)
    {
    	session.addLog("Login Button Pressed");
    	try {
	    	Stage stage;
	    	Parent root;
	    	
	    	stage = (Stage) loginButton.getScene().getWindow();
	    	
	      	Login newLogin = new Login(loginUsernameData.getText(), loginPasswordData.getText());
	      	int userType = newLogin.login(customers, businesses, database);
	      	int userPos = newLogin.getUserPosition();
	      	if( userType == 1)
	      	{
	      		session.addLog("Load customer menu");
	      		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerMenu.fxml"));
	    		CustomerMenuController controller = new CustomerMenuController(session, customers, businesses, userPos, database);
	    		loader.setController(controller);
	    		
	    		root = loader.load();
	      		Scene scene = new Scene(root, 860, 640);
	      		root.getStylesheets().add(getClass().getResource("customerMenu.css").toExternalForm());
	      		stage.setScene(scene);
	      		stage.show();
	      	}
	      	else if(userType == 2)
	      	{
	      		session.addLog("Load business menu");
	      		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessMenu.fxml"));
	    		BusinessMenuController controller = new BusinessMenuController(session, customers, businesses, userPos, database);
	    		loader.setController(controller);
	    		
	    		root = loader.load();
	      		Scene scene = new Scene(root, 860, 640);
	      		scene.getStylesheets().add(getClass().getResource("businessMenu.css").toExternalForm());
	      		stage.setScene(scene);
	      		stage.show();
	      	}
	      	else if(userType == 3)
	      	{
	      		session.addLog("Load admin menu");
	      		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminMenu.fxml"));
	    		AdminMenuController controller = new AdminMenuController(session, customers, businesses, database);
	    		loader.setController(controller);
	    		
	    		root = loader.load();
	      		Scene scene = new Scene(root, 860, 640);
//	      		scene.getStylesheets().add(getClass().getResource("adminMenu.css").toExternalForm());
	      		stage.setScene(scene);
	      		stage.show();
	      	}
	      	else
	      	{
	      		session.addLog("Login fail");
	      		actiontarget.setText("Username or password incorrect");
	      	}
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		session.addLog("Unable to load menu scenes");
    	}
    }
    
    /**
     * Handles when the register button is pressed and loads the registration page
     * @param event Gets action from when button is pressed
     */
    @FXML protected void handleRegisterButtonAction(ActionEvent event)
    {
    	session.addLog("Registration button pressed");
    	try {
    		Stage stage;
        	Parent root;
        	
        	stage = (Stage) registerButton.getScene().getWindow();
          	
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterCustomer.fxml"));
    		RegisterCustomerController controller = new RegisterCustomerController(session,customers, businesses, database);
    		loader.setController(controller);
    		
    		root = loader.load();
        	Scene scene = new Scene(root, 860, 640);
        	root.getStylesheets().add(getClass().getResource("registrationPage.css").toExternalForm());
        	stage.setScene(scene);
        	stage.show();
    	}catch(IOException e)
    	{
    		System.out.println(e);
    		session.addLog("Unable to load register scenes");
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}