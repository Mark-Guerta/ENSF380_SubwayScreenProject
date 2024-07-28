package subwayScreen;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URI;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class newsApi{
	public static void newsApiFetch(String[] args ) {
		try {
			String urli = "https://www.thenewsapi.com/";
			String apiKey = "fc7xQuAZMDSj0rMBFUEHh5a31KpbJHIxT6hZ4ZLL";
			String authUrl = urli + apiKey;
			URI uri = new URI(authUrl);
			URL url = uri.toURL();
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			
		} 
	}
}