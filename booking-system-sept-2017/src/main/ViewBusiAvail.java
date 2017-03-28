package main;
import java.util.ArrayList;

import users.Business;
import users.Customer;

public class ViewBusiAvail {
	
	private String[] services = {"WORKOUT", "YOGA CLASS", "CONSULATION"};
	private String[] weekDay = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	private String[][] serviceTime = new String[8][5];
    private String notAvailable = "XXXXXXXXXXX";
	public ViewBusiAvail() {
		
	}
	
	public boolean viewAvailabilities(ArrayList<Customer> customers, ArrayList<Business> businesses, int service) {
		service--;
		//initializes the times for each weekday.
		initServiceTable(service);
		
		//prints actual table to console
		printTable(service);
		return true;
	}
	
	void printTable(int service) {
		System.out.println("VIEWING TIMES FOR: " + services[service]);
		printBorder();
		System.out.print("     ");
		
		//prints the weekday label heads
		for (int counter = 0; counter < 5; counter++) {
			System.out.print(weekDay[counter]);
			
			//space between each weekday
			System.out.print("      ");
		}
		
		System.out.println("");
		printBorder();
		
		//prints the times for each weekday
		for (int rows = 0; rows < 8; rows++) {
			System.out.print("| ");
			
			for (int col = 0; col < 5; col++) {
				System.out.print(serviceTime[rows][col]);
				System.out.print(" | ");
			}
			
			System.out.println("");
			printBorder();
			
		}
	}
	
	void initServiceTable(int service) {
		if (service == 0) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 5; col++) {
				
					serviceTime[0][col] = " 9:00-10:00";
					
					serviceTime[1][col] = "10:00-11:00";
								
					serviceTime[2][col] = "11:00-12:00";
				
					serviceTime[3][col] = "12:00-13:00";
					
					serviceTime[4][col] = "13:00-14:00";
					
					serviceTime[5][col] = "14:00-15:00";
					
					serviceTime[6][col] = "15:00-16:00";
					
					serviceTime[7][col] = "16:00-17:00";
		
				}
			}
		} else if (service == 1) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 5; col++) {
				
					serviceTime[0][col] = " 9:00-10:00";
				
					serviceTime[1][col] = "10:00-11:00";
							
					serviceTime[2][col] = "11:00-12:00";
				
					serviceTime[3][col] = "12:00-13:00";
				
					serviceTime[4][col] = "13:00-14:00";
				
					serviceTime[5][col] = "14:00-15:00";
				
					serviceTime[6][col] = "15:00-16:00";
				
					serviceTime[7][col] = "16:00-17:00";
				
					serviceTime[5][2] = notAvailable;
					serviceTime[2][col] = notAvailable;
					serviceTime[4][col] = notAvailable;
					serviceTime[row][1] = notAvailable;
	
				}
			}
		} else if (service == 2) {
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 5; col++) {
				
					serviceTime[0][col] = " 9:00-10:00";
				
					serviceTime[1][col] = "10:00-11:00";
							
					serviceTime[2][col] = "11:00-12:00";
			
					serviceTime[3][col] = "12:00-13:00";
				
					serviceTime[4][col] = "13:00-14:00";
				
					serviceTime[5][col] = "14:00-15:00";
				
					serviceTime[6][col] = "15:00-16:00";
				
					serviceTime[7][col] = "16:00-17:00";
				}
			}
		}
	}
	
	void printBorder() {
		System.out.println("-----------------------------------------------------------------------");
	}
}
