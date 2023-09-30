package fi.tuni.weather;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;

public class WeatherView {
    private Label temperatureLabel;
    private Label maxTemperatureLabel;
    private Label minTemperatureLabel;
    private Label feelsLikeLabel;
    private Label pressureLabel;
    private Label humidityLabel;
    private Label weatherDescriptionLabel;
    private Label errorLabel;
    private ImageView weatherImage;
    private ImageView titleImage;
    private TextField latitudeInput;
    private TextField longitudeInput;

    public WeatherView(Label temperatureLabel, Label maxTemperatureLabel, Label minTemperatureLabel,
                       Label feelsLikeLabel, Label pressureLabel, Label humidityLabel,
                       Label weatherDescriptionLabel, Label errorLabel, ImageView weatherImage,
                       ImageView titleImage, TextField latitudeInput, TextField longitudeInput) {
        this.temperatureLabel = temperatureLabel;
        this.maxTemperatureLabel = maxTemperatureLabel;
        this.minTemperatureLabel = minTemperatureLabel;
        this.feelsLikeLabel = feelsLikeLabel;
        this.pressureLabel = pressureLabel;
        this.humidityLabel = humidityLabel;
        this.weatherDescriptionLabel = weatherDescriptionLabel;
        this.errorLabel = errorLabel;
        this.weatherImage = weatherImage;
        this.titleImage = titleImage;
        this.latitudeInput = latitudeInput;
        this.longitudeInput = longitudeInput;
    }

    

    // Methods to update UI components
    public void setTemperature(String temperature) {
        temperatureLabel.setText(temperature);
    }

    public void setMaxTemperature(String maxTemperature) {
        maxTemperatureLabel.setText(maxTemperature);
    }

    public void setMinTemperature(String minTemperature) {
        minTemperatureLabel.setText(minTemperature);
    }

    public void setFeelsLike(String feelsLike) {
        feelsLikeLabel.setText(feelsLike);
    }

    public void setPressure(String pressure) {
        pressureLabel.setText(pressure);
    }

    public void setHumidity(String humidity) {
        humidityLabel.setText(humidity);
    }

    public void setWeatherDescription(String description) {
        weatherDescriptionLabel.setText(description);
    }

    public void setErrorLabel(String errorMessage) {
        errorLabel.setText(errorMessage);
    }

    public void setWeatherImage(String imageUrl) {
        Image weatherIconImage = new Image(imageUrl);
        weatherImage.setImage(weatherIconImage);
    }

    public void setTitleImage(String imageUrl) {
        Image weatherIconImage = new Image(imageUrl);
        titleImage.setImage(weatherIconImage);
    }

    public String getLatitudeInput() {
        return latitudeInput.getText();
    }

    public String getLongitudeInput() {
        return longitudeInput.getText();
    }

    public void clearErrorLabel() {
        errorLabel.setText("");
    }
}
