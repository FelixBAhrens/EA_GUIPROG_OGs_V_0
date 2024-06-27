package control;

import javafx.fxml.FXML;
import model.GameFile;
import res.Strings;

public class KlassischesMenueController extends StartMenueController {
    @FXML
    public void initialize() {

    }

    @FXML
    public void handleDebugStadt() {
        SceneManager.changeScene(Strings.FXML_STADT);
    }

    @FXML
    public void handleDebugKarte() {
        SceneManager.changeScene(Strings.FXML_KARTE);
    }

    @FXML
    public void handleDebugKampf () {
        SceneManager.changeScene(Strings.FXML_KAMPF);
    }

    @FXML
    public void handleDebugArena () {
        SceneManager.changeScene(Strings.FXML_ARENA);
    }

    @FXML
    public void handleDebugEndgegner () {
        setzeGameFileInstanzLogisch();
        SceneManager.changeScene(Strings.FXML_MISSION_ENDGEGNER);
    }

    @FXML
    public void gameFileAusgeben () {
        System.out.println(GameFile.getInstance().toString());
    }

}
