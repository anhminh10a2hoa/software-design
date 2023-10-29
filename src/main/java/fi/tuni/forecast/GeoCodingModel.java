package fi.tuni.forecast;

public class GeoCodingModel {
    String cityName;
    String country;
    double latitude;
    double longitude;

    public GeoCodingModel(String cityName, String country, double latitude, double longitude) {
        this.cityName = cityName;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
