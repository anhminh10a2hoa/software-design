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
import javafx.scene.image.Image;
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

    private List<AirPollutionModel> airPollutionData;
    
    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image weatherIconImage = new Image("https://cdn-icons-png.flaticon.com/512/8371/8371878.png");
        titleImage.setImage(weatherIconImage);
        airPollutionComboBox.setItems(FXCollections.observableArrayList("CO", "NO", "NO2", "O3", "SO2", "PM2.5", "PM10", "NH3"));
        airPollutionLineChart.setAnimated(false);
        airPollutionLineChart.setVisible(false);
        airPollutionComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            airPollutionComboBox.setValue(newValue);
            airPollutionLineChart.getData().clear();
            drawLineGraph();
        }); 
    }
    
    @FXML 
    protected void getWeatherData(ActionEvent event) {
        String latitude = latitudeInput.getText();
        String longitude = longitudeInput.getText();
        
        if (latitude.isEmpty() || longitude.isEmpty()) {
            errorLabel.setVisible(true);
            errorLabel.setText("Please provide latitude and longitude!");
        } else {
            try {
                double latitudeValue = Double.parseDouble(latitude);
                double longitudeValue = Double.parseDouble(longitude);
                HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create("http://api.openweathermap.org/data/2.5/air_pollution/forecast?lat=" 
                                            + latitudeValue + "&lon=" + longitudeValue + "&APPID=cda257269cd8f052e74dc19afdd5252c"))
                                    .method("GET", HttpRequest.BodyPublishers.noBody())
                                    .build();
                try {
                    errorLabel.setVisible(false);
                    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                    String responseBody = response.body();

                    // Parse the JSON response
                    JSONObject json = new JSONObject(responseBody);
                    JSONArray listAirPollutionDataReturn = json.getJSONArray("list");
                    airPollutionData = new ArrayList<AirPollutionModel>();
                    for (int i = 0; i < listAirPollutionDataReturn.length(); i++) {
                      AirPollutionModel airPollutionModel = new AirPollutionModel();
                      JSONObject dataToJson = listAirPollutionDataReturn.getJSONObject(i);
                      airPollutionModel.setCO(dataToJson.getJSONObject("components").getDouble("co"));
                      airPollutionModel.setNO(dataToJson.getJSONObject("components").getDouble("no"));
                      airPollutionModel.setNO2(dataToJson.getJSONObject("components").getDouble("no2"));
                      airPollutionModel.setO3(dataToJson.getJSONObject("components").getDouble("o3"));
                      airPollutionModel.setSO2(dataToJson.getJSONObject("components").getDouble("so2"));
                      airPollutionModel.setPM25(dataToJson.getJSONObject("components").getDouble("pm2_5"));
                      airPollutionModel.setPM10(dataToJson.getJSONObject("components").getDouble("pm10"));
                      airPollutionModel.setNH3(dataToJson.getJSONObject("components").getDouble("nh3"));
                      airPollutionModel.setDateTime(dataToJson.getInt("dt"));
                      airPollutionData.add(airPollutionModel);
                    }
                    airPollutionLineChart.setVisible(true);
                    airPollutionComboBox.setValue("CO");
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
    
    @FXML 
    protected void drawLineGraph() {
        String selectedDataToShow = airPollutionComboBox.getValue();
        yAxis.setLabel("μg/m3");
        xAxis.setLabel("Date");
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
        airPollutionLineChart.getData().add(series);
    }
}
