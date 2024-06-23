package control;

import javafx.fxml.FXML;
import model.GameFile;
import res.Konstanten;
import res.Strings;
import utility.MyIO;

import java.io.IOException;

/**
 * Oberklasse aller Controller, die generelle Funktionalitäten beinhaltet
 * @author Felix
 */
public class ControllerController {
    /**
     * Methode, die die Anfrage fuer Hilfe behandelt, indem ein Hilfe-Menue aufgerufen lassen wird.
     * @throws IOException
     * @author Felix Ahrens
     */
    @FXML
    public void handleHilfe() {
        SceneManager.changeScene(Strings.FXML_HILFE);
    }

    /**
     * Methode, die die Zurueck-Funktionalitaet beinhaltet
     * @author Felix Ahrens
     */
    @FXML
    public void handleZurueck(){
        try {
            SceneManager.goBack();
        } catch (RuntimeException e) {
            MyIO.print(Strings.FEHLERMELDUNG_ZURUECK);
        }
    }

    /**
     * Methode, die den aktuellen Spielstand von einer entsprechenden Methode in der Klasse GameFile speichern laesst.
     * @author Felix Ahrens
     */
    @FXML
    public void speichereSpielstand(){
        GameFile.speichereSpielstand();
    }

    /**
     * Methode, die die Anwendung mit dem Exit-Code null beendet
     * @author Felix Ahrens
     */
    @FXML
    public void beendeAnwendung () {
        System.exit(Konstanten.INT_ZERO);
    }
}
