package fi.tuni.function;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import fi.tuni.forecast.Forecast16DaysModel;

public class API16DaysForecastData {
    public static List<Forecast16DaysModel> fetch16DaysData(HttpResponse<String> forecast16DaysRequest, String longitudeInput, String latitudeInput) {
      List<Forecast16DaysModel> forecast16DaysData = new ArrayList<>();
      String responseBody = forecast16DaysRequest.body();
      JSONObject json = new JSONObject(responseBody);
      JSONObject city = json.getJSONObject("city");
      String cityName = city.getString("name");
      String countryCode = city.getString("country");
      int population = city.getInt("population");
      int timezone = city.getInt("timezone");
      JSONArray listForecast16DaysDataReturn = json.getJSONArray("list");

      for (int i = 0; i < listForecast16DaysDataReturn.length(); i++) {
         JSONObject dataToJson = listForecast16DaysDataReturn.getJSONObject(i);
         int timeStamp = dataToJson.getInt("dt");
         JSONObject temp = dataToJson.getJSONObject("temp");
         double temperatureDay = temp.getDouble("day");
         double temperatureNight = temp.getDouble("night");
         double temperatureEvening = temp.getDouble("eve");
         double temperatureMorning = temp.getDouble("morn");
         JSONObject feelsLike = dataToJson.getJSONObject("feels_like");
         double feelsLikeDay = feelsLike.getDouble("day");
         double feelsLikeNight = feelsLike.getDouble("night");
         double feelsLikeEvening = feelsLike.getDouble("eve");
         double feelsLikeMorning = feelsLike.getDouble("morn");
         double pressure = dataToJson.getDouble("pressure");
         double humidity = dataToJson.getDouble("humidity");
         double windSpeed = dataToJson.getDouble("speed");
         double windDegree = dataToJson.getDouble("deg");
         double cloudiness = dataToJson.getDouble("clouds");
         double rain = 0;
         try {
            rain = dataToJson.getDouble("rain");
         } catch (Exception e) {
         }
         int sunrise = dataToJson.getInt("sunrise");
         int sunset = dataToJson.getInt("sunset");

         Forecast16DaysModel model = new Forecast16DaysModel(cityName, longitudeInput,
               latitudeInput, countryCode, population, timezone, timeStamp, temperatureDay,
               temperatureNight, temperatureEvening, temperatureMorning, feelsLikeDay, feelsLikeNight,
               feelsLikeEvening, feelsLikeMorning, pressure, humidity, windSpeed, windDegree, cloudiness, rain,
               sunrise, sunset);
         forecast16DaysData.add(model);
      }
      return forecast16DaysData;

   }
}
