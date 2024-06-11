package control;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class StartMenueController
{
    private Button fortfahrenButton;
    private Button neuesSpielButton;
    private Button spielLadenButton;
    private Button einstellungenButton;
    private Button spielBeendenButton;
    private Stage stage;
    private Scene menuScene;

    public void initialize (Stage stage)
    {
        this.stage = stage;
    }

    @FXML
    private void handleFortfahren (MouseEvent event) throws IOException {
        SceneManager.changeScene("stadt-view.fxml");
    }

    @FXML
    private void handleNeuesSpiel (MouseEvent event) throws IOException {
        SceneManager.changeScene("neuesSpiel-view.fxml");
    }

    @FXML
    private void handleSpielLaden (MouseEvent event)
    {
        System.out.println("Spiel Laden");
    }

    @FXML
    private void handleEinstellungen (MouseEvent event) throws IOException {
        SceneManager.changeScene("einstellungen-view.fxml");
    }
    @FXML
    private void handleSpielBeenden (MouseEvent event)
    {
        System.out.println("Spiel Beenden");
        Platform.exit(); // Abfrage, ob Spielstand gespeichert werden soll wäre noch sinnvoll
    }

    @FXML
    private void handleTutorial (MouseEvent event) throws IOException
    {
        SceneManager.changeScene("tutorial-view.fxml");
    }
}