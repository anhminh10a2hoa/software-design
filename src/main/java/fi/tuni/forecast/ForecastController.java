package fi.tuni.forecast;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ForecastController implements Initializable {

   @FXML
   private TextField latitudeInput;
   @FXML
   private TextField longitudeInput;
   @FXML
   private TextField placeInput;
   @FXML
   private ComboBox<String> forecastComboBox;
   @FXML
   private Label errorLabel;
   @FXML
   ImageView titleImage;
   @FXML
   TabPane forecastTabPane;

   private List<Forecast16DaysModel> forecast16DaysData;
   private List<ForecastYearlyModel> forecastYearlyData;

   @FXML
   protected void drawLineGraph() {
      // // Create a new line chart
      // LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
      // lineChart.setTitle("Air pollution");
      // lineChart.setLegendVisible(false);

      // // Define the x and y axes
      // xAxis.setLabel("Time");
      // yAxis.setLabel("Air pollution");
      // // Define a series
      // XYChart.Series<String, Number> series = new XYChart.Series<>();
      // // Add data to the series
      // for (int i = 0; i < airPollutionData.size(); i++) {
      // series.getData().add(new XYChart.Data<>(airPollutionData.get(i).getTime(),
      // airPollutionData.get(i).getAirPollution()));
      // }
      // // Add the series to the line chart
      // lineChart.getData().add(series);
      // // Add the line chart to the pane
      // airPollutionLineChart.getChildren().add(lineChart);
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      // Initialize forecast16DaysData and forecastYearlyData
      forecast16DaysData = new ArrayList<Forecast16DaysModel>();
      forecastYearlyData = new ArrayList<ForecastYearlyModel>();
      Image forecastIconImage = new Image("https://cdn0.iconfinder.com/data/icons/ikonate/48/line-chart-512.png");
      titleImage.setImage(forecastIconImage);
      forecastComboBox.setItems(FXCollections.observableArrayList(""));
      errorLabel.setVisible(false);
      forecastTabPane.setVisible(false);
   }

   // Function for loading HTML data from the given URL
   private HttpRequest createHttpRequest(String url) {
      HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
      return request;
   }

   @FXML
   protected void getForecastData(ActionEvent event) {
      System.out.println("Get forecast data");
      String latitude = latitudeInput.getText();
      String longitude = longitudeInput.getText();

      if (latitude.isEmpty() || longitude.isEmpty()) {
         errorLabel.setVisible(true);
         errorLabel.setText("Please provide latitude and longitude!");
      } else {
         try {
            double latitudeValue = Double.parseDouble(latitude);
            double longitudeValue = Double.parseDouble(longitude);
            System.out.println("Latitude: " + latitudeValue);
            System.out.println("Longitude: " + longitudeValue);
            String forecast16DaysURL = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + latitudeValue
                  + "&lon="
                  + longitudeValue + "&APPID=cda257269cd8f052e74dc19afdd5252c";

            HttpRequest forecast16DaysRequest = createHttpRequest(forecast16DaysURL);

            // history.openweathermap.org/data/2.5/aggregated/year?lat={lat}&lon={lon}&appid={API
            // key}
            String forecastYearlyUrl = "http://history.openweathermap.org/data/2.5/aggregated/year?lat="
                  + latitudeValue + "&lon="
                  + longitudeValue + "&appid=cda257269cd8f052e74dc19afdd5252c";

            HttpRequest forecastYearlyRequest = createHttpRequest(forecastYearlyUrl);

            try {
               errorLabel.setVisible(false);

               HttpResponse<String> forecast16DaysResponse = HttpClient.newHttpClient().send(forecast16DaysRequest,
                     HttpResponse.BodyHandlers.ofString());
               HttpResponse<String> forecastYearlyResponse = HttpClient.newHttpClient().send(forecastYearlyRequest,
                     HttpResponse.BodyHandlers.ofString());

               forecast16DaysData = fetch16DaysData(forecast16DaysResponse);
               forecastYearlyData = fetchYearlyData(forecastYearlyResponse);

               forecastTabPane.setVisible(true);
               drawLineGraph();
            } catch (IOException | InterruptedException e) {
               errorLabel.setText("Can not fetch weather!");
            }
         } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
            errorLabel.setText("Latitude and longitude must be a number!");
         }
      }
   }

   private List<Forecast16DaysModel> fetch16DaysData(HttpResponse<String> forecast16DaysRequest) {
      String responseBody = forecast16DaysRequest.body();
      JSONObject json = new JSONObject(responseBody);
      JSONObject city = json.getJSONObject("city");
      String cityName = city.getString("name");
      String countryCode = city.getString("country");
      // Write JSONObject city to a file for checking
      // try {
      // FileWriter file = new FileWriter("city.json");
      // file.write(city.toString());
      // file.flush();
      // file.close();
      // } catch (IOException e) {
      // e.printStackTrace();
      // }
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
         double rain = dataToJson.getDouble("rain");
         int sunrise = dataToJson.getInt("sunrise");
         int sunset = dataToJson.getInt("sunset");

         Forecast16DaysModel model = new Forecast16DaysModel(cityName, longitudeInput.getText(),
               latitudeInput.getText(), countryCode, population, timezone, timeStamp, temperatureDay,
               temperatureNight, temperatureEvening, temperatureMorning, feelsLikeDay, feelsLikeNight,
               feelsLikeEvening, feelsLikeMorning, pressure, humidity, windSpeed, windDegree, cloudiness, rain,
               sunrise, sunset);
         forecast16DaysData.add(model);
      }
      return forecast16DaysData;

   }

   private List<ForecastYearlyModel> fetchYearlyData(HttpResponse<String> forecastYearlyRequest) {

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
         ForecastYearlyModel model = new ForecastYearlyModel(cityID, longitudeInput.getText(), latitudeInput.getText(),
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