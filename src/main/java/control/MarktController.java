package control;

// COMPLETED

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

/**
 * Klasse MarktController bildet die Controllerklasse fuer die "markt-view.fxml".
 * In ihr befinden sich Variablen und Methoden, die die Interaktion und
 * Darstellung der GUI manipulieren und fuer Spiellogische Nutzereingaben und
 * -entscheidungen genutzt werden.
 *
 * @author Felix Ahrens.
 */
public class MarktController extends StadtController
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
     * Initialize-Methode, die fuer die Controllerklasse einer FXML-Datei verpflichtend ist.
     * Diese Methode ruft die Methode "updateInventar" auf, um die Anzeige korrekt darzustellen.
     *
     * @pre Die aufgerufenen Methoden muessen erreichbar sein.
     *
     * @post Die Preise und Displays wurden aktualisiert.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void initialize ()
    {
        updatePreise();
        updateDisplay();
    }

    /**
     * Methode, die die Anzeige des Marktes aktualisiert. Dabei sind die GUI-Elemente
     * in dieser Klasse deklariert, ueber die Annotation "@FXML" sind sie mit dem
     * GUI-Element der FXML-Datei verbunden, deren ID dem Bezeichner des jeweiligen
     * Elementes uebereinstimmt. In dieser Klasse wird auf diese zugegriffen und sie werden
     * mit den Parametern der Instanz auf neue Anzeigen gebracht. Es werden sowohl
     * Progressbars als auch Labels geupdated, da beides in etwa der gleichen Methodik
     * entspricht, kann hierfuer eine Methode verwendet werden. Der Progress wird
     * in Teilen von Hundert, also Prozent, angegeben.
     *
     * @pre Die Singleton-Instanz der GameFile muss gesetzt sein, die verwendeten
     * Konstanten, Variablen, Methoden und Klassen muessen erreichbar sein.
     *
     * @post Die Progressbars und Labels zeigen den aktuellen Stand mit den aktuellen
     * Werten des Spielstandes an.
     *
     * @author Felix Ahrens.
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

    /**
     * Methode zum Updaten der Preise. Die Methode erstellt ueber einen
     * Zufallsgenerator zufaellige Zahlen im Rahmen: Gold: Eins bis neun;
     * Gesundheit: Eins bis vier. Die generierten Zufallszahlen werden in den
     * Klassenvariablen "goldPreis" und "gesundheitsPreis" gespeichert.
     *
     * @pre Die Klassenvariablen und die Konstanten im Interface muessen erreichbar sein.
     *
     * @post Die Klassenvariablen "goldPreis" und "gesundheitsPreis" wurden auf
     * neue zufaellige Werte im jeweiligen angegebenen Rahmen gesetzt.
     *
     * @author Felix Ahrens.
     */
    public void updatePreise ()
    {
        Random zufallsGenerator = new Random();
        goldPreis = zufallsGenerator.nextInt(Konstanten.INT_NINE) + Konstanten.INT_ONE;
        gesundheitsPreis = zufallsGenerator.nextInt(Konstanten.INT_FOUR) + Konstanten.INT_ONE;
    }

    /**
     * Kaufe-Methode, mit der das Kaufen ausgefuehrt wird. Abhaengig von der ID des
     * Buttons wird ueberprueft, dass mehr Holz als der jeweilige Kaufpreis vorhanden
     * ist und dann der Kauf ausgefuehrt.
     *
     * @pre Die Button-ID muss entweder "goldButton" oder "gesundheitButton" sein.
     * Die benoetigten Instanzen muessen vorhanden und, im Fall der GameFile, gesetzt sein.
     * Die verwendeten Methoden, Konstanten und Klassen muessen alle erreichbar sein.
     *
     * @post Es wurde ein Kauf getaetigt und eine Visuelle Bestaetigung an die nutzende
     * Person ausgegeben. Falls nicht genug Holz fuer die Transaktion vorhanden ist,
     * wird dies der spielenden Person ebenfalls deutlich gemacht, ueber die Ausgabe
     * eines Fehlermeldungstextes.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void kaufe (MouseEvent mouseEvent)
    {
        GameFile instanz = GameFile.getInstanz();
        String buttonID = ((Button) mouseEvent.getSource()).getId();
        if (buttonID.equals(Strings.GOLD_BUTTON) && instanz.getHolzRessource() >= goldPreis)
        {
            instanz.setGoldRessource(instanz.getGoldRessource() + Konstanten.INT_ONE);
            instanz.setHolzRessource(instanz.getHolzRessource() - goldPreis);
            fehlerMeldungsText.setVisible(false);
        }
        else if (buttonID.equals(Strings.GESUNDHEIT_BUTTON) && instanz.getHolzRessource() >= gesundheitsPreis)
        {
            instanz.setGesundheitRessource(instanz.getGesundheitRessource() + Konstanten.INT_ONE);
            instanz.setHolzRessource(instanz.getHolzRessource() - gesundheitsPreis);
            fehlerMeldungsText.setVisible(false);
        }
        else
        {
            fehlerMeldungsText.setVisible(true);
        }
        speichereSpielstand();
        updatePreise();
        updateDisplay();
    }
}