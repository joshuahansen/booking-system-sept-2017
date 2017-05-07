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
import javafx.stage.Stage;
import main.Login;
import main.Session;
import users.*;

public class AdminMenuController implements  Initializable{
	private Session session;
	private ArrayList<Customer> customers;
	private ArrayList<Business> businesses;
	private Database database;

	@FXML private GridPane businessRegistration;
	@FXML private ComboBox<String> businessCombo;
	@FXML private Button confirmSelectedBusinessButton;
	
	private final ObservableList<String> businessesComboList = FXCollections.observableArrayList();
	
	public AdminMenuController(Session session, ArrayList<Customer> customers, ArrayList<Business> businesses, Database database)
	{
		this.customers = customers;
		this.businesses = businesses;
		this.session = session;
		this.database = database;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			businessRegistration.getChildren().clear();
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterBusiness.fxml"));
			RegisterBusinessController controller = new RegisterBusinessController(session,customers, businesses, database);
			loader.setController(controller);
		
			businessRegistration.getChildren().add(loader.load());
			businessRegistration.getStylesheets().add(getClass().getResource("registrationPage.css").toExternalForm());
		}catch(IOException e)
		{
			System.out.println(e);
			session.addLog("Unable to load Customer register scenes");
		}
		
		for(int i = 0; i < businesses.size(); i++)
		{
			businessesComboList.add(businesses.get(i).getBusinessName());
		}
		businessCombo.setItems(businessesComboList);
	}
	
	public void handleBusinessLogin(ActionEvent event)
	{
		String username = null;
		String password = null;
		int userPos = 0;
		
		Stage stage;
    	Parent root;
    	
    	stage = (Stage) confirmSelectedBusinessButton.getScene().getWindow();
    			
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
			newLogin.login(customers, businesses, database);
			session.addLog("Load business menu");
	  		FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessMenu.fxml"));
			BusinessMenuController controller = new BusinessMenuController(session, customers, businesses, userPos, database);
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
