package seifeldinyoussef.ucalgary.ca;
import java.util.regex.*;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class weatherReportParse {

	public static void main(String[] args) {
		
		
		try {
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Enter City ID:");
			
			String cityID = scanner.nextLine().trim();
			Document document = Jsoup.connect("https://wttr.in/" + cityID).get();
			
			//System.out.println(document);
			
			
			
			
			String cityIDReg =Pattern.quote(cityID);
			Pattern cityIDPattern = Pattern.compile(cityIDReg, Pattern.CASE_INSENSITIVE);
			Matcher matcher = cityIDPattern.matcher(document.html());
		
			if(matcher.find()) {
				Elements weatherReport = document.select("pre");
				for (Element element : weatherReport) {
					String weatherReportText = element.text();
					if (weatherReportText.contains(cityID)) {
						System.out.println(cityID + weatherReportText);	
						break;
					}
					
				}
					
			
			
			
			
			
			} else {
			System.out.println("City ID not found");
			}
		

		
			scanner.close();
		}catch (IOException e) {
			System.out.println(" error while fetching");
			e.printStackTrace();
			
		}
	}
}	
	
	 
	
