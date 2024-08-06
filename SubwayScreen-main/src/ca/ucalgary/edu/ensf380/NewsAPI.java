package ca.ucalgary.edu.ensf380;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 * 	NewsAPI.java
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */



public class NewsAPI{


	private String keyword = "";

	public String getKeyword(){
		/**
		 * method to get keyword
		 * @return current keyword as string
		 */
		return keyword;
	}	

	public void setKeyword(String keyword){
		/**
		 * method to set keyword
		 * @param keyword set for news search
		 */
		this.keyword = keyword;
	}	
	/**
	 * handles command line args
	 * @param  command line args 
	 * @return array with news description
	 */
	public static String[] mainNews(String[] args ) {
		String keyword = "";
		
		if (args.length > 2) {
			keyword = args[2];
		}
		return invokeApi(keyword);
	}
	
	
		
	/**
	 * fetches news based on keyword 
	 * @param parses and return description based on keyword 
	 * @return array with news description based on keyword
	 */
	private static String[] invokeApi(String keyword) {
		String [] newsDescription = null;
		try {
			String url1 = "https://api.thenewsapi.com/v1/news/all";//
			String apiKey = "EJFVfpkyGigzkTt2nBCGrRZYNlzMsfCc6j6tshOh";
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
				newsDescription = new String[dataArray.size()];
				Pattern snippetPattern = Pattern.compile("(\"description\":\".+?\")");

				for (int i = 0; i < dataArray.size(); i++) {
					JSONObject newsData = (JSONObject) dataArray.get(0);
					Matcher matcher = snippetPattern.matcher(newsData.toString());
					String description = null;
					if (matcher.find())
						description = matcher.group(1).replaceAll("\"description\":","").replaceAll("\\\\n\\\\n", "").replaceAll("\\\\u[\\d|[A-Z]]+", "");
					description = description.substring(1, description.length() - 1);
					newsDescription[i] = description;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsDescription;
	}
}
