package control;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Charakter;
import model.GameFile;
import res.Konstanten;
import res.Strings;

import java.util.Stack;

public class EinheitenController extends ControllerController
{
    Stack<Charakter> charakterStack = new Stack<>();

    @FXML
    public HBox einheitenHBox;

    @FXML
    public HBox MissionsEinheitenHBox;

    @FXML
    public Label tutorialLabel;

    @FXML
    public VBox charakterDisplay;

    /**
     * Initialize-Methode des EinheitenControllers. Hier werden Methoden aufgerufen, die zum Initialisieren
     *  der Funktionalitaet der Klasse benoetigt werden. Dazu gehoert etwa die Methode "erstelleDefaultCharakterHashMap", die
     *  eine HashMap mit Verbindungen zwischen Instanzen der Klasse Charakter und Farb-Strings herstellt.
     * @pre Die Methoden und die Klasse muessen existieren.
     * @post Die fuer die weitere Nutzung der Klasse als Controllerklasse der zugehoerigen FXML-Datei benoetigten Methoden
     *  zum initialisieren wurden aufgerufen.
     * @Author Felix Ahrens
     */
    @FXML
    public void initialize ()
    {
        CharakterController.erstelleDefaultCharakterHashMap();
        aktualisiereEinheitenHBox();
    }

    /**
     * Methode, die von GUI-Elementen in der FXML-Datei aufgerufen wird. Diese ruft die benoetigten Methoden auf,
     *  um das Waehlen eines Charakters logisch umzusetzen. Der zugehoerige Charakter wird auf einem Stack abgelegt und
     *  die Displays aktualisiert.
     * @pre Die verwendeten Methoden und Klassen muessen existieren. Die ID, die aus dem MouseEvent gezogen wurde,
     *  muss den unter "macheCharakterAusId" spezifizierten Konventionen entsprechen.
     * @post Das Waehlen des Charakters wurde gespeichert und visuell bestaetigt.
     * @param mouseEvent Das Event, aus dem der Methodenaufruf stammt.
     * @Author Felix Ahrens
     */
    @FXML
    public void waehleCharakter (MouseEvent mouseEvent)
    {
        charakterStack.add(macheCharakterAusID(((AnchorPane)mouseEvent.getSource()).getId()));
        ((AnchorPane) mouseEvent.getSource()).setDisable(true);
        aktualisiereEinheitenHBox();
        aktualisiereMissionsEinheitenHBox();
    }

    /**
     * Methode, die aus einer ID, wie sie in der FXML-Datei zahlreich vorhanden ist, den zugehoerigen Charakter waehlt und zurueckgibt.
     *  Dabei wird nur der hinter dem Unterstrich stehende Teil der ID (des uebergebenen Strings) betrachtet. Je nach dessen Name
     *  wird der jeweilige Getter des zugehoerigen Charakters aus der Klasse GameFile aufgerufen
     * @pre Die ID darf nur einen Unterstrich enthalten. Hinter dem Unterstrich darf nur noch der Name des Zugehoerigen
     *  Charakters stehen, etwa "Leader". Dabei muss der erste Buchstabe gross geschrieben sein. Die ID darf hinter dem Bruchstrich nur
     *  einen der fuenf Charakternamen enthalten. Die verwendeten Interfaces, Klassen und Methoden muessen vorhanden und erreichbar sein.
     * @post Es wurde ein Charakter zurueckgegeben, der der ID entsprach.
     * @param id Die ID des GUI-Elements, mit dem diese Methode einen Charakter auswaehlen soll.
     * @return Die zur ID passende Instanz der Klasse Charakter
     * @Author Felix Ahrens
     */
    public Charakter macheCharakterAusID (String id){
        GameFile instanz = GameFile.getInstanz();
        return switch(id.split(Strings.UNTERSTRICH)[Konstanten.INT_ONE])
        {
            case Strings.LEADER -> instanz.getLeader();
            case Strings.MEDIC -> instanz.getMedic();
            case Strings.HUNTER -> instanz.getHunter();
            case Strings.MAGICIAN -> instanz.getMagician();
            case Strings.SCOUT -> instanz.getScout();
            default -> null;
        };
    }

    /**
     * Methode, die die EinheitenHBox aktualisiert
     * @pre Die verwendeten Methoden, Klassen und Interfaces muessen existieren und erreichbar sein. Die Instanz der Klasse Charakter muss die verwendeten Methoden und Parameter haben.
     * @post Die EinheitenHBox wurde visuell auf den neuesten Stand gebracht und zeigt (nun wieder) die fuer die nutzende Person wichtigen Informationen an und ermoeglicht die Interaktion mit dieser.
     * @Author Felix Ahrens
     */
    public void aktualisiereEinheitenHBox () {
        int counter = Konstanten.INT_ZERO;
        for (Charakter charakter : GameFile.getInstanz().gebeCharakterAlsArrayZurueck()){
            if (!charakter.istAngeheuert() && counter != Konstanten.INT_ZERO){
                einheitenHBox.getChildren().get(counter).setDisable(true);
                einheitenHBox.getChildren().get(counter).setVisible(false);
            }
            counter++;
        }
    }

