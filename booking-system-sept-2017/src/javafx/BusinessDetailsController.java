package javafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import users.*;

/**
 * Controller used for displaying businesses details in business menu. 
 * @author Josh
 *
 */
public class BusinessDetailsController implements Initializable {
    @FXML private Text busDetailsUsernameData;
    @FXML private Text busDetailsBusNameData;
    @FXML private Text busDetailsFirstNameData;
    @FXML private Text busDetailsLastNameData;
    @FXML private Text busDetailsAddressData;
    @FXML private Text busDetailsPhoneData;
    
    ArrayList<Business> businesses;
    int busPos;
    
    /**
     * Constructor for the BusinessDetailsController
     * @param businesses Array of Businesses passed by reference to controller
     * @param busPos current user position in array passed to controller
     */
    public BusinessDetailsController(ArrayList<Business> businesses, int busPos)
    {
    	this.businesses = businesses;
    	this.busPos = busPos;
    }
    
    /**
     * Setter for username Text in BusinessMenu.fxml
     * @param username String for text to be displayed
     */
    public void setUsername(String username)
    {
    	busDetailsUsernameData.setText(username);
    }
    
    /**
     * Sets businessName Text object
     * @param busName String to be displayed
     */
    public void setBusName(String busName)
    {
    	this.busDetailsBusNameData.setText(busName);
    }

    /**
     * Sets first name Text object
     * @param firstName String of businesses owners first name to be displayed
     */
    public void setFirstName(String firstName)
    {
    	busDetailsFirstNameData.setText(firstName);
    }
    
    /**
     * Sets last name Text object
     * @param lastName String of owners last name to be displayed
     */
    public void setLastName(String lastName)
    {
    	busDetailsLastNameData.setText(lastName);
    }
    
    /**
     * Sets business's address Text object
     * @param address address of business to be displayed
     */
    public void setAddress(String address)
    {
    	busDetailsAddressData.setText(address);
    }
    
    /**
     * Sets phoneData Text object
     * @param phone String phone number of business to be dsisplayed
     */
    public void setPhone(String phone)
    {
    	busDetailsPhoneData.setText(phone);
    }

    /**
     * Initialize values when the controller is created to current business's details
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		session.addLog("Load Business details");
		setUsername(businesses.get(busPos).getUsername());
		setBusName(businesses.get(busPos).getBusinessName());
		setFirstName(businesses.get(busPos).getFirstName());
		setLastName(businesses.get(busPos).getLastName());
		setAddress(businesses.get(busPos).getAddress());
		setPhone(businesses.get(busPos).getContactNumber());
	}
    
}