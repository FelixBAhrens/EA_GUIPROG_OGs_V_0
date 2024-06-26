package control;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.GameFile;
import res.Konstanten;
import res.Strings;

import java.net.URL;
import java.util.ResourceBundle;

public class StartMenueController extends ControllerController implements Initializable
{
    @FXML
    private ImageView backgroundImage;

    /**
     * Methode, die durch einen Knopfdruck aufgerufen wird und die GameFile setzt und die Stadt anzeigen laesst
     * @author Felix Ahrens, David Kien
     */
    @FXML
    private void handleFortfahren () {
        GameFile gamefile = GameFile.gebeLetztesSpielZurueck();
        if (gamefile != null) {
            GameFile.setzeGameFile(gamefile);
            SceneManager.changeScene(Strings.FXML_STADT);
        } else {
            handleNeuesSpiel();
        }
    }

    @FXML
    private void handleNeuesSpiel () {
        SceneManager.changeScene(Strings.FXML_NEUESSPIEL);
    }

    @FXML
    private void handleSpielLaden () {
        SceneManager.changeScene(Strings.FXML_SPIELLADEN);
    }

    @FXML
    private void handleEinstellungen () {
        SceneManager.changeScene(Strings.FXML_EINSTELLUNGEN);
    }

    @FXML
    private void handleSpielBeenden () {
        SceneManager.changeScene(Strings.FXML_SPEICHERN_ABFRAGE);
    }

    /**
     * Override der Initialize-Methode des implementierten Interfaces "Initializable"
     * @param url
     * @param resourceBundle
     * @author Felix Ahrens
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // rotate
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(backgroundImage);
        rotate.setDuration(Duration.seconds(Konstanten.INT_TEN));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.EASE_BOTH);
        rotate.setAutoReverse(true);
        rotate.setByAngle(Konstanten.INT_TEN);
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.play();
    }
}