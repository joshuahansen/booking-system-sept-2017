package javafx;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import main.*;
import users.*;

 
public class CustomerDetailsController {
    @FXML private Text custDetailsUsernameData;
    @FXML private Text custDetailsFirstNameData;
    @FXML private Text custDetailsLastNameData;
    @FXML private Text custDetailsAddressData;
    @FXML private Text custDetailsPhoneData;
    
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
}
    
    