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
     * @param event
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
     * @pre
     * @post
     * @param artefakt
     * @Author Felix Ahrens
     */
    public void aktualisiereDisplayInformation (Artefakt artefakt)
    {
        if (!artefakt.istImBesitz()){
            verkaufenButton.setVisible(false);
            verbessernButton.setVisible(false);
        } else {
            verbessernButton.setVisible(true);
            verbessernButton.setText(Strings.STAERKE + Strings.SPACE +  Strings.VERBESSERN + Strings.DOPPELPUNKT + Strings.SPACE
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
     * @param ID
     * @return das Artefakt, das der ID des Aufrufenden Elements entspricht.
     * @author Felix Ahrens
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
     * @author Felix Ahrens
     */
    @FXML
    public void initialize ()
    {

    }

    /**
     * Alternative Methode zu "handleMouseEnter", die die Pane und dessen Children visible setzt ohne die
     * voreingestellte Farbe zu veraendern
     *
     * @param event
     * @author Felix Ahrens
     */
    public void handlePaneTransparencyOnMouseEntered (MouseEvent event)
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
     * Alternative Methode zu "handleMouseEnter", die die Pane und dessen Children visible setzt ohne die
     * voreingestellte Farbe zu veraendern
     *
     * @param event
     * @author Felix Ahrens
     */
    public void handlePaneTransparencyOnMouseExited (MouseEvent event)
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
