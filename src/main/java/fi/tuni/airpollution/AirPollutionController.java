package fi.tuni.airpollution;

import java.net.URI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

import org.json.JSONArray;
import org.json.JSONObject;

public class AirPollutionController implements Initializable {
    @FXML
    private Label errorLabel;
    
    @FXML
    ImageView titleImage;
    
    @FXML
    private TextField latitudeInput;
    
    @FXML
    private TextField longitudeInput;
    
    @FXML
    private LineChart<String, Number> airPollutionLineChart;

    @FXML 
    private CategoryAxis xAxis ;
    @FXML 
    private NumberAxis yAxis ;

    @FXML
    private ComboBox<String> airPollutionComboBox;

    private List<AirPollutionModel> airPollutionData = new ArrayList<AirPollutionModel>();
    private AirPollutionModel model;
    private AirPollutionView view;
    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.view = new AirPollutionView(errorLabel, titleImage, latitudeInput, longitudeInput, airPollutionLineChart, xAxis, yAxis, airPollutionComboBox);
        this.view.setTitleImage("https://cdn2.iconfinder.com/data/icons/weather-flat-14/64/weather02-512.png");
        this.view.setItemsAirPollutionComboBox(FXCollections.observableArrayList("CO", "NO", "NO2", "O3", "SO2", "PM2.5", "PM10", "NH3"));
        this.view.setAnimatedAirPollutionLineChart(false);
        this.view.setVisibleAirPollutionLineChart(false);
        this.view.setValueAirPollutionComboBox("CO");
        this.view.getAirPollutionComboBox().getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            this.view.setValueAirPollutionComboBox(newValue);
            this.view.clearAirPollutionLineChart();
            drawLineGraph();
        }); 
    }
    
    @FXML 
    protected void getAirPollutionData(ActionEvent event) {
        String latitude = view.getLatitudeInput();
        String longitude = view.getLongitudeInput();
        
        if (latitude.isEmpty() || longitude.isEmpty()) {
            view.setErrorLabel("Please provide latitude and longitude!");
        } else {
            try {
                double latitudeValue = Double.parseDouble(latitude);
                double longitudeValue = Double.parseDouble(longitude);
                String apiKey = "cda257269cd8f052e74dc19afdd5252c"; // Replace with your OpenWeatherMap API key

                HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create("http://api.openweathermap.org/data/2.5/air_pollution/forecast?lat=" 
                                            + latitudeValue + "&lon=" + longitudeValue + "&APPID=" + apiKey))
                                    .method("GET", HttpRequest.BodyPublishers.noBody())
                                    .build();
                try {
                    errorLabel.setVisible(false);
                    airPollutionData.clear();
                    this.view.clearAirPollutionLineChart();
                    
                    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                    String responseBody = response.body();

                    // Parse the JSON response
                    JSONObject json = new JSONObject(responseBody);
                    JSONArray listAirPollutionDataReturn = json.getJSONArray("list");
                    for (int i = 0; i < listAirPollutionDataReturn.length(); i++) {
                      JSONObject dataToJson = listAirPollutionDataReturn.getJSONObject(i);
                      double coValue = dataToJson.getJSONObject("components").getDouble("co");
                      double noValue = dataToJson.getJSONObject("components").getDouble("no");
                      double no2Value = dataToJson.getJSONObject("components").getDouble("no2");
                      double o3Value = dataToJson.getJSONObject("components").getDouble("o3");
                      double so2Value = dataToJson.getJSONObject("components").getDouble("so2");
                      double pm25Value = dataToJson.getJSONObject("components").getDouble("pm2_5");
                      double pm10Value = dataToJson.getJSONObject("components").getDouble("pm10");
                      double nh3Value = dataToJson.getJSONObject("components").getDouble("nh3");
                      int dateTime = dataToJson.getInt("dt");
                      this.model = new AirPollutionModel(coValue, noValue, no2Value, o3Value, so2Value, pm25Value, pm10Value, nh3Value);
                      this.model.setDateTime(dateTime);
                      airPollutionData.add(this.model);
                    }
                    this.view.setVisibleAirPollutionLineChart(true);
                    drawLineGraph();
                } catch (IOException | InterruptedException e) {
                    this.view.setErrorLabel("Can not fetch air pollution!");
                }
            } catch (NumberFormatException e) {
                this.view.setErrorLabel("Latitude and longitude must be a number!");
            }
        }
    }
    
    @FXML 
    protected void drawLineGraph() {
        String selectedDataToShow = airPollutionComboBox.getValue();
        this.view.setXAxisLabel("μg/m3");
        this.view.setYAxisLabel("Date");
        XYChart.Series series = new XYChart.Series();
        series.setName(selectedDataToShow);
        for (int i = 0; i < airPollutionData.size(); i++) {
            switch (selectedDataToShow) {
                case "CO":
                    series.getData().add(new XYChart.Data(airPollutionData.get(i).getDateTime(), airPollutionData.get(i).getCO()));
                    break;
                case "NO":
                    series.getData().add(new XYChart.Data(airPollutionData.get(i).getDateTime(), airPollutionData.get(i).getNO()));
                    break;
                case "NO2":
                    series.getData().add(new XYChart.Data(airPollutionData.get(i).getDateTime(), airPollutionData.get(i).getNO2()));
                    break;
                case "O3":
                    series.getData().add(new XYChart.Data(airPollutionData.get(i).getDateTime(), airPollutionData.get(i).getO3()));
                    break;
                case "SO2":
                    series.getData().add(new XYChart.Data(airPollutionData.get(i).getDateTime(), airPollutionData.get(i).getSO2()));
                    break;
                case "PM2.5":
                    series.getData().add(new XYChart.Data(airPollutionData.get(i).getDateTime(), airPollutionData.get(i).getPM25()));
                    break;
                case "PM10":
                    series.getData().add(new XYChart.Data(airPollutionData.get(i).getDateTime(), airPollutionData.get(i).getPM10()));
                    break;
                case "NH3":
                    series.getData().add(new XYChart.Data(airPollutionData.get(i).getDateTime(), airPollutionData.get(i).getNH3()));
                    break;
            }
        }
        this.view.setAirPollutionLineChartData(series);
    }
}
