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

    /**
     * Default constructor
     */
    public AirPollutionModel() {
        // Default constructor (you can initialize default values here if needed)
    }

    /**
     * Initialize air pollution data
     *
     * @param CO            Carbon Monoxide level
     * @param NO            Nitric Oxide level
     * @param NO2           Nitrogen Dioxide level
     * @param O3            Ozone level
     * @param SO2           Sulfur Dioxide level
     * @param PM25          Particulate Matter (2.5 micrometers) level
     * @param PM10          Particulate Matter (10 micrometers) level
     * @param NH3           Ammonia level
     */
    public AirPollutionModel(double CO, double NO, double NO2, double O3, double SO2, double PM25, double PM10, double NH3) {
        this.CO = CO;
        this.NO = NO;
        this.NO2 = NO2;
        this.O3 = O3;
        this.SO2 = SO2;
        this.PM25 = PM25;
        this.PM10 = PM10;
        this.NH3 = NH3;
    }

    /**
     * Get Carbon Monoxide level
     *
     * @return CO (Carbon Monoxide) level
     */
    public double getCO() {
        return CO;
    }

    /**
     * Set Carbon Monoxide level
     *
     * @param CO Carbon Monoxide level
     */
    public void setCO(double CO) {
        this.CO = CO;
    }

    /**
     * Get Nitric Oxide level
     *
     * @return NO (Nitric Oxide) level
     */
    public double getNO() {
        return NO;
    }

    /**
     * Set Nitric Oxide level
     *
     * @param NO Nitric Oxide level
     */
    public void setNO(double NO) {
        this.NO = NO;
    }

    /**
     * Get Nitrogen Dioxide level
     *
     * @return NO2 (Nitrogen Dioxide) level
     */
    public double getNO2() {
        return NO2;
    }

    /**
     * Set Nitrogen Dioxide level
     *
     * @param NO2 Nitrogen Dioxide level
     */
    public void setNO2(double NO2) {
        this.NO2 = NO2;
    }

    /**
     * Get Ozone level
     *
     * @return O3 (Ozone) level
     */
    public double getO3() {
        return O3;
    }

    /**
     * Set Ozone level
     *
     * @param O3 Ozone level
     */
    public void setO3(double O3) {
        this.O3 = O3;
    }

    /**
     * Get Sulfur Dioxide level
     *
     * @return SO2 (Sulfur Dioxide) level
     */
    public double getSO2() {
        return SO2;
    }

    /**
     * Set Sulfur Dioxide level
     *
     * @param SO2 Sulfur Dioxide level
     */
    public void setSO2(double SO2) {
        this.SO2 = SO2;
    }

    /**
     * Get Particulate Matter (2.5 micrometers) level
     *
     * @return PM25 (Particulate Matter 2.5) level
     */
    public double getPM25() {
        return PM25;
    }

    /**
     * Set Particulate Matter (2.5 micrometers) level
     *
     * @param PM25 Particulate Matter 2.5 level
     */
    public void setPM25(double PM25) {
        this.PM25 = PM25;
    }

    /**
     * Get Particulate Matter (10 micrometers) level
     *
     * @return PM10 (Particulate Matter 10) level
     */
    public double getPM10() {
        return PM10;
    }

    /**
     * Set Particulate Matter (10 micrometers) level
     *
     * @param PM10 Particulate Matter 10 level
     */
    public void setPM10(double PM10) {
        this.PM10 = PM10;
    }

    /**
     * Get Ammonia level
     *
     * @return NH3 (Ammonia) level
     */
    public double getNH3() {
        return NH3;
    }

    /**
     * Set Ammonia level
     *
     * @param NH3 Ammonia level
     */
    public void setNH3(double NH3) {
        this.NH3 = NH3;
    }

    /**
     * Get the date and time of the data
     *
     * @return Date and time as a String
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Set the date and time of the data
     *
     * @param dateTime Date and time as an int (Unix timestamp)
     */
    public void setDateTime(int dateTime) {
        String dateAsText = new SimpleDateFormat("MM-dd HH:mm:ss")
                .format(new Date(dateTime * 1000L));
        this.dateTime = dateAsText;
    }
}
