package fi.tuni.forecast;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ForecastView {
    private TextField latitudeInput;
    private TextField longitudeInput;
    private TextField placeInput;
    private ComboBox<String> dailySelectionComboBox;
    private ComboBox<String> days16SelectionComboBox;
    private ComboBox<String> yearlySelectionComboBox;
    private Label errorLabel;
    private ImageView titleImage;
    private TabPane forecastTabPane;
    private CategoryAxis xAxisDaily;
    private NumberAxis yAxisDaily;
    private CategoryAxis xAxis16Days;
    private NumberAxis yAxis16Days;
    private CategoryAxis xAxisYearly;
    private NumberAxis yAxisYearly;
    private LineChart<String, Number> dailyForecastLineChart;
    private LineChart<String, Number> weeklyForecastLineChart;
    private LineChart<String, Number> yearlyForecastLineChart;

    /**
     * Default constructor for ForecastView
     */
    public ForecastView(TextField latitudeInput, TextField longitudeInput, TextField placeInput,
            ComboBox<String> dailySelectionComboBox, ComboBox<String> days16SelectionComboBox,
            ComboBox<String> yearlySelectionComboBox, Label errorLabel, ImageView titleImage,
            TabPane forecastTabPane, CategoryAxis xAxisDaily, NumberAxis yAxisDaily, CategoryAxis xAxis16Days,
            NumberAxis yAxis16Days, CategoryAxis xAxisYearly, NumberAxis yAxisYearly,
            LineChart<String, Number> dailyForecastLineChart, LineChart<String, Number> weeklyForecastLineChart,
            LineChart<String, Number> yearlyForecastLineChart) {
        this.latitudeInput = latitudeInput;
        this.longitudeInput = longitudeInput;
        this.placeInput = placeInput;
        this.dailySelectionComboBox = dailySelectionComboBox;
        this.days16SelectionComboBox = days16SelectionComboBox;
        this.yearlySelectionComboBox = yearlySelectionComboBox;
        this.errorLabel = errorLabel;
        this.titleImage = titleImage;
        this.forecastTabPane = forecastTabPane;
        this.xAxisDaily = xAxisDaily;
        this.yAxisDaily = yAxisDaily;
        this.xAxis16Days = xAxis16Days;
        this.yAxis16Days = yAxis16Days;
        this.xAxisYearly = xAxisYearly;
        this.yAxisYearly = yAxisYearly;
        this.dailyForecastLineChart = dailyForecastLineChart;
        this.weeklyForecastLineChart = weeklyForecastLineChart;
        this.yearlyForecastLineChart = yearlyForecastLineChart;
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
     * Get place input
     *
     * @return place input
     */
    public String getPlaceInput() {
        return placeInput.getText();
    }

    /**
     * Get dailySelectionComboBox value
     *
     * @return dailySelectionComboBox value
     */
    public ComboBox<String> getDailySelectionComboBox() {
        return dailySelectionComboBox;
    }

    /**
     * Get days16SelectionComboBox
     *
     * @return days16SelectionComboBox
     */
    public ComboBox<String> getDays16SelectionComboBox() {
        return days16SelectionComboBox;
    }

    /**
     * Get yearlySelectionComboBox value
     *
     * @return yearlySelectionComboBox value
     */
    public ComboBox<String> getYearlySelectionComboBox() {
        return yearlySelectionComboBox;
    }

    /**
     * Clear errorLabel text
     */
    public void clearErrorLabel() {
        errorLabel.setText("");
    }

    /**
     * Set the data in the dailyForecastLineChart
     *
     * @param series
     */
    public void setDailyForecastLineChartData(Series<String, Number> series) {
        dailyForecastLineChart.getData().add(series);
    }

    /**
     * Set the data in the weeklyForecastLineChart
     *
     * @param data
     */
    public void setWeeklyForecastLineChartData(LineChart<String, Number> data) {
        weeklyForecastLineChart = data;
    }

    /**
     * Set the data in the yearlyForecastLineChart
     *
     * @param series
     */
    public void setYearlyForecastLineChartData(Series<String, Number> series) {
        yearlyForecastLineChart.getData().add(series);
    }

    /**
     * Get the dailyForecastLineChart
     *
     * @return dailyForecastLineChart
     */
    public LineChart<String, Number> getDailyForecastLineChart() {
        return dailyForecastLineChart;
    }

    /**
     * Get the weeklyForecastLineChart
     *
     * @return weeklyForecastLineChart
     */
    public LineChart<String, Number> getWeeklyForecastLineChart() {
        return weeklyForecastLineChart;
    }

    /**
     * Get the yearlyForecastLineChart
     *
     * @return yearlyForecastLineChart
     */
    public LineChart<String, Number> getYearlyForecastLineChart() {
        return yearlyForecastLineChart;
    }

    public void setItemsDailySelectionComboBox(ObservableList<String> observableArrayList) {
        dailySelectionComboBox.setItems(observableArrayList);
    }

    public void setItemsDays16SelectionComboBox(ObservableList<String> observableArrayList) {
        days16SelectionComboBox.setItems(observableArrayList);
    }

    public void setItemsYearlySelectionComboBox(ObservableList<String> observableArrayList) {
        yearlySelectionComboBox.setItems(observableArrayList);
    }

    public void setDailySelectionComboBoxFirst() {
        dailySelectionComboBox.getSelectionModel().selectFirst();
    }

    public void setDays16SelectionComboBoxFirst() {
        days16SelectionComboBox.getSelectionModel().selectFirst();
    }

    public void setYearlySelectionComboBoxFirst() {
        yearlySelectionComboBox.getSelectionModel().selectFirst();
    }

    void clearDailyLineChart() {
        dailyForecastLineChart.getData().clear();
        xAxisDaily.setLabel("");
        yAxisDaily.setLabel("");
    }

    public void clear16DaysLineChart() {
        weeklyForecastLineChart.getData().clear();
        xAxis16Days.setLabel("");
        yAxis16Days.setLabel("");

    }

    public void clearYearlyLineChart() {
        yearlyForecastLineChart.getData().clear();
        xAxisYearly.setLabel("");
        yAxisYearly.setLabel("");
    }

    public void set16DaysForecastLineChartData(Series<String, Number> series) {
        weeklyForecastLineChart.getData().add(series);
    }

    public void setValueDailySelectionComboBox(String newValue) {
        dailySelectionComboBox.setValue(newValue);
    }

    public void setValueDays16SelectionComboBox(String newValue) {
        days16SelectionComboBox.setValue(newValue);
    }

    public void setValueYearlySelectionComboBox(String newValue) {
        yearlySelectionComboBox.setValue(newValue);
    }

    public void setDailyXAxisLabel(String string) {
        xAxisDaily.setLabel(string);
    }

    public void setDailyYAxisLabel(String selection) {
        yAxisDaily.setLabel(selection);
    }

    public void set16DaysXAxisLabel(String string) {
        xAxis16Days.setLabel(string);
    }

    public void set16DaysYAxisLabel(String selection) {
        yAxis16Days.setLabel(selection);
    }

    public void setYearlyXAxisLabel(String string) {
        xAxisYearly.setLabel(string);
    }

    public void setYearlyYAxisLabel(String selection) {
        yAxisYearly.setLabel(selection);
    }

    public void setLatitudeInput(String latitude) {
        latitudeInput.setText(latitude);
    }

    public void setLongitudeInput(String longitude) {
        longitudeInput.setText(longitude);
    }

    public void setPlaceInput(String string) {
        placeInput.setText(string);
    }

}
