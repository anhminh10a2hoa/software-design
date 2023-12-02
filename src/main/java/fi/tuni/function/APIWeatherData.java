package fi.tuni.function;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import fi.tuni.weather.WeatherModel;

public class APIWeatherData {
    public static WeatherModel fetchWeatherData(HttpResponse<String> response) {
        WeatherModel weatherModel = new WeatherModel();
        String responseBody = response.body();
        // Parse the JSON response
        JSONObject json = new JSONObject(responseBody);
        double temp = json.getJSONObject("main").getDouble("temp");
        double temp_min = json.getJSONObject("main").getDouble("temp_min");
        double temp_max = json.getJSONObject("main").getDouble("temp_max");
        double feels_like = json.getJSONObject("main").getDouble("feels_like");
        double pressure = json.getJSONObject("main").getDouble("pressure");
        double humidity = json.getJSONObject("main").getDouble("humidity");
        String weatherDescription = json.getJSONArray("weather").getJSONObject(0).getString("main")
                + " - " + json.getJSONArray("weather").getJSONObject(0).getString("description");
        String iconCode = json.getJSONArray("weather").getJSONObject(0).getString("icon");
        weatherModel = new WeatherModel(temp, temp_max, temp_min, feels_like, humidity, pressure, weatherDescription,
                iconCode);

        return weatherModel;
    }
}
