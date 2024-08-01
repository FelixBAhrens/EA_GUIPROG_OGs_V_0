package control;

// NOT COMPLETED

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import res.Strings;

/**
 * Der Controller fuer die "missionStarten-view". Hierdurch laesst sich der Kampf
 * ueber die Map aus aufrufen.
 *
 * @author David Kien.
 */
public class MissionStartenController
{
    @FXML
    private AnchorPane missionStartenPane;

    //--------------------------------------------------------------------------

    /**
     * Diese Methode wird aufgerufen, wenn der Benutzer die "Ja"-Option auswaehlt und
     * somit den "Kampf" von der Karte aus starten moechte. Sie setzt den `kampfTyp` auf
     * `ANDERER_KAMPF` und wechselt die Szene zum Kampfszenario, indem sie die
     * entsprechende FXML-Datei laedt.
     *
     * @pre Die `kampfTyp`-Variable in `KampfController` und die Methode `wechseleSzene`
     * in `SzenenManager` muessen existieren. Die Konstante `Strings.FXML_KAMPF` muss den
     * korrekten Pfad zur Kampfszenen-FXML-Datei enthalten.
     *
     * @post Der `kampfTyp` wurde auf `ANDERER_KAMPF` gesetzt, und die Szene zum
     * Kampfszenario gewechselt.
     *
     * @throws Exception Wenn beim Laden der neuen Szene ein Fehler auftritt.
     *
     * @author David Kien.
     */
    @FXML
    private void handleJa () throws Exception
    {
        KampfController.kampfTyp = KampfController.KampfTyp.ANDERER_KAMPF;
        SzenenManager.wechseleSzene(Strings.FXML_KAMPF);
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Benutzer die "Nein"-Option auswaehlt.
     * Sie blendet das Pane `missionStartenPane` aus, um das Missionsstart-Menue zu verbergen.
     *
     * @pre Das Pane `missionStartenPane` muss initialisiert und sichtbar sein.
     *
     * @post Das Pane `missionStartenPane` wurde unsichtbar gemacht.
     *
     * @author David Kien.
     */
    @FXML
    private void handleNein ()
    {
        missionStartenPane.setVisible(false);
    }
}