package fi.tuni.forecast;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
<<<<<<< HEAD
=======

>>>>>>> 105190cf7be5d42b85e5298e76688ad9b11c03fa
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import fi.tuni.function.*;

public class ForecastController implements Initializable {

   @FXML
   private TextField latitudeInput;
   @FXML
   private TextField longitudeInput;
   @FXML
   private TextField placeInput;
   @FXML
   private ComboBox<String> dailySelectionComboBox;
   @FXML
   private ComboBox<String> days16SelectionComboBox;
   @FXML
   private ComboBox<String> yearlySelectionComboBox;
   @FXML
   private Label errorLabel;
   @FXML
   private ImageView titleImage;
   @FXML
   private TabPane forecastTabPane;

   @FXML
   private CategoryAxis xAxisDaily;
   @FXML
   private NumberAxis yAxisDaily;
   @FXML
   private CategoryAxis xAxis16Days;
   @FXML
   private NumberAxis yAxis16Days;
   @FXML
   private CategoryAxis xAxisYearly;
   @FXML
   private NumberAxis yAxisYearly;
   @FXML
   private LineChart<String, Number> dailyForecastLineChart;
   @FXML
   private LineChart<String, Number> weeklyForecastLineChart;
   @FXML
   private LineChart<String, Number> yearlyForecastLineChart;

   private ForecastView forecastView;

   // Create a list for saving the data from the API
   private List<ForecastHourlyModel> forecastHourlyData;
   private List<Forecast16DaysModel> forecast16DaysData;
   private List<ForecastYearlyModel> forecastYearlyData;
   private List<GeoCodingModel> geoCodingData;

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      // Initialize the list
      forecastHourlyData = new ArrayList<>();
      forecast16DaysData = new ArrayList<>();
      forecastYearlyData = new ArrayList<>();
      geoCodingData = new ArrayList<>();
      // Initialize the Image
      // Initialize the view
      forecastView = new ForecastView(latitudeInput, longitudeInput, placeInput, dailySelectionComboBox,
            days16SelectionComboBox, yearlySelectionComboBox, errorLabel, titleImage, forecastTabPane, xAxisDaily,
            yAxisDaily, xAxis16Days, yAxis16Days, xAxisYearly, yAxisYearly, dailyForecastLineChart,
            weeklyForecastLineChart, yearlyForecastLineChart);

      forecastView.setTitleImage("https://cdn0.iconfinder.com/data/icons/ikonate/48/line-chart-512.png");

      // Initialize the error label
      errorLabel.setVisible(false);
      forecastTabPane.setVisible(false);

      // Disable the latitude and longitude input fields if the other one is filled
      // and in the reverse direction
      latitudeInput.textProperty().addListener((observable, oldValue, newValue) -> {
         if (!newValue.isEmpty()) {
            placeInput.setDisable(true);
         } else {
            if (longitudeInput.getText().isEmpty()) {
               placeInput.setDisable(false);
            }
         }
      });

      longitudeInput.textProperty().addListener((observable, oldValue, newValue) -> {
         if (!newValue.isEmpty()) {
            placeInput.setDisable(true);
         } else {
            if (latitudeInput.getText().isEmpty()) {
               placeInput.setDisable(false);
            }
         }
      });

      placeInput.textProperty().addListener((observable, oldValue, newValue) -> {
         if (!newValue.isEmpty()) {
            latitudeInput.setDisable(true);
            longitudeInput.setDisable(true);
         } else {
            latitudeInput.setDisable(false);
            longitudeInput.setDisable(false);
         }
      });

      forecastView.setItemsDailySelectionComboBox(FXCollections.observableArrayList("Temperature", "Feels like",
            "Temp min", "Temp max", "Pressure", "Sea level", "Ground level", "Humidity", "Temp kf", "Clouds all",
            "Wind speed", "Wind degree", "Wind gust", "Visibility", "Pop", "Rain 1h"));

      forecastView.setItemsDays16SelectionComboBox(
            FXCollections.observableArrayList("Temperature day", "Temperature night", "Temperature evening",
                  "Temperature morning", "Feels like day", "Feels like night", "Feels like evening",
                  "Feels like morning", "Pressure", "Humidity", "Wind speed",
                  "Wind degree", "Cloudiness", "Rain", "Sunrise", "Sunset"));

