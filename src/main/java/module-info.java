module EA_GUIPROG_OGs_V_0 {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.desktop;

    opens Main;
    opens control;
    opens model;

    exports Main;
    exports model;
    exports control;
}