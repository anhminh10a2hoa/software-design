package fi.tuni.function;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import fi.tuni.forecast.ForecastYearlyModel;

public class APIYearlyForecastData {
    public static List<ForecastYearlyModel> fetchYearlyData(HttpResponse<String> forecastYearlyRequest, String longitudeInput, String latitudeInput) {
      List<ForecastYearlyModel> forecastYearlyData = new ArrayList<>();
      String responseBody = forecastYearlyRequest.body();
      JSONObject json = new JSONObject(responseBody);
      JSONArray listForecastYearlyDataReturn = json.getJSONArray("result");
      int cityID = json.getInt("city_id");
      for (int i = 0; i < listForecastYearlyDataReturn.length(); i++) {
         JSONObject dataToJson = listForecastYearlyDataReturn.getJSONObject(i);
         int month = dataToJson.getInt("month");
         int day = dataToJson.getInt("day");
         JSONObject temp = dataToJson.getJSONObject("temp");
         double recordMinTemp = temp.getDouble("record_min");
         double recordMaxTemp = temp.getDouble("record_max");
         double averageMinTemp = temp.getDouble("average_min");
         double averageMaxTemp = temp.getDouble("average_max");
         double medianTemp = temp.getDouble("median");
         double meanTemp = temp.getDouble("mean");
         double p25Temp = temp.getDouble("p25");
         double p75Temp = temp.getDouble("p75");
         double stDevTemp = temp.getDouble("st_dev");
         double numTemp = temp.getDouble("num");
         JSONObject pressure = dataToJson.getJSONObject("pressure");
         double minPressure = pressure.getDouble("min");
         double maxPressure = pressure.getDouble("max");
         double medianPressure = pressure.getDouble("median");
         double meanPressure = pressure.getDouble("mean");
         double p25Pressure = pressure.getDouble("p25");
         double p75Pressure = pressure.getDouble("p75");
         double stDevPressure = pressure.getDouble("st_dev");
         double numPressure = pressure.getDouble("num");
         JSONObject humidity = dataToJson.getJSONObject("humidity");
         double minHumidity = humidity.getDouble("min");
         double maxHumidity = humidity.getDouble("max");
         double medianHumidity = humidity.getDouble("median");
         double meanHumidity = humidity.getDouble("mean");
         double p25Humidity = humidity.getDouble("p25");
         double p75Humidity = humidity.getDouble("p75");
         double stDevHumidity = humidity.getDouble("st_dev");
         double numHumidity = humidity.getDouble("num");
         JSONObject wind = dataToJson.getJSONObject("wind");
         double minWind = wind.getDouble("min");
         double maxWind = wind.getDouble("max");
         double medianWind = wind.getDouble("median");
         double meanWind = wind.getDouble("mean");
         double p25Wind = wind.getDouble("p25");
         double p75Wind = wind.getDouble("p75");
         double stDevWind = wind.getDouble("st_dev");
         double numWind = wind.getDouble("num");
         JSONObject clouds = dataToJson.getJSONObject("clouds");
         double minClouds = clouds.getDouble("min");
         double maxClouds = clouds.getDouble("max");
         double medianClouds = clouds.getDouble("median");
         double meanClouds = clouds.getDouble("mean");
         double p25Clouds = clouds.getDouble("p25");
         double p75Clouds = clouds.getDouble("p75");
         double stDevClouds = clouds.getDouble("st_dev");
         double numClouds = clouds.getDouble("num");

         // Add to constructor
         double latitudeValue = Double.parseDouble(longitudeInput);
         double longitudeValue = Double.parseDouble(latitudeInput);
         ForecastYearlyModel model = new ForecastYearlyModel(cityID, latitudeValue, longitudeValue,
               month, day, recordMinTemp, recordMaxTemp, averageMinTemp, averageMaxTemp, medianTemp, meanTemp, p25Temp,
               p75Temp, stDevTemp, numTemp, minPressure, maxPressure, medianPressure, meanPressure, p25Pressure,
               p75Pressure, stDevPressure, numPressure, minHumidity, maxHumidity, medianHumidity, meanHumidity,
               p25Humidity, p75Humidity, stDevHumidity, numHumidity, minWind, maxWind, medianWind, meanWind, p25Wind,
               p75Wind, stDevWind, numWind, minClouds, maxClouds, medianClouds, meanClouds, p25Clouds, p75Clouds,
               stDevClouds, numClouds);
         forecastYearlyData.add(model);

      }
      return forecastYearlyData;
   }
}
