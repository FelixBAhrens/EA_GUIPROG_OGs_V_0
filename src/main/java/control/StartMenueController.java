package control;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.GameFile;
import res.Strings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartMenueController implements Initializable
{
    @FXML
    private ImageView backgroundImage;
    @FXML
    private Button fortfahrenButton;
    private Button neuesSpielButton;
    private Button spielLadenButton;
    private Button einstellungenButton;
    private Button spielBeendenButton;
    private Stage stage;
    private Scene menueScene;

//@TODO Auslagern in Strings
    @FXML
    private void handleFortfahren (MouseEvent event) throws IOException {
        GameFile.setzeGameFile(GameFile.gebeLetztesSpielZurueck());
        SceneManager.changeScene(Strings.FXML_STADT);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // rotate
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(backgroundImage);
        rotate.setDuration(Duration.seconds(10));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.EASE_BOTH);
        rotate.setAutoReverse(true);
        rotate.setByAngle(10);
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.play();
    }
}