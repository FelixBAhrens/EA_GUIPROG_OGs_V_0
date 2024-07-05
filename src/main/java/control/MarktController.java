package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.GameFile;
import res.Konstanten;
import res.Strings;

import java.util.Random;

public class MarktController extends PaneController
{
    @FXML
    public ProgressBar holzBar;
    @FXML
    public ProgressBar goldBar;
    @FXML
    public ProgressBar gesundheitsBar;
    @FXML
    public Label holzInventarLabel;
    @FXML
    public Label goldInventarLabel;
    @FXML
    public Label gesundheitsInventarLabel;
    @FXML
    public Label goldPreisLabel;
    @FXML
    public Label gesundheitsPreisLabel;
    @FXML
    public Text fehlerMeldungsText;

    private int goldPreis;
    private int gesundheitsPreis;

    /**
     * Initialize-Methode, die fuer die Controllerklasse einer FXML-Datei verpflichtend ist. Diese Methode ruft die
     * Methode "updateInventar" auf, um die Anzeige korrekt darzustellen.
     *
     * @author Felix Ahrens
     */
    @FXML
    public void initialize ()
    {
        updatePreise();
        updateDisplay();
    }

    /**
     * Methode, die die Anzeige des Marktes aktualisiert
     *
     * @author Felix Ahrens
     */
    public void updateDisplay ()
    {
        GameFile instanz = GameFile.getInstanz();
        holzBar.setProgress((double) instanz.getHolzRessource() / Konstanten.INT_ONE_HUNDRED);
        goldBar.setProgress((double) instanz.getGoldRessource() / Konstanten.INT_ONE_HUNDRED);
        gesundheitsBar.setProgress((double) instanz.getGesundheitRessource() / Konstanten.INT_ONE_HUNDRED);
        holzInventarLabel.setText(Strings.HOLZ + Strings.DOPPELPUNKT + Strings.SPACE + instanz.getHolzRessource());
        goldInventarLabel.setText(Strings.GOLD + Strings.DOPPELPUNKT + Strings.SPACE + instanz.getGoldRessource());
        gesundheitsInventarLabel.setText(Strings.GESUNDHEIT + Strings.DOPPELPUNKT + Strings.SPACE + instanz.getGesundheitRessource());
        goldPreisLabel.setText(Strings.GOLDPREIS + goldPreis);
        gesundheitsPreisLabel.setText(Strings.GESUNDHEITSPREIS + String.valueOf(gesundheitsPreis));
    }

    public void updatePreise ()
    {
        Random zufallsGenerator = new Random();
        goldPreis = zufallsGenerator.nextInt(Konstanten.INT_NINE) + Konstanten.INT_ONE;
        gesundheitsPreis = zufallsGenerator.nextInt(Konstanten.INT_FOUR) + Konstanten.INT_ONE;
    }

    /**
     * Kaufe-Methode, mit der das Kaufen ausgefuehrt wird.
     *
     * @author Felix Ahrens
     */
    @FXML
    public void kaufe (MouseEvent mouseEvent)
    {
        GameFile instanz = GameFile.getInstanz();
        String buttonID = ((Button) mouseEvent.getSource()).getId();
        if (buttonID.equals(Strings.GOLD_BUTTON) && instanz.getHolzRessource() >= goldPreis) {
            instanz.setGoldRessource(instanz.getGoldRessource() + Konstanten.INT_ONE);
            instanz.setHolzRessource(instanz.getHolzRessource() - goldPreis);
        } else if (buttonID.equals(Strings.GESUNDHEIT_BUTTON) && instanz.getHolzRessource() >= gesundheitsPreis) {
            instanz.setGesundheitRessource(instanz.getGesundheitRessource() + Konstanten.INT_ONE);
            instanz.setHolzRessource(instanz.getHolzRessource() - gesundheitsPreis);
        } else {
            fehlerMeldungsText.setVisible(true);
        }
        updatePreise();
        updateDisplay();
    }
}
