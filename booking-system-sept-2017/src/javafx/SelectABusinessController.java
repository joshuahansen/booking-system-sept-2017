package javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.CustomerMakeBookingController.AvailableBookingTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.*;
import users.*;

public class SelectABusinessController implements Initializable {
	private Session session;
	private ArrayList<Business> businesses;
	private Database database;
	
    private final ObservableList<BusinessTable> allBusinesses = FXCollections.observableArrayList();
	
	@FXML private Button selectBusinessButton;
	@FXML private TableView<BusinessTable> businessesTable;
	@FXML private Label selectBusinessLabel;
	
	public SelectABusinessController(Session session, ArrayList<Business> businesses, Database database)
	{
		this.session = session;
		this.businesses = businesses;
		this.database = database;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(int busPos = 0; busPos < businesses.size(); busPos++)
		{
			BusinessTable newBusiness = new BusinessTable(businesses.get(busPos));
			allBusinesses.add(newBusiness);
		}
		businessesTable.setItems(allBusinesses);
	}
	
	public static class BusinessTable
	{
		private final Business business;
		
		private final SimpleStringProperty businessName;
		private SimpleStringProperty monHours;
		private SimpleStringProperty tueHours;
		private SimpleStringProperty wedHours;
		private SimpleStringProperty thuHours;
		private SimpleStringProperty friHours;
		private SimpleStringProperty satHours;
		private SimpleStringProperty sunHours;
				
		public BusinessTable(Business business) //String businessName, String monHours, String tueHours, String wedHours, String thuHours, String friHours, String satHours, String sunHours)
		{
			this.business = business;
			getOpenDays();
			getClosedDays();
			this.businessName = new SimpleStringProperty(business.getBusinessName());
		}
		
		public void getOpenDays()
		{
			for(int i = 0; i < business.getBusinessHours().size(); i++)
			{
				if(business.getBusinessHours().get(i).getDay().equalsIgnoreCase("MONDAY"))
				{
					this.monHours = new SimpleStringProperty(business.getBusinessHours().get(i).getTimeslotAsString());
				}
				else if(business.getBusinessHours().get(i).getDay().equalsIgnoreCase("TUESDAY"))
				{
					this.tueHours = new SimpleStringProperty(business.getBusinessHours().get(i).getTimeslotAsString());
				}
				else if(business.getBusinessHours().get(i).getDay().equalsIgnoreCase("WEDNESDAY"))
				{
					this.wedHours = new SimpleStringProperty(business.getBusinessHours().get(i).getTimeslotAsString());
				}
				else if(business.getBusinessHours().get(i).getDay().equalsIgnoreCase("THURSDAY"))
				{
					this.thuHours = new SimpleStringProperty(business.getBusinessHours().get(i).getTimeslotAsString());
				}
				else if(business.getBusinessHours().get(i).getDay().equalsIgnoreCase("FRIDAY"))
				{
					this.friHours = new SimpleStringProperty(business.getBusinessHours().get(i).getTimeslotAsString());
				}
				else if(business.getBusinessHours().get(i).getDay().equalsIgnoreCase("SATURDAY"))
				{
					this.satHours = new SimpleStringProperty(business.getBusinessHours().get(i).getTimeslotAsString());
				}
				else if(business.getBusinessHours().get(i).getDay().equalsIgnoreCase("SUNDAY"))
				{
					this.sunHours = new SimpleStringProperty(business.getBusinessHours().get(i).getTimeslotAsString());
				}
			}
		}
		
		public void getClosedDays()
		{
			if(monHours == null)
			{
				this.monHours = new SimpleStringProperty("CLOSED");
			}
			
			if(tueHours == null)
			{
				this.tueHours = new SimpleStringProperty("CLOSED");
			}
			
			if(wedHours == null)
			{
				this.wedHours = new SimpleStringProperty("CLOSED");
			}
			
			if(thuHours == null)
			{
				this.thuHours = new SimpleStringProperty("CLOSED");
			}
			
			if(friHours == null)
			{
				this.friHours = new SimpleStringProperty("CLOSED");
			}
			
			if(satHours == null)
			{
				this.satHours = new SimpleStringProperty("CLOSED");
			}
			
			if(sunHours == null)
			{
				this.sunHours = new SimpleStringProperty("CLOSED");
			}
		}
	
		public Business getBusiness()
		{
			return this.business;
		}
		
		public String getBusinessName()
		{
			return this.businessName.get();
		}
		public String getMonHours()
		{
			return this.monHours.get();
		}
		public String getTueHours()
		{
			return this.tueHours.get();
		}
		public String getWedHours()
		{
			return this.wedHours.get();
		}
		public String getThuHours()
		{
			return this.thuHours.get();
		}
		public String getFriHours()
		{
			return this.friHours.get();
		}
		public String getSatHours()
		{
			return this.satHours.get();
		}
		public String getSunHours()
		{
			return this.sunHours.get();
		}
	}
	
	public void handleSelectedBusinessAction(ActionEvent event)
	{
		session.addLog("Select Business Button Pressed");
		BusinessTable newSelection = businessesTable.getSelectionModel().getSelectedItem();
		
		if(newSelection == null)
		{
			selectBusinessLabel.setText("Please select a business");
		}
		else
		{
	    	try {
		    	Stage stage;
		    	Parent root;
		    	
		    	stage = (Stage) selectBusinessButton.getScene().getWindow();
		      	
		    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
				LoginController controller = new LoginController(session, businesses, newSelection.getBusiness(), database);
				loader.setController(controller);
				
				root = loader.load();
				
		    	Scene scene = new Scene(root, 860, 640);
		    	root.getStylesheets().add(getClass().getResource("customerMenu.css").toExternalForm());
		    	stage.setScene(scene);
		    	stage.show();
	    	}catch(IOException e)
	    	{
	    		session.addLog("Unable to load login scene");
	    	}
		}
	}

}
