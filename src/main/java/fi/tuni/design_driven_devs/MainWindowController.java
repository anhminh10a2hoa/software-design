package fi.tuni.design_driven_devs;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

public class MainWindowController {

    @FXML
    private RadioButton weatherRadioButton;
    @FXML
    private RadioButton forecastRadioButton;

    @FXML
    private RadioButton airPollutionRadioButton;
    @FXML
    private StackPane contentArea;

    private ToggleGroup toggleGroup;

    private void loadFXML(String fxmlFileName) {
        try {
            contentArea.getChildren().clear();
            System.out.println("Loading " + fxmlFileName);
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlFileName));
            contentArea.getChildren().add(fxmlLoader.load());
        } catch (IOException e) {
            // Handle or log the exception appropriately

        }
    }

    public void initialize() {
        // Create a ToggleGroup to group the radio buttons
        toggleGroup = new ToggleGroup();
        weatherRadioButton.setUserData("weatherRadioButton");
        forecastRadioButton.setUserData("forecastRadioButton");
        airPollutionRadioButton.setUserData("airPollutionRadioButton");
        weatherRadioButton.setToggleGroup(toggleGroup);
        forecastRadioButton.setToggleGroup(toggleGroup);
        airPollutionRadioButton.setToggleGroup(toggleGroup);

        // Set the default selection to the first radio button and load the
        // corresponding FXML
        toggleGroup.selectToggle(weatherRadioButton);
        loadFXML("weather.fxml");

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            // Get the selection from the ToggleGroup and assign it to a String variable
            String value = toggleGroup.getSelectedToggle().getUserData().toString();
            switch (value) {
                case "weatherRadioButton":
                    value = "weather.fxml";
                    break;
                case "forecastRadioButton":
                    value = "forecast.fxml";
                    break;
                case "airPollutionRadioButton":
                    value = "airpollution.fxml";
                    break;
                default:
                    break;
            }
            loadFXML(value);
        });
    }
}
