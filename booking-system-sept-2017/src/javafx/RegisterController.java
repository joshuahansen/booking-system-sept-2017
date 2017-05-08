//package javafx;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Tab;
//import javafx.scene.layout.GridPane;
//import main.Session;
//import users.*;
//
//public class RegisterController implements  Initializable{
//	private Session session;
//	private ArrayList<Customer> customers;
//	private ArrayList<Business> businesses;
//	private Database database;
//
//	@FXML private GridPane businessRegistration;
//	@FXML private GridPane customerRegistration;
//	
//	@FXML Tab customerRegistrationTab;
//	@FXML Tab businessRegistrationTab;
//	
//	public RegisterController(Session session, ArrayList<Customer> customers, ArrayList<Business> businesses, Database database)
//	{
//		this.customers = customers;
//		this.businesses = businesses;
//		this.session = session;
//		this.database = database;
//	}
//	
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		try {
//			customerRegistration.getChildren().clear();
//	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterCustomer.fxml"));
//			RegisterCustomerController controller = new RegisterCustomerController(session,customers, businesses, database);
//			loader.setController(controller);
//		
//			customerRegistration.getChildren().add(loader.load());
//			customerRegistration.getStylesheets().add(getClass().getResource("registrationPage.css").toExternalForm());
//		}catch(IOException e)
//		{
//			System.out.println(e);
//			session.addLog("Unable to load Customer register scenes");
//		}
//	}
//	
//	public void onSelectionCustomerRegistration()
//	{
//		try {
//			customerRegistration.getChildren().clear();
//	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterCustomer.fxml"));
//			RegisterCustomerController controller = new RegisterCustomerController(session,customers, businesses, database);
//			loader.setController(controller);
//		
//			customerRegistration.getChildren().add(loader.load());
//			customerRegistration.getStylesheets().add(getClass().getResource("registrationPage.css").toExternalForm());
//		}catch(IOException e)
//		{
//			System.out.println(e);
//			session.addLog("Unable to load Customer register scenes");
//		}
//	}
//
//	public void onSelectionBusinessRegistration()
//	{
//		try {
//			businessRegistration.getChildren().clear();
//	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterBusiness.fxml"));
//			RegisterBusinessController controller = new RegisterBusinessController(session,customers, businesses, database);
//			loader.setController(controller);
//		
//			businessRegistration.getChildren().add(loader.load());
//			businessRegistration.getStylesheets().add(getClass().getResource("registrationPage.css").toExternalForm());
//		}catch(IOException e)
//		{
//			System.out.println(e);
//			session.addLog("Unable to load Customer register scenes");
//		}
//	}
//}
