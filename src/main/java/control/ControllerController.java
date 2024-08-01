package control;

import javafx.fxml.FXML;
import model.GameFile;
import res.Konstanten;
import res.Strings;
import utility.MyIO;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Oberklasse aller Controller, die generelle Funktionalitäten beinhaltet.
 *
 * @author Felix Ahrens, David Kien.
 */
public class ControllerController
{
    // Ein ScheduledExecutorService, um Aufgaben periodisch zu planen und auszufuehren.
    private ScheduledExecutorService scheduler;

    //--------------------------------------------------------------------------

    /**
     * Konstruktor fuer ein neues ControllerController-Objekt.
     *
     * @pre /.
     *
     * @post Ein Scheduler-Service mit einem einzelnen Thread wurde initialisiert.
     *
     * @author David Kien.
     */
    public ControllerController ()
    {
        // Initialisiert den Scheduler mit einem Single-Threaded-Executor.
        scheduler = Executors.newScheduledThreadPool(Konstanten.INT_ONE);
    }

    /**
     * Startet eine periodische Aufgabe, die den Spielstand in festen Intervallen speichert.
     *
     * @pre Der Scheduler muss initialisiert sein und darf nicht bereits heruntergefahren sein.
     *
     * @post Eine periodische Aufgabe wurde geplant, um den Spielstand alle 20 Sekunden
     * zu speichern.
     *
     * @throws IllegalStateException falls der Scheduler null ist oder bereits
     * heruntergefahren wurde.
     *
     * @author David Kien.
     */
    public void startSaving ()
    {
        // Definiert die Aufgabe zum Speichern des Spielstandes.
        Runnable saveTask = this::speichereSpielstand;

        // Plant die saveTask, die alle 20 Sekunden ausgefuehrt wird, beginnend sofort.
        scheduler.scheduleAtFixedRate(saveTask, Konstanten.INT_ZERO, Konstanten.INT_TWENTY, TimeUnit.SECONDS);
    }

    /**
     * Stoppt die periodische Speichern-Aufgabe, indem der Scheduler heuruntergefahren wird.
     *
     * @pre Der Scheduler muss initialisiert sein und darf nicht bereits heruntergefahren sein.
     *
     * @post Der Scheduler wurde heruntergefahren und keine weiteren Aufgaben werden ausgefuehrt.
     *
     * @throws IllegalStateException falls der Scheduler null ist.
     *
     * @author David Kien.
     */
    public void stopSaving ()
    {
        // Ueberprueft ob der Scheduler initialisiert ist und nicht bereits heruntergefahren wurde.
        if (scheduler != null && !scheduler.isShutdown())
        {
            // Faehrt den Scheduler herunter, um alle geplanten Aufgaben zu stoppen.
            scheduler.shutdown();
        }
    }

    /**
     * Methode, die entscheidet, wohin die Szene gewechselt werden soll,
     * abhaengig davon, ob ein "letztes Spiel" existiert oder nicht.
     *
     * @pre Die aufgerufenen Methoden muessen in ihren jeweiligen Klassen existieren.
     *
     * @post Die Singleton-Instanz der GameFile wurde auf die zuletzt bespielte
     * GameFile gesetzt und die Stadt-Szene wurde aufgerufen oder,
     * wenn kein letztes Spiel existiert, wurde die "neues Spiel"-Szene aufgerufen.
     *
     * @author Felix Ahrens.
     */
    public void setzeGameFileInstanzLogisch ()
    {
        GameFile gamefile = GameFile.gebeLetztesSpielZurueck();
        if (gamefile != null)
        {
            GameFile.setzeInstanz(gamefile);
            SzenenManager.wechseleSzene(Strings.FXML_STADT);
        }
        else
        {
            SzenenManager.wechseleSzene(Strings.FXML_NEUESSPIEL);
        }
    }

