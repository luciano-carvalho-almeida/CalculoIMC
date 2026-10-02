module org.calculoimc {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.calculoimc to javafx.fxml;
    exports org.calculoimc;
}