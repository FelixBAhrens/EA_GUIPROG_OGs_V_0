package control;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Charakter;
import model.GameFile;
import res.Strings;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class StartMenueController extends Application {

    public Stage getHauptStage() {
        return hauptStage;
    }

    public void setHauptStage(Stage hauptStage) {
        this.hauptStage = hauptStage;
    }

    //Name von hauptStage ueberdenken
    private Stage hauptStage;
    private Scene startMenue;
    private Scene schwierigkeitsMenue;
    private Scene neuesSpielDialog;
    private Scene spielLadenMenue;
    private Scene einstellungenMenue;

    @Override
    public void start(Stage stage) throws Exception {
        hauptStage = stage;
        //if (GameFile.)
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
            starteSpiel(GameFile.leseGameFile("Spiel1"));
        });
        neuesSpiel.setOnAction(e->zeigeNeuesSpielDialog(hauptStage));
        spielLaden.setOnAction(e->zeigeSpielstaende());
        einstellungen.setOnAction(e->{
            zeigeEinstellungen();
        });

        beenden.setOnAction(e -> System.exit(0));
        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(fortfahren, neuesSpiel, spielLaden, einstellungen, beenden);

        // Klassisches Menue
        // Erstellen der Menüelemente
        Menu fileMenu = new Menu("Menue");
        MenuItem newItem = new MenuItem("Fortfahren");
        MenuItem newGameItem = new MenuItem("Neues Spiel");
        MenuItem loadItem = new MenuItem("Spiel Laden");
        MenuItem settingItem = new MenuItem("Einstellungen");
        MenuItem exitItem = new MenuItem("Spiel Beenden");
        // Aktionen
        exitItem.setOnAction(e -> System.exit(0));
        // Hinzufügen der Menüelemente zum Menü
        fileMenu.getItems().addAll(newItem, newGameItem, loadItem, settingItem, exitItem);

        // Debugger Menue
        Menu debugMenu = new Menu("Debug");
        MenuItem debugKarte = new MenuItem("Karte");
        MenuItem debugKampf = new MenuItem("Kampf");
        // Aktionen
        debugMenu.setOnAction(e-> KartenController.setzeKarte(hauptStage));
        debugMenu.setOnAction(e-> KampfController.setzeKampfKarte(hauptStage));
        // Menuelemente zum Menue hinzufuegen
        debugMenu.getItems().addAll(debugKarte, debugKampf);

        // Erstellen der Menüleiste
        MenuBar menuBar = new MenuBar();
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
     * @Author Felix Ahrens22
     */
    public void zeigeNeuesSpielDialog(Stage hauptStage){
        //String schwierigkeit = frageSchwierigkeitAb();
        hauptStage.setTitle("Neues Spiel erstellen");
        //Text spielNummer_auswaehlen = new Text("Waehle deine Spielnummer selber aus");
        Button spielErstellen = new Button("Spiel Erstellen");

        spielErstellen.setOnAction(e-> {
            try {
                GameFile.erstelleNeueGameFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        VBox layoutNewGame = new VBox(10);
        layoutNewGame.getChildren().addAll(spielErstellen);
        neuesSpielDialog = new Scene(layoutNewGame, 400, 600);
        SzenenController.setzeSzene("Neues Spiel", neuesSpielDialog, hauptStage);

    }

    public void zeigeSpielstaende() {
        Button neuesSpiel = new Button("Neues Spiel");
        neuesSpiel.setOnAction(e->zeigeNeuesSpielDialog(hauptStage));
        VBox vBox = new VBox(neuesSpiel);
        File[] spielDateien = GameFile.gebeGameFileListeZurueck();
        for (File datei : spielDateien){
            Button dateiKnopf = new Button(datei.getName());
            dateiKnopf.setOnAction(e->GameFile.leseGameFile("src/main/java/res/" + datei.getName()));
            vBox.getChildren().add(dateiKnopf);
        }
        spielLadenMenue = new Scene(vBox, 400, 600);
        hauptStage.setTitle("Spiel Laden");
        hauptStage.setScene(spielLadenMenue);
    }

        public void zeigeEinstellungen () {
            Button geburtstag = new Button("gebe dein Geburtsjahr ein");
            Button ton = new Button("Ton an");
            ton.setOnAction(e -> ton.setText("Ton aus"));
            Button zurueck = new Button("Zurück");
            VBox layout2 = new VBox(10);
            layout2.getChildren().addAll(geburtstag, ton, zurueck);
            Text text = new Text("4.2.1969");
            geburtstag.setOnAction(e -> {
                layout2.getChildren().add(text);
            });
            zurueck.setOnAction(e -> SzenenController.zurueckSzene(hauptStage));
            einstellungenMenue = new Scene(layout2, 400, 600);
            SzenenController.setzeSzene("Einstellungen", einstellungenMenue, hauptStage);
        }


        public static void zeigeStory () {

        }

        public void starteSpiel (GameFile spielstand){
            StadtController.zeigeStadt(hauptStage);
        }
    }
