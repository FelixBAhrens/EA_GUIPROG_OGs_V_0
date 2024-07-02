package control;

import javafx.fxml.FXML;
import res.Strings;

/**
 * Klasse ArenaController, die die ControllerKlasse für "arena-view.fxml" ist.
 * @author Felix Ahrens
 */
public class ArenaController extends ControllerController {
    /**
     * Initialize-Methode, die fuer eine FXML-Controllerklasse verpflichtend ist.
     * @author Felix Ahrens
     */
    @FXML
    public void initialize() {

    }
    @FXML
    public void handleFortfahren (){
        KampfController.kampfTyp = KampfController.KampfTyp.ARENA_KAMPF;
        SceneManager.changeScene(Strings.FXML_KAMPF);
    }
}
