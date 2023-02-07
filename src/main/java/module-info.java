module com.example.neander {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.neander to javafx.fxml;
    exports com.example.neander;
    exports com.example.neander.Exepitions;
    exports com.example.neander.neander;
    exports com.example.neander.configs;
}