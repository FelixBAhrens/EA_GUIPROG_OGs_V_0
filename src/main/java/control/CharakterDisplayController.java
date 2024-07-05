package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Charakter;

public class CharakterDisplayController
{
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
    private AnchorPane charakterDisplay;

    @FXML
    public void initialize ()
    {

    }

    @FXML
    public void setzeDisplaySichtbar (boolean sichtbar)
    {
        charakterDisplay.setVisible(sichtbar);
    }

    @FXML
    public void zeigeCharakterWerte (Charakter charakter)
    {
        nameLabel.setText(charakter.getName());
        healthLabel.setText(String.valueOf(charakter.getGesundheit()));
        shieldLabel.setText(String.valueOf(charakter.getSchild()));
        manaLabel.setText(String.valueOf(charakter.getManapunkte()));
        closeCombatLabel.setText(String.valueOf(charakter.getNahkampfWert()));
        distanceCombatLabel.setText(String.valueOf(charakter.getFernkampfWert()));
        numberDistComLabel.setText(String.valueOf(charakter.getFernkaempfeVerbleibenZahl()));
        dodgeLabel.setText(String.valueOf(charakter.getZahlAusweichen()));
        magResLabel.setText(String.valueOf(charakter.getMagieResistenz()));
        reachLabel.setText(String.valueOf(charakter.getBewegungsWeite()));
        initLabel.setText(String.valueOf(charakter.getInitiative()));
    }
}
