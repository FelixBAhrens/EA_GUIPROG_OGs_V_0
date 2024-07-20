package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Charakter;
import model.GameFile;
import res.Konstanten;
import res.Strings;

/**
 * Die Klasse SchenkenController bildet die Controllerklasse zur FXML-Datei "schenke-view.fxml" und beinhaltet die
 * noetigen Methoden und Variablen zur Behandlung von Nutzereingaben und Anpassung der Gui an diese.
 *
 * @Author Felix Ahrens
 */
public class SchenkenController extends StadtController
{
    private Charakter angezeigterCharakter;
    @FXML
    private AnchorPane charakterDisplay;
    @FXML
    public Button anheuernButton;

    @FXML
    private Label nameLabel;
    @FXML
    private Label healthLabel;
    @FXML
    private Label shieldLabel;
    @FXML
    private Label manaLabel;
    @FXML
    private Label closeCombatLabel;
    @FXML
    private Label distanceCombatLabel;
    @FXML
    private Label numberDistComLabel;
    @FXML
    private Label dodgeLabel;
    @FXML
    private Label magResLabel;
    @FXML
    private Label reachLabel;
    @FXML
    private Label initLabel;
    @FXML
    public Pane medic;
    @FXML
    public Pane magician;
    @FXML
    public Pane scout;
    @FXML
    public Pane hunter;

    /**
     * Initialize-Methode, die bei Controllerklassen von FXML-Dateien verpflichtend ist.
     *
     * @pre /
     * @post /
     * @Author Felix Ahrens
     */
    @FXML
    public void initialize ()
    {

    }

    /**
     * Methode zum Anzeigen der Pane "charakterDisplay". Ueber die Methode "gebeCharakterAusID" wird auf den
     * zugehoerigen Charakter zugegriffen. Das Aktualisieren der Charakterwerte im "charakterDisplay" wird ueber die
     * aufgerufene Methode "zeigeCharakterWerteImDisplay" beauftragt.
     *
     * @param event das Event, aus dem die Methode aufgerufen wurde.
     * @pre Die GUI-Elemente, Methoden und der Konstruktor muessen erreichbar sein.
     * @post Die Werte des Charakters werden der nutzenden Person angezeigt.
     * @Author Felix Ahrens
     */
    @FXML
    public void openCharakter (MouseEvent event)
    {
        Charakter charakter = gebeCharakterAusID((Pane) event.getSource());
        angezeigterCharakter = charakter;
        zeigeCharakterWerteImDisplay(charakter);
    }

