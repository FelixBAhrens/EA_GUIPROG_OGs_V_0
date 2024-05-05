package control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SimpleMenuBarExample extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Erstellen der Menüelemente
        Menu fileMenu = new Menu("Menue");
        MenuItem newItem = new MenuItem("Fortfahren");
        MenuItem newGameItem = new MenuItem("Neues Spiel");
        MenuItem loadItem = new MenuItem("Spiel Laden");
        MenuItem settingItem = new MenuItem("Einstellungen");
        MenuItem exitItem = new MenuItem("Spiel Beenden");

        // Hinzufügen von Aktionen zu den Menüelementen
        exitItem.setOnAction(e -> primaryStage.close());

        // Hinzufügen der Menüelemente zum Menü
        fileMenu.getItems().addAll(newItem, newGameItem, loadItem, settingItem, exitItem);

        // Erstellen der Menüleiste
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        // Erstellen des Hauptlayouts
        BorderPane root = new BorderPane();
        root.setTop(menuBar);

        // Erstellen der Szene
        Scene scene = new Scene(root, 400, 300);

        // Setzen der Szene im Hauptfenster
        primaryStage.setScene(scene);

        // Setzen des Titels des Hauptfensters
        primaryStage.setTitle("Simple MenuBar Example");

        // Anzeigen des Hauptfensters
        primaryStage.show();
    }
}
