module fi.tuni.design_driven_devs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;
    
    opens fi.tuni.design_driven_devs to javafx.fxml;
    opens fi.tuni.weather to javafx.fxml;
    opens fi.tuni.forecast to javafx.fxml;
    opens fi.tuni.airpollution to javafx.fxml;
    opens fi.tuni.statistic to javafx.fxml;


    exports fi.tuni.design_driven_devs;
    exports fi.tuni.weather;
    exports fi.tuni.forecast;
    exports fi.tuni.airpollution;
    exports fi.tuni.statistic;
}
