package ca.ucalgary.edu.ensf380;

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;







public class NewsAPI {
    String description;

    public NewsAPI(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

class InvokeApi {
    private static final String URL1 = "https://api.thenewsapi.com/v1/news/all";
    private static final String API_KEY = "EJFVfpkyGigzkTt2nBCGrRZYNlzMsfCc6j6tshOh";
    private static final String LANG = "en";

    public static String fetchNews(String keyword) throws Exception {
        String authUrl = URL1 + "?api_token=" + API_KEY + "&language=" + LANG + "&search=" + keyword;
        URI uri = new URI(authUrl);
        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("Response Code: " + responseCode);
        }

        StringBuilder informationString = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                informationString.append(inputLine);
            }
        }

        return informationString.toString();
    }
}

 class NewsAPI1 {

    public static String[] mainNews(String[] args) {
        String keyword = "";

        if (args.length > 2) {
            keyword = args[2];
        }

        try {
            String jsonResponse = InvokeApi.fetchNews(keyword);
            return parseNews(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private static String[] parseNews(String jsonResponse) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
            JSONArray dataArray = (JSONArray) jsonObject.get("data");

            String[] newsDescriptions = new String[dataArray.size()];

            for (int i = 0; i < dataArray.size(); i++) {
                JSONObject newsData = (JSONObject) dataArray.get(i);
                String description = (String) newsData.get("description");
                if (description != null) {
                    newsDescriptions[i] = description.replaceAll("\\\\n", " ").replaceAll("\\\\u[\\d|[A-Z]]+", "");
                } else {
                    newsDescriptions[i] = "No description available";
                }
            }

            return newsDescriptions;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[0];
        }
    }
}
/*public class NewsAPI{
	
	
	public static String[] mainNews(String[] args ) {
		String keyword = "";
		
		if (args.length > 2) {
			keyword = args[2];
		}
		return invokeApi(keyword);
	}
	
	
		
	
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
}*/