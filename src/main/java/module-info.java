module EA_GUIPROG_OGs_V_0 {
    exports control;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.desktop;

    opens Main;
    opens control;

    exports Main;

}