    /**
     * Methode, die die "MissionsEinheitenHBox" aktualisiert. Dazu werden erst alle Children, also der gesamte Inhalt, invisible
     *  gesetzt. Danach wird der "charakterStack" durchgegangen. Fuer jeden Charakter in diesem wird die zugehoerige
     *  AnchorPane auf visible gesetzt.
     * @pre Die verwendeten Klassen, Methoden und Interfaces (Konstanten) muessen verfuegbar und erreichbar sein.
     * @post Die HBox "MissionsEinheitenHBox" wurde aktualisiert, dabei werden nur die ausgewaehlten, also im "charakterStack" liegenden Charakter-AnchorPanes angezeigt
     * @Author Felix Ahrens
     */
    public void aktualisiereMissionsEinheitenHBox ()
    {
        MissionsEinheitenHBox.getChildren().forEach(node -> node.setVisible(false));
        for (Charakter charakter : charakterStack) {
            MissionsEinheitenHBox.getChildren().get(switch (charakter.getName()){
                case Strings.LEADER -> Konstanten.INT_ZERO;
                case Strings.MEDIC -> Konstanten.INT_ONE;
                case Strings.HUNTER -> Konstanten.INT_TWO;
                case Strings.MAGICIAN -> Konstanten.INT_THREE;
                case Strings.SCOUT -> Konstanten.INT_FOUR;
                default -> Konstanten.INT_ZERO;
            }).setVisible(true);
        }
    }

    /**
     * Methode zum Fortfahren. Diese setzt das "EinheitenArray" in der Klasse GameFile auf den Inhalt des charakterStacks.
     * @pre Die Singleton-Instanz der Klasse GameFile muss gesetzt sein. Die Methoden und Klassen muessen vorhanden und erreichbar sein.
     * @post Der charakterStack wurde im "einheitenArray" in der Klasse GameFile gespeichert und die Szene "Kampf" wurde geladen.
     * @Author Felix Ahrens
     */
    @FXML
    public void fahreFort (){
        GameFile.getInstanz().setEinheitenArray(charakterStack.toArray(new Charakter[charakterStack.size()]));
        SzenenManager.wechseleSzene(Strings.FXML_KAMPF);
    }

    /**
     * Methode, die aus GUI-Elementen der zugehoerigen FXML-Datei aufgerufen wird und die dem Aufruferelement zugehoerige
     *  passende Information anzeigen laesst.
     * @pre Die verwendeten Methoden muessen verfuegbar und erreichbar sein sowie Design-By-Contract erfuellen.
     * @post Die Info der Pane, die zu einer Klasse gehoert, wurde auf dem "CharakterDisplay" angezeigt.
     * @param mouseEvent Das Event, das den Methodenaufruf ausgeloest hat
     * @Author Felix Ahrens
     */
    @FXML
    public void zeigeInfo (MouseEvent mouseEvent)
    {
        setzeLabelTexte(macheCharakterAusID(((AnchorPane)mouseEvent.getSource()).getId()));
    }

    /**
     * Methode zum Fuellen der Labels der Fxml-datei mit huebsch verpackten Charakterausgaben.
     *  Die Methode setzt in die jeweiligen Labels jeweils einen Bezeichner und den zugehoerigen Wert der Instanz der
     *  Klasse Charakter, die der Methode uebergeben wurde.
     * @pre Die Klasse Charakter muss die Variablen besitzen. Die Methode muss einen gueltigen Charakter
     *  uebergeben bekommen. Die Labels muessen mit den korrekten IDs in der Klasse SchenkenController und in der
     *  FXML-Datei "schenke-view.fxml" existieren.
     * @post Die Labels in der "schenke-view.fxml" zeigen die Werte an, die den Werten des Charakters entsprechen,
     *  der der Methode uebergeben wurde.
     * @param charakter Der Charakter, dessen Werte in die Labels gesetzt werden sollen.
     * @Author Felix Ahrens
     */
    public void setzeLabelTexte (Charakter charakter) {
        ObservableList<Node> childrenListe = charakterDisplay.getChildren();
        int index = Konstanten.INT_ZERO;
        for (Node node : childrenListe){
            ((Label)node).setText(generiereText(index, charakter));
            index++;
        }
    }

    /**
     * Methode, die
     * @param index
     * @param charakter
     * @return
     */
    public String generiereText(int index, Charakter charakter){
        return (switch (index) {
            case Konstanten.INT_ZERO -> Strings.NAME;
            case Konstanten.INT_ONE -> Strings.GESUNDHEIT;
            case Konstanten.INT_TWO -> Strings.SCHILD;
            case Konstanten.INT_THREE -> Strings.MANAPUNKTE;
            case Konstanten.INT_FOUR -> Strings.NAHKAMPFWERT;
            case Konstanten.INT_FIVE -> Strings.FERNKAMPFWERT;
            case Konstanten.INT_SIX -> Strings.FERNKAEMPFE_VERBLEIBEND;
            case Konstanten.INT_SEVEN -> Strings.AUSWEICHEN_ZAHL;
            case Konstanten.INT_EIGHT -> Strings.MAGIERESISTENZ;
            case Konstanten.INT_NINE -> Strings.BEWEGUNGSWEITE;
            case Konstanten.INT_TEN -> Strings.INITIATIVE;
            default -> Strings.SPACE;
        }
                + Strings.DOPPELPUNKT
                + Strings.SPACE
                + (switch (index) {
            case Konstanten.INT_ZERO -> charakter.getName();
            case Konstanten.INT_ONE -> charakter.getGesundheit();
            case Konstanten.INT_TWO -> charakter.getSchild();
            case Konstanten.INT_THREE -> charakter.getManapunkte();
            case Konstanten.INT_FOUR -> charakter.getNahkampfWert();
            case Konstanten.INT_FIVE -> charakter.getFernkampfWert();
            case Konstanten.INT_SIX -> charakter.getFernkaempfeVerbleibenZahl();
            case Konstanten.INT_SEVEN -> charakter.getZahlAusweichen();
            case Konstanten.INT_EIGHT -> charakter.getMagieResistenz();
            case Konstanten.INT_NINE -> charakter.getBewegungsWeite();
            case Konstanten.INT_TEN -> charakter.getInitiative();
            default -> Strings.SPACE;
        }));
    }
}
