package fi.tuni.weather;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    /**
     * Default constructor for WeatherView
     */
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

    /**
     * Set temperatureLabel text
     *
     * @param temperature
     *
     */
    public void setTemperatureLabel(String temperature) {
        temperatureLabel.setText(temperature);
    }

    /**
     * Set maxTemperatureLabel text
     *
     * @param maxTemperature
     *
     */
    public void setMaxTemperatureLabel(String maxTemperature) {
        maxTemperatureLabel.setText(maxTemperature);
    }

    /**
     * Set minTemperatureLabel text
     *
     * @param minTemperature
     *
     */
    public void setMinTemperatureLabel(String minTemperature) {
        minTemperatureLabel.setText(minTemperature);
    }

    /**
     * Set feelsLikeLabel text
     *
     * @param feelsLike
     *
     */
    public void setFeelsLikeLabel(String feelsLike) {
        feelsLikeLabel.setText(feelsLike);
    }

    /**
     * Set pressureLabel text
     *
     * @param pressure
     *
     */
    public void setPressureLabel(String pressure) {
        pressureLabel.setText(pressure);
    }

    /**
     * Set humidityLabel text
     *
     * @param humidity
     *
     */
    public void setHumidityLabel(String humidity) {
        humidityLabel.setText(humidity);
    }

    /**
     * Set weatherDescriptionLabel text
     *
     * @param description
     *
     */
    public void setWeatherDescriptionLabel(String description) {
        weatherDescriptionLabel.setText(description);
    }

    /**
     * Set errorLabel text
     *
     * @param errorMessage
     *
     */
    public void setErrorLabel(String errorMessage) {
        errorLabel.setText(errorMessage);
    }

    /**
     * Set weatherImage image
     *
     * @param imageUrl
     *
     */
    public void setWeatherImage(String imageUrl) {
        Image weatherIconImage = new Image(imageUrl);
        weatherImage.setImage(weatherIconImage);
    }

    /**
     * Set titleImage image
     *
     * @param imageUrl
     *
     */
    public void setTitleImage(String imageUrl) {
        Image weatherIconImage = new Image(imageUrl);
        titleImage.setImage(weatherIconImage);
    }

    /**
     * Get latitude input
     *
     * @return latitude input
     *
     */
    public String getLatitudeInput() {
        return latitudeInput.getText();
    }

    /**
     * Get longitude input
     *
     * @return longitude input
     *
     */
    public String getLongitudeInput() {
        return longitudeInput.getText();
    }

    /**
     * Clear errorLabel text
     *
     */
    public void clearErrorLabel() {
        errorLabel.setText("");
    }
}
