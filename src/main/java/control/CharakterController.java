package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Charakter;
import res.Konstanten;
import res.Strings;

public class CharakterController {

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
    private AnchorPane charakterDisplay = new AnchorPane();

    private static Charakter[] charakterArray = new Charakter[5];

    public static Charakter[] getCharakterArray() {
        return charakterArray;
    }

    public static void erstelleDefaultCharakter () {
        Charakter leader = new Charakter(Strings.LEADER,
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_ONE],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_TWO],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_THREE],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_FOUR],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_FIVE],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_SIX],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_SEVEN],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_EIGHT],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_NINE]
        );

        Charakter medic = new Charakter(Strings.MEDIC,
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_ONE],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_TWO],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_THREE],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_FOUR],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_FIVE],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_SIX],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_SEVEN],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_EIGHT],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_NINE]
        );

        Charakter hunter = new Charakter(Strings.HUNTER,
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_ONE],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_TWO],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_THREE],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_FOUR],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_FIVE],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_SIX],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_SEVEN],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_EIGHT],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_NINE]
        );

        Charakter magician = new Charakter(Strings.MAGICIAN,
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_ONE],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_TWO],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_THREE],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_FOUR],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_FIVE],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_SIX],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_SEVEN],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_EIGHT],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_NINE]
        );

        Charakter scout = new Charakter(Strings.SCOUT,
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_ONE],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_TWO],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_THREE],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_FOUR],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_FIVE],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_SIX],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_SEVEN],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_EIGHT],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_NINE]
        );

        charakterArray[Konstanten.INT_ZERO] = leader;
        charakterArray[Konstanten.INT_ONE] = medic;
        charakterArray[Konstanten.INT_TWO] = hunter;
        charakterArray[Konstanten.INT_THREE] = magician;
        charakterArray[Konstanten.INT_FOUR] = scout;
    }

    @FXML
    public void initialize() {
        charakterDisplay.setVisible(false);
    }

    @FXML
    public void setzeDisplaySichtbar(boolean sichtbar){
        charakterDisplay.setVisible(sichtbar);
    }

    @FXML
    public void zeigeCharakterWerte(Charakter charakter) {
        nameLabel.setText(charakter.getName());
        healthLabel.setText(String.valueOf(charakter.getGesundheit()));
        shieldLabel.setText(String.valueOf(charakter.getSchild()));
        manaLabel.setText(String.valueOf(charakter.getManapunkte()));
        closeCombatLabel.setText(String.valueOf(charakter.getNahkampfWert()));
        distanceCombatLabel.setText(String.valueOf(charakter.getFernkampfWert()));
        numberDistComLabel.setText(String.valueOf(charakter.getFernkaempfeZahl()));
        dodgeLabel.setText(String.valueOf(charakter.getZahlAusweichen()));
        magResLabel.setText(String.valueOf(charakter.getMagieResistenz()));
        reachLabel.setText(String.valueOf(charakter.getBewegungsWeite()));
        initLabel.setText(String.valueOf(charakter.getInitiative()));
    }
}
