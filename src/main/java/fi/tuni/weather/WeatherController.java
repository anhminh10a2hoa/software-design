package fi.tuni.weather;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import fi.tuni.function.*;

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

    private WeatherView view;

    /**
     * Initialize the WeatherController and set the title image for the view
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
            errorLabel.setVisible(true);
            view.setErrorLabel("Please provide latitude and longitude!");
            return;
        } else {
            errorLabel.setVisible(false);
            try {
                double latitudeValue = Double.parseDouble(latitude);
                double longitudeValue = Double.parseDouble(longitude);
                if(latitudeValue < -90 || latitudeValue > 90) {
                    errorLabel.setVisible(true);
                    view.setErrorLabel("Latitude must be between -90 and 90!");
                    return;
                } else if(longitudeValue < -180 || longitudeValue > 180) {
                    errorLabel.setVisible(true);
                    view.setErrorLabel("Longitude must be between -180 and 180!");
                    return;
                } 
                String apiKey = "cda257269cd8f052e74dc19afdd5252c"; // Replace with your OpenWeatherMap API key

                HttpRequest request = Function.createHttpRequest("https://api.openweathermap.org/data/2.5/weather?lat="
                        + latitudeValue + "&lon=" + longitudeValue + "&APPID=" + apiKey);
                try {
                    WeatherModel model = APIWeatherData.fetchWeatherData(HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()));
                    view.clearErrorLabel();
                    updateUI(model);
                } catch (IOException | InterruptedException e) {
                    view.setErrorLabel("Can not fetch weather!");
                }

            } catch (NumberFormatException e) {
                errorLabel.setVisible(true);
                view.setErrorLabel("Latitude and longitude must be a number!");
            }
        }
    }

    /**
     * Update the UI with the data from the JSON response
     *
     * @param json
     */
    private void updateUI(WeatherModel model) {

        view.setTemperatureLabel("Temperature: " + model.getTemperature() + "°C");
        view.setMaxTemperatureLabel("Max temperature: " + model.getMaxTemperature() + "°C");
        view.setMinTemperatureLabel("Min temperature: " + model.getMinTemperature() + "°C");
        view.setFeelsLikeLabel("Feels like: " + model.getFeelsLike() + "°C");
        view.setPressureLabel("Pressure: " + model.getPressure());
        view.setHumidityLabel("Humidity: " + model.getHumidity() + "%");
        String weatherDescription = model.getWeatherDescription();
        String iconCode = model.getIconCode();
        String iconUrl = "https://openweathermap.org/img/wn/" + iconCode + "@2x.png";
        // Load the image and set it in the view
        // You can use JavaFX Image and ImageView to load and display the image
        view.setWeatherImage(iconUrl);
        // You need to implement loading and displaying the image in your view.
        view.setWeatherDescriptionLabel(weatherDescription);
    }
}
