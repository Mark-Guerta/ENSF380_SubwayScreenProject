package ca.ucalgary.edu.ensf380;
import java.util.regex.*;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class weatherReportParse {

	public static void parse() {
		
		
		try {// catches exceptions at the end
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Enter City Name:");// prompts user to input the name of the city
			
			String cityID = scanner.nextLine().trim();//takes user input, trims it from whitespace and saves it into cityID
			Document document = Jsoup.connect("https://wttr.in/" + cityID).get();// parses and returns the website in a form of a document
			
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
		

		
			scanner.close();
		}catch (IOException e) {
			throw new IllegalArgumentException(" error while fetching");
			
		}
	}
}	
	
	 
	
