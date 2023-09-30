package fi.tuni.design_driven_devs;

import java.net.URI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class PrimaryController implements Initializable {
    @FXML
    private Label temperatureLabel;
    
    @FXML
    private Label maxTemperatureLabel;
    
    @FXML
    private Label minTemperatureLabel;
    
    @FXML
    private Label feelsLikeLabel;
    
    @FXML
    private Label pressureLabel;
    
    @FXML
    private Label humidityLabel;
    
    @FXML
    private Label weatherDescriptionLabel;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private ImageView weatherImage;
    
    @FXML
    ImageView titleImage;
    
    @FXML
    private TextField latitudeInput;
    
    @FXML
    private TextField longitudeInput;
    
    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image weatherIconImage = new Image("https://cdn2.iconfinder.com/data/icons/weather-flat-14/64/weather02-512.png");
        titleImage.setImage(weatherIconImage);
    }
    
    @FXML protected void getWeatherData(ActionEvent event) {
        String latitude = latitudeInput.getText();
        String longitude = longitudeInput.getText();
        
        if (latitude.isEmpty() || longitude.isEmpty()) {
            errorLabel.setVisible(true);
            errorLabel.setText("Please provide latitude and longitude!");
        } else {
            try {
                double latitudeValue = Double.parseDouble(latitude);
                double longitudeValue = Double.parseDouble(longitude);
                HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?lat=" 
                                            + latitudeValue + "&lon=" + longitudeValue + "&APPID=7ab8c7f2be8a514f54ffadb36862fa74"))
                                    .method("GET", HttpRequest.BodyPublishers.noBody())
                                    .build();

                try {
                    errorLabel.setVisible(false);
                    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                    String responseBody = response.body();

                    // Parse the JSON response
                    JSONObject json = new JSONObject(responseBody);

                    // Extract lon, lat, and temp_max
                    double temp = json.getJSONObject("main").getDouble("temp");
                    double temp_min = json.getJSONObject("main").getDouble("temp_min");
                    double temp_max = json.getJSONObject("main").getDouble("temp_max");
                    double feels_like = json.getJSONObject("main").getDouble("feels_like");
                    double pressure = json.getJSONObject("main").getDouble("pressure");
                    double humidity = json.getJSONObject("main").getDouble("humidity");

                    temperatureLabel.setText("Temperature: " + convertKelvinToCelsius(temp) + "°C");
                    maxTemperatureLabel.setText("Max temperature: " + convertKelvinToCelsius(temp_max) + "°C");
                    minTemperatureLabel.setText("Min temperature: " + convertKelvinToCelsius(temp_min) + "°C");
                    feelsLikeLabel.setText("Feels like: " + convertKelvinToCelsius(feels_like) + "°C");
                    pressureLabel.setText("Pressure: " + convertKelvinToCelsius(pressure));
                    humidityLabel.setText("Humidity: " + humidity + "%");
                    String iconCode = json.getJSONArray("weather").getJSONObject(0).getString("icon");
                    String iconUrl = "https://openweathermap.org/img/wn/" + iconCode + "@2x.png";
                    Image weatherIconImage = new Image(iconUrl);
                    weatherImage.setImage(weatherIconImage);
                    String weatherDescription = json.getJSONArray("weather").getJSONObject(0).getString("main") 
                                                + " - " + json.getJSONArray("weather").getJSONObject(0).getString("description");
                    weatherDescriptionLabel.setText(weatherDescription);
                } catch (IOException | InterruptedException e) {
                    errorLabel.setText("Can not fetch weather!");
                }
            } catch (NumberFormatException e) {
                errorLabel.setVisible(true);
                errorLabel.setText("Latitude and longitude must be a number!");
                // Handle the error if the input is not a valid number
                System.out.println("Error: Input is not a valid number");
            }
        }
    }
    
    static Double convertKelvinToCelsius(Double temperature) {
        double formatValue = temperature - 273.15;
        return (double) Math.floor(formatValue * 100) / 100;
    }
}
