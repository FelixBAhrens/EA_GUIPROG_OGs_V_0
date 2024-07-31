package control;

// COMPLETED

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import res.Konstanten;
import res.Strings;

/**
 * Klasse HauptquartierController zum Steuern der Funktionalitaet des Hauptquartiers.
 * Diese Klasse ist die ControllerKlasse der "hauptquartier -view.fxml" und beinhaltet
 * die Methoden zum Steuern und Setzen der Nutzereingaben und Informationsausgaben.
 *
 * @author David Kien, Felix Ahrens
 */
public class HauptquartierController extends PaneController
{
    @FXML
    public Label entschluesselungDetail;
    @FXML
    public Label sammelnDetail;
    @FXML
    public Label memoryDetail;
    @FXML
    public Label endgegnerDetail;

    /**
     * Enum fuer die Mission, das die ausgewaehlte Mission speichert und ueberall in der
     * Klasse erreichbar ist.
     *
     * @author Felix Ahrens.
     */
    public enum Mission
    {
        ENTSCHLUESSELUNG(Strings.ENTSCHLUESSELUNG),
        ENDGEGNER(Strings.ENDGEGNER),
        MEMORY(Strings.MEMORY),
        SAMMELN(Strings.SAMMELN);

        /**
         * Konstruktor des Enums "Mission".
         *
         * @param missionsName Der Name der Mission, der als Parameter aufrufbar ist.
         *
         * @author Felix Ahrens.
         */
        Mission (String missionsName)
        {
        }
    }

    private Mission missionsName;

    @FXML
    private Label detailLabel;
    @FXML
    public Button missionStarten;
    @FXML
    public AnchorPane flappyBirdPane;
    @FXML
    public AnchorPane sammelPane;
    @FXML
    public AnchorPane memoryPane;
    @FXML
    public AnchorPane endgegnerPane;
    @FXML
    public AnchorPane missionDetailPane;


    /**
     * Initialize-Methode der Klasse. Diese ist fuer Controllerklassen verpflichtend.
     *
     * @pre Die aufgerufene Methode muss erreichbar sein.
     *
     * @post Die angezeigten Belohnungen wurde aktualisiert.
     *
     * @author David Kien, Felix Ahrens.
     */
    @FXML
    public void initialize ()
    {
        aktualisiereangezeigteBelohnungen();
    }


    /**
     * Methode, um das enum fuer die ausgewaehlte Mission passend zur uebergebenen ID zu setzen.
     *
     * @param ID Die ID des jeweiligen Buttons, zu dem ein Enum gesetzt werden soll.
     *
     * @pre Der uebergebene String muss mit einem der vier cases uebereinstimmen.
     *
     * @post Das enum wurde auf einen zu uebergebenen ID passenden Wert gesetzt.
     *
     * @Author Felix Ahrens.
     */
    public void setzeEnumNachID (String ID)
    {
        switch (ID)
        {
            case Strings.ID_BUTTON_ENTSCHLUESSELUNG -> missionsName = Mission.ENTSCHLUESSELUNG;
            case Strings.ID_BUTTON_ENDGEGNER -> missionsName = Mission.ENDGEGNER;
            case Strings.ID_BUTTON_MEMORY -> missionsName = Mission.MEMORY;
            case Strings.ID_BUTTON_SAMMELN -> missionsName = Mission.SAMMELN;
        }
    }

    /**
     * Methode zum Aktivieren der Mission.
     *
     * @param event Das Ereignis, das durch eine Mausaktion ausgeloest wurde und zum
     * Methodenaufruf gefuehrt hat.
     *
     * @pre Die verwendeten Methoden muessen existieren. Die Methode muss von einem der
     * vier Buttons zum Waehlen der Missionen aufgerufen werden.
     *
     * @post Das Enum wurde gesetzt und die Wahl der Mission visuell und durch das Anzeigen
     * von Informationen der nutzenden Person bestaetigt.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleMissionWaehlen (MouseEvent event)
    {
        setzeEnumNachID(((Button)event.getSource()).getId());
        aktualisiereMissionDetailPane();
        missionDetailPane.setVisible(true);
    }

    /**
     * Methode zum Aktualisieren der DetailPane.
     *
     * @pre Das Enum muss gesetzt sein. Die verwendeten Konstanten muessen erreichbar sein.
     *
     * @post Das "detailLabel" zeigt der nutzenden Person einen kontextuellen Text zur gewaehlten Mission an.
     *
     * @author Felix Ahrens.
     */
    public void aktualisiereMissionDetailPane ()
    {
        detailLabel.setText(switch (missionsName)
        {
            case ENTSCHLUESSELUNG -> Strings.TEXT_ENTSCHLUESSELUNG;
            case ENDGEGNER -> Strings.TEXT_ENDGEGNER;
            case MEMORY -> Strings.TEXT_MEMORY;
            case SAMMELN -> Strings.TEXT_SAMMELN;
        });
        detailLabel.setVisible(true);
    }

