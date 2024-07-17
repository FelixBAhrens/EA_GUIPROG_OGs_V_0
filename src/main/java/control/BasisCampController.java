package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import res.Konstanten;
import res.Strings;

/**
 * Der BasisCampController bildet die Controllerklasse zur "basis-camp-view.fxml" und beinhaltet Methoden zur Behandlung von Nutzereingaben sowie zur Manipulation der GUI um auf Veraenderungen im Spiel zu reagieren.
 * Dem Controller fehlen noch viele Methoden zum richtigen Arbeiten. Das GUI-Framework steht allerdings.
 * @Author Felix Ahrens
 */
public class BasisCampController extends PaneController
{

    @FXML
    public VBox einheitenVbox;
    @FXML
    public Label detailLabel;
    @FXML
    public ProgressBar BarA;
    @FXML
    public ProgressBar BarB;
    @FXML
    public ProgressBar BarC;
    @FXML
    public ProgressBar BarD;
    @FXML
    public ProgressBar BarE;

    /**
     * Initialize-Methode der Controllerklasse einer FXMl-Datei
     * @pre /
     * @post /
     * @Author Felix Ahrens
     */
    @FXML
    public void initialize ()
    {

    }

    /**
     * Methode, die auf Knopfdruck den Aufbau/ die Rekrutierung von Truppen veranlasst.
     * @pre Die verwendete Methode muss existieren.
     * @post /
     * @param event Das Event, dem der Methodenaufruf entstammt
     * @Author Felix Ahrens
     */
    @FXML
    public void handleAufbau (MouseEvent event){
        //Hier dann der Code gemaess der Spiellogik zum rekrutieren von Einheiten
        updateProgressBars();
    }

    /**
     * Methode, die auf Knopfdruck den Abzug/ die Reduktion von Truppen veranlasst.
     * @pre Die verwendete Methode muss existieren.
     * @post /
     * @param event Das Event, dem der Methodenaufruf entstammt
     * @Author Felix Ahrens
     */
    @FXML
    public void handleAbzug (MouseEvent event){
        //Hier dann der Code gemaess der Spiellogik zum reduzieren von Einheiten
        updateProgressBars();
    }

    /**
     * Methode zum aktualisieren vom angezeigten Fortschritt der ProgressBars
     * @pre Die ProgressBars muessen als Parameter in der Klasse deklariert sein und ueber die ID mit ihrem Äquivalent in der FXML-Datei verbunden sein.
     * @post Die ProgressBars wurden (wenn die Methode fertig ist) auf den aktuellen Stand gebracht
     * @Author Felix Ahrens
     */
    public void updateProgressBars ()
    {
        //BarA.setProgress(/*Hier dann code zum Feststellen des Progress*/);
        //BarB.set...
    }


    /**
     * Methode zum Anzeigen von Details
     * @param event Das Ereignis, das durch eine Mausaktion ausgeloest wurde und zum Methodenaufruf gefuehrt hat.
     */
    @FXML
    public void zeigeDetail (MouseEvent event){
        HBox hBox = ((HBox)event.getSource());
        //((Label)(hBox.getChildren().get(Konstanten.INT_ZERO)))
        detailLabel.setText(switch (hBox.getId()){
            case (Strings.HBOX + Konstanten.INT_ZERO) -> Strings.BC_TEXT_EINHEIT_A;
            case (Strings.HBOX + Konstanten.INT_ONE) -> Strings.BC_TEXT_EINHEIT_B;
            case (Strings.HBOX + Konstanten.INT_TWO) -> Strings.BC_TEXT_EINHEIT_C;
            case (Strings.HBOX + Konstanten.INT_THREE) -> Strings.BC_TEXT_EINHEIT_D;
            case (Strings.HBOX + Konstanten.INT_FOUR) -> Strings.BC_TEXT_EINHEIT_E;
            default -> Strings.FEHLERMELDUNG_ID;
        });
        detailLabel.setVisible(true);
    }

    @FXML
    public void entferneDetail () {
        detailLabel.setText(Strings.SPACE);
    }

    /**
     * Methode, die die Hbox, die eine Einheit repraesentiert, zurueckgibt
     * @param index
     * @return
     * @Author Felix Ahrens
     */
    public HBox findeHbox (int index) {
        return ((HBox)einheitenVbox.getChildren().get(index));
    }

}
