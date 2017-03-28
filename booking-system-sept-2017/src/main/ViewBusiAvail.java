package main;
import java.util.ArrayList;

import users.Business;
import users.Customer;

public class ViewBusiAvail {
	
	private String[] services = {"WORKOUT", "YOGA CLASS", "CONSULATION"};
	private String[] weekDay = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	private int TABLE_HEIGHT = 8;
	private int TABLE_WIDTH = 5;
	private String[][] serviceTime = new String[TABLE_HEIGHT][TABLE_WIDTH];
    private String notAvailable = "XXXXXXXXXXX";
	
    public ViewBusiAvail() {
		
	}
	
	public void viewAvailabilities(ArrayList<Customer> customers, ArrayList<Business> businesses, int service) {
		
		service--;
		//initializes the times for each weekday.
		initServiceTable(service);
		
		//prints actual table to console
		printTable(service);
		
	}
	
	void printTable(int service) {
		
		//updates user on which service times is being viewed.
		System.out.println("VIEWING TIMES FOR" + services[service] + ":");
		printBorder();
		System.out.print("     ");
		
		//prints the weekday label heads
		for (int counter = 0; counter < TABLE_WIDTH; counter++) {
			System.out.print(weekDay[counter]);
			
			//space between each weekday
			System.out.print("      ");
		}
		
		System.out.println("");
		printBorder();
		
		//prints the times for each weekday
		for (int row = 0; row < TABLE_HEIGHT; row++) {
			System.out.print("| ");
			
			for (int col = 0; col < TABLE_WIDTH; col++) {
				System.out.print(serviceTime[row][col]);
				System.out.print(" | ");
			}
			
			System.out.println("");
			printBorder();
			
		}
	}
	
	void initServiceTable(int service) {
		
		//initialises the default values for all array elements
		for (int col = 0; col < TABLE_WIDTH; col++) {
			for (int row = 0; row < TABLE_HEIGHT; row++) {
				
				serviceTime[0][col] = " 9:00-10:00";
					
				serviceTime[1][col] = "10:00-11:00";
								
				serviceTime[2][col] = "11:00-12:00";
				
				serviceTime[3][col] = "12:00-13:00";
					
				serviceTime[4][col] = "13:00-14:00";
					
		     	serviceTime[5][col] = "14:00-15:00";
					
				serviceTime[6][col] = "15:00-16:00";
					
				serviceTime[7][col] = "16:00-17:00";
					
				//manually set array availabilities for notAvailable for each service.?
				if (service == 0) {
					serviceTime[row][0] = notAvailable;
					serviceTime[2][col] = notAvailable;
					serviceTime[3][3] = notAvailable;
					serviceTime[2][4] = notAvailable;
						
				} else if (service == 1) {
						
					serviceTime[row][3] = notAvailable;
					serviceTime[2][2] = notAvailable;
					serviceTime[3][1] = notAvailable;						
					serviceTime[0][2] = notAvailable;
					serviceTime[6][4] = notAvailable;
					
				} else if (service == 2) {
					serviceTime[4][col] = notAvailable;
					serviceTime[3][3] = notAvailable;
					serviceTime[1][3] = notAvailable;						
					serviceTime[5][2] = notAvailable;
					serviceTime[5][2] = notAvailable;
					serviceTime[1][0] = notAvailable;
						
				} else {
					System.out.println("invalid service");
				}
			}
		}
	}
	
	void printBorder() {
		System.out.println("-----------------------------------------------------------------------");
	}
}
