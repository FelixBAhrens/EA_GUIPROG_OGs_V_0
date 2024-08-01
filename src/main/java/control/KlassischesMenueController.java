package control;

// COMPLETED

import javafx.fxml.FXML;
import model.Artefakt;
import model.GameFile;
import res.Konstanten;
import res.Strings;

/**
 * Controllerklasse der "klassisches-menue-view.fxml". Hier finden sich diverse
 * Methoden zur Behandlung von Nutzereingaben ueber dieses Menue.
 *
 * @author Felix Ahrens.
 */
public class KlassischesMenueController extends StartMenueController
{
    /**
     * Initialize-Methode, die fuer FXML-Controllerklassen verpflichtend ist.
     *
     * @pre /.
     *
     * @post /.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void initialize ()
    {

    }

    /**
     * Methode, um in die Stadt direkt zu wechseln. Zu Debugzwecken.
     *
     * @pre Die Methode "wechseleSzene" im SzenenManager und die Konstante muss erreichbar sein.
     *
     * @post Es wurde ein Szenenwechsel zur Stadt vollzogen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleDebugStadt ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_PLAYER_REBORN);
    }

    /**
     * Methode, um in die Karte direkt zu wechseln. Zu Debugzwecken.
     *
     * @pre Die Methode "wechseleSzene" im SzenenManager und die Konstante muss erreichbar sein.
     *
     * @post Es wurde ein Szenenwechsel zur Karte vollzogen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleDebugKarte ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_KARTE);
    }

    /**
     * Methode, um in den Kampf direkt zu wechseln. Zu Debugzwecken.
     *
     * @pre Die Methode "wechseleSzene" im SzenenManager und die Konstante muss erreichbar sein.
     *
     * @post Es wurde ein Szenenwechsel zum Kampf vollzogen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleDebugKampf ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_KAMPF);
    }

    /**
     * Methode, um in die Arena direkt zu wechseln. Zu Debugzwecken.
     *
     * @pre Die Methode "wechseleSzene" im SzenenManager und die Konstante muss erreichbar sein.
     *
     * @post Es wurde ein Szenenwechsel zur Arena vollzogen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleDebugArena ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_ARENA);
    }

    /**
     * Methode, um in den Engegnerkampf direkt zu wechseln. Zu Debugzwecken.
     *
     * @pre Die Methode "wechseleSzene" im SzenenManager und die Konstante muss erreichbar sein.
     *
     * @post Es wurde ein Szenenwechsel zum Endgegnerkampf vollzogen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleDebugEndgegner ()
    {
        setzeGameFileInstanzLogisch();
        KampfController.kampfTyp = KampfController.KampfTyp.ENDGEGNER_KAMPF;
        SzenenManager.wechseleSzene(Strings.FXML_KAMPF);
    }

    /**
     * Methode, um die Spieldatei in die Konsole auszugeben. Zu Debugzwecken.
     *
     * @pre Die Singleton-Instanz der GameFile muss gesetzt sein.
     *
     * @post Es wurde der Spielstand in die Konsole ausgegeben.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void gameFileAusgeben ()
    {
        System.out.println(GameFile.getInstanz().toString());
    }

    /**
     * Methode zum setzen aller Ressourcen auf Eintausend des jeweiligen Wertes. Zu Debugzwecken.
     *
     * @pre Die Singleton-Instanz der GameFile muss gesetzt sein. Die Konstanten muessen existieren.
     *
     * @post Alle Ressourcenwerte wurden auf Eintausend gesetzt.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleInfiniteMoney ()
    {
        GameFile.getInstanz().setHolzRessource(Konstanten.INT_ONE_THOUSAND);
        GameFile.getInstanz().setSteinRessource(Konstanten.INT_ONE_THOUSAND);
        GameFile.getInstanz().setGoldRessource(Konstanten.INT_ONE_THOUSAND);
        GameFile.getInstanz().setGesundheitRessource(Konstanten.INT_ONE_THOUSAND);
        GameFile.getInstanz().setBanonasRessource(Konstanten.INT_ONE_THOUSAND);
    }

    /**
     * Methode, die alle Artefakte auf "imBesitz" setzt. Zu Debugzwecken.
     *
     * @pre Die GameFile-Instanz muss gesetzt sein. Der Konstruktor der Klasse
     * Artefakt muss verwendbar sein. Die GameFile muss die Getter fuer die Artefakte
     * {Schwert, Statue, Ring} besitzen.
     *
     * @post Alle Artefakte wurden auf "imBesitz = true" gesetzt.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleAlleArtefakteBekommen ()
    {
        GameFile instanz = GameFile.getInstanz();
        Artefakt[] artefakte = {instanz.getSchwert(), instanz.getStatue(), instanz.getRing()};
        for (Artefakt artefakt : artefakte)
        {
            artefakt.setImBesitz(true);
        }
    }
}