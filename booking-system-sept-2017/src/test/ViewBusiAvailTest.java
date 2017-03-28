package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ViewBusiAvailTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void initServiceTableTest() {
		int TABLE_WIDTH = 5;
		int TABLE_HEIGHT = 8;
		String[][] serviceTable = new String[TABLE_HEIGHT][TABLE_WIDTH];
		
		assertEquals(null, serviceTable[0][0]);
		for (int col = 0; col < TABLE_WIDTH; col++) {
			
			for (int row = 0; row < TABLE_HEIGHT; row++) {
			
				serviceTable[0][col] = "9:00-10:00";
				assertEquals("9:00-10:00", serviceTable[0][col]);
				serviceTable[row][3] = "12:00-13:00";
				assertEquals("12:00-13:00", serviceTable[row][3]);
			}
		}
	}	
}
