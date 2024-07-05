package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.GameFile;
import res.Konstanten;
import res.Strings;

public class NeuesSpielController extends ControllerController
{
    private static String spielName;
    private static GameFile.Schwierigkeit schwierigkeit;
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


    @FXML
    private void initialize ()
    {
        schwierigkeitsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            schwierigkeitsSlider.setValue(value);
            schwierigkeitsLabel.setVisible(true);
            switch (value)
            {
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
     * Methode zum Fortfahren Abhaengig von der AnchorPane, die gerade visible ist, wird entweder die Schwierigkeit
     * gesetzt oder die GameFile mit der eingegebenen Schwierigkeit und dem eingegebenen Dateinamen erstellt
     *
     * @author David Kien, Felix Ahrens
     */
    @FXML
    public void handleFortfahren ()
    {
        if (schwierigkeitsAnchPane.isVisible())
        {
            if (schwierigkeitsLabel.getText().equals(Strings.STRING_EINFACH) || schwierigkeitsLabel.getText().equals(Strings.STRING_NORMAL) || schwierigkeitsLabel.getText().equals(Strings.STRING_SCHWER))
            {
                System.out.println(schwierigkeitsLabel.getText());
                schwierigkeit = GameFile.stringZuSchwierigkeitsEnum(schwierigkeitsLabel.getText());
                schwierigkeitsAnchPane.setVisible(false);
                spielNameAnchPane.setVisible(true);
            } else
            {
                schwierigkeitsLabel.setText(Strings.EINGABEAUFFORDERUNG_SCHWIERIGKEIT);
                schwierigkeitsLabel.setVisible(true);
            }
        } else if (!schwierigkeitsAnchPane.isVisible() && spielNameAnchPane.isVisible())
        {
            GameFile.setzeInstanz(GameFile.erstelleNeueDefaultGameFile(spielNameText.getText(), schwierigkeit));
            SzenenManager.wechseleSzene(Strings.FXML_TUTORIAL);
        }
    }

    /**
     * Override der "handleZurueck"-methode aus "ControllerController". Dadurch wird zusaetzliche Funktionalitaet
     * implementiert, da hier ueber Zurueck-Buttons zwischen AnchorPanes, die verschiedene Nutzereingaben fordern,
     * innerhalb der Szene gewechselt werden kann.
     *
     * @precondition
     * @postcontidion
     * @author Felix Ahrens
     */
    @Override
    public void handleZurueck ()
    {
        if (schwierigkeitsAnchPane.isVisible())
        {
            handleZurueck();
        } else if (spielNameAnchPane.isVisible())
        {
            spielNameAnchPane.setVisible(false);
            schwierigkeitsAnchPane.setVisible(true);
        }
    }

}
