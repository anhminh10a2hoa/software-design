package fi.tuni.airpollution;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AirPollutionModel {
    private double CO;
    private double NO;
    private double NO2;
    private double O3;
    private double SO2;
    private double PM25;
    private double PM10;
    private double NH3;
    private String dateTime;

    // Default constructor
    public AirPollutionModel() {
        // Initialize any default values if needed
    }

    // Parameterized constructor
    public AirPollutionModel(double CO, double NO, double NO2, double O3, double SO2, double PM25, double PM10, double NH3, String dateTime) {
        this.CO = CO;
        this.NO = NO;
        this.NO2 = NO2;
        this.O3 = O3;
        this.SO2 = SO2;
        this.PM25 = PM25;
        this.PM10 = PM10;
        this.NH3 = NH3;
        this.dateTime = dateTime;
    }

    // Getter and Setter methods for each property

    public double getCO() {
        return CO;
    }

    public void setCO(double CO) {
        this.CO = CO;
    }

    public double getNO() {
        return NO;
    }

    public void setNO(double NO) {
        this.NO = NO;
    }

    public double getNO2() {
        return NO2;
    }

    public void setNO2(double NO2) {
        this.NO2 = NO2;
    }

    public double getO3() {
        return O3;
    }

    public void setO3(double O3) {
        this.O3 = O3;
    }

    public double getSO2() {
        return SO2;
    }

    public void setSO2(double SO2) {
        this.SO2 = SO2;
    }

    public double getPM25() {
        return PM25;
    }

    public void setPM25(double PM25) {
        this.PM25 = PM25;
    }

    public double getPM10() {
        return PM10;
    }

    public void setPM10(double PM10) {
        this.PM10 = PM10;
    }

    public double getNH3() {
        return NH3;
    }

    public void setNH3(double NH3) {
        this.NH3 = NH3;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(int dateTime) {
        String dateAsText = new SimpleDateFormat("MM-dd HH:mm:ss")
                          .format(new Date(dateTime * 1000L));
        this.dateTime = dateAsText;
    }
}
