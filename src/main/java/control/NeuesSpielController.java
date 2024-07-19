package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.GameFile;
import res.Konstanten;
import res.Strings;

/**
 * Die Klasse NeuesSpielController bildet die Controllerklasse fuer die "neuesSpiel-view.fxml" und beinhaltet Methoden
 *  zum Setzen von GUI-Elementen und zum Reagieren auf GUI-seitige Nutzereingaben und Behandeln von dessen Intentionen.
 * @Author Felix Ahrens
 */
public class NeuesSpielController extends ControllerController
{
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

    /**
     * Initialize-Methode, wie sie fuer FXML-Controllerklassen verpflichtend ist.
     * @pre Die aufgerufene Methode muss erreichbar sein.
     * @post Der Schwierigkeitsslider wurde initialisiert.
     * @Author Felix Ahrens
     */
    @FXML
    private void initialize ()
    {
        initialisiereSchwierigkeitsSlider();
    }

    /**
     * Methode zum Initialisieren des Schwierigkeitssliders. Diesem wird ein Eventlistener hinzugefuegt, der eine Value
     *  bei Nutzer-Gui-Interaktion liefert, auf dessen Basis das Label zum Anzeigen der Schwierigkeit aktualisiert wird.
     * @pre die verwendeten Methoden, GUI-Elemente und Konstanten muessen erreichbar sein.
     * @post Dem "schwierigkeitsSlider" wurde ein Listener hinzugefuegt, auf Basis dessen Nutzereingabe die gewaehlte
     *  Schwierigkeit ausgegeben wurde.
     * @Author Felix Ahrens
     */
    public void initialisiereSchwierigkeitsSlider () {
        schwierigkeitsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            schwierigkeitsSlider.setValue(value);
            schwierigkeitsLabel.setVisible(true);
            schwierigkeitsLabel.setText(switch (value)
            {
                case Konstanten.INT_ONE -> Strings.STRING_EINFACH;
                case Konstanten.INT_TWO -> Strings.STRING_NORMAL;
                case Konstanten.INT_THREE -> Strings.STRING_SCHWER;
                default -> null;
            });
        });
    }

    /**
     * Methode zum Fortfahren Abhaengig von der AnchorPane, die gerade visible ist, wird entweder die Schwierigkeit
     * gesetzt oder die GameFile mit der eingegebenen Schwierigkeit und dem eingegebenen Dateinamen erstellt
     * @pre Die verwendeten Methoden, GUI-Elemente und Konstanten muessen erreichbar sein.
     * @post Die GameFile-Instanz wurde mit der ausgewaehlten Schwierigkeit gesetzt, wenn das Menue zum Auswaehlen des SpielstandsNamens angezeigt wurde und auf Fortfahren gedrueckt wurde.
     * @author David Kien, Felix Ahrens
     */
    @FXML
    public void handleFortfahren ()
    {
        if (schwierigkeitsAnchPane.isVisible())
        {
            if (schwierigkeitsLabel.getText().equals(Strings.STRING_EINFACH) || schwierigkeitsLabel.getText().equals(Strings.STRING_NORMAL) || schwierigkeitsLabel.getText().equals(Strings.STRING_SCHWER))
            {
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
     * @pre Die GUI-elemente muessen erreichbar sein.
     * @post Es wurde, abhaengig von dem Fortschritt im "neuesSpiel-Dialog", eine Ebene zurueck gegangen.
     * @Author Felix Ahrens
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
