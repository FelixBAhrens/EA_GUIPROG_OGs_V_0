package control;

import javafx.fxml.FXML;
import model.Charakter;
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
            SzenenManager.wechseleSzene(Strings.FXML_STADT);
        } else {
            SzenenManager.wechseleSzene(Strings.FXML_NEUESSPIEL);
        }
    }

    /**
     * Methode, die die Anfrage fuer Hilfe behandelt, indem ein Hilfe-Menue aufgerufen lassen wird.
     * @throws IOException
     * @author Felix Ahrens
     */
    @FXML
    public void handleHilfe() {
        SzenenManager.wechseleSzene(Strings.FXML_HILFE);
    }

    /**
     * Methode, um eine Transaktion, also einen Kauf durchzufuehren. muss noch um die neuen ressourcen ergänzt werden
     * @param charakter
     * @param holz
     * @param stein
     * @param gold
     * @param banonas
     * @param gesundheit
     * @return
     * @author Felix Ahrens
     */
    public boolean fuehreTransaktionDurch (Charakter charakter, int holz, int stein, int gold, int banonas, int gesundheit){
        GameFile instanz = GameFile.gebeLetztesSpielZurueck();
        if (instanz.getHolzRessource() > holz){
            instanz.setHolzRessource(instanz.getHolzRessource() - holz);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Methode, die die Zurueck-Funktionalitaet beinhaltet
     * @author Felix Ahrens
     */
    @FXML
    public void handleZurueck()
    {
        try {
            SzenenManager.szeneZurueck();
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
