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

import fi.tuni.function.APIAirPollutionData;

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
            errorLabel.setVisible(true);
            view.setErrorLabel("Please provide latitude and longitude!");
            return;
        } else {
            errorLabel.setVisible(false);
            try {
                double latitudeValue = Double.parseDouble(latitude);
                double longitudeValue = Double.parseDouble(longitude);
                if(latitudeValue < -90 || latitudeValue > 90) {
                    errorLabel.setVisible(true);
                    view.setErrorLabel("Latitude must be between -90 and 90!");
                    return;
                } else if(longitudeValue < -180 || longitudeValue > 180) {
                    errorLabel.setVisible(true);
                    view.setErrorLabel("Longitude must be between -180 and 180!");
                    return;
                } 
                String apiKey = "cda257269cd8f052e74dc19afdd5252c"; // Replace with your OpenWeatherMap API key

                HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create("http://api.openweathermap.org/data/2.5/air_pollution/forecast?lat=" 
                                            + latitudeValue + "&lon=" + longitudeValue + "&APPID=" + apiKey))
                                    .method("GET", HttpRequest.BodyPublishers.noBody())
                                    .build();
                try {
                    this.view.clearErrorLabel();
                    airPollutionData.clear();
                    this.view.clearAirPollutionLineChart();
                    airPollutionData = APIAirPollutionData.fetchAirPollutionData(HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()));
                    this.view.setVisibleAirPollutionLineChart(true);
                    drawLineGraph();
                } catch (IOException | InterruptedException e) {
                    this.view.setErrorLabel("Can not fetch air pollution!");
                }
            } catch (NumberFormatException e) {
                errorLabel.setVisible(true);
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
