package hallo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StartMenueController
{
    private Button fortfahrenButton;
    private Button neuesSpielButton;
    private Button spielLadenButton;
    private Button einstellungenButton;
    private Button spielBeendenButton;
    private Stage stage;
    private Scene menuScene;

    public void initialize (Stage stage, Scene scene)
    {
        this.stage = stage;
        this.menuScene = menuScene;
    }

    @FXML
    private void handleFortfahren (MouseEvent event)
    {
        System.out.println("Fortfahren");
    }

    @FXML
    private void handleNeuesSpiel (MouseEvent event)
    {
        System.out.println("Neues Spiel");
    }

    @FXML
    private void handleSpielLaden (MouseEvent event)
    {
        System.out.println("Spiel Laden");
    }

    @FXML
    private void handleEinstellungen (MouseEvent event)
    {
        System.out.println("Einstellungen");
    }

    @FXML
    private void handleSpielBeenden (MouseEvent event)
    {
        System.out.println("Spiel Beenden");
        Platform.exit(); // Abfrage, ob Spielstand gespeichert werden soll wäre noch sinnvoll
    }

    @FXML
    private void handleTutorial (MouseEvent event)
    {
        try
        {
            FXMLLoader tutorialLoader = new FXMLLoader(getClass().getResource("/resources/org.example/tutorial-view.fxml"));
            Parent root = tutorialLoader.load();

            TutorialController controller = tutorialLoader.getController();
            controller.setTutorialText("Tutorial Text");

            controller.initialize(stage, menuScene);

            Scene tutorialScene = new Scene(root);
            stage.setScene(tutorialScene);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