    /**
     * Methode, die das Anheuern steuert. Aufgerufen wird diese ueber einen Klick auf den "anheuernButton"-Knopf. Das
     * Anheuern geschieht nur, wenn genug Gold im Besitz ist.
     *
     * @pre Die Methode "fuehreTransaktionDurchWennMoeglich" muss existieren und von der Klasse "SchenkenController" aus
     * zugaenglich sein. Die GUI-Elemente muessen in der FXML-Datei "schenke-view.fxml" vorhanden sein und in dieser
     * Controllerklasse ebenso. Auf die Interfaces fuer Konstanten und Strings muss zugegriffen werden koennen.
     * @post Der boolesche Wert "istAngeheuert" des ausgewaehlten Charakters wurde auf true gesetzt, wenn sich die
     * spielende Person den Charakter leisten konnte. Ueber die GUI  ist eine visuelle Bestaetigung getaetigt worden.
     * @Author Felix Ahrens
     */
    @FXML
    public void handleAnheuern ()
    {
        if (fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO,
                angezeigterCharakter.berechnePreisInGold(), Konstanten.INT_ZERO,
                Konstanten.INT_ZERO))
        {
            angezeigterCharakter.setAngeheuert(true);
            anheuernButton.setDisable(true);
            anheuernButton.setText(Strings.ANGEHEUERT);
        }
    }

    /**
     * Methode zum Aktualisieren des CharakterDisplays. Es werden die Methoden "zeigeCharakterAnheuernButtonSinnvoll"
     * und "setzeLabelTexte" aufgerufen, beiden wird die Instanz der Klasse Charakter, die der Methode schon beim Aufruf
     * uebergeben wurde, uebergeben. Beide Methoden steuern GUI-Elemente im "charakterDisplay". Zusaetzlich wird der
     * Charakter, der der Methode uebergeben wurde, als Klassenvariable des SchenkenControllers gesetzt.
     *
     * @param charakter Der Charakter, der angezeigt werden soll
     * @pre Die verwendeten GUI-Elemente, Konstanten und Methoden muessen erreichbar sein.
     * @post Die Charakterwerte werden im "charakterDisplay" angezeigt.
     * @Author Felix Ahrens
     */
    @FXML
    public void zeigeCharakterWerteImDisplay (Charakter charakter)
    {
        anheuernButton.setText(Strings.ANHEUERN + Strings.DOPPELPUNKT + Strings.SPACE + charakter.berechnePreisInGold());
        zeigeCharakterAnheuernButtonSinnvoll(charakter);
        setzeLabelTexte(charakter);
        charakterDisplay.setVisible(true);
    }

    /**
     * Methode, die, abhaengig davon, ob der die booleschen Werte "istImBesitz" und "transaktionIstMoeglich" des
     * uebergebenen Charakters true oder false ist, den Knopf mit der ID "anheuernButton" in der FXML-Datei
     * "schenke-view.fxml" aktiviert oder deaktiviert. Grund dafuer ist, dass man einen gekauften Charakter nicht
     * nochmal kaufen koennen soll und der Versuch des Kaufens unterbunden werden soll, wenn man sich den Charakter
     * nicht leisten kann.
     *
     * @param charakter Der Charakter, der in der Schenke gerade ausgewaehlt ist und der in der "charakterDisplay"-Pane
     *                  angezeigt wird.
     * @pre Die verwendeten Methoden, GUI-Elemente und Konstanten muessen erreichbar sein.
     * @post Wenn der Charakter angeheuert werden kann, wird der "anheuernButton" enabled.
     * @Author Felix Ahrens
     */
    public void zeigeCharakterAnheuernButtonSinnvoll (Charakter charakter)
    {
        if (charakter.istAngeheuert() || !transaktionIstMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO, charakter.berechnePreisInGold(), Konstanten.INT_ZERO, Konstanten.INT_ZERO))
        {
            anheuernButton.setDisable(true);
        } else
        {
            anheuernButton.setDisable(false);
        }
    }

    /**
     * Methode zum Fuellen der Labels der Fxml-datei mit huebsch verpackten Charakterausgaben. Die Methode setzt in die
     * jeweiligen Labels jeweils einen Bezeichner und den zugehoerigen Wert der Instanz der Klasse Charakter, die der
     * Methode uebergeben wurde.
     *
     * @param charakter Der Charakter, dessen Werte in die Labels gesetzt werden sollen.
     * @pre Die Klasse Charakter muss die Variablen besitzen. Die Methode muss einen gueltigen Charakter uebergeben
     * bekommen. Die Labels muessen mit den korrekten IDs in der Klasse SchenkenController und in der FXML-Datei
     * "schenke-view.fxml" existieren.
     * @post Die Labels in der "schenke-view.fxml" zeigen die Werte an, die den Werten des Charakters entsprechen, der
     * der Methode uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setzeLabelTexte (Charakter charakter)
    {
        nameLabel.setText(Strings.NAME + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getName());
        healthLabel.setText(Strings.GESUNDHEIT + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getGesundheit());
        shieldLabel.setText(Strings.SCHILD + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getSchild());
        manaLabel.setText(Strings.MANAPUNKTE + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getManapunkte());
        closeCombatLabel.setText(Strings.NAHKAMPFWERT + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getNahkampfWert());
        distanceCombatLabel.setText(Strings.FERNKAMPFWERT + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getFernkampfWert());
        numberDistComLabel.setText(Strings.FERNKAEMPFE_VERBLEIBEND + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getFernkaempfeVerbleibenZahl());
        dodgeLabel.setText(Strings.AUSWEICHEN_ZAHL + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getZahlAusweichen());
        magResLabel.setText(Strings.MAGIERESISTENZ + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getMagieResistenz());
        reachLabel.setText(Strings.BEWEGUNGSWEITE + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getBewegungsWeite());
        initLabel.setText(Strings.INITIATIVE + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getInitiative());
    }

    /**
     * Methode, die einer uebergebenen Pane einen Charakter zuordnet. Dabei wird die ID der Pane ueber eine
     * switch-Anweisung mit Konstanten verglichen und der zugehoerige Charakter aus der GameFile zurueckgegeben.
     *
     * @param pane Die Pane, der diese Methode basierend auf dessen ID einen Charakter zuordnet.
     * @return Den Charakter, der zur uebergebenen Pane gehoert.
     * @pre Die Singleton-Instanz muss gesetzt sein, die ID der Pane muss mit einem der vier Konstanten-Strings aus der
     * switch-Anweisungen uebereinstimmen.
     * @post Es wurde der zur ID der uebergebenen Pane gehoerender Charakter aus dem Spielstand (GameFile)
     * zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Charakter gebeCharakterAusID (Pane pane)
    {
        GameFile instanz = GameFile.getInstanz();
        return switch (pane.getId())
        {
            case Strings.MEDIC_LC -> instanz.getMedic();
            case Strings.MAGICIAN_LC -> instanz.getMagician();
            case Strings.SCOUT_LC -> instanz.getScout();
            case Strings.HUNTER_LC -> instanz.getHunter();
            default -> null;
        };
    }
}
