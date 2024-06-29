package control;

import javafx.fxml.FXML;
import model.GameFile;
import res.Konstanten;

/**
 * Controllerklasse der "baustelle-view.fxml". Steuert das Baustellenoverlay.
 * @author Felix Ahrens
 */
public class BaustellenController {
    @FXML
    public void initialize() {

    }

    @FXML
    public void schalteBaustelleFrei () {
        GameFile instanz = GameFile.getInstance();
        if (instanz.getHolzRessource() >= Konstanten.INT_FIFTY && instanz.getGoldRessource() >= Konstanten.INT_FIVE) {
            //schalte frei
        }
    }
}
