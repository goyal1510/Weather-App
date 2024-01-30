import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp {
    public static void main(String[] args) {
        try {
            // Specify the URL of the weather API
            String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY";
            // Send an HTTP GET request to the API
            HttpURLConnection connection = (HttpURLConnection) new URL(weatherUrl).openConnection();
            connection.setRequestMethod("GET");
            // Get the response from the API
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                // Parse the response as needed
                String weatherData = response.toString();
                System.out.println("Weather Data: " + weatherData);
            } else {
                System.out.println("Error: Failed to retrieve weather data. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to connect to the weather API.");
            e.printStackTrace();
        }
    }
}