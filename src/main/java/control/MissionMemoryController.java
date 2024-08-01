package control;

// COMPLETED (OPEN TODO)

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import res.Konstanten;
import res.Strings;

/**
 * Die Klasse MissionMemoryController ist die Controllerklasse fuer die FXML Datei
 * "mission-memory-view.fxml". In ihr befinden sich alle Methoden, die die Mission
 * spielbar machen. Dazu gehoeren Spiellogische Methoden und solche, die die GUI veraendern.
 *
 * @author Felix Ahrens.
 */
public class MissionMemoryController extends ControllerController
{
    private int steinPaare = Konstanten.INT_ZERO;

    @FXML
    private boolean ersterSteinUmgedreht = false;

    @FXML
    public Label SpielInfoLabel;
    @FXML
    public Label steinIDLabel;

    @FXML
    public AnchorPane MissionBeendenLabel;
    @FXML
    private AnchorPane ersterStein;

    /**
     * Initialize-Methode, wie sie fuer alle FXML-Controllerklassen verpflichtend ist.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void initialize ()
    {

    }

    /**
     * Methode zum Behandeln des "umdrehens" eines Steins. Dabei wird ueberprueft,
     * ob das der erste von zwei jeweils "umdrehbaren" Steinen ist. Ist das der Fall,
     * wird der Stein nur in einer Klassenvariable gespeichert und dessen
     * ID der spielenden Person ausgegeben. Ist schon ein Stein als "umgedreht" gespeichert,
     * wird dieser mit dem nun "umgedrehten" Stein verglichen. Haben beide den gleichen
     * Kennbuchstaben an festgelegter Stelle, wird das Paar ueber den Methodenaufruf
     * "updateSpielInfoLabel" im entsprechenden Label fuer die anwendende Person sichtbar
     * gespeichert und beide Steine (AnchorPanes) auf invisible gesetzt.
     *
     * @param event Das Event, aus dem der Methodenaufruf stammt.
     *
     * @pre Die GUI-Elemente, Methoden und Klassen muessen existieren und verwendbar sein.
     *
     * @post Es wurde entweder die eine ID des ersten Steins angezeigt oder beide
     * Steine invisible gesetzt, dafuer die IDs im "SpielInfoLabel" angezeigt und
     * beide invisible gemacht oder beide Steine auf "nicht umgedreht" zurueckgesetzt.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleStein (MouseEvent event)
    {
        AnchorPane steinPane = (AnchorPane) event.getSource();
        if (!ersterSteinUmgedreht)
        {
            ersterStein = steinPane;
            steinIDLabel.setText(steinPane.getId());
            ersterSteinUmgedreht = true;
        }
        else
        {
            steinIDLabel.setText(Strings.SPACE);
            if (ersterStein.getId().toString().charAt(Konstanten.INT_FIVE) == (steinPane.getId().toString().charAt(Konstanten.INT_FIVE)) && ersterStein.getId() != steinPane.getId())
            {
                ersterStein.setVisible(false);
                steinPane.setVisible(false);
                steinPaare++;
                updateSpielInfoLabel(ersterStein.getId(), steinPane.getId());
            }
            ersterSteinUmgedreht = false;
        }
    }

    /**
     * Methode, die das InfoLabel der Mission updated, indem der vorhandene Text ergaenzt wird.
     *
     * @param steinEins Die ID des ersten Steins.
     *
     * @param steinZwei Die ID des zweiten Steins.
     *
     * @pre Das "SpielInfoLabel" muss existieren. Die Konstanten muessen verwendbar sein.
     *
     * @post Das "SpielInfoLabel" zeigt die bisher gesammelten Steinpaare an.
     * Sind das alle (also Acht), wird ueber den Methodenaufruf beendeMissionWennFertig
     * die Mission beendet.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void updateSpielInfoLabel (String steinEins, String steinZwei)
    {
        SpielInfoLabel.setText(SpielInfoLabel.getText().toString() + Strings.NEWLINE + steinEins + Strings.SPACE + steinZwei);
        beendeMissionWennFertig();
    }

    /**
     * Methode, die ueberprueft, ob alle Steinpaare schon gesammelt wurden.
     *
     * @pre Die GUI-Elemente und Konstanten muessen existieren und erreichbar sein.
     *
     * @post Die Mission wird beendet, wenn alle Steinpaare gesammelt wurden.
     *
     * @author Felix Ahrens.
     */
    public void beendeMissionWennFertig ()
    {
        if (steinPaare > Konstanten.INT_SEVEN)
        {
            MissionBeendenLabel.setVisible(true);
        }
    }

    /**
     * Methode, die die Mission beendet. Diese wird von dem Beenden-Button aufgerufen,
     * wenn die Mission abgeschlossen wurde.
     *
     * @pre Die Methode "wechsleSzene" muss in der Klasse "SzenenManager" existieren.
     *
     * @post Die Stadt-Szene wurde geladen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void beendeMission ()
    {
        // TODO
        //Noch Belohnungnen auszahlen.
        SzenenManager.wechseleSzene(Strings.FXML_STADT);
    }
}
