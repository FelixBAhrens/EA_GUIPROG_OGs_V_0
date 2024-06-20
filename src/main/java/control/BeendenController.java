package control;

import javafx.fxml.FXML;
import model.GameFile;

public class BeendenController extends ControllerController {
    @FXML
    public void initialize () {

    }

    public void speichereSpielstandUndBeendeSpiel(){
        GameFile.speichereSpielstand();
        beendeAnwendung();
    }
}
