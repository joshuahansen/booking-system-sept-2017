package javafx;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Session;
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
    @FXML private Button addImageButton;
    @FXML private ImageView busImageView;
    
    private Business business;
    private Session session;
    /**
     * Constructor for the BusinessDetailsController
     * @param businesses Array of Businesses passed by reference to controller
     * @param busPos current user position in array passed to controller
     */
    public BusinessDetailsController(Session session, Business business)
    {
    	this.business = business;
    	this.session = session;
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
    @FXML protected void handleAddImageButton(ActionEvent event) {
    	FileChooser fc = new FileChooser();
  
    	File selectedFile = fc.showOpenDialog(new Stage());
    
    	if(fc != null) {
   		 fc.getExtensionFilters().addAll(
   	                new FileChooser.ExtensionFilter("All Images", "*.*"),
   	                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
   	                new FileChooser.ExtensionFilter("PNG", "*.png")
   	            );
   		if(selectedFile != null) {
   			String filepath = selectedFile.toURI().toString();
   			Image busImage = new Image(filepath);
   			busImageView.setImage(busImage);
   			
   			}
    	}
    	
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		session.addLog("Load Business details");
		setUsername(business.getUsername());
		setBusName(business.getBusinessName());
		setFirstName(business.getFirstName());
		setLastName(business.getLastName());
		setAddress(business.getAddress());
		setPhone(business.getContactNumber());
	}
    
}