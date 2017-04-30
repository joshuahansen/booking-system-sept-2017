package javafx;

import java.io.IOException;
import java.util.ArrayList;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.*;
import users.*;

/**
 * Main driver of the Booking System.
 * Contains main method and also start method to load GUI
 * @author Josh
 *
 */
@SuppressWarnings("restriction")
public class BookingSystem extends Application {
	private Database database;
	private ArrayList<Customer> customers;
	private ArrayList<Business> businesses;
	private Session session;
	
	
	private Scene loginScene;
	
	public static void launchApplication(final Class<? extends Application> appClass,
			   final Class<? extends Preloader> preloaderClass,
			   final String[] args) {
			}
	
	public void init() throws Exception {
	  	customers = new ArrayList<>();
		businesses = new ArrayList<>();
		session = new Session();
		
		String url = "jdbc:sqlite:./database.db";
		
		database = new Database();
		/*only try loading database if a connection is established */
		if(database.connectDatabase(session, url) == true)
		{		
			/*Try reading from database tables customers and businesses*/
			if(database.readCustDB(session, customers) == true && database.readBusDB(session, businesses) == true)
			{
				session.addLog("Customer Database loaded");
				session.addLog("Business Database loaded");
				/*read employee availabilities only if customers and businesses are loaded correctly*/
				if(database.readEmplDB(session, businesses) && database.readAvailablityTimes(session, businesses))
				{
					session.addLog("Employee Database loaded");
					session.addLog("Employee availible times loaded");
				}
				else
				{
					session.addLog("Can not load employee database");
					session.addLog("Can not load employee availibilities");
				}
				if(database.readBookingsDB(session, businesses, customers))
				{
					session.addLog("Booking Databse loaded");
				}
				else
				{
					session.addLog("Can not load Bookings");
				}
			}
			else
			{
				/*if database doesn't exist initialize new database with default values and read database into arrays*/
				database.clearTables(session);
				database.initDatabase(session);
				database.defaultValues(session);
				if(database.readCustDB(session, customers) == true && database.readBusDB(session, businesses) == true)
				{
					session.addLog("Customer Database loaded");
					session.addLog("Business Database loaded");
					if(database.readEmplDB(session, businesses) && database.readAvailablityTimes(session, businesses))
					{
						session.addLog("Employee Database loaded");
						session.addLog("Employee availible times loaded");
					}
					else
					{
						session.addLog("Can not load employee database");
						session.addLog("Can not load employee availibilities");
					}
					if(database.readBookingsDB(session, businesses, customers))
					{
						session.addLog("Booking Databse loaded");
					}
					else
					{
						session.addLog("Can not load Bookings");
					}
				}
			}
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
		LoginController controller = new LoginController(session, customers, businesses);
		loader.setController(controller);
		
		Parent bookingSystem = loader.load();
		loginScene = new Scene(bookingSystem, 860, 640);        
		bookingSystem.getStylesheets().add(getClass().getResource("bookingSystem.css").toExternalForm());
		Thread.sleep(2000);
	}
	/**
	 * Loads data from database into program
	 * Initializes program and sets login scene to the stage.
	 */
    @Override
    public void start(Stage stage) throws Exception {
 
        stage.setTitle("Booking System");
        stage.setScene(loginScene);
        stage.show();
        
        stage.setOnCloseRequest(e -> {
        session.addLog("Stage is closing");
    	database.writeCustDB(session, customers);
    	database.writeEmplToDB(session, businesses);
    	database.writeBookingToDB(session, businesses);
    	try {
			session.terminateSession();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        Platform.exit();
        });
    }

    /**
     * Main method. Launches GUI interface.
     * @param args
     */
	public static void main(String[] args) {
//		launch(args);
		LauncherImpl.launchApplication(BookingSystem.class, BookingSystemPreloader.class, args);
	}
}
