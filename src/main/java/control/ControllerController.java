package control;

import javafx.fxml.FXML;
import res.Strings;

import java.io.IOException;

/**
 * Oberklasse aller Controller, die generelle Funktionalitäten beinhaltet
 * @author Felix
 */
public class ControllerController extends GameMasterViewController {



    @FXML
    public void handleHilfe() throws IOException {
        SceneManager.changeScene(Strings.FXML_HILFE);
    }

    @FXML
    public void handleZurueck(){
        SceneManager.goBack();
    }



}
