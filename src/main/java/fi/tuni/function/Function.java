package fi.tuni.function;

public class Function {
    /**
     * Convert Kelvin to Celsius
     *
     * @param kelvinTemperature
     * @return temperature in Celsius
     */
    public static double convertKelvinToCelsius(double kelvinTemperature) {
        double formatValue = kelvinTemperature - 273.15;
        return (double) Math.floor(formatValue * 100) / 100;
    }

    // Convert unix timestamp to date with the string as a format as 2023-12-05 15:00:00
    public static String convertUnixTimeStampToDate(int unixTimeStamp) {
        long timeStamp = (long) unixTimeStamp * 1000;
        java.util.Date date = new java.util.Date(timeStamp);
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
