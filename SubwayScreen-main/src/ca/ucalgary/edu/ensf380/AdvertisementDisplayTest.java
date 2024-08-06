package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 	Tests Advertisement Display
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */
public class AdvertisementDisplayTest {
	// initializes ad display
	AdvertisementDisplay adDisplay;
// initializes the train lists
	@BeforeEach
	void setUp() throws Exception {
		String [] trainList = {"T1(R01, F)", "T2(G01, B)", "T3(B01, F)", "T4(R12, F)", "T5(G12, B)", "T6(B12, F)",
								"T7(R13, F)", "T8(G13, B)", "T9(B13, F)", "T10(R14, F)", "T11(G14, B)", "T12(B14, F)"};
		String currentTrain = "T1(R01, F)";
		ArrayList<String[]> stationArray = SubwayScreen.readMapCSV("Map.csv");
		adDisplay = new AdvertisementDisplay(trainList, currentTrain, stationArray);
		
		}
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testConstructor() {
		assertNotNull(adDisplay);
	}
	
	// tests the extraxt coords method to ensure it calculates the correct location of the given train
	@Test
	void testExtractStationCoords() {
		JLabel trainLabel = adDisplay.getAdLabel();
		adDisplay.extractStationCoords("T1(R01, F)", trainLabel);
		Point location = trainLabel.getLocation();
		Point expectedLocation = new Point((int) (648 * (8.756969333/1600)), (int) (480 *(14.79016876/1600)));
		assertEquals(expectedLocation.x, location.x);
		assertEquals(expectedLocation.y, location.y);
	}
	// tests update display method
	@Test
	void testUpdateDisplay() {
		assertDoesNotThrow(() -> adDisplay.updateDisplay());
	}
	// tests resize image method
	@Test 
	void testResizeImage() {
		assertDoesNotThrow(() -> adDisplay.resizeImage(new File("trainSymbols","TrainSymbolRed.png"), 1, 1));
	}
	// tests ad timer
	@Test
	void testAdTime() {
		assertDoesNotThrow(() -> adDisplay.adTimer());
	}
	// Setter and Getters
}
	