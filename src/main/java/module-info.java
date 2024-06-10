module EA_GUIPROG_OGs_V_0 {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.fxml;

    opens Main;
    opens control;
    opens model;

    exports Main;
    exports model;
    exports control;

    opens hallo to javafx.fxml;
    exports hallo;
}