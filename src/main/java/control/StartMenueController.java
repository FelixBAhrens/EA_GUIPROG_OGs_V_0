package control;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import res.Strings;

public class StartMenueController extends Application {
    public Stage hauptStage;
    private Scene startMenue;
    private Scene schwierigkeitsMenue;
    private String schwierigkeitsgrad;
    private Scene einstellungenMenue;

    @Override
    public void start(Stage primaryStage) throws Exception {
        hauptStage = primaryStage;
        hauptStage.setTitle("StartMenue");
        primaryStage.setScene(zeigeStartMenue());
        primaryStage.show();
    }

    public Scene zeigeStartMenue(){
        Button fortfahren = new Button("Fortfahren");
        fortfahren.setOnAction(e->{
            hauptStage.setTitle("Schwierigkeitsgrad waehlen");
            hauptStage.setScene(frageSchwierigkeitAb());
        });
        Button einstellungen = new Button("Einstellungen");
        einstellungen.setOnAction(e->{
            hauptStage.setTitle("Einstellungen");
            hauptStage.setScene(zeigeEinstellungen());
        });
        Button beenden = new Button("Beenden");
        beenden.setOnAction(e -> System.exit(0));
        Button resetButton = new Button("Zurueck");
        resetButton.setOnAction(e -> {
            try {
                start(hauptStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });
        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(fortfahren, einstellungen, beenden, resetButton);


        // Erstellen der Menüelemente
        javafx.scene.control.Menu fileMenu = new javafx.scene.control.Menu("Menue");
        javafx.scene.control.MenuItem newItem = new javafx.scene.control.MenuItem("Fortfahren");
        javafx.scene.control.MenuItem newGameItem = new javafx.scene.control.MenuItem("Neues Spiel");
        javafx.scene.control.MenuItem loadItem = new javafx.scene.control.MenuItem("Spiel Laden");
        javafx.scene.control.MenuItem settingItem = new javafx.scene.control.MenuItem("Einstellungen");
        javafx.scene.control.MenuItem exitItem = new javafx.scene.control.MenuItem("Spiel Beenden");

        // Hinzufügen von Aktionen zu den Menüelementen
        exitItem.setOnAction(e -> hauptStage.close());

        // Hinzufügen der Menüelemente zum Menü
        fileMenu.getItems().addAll(newItem, newGameItem, loadItem, settingItem, exitItem);

        // Erstellen der Menüleiste
        javafx.scene.control.MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        // Erstellen des Hauptlayouts
        BorderPane layoutStartmenue = new BorderPane();
        layoutStartmenue.setTop(menuBar);
        layoutStartmenue.setCenter(layout1);

        startMenue = new Scene(layoutStartmenue, 400, 600);
        return startMenue;
    }

    public Scene zeigeEinstellungen(){
        Button geburtstag = new Button("gebe dein Geburtsjahr ein");
        Button zurueck = new Button("Zurück");
        VBox layout2 = new VBox(10);
        layout2.getChildren().addAll(geburtstag, zurueck);
        Text text = new Text("4.2.1969");
        geburtstag.setOnAction(e->{
            layout2.getChildren().add(text);
        });
        zurueck.setOnAction(e->zeigeStartMenue());
        einstellungenMenue = new Scene(layout2, 400, 600);
        return einstellungenMenue;
    }

    public Scene frageSchwierigkeitAb(){

        Button einfach = new Button(Strings.STRING_EINFACH);
        einfach.setOnAction(e->setSchwierigkeitsgrad(Strings.STRING_EINFACH));
        Button normal = new Button(Strings.STRING_NORMAL);
        normal.setOnAction(e->setSchwierigkeitsgrad(Strings.STRING_NORMAL));
        Button schwer = new Button(Strings.STRING_SCHWER);
        schwer.setOnAction(e->setSchwierigkeitsgrad(Strings.STRING_SCHWER));
        VBox layout2 = new VBox(10);
        layout2.getChildren().addAll(einfach, normal, schwer);
        schwierigkeitsMenue = new Scene(layout2, 400, 600);
        return schwierigkeitsMenue;
    }

    public void setSchwierigkeitsgrad(String schwierigkeitsgrad){
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        System.out.println(schwierigkeitsgrad);
        StadtController.zeigeStadt();
    }

    public static void zeigeStory(){

    }
}
