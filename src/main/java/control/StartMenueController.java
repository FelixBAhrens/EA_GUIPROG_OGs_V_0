package control;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import model.GameFile;
import res.Strings;

public class StartMenueController extends ControllerController
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
            SzenenManager.wechseleSzene(Strings.FXML_STADT);
        } else {
            handleNeuesSpiel();
        }
    }

    @FXML
    private void handleNeuesSpiel () {
        SzenenManager.wechseleSzene(Strings.FXML_NEUESSPIEL);
    }

    @FXML
    private void handleSpielLaden () {
        SzenenManager.wechseleSzene(Strings.FXML_SPIELLADEN);
    }

    @FXML
    private void handleArena () {
        SzenenManager.wechseleSzene(Strings.FXML_ARENA);
    }

    @FXML
    private void handleEinstellungen () {
        SzenenManager.wechseleSzene(Strings.FXML_EINSTELLUNGEN);
    }

    @FXML
    private void handleSpielBeenden () {
        SzenenManager.wechseleSzene(Strings.FXML_SPEICHERN_ABFRAGE);
    }

}