package control;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.GameFile;
import res.Konstanten;
import res.Strings;

import java.io.File;
import java.io.IOException;

public class StartMenueControllerOld extends Application {

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
    public void start (Stage stage) throws Exception
    {
        hauptStage = stage;
        zeigeStartMenue();
        hauptStage.setScene(startMenue);
        hauptStage.show();
    }

    public void zeigeStartMenue ()
    {
        Button fortfahren = new Button(Strings.FORTFAHREN);
        Button neuesSpiel = new Button(Strings.NEUES_SPIEL);
        Button spielLaden = new Button(Strings.SPIEL_LADEN);
        Button einstellungen = new Button(Strings.EINSTELLUNGEN);
        Button beenden = new Button(Strings.SPIEL_BEENDEN);

        fortfahren.setOnAction(e->{
            starteSpiel(GameFile.leseGameFile(Strings.SPIEL1));
        });

        neuesSpiel.setOnAction(e->zeigeNeuesSpielDialog(hauptStage));

        spielLaden.setOnAction(e->zeigeSpielstaende());

        einstellungen.setOnAction(e->{
            zeigeEinstellungen();
        });

        beenden.setOnAction(e -> System.exit(Konstanten.INT_ZERO));

        //Vbox

        VBox layout1 = new VBox(Konstanten.INT_TEN);
        layout1.getChildren().addAll(fortfahren, neuesSpiel, spielLaden, einstellungen, beenden);

        // Klassisches Menue
        // Erstellen der Menüelemente
        Menu fileMenu = new Menu(Strings.MENUE);
        MenuItem newItem = new MenuItem(Strings.FORTFAHREN);
        MenuItem newGameItem = new MenuItem(Strings.NEUES_SPIEL);
        MenuItem loadItem = new MenuItem(Strings.SPIEL_LADEN);
        MenuItem settingItem = new MenuItem(Strings.EINSTELLUNGEN);
        MenuItem exitItem = new MenuItem(Strings.SPIEL_BEENDEN);
        // Aktionen
        exitItem.setOnAction(e -> System.exit(Konstanten.INT_ZERO));
        // Hinzufügen der Menüelemente zum Menü
        fileMenu.getItems().addAll(newItem, newGameItem, loadItem, settingItem, exitItem);

        // Debugger Menue
        Menu debugMenu = new Menu(Strings.DEBUG);
        MenuItem debugKarte = new MenuItem(Strings.KARTE);
        MenuItem debugKampf = new MenuItem(Strings.KAMPF);
        // Aktionen
        debugMenu.setOnAction(e-> KartenController.setzeKarte(hauptStage));
        //debugMenu.setOnAction(e-> KampfController.setzeKampfKarte(hauptStage));
        // Menuelemente zum Menue hinzufuegen
        debugMenu.getItems().addAll(debugKarte, debugKampf);

        // Erstellen der Menüleiste
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, debugMenu);

        // Erstellen des Hauptlayouts
        BorderPane layoutStartmenue = new BorderPane();
        layoutStartmenue.setTop(menuBar);
        layoutStartmenue.setCenter(layout1);

        startMenue = new Scene(layoutStartmenue, Konstanten.INT_FOUR_HUNDRED, Konstanten.INT_SIX_HUNDRED);
        SzenenController.setzeSzene(Strings.START_MENUE, startMenue, hauptStage);
    }

    /**
     * Methode, die das Menue zeigt, mit dem der Anwender einen neuen Spielstand aufmachen kann.
     * @param hauptStage
     * @Author Felix Ahrens22
     */

    public void zeigeNeuesSpielDialog(Stage hauptStage){
        //String schwierigkeit = frageSchwierigkeitAb();
        hauptStage.setTitle(Strings.NEUES_SPIEL_ERSTELLEN);
        //Text spielNummer_auswaehlen = new Text("Waehle deine Spielnummer selber aus");
        Button spielErstellen = new Button(Strings.SPIEL_ERSTELLEN);
        spielErstellen.setOnAction(e-> {
            try {
                GameFile.erstelleNeueGameFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        VBox layoutNewGame = new VBox(Konstanten.INT_TEN);
        layoutNewGame.getChildren().addAll(spielErstellen, SzenenController.gebeZurueckButton(hauptStage));
        neuesSpielDialog = new Scene(layoutNewGame, Konstanten.INT_FOUR_HUNDRED, Konstanten.INT_SIX_HUNDRED);
        SzenenController.setzeSzene(Strings.NEUES_SPIEL, neuesSpielDialog, hauptStage);

    }

    public void zeigeSpielstaende() {
        Button neuesSpiel = new Button(Strings.NEUES_SPIEL);
        neuesSpiel.setOnAction(e->zeigeNeuesSpielDialog(hauptStage));
        VBox vBox = new VBox(neuesSpiel, SzenenController.gebeZurueckButton(hauptStage));
        File[] spielDateien = GameFile.gebeGameFileListeZurueck();
        for (File datei : spielDateien){
            Button dateiKnopf = new Button(datei.getName());
            dateiKnopf.setOnAction(e->GameFile.leseGameFile(Strings.SPIELDATEIPFAD + datei.getName()));
            vBox.getChildren().add(dateiKnopf);
        }
        spielLadenMenue = new Scene(vBox, Konstanten.INT_FOUR_HUNDRED, Konstanten.INT_SIX_HUNDRED);
        hauptStage.setTitle(Strings.SPIEL_LADEN);
        hauptStage.setScene(spielLadenMenue);
    }

        public void zeigeEinstellungen () {
            VBox layout2 = new VBox(Konstanten.INT_TEN);
            layout2.getChildren().addAll(SzenenController.gebeZurueckButton(hauptStage));
            einstellungenMenue = new Scene(layout2, Konstanten.INT_FOUR_HUNDRED, Konstanten.INT_SIX_HUNDRED);
            SzenenController.setzeSzene(Strings.EINSTELLUNGEN, einstellungenMenue, hauptStage);
        }


        public static void zeigeStory () {

        }

        public void starteSpiel (GameFile spielstand){
            StadtControllerOld.zeigeStadt(hauptStage);
        }
    }