      forecastView.setItemsYearlySelectionComboBox(FXCollections.observableArrayList("Record min temp",
            "Record max temp",
            "Average min temp", "Average max temp", "Median temp", "Mean temp", "P25 temp", "P75 temp", "St dev temp",
            "Num temp", "Min pressure", "Max pressure", "Median pressure", "Mean pressure", "P25 pressure",
            "P75 pressure", "St dev pressure", "Num pressure", "Min humidity", "Max humidity", "Median humidity",
            "Mean humidity", "P25 humidity", "P75 humidity", "St dev humidity", "Num humidity", "Min wind", "Max wind",
            "Median wind", "Mean wind", "P25 wind", "P75 wind", "St dev wind", "Num wind", "Min clouds", "Max clouds",
            "Median clouds", "Mean clouds", "P25 clouds", "P75 clouds", "St dev clouds", "Num clouds"));

      forecastView.setDailySelectionComboBoxFirst();
      forecastView.setDays16SelectionComboBoxFirst();
      forecastView.setYearlySelectionComboBoxFirst();

      forecastView.getDailySelectionComboBox().getSelectionModel().selectedItemProperty()
            .addListener((options, oldValue, newValue) -> {
               // if the data has been fetched, do these functions below
               if (!forecastHourlyData.isEmpty()) {
                  forecastView.setValueDailySelectionComboBox(newValue);
                  forecastView.clearDailyLineChart();
                  drawDailyLineGraph();
               }

            });
      forecastView.getDays16SelectionComboBox().getSelectionModel().selectedItemProperty()
            .addListener((options, oldValue, newValue) -> {
               if (!forecastHourlyData.isEmpty()) {
                  forecastView.setValueDays16SelectionComboBox(newValue);
                  forecastView.clear16DaysLineChart();
                  draw16DaysLineGraph();
               }
            });

