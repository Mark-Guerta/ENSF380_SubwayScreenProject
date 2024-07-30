package subwayScreen;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*String invokeApi(String keyword) {
if (keyword) use it; else -> Call API without keyword
return response;
}

main() {
invokeApi();

Scanner in = take input();
keyword = input string();
while (keyword != null) {
invokeAPi(keyword);
}*/


public class newsApi{
	public static void main(String[] args ) {
		/*Scanner scanner2 = new Scanner (System.in);
		System.out.println("Enter a keyword:");
	    String keyword = scanner2.nextLine().trim();*/
		String keyword ="";
		try {
			String url1 = "https://api.thenewsapi.com/v1/news/all";//
			String apiKey = "fc7xQuAZMDSj0rMBFUEHh5a31KpbJHIxT6hZ4ZLL";
			String lang = "en";
			String authUrl = url1 + "?api_token="+ apiKey + "&language=" + lang   + "&search=" + keyword;
			URI uri = new URI(authUrl);
			URL url = uri.toURL();
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			
			
			int responseCode = connection.getResponseCode();
			
			if (responseCode != 200) {
				throw new RuntimeException("Response Code" + responseCode);
				
			}else {
				StringBuilder informationString = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());
				
				
				while(scanner.hasNext()) {
					informationString.append(scanner.nextLine());
				}
				scanner.close();
				System.out.println(informationString);
				
				JSONParser parse = new JSONParser();
				JSONObject jsonObject = (JSONObject) parse.parse(String.valueOf(informationString.toString()));
				JSONArray dataArray = (JSONArray) jsonObject.get("data");
				System.out.println(dataArray.get(0));
				JSONObject newsData = (JSONObject) dataArray.get(0);
				
				System.out.println(newsData);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		   	
		   	//scanner2.close();
			
		}
	}
}