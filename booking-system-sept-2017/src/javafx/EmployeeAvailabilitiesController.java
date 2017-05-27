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
import main.AvailableTime;
import main.Session;
import users.Business;
import users.Employee;

public class EmployeeAvailabilitiesController implements Initializable{

	private Business business;
	public Button btn[][] = new Button[7][3];


	private int emplPos;
	
	@FXML private Button monM;
	@FXML private Button monA;
	@FXML private Button monE;
	@FXML private Button tueM;
	@FXML private Button tueA;
	@FXML private Button tueE;
	@FXML private Button wedM;
	@FXML private Button wedA;
	@FXML private Button wedE;
	@FXML private Button thuM;
	@FXML private Button thuA;
	@FXML private Button thuE;
	@FXML private Button friM;
	@FXML private Button friA;
	@FXML private Button friE;
	@FXML private Button satM;
	@FXML private Button satA;
	@FXML private Button satE;
	@FXML private Button sunM;
	@FXML private Button sunA;
	@FXML private Button sunE;

	@FXML private ComboBox<String> employeeCombo;
	@FXML private MenuButton employeeDropdown;
	@FXML private BorderPane empBorder;
    private final ObservableList<String> employeeList = FXCollections.observableArrayList();

	private ArrayList<MenuItem> menuButtons;
	private Session session;
	
	LocalTime midday = LocalTime.of(12, 00);
	LocalTime evening = LocalTime.of(17, 00);
	

	 public EmployeeAvailabilitiesController(Session session, Business business)
	 {
	    	this.business = business;
	    	this.session = session;
	 }
	 
//	 public void initializeButtonArray() 
//	 { 
//		    btn[0][0] = button1;
//		    btn[0][1] = button2;
//		    btn[0][2] = button3;
//		    btn[1][0] = button4;
//		    btn[1][1] = button5;
//		    btn[1][2] = button6;
//		    btn[2][0] = button7;
//		    btn[2][1] = button8;
//		    btn[2][2] = button9;
//		    btn[3][0] = button10;
//		    btn[3][1] = button11;
//		    btn[3][2] = button12;
//		    btn[4][0] = button13;
//		    btn[4][1] = button14;
//		    btn[4][2] = button15;
//		    btn[5][0] = button16;
//		    btn[5][1] = button17;
//		    btn[5][2] = button18;
//			btn[6][0] = button19;
//			btn[6][1] = button20;
//			btn[6][2] = button21;
//		}
	 
