/*
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.RadioButton?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.layout.StackPane?>
<?import javafx.scene.layout.VBox?>

<AnchorPane prefHeight="800.0" prefWidth="1300.0" xmlns="http://javafx.com/javafx/19" xmlns:fx="http://javafx.com/fxml/1" fx:controller="fi.tuni.design_driven_devs.MainWindowController">
   <StackPane fx:id="contentArea" layoutX="200.0" prefHeight="800.0" prefWidth="1100.0" AnchorPane.bottomAnchor="0.0" AnchorPane.leftAnchor="200.0" AnchorPane.rightAnchor="0.0" AnchorPane.topAnchor="0.0" />
   <VBox prefHeight="800.0" prefWidth="200.0">
      <children>
         <RadioButton fx:id="weatherRadioButton" mnemonicParsing="false" text="Weather" />
         <RadioButton fx:id="forecastRadioButton" mnemonicParsing="false" text="Forecast" />
         <RadioButton fx:id="statisticRadioButton" mnemonicParsing="false" text="Statistic" />
         <RadioButton fx:id="airPollutionRadioButton" mnemonicParsing="false" text="Air Pollution" />
      </children>
   </VBox>
</AnchorPane>
 */
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
    private RadioButton statisticRadioButton;
    @FXML
    private RadioButton airPollutionRadioButton;
    @FXML
    private StackPane contentArea;

    private ToggleGroup toggleGroup;

    private void loadFXML(String fxmlFileName) {
        try {
            contentArea.getChildren().clear();
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
        statisticRadioButton.setUserData("statisticRadioButton");
        airPollutionRadioButton.setUserData("airPollutionRadioButton");
        weatherRadioButton.setToggleGroup(toggleGroup);
        forecastRadioButton.setToggleGroup(toggleGroup);
        statisticRadioButton.setToggleGroup(toggleGroup);
        airPollutionRadioButton.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            // Get the selection from the ToggleGroup and assign it to a String variable
            String value = toggleGroup.getSelectedToggle().getUserData().toString();
            if (value.equals("weatherRadioButton")) {
                value = "weather.fxml";
            } else if (value.equals("forecastRadioButton")) {
                value = "forecast.fxml";
            } else if (value.equals("statisticRadioButton")) {
                value = "statistic.fxml";
            } else if (value.equals("airPollutionRadioButton")) {
                value = "airpollution.fxml";
            }
            loadFXML(value);
        });

    }

}
