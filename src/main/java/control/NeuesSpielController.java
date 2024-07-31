package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Charakter;
import model.GameFile;
import res.Konstanten;
import res.Strings;

import static res.Konstanten.*;

public class NeuesSpielController extends ControllerController{
    private static String spielName;
    private static String schwierigkeit;
    @FXML
    public Slider schwierigkeitsSlider;
    @FXML
    public Label schwierigkeitsLabel;
    @FXML
    public AnchorPane spielNameAnchPane;
    @FXML
    public AnchorPane schwierigkeitsAnchPane;
    @FXML
    public TextField spielNameText;

    public static int[] NEW_VALUES_LEADER = {0,0,0,0,0,0,0,0,0,0};
    public static int[] NEW_VALUES_MEDIC = {0,0,0,0,0,0,0,0,0,0};
    public static int[] NEW_VALUES_HUNTER = {0,0,0,0,0,0,0,0,0,0};
    public static int[] NEW_VALUES_MAGICIAN = {0,0,0,0,0,0,0,0,0,0};
    public static int[] NEW_VALUES_SCOUT = {0,0,0,0,0,0,0,0,0,0};


    @FXML
    private void initialize() {
        schwierigkeitsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            schwierigkeitsSlider.setValue(value);
            schwierigkeitsLabel.setVisible(true);
            switch (value) {
                case INT_ONE:
                    schwierigkeitsLabel.setText(Strings.STRING_EINFACH);
                    erstelleCharacter();
                    break;
                case INT_TWO:
                    schwierigkeitsLabel.setText(Strings.STRING_NORMAL);
                    erstelleCharacter();
                    break;
                case Konstanten.INT_THREE:
                    schwierigkeitsLabel.setText(Strings.STRING_SCHWER);
                    erstelleCharacter();
                    break;
            }
        });
    }
    public void erstelleCharacter() {
        if (schwierigkeitsSlider.getValue() == INT_ONE) {
            for (int i = 0; i < DEFAULT_VALUES_LEADER.length; i++) {
                NEW_VALUES_LEADER[i] = DEFAULT_VALUES_LEADER[i] + INT_TWO;
                NEW_VALUES_MEDIC[i] = DEFAULT_VALUES_MEDIC[i] + INT_TWO;
                NEW_VALUES_HUNTER[i] = DEFAULT_VALUES_HUNTER[i] + INT_TWO;
                NEW_VALUES_MAGICIAN[i] = DEFAULT_VALUES_MAGICIAN[i] + INT_TWO;
                NEW_VALUES_SCOUT[i] = DEFAULT_VALUES_SCOUT[i] + INT_TWO;
            }
        }
        else if (schwierigkeitsSlider.getValue() == INT_TWO) {
            for (int i = 0; i < DEFAULT_VALUES_LEADER.length; i++) {
                NEW_VALUES_LEADER[i] = DEFAULT_VALUES_LEADER[i] + INT_ONE;
                NEW_VALUES_MEDIC[i] = DEFAULT_VALUES_MEDIC[i] + INT_ONE;
                NEW_VALUES_HUNTER[i] = DEFAULT_VALUES_HUNTER[i] + INT_ONE;
                NEW_VALUES_MAGICIAN[i] = DEFAULT_VALUES_MAGICIAN[i] + INT_ONE;
                NEW_VALUES_SCOUT[i] = DEFAULT_VALUES_SCOUT[i] + INT_ONE;
            }
        }
        else {
            NEW_VALUES_LEADER = DEFAULT_VALUES_LEADER;
            NEW_VALUES_MEDIC = DEFAULT_VALUES_MEDIC;
            NEW_VALUES_HUNTER = DEFAULT_VALUES_HUNTER;
            NEW_VALUES_MAGICIAN = DEFAULT_VALUES_MAGICIAN;
            NEW_VALUES_SCOUT = DEFAULT_VALUES_SCOUT;

        }
    }

    /**
     * Methode zum Fortfahren
     * Abhaengig von der AnchorPane, die gerade visible ist, wird entweder die Schwierigkeit gesetzt oder die GameFile mit
     * der eingegebenen Schwierigkeit und dem eingegebenen Dateinamen erstellt
     * @author David Kien, Felix Ahrens
     */
    @FXML
    public void handleFortfahren () {
        if (schwierigkeitsAnchPane.isVisible()) {
            if (schwierigkeitsLabel.getText().equals(Strings.STRING_EINFACH) || schwierigkeitsLabel.getText().equals(Strings.STRING_NORMAL) || schwierigkeitsLabel.getText().equals(Strings.STRING_SCHWER)) {
                System.out.println(schwierigkeitsLabel.getText());
                schwierigkeit = schwierigkeitsLabel.getText();
                schwierigkeitsAnchPane.setVisible(false);
                spielNameAnchPane.setVisible(true);
            }
            else {
                schwierigkeitsLabel.setText(Strings.EINGABEAUFFORDERUNG_SCHWIERIGKEIT);
                schwierigkeitsLabel.setVisible(true);
            }
        }
        else if (!schwierigkeitsAnchPane.isVisible() && spielNameAnchPane.isVisible()) {
            GameFile.setzeInstanz(GameFile.erstelleNeueGameFile(spielNameText.getText(), schwierigkeit));
            SzenenManager.wechseleSzene(Strings.FXML_TUTORIAL);
        }
    }

    /**
     * Override der "handleZurueck"-methode aus "ControllerController". Dadurch wird zusaetzliche Funktionalitaet implementiert, da hier ueber
     * Zurueck-Buttons zwischen AnchorPanes, die verschiedene Nutzereingaben fordern, innerhalb der Szene gewechselt werden kann.
     * @precondition
     * @postcontidion
     * @author Felix Ahrens
     */
    @Override
    public void handleZurueck () {
        if (schwierigkeitsAnchPane.isVisible()){
            handleZurueck();
        } else if (spielNameAnchPane.isVisible()) {
            spielNameAnchPane.setVisible(false);
            schwierigkeitsAnchPane.setVisible(true);
        }
    }

}