	 public void addEmployeeDropdown() 
	 {
		 
		 for (int i = 0; i <= business.getEmployees().size(); i++)
		 {
			 MenuItem menuItem = new MenuItem(business.getEmployees().get(i).getFirstName());
			 menuButtons.add(menuItem);
			 employeeDropdown.getItems().add(menuItem);
		 }
	 }
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		empBorder.setStyle("-fx-background-color: #F9F9F9;");
	//	initializeButtonArray();
		for(int empNo = 0; empNo < business.getEmployees().size(); empNo++)
		{
			employeeList.add(business.getEmployees().get(empNo).getName());
		}
		employeeCombo.setItems(employeeList);
	}
	
	public void updateBoard(ActionEvent event) 
	{
		String employeeSelected = employeeCombo.getValue();
		
		for(int count = business.getEmployees().size() - 1; count >= 0 ; count--)
			if(!employeeSelected.equalsIgnoreCase("Select") && employeeSelected.equals(business.getEmployees().get(count).getName()))
			{
				loadButtons(business.getEmployees().get(count));
				emplPos = count;
			}
	}

	 @FXML
	 protected void handleUpdateButtonAction(ActionEvent event) {
		 	session.addLog("Availability Button pressed");
		 	Button button = (Button) event.getSource();
	        updateButton(business.getEmployees().get(emplPos), button);
	 }
	 
	 public void updateButton(Employee empl, Button but)
	 {
		 if(but.getText().equals("Not Availiable") || but.getText().equals(""))
		 {
			 but.setText("Availiable");
			 but.setStyle("-fx-text-fill: #00ce00");
			 			 
			 if(but.getId().equals("monM"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Monday"))
					{
							AvailableTime avail = new AvailableTime(business.getBusinessHours().get(k).getStartTime(), midday, "Monday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("monA"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Monday"))
					{
							AvailableTime avail = new AvailableTime(midday, evening, "Monday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("monE"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Monday"))
					{
							AvailableTime avail = new AvailableTime(evening, business.getBusinessHours().get(k).getEndTime(), "Monday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("tueM"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Tuesday"))
					{
							AvailableTime avail = new AvailableTime(business.getBusinessHours().get(k).getStartTime(), midday, "Tuesday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("tueA"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Tuesday"))
					{
							AvailableTime avail = new AvailableTime(midday, evening, "Tuesday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("tueE"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Tuesday"))
					{
							AvailableTime avail = new AvailableTime(evening, business.getBusinessHours().get(k).getEndTime(), "Tuesday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("wedM"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Wednesday"))
					{
							AvailableTime avail = new AvailableTime(business.getBusinessHours().get(k).getStartTime(), midday, "Wednesday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("wedA"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Wednesday"))
					{
							AvailableTime avail = new AvailableTime(midday, evening, "Wednesday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("wedE"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Wednesday"))
					{
							AvailableTime avail = new AvailableTime(evening, business.getBusinessHours().get(k).getEndTime(), "Wednesday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("thuM"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Thursday"))
					{
							AvailableTime avail = new AvailableTime(business.getBusinessHours().get(k).getStartTime(), midday, "Thursday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("thuA"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Thursday"))
					{
							AvailableTime avail = new AvailableTime(midday, evening, "Thursday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("thuE"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Thursday"))
					{
							AvailableTime avail = new AvailableTime(evening, business.getBusinessHours().get(k).getEndTime(), "Thursday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("friM"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Friday"))
					{
							AvailableTime avail = new AvailableTime(business.getBusinessHours().get(k).getStartTime(), midday, "Friday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("friA"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Friday"))
					{
							AvailableTime avail = new AvailableTime(midday, evening, "Friday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("friE"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Friday"))
					{
							AvailableTime avail = new AvailableTime(evening, business.getBusinessHours().get(k).getEndTime(), "Friday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("satM"))
			 {
				 System.out.println("test3");
				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Saturday"))
					{
							AvailableTime avail = new AvailableTime(business.getBusinessHours().get(k).getStartTime(), midday, "Saturday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("satA"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Saturday"))
					{
							AvailableTime avail = new AvailableTime(midday, evening, "Saturday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("satE"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Saturday"))
					{
							AvailableTime avail = new AvailableTime(evening, business.getBusinessHours().get(k).getEndTime(), "Saturday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("sunM"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Sunday"))
					{
							AvailableTime avail = new AvailableTime(business.getBusinessHours().get(k).getStartTime(), midday, "Sunday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("sunA"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Sunday"))
					{
							AvailableTime avail = new AvailableTime(midday, evening, "Sunday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
			 if(but.getId().equals("sunE"))
			 {

				 for(int k = 0; k < business.getBusinessHours().size(); k++)
				 {

					if(business.getBusinessHours().get(k).getDay().equals("Sunday"))
					{
							AvailableTime avail = new AvailableTime(evening, business.getBusinessHours().get(k).getEndTime(), "Sunday");
							empl.availableTimes.add(avail);
							System.out.println("Added");
					}
				}
			 }
			 
		 }
		 
		 else if(but.getText().equals("Availiable") || but.getText().equals(""))
		 {
			 but.setText("Not Availiable");
			 but.setStyle("-fx-text-fill: #cc0000");

			 if(but.getId().equals("monM"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Monday") && empl.availableTimes.get(l).getEndTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("monA"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Monday") && empl.availableTimes.get(l).getStartTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("monE"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{
			
					if(empl.availableTimes.get(l).getDay().equals("Monday") && empl.availableTimes.get(l).getStartTime().equals(evening))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("tueM"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Tuesday") && empl.availableTimes.get(l).getEndTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("tueA"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Tuesday") && empl.availableTimes.get(l).getStartTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("tueE"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{
			
					if(empl.availableTimes.get(l).getDay().equals("Tuesday") && empl.availableTimes.get(l).getStartTime().equals(evening))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("wedM"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Wednesday") && empl.availableTimes.get(l).getEndTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("wedA"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Wednesday") && empl.availableTimes.get(l).getStartTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("wedE"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{
			
					if(empl.availableTimes.get(l).getDay().equals("Wednesday") && empl.availableTimes.get(l).getStartTime().equals(evening))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("thuM"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Tuesday") && empl.availableTimes.get(l).getEndTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("thuA"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Thursday") && empl.availableTimes.get(l).getStartTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("thuE"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{
			
					if(empl.availableTimes.get(l).getDay().equals("Thursday") && empl.availableTimes.get(l).getStartTime().equals(evening))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("friM"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Friday") && empl.availableTimes.get(l).getEndTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("friA"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Friday") && empl.availableTimes.get(l).getStartTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("friE"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{
			
					if(empl.availableTimes.get(l).getDay().equals("Friday") && empl.availableTimes.get(l).getStartTime().equals(evening))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("satM"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Saturday") && empl.availableTimes.get(l).getEndTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("satA"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Saturday") && empl.availableTimes.get(l).getStartTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("satE"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{
			
					if(empl.availableTimes.get(l).getDay().equals("Saturday") && empl.availableTimes.get(l).getStartTime().equals(evening))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("sunM"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Sunday") && empl.availableTimes.get(l).getEndTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("sunA"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{

					if(empl.availableTimes.get(l).getDay().equals("Sunday") && empl.availableTimes.get(l).getStartTime().equals(midday))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
			 
			 if(but.getId().equals("sunE"))
			 {
				for(int l = 0; l < empl.availableTimes.size(); l++)
				{
			
					if(empl.availableTimes.get(l).getDay().equals("Sunday") && empl.availableTimes.get(l).getStartTime().equals(evening))
					{
						empl.availableTimes.remove(l);
						System.out.println("Deleted");
					}
				}
			}
		}		 
	 }
	
	 public void loadButtons(Employee empl) 
	 {
		 for(int i = 0; i < empl.availableTimes.size(); i++)
		 {

			 if(empl.availableTimes.get(i).getDay().equals("Monday"))
			 {

				if(empl.availableTimes.get(i).getEndTime().equals(midday))
				{
					 monM.setText("Availiable");
					 monM.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					monM.setText("Not Availiable");
					monM.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(midday))
				{
					 monA.setText("Availiable");
					 monA.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					monA.setText("Not Availiable");
					monA.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(evening))
				{

					 monE.setText("Availiable");
					 monE.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					monE.setText("Not Availiable");
					monE.setStyle("-fx-text-fill: #cc0000");
				}
			 }	
			
			if(empl.availableTimes.get(i).getDay().equals("Tuesday"))
			{
				if(empl.availableTimes.get(i).getEndTime().equals(midday))
				{
					 tueM.setText("Availiable");
					 tueM.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					tueM.setText("Not Availiable");
					tueM.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(midday))
				{
					 tueA.setText("Availiable");
					 tueA.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					tueA.setText("Not Availiable");
					tueA.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(evening))
				{
					 tueE.setText("Availiable");
					 tueE.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					tueE.setText("Not Availiable");
					tueE.setStyle("-fx-text-fill: #cc0000");
				}
				
			}

		
			if(empl.availableTimes.get(i).getDay().equals("Wednesday"))
			{
				
				if(empl.availableTimes.get(i).getEndTime().equals(midday))
				{
					 wedM.setText("Availiable");
					 wedM.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					wedM.setText("Not Availiable");
					wedM.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(midday))
				{
					 wedA.setText("Availiable");
					 wedA.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					wedA.setText("Not Availiable");
					wedA.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(evening))
				{
					 wedE.setText("Availiable");
					 wedE.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					wedE.setText("Not Availiable");
					wedE.setStyle("-fx-text-fill: #cc0000");
				}
			}
		
			if(empl.availableTimes.get(i).getDay().equals("Thursday"))
			{	
				
				if(empl.availableTimes.get(i).getEndTime().equals(midday))
				{
					 thuM.setText("Availiable");
					 thuM.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					thuM.setText("Not Availiable");
					thuM.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(midday))
				{
					 thuA.setText("Availiable");
					 thuA.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					thuA.setText("Not Availiable");
					thuA.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(evening))
				{
					 thuE.setText("Availiable");
					 thuE.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					thuE.setText("Not Availiable");
					thuE.setStyle("-fx-text-fill: #cc0000");
				}
			}
		
			if(empl.availableTimes.get(i).getDay().equals("Friday"))
			{
				System.out.println("test");
				if(empl.availableTimes.get(i).getEndTime().equals(midday))
				{
					System.out.println("test55");

					 friM.setText("Availiable");
					 friM.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					friM.setText("Not Availiable");
					friM.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(midday))
				{
					 friA.setText("Availiable");
					 friA.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					friA.setText("Not Availiable");
					friA.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(evening))
				{
					System.out.println("test2");

					 friE.setText("Availiable");
					 friE.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					friE.setText("Not Availiable");
					friE.setStyle("-fx-text-fill: #cc0000");
				}
				
			}
		
			if(empl.availableTimes.get(i).getDay().equals("Saturday"))
			{	
				if(empl.availableTimes.get(i).getEndTime().equals(midday))
				{
					 satM.setText("Availiable");
					 satM.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					satM.setText("Not Availiable");
					satM.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(midday))
				{
					 satA.setText("Availiable");
					 satA.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					satA.setText("Not Availiable");
					satA.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(evening))
				{
					 satE.setText("Availiable");
					 satE.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					satE.setText("Not Availiable");
					satE.setStyle("-fx-text-fill: #cc0000");
				}
			}
				
			if(empl.availableTimes.get(i).getDay().equals("Sunday"))
			{
				if(empl.availableTimes.get(i).getEndTime().equals(midday))
				{
					 sunM.setText("Availiable");
					 sunM.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					sunM.setText("Not Availiable");
					sunM.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(midday))
				{
					 sunA.setText("Availiable");
					 sunA.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					sunA.setText("Not Availiable");
					sunA.setStyle("-fx-text-fill: #cc0000");
				}
				if(empl.availableTimes.get(i).getStartTime().equals(evening))
				{
					 sunE.setText("Availiable");
					 sunE.setStyle("-fx-text-fill: #00ce00");
				}
				else
				{
					sunE.setText("Not Availiable");
					sunE.setStyle("-fx-text-fill: #cc0000");
				}
				
						
			}
			 
		 }
			
	 }	
}
