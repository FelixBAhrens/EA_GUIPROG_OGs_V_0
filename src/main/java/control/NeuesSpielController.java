package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import model.GameFile;
import res.Konstanten;
import res.Strings;

public class NeuesSpielController extends ControllerController{
    private String schwierigkeit;
    public String getSchwierigkeit(){
        return schwierigkeit;
    }
    @FXML
    public Slider schwierigkeitsSlider;
    @FXML
    public Label schwierigkeitsLabel;

    @FXML
    private void initialize() {
        schwierigkeitsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            schwierigkeitsSlider.setValue(value);
            schwierigkeitsLabel.setVisible(true);
            switch (value) {
                case Konstanten.INT_ONE:
                    schwierigkeitsLabel.setText(Strings.STRING_EINFACH);
                    break;
                case Konstanten.INT_TWO:
                    schwierigkeitsLabel.setText(Strings.STRING_NORMAL);
                    break;
                case Konstanten.INT_THREE:
                    schwierigkeitsLabel.setText(Strings.STRING_SCHWER);
                    break;
            }
        });
    }

    /**
     * Methode zum Fortfahren
     * Die Methode holt sich den Text des sliders, der der ausgewählten Schwierigkeit entspricht und
     *  erstellt damit eine neue GameFile die auch als Singleton gesetzt wird. Dann wird das Tutorial
     *  geladen.
     * @author David Kien, Felix Ahrens
     */
    @FXML
    public void handleFortfahren () {
        if (schwierigkeitsLabel.getText().equals(Strings.STRING_EINFACH) || schwierigkeitsLabel.getText().equals(Strings.STRING_NORMAL) || schwierigkeitsLabel.getText().equals(Strings.STRING_SCHWER)) {
            System.out.println(schwierigkeitsLabel.getText());
            GameFile.setzeGameFile(GameFile.erstelleNeueGameFile(schwierigkeitsLabel.getText()));
            SceneManager.changeScene(Strings.FXML_TUTORIAL);
        }
        else {
            schwierigkeitsLabel.setText(Strings.EINGABEAUFFORDERUNG_SCHWIERIGKEIT);
            schwierigkeitsLabel.setVisible(true);
        }
    }
}
