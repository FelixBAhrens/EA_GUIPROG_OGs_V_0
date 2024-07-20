package control;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Artefakt;
import model.GameFile;
import res.Strings;

/**
 * Die Klasse SchmiedenController ist die Controllerklasse fuer die "schmiede-view.fxml".
 *
 * @Author Felix Ahrens
 */
public class SchmiedenController extends StadtController
{
    @FXML
    public AnchorPane artefaktDisplay;
    @FXML
    public Label infoLabel;
    @FXML
    public Label kostenLabel;
    @FXML
    public Button verbessernButton;
    @FXML
    public Button verkaufenButton;


    /**
     * Methode zum Anzeigen des Artefaktes
     *
     * @param event Das Event, dem dieser Methodenaufruf entstammt.
     * @pre Die verwendeten GUI-Elemente und Methoden muessen erreichbar sein.
     * @post Das "artefaktDisplay" wurde in seinem Inhalt aktualisiert und visible gesetzt.
     * @Author Felix Ahrens
     */
    @FXML
    public void zeigeArtefaktDetails (MouseEvent event)
    {
        aktualisiereDisplayInformation(findeArtefaktAusID(((Pane) event.getSource()).getId()));
        artefaktDisplay.setVisible(true);
    }

    /**
     * Methode, die die auf dem "artefaktDisplay" ausgegebenen Informationen aktualisiert.
     *
     * @param artefakt Das Artefakt, dessen Werte im "infoLabel" dargestellt werden soll.
     * @pre Die GUI-Elemente, Methoden und Konstanten muessen erreichbar sein.
     * @post Die Information im "infoLabel" wurde aktualisiert und fuer die spielende Person sinnvoll verstaendlich
     * dargestellt.
     * @Author Felix Ahrens
     */
    public void aktualisiereDisplayInformation (Artefakt artefakt)
    {
        if (!artefakt.istImBesitz())
        {
            verkaufenButton.setVisible(false);
            verbessernButton.setVisible(false);
        } else
        {
            verbessernButton.setVisible(true);
            verbessernButton.setText(Strings.STAERKE + Strings.SPACE + Strings.VERBESSERN + Strings.DOPPELPUNKT + Strings.SPACE
                    + artefakt.ermittleVerbesserungsKosten() + Strings.SPACE + Strings.GOLD);
            verkaufenButton.setVisible(true);
            verkaufenButton.setText(Strings.VERKAUFEN + Strings.DOPPELPUNKT + Strings.SPACE
                    + artefakt.ermittleWert() + Strings.SPACE + Strings.GOLD);
        }
        infoLabel.setText(Strings.NAME + Strings.DOPPELPUNKT + Strings.SPACE + artefakt.getName() + Strings.NEWLINE
                + Strings.IMBESITZ + Strings.DOPPELPUNKT + Strings.SPACE + artefakt.istImBesitz() + Strings.NEWLINE
                + Strings.ANZAHL_ANWENDUNGEN + Strings.DOPPELPUNKT + Strings.SPACE + artefakt.getAnwendungenUebrig() + Strings.NEWLINE
                + Strings.STAERKE + Strings.DOPPELPUNKT + Strings.SPACE + artefakt.getStaerke());
    }

    /**
     * Methode, die abhhaengig von der uebergebenen ID das entsprechende Artefakt heraussucht
     *
     * @param ID Die ID als String, zu der sich ein Artefakt zuordnen laesst.
     * @return das Artefakt, das der ID des aufrufenden Elements entspricht.
     * @pre Die Singleton-Instanz der GameFile muss gesetzt sein. Die Id muss mit einer der Konstanten-Strings fuer den
     * Namen des jeweiligen Artefakts uebereinstimmen.
     * @post Das zur uebergebenen ID eindeutig zugehoerige Artefakt wurde zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Artefakt findeArtefaktAusID (String ID)
    {
        GameFile instanz = GameFile.getInstanz();
        return switch (ID)
        {
            case (Strings.SCHWERT_PANE) -> instanz.getSchwert();
            case (Strings.STATUE_PANE) -> instanz.getStatue();
            case (Strings.RING_PANE) -> instanz.getRing();
            default -> null;
        };
    }

    /**
     * Initialize-Methode der Controllerklasse fuer die "schmiede-view.fxml".
     *
     * @pre /
     * @post
     * @Author Felix Ahrens
     */
    @FXML
    public void initialize ()
    {

    }

    /**
     * Methode, die die Sichtbarkeit der Pane und dessen Children bei Methodenaufruf auf "sichtbar" setzt.
     *
     * @param event Das Event (Source Pane!!) dem der Methodenaufruf entstammt.
     * @pre Das MouseEvent muss von einer Pane stammen. Die Konstanten, Methoden und GUI-Elemente muessen erreichbar und
     * bekommmbar sein.
     * @post Die Styleclass der Pane wurde veraendert und die Sichtbarkeit dessen Children veraendert.
     * @Author Felix Ahrens
     */
    @FXML
    public void veraenderePaneSichtbarkeitBeiMouseEnter (MouseEvent event)
    {
        Pane pane = (Pane) event.getSource();
        pane.setStyle(Strings.STYLE_BORDER_ORANGE);
        for (Node node : pane.getChildren())
        {
            if (node instanceof Button)
            {
                node.setVisible(true);
            }
        }
    }

    /**
     * Methode, die die Sichtbarkeit der Pane und dessen Children bei Methodenaufruf auf "unsichtbar" setzt.
     *
     * @param event Das Event (Source Pane!!) dem der Methodenaufruf entstammt.
     * @pre Das MouseEvent muss von einer Pane stammen. Die Konstanten, Methoden und GUI-Elemente muessen erreichbar und
     * bekommmbar sein.
     * @post Die Styleclass der Pane wurde veraendert und die Sichtbarkeit dessen Children veraendert.
     * @Author Felix Ahrens
     */
    public void veraenderePaneSichtbarkeitBeiMouseExit (MouseEvent event)
    {
        Pane pane = (Pane) event.getSource();
        pane.setStyle(Strings.STYLE_BORDER_TRANSPARENT);
        for (javafx.scene.Node node : pane.getChildren())
        {
            if (node instanceof Button)
            {
                node.setVisible(false);
            }
        }
    }
}
