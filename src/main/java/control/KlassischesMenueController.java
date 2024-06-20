package control;

import javafx.fxml.FXML;
import model.GameFile;
import res.Strings;

import java.io.IOException;

public class KlassischesMenueController extends StartMenueController {
    @FXML
    public void initialize() {

    }

    @FXML
    public void handleDebugStadt() throws IOException {
        SceneManager.changeScene(Strings.FXML_STADT);
    }

    @FXML
    public void handleDebugKarte() throws IOException {
        SceneManager.changeScene(Strings.FXML_KARTE);
    }

    @FXML
    public void handleDebugKampf () throws IOException {
        SceneManager.changeScene(Strings.FXML_KAMPF);
    }

    @FXML
    public void gameFileAusgeben () throws Exception {
        System.out.println(GameFile.getInstance().toString());
    }

}