    /**
     * Methode, die die Anfrage fuer Hilfe behandelt, indem die Methode
     * "wechseleSzene" mit dem Dateipfad fuer ein Hilfe-Menue aufgerufen wird.
     *
     * @pre Die Methode "wechseleSzene" muss in der Klasse "SzenenManager"
     * existieren und das Interface "Strings" muss den String "FXML_HILFE" beinhalten.
     *
     * @post Die Methode "wechseleSzene" im SzenenManager wurde mit dem String fuer
     * den Dateipfad fuer die "hilfe"-FXML-Datei aufgerufen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleHilfe ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_HILFE);
    }

    /**
     * Methode, um eine Transaktion, also einen Kauf durchzufuehren,
     * wenn dieser moeglich ist. Sie nutzt die Methode "transaktionIstMoeglich",
     * um zu ueberpruefen, ob eine entsprechende Transaktion moeglich ist.
     * Ist dies der Fall, wird die Transaktion durchgefuehrt, indem die Kosten,
     * die den uebergebenen Parametern fuer Holz, Stein, Gold, Gesundheit und
     * Banonas entsprechen, vom "Ressourcenkonto" abgezogen.
     *
     * @param holz       Die Kosten fuer Holz in der Transaktion.
     * @param stein      Die Kosten fuer Stein in der Transaktion.
     * @param gold       Die Kosten fuer Gold in der Transaktion.
     * @param gesundheit Die Kosten fuer Gesundheit in der Transaktion.
     * @param banonas    Die Kosten fuer Banonas in der Transaktion.
     *
     * @return True, wenn eine Transaktion moeglich war und durchgefuehrt wurde.
     *
     * @pre Die verwendeten Methoden muessen in den jeweiligen Klassen liegen.
     * Der Methode muessen fuenf Integer-werte uebergeben werden.
     *
     * @author Felix Ahrens.
     */
    public boolean fuehreTransaktionDurchWennMoeglich (int holz, int stein, int gold, int gesundheit, int banonas)
    {
        GameFile instanz = GameFile.getInstanz();
        if (transaktionIstMoeglich(holz, stein, gold, gesundheit, banonas))
        {
            instanz.setHolzRessource(instanz.getHolzRessource() - holz);
            instanz.setSteinRessource(instanz.getSteinRessource() - stein);
            instanz.setGoldRessource(instanz.getGoldRessource() - gold);
            instanz.setGesundheitRessource(instanz.getGesundheitRessource() - gesundheit);
            instanz.setBanonasRessource(instanz.getBanonasRessource() - banonas);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Methode, die die Machbarkeit einer Transaktion bestimmt.
     * Sie bekommt die Kosten der einzelnen Ressourcen
     * uebergeben und versichert mit dem booleschen Rueckgabewert, dass das "Konto"
     * des Spielstandes nicht ueberzogen wird. Dazu wird fuer alle fuenf Ressourcentypen
     * ueberprueft, dass die im Besitz liegende Ressource nicht unter
     * dem uebergebenen Kostenwert der jeweiligen Ressource liegt.
     *
     * @param holz       Die Holzkosten.
     * @param stein      Die Steinkosten.
     * @param gold       Die Goldkosten.
     * @param gesundheit Die Gesundheitskosten.
     * @param banonas    Die Banonaskosten.
     *
     * @return Einen Booleschen Wert, der angibt, ob eine Transaktion durchfuehrbar
     * waere, ohne das "Ressourcenkonto" des Spielstandes zu ueberziehen.
     *
     * @author Felix Ahrens.
     */
    public boolean transaktionIstMoeglich (int holz, int stein, int gold, int gesundheit, int banonas)
    {
        GameFile instanz = GameFile.getInstanz();
        return ((instanz.getHolzRessource() >= holz) && instanz.getSteinRessource() >= stein && instanz.getGoldRessource() >= gold && instanz.getGesundheitRessource() >= gesundheit && instanz.getBanonasRessource() >= banonas);
    }

    /**
     * Methode, die die Zurueck-Funktionalitaet beinhaltet.
     *
     * @pre Die verwendeten Methoden muessen in den jeweiligen Klassen existieren. Der SzenenStack im SzenenManager darf
     * nicht leer sein, sonst wird eine Fehlermeldung ausgegeben
     * @post Es wurde um eine Szene zurueck gegangen. Falls das nicht moeglich war, wurde eine Fehlermeldung in die
     * Konsole ausgegeben.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleZurueck ()
    {
        try
        {
            SzenenManager.szeneZurueck();
        } catch (RuntimeException e)
        {
            MyIO.print(Strings.FEHLERMELDUNG_ZURUECK);
        }
    }

    /**
     * Methode zum Beenden des Spiels. Abhaengig davon, ob das Singleton der
     * GameFile gesetzt ist, wird das Spiel entweder direkt beendet oder es wird
     * eine Szene zum Abfragen der Speicherung des Spiels aufgerufen.
     *
     * @pre Die methode "instanzIstGesetzt" muss in der Klasse GameFile existieren.
     * Die Methode "wechseleSzene" muss in der Klasse "SzenenManager" existieren.
     * Die Interfaces fuer Konstanten und Strings muessen die benoetigten Elemente halten.
     *
     * @post Das Spiel wurde beendet oder es wurde die Szene "speichern-abfrage-view.fxml" aufgerufen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void handleSpielBeenden ()
    {
        if (GameFile.instanzIstGesetzt())
        {
            SzenenManager.wechseleSzene(Strings.FXML_SPEICHERN_ABFRAGE);
        }
        else
        {
            System.exit(Konstanten.INT_ZERO);
        }
    }


    /**
     * Methode, die den aktuellen Spielstand von einer entsprechenden Methode in
     * der Klasse GameFile speichern laesst.
     *
     * @pre Die benoetigten Methoden muss in der jeweiligen Klasse existieren.
     *
     * @post Die Methode "speichereSpielstand" in der Klasse GameFile wurde aufgerufen.
     *
     * @author Felix Ahrens, David Kien.
     */
    @FXML
    public void speichereSpielstand ()
    {
        GameFile.speichereSpielstand();
    }

    /**
     * Methode, die den Spielstand speichert und dann die Methode "beendeAnwendung" aufruft,
     * um die Anwendung zu beenden.
     *
     * @pre Die benoetigten Methoden muessen in den jeweiligen Klassen existieren.
     *
     * @post Der Spielstand wurde gespeichert und die Methode "beendeAnwendung" aufgerufen.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void speichereSpielstandUndBeendeSpiel ()
    {
        GameFile.speichereSpielstand();
        beendeAnwendung();
    }

    /**
     * Methode, die die Anwendung mit dem Exit-Code null beendet.
     *
     * @pre /.
     *
     * @post Die Anwedung wurde mit dem Exit-Code null beendet.
     *
     * @author Felix Ahrens.
     */
    @FXML
    public void beendeAnwendung ()
    {
        System.exit(Konstanten.INT_ZERO);
    }
}