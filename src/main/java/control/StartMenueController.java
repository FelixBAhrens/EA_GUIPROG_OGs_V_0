package control;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameFile;
import res.Strings;

import java.io.IOException;
import java.util.Date;

public class StartMenueController extends Application {

    //Name von hauptStage ueberdenken
    public Stage hauptStage;
    private Scene startMenue;
    private Scene schwierigkeitsMenue;
    private String schwierigkeitsgrad;
    private Scene neuesSpielDialog;
    private Scene einstellungenMenue;

    @Override
    public void start(Stage stage) throws Exception {
        hauptStage = stage;
        zeigeStartMenue();
        hauptStage.setScene(startMenue);
        hauptStage.show();
    }

    public void zeigeStartMenue(){
        Button fortfahren = new Button("Fortfahren");
        Button neuesSpiel = new Button("Neues Spiel");
        Button spielLaden = new Button("Spiel Laden");
        Button einstellungen = new Button("Einstellungen");
        Button beenden = new Button("Beenden");
        fortfahren.setOnAction(e->{
            hauptStage.setTitle("Schwierigkeitsgrad waehlen");
            hauptStage.setScene(frageSchwierigkeitAb());
        });
        neuesSpiel.setOnAction(e->zeigeNeuesSpielDialog(hauptStage));
        einstellungen.setOnAction(e->{
            SzenenController.setzeSzene("Einstellungen", zeigeEinstellungen(), hauptStage);
        });

        beenden.setOnAction(e -> System.exit(0));
        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(fortfahren, neuesSpiel, spielLaden, einstellungen, beenden);

        // Klassisches Menue
        // Erstellen der Menüelemente
        javafx.scene.control.Menu fileMenu = new javafx.scene.control.Menu("Menue");
        javafx.scene.control.MenuItem newItem = new javafx.scene.control.MenuItem("Fortfahren");
        javafx.scene.control.MenuItem newGameItem = new javafx.scene.control.MenuItem("Neues Spiel");
        javafx.scene.control.MenuItem loadItem = new javafx.scene.control.MenuItem("Spiel Laden");
        javafx.scene.control.MenuItem settingItem = new javafx.scene.control.MenuItem("Einstellungen");
        javafx.scene.control.MenuItem exitItem = new javafx.scene.control.MenuItem("Spiel Beenden");
        // Aktionen
        exitItem.setOnAction(e -> hauptStage.close());
        // Hinzufügen der Menüelemente zum Menü
        fileMenu.getItems().addAll(newItem, newGameItem, loadItem, settingItem, exitItem);

        // Debugger Menue
        javafx.scene.control.Menu debugMenu = new javafx.scene.control.Menu("Debug");
        javafx.scene.control.MenuItem debugKarte = new javafx.scene.control.MenuItem("Karte");
        // Aktionen
        debugMenu.setOnAction(e->KartenController.setzeKarte(hauptStage));
        // Menuelemente zum Menue hinzufuegen
        debugMenu.getItems().addAll(debugKarte);

        // Erstellen der Menüleiste
        javafx.scene.control.MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, debugMenu);

        // Erstellen des Hauptlayouts
        BorderPane layoutStartmenue = new BorderPane();
        layoutStartmenue.setTop(menuBar);
        layoutStartmenue.setCenter(layout1);

        startMenue = new Scene(layoutStartmenue, 400, 600);
        SzenenController.setzeSzene("StartMenue", startMenue, hauptStage);
    }

    /**
     * Methode, die das Menue zeigt, mit dem der Anwender einen neuen Spielstand aufmachen kann.
     * @param hauptStage
     * @Author Felix Ahrens
     */
    public void zeigeNeuesSpielDialog(Stage hauptStage){
        hauptStage.setTitle("Neues Spiel erstellen");
        Text spielNummer_auswaehlen = new Text("Waehle deine Spielnummer selber aus");
        Button spielErstellen = new Button("Spiel Erstellen");
        spielErstellen.setOnAction(e-> {
            try {
                GameFile.erstelleNeueGameFile("Spiel1", new Date());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        VBox layoutNewGame = new VBox(10);
        layoutNewGame.getChildren().addAll(spielNummer_auswaehlen, spielErstellen);
        neuesSpielDialog = new Scene(layoutNewGame, 400, 600);
        SzenenController.setzeSzene("Neues Spiel", neuesSpielDialog, hauptStage);

    }

    public Scene zeigeEinstellungen(){
        Button geburtstag = new Button("gebe dein Geburtsjahr ein");
        Button ton = new Button("Ton an");
        ton.setOnAction(e-> ton.setText("Ton aus"));
        Button zurueck = new Button("Zurück");
        VBox layout2 = new VBox(10);
        layout2.getChildren().addAll(geburtstag, ton, zurueck);
        Text text = new Text("4.2.1969");
        geburtstag.setOnAction(e->{
            layout2.getChildren().add(text);
        });
        zurueck.setOnAction(e->SzenenController.zurueckSzene(hauptStage));
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
        StadtController.zeigeStadt(hauptStage);
    }

    public static void zeigeStory(){

    }
}
