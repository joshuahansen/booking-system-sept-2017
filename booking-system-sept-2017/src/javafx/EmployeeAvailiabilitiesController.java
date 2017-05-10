package javafx;

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import main.AvailableTime;
import main.Session;
import users.Business;
import users.Employee;

public class EmployeeAvailiabilitiesController implements Initializable{
	
	private ArrayList<Business> businesses;
	public Button btn[][] = new Button[7][3];

	private int busPos;
	private int emplPos;
	
	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;
	@FXML private Button button4;
	@FXML private Button button5;
	@FXML private Button button6;
	@FXML private Button button7;
	@FXML private Button button8;
	@FXML private Button button9;
	@FXML private Button button10;
	@FXML private Button button11;
	@FXML private Button button12;
	@FXML private Button button13;
	@FXML private Button button14;
	@FXML private Button button15;
	@FXML private Button button16;
	@FXML private Button button17;
	@FXML private Button button18;
	@FXML private Button button19;
	@FXML private Button button20;
	@FXML private Button button21;

	@FXML private ComboBox<String> employeeCombo;
	@FXML private MenuButton employeeDropdown;
	@FXML private BorderPane empBorder;
    private final ObservableList<String> employeeList = FXCollections.observableArrayList();

	private ArrayList<MenuItem> menuButtons;
	private Session session;
	
	LocalTime midday = LocalTime.of(12, 00);
	LocalTime evening = LocalTime.of(17, 00);
	

	 public EmployeeAvailiabilitiesController(Session session, ArrayList<Business> businesses, int busPos)
	 {
	    	this.businesses = businesses;
	    	this.busPos = busPos;
	    	this.session = session;
	 }
	 
	 public void initializeButtonArray() 
	 { 
		    btn[0][0] = button1;
		    btn[0][1] = button2;
		    btn[0][2] = button3;
		    btn[1][0] = button4;
		    btn[1][1] = button5;
		    btn[1][2] = button6;
		    btn[2][0] = button7;
		    btn[2][1] = button8;
		    btn[2][2] = button9;
		    btn[3][0] = button10;
		    btn[3][1] = button11;
		    btn[3][2] = button12;
		    btn[4][0] = button13;
		    btn[4][1] = button14;
		    btn[4][2] = button15;
		    btn[5][0] = button16;
		    btn[5][1] = button17;
		    btn[5][2] = button18;
			btn[6][0] = button19;
			btn[6][1] = button20;
			btn[6][2] = button21;
		}
	 
