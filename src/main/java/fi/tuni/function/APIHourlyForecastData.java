package fi.tuni.function;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import fi.tuni.forecast.ForecastHourlyModel;

public class APIHourlyForecastData {

    public static List<ForecastHourlyModel> fetchHourlyData(HttpResponse<String> forecastHourlyRequest) {
      List<ForecastHourlyModel> forecastHourlyData = new ArrayList<>();
      String responseBody = forecastHourlyRequest.body();
      JSONObject json = new JSONObject(responseBody);

      JSONArray listForecastHourlyDataReturn = json.getJSONArray("list");

      JSONObject city = json.getJSONObject("city");
      String countryCode = city.getString("country");
      int population = city.getInt("population");
      int timezone = city.getInt("timezone");
      int sunrise = city.getInt("sunrise");
      int sunset = city.getInt("sunset");
      int cityID = city.getInt("id");
      String cityName = city.getString("name");

      JSONObject coord = city.getJSONObject("coord");
      double latitude = coord.getDouble("lat");
      double longitude = coord.getDouble("lon");
      for (int i = 0; i < listForecastHourlyDataReturn.length(); i++) {
         JSONObject dataToJson = listForecastHourlyDataReturn.getJSONObject(i);
         int timeStamp = dataToJson.getInt("dt");
         JSONObject main = dataToJson.getJSONObject("main");
         double temperature = main.getDouble("temp");
         double feelsLike = main.getDouble("feels_like");
         double tempMin = main.getDouble("temp_min");
         double tempMax = main.getDouble("temp_max");
         double pressure = main.getDouble("pressure");
         double seaLevel = main.getDouble("sea_level");
         double groundLevel = main.getDouble("grnd_level");
         double humidity = main.getDouble("humidity");
         double tempKf = main.getDouble("temp_kf");

         JSONArray weather = dataToJson.getJSONArray("weather");
         JSONObject weatherObject = weather.getJSONObject(0);
         int weatherID = weatherObject.getInt("id");
         String weatherMain = weatherObject.getString("main");
         String weatherDescription = weatherObject.getString("description");

         String weatherIcon = weatherObject.getString("icon");

         JSONObject clouds = dataToJson.getJSONObject("clouds");
         double cloudsAll = clouds.getDouble("all");

         JSONObject wind = dataToJson.getJSONObject("wind");
         double windSpeed = wind.getDouble("speed");
         double windDegree = wind.getDouble("deg");
         double windGust = wind.getDouble("gust");
         double visibility = dataToJson.getDouble("visibility");
         double pop = dataToJson.getDouble("pop");
         // System.out.println(timeStamp);
         double rain1h = 0;
         try {
            JSONObject rain = dataToJson.getJSONObject("rain");
            rain1h = rain.getDouble("1h");
         } catch (Exception e) {
         }
         JSONObject sys = dataToJson.getJSONObject("sys");
         String sysPod = sys.getString("pod");
         String dtTxt = dataToJson.getString("dt_txt");

         ForecastHourlyModel model = new ForecastHourlyModel(cityID, cityName, longitude, latitude, countryCode,
               population, timezone, sunrise, sunset, timeStamp, temperature, feelsLike, tempMin, tempMax, pressure,
               seaLevel, groundLevel, humidity, tempKf, weatherID, weatherMain, weatherDescription, weatherIcon,
               cloudsAll, windSpeed, windDegree, windGust, visibility, pop, rain1h, sysPod, dtTxt);
        
        forecastHourlyData.add(model);
      }
      return forecastHourlyData;
   }
}
