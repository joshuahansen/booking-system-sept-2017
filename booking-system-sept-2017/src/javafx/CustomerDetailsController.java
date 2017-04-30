package javafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import main.Session;
import users.*;

 
public class CustomerDetailsController implements Initializable {
    @FXML private Text custDetailsUsernameData;
    @FXML private Text custDetailsFirstNameData;
    @FXML private Text custDetailsLastNameData;
    @FXML private Text custDetailsAddressData;
    @FXML private Text custDetailsPhoneData;
        
    private ArrayList<Customer> customers;
//    private ArrayList<Business> businesses;
    private int custPos;
    private Session session;
   
    public CustomerDetailsController(Session session, ArrayList<Customer> customers, int custPos)
    {
    	this.customers = customers;
//    	this.businesses = businesses;
    	this.custPos = custPos;
    	this.session = session;
    }
    
    public void setUsername(String username)
    {
    	custDetailsUsernameData.setText(username);
    }
    
    public void setFirstName(String firstName)
    {
    	custDetailsFirstNameData.setText(firstName);
    }
    
    public void setLastName(String lastName)
    {
    	custDetailsLastNameData.setText(lastName);
    }
    
    public void setAddress(String address)
    {
    	custDetailsAddressData.setText(address);
    }
    
    public void setPhone(String phone)
    {
    	custDetailsPhoneData.setText(phone);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		session.addLog("Load custoemr details");
		setUsername(customers.get(custPos).getUsername());
		setFirstName(customers.get(custPos).getFirstName());
		setLastName(customers.get(custPos).getLastName());
		setAddress(customers.get(custPos).getAddress());
		setPhone(customers.get(custPos).getContactNumber());	
	}
    
}
    