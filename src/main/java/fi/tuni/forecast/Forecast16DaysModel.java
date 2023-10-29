package fi.tuni.forecast;

public class Forecast16DaysModel {

    private String cityName;
    private String longtitude;
    private String latitude;
    private String countryCode;
    private int population;
    private int timezone;
    private int timeStamp;
    private double temperatureDay;
    private double temperatureNight;
    private double temperatureEvening;
    private double temperatureMorning;
    private double feelsLikeDay;
    private double feelsLikeNight;
    private double feelsLikeEvening;
    private double feelsLikeMorning;
    private double pressure;
    private double humidity;
    private double windSpeed;
    private double windDegree;
    private double cloudiness;
    private double rain;
    private int sunrise;
    private int sunset;

    // Parameterized constructor
    public Forecast16DaysModel(String cityName, String longtitude, String latitude, String countryCode,
            int population, int timezone, int timeStamp, double temperatureDay, double temperatureNight,
            double temperatureEvening, double temperatureMorning, double feelsLikeDay, double feelsLikeNight,
            double feelsLikeEvening, double feelsLikeMorning, double pressure, double humidity, double windSpeed,
            double windDegree, double cloudiness, double rain, int sunrise, int sunset) {
        this.cityName = cityName;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.countryCode = countryCode;
        this.population = population;
        this.timezone = timezone;
        this.timeStamp = timeStamp;
        this.temperatureDay = temperatureDay;
        this.temperatureNight = temperatureNight;
        this.temperatureEvening = temperatureEvening;
        this.temperatureMorning = temperatureMorning;
        this.feelsLikeDay = feelsLikeDay;
        this.feelsLikeNight = feelsLikeNight;
        this.feelsLikeEvening = feelsLikeEvening;
        this.feelsLikeMorning = feelsLikeMorning;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDegree = windDegree;
        this.cloudiness = cloudiness;
        this.rain = rain;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getTemperatureDay() {
        return temperatureDay;
    }

    public void setTemperatureDay(double temperatureDay) {
        this.temperatureDay = temperatureDay;
    }

    public double getTemperatureNight() {
        return temperatureNight;
    }

    public void setTemperatureNight(double temperatureNight) {
        this.temperatureNight = temperatureNight;
    }

    public double getTemperatureEvening() {
        return temperatureEvening;
    }

    public void setTemperatureEvening(double temperatureEvening) {
        this.temperatureEvening = temperatureEvening;
    }

    public double getTemperatureMorning() {
        return temperatureMorning;
    }

    public void setTemperatureMorning(double temperatureMorning) {
        this.temperatureMorning = temperatureMorning;
    }

    public double getFeelsLikeDay() {
        return feelsLikeDay;
    }

    public void setFeelsLikeDay(double feelsLikeDay) {
        this.feelsLikeDay = feelsLikeDay;
    }

    public double getFeelsLikeNight() {
        return feelsLikeNight;
    }

    public void setFeelsLikeNight(double feelsLikeNight) {
        this.feelsLikeNight = feelsLikeNight;
    }

    public double getFeelsLikeEvening() {
        return feelsLikeEvening;
    }

    public void setFeelsLikeEvening(double feelsLikeEvening) {
        this.feelsLikeEvening = feelsLikeEvening;
    }

    public double getFeelsLikeMorning() {
        return feelsLikeMorning;
    }

    public void setFeelsLikeMorning(double feelsLikeMorning) {
        this.feelsLikeMorning = feelsLikeMorning;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(double windDegree) {
        this.windDegree = windDegree;
    }

    public double getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(double cloudiness) {
        this.cloudiness = cloudiness;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

}
