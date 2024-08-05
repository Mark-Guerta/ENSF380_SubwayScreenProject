package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdvertisementDisplayTest {
	AdvertisementDisplay adDisplay;

	@BeforeEach
	void setUp() throws Exception {
		String [] trainList = {"R01", "G01", "B01"};
		String currentTrain = "R01";
		ArrayList<String[]> stationArray = new ArrayList<>();
		stationArray.add(new String[] {"Station1", "R01",  "0.1", "0.2"});
		stationArray.add(new String[] {"Station2", "G01", "0.3", "0.4"});
		adDisplay = new AdvertisementDisplay(trainList, currentTrain, stationArray);
		
		}
		
	


	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testExtraxtStationCoords() {
		JLabel trainLabel = new JLabel();
		adDisplay.extractStationCoords("R01", trainLabel);
		Point location = trainLabel.getLocation();
		Point expectedLocation = new Point((int) (648 * 0.1) , (int) (480 * 0.2));
		assertEquals(expectedLocation.x, location.x);
		assertEquals(expectedLocation.y, location.y);
		
	}
}
	