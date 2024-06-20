package control;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.GameFile;
import res.Strings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartMenueController extends ControllerController implements Initializable
{
    @FXML
    private ImageView backgroundImage;


    @FXML
    private void handleFortfahren () throws IOException {
        GameFile.setzeGameFile(GameFile.gebeLetztesSpielZurueck());
        SceneManager.changeScene(Strings.FXML_STADT);
    }

    @FXML
    private void handleNeuesSpiel () throws IOException {
        SceneManager.changeScene(Strings.FXML_NEUESSPIEL);
    }

    @FXML
    private void handleSpielLaden () throws IOException {
        SceneManager.changeScene(Strings.FXML_SPIELLADEN);
    }

    @FXML
    private void handleEinstellungen () throws IOException {
        SceneManager.changeScene(Strings.FXML_EINSTELLUNGEN);
    }

    @FXML
    private void handleSpielBeenden () throws IOException {
        SceneManager.changeScene(Strings.FXML_SPEICHERN_ABFRAGE);
    }

    //Der kann jetzt raus, das Tutuorial wird ja beim neuen Spiel angezeigt
    @FXML
    private void handleTutorial (MouseEvent event) throws IOException
    {
        SceneManager.changeScene(Strings.FXML_TUTORIAL);
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