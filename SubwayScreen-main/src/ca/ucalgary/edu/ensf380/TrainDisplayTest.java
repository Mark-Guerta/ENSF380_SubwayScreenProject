package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 	TrainDisplay.java
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */
class TrainDisplayTest {
	TrainDisplay trainDisplay;
	Announcer testAnnouncer;
	ArrayList<String[]> stationArray;
	String [] trainList = {"T1(R01, F)", "T2(G01, B)", "T3(B01, F)", "T4(R12, F)", "T5(G12, B)", "T6(B12, F)",
			"T7(R13, F)", "T8(G13, B)", "T9(B13, F)", "T10(R14, F)", "T11(G14, B)", "T12(B14, F)"};
	@BeforeEach
	void setUp() throws Exception {
		testAnnouncer = new Announcer();
		stationArray = SubwayScreen.readMapCSV("Map.csv");
		trainDisplay = new TrainDisplay("T1(R01, F)", stationArray, testAnnouncer);
	}
	
	

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testUpdateDisplay() {
		assertDoesNotThrow(() -> trainDisplay.updateDisplay());
	}

	@Test
	void testStationMatcher() {
		assertDoesNotThrow(() -> trainDisplay.stationMatcher("T1(R01, F)"));
		assertEquals(1, trainDisplay.getCurrentStation());
	}
}
