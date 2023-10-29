package fi.tuni.airpollution;

import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;

public class AirPollutionView {

    private Label errorLabel;
    private ImageView titleImage;
    private TextField latitudeInput;
    private TextField longitudeInput;
    private LineChart<String, Number> airPollutionLineChart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private ComboBox<String> airPollutionComboBox;

    /**
     * Default constructor for AirPollutionView
     */
    public AirPollutionView(Label errorLabel, ImageView titleImage, TextField latitudeInput,
            TextField longitudeInput, LineChart<String, Number> airPollutionLineChart, CategoryAxis xAxis,
            NumberAxis yAxis, ComboBox<String> airPollutionComboBox) {
        this.errorLabel = errorLabel;
        this.titleImage = titleImage;
        this.latitudeInput = latitudeInput;
        this.longitudeInput = longitudeInput;
        this.airPollutionLineChart = airPollutionLineChart;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.airPollutionComboBox = airPollutionComboBox;
    }

    /**
     * Set errorLabel text
     *
     * @param errorMessage
     */
    public void setErrorLabel(String errorMessage) {
        errorLabel.setText(errorMessage);
    }

    /**
     * Set titleImage image
     *
     * @param imageUrl
     */
    public void setTitleImage(String imageUrl) {
        Image image = new Image(imageUrl);
        titleImage.setImage(image);
    }

    /**
     * Get latitude input
     *
     * @return latitude input
     */
    public String getLatitudeInput() {
        return latitudeInput.getText();
    }

    /**
     * Get longitude input
     *
     * @return longitude input
     */
    public String getLongitudeInput() {
        return longitudeInput.getText();
    }

    /**
     * Clear errorLabel text
     */
    public void clearErrorLabel() {
        errorLabel.setText("");
    }

    /**
     * Set the data in the airPollutionLineChart
     *
     * @param data
     */
    public void setAirPollutionLineChartData(XYChart.Series<String, Number> data) {
        airPollutionLineChart.getData().add(data);
    }

    /**
     * Set the selected item in the airPollutionComboBox
     *
     * @param selectedItem
     */
    public void setAirPollutionComboBoxSelectedItem(String selectedItem) {
        airPollutionComboBox.setValue(selectedItem);
    }

    /**
     * Set the visibility of the airPollutionLineChart
     *
     * @param visible
     */
    public void setAirPollutionLineChartVisibility(boolean visible) {
        airPollutionLineChart.setVisible(visible);
    }

    /**
     * Set the label for the x-axis
     *
     * @param label
     */
    public void setXAxisLabel(String label) {
        xAxis.setLabel(label);
    }

    /**
     * Set the label for the y-axis
     *
     * @param label
     */
    public void setYAxisLabel(String label) {
        yAxis.setLabel(label);
    }

    /**
     * Clear the data in the airPollutionLineChart
     */
    public void clearAirPollutionLineChart() {
        airPollutionLineChart.getData().clear();
    }

    /**
     * Set items for airPollutionComboBox
     * 
     * @param items
     */
    public void setItemsAirPollutionComboBox(ObservableList<String> items) {
        airPollutionComboBox.setItems(items);
    }

    /**
     * Set animated for airPollutionLineChart
     * 
     * @param isAnimated
     */
    public void setAnimatedAirPollutionLineChart(boolean isAnimated) {
        airPollutionLineChart.setAnimated(isAnimated);
    }

    /**
     * Set visible for airPollutionLineChart
     * 
     * @param isVisible
     */
    public void setVisibleAirPollutionLineChart(boolean isVisible) {
        airPollutionLineChart.setVisible(isVisible);
    }

    /**
     * Set visible for airPollutionLineChart
     * 
     * @param value
     */
    public void setValueAirPollutionComboBox(String value) {
        airPollutionComboBox.setValue(value);
    }

    /**
     * Return airPollutionComboBox
     */
    public ComboBox<String> getAirPollutionComboBox() {
        return airPollutionComboBox;
    }
}
