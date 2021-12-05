module com.example.notthefinalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens com.example.notthefinalproject to javafx.fxml;
    exports com.example.notthefinalproject;
}