      forecastView.getYearlySelectionComboBox().getSelectionModel().selectedItemProperty()
            .addListener((options, oldValue, newValue) -> {
               if (!forecastHourlyData.isEmpty()) {
                  forecastView.setValueYearlySelectionComboBox(newValue);
                  forecastView.clearYearlyLineChart();
                  drawYearlyLineGraph();
               }
            });
   }

   @FXML
   protected void drawDailyLineGraph() {
      // Get the selection from the combo box
      String selection = dailySelectionComboBox.getSelectionModel().getSelectedItem();

      dailyForecastLineChart.setLegendVisible(false);
      forecastView.setDailyXAxisLabel("Time");
      forecastView.setDailyYAxisLabel(selection);

      // Define a series
      XYChart.Series<String, Number> series = new XYChart.Series<>();

      // Add data to the series
      // for (int i = 0; i < forecastHourlyData.size(); i++) {
      // Take the last 24 hours
      for (int i = forecastHourlyData.size() - 24; i < forecastHourlyData.size(); i++) {
         switch (selection) {
            case "Temperature":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getTemperature()));
               break;
            case "Feels like":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getFeelsLike()));
               break;
            case "Temp min":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getTempMin()));
               break;
            case "Temp max":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getTempMax()));
               break;
            case "Pressure":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getPressure()));
               break;
            case "Sea level":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getSeaLevel()));
               break;
            case "Ground level":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getGroundLevel()));
               break;
            case "Humidity":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getHumidity()));
               break;
            case "Temp kf":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getTempKf()));
               break;
            case "Clouds all":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getCloudsAll()));

               break;
            case "Wind speed":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getWindSpeed()));
               break;
            case "Wind degree":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getWindDeg()));
               break;
            case "Wind gust":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getWindGust()));
               break;
            case "Visibility":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getVisibility()));
               break;
            case "Pop":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getPop()));
               break;
            case "Rain 1h":
               series.getData().add(new XYChart.Data<>(forecastHourlyData.get(i).getDtTxt(),
                     forecastHourlyData.get(i).getRain1h()));
               break;
            default:
               break;
         }
      }
      // Add the series to the line chart
      forecastView.setDailyForecastLineChartData(series);
   }

   @FXML
   protected void draw16DaysLineGraph() {
      // Get the selection from the combo box
      String selection = days16SelectionComboBox.getSelectionModel().getSelectedItem();

      // Create a new line chart
      weeklyForecastLineChart.setLegendVisible(false);

      // Set the x and y labels for the line chart
      forecastView.set16DaysXAxisLabel("Time");
      forecastView.set16DaysYAxisLabel(selection);

      // Define a series
      XYChart.Series<String, Number> series = new XYChart.Series<>();
      // Add data to the series
      for (int i = 0; i < forecast16DaysData.size(); i++) {
         switch (selection) {
            case "Temperature day":
               int timeStamp = forecast16DaysData.get(i).getTimeStamp();
               String date = Function.convertUnixTimeStampToDate(timeStamp);

               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getTemperatureDay()));
               break;
            case "Temperature night":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);

               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getTemperatureNight()));
               break;
            case "Temperature evening":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);

               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getTemperatureEvening()));
               break;
            case "Temperature morning":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);

               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getTemperatureMorning()));
               break;
            case "Feels like day":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);

               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getFeelsLikeDay()));
               break;
            case "Feels like night":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);

               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getFeelsLikeNight()));
               break;
            case "Feels like evening":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);

               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getFeelsLikeEvening()));
               break;
            case "Feels like morning":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);

               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getFeelsLikeMorning()));
               break;
            case "Pressure":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);
               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getPressure()));
               break;
            case "Humidity":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);
               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getHumidity()));
               break;
            case "Wind speed":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);
               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getWindSpeed()));
               break;
            case "Wind degree":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);
               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getWindDegree()));
               break;
            case "Cloudiness":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);
               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getCloudiness()));
               break;
            case "Rain":
               timeStamp = forecast16DaysData.get(i).getTimeStamp();
               date = Function.convertUnixTimeStampToDate(timeStamp);
               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getRain()));
               break;
            case "Sunrise":
               timeStamp = forecast16DaysData.get(i).getSunrise();
               date = Function.convertUnixTimeStampToDate(timeStamp);
               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getSunrise()));
               break;
            case "Sunset":
               timeStamp = forecast16DaysData.get(i).getSunset();
               date = Function.convertUnixTimeStampToDate(timeStamp);
               series.getData().add(new XYChart.Data<>(date,
                     forecast16DaysData.get(i).getSunset()));
               break;
            default:
               break;
         }
      }
      // Add the series to the line chart
      forecastView.set16DaysForecastLineChartData(series);
   }

   @FXML
   protected void drawYearlyLineGraph() {
      // Get the selection from the combo box
      String selection = yearlySelectionComboBox.getSelectionModel().getSelectedItem();

      yearlyForecastLineChart.setLegendVisible(false);
      // Create a new line chart
      forecastView.setYearlyXAxisLabel("Time");
      forecastView.setYearlyYAxisLabel(selection);

      // Define a series
      XYChart.Series<String, Number> series = new XYChart.Series<>();
      // Add data to the series
      for (int i = 0; i < forecastYearlyData.size(); i++) {
         int month = forecastYearlyData.get(i).getMonth();
         int day = forecastYearlyData.get(i).getDay();
         String date = month + "-" + day;
         switch (selection) {
            case "Record min temp":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getRecordMinTemp()));
               break;
            case "Record max temp":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getRecordMaxTemp()));
               break;
            case "Average min temp":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getAverageMinTemp()));
               break;
            case "Average max temp":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getAverageMaxTemp()));
               break;
            case "Median temp":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMedianTemp()));
               break;
            case "Mean temp":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMeanTemp()));
               break;
            case "P25 temp":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getP25Temp()));
               break;
            case "P75 temp":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getP75Temp()));
               break;
            case "St dev temp":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getStDevTemp()));
               break;
            case "Num temp":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getNumTemp()));
               break;
            case "Min pressure":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMinPressure()));
               break;
            case "Max pressure":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMaxPressure()));
               break;
            case "Median pressure":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMedianPressure()));
               break;
            case "Mean pressure":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMeanPressure()));
               break;
            case "P25 pressure":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getP25Pressure()));
               break;
            case "P75 pressure":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getP75Pressure()));
               break;
            case "St dev pressure":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getStDevPressure()));
               break;
            case "Num pressure":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getNumPressure()));
               break;
            case "Min humidity":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMinHumidity()));
               break;
            case "Max humidity":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMaxHumidity()));
               break;
            case "Median humidity":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMedianHumidity()));
               break;
            case "Mean humidity":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMeanHumidity()));
               break;
            case "P25 humidity":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getP25Humidity()));
               break;
            case "P75 humidity":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getP75Humidity()));
               break;
            case "St dev humidity":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getStDevHumidity()));
               break;
            case "Num humidity":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getNumHumidity()));
               break;
            case "Min wind":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMinWind()));
               break;
            case "Max wind":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMaxWind()));
               break;
            case "Median wind":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMedianWind()));
               break;
            case "Mean wind":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMeanWind()));
               break;
            case "P25 wind":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getP25Wind()));
               break;
            case "P75 wind":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getP75Wind()));
               break;
            case "St dev wind":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getStDevWind()));
               break;
            case "Num wind":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getNumWind()));
               break;
            case "Min clouds":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMinClouds()));
               break;
            case "Max clouds":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMaxClouds()));
               break;
            case "Median clouds":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMedianClouds()));
               break;
            case "Mean clouds":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getMeanClouds()));
               break;
            case "P25 clouds":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getP25Clouds()));
               break;
            case "P75 clouds":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getP75Clouds()));
               break;
            case "St dev clouds":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getStDevClouds()));
               break;
            case "Num clouds":
               series.getData().add(new XYChart.Data<>(date,
                     forecastYearlyData.get(i).getNumClouds()));
               break;
            default:
               break;
         }
      }
      // Add the series to the line chart
      forecastView.setYearlyForecastLineChartData(series);
   }

   @FXML
   protected void getForecastData(ActionEvent event) {
      // Get the forecast data based on the user input (latitude, longitude)
      System.out.println("Get forecast data");
      String latitude = forecastView.getLatitudeInput();
      String longitude = forecastView.getLongitudeInput();
      String place = forecastView.getPlaceInput();

      // Check if the input is valid
      if (latitude.isEmpty() || longitude.isEmpty()) {
         if (place.isEmpty()) {
            errorLabel.setVisible(true);
            forecastView.setErrorLabel("Please provide latitude and longitude or place!");
            return;
         }
      }

      // Check if the input is in the correct range
      if (!latitude.isEmpty()) {
         double latitudeValue = Double.parseDouble(latitude);
         if (latitudeValue < -90 || latitudeValue > 90) {
            errorLabel.setVisible(true);
            forecastView.setErrorLabel("Latitude must be between -90 and 90!");
            return;
         }
      }

      if (!longitude.isEmpty()) {
         double longitudeValue = Double.parseDouble(longitude);
         if (longitudeValue < -180 || longitudeValue > 180) {
            errorLabel.setVisible(true);
            forecastView.setErrorLabel("Longitude must be between -180 and 180!");
            return;
         }
      }

      if (latitude.isEmpty() || longitude.isEmpty()) {
         // Get the latitude and longitude based on the place
         String geoCodingURL = "https://api.openweathermap.org/geo/1.0/direct?q=" + place
               + "&limit=1&appid=cda257269cd8f052e74dc19afdd5252c";
         HttpRequest geoCodingRequest = Function.createHttpRequest(geoCodingURL);
         try { 
            errorLabel.setVisible(false);
            HttpResponse<String> geoCodingResponse = HttpClient.newHttpClient().send(geoCodingRequest,
                  HttpResponse.BodyHandlers.ofString());
            geoCodingData = APIGeoCodingData.fetchGeoCodingData(geoCodingResponse);
            try {
               latitude = String.valueOf(geoCodingData.get(0).getLatitude());
               longitude = String.valueOf(geoCodingData.get(0).getLongitude());
            } catch (IndexOutOfBoundsException e) {
               errorLabel.setVisible(true);
               forecastView.setErrorLabel("Can not fetch the city information!");
               return;
            }
            // Set the latitude and longitude to the input fields
            forecastView.setLatitudeInput(latitude);
            forecastView.setLongitudeInput(longitude);

         } catch (IOException | InterruptedException e) {
            forecastView.setErrorLabel("Can not fetch weather!");
         }
      }

      try {
        double latitudeValue = Double.parseDouble(latitude);
        double longitudeValue = Double.parseDouble(longitude);
        place = forecastView.getPlaceInput();
        if(latitudeValue < -90 || latitudeValue > 90) {
            errorLabel.setVisible(true);
            errorLabel.setText("Latitude must be between -90 and 90!");
            return;
        } else if(longitudeValue < -180 || longitudeValue > 180) {
            errorLabel.setVisible(true);
            errorLabel.setText("Longitude must be between -180 and 180!");
            return;
        } 
         // Print the latitude and longitude for debugging purposes
         System.out.println("Latitude: " + latitudeValue);
         System.out.println("Longitude: " + longitudeValue);
         System.out.println("Place: " + place);
         String geoCodingURL = "https://api.openweathermap.org/geo/1.0/reverse?lat=" + latitudeValue + "&lon="
               + longitudeValue + "&limit=1&appid=cda257269cd8f052e74dc19afdd5252c";

         HttpRequest geoCodingRequest = Function.createHttpRequest(geoCodingURL);

         String forecastHourlyURL = "https://pro.openweathermap.org/data/2.5/forecast/hourly?lat=" + latitudeValue
               + "&lon="
               + longitudeValue + "&appid=cda257269cd8f052e74dc19afdd5252c";
         HttpRequest forecastHourlyRequest = Function.createHttpRequest(forecastHourlyURL);

         String forecast16DaysURL = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + latitudeValue
               + "&lon="
               + longitudeValue + "&APPID=cda257269cd8f052e74dc19afdd5252c";
         HttpRequest forecast16DaysRequest = Function.createHttpRequest(forecast16DaysURL);

         String forecastYearlyUrl = "http://history.openweathermap.org/data/2.5/aggregated/year?lat="
               + latitudeValue + "&lon="
               + longitudeValue + "&appid=cda257269cd8f052e74dc19afdd5252c";
         HttpRequest forecastYearlyRequest = Function.createHttpRequest(forecastYearlyUrl);

         try {
            errorLabel.setVisible(false);

            // Get the data from the GeoCoding API
            HttpResponse<String> geoCodingResponse = HttpClient.newHttpClient().send(geoCodingRequest,
                  HttpResponse.BodyHandlers.ofString());
            geoCodingData = APIGeoCodingData.fetchGeoCodingData(geoCodingResponse);
            try {
               forecastView
                     .setPlaceInput(geoCodingData.get(0).getCityName() + ", " + geoCodingData.get(0).getCountry());
            } catch (IndexOutOfBoundsException e) {
               errorLabel.setVisible(true);
               forecastView.setErrorLabel("Can not fetch the city information!");
               // return;
            }
            // Get the data from the hourly forecast API
            HttpResponse<String> forecastHourlyResponse = HttpClient.newHttpClient().send(forecastHourlyRequest,
                  HttpResponse.BodyHandlers.ofString());
            forecastHourlyData = APIHourlyForecastData.fetchHourlyData(forecastHourlyResponse);

            // Get the data from the 16-day forecast API
            HttpResponse<String> forecast16DaysResponse = HttpClient.newHttpClient().send(forecast16DaysRequest,
                  HttpResponse.BodyHandlers.ofString());
            forecast16DaysData = API16DaysForecastData.fetch16DaysData(forecast16DaysResponse,
                  forecastView.getLongitudeInput(),
                  forecastView.getLatitudeInput());

            // Get the data from the yearly forecast API
            HttpResponse<String> forecastYearlyResponse = HttpClient.newHttpClient().send(forecastYearlyRequest,
                  HttpResponse.BodyHandlers.ofString());

            forecastYearlyData = APIYearlyForecastData.fetchYearlyData(forecastYearlyResponse,
                  forecastView.getLongitudeInput(),
                  forecastView.getLatitudeInput());

            // Display the data
            forecastTabPane.setVisible(true);
            drawDailyLineGraph();
            draw16DaysLineGraph();
            drawYearlyLineGraph();
         } catch (IOException | InterruptedException e) {
            errorLabel.setVisible(true);
            forecastView.setErrorLabel("Can not fetch weather!");
         }
      } catch (NumberFormatException e) {
         errorLabel.setVisible(true);
         forecastView.setErrorLabel("Latitude and longitude must be a number!");
      }

   }

   @FXML
   protected void resetForecastData(ActionEvent event) {
      // Reset the forecast data
      forecastHourlyData.clear();
      forecast16DaysData.clear();
      forecastYearlyData.clear();
      geoCodingData.clear();
      forecastTabPane.setVisible(false);

      // Reset the input fields
      latitudeInput.setText("");
      longitudeInput.setText("");
      placeInput.setText("");
      errorLabel.setVisible(false);

      // Reset the LineChart, X and Y axises
      dailyForecastLineChart.getData().clear();
      xAxisDaily.setLabel("");
      yAxisDaily.setLabel("");
      weeklyForecastLineChart.getData().clear();
      xAxis16Days.setLabel("");
      yAxis16Days.setLabel("");
      yearlyForecastLineChart.getData().clear();
      xAxisYearly.setLabel("");
      yAxisYearly.setLabel("");

      // Reset forecastView
      forecastView.setDailySelectionComboBoxFirst();
      forecastView.setDays16SelectionComboBoxFirst();
      forecastView.setYearlySelectionComboBoxFirst();

   }
}