	 public void addEmployeeDropdown() 
	 {
		 
		 for (int i = 0; i <= businesses.get(busPos).employees.size(); i++)
		 {
			 MenuItem menuItem = new MenuItem(businesses.get(busPos).employees.get(i).getFirstName());
			 menuButtons.add(menuItem);
			 employeeDropdown.getItems().add(menuItem);
		 }
	 }
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		empBorder.setStyle("-fx-background-color: #F9F9F9;");
		initializeButtonArray();
		for(int empNo = 0; empNo < businesses.get(busPos).employees.size(); empNo++)
		{
			employeeList.add(businesses.get(busPos).employees.get(empNo).getName());
		}
		employeeCombo.setItems(employeeList);
	}
	
	public void updateBoard(ActionEvent event) 
	{
		String employeeSelected = employeeCombo.getValue();
		
		for(int count = businesses.get(busPos).employees.size() - 1; count >= 0 ; count--)
			if(!employeeSelected.equalsIgnoreCase("Select") && employeeSelected.equals(businesses.get(busPos).employees.get(count).getName()))
			{
				loadButtons(businesses.get(busPos).employees.get(count));
				emplPos = count;
			}
	}

	 @FXML
	 protected void handleUpdateButtonAction(ActionEvent event) {
		 	session.addLog("Availability Button pressed");
		 	Button button = (Button) event.getSource();
	        updateButton(businesses.get(busPos).employees.get(emplPos), button);
	 }
	 
	 public void updateButton(Employee empl, Button but)
	 {
		 if(but.getText().equals("Not Available") || but.getText().equals(""))
		 {
			 but.setText("Available");
			 but.setStyle("-fx-text-fill: #00ce00");
		 }
		 
		 else if(but.getText().equals("Available") || but.getText().equals(""))
		 {
			 but.setText("Not Available");
			 but.setStyle("-fx-text-fill: #cc0000");
		 }
		 	
		 for(int i = 0; i < btn.length; i++)
		 {
			for(int j = 0; j < btn[i].length; j++)
			{
				if(i == 0 && j == 0)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Monday"))
	        			{
        					AvailableTime avail = new AvailableTime(businesses.get(busPos).businessHours.get(k).getStartTime(), midday , "Monday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 0 && j == 1)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Monday"))
	        			{
        					AvailableTime avail = new AvailableTime(midday, evening , "Monday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 0 && j == 2)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Monday"))
	        			{
        					AvailableTime avail = new AvailableTime(evening, businesses.get(busPos).businessHours.get(k).getEndTime() , "Monday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 1 && j == 0)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Tuesday"))
	        			{
        					AvailableTime avail = new AvailableTime(businesses.get(busPos).businessHours.get(k).getStartTime(), midday , "Tuesday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 1 && j == 1)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Tuesday"))
	        			{
        					AvailableTime avail = new AvailableTime(midday, evening , "Tuesday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 1 && j == 2)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Tuesday"))
	        			{
        					AvailableTime avail = new AvailableTime(evening, businesses.get(busPos).businessHours.get(k).getEndTime() , "Tuesday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 2 && j == 0)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Wednesday"))
	        			{
        					AvailableTime avail = new AvailableTime(businesses.get(busPos).businessHours.get(k).getStartTime(), midday , "Wednesday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 2 && j == 1)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Wednesday"))
	        			{
        					AvailableTime avail = new AvailableTime(midday, evening , "Wednesday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 2 && j == 2)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Wednesday"))
	        			{
        					AvailableTime avail = new AvailableTime(evening, businesses.get(busPos).businessHours.get(k).getEndTime() , "Wednesday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 3 && j == 0)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Thursday"))
	        			{
        					AvailableTime avail = new AvailableTime(businesses.get(busPos).businessHours.get(k).getStartTime(), midday , "Thursday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 3 && j == 1)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Thursday"))
	        			{
        					AvailableTime avail = new AvailableTime(midday, evening , "Thursday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 3 && j == 2)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Thursday"))
	        			{
        					AvailableTime avail = new AvailableTime(evening, businesses.get(busPos).businessHours.get(k).getEndTime() , "Thursday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 4 && j == 0)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Friday"))
	        			{
        					AvailableTime avail = new AvailableTime(businesses.get(busPos).businessHours.get(k).getStartTime(), midday , "Friday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 4 && j == 1)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Friday"))
	        			{
        					AvailableTime avail = new AvailableTime(midday, evening , "Friday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 4 && j == 2)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Friday"))
	        			{
        					AvailableTime avail = new AvailableTime(evening, businesses.get(busPos).businessHours.get(k).getEndTime() , "Friday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 5 && j == 0)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Saturday"))
	        			{
        					AvailableTime avail = new AvailableTime(businesses.get(busPos).businessHours.get(k).getStartTime(), midday , "Saturday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 5 && j == 1)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Saturday"))
	        			{
        					AvailableTime avail = new AvailableTime(midday, evening , "Saturday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 5 && j == 2)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Saturday"))
	        			{
        					AvailableTime avail = new AvailableTime(evening, businesses.get(busPos).businessHours.get(k).getEndTime() , "Saturday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 6 && j == 0)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Sunday"))
	        			{
        					AvailableTime avail = new AvailableTime(businesses.get(busPos).businessHours.get(k).getStartTime(), midday , "Sunday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 6 && j == 1)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Sunday"))
	        			{
        					AvailableTime avail = new AvailableTime(midday, evening , "Sunday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
				if(i == 6 && j == 2)
				{
					for(int k = 0; k < businesses.get(busPos).businessHours.size(); k++)
					{
						if(businesses.get(busPos).businessHours.get(k).getDay().equals("Sunday"))
	        			{
        					AvailableTime avail = new AvailableTime(evening, businesses.get(busPos).businessHours.get(k).getEndTime() , "Sunday");
        					empl.availableTimes.add(avail);
	        			}	 
					}
	   
				}
				
			}
			
		 }
		 
	 }
	
//	public void updateButton(Employee empl, Button button) 
//	{		
//		if(button.getText().equals("Not Availiable") || button.getText().equals(""))
//		{
//			button.setText("Availiable");
//			button.setStyle("-fx-text-fill: #00ce00");
//			
//		}
//		else if(button.getText().equals("Availiable") || button.getText().equals(""))
//		{
//			button.setText("Not Availiable");
//			button.setStyle("-fx-text-fill: #cc0000");
//
//		}	
//		
//		for(int i = 0; i <btn.length; i++) 
//		{
//	        for(int j = 0; j <btn[i].length; j++) 
//	        {	
//	        	if(btn[i][j].getText().equals("Not Availiable"))
//	        	{
//	        		if(j == 0)
//	        		{
//	        			empl.availableTimes[i][0] = 0;
//	        			empl.availableTimes[i][1] = 0;
//	        			empl.availableTimes[i][2] = 0;
//	        			empl.availableTimes[i][3] = 0;
//	        		}
//	        		else if(j == 1) 
//	        		{
//	        			empl.availableTimes[i][4] = 0;
//	        			empl.availableTimes[i][5] = 0;
//	        			empl.availableTimes[i][6] = 0;
//	        		}
//	        		else if(j == 2) 
//	        		{
//	        			empl.availableTimes[i][7] = 0;
//	        			empl.availableTimes[i][8] = 0;
//	        			empl.availableTimes[i][9] = 0;
//	        		}
//	        	}
//	        	else if(btn[i][j].getText().equals("Availiable"))
//	        	{
//	        		if(j == 0)
//	        		{
//	        			empl.availableTimes[i][0] = 1;
//	        			empl.availableTimes[i][1] = 1;
//	        			empl.availableTimes[i][2] = 1;
//	        			empl.availableTimes[i][3] = 1;
//	        		}
//	        		else if(j == 1) 
//	        		{
//	        			empl.availableTimes[i][4] = 1;
//	        			empl.availableTimes[i][5] = 1;
//	        			empl.availableTimes[i][6] = 1;
//	        		}
//	        		else if(j == 2) 
//	        		{
//	        			empl.availableTimes[i][7] = 1;
//	        			empl.availableTimes[i][8] = 1;
//	        			empl.availableTimes[i][9] = 1;
//	        		}
//	        	}
//	        }
//		}
//	}
//	
//	
//	public void loadButtons(Employee empl) 
//	{
//		
//		for(int i = 0; i <empl.availableTimes.length; i++) 
//		{
//	        for(int j = 0; j <empl.availableTimes[i].length; j++) 
//	        {
//	            
//	        	if (j == 0 || j == 1 || j == 2 || j == 3) 
//	        	{
//	        		if(empl.availableTimes[i][j] == 1)
//	        		{
//	        			btn[i][0].setText("Availiable");
//	        			btn[i][0].setStyle("-fx-text-fill: #00ce00");
//	        		}
//	        		else if(empl.availableTimes[i][j] == 0)
//	        		{
//	        			btn[i][0].setText("Not Availiable");
//	        			btn[i][0].setStyle("-fx-text-fill: #cc0000");
//
//	        		}
//	        	}
//	        	else if (j == 4 || j == 5 || j == 6)
//	        	{
//	        		if(empl.availableTimes[i][j] == 1)
//	        		{
//	        			btn[i][1].setText("Availiable");
//	        			btn[i][1].setStyle("-fx-text-fill: #00ce00");
//	        		}
//	        		else if(empl.availableTimes[i][j] == 0)
//	        		{
//	        			btn[i][1].setText("Not Availiable");
//	        			btn[i][1].setStyle("-fx-text-fill: #cc0000");
//	        		}
//	        	}
//	        	else if (j == 7 || j == 8 || j == 9)
//	        	{
//	        		if(empl.availableTimes[i][j] == 1)
//	        		{
//	        			btn[i][2].setText("Availiable");
//	        			btn[i][2].setStyle("-fx-text-fill: #00ce00");
//	        		}
//	        		else if(empl.availableTimes[i][j] == 0)
//	        		{
//	        			btn[i][2].setText("Not Availiable");
//	        			btn[i][2].setStyle("-fx-text-fill: #cc0000");
//	        		}
//	        	}    	
//	        }
//	    }
//	}
}
