package jetty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyClient {
	public static void main(String[] args) throws IOException {
		// Make a request to /path1
		String response1 = sendGetRequest("http://localhost:8080/path1");
		System.out.println("Response from /path1: " + response1);

		// Make a request to /path2
		String response2 = sendGetRequest("http://localhost:8080/path2");
		System.out.println("Response from /path2: " + response2);

		// Make a request to an invalid path
		String response3 = sendGetRequest("http://localhost:8080/invalid");
		System.out.println("Response from /invalid: " + response3);
	}

	private static String sendGetRequest(String url) throws IOException {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		StringBuilder response = new StringBuilder();

		try {
			URL requestUrl = new URL(url);
			connection = (HttpURLConnection) requestUrl.openConnection();
			connection.setRequestMethod("GET");

			// Get the response
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (connection != null) {
				connection.disconnect();
			}
		}

		return response.toString();
	}
}
