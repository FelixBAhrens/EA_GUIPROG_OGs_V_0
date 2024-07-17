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
     * Methode, die die EinheitenHBox aktualisiert
     * @pre
     * @post
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
     * Initialize-Methode des EinheitenControllers.
     * @pre
     * @post
     * @Author Felix Ahrens
     */
    @FXML
    public void initialize ()
    {
        CharakterController.erstelleDefaultCharakterHashMap();
        aktualisiereEinheitenHBox();
    }


    /**
     * Methode, die durch einen Button-click aufgerufen wird und den gesetzten Kampf mit den ausgewaehlten Einheiten startet.
     * @pre
     * @post
     * @Author Felix Ahrens
     */
    @FXML
    public void starteMission(){
        ObservableList<Node> alleCharaktere = MissionsEinheitenHBox.getChildren();
    }

    @FXML
    public void waehleCharakter (MouseEvent mouseEvent)
    {
        charakterStack.add(macheCharakterAusID(((AnchorPane)mouseEvent.getSource()).getId()));
        ((AnchorPane) mouseEvent.getSource()).setDisable(true);
        aktualisiereEinheitenHBox();
        aktualisiereMissionsEinheitenHBox();
    }

    public Charakter macheCharakterAusID(String id){
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

    public void aktualisiereMissionsEinheitenHBox ()
    {
        MissionsEinheitenHBox.getChildren().forEach(node -> node.setVisible(false));
        System.out.println(charakterStack.size());
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

    @FXML
    public void fahreFort (){
        GameFile.getInstanz().setEinheitenArray(charakterStack.toArray(new Charakter[charakterStack.size()]));
        SzenenManager.wechseleSzene(Strings.FXML_KAMPF);
    }

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
