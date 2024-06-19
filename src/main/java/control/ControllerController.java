package control;

import javafx.fxml.FXML;
import model.GameFile;
import res.Strings;

import java.io.IOException;

/**
 * Oberklasse aller Controller, die generelle Funktionalitäten beinhaltet
 * @author Felix
 */
public class ControllerController {



    @FXML
    public void handleHilfe() throws IOException {
        SceneManager.changeScene(Strings.FXML_HILFE);
    }

    @FXML
    public void handleZurueck(){
        SceneManager.goBack();
    }

    @FXML
    public void speichereSpielstand(){
        GameFile.speichereSpielstand();
    }

}
