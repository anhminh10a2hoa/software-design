package fi.tuni.forecast;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ForecastView {
    private ImageView titleImage;
    
    public void setTitleImage(String imageUrl) {
        Image forecastIconImage = new Image(imageUrl);
        titleImage.setImage(forecastIconImage);
    }
    
}
