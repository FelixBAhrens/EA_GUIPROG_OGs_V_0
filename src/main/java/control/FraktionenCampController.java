package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.GameFile;
import res.Konstanten;
import res.Strings;

/**
 * Controllerklasse der "fraktionen-camp-view.fxml". In ihr befinden sich die Methoden zum Steuern der GUI des Fraktionencamps,
 *  zum Behandeln von Nutzereingaben und zum Verwalten des Fraktionencamps
 * @author Felix Ahrens, David Kien
 */
public class FraktionenCampController extends StadtController
{
    @FXML
    public AnchorPane baustelle;

    @FXML
    public AnchorPane AnchPaneFraktionA;

    @FXML
    public AnchorPane AnchPaneFraktionB;

    @FXML
    public AnchorPane AnchPaneFraktionC;

    @FXML
    public VBox VBoxFraktionA;

    @FXML
    public VBox VBoxFraktionB;

    @FXML
    public VBox VBoxFraktionC;

    @FXML
    public Label detailLabel;

    /**
     * Initialize-Methode der Klasse.
     *  Diese ist bei Controllerklassen von FXML-Dateien verpflichtend.
     * @pre Die verwendete Methode muss erreichbar sein.
     * @post Die Baustelle ist sichtbar, wenn diese im Spielstand noch nicht anders gespeichert wurde.
     * @Author David Kien, Felix Ahrens
     */
    @FXML
    public void initialize ()
    {
        handleBaustelleSichtbarkeit();
    }

    /**
     * Methode, die die Baustelle, abhaengig davon, ob diese in der GameFile als "freigeschaltet" gespeichert ist,
     *  visible oder nicht visible setzt.
     * @pre Die verwendeten Methoden und Klassen muessen erreichbar sein. Die Singleton-Instanz der GameFile muss gesetzt sein.
     * @post Die Baustelle ist sichtbar, wenn diese als "freigeschaltet" gespeichert wurde
     * @Author Felix Ahrens
     */
    public void handleBaustelleSichtbarkeit (){
        if (GameFile.getInstanz().fraktionenCampIstFreigeschaltet())
        {
            baustelle.setVisible(false);
        }
        else
        {
            baustelle.setVisible(true);
        }
    }

    /**
     * Methode, die vom jeweiligen Button aufgerufen wird und die jeweilige Anchorpane visible setzt.
     * @pre Die Anchorpanes muessen in der Klasse als Parameter gespeichert sein sowie eine Verbindung zu den GUI-Elementen
     *  in der FXML-Datei haben
     * @post Die zu dem Button passende FraktionenAnchorPane wurde auf "visible=true" gesetzt.
     * @param event Das Event, aus dem der Methodenaufruf stammt
     * @Author Felix Ahrens
     */
    @FXML
    public void zeigeFraktion (ActionEvent event) {
        AnchPaneFraktionA.setVisible(false);
        AnchPaneFraktionB.setVisible(false);
        AnchPaneFraktionC.setVisible(false);
        //System.out.println(((Button)event.getSource()).getId().split(Strings.UNTERSTRICH)[Konstanten.INT_ONE]);
        switch (((Button)event.getSource()).getId().split(Strings.UNTERSTRICH)[Konstanten.INT_ONE]){
            case Strings.FRAKTION_A -> {
                AnchPaneFraktionA.setVisible(true);
                setzeDetailLabelText(Strings.A);}
            case Strings.FRAKTION_B -> {
                AnchPaneFraktionB.setVisible(true);
                setzeDetailLabelText(Strings.B);}
            case Strings.FRAKTION_C -> {
                AnchPaneFraktionC.setVisible(true);
                setzeDetailLabelText(Strings.C);}
        }
    }

    /**
     * Methode, die den DetaillabelText entsprechend des Fraktionentyps (String) setzt.
     *  Die Texte bilden nur eine Schnittstelle zu anderen Verantwortungsbereichen und enthalten zurzeit keinen sinnvollen Inhalt.
     * @pre Die Konstanten und GUI-Elemente muessen vorhanden sein. Der Fraktion soll ein String der entweder "A", "B" oder "C" enthaelt, uebergeben werden, sonst kommt es zu keiner vernuenftigen Ausgabe.
     * @post Der Text im "detailLabel" wurde auf einen dem Label entsprechenden Text gesetzt. Das "detailLabel" wurde auf "sichtbar" gesetzt.
     * @param fraktionenTyp Als String der Typ {A,B,C} der jeweiligen Fraktion
     * @Author Felix Ahrens
     */
    public void setzeDetailLabelText (String fraktionenTyp){
        detailLabel.setText(switch (fraktionenTyp){
            case Strings.A -> Strings.TEXT_FRAKTION_A;
            case Strings.B -> Strings.TEXT_FRAKTION_B;
            case Strings.C -> Strings.TEXT_FRAKTION_C;
            default -> Strings.SPACE;
        });
        detailLabel.setVisible(true);
    }

    /**
     * Methode, die das Erwerben einer Einheit durchfuehren soll.
     *  Wird von der GUI aufgerufen, um Einheiten dazuzukaufen.
     * @pre /
     * @post Die Methode bildet nur die Schnittstelle zur GUI
     * @Author Felix Ahrens
     */
    @FXML
    public void handleErwerb (){

    }

    /**
     * Methode, die das Verkaufen einer Einheit durchfuehren soll.
     *  Wird von der GUI aufgerufen, um Einheiten zu verkaufen.
     * @pre /
     * @post Die Methode bildet nur die Schnittstelle zur GUI
     * @Author Felix Ahrens
     */
    @FXML
    public void handleVerkauf (){

    }

    /**
     * Methode, die die Baustelle freischaltet, wenn die Transaktion gelungen ist.
     *  Dazu wird die Methode "fuehreTransaktionDurchWennMoeglich" der Oberklasse ControllerController verwendet
     * @pre Die Methoden und Konstanten muessen verfuegbar und erreichbar sein.
     * @post Die Baustelle wurde Invisible gesetzt, wenn der Spielstand genug Ressourcen hergab.
     * @Author Felix Ahrens
     */
    @FXML
    public void schalteBaustelleFrei ()
    {
        if (fuehreTransaktionDurchWennMoeglich(Konstanten.INT_FIFTY, Konstanten.INT_ZERO, Konstanten.INT_FIVE, Konstanten.INT_ZERO, Konstanten.INT_ZERO)){
            baustelle.setVisible(false);
        }
    }
}
