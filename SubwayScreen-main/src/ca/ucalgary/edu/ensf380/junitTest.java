package ca.ucalgary.edu.ensf380;
import org.junit.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;





public class junitTest {

	public class weatherReportParseTest{
		
		
		@Test
		public void testCityName() throws IOException {
			String cityID = "Calgary";
			//String[] args = {cityID};
			
			
			String expResult =  "Calgary" +  java.time.LocalTime.now() +" Weather report: Calgary";
			Document result = Jsoup.connect("https://wttr.in/" + cityID).timeout(10000).get();
			
			assertEquals(expResult, result);
			
			System.out.println( expResult + result);
		}
		@Test
		public void invalidCityName() throws IOException {
			String cityID = "lovecity";
			//String[] args = {cityID};
			
			
			String expResult =  "City ID not found";
			Document result = Jsoup.connect("https://wttr.in/" + cityID).timeout(10000).get();
			
			assertEquals(expResult, result);
			System.out.println( expResult + result);
		}
		
	}




}
