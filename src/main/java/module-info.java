module com.mycompany.appferia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.appferia to javafx.fxml;
    opens Modelo to javafx.base;
    exports com.mycompany.appferia;
}
