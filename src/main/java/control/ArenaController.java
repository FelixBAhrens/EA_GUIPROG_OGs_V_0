package control;

import javafx.fxml.FXML;
import res.Strings;

/**
 * Klasse ArenaController, die die ControllerKlasse für "arena-view.fxml" ist.
 *
 * @author Felix Ahrens
 */
public class ArenaController extends ControllerController
{
    /**
     * Initialize-Methode, die fuer eine FXML-Controllerklasse verpflichtend ist.
     *
     * @pre /
     * @post /
     * @author Felix Ahrens
     */
    @FXML
    public void initialize ()
    {

    }

    /**
     * Methode zum Behandeln der Eingabe der spielenden Person zum Fortfahren. Dabei wird auf den Kampf gewechselt.
     *
     * @pre Die verwendeten Klassen, Konstanten, Methoden und das Enum muessen erreichbar sein.
     * @post Der Kampf wird angezeigt.
     * @Author Felix Ahrens
     */
    @FXML
    public void handleFortfahren ()
    {
        KampfController.kampfTyp = KampfController.KampfTyp.ARENA_KAMPF;
        SzenenManager.wechseleSzene(Strings.FXML_KAMPF);
    }
}
