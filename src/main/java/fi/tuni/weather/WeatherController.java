package fi.tuni.weather;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;


import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import org.json.JSONObject;

public class WeatherController implements Initializable {
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
    private ImageView titleImage;
    
    @FXML
    private TextField latitudeInput;
    
    @FXML
    private TextField longitudeInput;

    private WeatherModel model;
    private WeatherView view;

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      this.view = new WeatherView(temperatureLabel, maxTemperatureLabel, minTemperatureLabel, feelsLikeLabel, pressureLabel, humidityLabel, weatherDescriptionLabel, errorLabel, weatherImage, titleImage, latitudeInput, longitudeInput);
      this.view.setTitleImage("https://cdn2.iconfinder.com/data/icons/weather-flat-14/64/weather02-512.png");
    }

    public void getWeatherData(ActionEvent event) {
        String latitude = view.getLatitudeInput();
        String longitude = view.getLongitudeInput();
        
        if (latitude.isEmpty() || longitude.isEmpty()) {
            view.setErrorLabel("Please provide latitude and longitude!");
        } else {
            try {
                double latitudeValue = Double.parseDouble(latitude);
                double longitudeValue = Double.parseDouble(longitude);
                String apiKey = "7ab8c7f2be8a514f54ffadb36862fa74"; // Replace with your OpenWeatherMap API key

                // Build the API request URL
                HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?lat=" 
                                            + latitudeValue + "&lon=" + longitudeValue + "&APPID=" + apiKey))
                                    .method("GET", HttpRequest.BodyPublishers.noBody())
                                    .build();
                try {
                    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                    String responseBody = response.body();

                    // Parse the JSON response
                    JSONObject json = new JSONObject(responseBody);
                    view.clearErrorLabel();
                    updateUI(json);
                } catch (IOException | InterruptedException e) {
                    view.setErrorLabel("Can not fetch weather!");
                }

                

            } catch (NumberFormatException e) {
                view.setErrorLabel("Latitude and longitude must be a number!");
            }
        }
    }

    private void updateUI(JSONObject json) {
        double temp = json.getJSONObject("main").getDouble("temp");
        double temp_min = json.getJSONObject("main").getDouble("temp_min");
        double temp_max = json.getJSONObject("main").getDouble("temp_max");
        double feels_like = json.getJSONObject("main").getDouble("feels_like");
        double pressure = json.getJSONObject("main").getDouble("pressure");
        double humidity = json.getJSONObject("main").getDouble("humidity");
        String weatherDescription = json.getJSONArray("weather").getJSONObject(0).getString("main")
                + " - " + json.getJSONArray("weather").getJSONObject(0).getString("description");
        
        this.model = new WeatherModel(temp, temp_max, temp_min, feels_like, humidity, pressure, weatherDescription);

        view.setTemperature("Temperature: " + this.model.getTemperature() + "°C");
        view.setMaxTemperature("Max temperature: " + this.model.getMaxTemperature() + "°C");
        view.setMinTemperature("Min temperature: " + this.model.getMinTemperature() + "°C");
        view.setFeelsLike("Feels like: " + this.model.getFeelsLike() + "°C");
        view.setPressure("Pressure: " + this.model.getPressure());
        view.setHumidity("Humidity: " + this.model.getHumidity() + "%");

        String iconCode = json.getJSONArray("weather").getJSONObject(0).getString("icon");
        String iconUrl = "https://openweathermap.org/img/wn/" + iconCode + "@2x.png";
        // Load the image and set it in the view
        // You can use JavaFX Image and ImageView to load and display the image
        view.setWeatherImage(iconUrl);
        // You need to implement loading and displaying the image in your view.
        view.setWeatherDescription(weatherDescription);
    }
}