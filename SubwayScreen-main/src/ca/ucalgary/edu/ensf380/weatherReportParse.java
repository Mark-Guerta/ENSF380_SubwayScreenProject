package ca.ucalgary.edu.ensf380;
import java.util.regex.*;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;





public class weatherReportParse{
	
	
	public static void weatherMain(String[] args) throws IllegalArgumentException{
		if (args.length > 1) {
			System.out.println("enter city name using command line");
	
		}
		else
			throw new IllegalArgumentException("Please add a city name");
		String cityID = args[1].trim();
		
		
		WeatherReportURL weatherReportURL = new WeatherReportURL();
		WeatherReportParser weatherReportParser = new WeatherReportParser();
		WeatherReportDisplay weatherReportDisplay = new WeatherReportDisplay();
		
		
		try {
			Document document = weatherReportURL.fetchWeatherReport(cityID);
			String weatherReport = weatherReportParser.parseWeatherReport(document, cityID);
			weatherReportDisplay.displayWeatherReport(cityID, weatherReport);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static class WeatherReportURL{
		String URL = "https://wttr.in/";
		Document fetchWeatherReport(String cityID) throws IOException {
			return Jsoup.connect(URL + cityID).timeout(10000).get();
		}
	}
	
	
	public static class WeatherReportParser{
		String parseWeatherReport(Document document,String cityID) {
			String cityIDReg =Pattern.quote(cityID);// determines the regex pattern of the user input
			Pattern cityIDPattern = Pattern.compile(cityIDReg, Pattern.CASE_INSENSITIVE);
			Matcher matcher = cityIDPattern.matcher(document.html());// looks for a match in the document with the user input
		
		    if (matcher.find()) {
			   Elements weatherReportElements = document.select("pre");// if the input is found, it returns the pre elemets which is likley to contain the weather report in the html document
			   for (Element element : weatherReportElements) {// iterates over each element in the weather collection
			    	String weatherReportText = element.text();//retrieves the text content of the element
			    	if (weatherReportText.contains(cityID)) {// if the inputted city is found, it prints the information.
					    return weatherReportText;
				}
			}	
		}
		return null;
	}
}		
		
		
	public static class WeatherReportDisplay{
		public void displayWeatherReport(String cityID, String weatherReport) {
			if (weatherReport != null) {
				System.out.println(cityID + " " + java.time.LocalTime.now() + " " + weatherReport);
				
			}else {
				System.out.println("City ID not found");
				System.exit(1);
			}
		}
	}
	
}






/*

public class weatherReportParse {

	
	
	public static void mainWeather(String[] args) {
		if (args.length == 0) {
			System.out.println("enter city name using command line");
			return;
		}
		String cityID = args[0].trim();
		
		try {// catches exceptions at the end
			
			
			Document document = Jsoup.connect("https://wttr.in/" + cityID).timeout(10000).get();// parses and returns the website in a form of a document. timeout is added in case it takes too long to fetch, so that it doesnt give errors.
			
			//System.out.println(document);
			
			
			
			
			String cityIDReg =Pattern.quote(cityID);// determines the regex pattern of the user input
			Pattern cityIDPattern = Pattern.compile(cityIDReg, Pattern.CASE_INSENSITIVE);
			Matcher matcher = cityIDPattern.matcher(document.html());// looks for a match in the document with the user input
		
			if(matcher.find()) {
				Elements weatherReport = document.select("pre");// if the input is found, it returns the pre elemets which is likley to contain the weather report in the html document
				for (Element element : weatherReport) {// iterates over each element in the weather collection
					String weatherReportText = element.text();//retrieves the text content of the element
					if (weatherReportText.contains(cityID)) {// if the inputted city is found, it prints the information.
						System.out.println(cityID+" "+ java.time.LocalTime.now()+"  "+ weatherReportText );	
						
						break;
						
					}
					
				}
					
			
			
			
			
			
			} else {
			System.out.println("City ID not found");
			}
		

		
			
		}catch (IOException e) {
			e.printStackTrace();
			
		}
	}
}	
	*/
	 
	
