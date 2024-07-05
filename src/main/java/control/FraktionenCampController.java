package control;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import model.GameFile;
import res.Konstanten;

/**
 * Controllerklasse der "fraktionen-camp-view.fxml".
 *
 * @author Felix Ahrens, David Kien
 */
public class FraktionenCampController extends PaneController
{
    @FXML
    public AnchorPane baustelle;

    @FXML
    public void initialize ()
    {
        if (GameFile.getInstanz().fraktionenCampIstFreigeschaltet()) {
            baustelle.setVisible(false);
        } else {
            baustelle.setVisible(true);
        }
    }

    @FXML
    public void schalteBaustelleFrei ()
    {
        GameFile instanz = GameFile.getInstanz();
        if (instanz.getHolzRessource() >= Konstanten.INT_FIFTY && instanz.getGoldRessource() >= Konstanten.INT_FIVE) {
            instanz.setHolzRessource(instanz.getHolzRessource() - Konstanten.INT_FIFTY);
            instanz.setGoldRessource(instanz.getGoldRessource() - Konstanten.INT_FIVE);
            baustelle.setVisible(false);
        }
    }
}
