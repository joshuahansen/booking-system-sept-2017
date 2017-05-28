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
 * @author SEPT Team 6
 *
 */
@SuppressWarnings("restriction")
public class BookingSystem extends Application {
	private Database database;
	private ArrayList<Business> businesses;
	private Session session;
	private Scene loginScene;

	public static void launchApplication(final Class<? extends Application> appClass,
			   final Class<? extends Preloader> preloaderClass,
			   final String[] args) {
			}
	
	/**
	 * initializes system and loads database. runs pre-loader  while database is connecting.
	 */
	public void init() throws Exception {
		businesses = new ArrayList<>();
		session = new Session();
		
		String url = "jdbc:sqlite:./database.db";
		
		database = new Database();
		/*only try loading database if a connection is established */
		if(database.connectDatabase(session, url) == true)
		{		
			/*Try reading from database tables customers and businesses*/
			if(database.readBusDB(session, businesses))
			{
				session.addLog("Business Database loaded");
				
				if(database.readBusinessHoursDB(session, businesses))
					session.addLog("Business Hours Database loaded");
				else
					session.addLog("Business Hours Database not loaded");
				
				if(database.readBookingTypesDB(session, businesses))
					session.addLog("Booking Types loaded");
				else
					session.addLog("Booking Types Database not loaded");
				
				if(database.readCustDB(session, businesses))
						session.addLog("Customer Database loaded");
				else
					session.addLog("Customer Database not loaded");
				
				if(database.readEmplDB(session, businesses))
					session.addLog("Employee Database loaded");
				else
					session.addLog("Can not load employee database");
				
				if(database.readAvailablityTimes(session, businesses))
					session.addLog("Employee available times loaded");
				else
					session.addLog("Can not load employee availabilities");
	
				if(database.readBookingsDB(session, businesses))
					session.addLog("Booking Database loaded");
				else
					session.addLog("Can not load Bookings");
			}
		
			else
			{
				/*if database doesn't exist initialize new database with default values and read database into arrays*/
				database.clearTables(session);
				database.initDatabase(session);
				database.defaultValues(session);
				
				/*Try reading from database table businesses*/
				if(database.readBusDB(session, businesses))
				{
					session.addLog("Business Database loaded");
					
					if(database.readBusinessHoursDB(session, businesses))
						session.addLog("Business Hours Database loaded");
					else
						session.addLog("Business Hours Database not loaded");
					
					if(database.readBookingTypesDB(session, businesses))
						session.addLog("Booking Types loaded");
					else
						session.addLog("Booking Types Database not loaded");
					
					if(database.readCustDB(session, businesses))
							session.addLog("Customer Database loaded");
					else
						session.addLog("Customer Database not loaded");
					
					if(database.readEmplDB(session, businesses))
						session.addLog("Employee Database loaded");
					else
						session.addLog("Can not load employee database");
					
					if(database.readAvailablityTimes(session, businesses))
						session.addLog("Employee available times loaded");
					else
						session.addLog("Can not load employee availabilities");
		
					if(database.readBookingsDB(session, businesses))
						session.addLog("Booking Database loaded");
					else
						session.addLog("Can not load Bookings");
					}
				}
		}

		FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectABusiness.fxml"));
		SelectABusinessController controller = new SelectABusinessController(session, businesses, database);
		loader.setController(controller);
		
		Parent bookingSystem = loader.load();
		loginScene = new Scene(bookingSystem, 860, 640);        
		bookingSystem.getStylesheets().add(getClass().getResource("bookingSystem.css").toExternalForm());
		Thread.sleep(2000);
	}
	/**
	 * Initializes program and sets login scene to the stage.
	 * Handles exit. writes to database
	 */
    @Override
    public void start(Stage stage) throws Exception {
 
        stage.setTitle("Booking System");
        stage.setScene(loginScene);
        
        stage.setResizable(false);
        stage.show();
        
        stage.setOnCloseRequest(e -> {
        session.addLog("Stage is closing");
    	database.writeCustDB(session, businesses);
    	database.writeEmplToDB(session, businesses);
    	database.writeBookingToDB(session, businesses);
    	database.writeBusinessDB(session, businesses);
    	database.writeBusinessHoursToDB(session, businesses);
    	database.writeBookingTypeToDB(session, businesses);
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
