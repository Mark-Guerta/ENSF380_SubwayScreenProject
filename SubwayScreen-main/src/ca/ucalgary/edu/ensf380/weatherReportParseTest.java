package ca.ucalgary.edu.ensf380;
import ca.ucalgary.edu.ensf380.weatherReportParse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class weatherReportParseTest {

	

	@Test
	void testMainWeather() {
		String cityID = "Calgary";
		
		String expResult = "Calgary";
		String result = WeatherReportParse.weatherReportText();
				
		assertEquals(expResult, result);
	}

}
