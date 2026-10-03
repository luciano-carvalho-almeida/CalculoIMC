module org.calculoimc {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.calculoimc to javafx.fxml;
    exports org.calculoimc;
    exports org.calculoimc.controller;
    opens org.calculoimc.controller to javafx.fxml;
    opens org.calculoimc.model to javafx.base;
}