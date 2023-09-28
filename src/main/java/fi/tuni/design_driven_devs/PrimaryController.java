package fi.tuni.design_driven_devs;

import javafx.fxml.FXML;
import java.net.http.HttpRequest;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class PrimaryController {
    @FXML
    private Label humidityLabel;
    
    @FXML protected void getWeatherData(ActionEvent event) {
//        HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create("https://jokes-by-api-ninjas.p.rapidapi.com/v1/jokes"))
//				.header("X-RapidAPI-Host", "jokes-by-api-ninjas.p.rapidapi.com")
//				.header("X-RapidAPI-Key", "your-rapidapi-key")
//				.method("GET", HttpRequest.BodyPublishers.noBody())
//				.build();
        humidityLabel.setText("Sign in button pressed");
    }
}
