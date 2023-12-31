package fi.tuni.weather;

import fi.tuni.function.Function;

public class WeatherModel {

    private double temperature;
    private double maxTemperature;
    private double minTemperature;
    private double feelsLike;
    private double humidity;
    private double pressure;
    private String weatherDescription;
    private String iconCode;

    /**
     * Default constructor
     *
     *
     */
    public WeatherModel() {
        // Default constructor (you can initialize default values here if needed)
    }

    /**
     *
     * Initialize model data
     *
     * @param temperature
     * @param maxTemperature
     * @param minTemperature
     * @param feelsLike
     * @param humidity
     * @param pressure
     * @param weatherDescription
     * @param iconCode
     */

    public WeatherModel(double temperature, double maxTemperature, double minTemperature,
            double feelsLike, double humidity, double pressure, String weatherDescription, String iconCode) {
        this.temperature = temperature;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.pressure = pressure;
        this.weatherDescription = weatherDescription;
        this.iconCode = iconCode;
    }

    /**
     * Get Temperature in Celsius
     *
     * @return temperature in Celsius
     */
    public double getTemperature() {
        return Function.convertKelvinToCelsius(temperature);
    }

    /**
     * Get max temperature in Celsius
     *
     * @return max temperature in Celsius
     */
    public double getMaxTemperature() {
        return Function.convertKelvinToCelsius(maxTemperature);
    }

    /**
     * Get min temperature in Celsius
     *
     * @return min temperature in Celsius
     */
    public double getMinTemperature() {
        return Function.convertKelvinToCelsius(minTemperature);
    }

    /**
     * Get feels like temperature in Celsius
     *
     * @return feels like temperature in Celsius
     */
    public double getFeelsLike() {
        return Function.convertKelvinToCelsius(feelsLike);
    }

    /**
     * Get humidity in Celsius
     *
     * @return humidity in Celsius
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * Get pressure in Celsius
     *
     * @return pressure in Celsius
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * Get weather description
     *
     * @return weather description
     */
    public String getWeatherDescription() {
        return weatherDescription;
    }

    /**
     * Set temperature in Celsius
     *
     * @param temperature
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Set max temperature in Celsius
     *
     * @param maxTemperature
     */
    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    /**
     * Set min temperature in Celsius
     *
     * @param minTemperature
     */
    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    /**
     * Set feels like temperature in Celsius
     *
     * @param feelsLike
     */
    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    /**
     * Set humidity in Celsius
     *
     * @param humidity
     */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    /**
     * Set pressure in Celsius
     *
     * @param pressure
     */
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    /**
     * Set weather description
     *
     * @param weatherDescription
     */
    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    /**
     * Get icon code
     *
     * @return icon code
     */
    public String getIconCode() {
        return iconCode;
    }

    /**
     * Set icon code
     * 
     * @param iconCode
     */
    public void setIconCode(String iconCode) {
        this.iconCode = iconCode;
    }
}
