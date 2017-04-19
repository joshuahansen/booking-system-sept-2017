package javafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import users.*;
 
public class BusinessDetailsController implements Initializable {
    @FXML private Text busDetailsUsernameData;
    @FXML private Text busDetailsBusNameData;
    @FXML private Text busDetailsFirstNameData;
    @FXML private Text busDetailsLastNameData;
    @FXML private Text busDetailsAddressData;
    @FXML private Text busDetailsPhoneData;
    
    ArrayList<Business> businesses;
    int busPos;
    
    public BusinessDetailsController(ArrayList<Business> businesses, int busPos)
    {
    	this.businesses = businesses;
    	this.busPos = busPos;
    }
    
    public void setUsername(String username)
    {
    	busDetailsUsernameData.setText(username);
    }
    
    public void setBusName(String busName)
    {
    	this.busDetailsBusNameData.setText(busName);
    }
    public void setFirstName(String firstName)
    {
    	busDetailsFirstNameData.setText(firstName);
    }
    
    public void setLastName(String lastName)
    {
    	busDetailsLastNameData.setText(lastName);
    }
    
    public void setAddress(String address)
    {
    	busDetailsAddressData.setText(address);
    }
    
    public void setPhone(String phone)
    {
    	busDetailsPhoneData.setText(phone);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUsername(businesses.get(busPos).getUsername());
		setBusName(businesses.get(busPos).getBusinessName());
		setFirstName(businesses.get(busPos).getFirstName());
		setLastName(businesses.get(busPos).getLastName());
		setAddress(businesses.get(busPos).getAddress());
		setPhone(businesses.get(busPos).getContactNumber());
	}
    
}