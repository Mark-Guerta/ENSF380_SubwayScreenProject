package seifeldinyoussef.ucalgary.ca;
import java.util.regex.*;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class weatherReportParse {

	public static void main(String[] args) {
		
		try {
			//String html = Jsoup.connect("https://openweathermap.org/").get.html;
			
			Document document = Jsoup.connect("https://openweathermap.org/").get();
			//String websiteContent = document.html();

			System.out.println(document);
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Enter City ID:");
			
			String cityID = scanner.nextLine();
			//String cityIDReg ="\\b[0-9]{7}\\b";
			//System.out.println(cityID);
			
			Pattern cityIDPattern = Pattern.compile(cityID);
			Matcher matcher = cityIDPattern.matcher(document.html());
		
			if(matcher.find()) {
				System.out.println(matcher.group());	
			} else {
			System.out.println("City ID not found");
			}
		

		
			scanner.close();
		}catch (IOException e) {
			System.out.println(" City ID not found");
			e.printStackTrace();
			
		}
	}
}	
	
	 
	
