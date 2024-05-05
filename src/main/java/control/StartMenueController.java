package control;

import javafx.application.Application;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartMenueController extends Application {
    private Scene startMenue;
    private Scene schwierigkeitsMenue;

    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.setTitle("StartMenue");

        //Startmenue
        Button fortfahren = new Button("Fortfahren");
        fortfahren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setTitle("Schwierigkeitsgrad waehlen");
                primaryStage.setScene(schwierigkeitsMenue);
            }
        });
        Button einstellungen = new Button("Einstellungen");
        Button beenden = new Button("Beenden");
        beenden.setOnAction(e -> System.exit(0));
        Button resetButton = new Button("Zurueck");
        resetButton.setOnAction(e -> {
            try {
                start(primaryStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });
        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(fortfahren, einstellungen, beenden, resetButton);
        startMenue = new Scene(layout1, 400, 600);

        Button einfach = new Button("Einfach");
        Button mittel = new Button("Mittel");
        Button schwer = new Button("Schwer");
        VBox layout2 = new VBox(10);
        layout2.getChildren().addAll(einfach, mittel, schwer, resetButton);
        schwierigkeitsMenue = new Scene(layout2, 400, 600);

        // Setzen der Szene im Hauptfenster
        primaryStage.setScene(startMenue);
        // Anzeigen des Hauptfensters
        primaryStage.show();
    }
}
