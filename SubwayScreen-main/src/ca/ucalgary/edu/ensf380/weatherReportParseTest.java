package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class weatherReportParseTest {
	weatherReportParse.WeatherReportURL weatherReportURL;
	weatherReportParse.WeatherReportParser weatherReportParser;
	weatherReportParse.WeatherReportDisplay weatherReportDisplay;

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
	@Test
	void testWeatherReportURLInvalidCity() {
		//String URL = "https://wttr.in/";
		String cityID = "phone";
		String weatherReport = null;
		weatherReportDisplay.displayWeatherReport(cityID, weatherReport);
	}
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
