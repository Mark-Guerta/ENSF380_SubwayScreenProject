package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.swing.JLabel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainDisplayTest {
	TrainDisplay trainDisplay;
	Announcer testAnnouncer;
	ArrayList<String[]> stationArray;

	@BeforeEach
	void setUp() throws Exception {
		testAnnouncer = new Announcer() {
			String station;
			
			public void setStation(String station){
				this.station = station;
				
				
			}
			
			public String getStation() {
				return station;
			}
		};
			
			
		
		stationArray = new ArrayList<>();
		stationArray.add(new String[] {"1", "R", "10", "R10" , "Station1"});
		stationArray.add(new String[] {"2", "B", "20", "B20" , "Station2"});
		stationArray.add(new String[] {"3", "G", "30", "G30" , "Station3"});
		stationArray.add(new String[] {"4", "R", "40", "R50" , "Station4"});
		stationArray.add(new String[] {"5", "B", "50", "B50" , "Station5"});
		trainDisplay = new TrainDisplay("RFF", stationArray, testAnnouncer);
	}
	
	

	@AfterEach
	void tearDown() throws Exception {
	}

	

	@Test
	void testGetCurrentStation() {
		trainDisplay.setCurrentStation(1);
		trainDisplay.updateDisplay();
		JLabel[] stations = trainDisplay.getStations();
		System.out.println("Station 0 Text:" + stations[0].getText());
		assertEquals("Station2", stations[0].getText());
		assertEquals("Station3", stations[1].getText());
		assertEquals("Station4", stations[2].getText());
		assertEquals("Station5", stations[3].getText());
		assertEquals("Station3", testAnnouncer.getStation());
		
	}

	
}
