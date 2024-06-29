package control;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import model.GameFile;

/**
 * Controllerklasse der "fraktionen-camp-view.fxml".
 * @author Felix Ahrens, David Kien
 */
public class FraktionenCampController extends PaneController {
    @FXML
    public AnchorPane baustelle;
    @FXML
    public void initialize() {
        if (GameFile.getInstance().fraktionenCampIstFreigeschaltet()) {
            baustelle.setVisible(false);
        }
        else {
            baustelle.setVisible(true);
        }
    }
}
