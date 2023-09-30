package fi.tuni.weather;

public class WeatherModel {
    private double temperature;
    private double maxTemperature;
    private double minTemperature;
    private double feelsLike;
    private double humidity;
    private double pressure;
    private String weatherDescription;

    public WeatherModel() {
        // Default constructor (you can initialize default values here if needed)
    }

    public WeatherModel(double temperature, double maxTemperature, double minTemperature,
                        double feelsLike, double humidity, double pressure, String weatherDescription) {
        this.temperature = temperature;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.pressure = pressure;
        this.weatherDescription = weatherDescription;
    }

    // Getters for model data
    public double getTemperature() {
        return convertKelvinToCelsius(temperature);
    }

    public double getMaxTemperature() {
        return convertKelvinToCelsius(maxTemperature);
    }

    public double getMinTemperature() {
        return convertKelvinToCelsius(minTemperature);
    }

    public double getFeelsLike() {
        return convertKelvinToCelsius(feelsLike);
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    // Setters for model data
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public double convertKelvinToCelsius(double kelvinTemperature) {
        double formatValue = kelvinTemperature - 273.15;
        return (double) Math.floor(formatValue * 100) / 100;
    }
}
