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

public class SchenkenController extends PaneController
{
    private Charakter angezeigterCharakter;
    @FXML
    private AnchorPane charakterDisplay;
    @FXML
    public Button anheuenButton;

    @FXML
    private Label nameLabel = new Label();
    @FXML
    private Label healthLabel = new Label();
    @FXML
    private Label shieldLabel = new Label();
    @FXML
    private Label manaLabel = new Label();
    @FXML
    private Label closeCombatLabel = new Label();
    @FXML
    private Label distanceCombatLabel = new Label();
    @FXML
    private Label numberDistComLabel = new Label();
    @FXML
    private Label dodgeLabel = new Label();
    @FXML
    private Label magResLabel = new Label();
    @FXML
    private Label reachLabel = new Label();
    @FXML
    private Label initLabel = new Label();
    @FXML
    public Pane medic;
    @FXML
    public Pane magician;
    @FXML
    public Pane scout;
    @FXML
    public Pane hunter;

    @FXML
    public void initialize ()
    {

    }

    /**
     * Methode zum Anzeigen der Pane "charakterDisplay"
     * @param event
     */
    @FXML
    public void openCharakter (MouseEvent event){
        Charakter charakter = gebeCharakterAusID((Pane)event.getSource());
        zeigeCharakterWerteImDisplay(charakter);
        charakterDisplay.setVisible(true);
        anheuenButton.setText(Strings.ANHEUERN + Strings.DOPPELPUNKT + Strings.SPACE + charakter.berechnePreisInGold());
    }

    @FXML
    public void handleAnheuern (MouseEvent event){
        if (fuehreTransaktionDurchWennMoeglich(Konstanten.INT_ZERO, Konstanten.INT_ZERO,
                angezeigterCharakter.berechnePreisInGold(), Konstanten.INT_ZERO,
                Konstanten.INT_ZERO)) {
            anheuenButton.setDisable(true);
            anheuenButton.setText(Strings.ANGEHEUERT);
        }
    }

    @FXML
    public void zeigeCharakterWerteImDisplay (Charakter charakter)
    {
        nameLabel.setText(Strings.NAME + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getName());
        healthLabel.setText(Strings.GESUNDHEIT + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getGesundheit());
        shieldLabel.setText(Strings.SCHILD + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getSchild());
        manaLabel.setText(Strings.MANAPUNKTE + Strings.DOPPELPUNKT + Strings.SPACE +  charakter.getManapunkte());
        closeCombatLabel.setText(Strings.NAHKAMPFWERT + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getNahkampfWert());
        distanceCombatLabel.setText(Strings.FERNKAMPFWERT + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getFernkampfWert());
        numberDistComLabel.setText(Strings.FERNKAEMPFE_VERBLEIBEND + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getFernkaempfeVerbleibenZahl());
        dodgeLabel.setText(Strings.AUSWEICHEN_ZAHL + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getZahlAusweichen());
        magResLabel.setText(Strings.MAGIERESISTENZ + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getMagieResistenz());
        reachLabel.setText(Strings.BEWEGUNGSWEITE + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getBewegungsWeite());
        initLabel.setText(Strings.INITIATIVE + Strings.DOPPELPUNKT + Strings.SPACE + charakter.getInitiative());
        angezeigterCharakter = charakter;
    }

    /**
     * Methode, die einer uebergebenen ID einen Charakter zuordnet
     * @param pane
     * @return
     * @
     */
    public Charakter gebeCharakterAusID (Pane pane){
        GameFile instanz = GameFile.getInstanz();
        return switch (pane.getId()){
            case Strings.MEDIC_LC -> instanz.getMedic();
            case Strings.MAGICIAN_LC -> instanz.getMagician();
            case Strings.SCOUT_LC -> instanz.getScout();
            case Strings.HUNTER_LC -> instanz.getHunter();
            default -> null;
        };
    }

    /**
     * Ueberschriebene methode
     * @param button
     * @return
     * @Author Felix Ahrens
     */
    public Charakter gebeCharakterausID (Button button) {
        GameFile instanz = GameFile.getInstanz();
        return switch (button.getId()){
            case Strings.MEDIC_LC -> instanz.getMedic();
            case Strings.MAGICIAN_LC -> instanz.getMagician();
            case Strings.SCOUT_LC -> instanz.getScout();
            case Strings.HUNTER_LC -> instanz.getHunter();
            default -> null;
        };
    }

}
