package javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
/**
 * Start up page allowing user to select the business they wish to make bookings for.
 * Once logged in the system stays in selected business until closed or admin logs in and selects another business  
 * @author SEPT Team 6
 *
 */
public class SelectABusinessController implements Initializable {
	private Session session;
	private ArrayList<Business> businesses;
	private Database database;
	
    private final ObservableList<BusinessTable> allBusinesses = FXCollections.observableArrayList();
	
	@FXML private Button selectBusinessButton;
	@FXML private TableView<BusinessTable> businessesTable;
	@FXML private Label selectBusinessLabel;
	
	/**
	 * Constructor for select a business controller
	 * @param session for system runtime logging
	 * @param businesses array of businesses
	 * @param database database connection
	 */
	public SelectABusinessController(Session session, ArrayList<Business> businesses, Database database)
	{
		this.session = session;
		this.businesses = businesses;
		this.database = database;
	}
	
	/**
	 * initialize table with a list of businesses and business hours 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(int busPos = 0; busPos < businesses.size(); busPos++)
		{
			BusinessTable newBusiness = new BusinessTable(businesses.get(busPos));
			allBusinesses.add(newBusiness);
		}
		businessesTable.setItems(allBusinesses);
	}
	
	/**
	 * Objects of businesses to be displayed in the table
	 * @author SEPT Team 6
	 *
	 */
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
		
		/**
		 * constructor for business table object
		 * @param business current business to be added
		 */
		public BusinessTable(Business business)
		{
			this.business = business;
			getOpenDays();
			getClosedDays();
			this.businessName = new SimpleStringProperty(business.getBusinessName());
		}
		
		/**
		 * get open days and business hours
		 */
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
		/**
		 * get days the business is closed
		 */
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
	
		/**
		 * get business
		 * @return business being displayed
		 */
		public Business getBusiness()
		{
			return this.business;
		}
		/**
		 * get business name
		 * @return business name
		 */
		public String getBusinessName()
		{
			return this.businessName.get();
		}
		/**
		 * get Monday hours
		 * @return String of Mondays hours
		 */
		public String getMonHours()
		{
			return this.monHours.get();
		}
		/**
		 * get Tuesday hours
		 * @return String of Tuesdays hours
		 */
		public String getTueHours()
		{
			return this.tueHours.get();
		}
		/**
		 * get Wednesday hours
		 * @return String of Wednesdays hours
		 */
		public String getWedHours()
		{
			return this.wedHours.get();
		}
		/**
		 * get Thursday hours
		 * @return String of Thursdays hours
		 */
		public String getThuHours()
		{
			return this.thuHours.get();
		}
		/**
		 * get Fridays hours
		 * @return String of Fridays hours
		 */
		public String getFriHours()
		{
			return this.friHours.get();
		}
		/**
		 * get Saturday hours
		 * @return String of Saturdays hours
		 */
		public String getSatHours()
		{
			return this.satHours.get();
		}
		/**
		 * get Sunday hours
		 * @return String of Sundays hours
		 */
		public String getSunHours()
		{
			return this.sunHours.get();
		}
	}
	/**
	 * Handle selected button action
	 * get selected business, load login page allowing only customers and selected business to login
	 * @param event
	 */
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
