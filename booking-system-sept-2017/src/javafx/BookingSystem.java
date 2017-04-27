package javafx;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import users.*;

/**
 * Main driver of the Booking System.
 * Contains main method and also start method to load GUI
 * @author Josh
 *
 */
public class BookingSystem extends Application {
	private Database database;
	private ArrayList<Customer> customers;
	private ArrayList<Business> businesses;
	
	/**
	 * Loads data from database into program
	 * Initializes program and sets login scene to the stage.
	 */
    @Override
    public void start(Stage stage) throws Exception {
    	customers = new ArrayList<>();
		businesses = new ArrayList<>();
		
		String url = "jdbc:sqlite:./database.db";
		
		database = new Database();
		/*only try loading database if a connection is established */
		if(database.connectDatabase(url) == true)
		{		
			/*Try reading from database tables customers and businesses*/
			if(database.readCustDB(customers) == true && database.readBusDB(businesses) == true)
			{
				System.out.println("Customer Database loaded");
				System.out.println("Business Database loaded");
				/*read employee availabilities only if customers and businesses are loaded correctly*/
				if(database.readEmplDB(businesses) && database.readAvailablityTimes(businesses))
				{
					System.out.println("Employee Database loaded");
					System.out.println("Employee availible times loaded");
				}
				else
				{
					System.out.println("Can not load employee database");
					System.out.println("Can not load employee availibilities");
				}
				if(database.readBookingsDB(businesses, customers))
				{
					System.out.println("Booking Databse loaded");
				}
				else
				{
					System.out.println("Can not load Bookings");
				}
			}
			else
			{
				/*if database doesn't exist initialize new database with default values and read database into arrays*/
				database.clearTables();
				database.initDatabase();
				database.defaultValues();
				if(database.readCustDB(customers) == true && database.readBusDB(businesses) == true)
				{
					System.out.println("Customer Database loaded");
					System.out.println("Business Database loaded");
					if(database.readEmplDB(businesses) && database.readAvailablityTimes(businesses))
					{
						System.out.println("Employee Database loaded");
						System.out.println("Employee available times loaded");
					}
					else
					{
						System.out.println("Can not load employee database");
						System.out.println("Can not load employee availabilities");
					}
					if(database.readBookingsDB(businesses, customers))
					{
						System.out.println("Booking Databse loaded");
					}
					else
					{
						System.out.println("Can not load Bookings");
					}
				}
			}
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
		LoginController controller = new LoginController(customers, businesses);
		loader.setController(controller);
		
		Parent bookingSystem = loader.load();
		Scene loginScene = new Scene(bookingSystem, 1080, 720);        
    
        stage.setTitle("Booking System - Login");
        stage.setScene(loginScene);
        stage.show();
        
        stage.setOnCloseRequest(e -> {
        System.out.println("Stage is closing");
    	database.writeCustDB(customers);
    	database.writeEmplToDB(businesses);
    	database.writeBookingToDB(businesses);
        Platform.exit();
        });
    }

    /**
     * Main method. Launches GUI interface.
     * @param args
     */
	public static void main(String[] args) {
		launch(args);
		
	}
}
