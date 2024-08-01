package control;

// COMPLETED

import javafx.fxml.FXML;
import res.Strings;

/**
 * Die Klasse PlayerRebornController ist die Controllerklasse fuer die
 * "player-reborn-view.fxml" und stellt Methoden zum Behandeln von Nutzereingaben bereit.
 *
 * @author Felix Ahrens.
 */
public class PlayerRebornController extends ControllerController
{
    /**
     * Initialize-Methode, wie sie fuer alle Controllerklassen von fxml-Dateien verpflichtend ist.
     *
     * @pre /.
     *
     * @post /.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void initialize ()
    {

    }

    /**
     * Methode, die bei Methodenaufruf die zur Stadt wechseln laesst.
     *
     * @pre Die Konstante und die Methode muessen erreichbar sein.
     *
     * @post Die Stadt wurde als Szene geladen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleZurStadt ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_STADT);
    }
}