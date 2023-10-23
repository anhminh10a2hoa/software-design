package fi.tuni.forecast;

import java.time.LocalDateTime;
import java.util.List;

public class ForecastModel {

    private String cityName;
    private String longtitude;
    private String latitude;
    private String countryCode;
    private String population;
    private String timezone;
    // Make lists with all the data you need to display, starting from timestamp
    // and ending with rain
    private List<LocalDateTime> timeStampList;
    private List<Double> temperatureDayList;
    private List<Double> temperatureNightList;
    private List<Double> temperatureEveningList;
    private List<Double> temperatureMorningList;
    private List<Double> feelsLikeDayList;
    private List<Double> feelsLikeNightList;
    private List<Double> feelsLikeEveningList;
    private List<Double> feelsLikeMorningList;
    private List<Double> pressureList;
    private List<Double> humidityList;
    private List<Double> windSpeedList;
    private List<Double> windDegreeList;
    private List<Double> cloudinessList;
    private List<Double> rainList;

    public ForecastModel() {
        // Default constructor
    }

    // Parameterized constructor
    public ForecastModel(String cityName, String longtitude, String latitude, String countryCode, String population, String timezone, List<LocalDateTime> timeStampList, List<Double> temperatureDayList, List<Double> temperatureNightList, List<Double> temperatureEveningList, List<Double> temperatureMorningList, List<Double> feelsLikeDayList, List<Double> feelsLikeNightList, List<Double> feelsLikeEveningList, List<Double> feelsLikeMorningList, List<Double> pressureList, List<Double> humidityList, List<Double> windSpeedList, List<Double> windDegreeList, List<Double> cloudinessList, List<Double> rainList) {
        this.cityName = cityName;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.countryCode = countryCode;
        this.population = population;
        this.timezone = timezone;
        this.timeStampList = timeStampList;
        this.temperatureDayList = temperatureDayList;
        this.temperatureNightList = temperatureNightList;
        this.temperatureEveningList = temperatureEveningList;
        this.temperatureMorningList = temperatureMorningList;
        this.feelsLikeDayList = feelsLikeDayList;
        this.feelsLikeNightList = feelsLikeNightList;
        this.feelsLikeEveningList = feelsLikeEveningList;
        this.feelsLikeMorningList = feelsLikeMorningList;
        this.pressureList = pressureList;
        this.humidityList = humidityList;
        this.windSpeedList = windSpeedList;
        this.windDegreeList = windDegreeList;
        this.cloudinessList = cloudinessList;
        this.rainList = rainList;
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

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public List<LocalDateTime> getTimeStampList() {
        return timeStampList;
    }

    public void setTimeStampList(List<LocalDateTime> timeStampList) {
        this.timeStampList = timeStampList;
    }

    public List<Double> getTemperatureDayList() {
        return temperatureDayList;
    }

    public void setTemperatureDayList(List<Double> temperatureDayList) {
        this.temperatureDayList = temperatureDayList;
    }

    public List<Double> getTemperatureNightList() {
        return temperatureNightList;
    }

    public void setTemperatureNightList(List<Double> temperatureNightList) {
        this.temperatureNightList = temperatureNightList;
    }

    public List<Double> getTemperatureEveningList() {
        return temperatureEveningList;
    }

    public void setTemperatureEveningList(List<Double> temperatureEveningList) {
        this.temperatureEveningList = temperatureEveningList;
    }

    public List<Double> getTemperatureMorningList() {
        return temperatureMorningList;
    }

    public void setTemperatureMorningList(List<Double> temperatureMorningList) {
        this.temperatureMorningList = temperatureMorningList;
    }

    public List<Double> getFeelsLikeDayList() {
        return feelsLikeDayList;
    }

    public void setFeelsLikeDayList(List<Double> feelsLikeDayList) {
        this.feelsLikeDayList = feelsLikeDayList;
    }

    public List<Double> getFeelsLikeNightList() {
        return feelsLikeNightList;
    }

    public void setFeelsLikeNightList(List<Double> feelsLikeNightList) {
        this.feelsLikeNightList = feelsLikeNightList;
    }

    public List<Double> getFeelsLikeEveningList() {
        return feelsLikeEveningList;
    }

    public void setFeelsLikeEveningList(List<Double> feelsLikeEveningList) {
        this.feelsLikeEveningList = feelsLikeEveningList;
    }

    public List<Double> getFeelsLikeMorningList() {
        return feelsLikeMorningList;
    }

    public void setFeelsLikeMorningList(List<Double> feelsLikeMorningList) {
        this.feelsLikeMorningList = feelsLikeMorningList;
    }

    public List<Double> getPressureList() {
        return pressureList;
    }

    public void setPressureList(List<Double> pressureList) {
        this.pressureList = pressureList;
    }

    public List<Double> getHumidityList() {
        return humidityList;
    }

    public void setHumidityList(List<Double> humidityList) {
        this.humidityList = humidityList;
    }

    public List<Double> getWindSpeedList() {
        return windSpeedList;
    }

    public void setWindSpeedList(List<Double> windSpeedList) {
        this.windSpeedList = windSpeedList;
    }

    public List<Double> getWindDegreeList() {
        return windDegreeList;
    }

    public void setWindDegreeList(List<Double> windDegreeList) {
        this.windDegreeList = windDegreeList;
    }

    public List<Double> getCloudinessList() {
        return cloudinessList;
    }

    public void setCloudinessList(List<Double> cloudinessList) {
        this.cloudinessList = cloudinessList;
    }

    public List<Double> getRainList() {
        return rainList;
    }

    public void setRainList(List<Double> rainList) {
        this.rainList = rainList;
    }

    
}
