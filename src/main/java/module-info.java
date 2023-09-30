module fi.tuni.design_driven_devs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;
    
    opens fi.tuni.design_driven_devs to javafx.fxml;
    exports fi.tuni.design_driven_devs;
}
