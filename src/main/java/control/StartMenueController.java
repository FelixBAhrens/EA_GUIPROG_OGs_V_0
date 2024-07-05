package control;

import javafx.fxml.FXML;
import model.GameFile;
import res.Strings;

/**
 * Die Klasse StartMenueController bildet die Controllerklasse zur zugehoerigen FXML-Datei "startMenue-view.fxml". Hier
 * befinden sich Methoden zum Behandeln von Nutzereingaben. Im StartMenue koennen folgende Aktionen ueber ButtonKlicks
 * getaetigt werden: "Fortfahren", "Neues Spiel", "Spiel Laden", "Einstellungen", "Spiel Beenden". Das StartMenue ist um
 * ein klassisches Menue, repraesentiert durch die "klassisches-menue-view.fxml" ergaenzt. Ueber dieses lassen sich
 * neben Debug-funktionen auch oben genannten im StartMenue vertretenen Funktionen auswaehlen, ergaenzt um den
 * Menuepunkt "Arena", in welchem zwei Spieler einen stand-alone-Kampf ueber Netzwerk ausfuehren koennen.
 *
 * @author Felix Ahrens, David Kien
 */
public class StartMenueController extends ControllerController
{
    /**
     * Methode, die durch einen Knopfdruck aufgerufen wird, die GameFile setzt und die Stadt anzeigen laesst. Die
     * GameFile ist der Rueckgabewert von der Methode "gebeLetztesSpielzurueck" aus der Klasse GameFile. Gibt diese
     * "null" zurueck, wird ein Szenenwechsel zum "Neues Spiel"-Dialog vollzogen. Konkret ist das der Aufruf der
     * handleNeuesSpiel-Methode, die normalerweise vom Knopf "Neues Spiel" aus dem Startmenue aufgerufen wird.
     *
     * @author Felix Ahrens, David Kien
     */
    @FXML
    private void handleFortfahren ()
    {
        GameFile gamefile = GameFile.gebeLetztesSpielZurueck();
        if (gamefile != null)
        {
            GameFile.setzeInstanz(gamefile);
            SzenenManager.wechseleSzene(Strings.FXML_STADT);
        } else
        {
            handleNeuesSpiel();
        }
    }

    /**
     * Methode, die den Szenenwechsel auf die "Neues-Spiel"-Szene veranlasst. Aufgerufen wird diese vom "Neues Spiel"
     * Knopf im Startmenue und im Menuepunkt "Neues Spiel".
     *
     * @author Felix Ahrens
     */
    @FXML
    private void handleNeuesSpiel ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_NEUESSPIEL);
    }

    /**
     * Methode, die den Szenenwechsel auf die "Spiel Laden"-Szene veranlasst. Aufgerufen wird diese vom "Spiel Laden"
     * Knopf im Startmenue und im Menuepunkt "Spiel Laden".
     *
     * @author Felix Ahrens
     */
    @FXML
    private void handleSpielLaden ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_SPIELLADEN);
    }

    /**
     * Methode, die den Szenenwechsel auf die "Arena"-Szene veranlasst. Aufgerufen wird diese vom Menuepunkt "Arena" im
     * klassischen Menue, welches eine in die "startMenue-view.fxml" integrierte FXML-Datei ist und ein klassisches
     * Menue in der oberen linken Ecke des Startmenues definiert.
     *
     * @author Felix Ahrens
     */
    @FXML
    private void handleArena ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_ARENA);
    }

    /**
     * Methode, die den Szenenwechsel auf die "Einstellungen"-Szene veranlasst. Aufgerufen wird diese vom Knopf
     * "Einstllungen" im Startmenue und vom Menuepunkt "Einstellungen".
     *
     * @author Felix Ahrens
     */
    @FXML
    private void handleEinstellungen ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_EINSTELLUNGEN);
    }
}