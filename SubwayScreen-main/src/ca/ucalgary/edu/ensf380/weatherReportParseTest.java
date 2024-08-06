package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 	weatherReportParseTest.java
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */



class weatherReportParseTest {
	weatherReportParse.WeatherReportURL weatherReportURL;
	weatherReportParse.WeatherReportParser weatherReportParser;
	weatherReportParse.WeatherReportDisplay weatherReportDisplay;
// initializes instances before each run
	@BeforeEach
	void setUp() throws Exception {
		weatherReportURL = new weatherReportParse.WeatherReportURL();
		weatherReportParser = new weatherReportParse.WeatherReportParser();
		weatherReportDisplay = new weatherReportParse.WeatherReportDisplay();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMain() {
		//fail("Not yet implemented");
	}
	// verifies the method correctly fetches weather report and that it contains the inputted city
	@Test
	void testWeatherReportURL() {
		//String URL = "https://wttr.in/";
		String cityID = "Calgary";
		try {
			
			Document document = weatherReportURL.fetchWeatherReport(cityID);
			
			assertNotNull(document);
			assertTrue(document.html().contains(cityID));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	// verifies it returns null for invalid cities
	@Test
	void testWeatherReportURLInvalidCity() {
		//String URL = "https://wttr.in/";
		String cityID = "phone";
		String weatherReport = null;
		weatherReportDisplay.displayWeatherReport(cityID, weatherReport);
	}
	// verifies that the parsed results are not null
	@Test
	void testWeatherReportParser() {
		String cityID = "Calgary";
		String html = "<html><body><pre>Calgary weather: report</pre><body></html>";
		Document document = Jsoup.parse(html);
		String result = weatherReportParser.parseWeatherReport(document, cityID);
		assertNotNull(result);
		assertTrue(result.contains("Calgary weather"));
	}
	
	
	void testWeatherReportDisplay() {
		
	}

}
