package ca.ucalgary.edu.ensf380;
import org.junit.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



public class weatherReportParseTest{
	
	
	@Test
	public void testCityName() {
		String cityID = "Calgary";
		String[] args = {cityID};
		
		
		String expResult =  "Calgary" +  java.time.LocalTime.now() +" Weather report: Calgary";
		Document result = Jsoup.connect("https://wttr.in/" + cityID).timeout(10000).get();
	}
	@Test
	public void invalidCityName() {
		String cityID = "lovecity";
		String[] args = {cityID};
		
		
		String expResult =  "City ID not found";
		Document result = Jsoup.connect("https://wttr.in/" + cityID).timeout(10000).get();
	}
	
}






public class junitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