    /**
     * Methode zum Aktualisieren der angezeigten Belohnungen.
     *
     * @pre Die Konstanten, GUI-Elemente und Methoden muessen existieren und erreichbar sein.
     *
     * @post Die der nutzenden Person angezeigten Belohnungen wurden gemaess der in Konstanten
     * gespeicherten Belohnungswerte aktualisiert und in einem Text verpackt.
     *
     * @author Felix Ahrens.
     */
    public void aktualisiereangezeigteBelohnungen ()
    {
        entschluesselungDetail.setText(erstelleBelohnungsText(Konstanten.BELOHNUNGEN_ENTSCHLUESSELUNG));
        sammelnDetail.setText(erstelleBelohnungsText(Konstanten.BELOHNUNGEN_SAMMELN));
        memoryDetail.setText(erstelleBelohnungsText(Konstanten.BELOHNUNGEN_MEMORY));
        endgegnerDetail.setText(erstelleBelohnungsText(Konstanten.BELOHNUNGEN_ENDGEGNER));
    }

    /**
     * Methode zum Erstellen eines Belohnungstextes.
     *
     * @param belohnungsArray Die der Methode uebergebenen Integer-Belohnungswerte:
     * {Holz, Stein, Gold, Gesundheit, Banonas}.
     *
     * @return Als String einen Text mit huebsch verpackten Informationen zu den Belohnungen.
     *
     * @pre Die verwendeten Konstanten muessen existieren. Das eindimensionale Integer-Array
     * muss auf den ersten fuenf Plaetzen die Belohnungen in folgender Reihenfolge enthalten:
     * {Holz, Stein, Gold, Gesundheit, Banonas}.

     * @post Es wurde ein String zurueckgegeben, der fuer die nutzende Person wichtige
     * Informationen zu den der Methode uebergebenen Belohnungen enthaelt.
     *
     * @author Felix Ahrens.
     */
    public String erstelleBelohnungsText (int[] belohnungsArray)
    {
        return (Strings.BELOHNUNGEN + Strings.DOPPELPUNKT + Strings.NEWLINE
            + Strings.HOLZ + Strings.DOPPELPUNKT + Strings.SPACE + belohnungsArray[Konstanten.INT_ZERO] + Strings.NEWLINE
            + Strings.STEIN + Strings.DOPPELPUNKT + Strings.SPACE + belohnungsArray[Konstanten.INT_ONE] + Strings.NEWLINE
            + Strings.GOLD + Strings.DOPPELPUNKT + Strings.SPACE + belohnungsArray[Konstanten.INT_TWO] + Strings.NEWLINE
            + Strings.GESUNDHEIT + Strings.DOPPELPUNKT + Strings.SPACE + belohnungsArray[Konstanten.INT_THREE] + Strings.NEWLINE
            + Strings.BANONAS + Strings.DOPPELPUNKT + Strings.SPACE + belohnungsArray[Konstanten.INT_FOUR] + Strings.NEWLINE);
    }

    /**
     * Methode, die eine Mission startet. Teilweise werden die Enums der Karte oder des Kampfes
     * gesetzt, um den Controllerklassen fuer Karte und Kampf die Information fuer den ausgewaehlten
     * Kampf zu uebergeben. Fuer Karte und Kampf wird allerdings zuerst das Einheiten-Menue geladen.
     *
     * @pre Die Konstanten, das Enum, die Klassen und die Methoden muessen erreichbar sein
     * und "design-by-contract" erfuellen.
     *
     * @post Die naechste Szene wurde gesetzt, abhaengig vom vorher gesetzten Enum.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void starteMission ()
    {
        SzenenManager.wechseleSzene(switch (missionsName)
        {
            case ENTSCHLUESSELUNG -> Strings.FXML_MISSION_FLAPPYBIRD;
            case ENDGEGNER ->
            {
                KampfController.kampfTyp = KampfController.KampfTyp.ENDGEGNER_KAMPF;
                yield Strings.FXML_KAMPF;
            }
            case MEMORY -> Strings.FXML_MISSION_MEMORY;
            case SAMMELN -> {
                KartenController.kartenTyp = KartenController.KartenTyp.SAMMELN_MISSION;
                yield Strings.FXML_KARTENEW;
            }
        });
    }
}
