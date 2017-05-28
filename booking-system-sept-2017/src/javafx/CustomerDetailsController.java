package javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import main.Session;
import users.*;

/**
 *  Controller for customer details page. 
 *  Page displays customers details in a simple and easy to read format
 * @author SEPT Team 6
 *
 */
public class CustomerDetailsController implements Initializable {
    @FXML private Text custDetailsUsernameData;
    @FXML private Text custDetailsFirstNameData;
    @FXML private Text custDetailsLastNameData;
    @FXML private Text custDetailsAddressData;
    @FXML private Text custDetailsPhoneData;
        
    private Session session;
    private Customer customer;
   /**
    * Constructor for customer details controller
    * @param session for system runtime logging
    * @param customer that is logged in
    */
    public CustomerDetailsController(Session session, Customer customer)
    {
    	this.customer = customer;
    	this.session = session;
    }
    /**
     * set the username text of the customer
     * @param username String to set the username to
     */
    public void setUsername(String username)
    {
    	custDetailsUsernameData.setText(username);
    }
    /**
     * set the first name text of the customer
     * @param firstName String to set the first name to
     */
    public void setFirstName(String firstName)
    {
    	custDetailsFirstNameData.setText(firstName);
    }
    /**
     * set the last name text of the customer
     * @param lastName String to set the text to
     */
    public void setLastName(String lastName)
    {
    	custDetailsLastNameData.setText(lastName);
    }
    /**
     * set the address text of the customer
     * @param address String to display
     */
    public void setAddress(String address)
    {
    	custDetailsAddressData.setText(address);
    }
    /**
     * set the phone number text of the customer
     * @param phone String to display
     */
    public void setPhone(String phone)
    {
    	custDetailsPhoneData.setText(phone);
    }
    /**
     * initialize the text to the customer values
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		session.addLog("Load custoemr details");
		setUsername(customer.getUsername());
		setFirstName(customer.getFirstName());
		setLastName(customer.getLastName());
		setAddress(customer.getAddress());
		setPhone(customer.getContactNumber());	
	}
    
}
    