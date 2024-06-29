package control;

import javafx.fxml.FXML;
import model.GameFile;
import res.Konstanten;
import res.Strings;
import utility.MyIO;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Oberklasse aller Controller, die generelle Funktionalitäten beinhaltet
 * @author Felix
 */
public class ControllerController
{
    private ScheduledExecutorService scheduler;

    // @author David Kien
    public ControllerController ()
    {
        scheduler = Executors.newScheduledThreadPool(Konstanten.INT_ONE);
    }

    // @author David Kien
    public void startSaving ()
    {
        Runnable saveTask = this::speichereSpielstand;
        scheduler.scheduleAtFixedRate(saveTask, Konstanten.INT_ZERO, Konstanten.INT_TWENTY, TimeUnit.SECONDS);
    }

    public void stopSaving ()
    {
        if (scheduler != null && !scheduler.isShutdown())
        {
            scheduler.shutdown();
        }
    }

    public void setzeGameFileInstanzLogisch() {
        GameFile gamefile = GameFile.gebeLetztesSpielZurueck();
        if (gamefile != null) {
            GameFile.setzeGameFile(gamefile);
            SceneManager.changeScene(Strings.FXML_STADT);
        } else {
            SceneManager.changeScene(Strings.FXML_NEUESSPIEL);
        }
    }

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
    public void handleZurueck()
    {
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
    public void speichereSpielstand()
    {
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

    @FXML
    public void speichereSpielstandUndBeendeSpiel(){
        GameFile.speichereSpielstand();
        beendeAnwendung();
    }
}
