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
import main.*;
import users.*;

public class AdminSelectBusinessController implements Initializable {
	private ArrayList<Customer> customers;
	private ArrayList<Business> businesses;
	private Session session;
	private Database database;
	
	@FXML private ComboBox<String> businessCombo;
	@FXML private Button confirmSelectedBusinessButton;
	@FXML private GridPane selectBusinessLayout;
	
	private final ObservableList<String> businessesComboList = FXCollections.observableArrayList();
	
	public AdminSelectBusinessController(Session session, ArrayList<Customer> customers, ArrayList<Business> businesses, Database database)
	{
		this.customers = customers;
		this.businesses = businesses;
		this.session = session;
		this.database = database;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(int i = 0; i < businesses.size(); i++)
		{
			businessesComboList.add(businesses.get(i).getBusinessName());
		}
		businessCombo.setItems(businessesComboList);
		
	}


}
