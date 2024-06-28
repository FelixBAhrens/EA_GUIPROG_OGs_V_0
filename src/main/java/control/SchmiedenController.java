package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Artefakt;
import model.GameFile;
import res.Konstanten;
import res.Strings;


public class SchmiedenController extends PaneController {
    @FXML
    public AnchorPane artefaktDisplay;
    /**
     * Methode zum Anzeigen des Artefaktes
     * @param event
     */
    @FXML
    public void zeigeArtefaktDetails (MouseEvent event) {
        aktualisiereDisplayInformation(findeArtefaktAusID(((Pane) event.getSource()).getId()));
        artefaktDisplay.setVisible(true);
    }

    public void aktualisiereDisplayInformation (Artefakt artefakt)
    {
        Label label = (Label) artefaktDisplay.getChildren().get(Konstanten.INT_ONE);
        label.setText(Strings.NAME + Strings.DOPPELPUNKT + Strings.SPACE + artefakt.getName() + Strings.NEWLINE
        + Strings.IMBESITZ + Strings.DOPPELPUNKT + Strings.SPACE + artefakt.istImBesitz() + Strings.NEWLINE
        + Strings.ANZAHL_ANWENDUNGEN + Strings.DOPPELPUNKT + Strings.SPACE + artefakt.getAnwendungenUebrig() + Strings.NEWLINE
        + Strings.STAERKE + Strings.DOPPELPUNKT + Strings.SPACE + artefakt.getStaerke());
    }

    /**
     * Methode, die abhhaengig von der uebergebenen ID das entsprechende Artefakt heraussucht
     * @param ID
     * @return das Artefakt, das der ID des Aufrufenden Elements entspricht.
     * @author Felix Ahrens
     */
    public Artefakt findeArtefaktAusID (String ID)
    {
        GameFile instanz = GameFile.getInstance();
        switch (ID) {
            case (Strings.SCHWERT_PANE): {
                return instanz.getSchwert();
            }
            case (Strings.STATUE_PANE): {
                return instanz.getStatue();
            }
            case (Strings.RING_PANE): {
                return instanz.getRing();
            }
        }
        return null;
    }

    /**
     * Initialize-Methode der Controllerklasse fuer die "schmiede-view.fxml".
     * @author Felix Ahrens
     */
    @FXML
    public void initialize() {

    }

    public void gebeArteFaktAus () {
        System.out.println(GameFile.getInstance().getSchwert().toString());
    }
}
