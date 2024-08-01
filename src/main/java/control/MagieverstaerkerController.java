package control;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import model.GameFile;
import res.Konstanten;

/**
 * Controllerklasse zur "magieverstaerker-view.fxml"-Datei.
 *
 * @author Felix Ahrens.
 */
public class MagieverstaerkerController extends StadtController
{
    private int magiePreis_Banonas = Konstanten.INT_TWO;

    @FXML
    public ProgressBar PreisBar;
    @FXML
    public ProgressBar MagieBar;

    //--------------------------------------------------------------------------

    /**
     * Initialize-Methode der Klasse. Diese ist verpflichtend fuer
     * FXML-Controllerklassen und beinhaltet saemtliche Methoden zum initialisien der
     * Funktionalitaet der Klasse, da diese aufgerufen wird, sobald die FXML-Datei und
     * dadurch ihr Controller, geladen wird. Diese hier aufgerufenen Methoden erfolgen
     * also vor eventuellen Nutzereingaben.
     *
     * @pre Die verwendete Methode muss erreichbar sein.
     *
     * @post Die Progressbars, welche fuer die Darstellung von Informationen verwendet
     * werden, sind aktualisiert worden.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void initialize ()
    {
        updateProgressBars();
    }

    /**
     * Methode zum Kaufen. Wird vom Button aufgerufen, der NutzerEingaben zur
     * Verbesserung der Magie verwendet. //NUR GUI-SCHNITTSTELLE @TODO
     *
     * @param mouseEvent Das Event, dem der Methodenaufruf entstammt.
     *
     * @pre /.
     *
     * @post /.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void kaufe (MouseEvent mouseEvent)
    {

    }

    /**
     * Methode, die die Progressbars zum Anzeigen des Magielevels
     * (hier Nahkampfwert, Aufgabenbereich Spiellogik) und zum Anzeigen des
     * UpgradePreises in Banonas aktualisiert.
     *
     * @pre Die Singleton-Instanz muss gesetzt sein. Die verwendeten GUI-Elemente
     * sowie Variablen und Methoden muessen erreichbar sein.
     *
     * @post Die beiden Progressbars wurden auf den neuesten Stand gebracht.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void updateProgressBars ()
    {
        PreisBar.setProgress((double) GameFile.getInstanz().getBanonasRessource() / (double) magiePreis_Banonas);
        MagieBar.setProgress(GameFile.getInstanz().getLeader().getManapunkte() / (double) Konstanten.INT_TEN);
    }
}