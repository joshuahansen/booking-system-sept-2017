package javafx;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import users.Business;
import users.Customer;
import users.Database;
import users.Employee;

public class BookingSystem extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<Business> businesses = new ArrayList<>();
		ArrayList<Employee> employees = new ArrayList<>();
		
		String url = "jdbc:sqlite:./database.db";
		
		Database database = new Database();
		/*only try loading database if a connection is established */
		if(database.connectDatabase(url) == true)
		{		
			/*Try reading from database tables customers and businesses*/
			if(database.readCustDB(customers) == true && database.readBusDB(businesses) == true)
			{
				System.out.println("Customer Database loaded");
				System.out.println("Business Database loaded");
				/*read employee availabilities only if customers and businesses are loaded correctly*/
				if(database.readEmplDB(employees) && database.readAvailablityTimes(employees))
				{
					System.out.println("Employee Database loaded");
					System.out.println("Employee availible times loaded");
				}
				else
				{
					System.out.println("Can not load employee database");
					System.out.println("Can not load employee availibilities");
				}
				if(database.readBookingsDB(businesses, customers, employees))
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
					if(database.readEmplDB(employees) && database.readAvailablityTimes(employees))
					{
						System.out.println("Employee Database loaded");
						System.out.println("Employee available times loaded");
					}
					else
					{
						System.out.println("Can not load employee database");
						System.out.println("Can not load employee availabilities");
					}
					if(database.readBookingsDB(businesses, customers, employees))
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
//       bookingSystem = FXMLLoader.load(getClass().getResource("Register.fxml"));
//       Scene registerScene = new Scene(bookingSystem,1080, 720);
        
    
        stage.setTitle("Booking System - Login");
        stage.setScene(loginScene);
//        stage.setScene(registerScene);
        stage.show();
    }
    
	public static void main(String[] args) {
		launch(args);
		
	}

}
