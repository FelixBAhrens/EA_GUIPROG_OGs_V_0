package control;

import javafx.fxml.FXML;
import res.Strings;

/**
 * Controllerklasse fuer die "story-view.fxml".
 *
 * @author Felix Ahrens.
 */
public class StoryController extends ControllerController
{
    /**
     * Initialize-Methode, wie sie in allen FXML-controllerklassen verpflichtend ist.
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
     * Methode zum Fortfahren in die Stadt.
     *
     * @pre Die Konstante sowie die Methode in der Klasse "SzenenManager" muss erreichbar sein.
     *
     * @post Die Szene "stadt-view.fxml" wurde geladen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleFortfahren ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_STADT);
    }
}