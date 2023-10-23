package fi.tuni.forecast;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ForecastController implements Initializable {
//public class ForecastController {
    private ForecastModel model;
    @FXML
    ImageView titleImage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image forecastIconImage = new Image("https://cdn0.iconfinder.com/data/icons/ikonate/48/line-chart-512.png");
        titleImage.setImage(forecastIconImage);
    }
}
