package ca.ucalgary.edu.ensf380;
import java.util.regex.*;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 	Parses a given website for weather content and returns it to user, using REGEX
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */



public class WeatherReportParse{
	
	/**
	 * handles command line args
	 * @param args Command Line Argument array (args[1] contains the city)
	 * @throws IllegalArgumentException illegal args exception if city is not found
	 */
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
		private String URL = "https://wttr.in/";
		/**
		 * method to return URL
		 * @return URL url as String
		 */
		
		public String getURL(){
			
			return URL;
		}
		/**
		 * method to set URL
		 * @param URL set URL
		 */
		public void setURL(String URL){
			this.URL = URL;
		}	


		
		/**
		 * @param cityID fetches cityID
		 * @return 	Document Jsoup doc contaning weather report 
		 * @throws IOException if it encounters an error while fetching
		 */
	public Document fetchWeatherReport(String cityID) throws IOException {
			return Jsoup.connect(URL + cityID).timeout(10000).get();
		}
	}
	
	
	public static class WeatherReportParser{
		private String cityID;

		public String getCityID(){
			/**
			 *method to get cityID
			 */
			return cityID;
		}

		public void setCityID(String cityID){
			this.cityID = cityID;
		}	


		/**
		 * @param document fetched containing weather report 
		 * @param cityID String of cityID
		 * @return weatherReportText weather report as a string
		 */
		
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

		private String cityID;
		private String weatherReport;


		public String getCityID(){
			return cityID;
		}
		public void setCityID(String cityID){
			this.cityID = cityID;
		}

		public String getWeatherReport(){
			/**
			 * method to get weather report
			 */
			return weatherReport;
		}

		public void setWeatherReport(String weatherReport){
			this.weatherReport = weatherReport;
		}	

		/**
		 * @param cityID displays city inputted in cityID
		 * @param weatherReport weather report is displayed
		 */
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






