package seifeldinyoussef.ucalgary.ca;
import java.util.regex.*;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class weatherReportParse {

	public static void main(String[] args) {
		//String html = Jsoup.connect("https://openweathermap.org/").get.html;
		try {
			Document document = Jsoup.connect("https://openweathermap.org/").get();
			//System.out.println(document);
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter City ID:");
			String cityID = scanner.nextLine();
		
			//System.out.println(cityID);
			Pattern cityIDMatcher = Pattern.compile("city ID" + Pattern.quote(cityID));
			Matcher matcher = cityIDMatcher.matcher(document.html());
		
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
	
	 
	
