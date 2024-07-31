package ca.ucalgary.edu.ensf380;
import java.util.regex.*;
import javax.swing.*;

import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class weatherReportParse {

	public static void weatherTimeMain(String[] args, JFrame frame) {
		
		
		try {// catches exceptions at the end
			
			Scanner scanner = new Scanner(System.in);
			
			//System.out.println("Enter City Name:");// prompts user to input the name of the city
			
			//String cityID = scanner.nextLine().trim();//takes user input, trims it from whitespace and saves it into cityID
			String cityID = "calgary";
			Document document = Jsoup.connect("https://wttr.in/" + cityID).get();// parses and returns the website in a form of a document
			
			//System.out.println(document);
			
			
			

			String cityIDReg =Pattern.quote(cityID);// determines the regex pattern of the user input
			Pattern cityIDPattern = Pattern.compile(cityIDReg, Pattern.CASE_INSENSITIVE);
			Matcher matcher = cityIDPattern.matcher(document.html());// looks for a match in the document with the user input
		
			if(matcher.find()) {
				//Elements 
				Elements weatherReport = document.select("pre");// if the input is found, it returns the <pre> elements which is likely to contain the weather report in the HTML document
				for (Element element : weatherReport) {// iterates over each element in the weather collection
					String weatherReportText = element.text();//retrieves the text content of the element
					if (weatherReportText.contains(cityID)) {// if the inputed city is found, it prints the information.
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
	public static void updateWeatherTime() {
		
	}
}	
	
	 
	
