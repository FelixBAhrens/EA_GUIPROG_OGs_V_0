package model;

import control.ArtefaktController;
import control.CharakterController;
import res.Konstanten;
import res.Strings;
import utility.MyIO;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

/**
 * Die Klasse GameFile ermoeglicht die Verwendung von Spielstanden als Instanz dieser Klasse. Die GameFile-Instanz ist
 * dabei ein Singleton, da immer nur ein Spielstand zurzeit bespielt werden soll. Auf die (vorher gesetzte) Instanz kann
 * von ueberall im Programm aus zugegriffen werden.
 *
 * @author Felix Ahrens
 */
public class GameFile
{
    /**
     * GameFile ist ein Singleton. Dies ist die einzige Instanz der Klasse GameFile. Sie muss zu Spielstart durch die
     * Methode "setzeInstanz" gesetzt werden.
     *
     * @author Felix Ahrens
     */
    private static GameFile instanz;

    /**
     * Getter fuer die Singleton-Instanz der Klasse GameFile,
     *
     * @return Die einzige Instanz der Klasse GameFile, sofern diese vorher ueber "setzeInstanz" gesetzt wurde.
     * @throws Exception
     * @author Felix Ahrens
     */
    public static GameFile getInstanz ()
    {
        if (instanz != null)
        {
            return instanz;
        } else
        {
            MyIO.print(Strings.FEHLERMELDUNG_GAMEFILE);
            return null;
        }
    }

    /**
     * Setter des Singletons der Klasse GameFile. Diese Methode muss bei Spielstart aufgerufen werden, um einen
     * spielbaren Spielstand als Singleton zu setzen, damit Methoden von ueberall aus der Anwendung auf die Spieldateien
     * zugreifen koennen.
     *
     * @param gameFile Die Instanz der Klasse GameFile, die als Singleton gesetzt werden soll.
     * @pre
     * @author Felix Ahrens
     */
    public static void setzeInstanz (GameFile gameFile)
    {
        instanz = gameFile;
    }

    /**
     * Methode, die zurueckgibt, ob die Singleton-Instanz gesetzt wurde oder nicht.
     *
     * @return True, wenn die "instanz" nicht "null" ist, also ein Spielstand gesetzt wurde.
     * @Author Felix Ahrens
     */
    public static boolean instanzIstGesetzt ()
    {
        return instanz != null;
    }

    //Parameter der Klasse GameFile
    private String dateiPfadUndName;

    public enum Schwierigkeit
    {
        EINFACH(Strings.STRING_EINFACH),
        NORMAL(Strings.STRING_NORMAL),
        SCHWER(Strings.STRING_SCHWER);

        Schwierigkeit (String schwierigkeit)
        {
        }
    }

    private Schwierigkeit schwierigkeit;
    private int holzRessource;
    private int steinRessource;
    private int goldRessource;
    private int gesundheitRessource;
    private int banonasRessource;
    private Artefakt statue;
    private Artefakt schwert;
    private Artefakt ring;
    private Charakter leader;
    private Charakter medic;
    private Charakter hunter;
    private Charakter magician;
    private Charakter scout;
    private boolean fraktionenCampFreigeschaltet;
    private int trainingsgelaendeLevel;
    private int magieverstaerkerLevel;
    private Charakter[] einheitenArray = new Charakter[Konstanten.INT_FIVE];

    /**
     * Getter fuer die Schwierigkeit. Gibt die Schwierigkeit als String zurueck.
     *
     * @return Die Schwierigkeit als String.
     * @pre Die Schwierigkeit muss als Enum in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable schwierigkeit als String zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Schwierigkeit getSchwierigkeit ()
    {
        return schwierigkeit;
    }

    /**
     * Setter fuer die Schwierigkeit. Setzt die Schwierigkeit auf den der Methode als Enum uebergebenen Wert.
     *
     * @param schwierigkeit Die Schwierigkeit als Enum, auf den die Klassenvariable "schwierigkeit" gesetzt werden
     *                      soll.
     * @pre Die Schwierigkeit muss als Enum in der Klasse GameFile enthalten sein.
     * @post Die in der Klasse gespeicherte Schwierigkeit stimmt mit dem Parameter ueberein, der der Methode beim Aufruf
     * uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setSchwierigkeit (Schwierigkeit schwierigkeit)
    {
        this.schwierigkeit = schwierigkeit;
    }

    /**
     * Setter fuer den Dateipfad und Namen. Setzt den Dateipfad und Namen auf den der Methode als String uebergebenen
     * Wert.
     *
     * @param dateiPfadUndName Der Dateipfad und Name als String, auf den die Klassenvariable "filePathAndName" gesetzt
     *                         werden soll.
     * @pre Der Dateipfad und Name muss als String in der Klasse GameFile enthalten sein.
     * @post Der in der Klasse gespeicherte Dateipfad und Name stimmt mit dem Parameter ueberein, der der Methode beim
     * Aufruf uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setDateiPfadUndName (String dateiPfadUndName)
    {
        this.dateiPfadUndName = dateiPfadUndName;
    }

    /**
     * Getter fuer die HolzRessource. Gibt die HolzRessource zurueck.
     *
     * @return Die HolzRessource als int.
     * @pre Die HolzRessource muss als int in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable holzRessource als int zurueckgegeben.
     * @Author Felix Ahrens
     */
    public int getHolzRessource ()
    {
        return holzRessource;
    }

    /**
     * Setter fuer die HolzRessource. Setzt die HolzRessource auf den der Methode als int uebergebenen Wert.
     *
     * @param holzRessource Die HolzRessource als int, auf den die Klassenvariable "holzRessource" gesetzt werden soll.
     * @pre Die HolzRessource muss als int in der Klasse GameFile enthalten sein.
     * @post Die in der Klasse gespeicherte HolzRessource stimmt mit dem Parameter ueberein, der der Methode beim Aufruf
     * uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setHolzRessource (int holzRessource)
    {
        this.holzRessource = holzRessource;
    }

    /**
     * Getter fuer die SteinRessource. Gibt die SteinRessource zurueck.
     *
     * @return Die SteinRessource als int.
     * @pre Die SteinRessource muss als int in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable steinRessource als int zurueckgegeben.
     * @Author Felix Ahrens
     */
    public int getSteinRessource ()
    {
        return steinRessource;
    }

    /**
     * Setter fuer die SteinRessource. Setzt die SteinRessource auf den der Methode als int uebergebenen Wert.
     *
     * @param steinRessource Die SteinRessource als int, auf den die Klassenvariable "steinRessource" gesetzt werden
     *                       soll.
     * @pre Die SteinRessource muss als int in der Klasse GameFile enthalten sein.
     * @post Die in der Klasse gespeicherte SteinRessource stimmt mit dem Parameter ueberein, der der Methode beim
     * Aufruf uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setSteinRessource (int steinRessource)
    {
        this.steinRessource = steinRessource;
    }

    /**
     * Getter fuer die GoldRessource. Gibt die GoldRessource zurueck.
     *
     * @return Die GoldRessource als int.
     * @pre Die GoldRessource muss als int in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable goldRessource als int zurueckgegeben.
     * @Author Felix Ahrens
     */
    public int getGoldRessource ()
    {
        return goldRessource;
    }

    /**
     * Setter fuer die GoldRessource. Setzt die GoldRessource auf den der Methode als int uebergebenen Wert.
     *
     * @param goldRessource Die GoldRessource als int, auf den die Klassenvariable "goldRessource" gesetzt werden soll.
     * @pre Die GoldRessource muss als int in der Klasse GameFile enthalten sein.
     * @post Die in der Klasse gespeicherte GoldRessource stimmt mit dem Parameter ueberein, der der Methode beim Aufruf
     * uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setGoldRessource (int goldRessource)
    {
        this.goldRessource = goldRessource;
    }

    /**
     * Getter fuer die GesundheitRessource. Gibt die GesundheitRessource zurueck.
     *
     * @return Die GesundheitRessource als int.
     * @pre Die GesundheitRessource muss als int in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable gesundheitRessource als int zurueckgegeben.
     * @Author Felix Ahrens
     */
    public int getGesundheitRessource ()
    {
        return gesundheitRessource;
    }

    /**
     * Setter fuer die GesundheitRessource. Setzt die GesundheitRessource auf den der Methode als int uebergebenen
     * Wert.
     *
     * @param gesundheitRessource Die GesundheitRessource als int, auf den die Klassenvariable "gesundheitRessource"
     *                            gesetzt werden soll.
     * @pre Die GesundheitRessource muss als int in der Klasse GameFile enthalten sein.
     * @post Die in der Klasse gespeicherte GesundheitRessource stimmt mit dem Parameter ueberein, der der Methode beim
     * Aufruf uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setGesundheitRessource (int gesundheitRessource)
    {
        this.gesundheitRessource = gesundheitRessource;
    }

    /**
     * Getter fuer die BanonasRessource. Gibt die BanonasRessource zurueck.
     *
     * @return Die BanonasRessource als int.
     * @pre Die BanonasRessource muss als int in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable banonasRessource als int zurueckgegeben.
     * @Author Felix Ahrens
     */
    public int getBanonasRessource ()
    {
        return banonasRessource;
    }

    /**
     * Setter fuer die BanonasRessource. Setzt die BanonasRessource auf den der Methode als int uebergebenen Wert.
     *
     * @param banonaRessource Die BanonasRessource als int, auf den die Klassenvariable "banonasRessource" gesetzt
     *                        werden soll.
     * @pre Die BanonasRessource muss als Integer in der Klasse GameFile enthalten sein.
     * @post Die in der Klasse gespeicherte BanonasRessource stimmt mit dem Parameter ueberein, der der Methode beim
     * Aufruf uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setBanonasRessource (int banonaRessource)
    {
        this.banonasRessource = banonaRessource;
    }

    /**
     * Getter fuer die Statue. Gibt die Statue zurueck.
     *
     * @return Die Statue als Artefakt.
     * @pre Die Statue muss als Artefakt in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable statue als Artefakt zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Artefakt getStatue ()
    {
        return statue;
    }

    /**
     * Setter fuer die Statue. Setzt die Statue auf den der Methode als Artefakt uebergebenen Wert.
     *
     * @param statue Die Statue als Artefakt, auf den die Klassenvariable "statue" gesetzt werden soll.
     * @pre Die Statue muss als Artefakt in der Klasse GameFile enthalten sein.
     * @post Die in der Klasse gespeicherte Statue stimmt mit dem Parameter ueberein, der der Methode beim Aufruf
     * uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setStatue (Artefakt statue)
    {
        this.statue = statue;
    }

    /**
     * Getter fuer den Ring. Gibt den Ring zurueck.
     *
     * @return Der Ring als Artefakt.
     * @pre Der Ring muss als Artefakt in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable ring als Artefakt zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Artefakt getRing ()
    {
        return ring;
    }

    /**
     * Setter fuer den Ring. Setzt den Ring auf den der Methode als Artefakt uebergebenen Wert.
     *
     * @param ring Der Ring als Artefakt, auf den die Klassenvariable "ring" gesetzt werden soll.
     * @pre Der Ring muss als Artefakt in der Klasse GameFile enthalten sein.
     * @post Der in der Klasse gespeicherte Ring stimmt mit dem Parameter ueberein, der der Methode beim Aufruf
     * uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setRing (Artefakt ring)
    {
        this.ring = ring;
    }

    /**
     * Getter fuer das Schwert. Gibt das Schwert zurueck.
     *
     * @return Das Schwert als Artefakt.
     * @pre Das Schwert muss als Artefakt in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable schwert als Artefakt zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Artefakt getSchwert ()
    {
        return schwert;
    }

    /**
     * Setter fuer das Schwert. Setzt das Schwert auf den der Methode als Artefakt uebergebenen Wert.
     *
     * @param schwert Das Schwert als Artefakt, auf den die Klassenvariable "schwert" gesetzt werden soll.
     * @pre Das Schwert muss als Artefakt in der Klasse GameFile enthalten sein.
     * @post Das in der Klasse gespeicherte Schwert stimmt mit dem Parameter ueberein, der der Methode beim Aufruf
     * uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setSchwert (Artefakt schwert)
    {
        this.schwert = schwert;
    }

    /**
     * Getter fuer den Leader. Gibt den Leader zurueck.
     *
     * @return Der Leader als Charakter.
     * @pre Der Leader muss als Charakter in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable leader als Charakter zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Charakter getLeader ()
    {
        return leader;
    }

    /**
     * Setter fuer den Leader. Setzt den Leader auf den der Methode als Charakter uebergebenen Wert.
     *
     * @param leader Der Leader als Charakter, auf den die Klassenvariable "leader" gesetzt werden soll.
     * @pre Der Leader muss als Charakter in der Klasse GameFile enthalten sein.
     * @post Der in der Klasse gespeicherte Leader stimmt mit dem Parameter ueberein, der der Methode beim Aufruf
     * uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setLeader (Charakter leader)
    {
        this.leader = leader;
    }

    /**
     * Getter fuer den Medic. Gibt den Medic zurueck.
     *
     * @return Der Medic als Charakter.
     * @pre Der Medic muss als Charakter in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable medic als Charakter zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Charakter getMedic ()
    {
        return medic;
    }

    /**
     * Setter fuer den Medic. Setzt den Medic auf den der Methode als Charakter uebergebenen Wert.
     *
     * @param medic Der Medic als Charakter, auf den die Klassenvariable "medic" gesetzt werden soll.
     * @pre Der Medic muss als Charakter in der Klasse GameFile enthalten sein.
     * @post Der in der Klasse gespeicherte Medic stimmt mit dem Parameter ueberein, der der Methode beim Aufruf
     * uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setMedic (Charakter medic)
    {
        this.medic = medic;
    }

    /**
     * Getter fuer den Hunter. Gibt den Hunter zurueck.
     *
     * @return Der Hunter als Charakter.
     * @pre Der Hunter muss als Charakter in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable hunter als Charakter zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Charakter getHunter ()
    {
        return hunter;
    }

    /**
     * Setter fuer den Hunter. Setzt den Hunter auf den der Methode als Charakter uebergebenen Wert.
     *
     * @param hunter Der Hunter als Charakter, auf den die Klassenvariable "hunter" gesetzt werden soll.
     * @pre Der Hunter muss als Charakter in der Klasse GameFile enthalten sein.
     * @post Der in der Klasse gespeicherte Hunter stimmt mit dem Parameter ueberein, der der Methode beim Aufruf
     * uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setHunter (Charakter hunter)
    {
        this.hunter = hunter;
    }

    /**
     * Getter fuer den Magician. Gibt den Magician zurueck.
     *
     * @return Der Magician als Charakter.
     * @pre Der Magician muss als Charakter in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable magician als Charakter zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Charakter getMagician ()
    {
        return magician;
    }

    /**
     * Setter fuer den Magician. Setzt den Magician auf den der Methode als Charakter uebergebenen Wert.
     *
     * @param magician Der Magician als Charakter, auf den die Klassenvariable "magician" gesetzt werden soll.
     * @pre Der Magician muss als Charakter in der Klasse GameFile enthalten sein.
     * @post Der in der Klasse gespeicherte Magician stimmt mit dem Parameter ueberein, der der Methode beim Aufruf
     * uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setMagician (Charakter magician)
    {
        this.magician = magician;
    }

    /**
     * Getter fuer den Scout. Gibt den Scout zurueck.
     *
     * @return Der Scout als Charakter.
     * @pre Der Scout muss als Charakter in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable scout als Charakter zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Charakter getScout ()
    {
        return scout;
    }

    /**
     * Setter fuer den Scout. Setzt den Scout auf den der Methode als Charakter uebergebenen Wert.
     *
     * @param scout Der Scout als Charakter, auf den die Klassenvariable "scout" gesetzt werden soll.
     * @pre Der Scout muss als Charakter in der Klasse GameFile enthalten sein.
     * @post Der in der Klasse gespeicherte Scout stimmt mit dem Parameter ueberein, der der Methode beim Aufruf
     * uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setScout (Charakter scout)
    {
        this.scout = scout;
    }

    /**
     * Getter fuer den Dateipfad und Namen. Gibt den Dateipfad und Namen zurueck.
     *
     * @return Der Dateipfad und Name als String.
     * @pre Der Dateipfad und Name muss als String in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable filePathAndName als String zurueckgegeben.
     * @Author Felix Ahrens
     */
    public String getDateiPfadUndName ()
    {
        return dateiPfadUndName;
    }

    /**
     * Ueberprueft, ob das FraktionenCamp freigeschaltet ist.
     *
     * @return Der Status des FraktionenCamps als boolean.
     * @pre Der Status des FraktionenCamps muss als boolean in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable fraktionenCampFreigeschaltet als boolean zurueckgegeben.
     * @Author Felix Ahrens
     */
    public boolean fraktionenCampIstFreigeschaltet ()
    {
        return fraktionenCampFreigeschaltet;
    }

    /**
     * Setter fuer den Status des FraktionenCamps. Setzt den Status des FraktionenCamps auf den der Methode als boolean
     * uebergebenen Wert.
     *
     * @param fraktionenCampFreigeschaltet Der Status des FraktionenCamps als boolean, auf den die Klassenvariable
     *                                     "fraktionenCampFreigeschaltet" gesetzt werden soll.
     * @pre Der Status des FraktionenCamps muss als boolean in der Klasse GameFile enthalten sein.
     * @post Der in der Klasse gespeicherte Status des FraktionenCamps stimmt mit dem Parameter ueberein, der der
     * Methode beim Aufruf uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setFraktionenCampFreigeschaltet (boolean fraktionenCampFreigeschaltet)
    {
        this.fraktionenCampFreigeschaltet = fraktionenCampFreigeschaltet;
    }

    /**
     * Getter fuer das TrainingsgelaendeLevel. Gibt das TrainingsgelaendeLevel zurueck.
     *
     * @return Das TrainingsgelaendeLevel als int.
     * @pre Das TrainingsgelaendeLevel muss als int in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable trainingsgelaendeLevel als int zurueckgegeben.
     * @Author Felix Ahrens
     */
    public int getTrainingsgelaendeLevel ()
    {
        return trainingsgelaendeLevel;
    }

    /**
     * Setter fuer das TrainingsgelaendeLevel. Setzt das TrainingsgelaendeLevel auf den der Methode als int uebergebenen
     * Wert.
     *
     * @param trainingsgelaendeLevel Das TrainingsgelaendeLevel als int, auf den die Klassenvariable
     *                               "trainingsgelaendeLevel" gesetzt werden soll.
     * @pre Das TrainingsgelaendeLevel muss als int in der Klasse GameFile enthalten sein.
     * @post Das in der Klasse gespeicherte TrainingsgelaendeLevel stimmt mit dem Parameter ueberein, der der Methode
     * beim Aufruf uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setTrainingsgelaendeLevel (int trainingsgelaendeLevel)
    {
        this.trainingsgelaendeLevel = trainingsgelaendeLevel;
    }

    /**
     * Getter fuer das MagieverstaerkerLevel. Gibt das MagieverstaerkerLevel zurueck.
     *
     * @return Das MagieverstaerkerLevel als int.
     * @pre Das MagieverstaerkerLevel muss als int in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable magieverstaerkerLevel als int zurueckgegeben.
     * @Author Felix Ahrens
     */
    public int getMagieverstaerkerLevel ()
    {
        return magieverstaerkerLevel;
    }

    /**
     * Setter fuer das MagieverstaerkerLevel. Setzt das MagieverstaerkerLevel auf den der Methode als int uebergebenen
     * Wert.
     *
     * @param magieverstaerkerLevel Das MagieverstaerkerLevel als int, auf den die Klassenvariable
     *                              "magieverstaerkerLevel" gesetzt werden soll.
     * @pre Das MagieverstaerkerLevel muss als int in der Klasse GameFile enthalten sein.
     * @post Das in der Klasse gespeicherte MagieverstaerkerLevel stimmt mit dem Parameter ueberein, der der Methode
     * beim Aufruf uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setMagieverstaerkerLevel (int magieverstaerkerLevel)
    {
        this.magieverstaerkerLevel = magieverstaerkerLevel;
    }

    /**
     * Getter fuer das einheitenArray. Gibt das EinheitenArray zurueck.
     *
     * @return Das einheitenArray als int.
     * @pre Das einheitenArray muss als int in der Klasse GameFile enthalten sein.
     * @post Es wurde der Inhalt der Variable einheitenArray als Charakter-Array zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Charakter[] getEinheitenArray ()
    {
        return einheitenArray;
    }

    /**
     * Setter fuer das einheitenArray. Setzt das MagieverstaerkerLevel auf den der Methode als int uebergebenen Wert.
     *
     * @param einheitenArray Das einheitenArray als int, auf den die einheitenArray "einheitenArray" gesetzt werden
     *                       soll.
     * @pre Das einheitenArray muss als CharakterArray in der Klasse GameFile enthalten sein.
     * @post Das in der Klasse gespeicherte einheitenArray stimmt mit dem Parameter ueberein, der der Methode beim
     * Aufruf uebergeben wurde.
     * @Author Felix Ahrens
     */
    public void setEinheitenArray (Charakter[] einheitenArray)
    {
        this.einheitenArray = einheitenArray;
    }

    /**
     * Default Konstruktor von GameFile
     *
     * @Author Felix Ahrens
     */
    public GameFile ()
    {
    }

    /**
     * Konstruktor der Klasse GameFile. Bekommt alle Werte, mit der die GameFile erstellt wird, uebergeben. Gibt eine
     * neue Instanz der Klasse GameFile zurueck.
     *
     * @param dateiPfadUndName             Dateipfad- und name, um auf den Dateipfad der CSV-Datei der GameFile
     *                                     zugreifen zu koennen
     * @param schwierigkeit                Die Schwierigkeit des Spielstands
     * @param holzRessource                Die Anzahl an Holzressourcen des Spielstands
     * @param steinRessource               Die Anzahl an Steinressourcen des Spielstandes
     * @param goldRessource                Die Anzahl an Goldressourcen des Spielstands
     * @param gesundheitRessource          Die Anzahl an Gesundheitsressourcen des Spielstands
     * @param banonasRessource             Die Anzahl an Banonasressourcen des Spielstandes
     * @param statue                       Die Instanz "statue" der Klasse "Artefakt" des Spielstands
     * @param schwert                      Die Instanz "schwert" der Klasse "Artefakt" des Spielstands
     * @param ring                         Die Instanz "ring" der Klasse "Artefakt" des Spielstands
     * @param leader                       Die Instanz "leader" der Klasse "Charakter" des Spielstands
     * @param medic                        Die Instanz "medic" der Klasse "Charakter" des Spielstands
     * @param hunter                       Die Instanz "hunter" der Klasse "Charakter" des Spielstands
     * @param magician                     Die Instanz "magician" der Klasse "Charakter" des Spielstands
     * @param scout                        Die Instanz "scout" der Klasse "Charakter" des Spielstands
     * @param fraktionenCampFreigeschaltet Der boolesche Wert, ob das Fraktionencamp im Spiel freigeschaltet wurde
     * @param trainingsgelaendeLevel       Das Level, in dem sich das verbesserbare Trainingsgelaende befindet
     * @param magieverstaerkerLevel        Das Level, in dem sich der verbesserbare Magieverstaerker befindet
     * @pre Die Datentypen der uebergebenen Parameter muessen in der Reihenfolge mit denen der Instanzvariablen
     * uebereinstimmen. Die Variablen muessen in der Klasse existieren.
     * @post Es wurde eine neue Instanz der Klasse GameFile mit den dem Konstruktor uebergebenen Werten erstellt und
     * zurueckgegeben.
     * @Author Felix Ahrens
     */
    private GameFile (String dateiPfadUndName, Schwierigkeit schwierigkeit, int holzRessource, int steinRessource, int goldRessource, int gesundheitRessource, int banonasRessource, Artefakt statue, Artefakt schwert, Artefakt ring, Charakter leader, Charakter medic, Charakter hunter,
                      Charakter magician, Charakter scout, boolean fraktionenCampFreigeschaltet, int trainingsgelaendeLevel, int magieverstaerkerLevel)
    {
        this.dateiPfadUndName = dateiPfadUndName;
        this.schwierigkeit = schwierigkeit;
        this.holzRessource = holzRessource;
        this.steinRessource = steinRessource;
        this.goldRessource = goldRessource;
        this.gesundheitRessource = gesundheitRessource;
        this.banonasRessource = banonasRessource;
        this.statue = statue;
        this.schwert = schwert;
        this.ring = ring;
        this.leader = leader;
        this.medic = medic;
        this.hunter = hunter;
        this.magician = magician;
        this.scout = scout;
        this.fraktionenCampFreigeschaltet = fraktionenCampFreigeschaltet;
        this.trainingsgelaendeLevel = trainingsgelaendeLevel;
        this.magieverstaerkerLevel = magieverstaerkerLevel;
    }

    /**
     * Methode, die eine neue GameFile mit den ihr uebergebenen Parametern erstellt. Fuer die nicht angegebenen Werte
     * werden die Standartwerte aus der jeweiligen Klasse verwendet, etwa bei Charakteren oder Artefakten.
     *
     * @return Eine neue, mit den Default-Werten erstellte Instanz der Klasse GameFile
     * @pre Der Konstruktor der GameFile muss existieren und die uebergebenen Werte in der gleichen Reihenfolge
     * "akzeptieren" und verwenden koennen. Die der Methode uebergebenen Werte muessen Strings fuer den Spielnamen und
     * die Schwierigkeit sein
     * @Author Felix Ahrens
     */
    public static GameFile erstelleNeueDefaultGameFile (String spielName, Schwierigkeit schwierigkeit)
    {
        if (spielName.length() < Konstanten.INT_ONE)
        {
            spielName = Strings.SPIEL + (gebeFileListeZurueck(Strings.SPIELDATEIPFAD).length - 1);
        }
        String spielPfad_Name = Strings.SPIELDATEIPFAD + spielName + Strings.CSV_ENDUNG;
        Artefakt[] artefaktArray = ArtefaktController.erstelleDefaultArtefakte();
        Charakter[] charakterArray = CharakterController.erstelleDefaultCharakter();
        GameFile neueGameFile = new GameFile(spielPfad_Name,
                schwierigkeit,
                Konstanten.DEFAULT_VALUE_HOLZ,
                Konstanten.DEFAULT_VALUE_STEIN,
                Konstanten.DEFAULT_VALUE_GOLD,
                Konstanten.DEFAULT_VALUE_GESUNDHEIT,
                Konstanten.DEFAULT_VALUE_BANANAS,
                artefaktArray[Konstanten.INT_ZERO],
                artefaktArray[Konstanten.INT_ONE],
                artefaktArray[Konstanten.INT_TWO],
                charakterArray[Konstanten.INT_ZERO],
                charakterArray[Konstanten.INT_ONE],
                charakterArray[Konstanten.INT_TWO],
                charakterArray[Konstanten.INT_THREE],
                charakterArray[Konstanten.INT_FOUR],
                false,
                Konstanten.INT_ZERO,
                Konstanten.INT_ZERO);
        schreibeGameFile(neueGameFile);
        return leseGameFile(spielPfad_Name);
    }

    public static void schreibeGameFile (GameFile gameFile)
    {
        try
        {
            FileWriter dateiSchreiber = new FileWriter(gameFile.dateiPfadUndName);
            dateiSchreiber.write(gameFile.dateiPfadUndName + Strings.NEWLINE + gameFile.schwierigkeit + Strings.NEWLINE);
            //Ressourcen
            dateiSchreiber.write(gameFile.holzRessource + Strings.SEMIKOLON
                    + gameFile.steinRessource + Strings.SEMIKOLON
                    + gameFile.goldRessource + Strings.SEMIKOLON
                    + gameFile.gesundheitRessource + Strings.SEMIKOLON
                    + gameFile.banonasRessource + Strings.SEMIKOLON
                    + Strings.NEWLINE);
            //Artefakte
            dateiSchreiber.write(gameFile.statue.toCSV() + Strings.NEWLINE);
            dateiSchreiber.write(gameFile.schwert.toCSV() + Strings.NEWLINE);
            dateiSchreiber.write(gameFile.ring.toCSV() + Strings.NEWLINE);
            //Charaktere
            dateiSchreiber.write(gameFile.leader + Strings.NEWLINE);
            dateiSchreiber.write(gameFile.medic + Strings.NEWLINE);
            dateiSchreiber.write(gameFile.hunter + Strings.NEWLINE);
            dateiSchreiber.write(gameFile.magician + Strings.NEWLINE);
            dateiSchreiber.write(gameFile.scout + Strings.NEWLINE);
            dateiSchreiber.write(gameFile.fraktionenCampFreigeschaltet + Strings.NEWLINE);
            dateiSchreiber.write(gameFile.trainingsgelaendeLevel + Strings.NEWLINE);
            dateiSchreiber.write(gameFile.magieverstaerkerLevel + Strings.NEWLINE);
            dateiSchreiber.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Methode, die eine bestimmte gespeicherte GameFile lesen kann.
     *
     * @return
     * @Author Felix Ahrens
     */
    public static GameFile leseGameFile (String fileName)
    {
        if (!fileName.contains(Strings.CSV_ENDUNG))
        {
            fileName = fileName + Strings.CSV_ENDUNG;
        }
        if (!fileName.contains(Strings.SPIELDATEIPFAD))
        {
            fileName = Strings.SPIELDATEIPFAD + fileName;
        }
        return macheGameFileAusZeilenArray(leseCSV(fileName));
    }

    /**
     * Methode, die eine CSV-Datei einliest und diese als String-Array, in welcher der CSV-Inhalt zeilenweise
     * gespeichert ist, zurueckgibt.
     *
     * @param filePathAndName Der Dateipfad und -name der zu einlesenden CSV-Datei.
     * @return String[] Das String-Array, das die Zeilen der CSV-Datei beinhaltet.
     * @author Felix Ahrens
     */
    public static String[] leseCSV (String filePathAndName)
    {
        System.out.println(filePathAndName);
        List<String> lines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePathAndName)))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                lines.add(line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return lines.toArray(new String[0]);
    }

    /**
     * @param schwierigkeitsString
     * @return
     */
    public static Schwierigkeit stringZuSchwierigkeitsEnum (String schwierigkeitsString)
    {
        return switch (schwierigkeitsString)
        {
            case Strings.STRING_EINFACH -> Schwierigkeit.EINFACH;
            case Strings.STRING_NORMAL -> Schwierigkeit.NORMAL;
            case Strings.STRING_SCHWER -> Schwierigkeit.SCHWER;
            default ->
            {
                MyIO.print(Strings.FEHLERMELDUNG_SCHWIERIGKEIT);
                yield null;
            }
        };
    }

    /**
     * Methode, die aus dem Inhalt eines String-Arrays eine GameFile macht. GameFiles sollen noch mehr Daten als nur die
     * Beispieldaten Name und Datum enthalten. Dafuer braucht es aber auch noch einen selbst erstellten Standard zur
     * Reihenfolge.
     *
     * @return
     * @Author Felix Ahrens
     */
    public static GameFile macheGameFileAusZeilenArray (String[] zeilenArray)
    {
        try
        {
            return new GameFile(zeilenArray[Konstanten.INT_ZERO],
                    stringZuSchwierigkeitsEnum(zeilenArray[Konstanten.INT_ONE]),
                    Integer.parseInt(zeilenArray[Konstanten.INT_TWO].split(Strings.SEMIKOLON)[Konstanten.INT_ZERO]),
                    Integer.parseInt(zeilenArray[Konstanten.INT_TWO].split(Strings.SEMIKOLON)[Konstanten.INT_ONE]),
                    Integer.parseInt(zeilenArray[Konstanten.INT_TWO].split(Strings.SEMIKOLON)[Konstanten.INT_TWO]),
                    Integer.parseInt(zeilenArray[Konstanten.INT_TWO].split(Strings.SEMIKOLON)[Konstanten.INT_THREE]),
                    Integer.parseInt(zeilenArray[Konstanten.INT_TWO].split(Strings.SEMIKOLON)[Konstanten.INT_FOUR]),
                    erstelleArtefaktAusCSVZeile(zeilenArray[Konstanten.INT_THREE]),
                    erstelleArtefaktAusCSVZeile(zeilenArray[Konstanten.INT_FOUR]),
                    erstelleArtefaktAusCSVZeile(zeilenArray[Konstanten.INT_FIVE]),
                    erstelleCharakterAusCSVZeile(zeilenArray[Konstanten.INT_SIX]),
                    erstelleCharakterAusCSVZeile(zeilenArray[Konstanten.INT_SEVEN]),
                    erstelleCharakterAusCSVZeile(zeilenArray[Konstanten.INT_EIGHT]),
                    erstelleCharakterAusCSVZeile(zeilenArray[Konstanten.INT_NINE]),
                    erstelleCharakterAusCSVZeile(zeilenArray[Konstanten.INT_TEN]),
                    Boolean.valueOf(zeilenArray[Konstanten.INT_ELEVEN]),
                    Integer.parseInt(zeilenArray[Konstanten.INT_TWELVE]),
                    Integer.parseInt(zeilenArray[Konstanten.INT_THIRTEEN]));
        } catch (ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Methode, die alle Files in dem angegebenen Dateipfad als Array zurueckgibt.
     *
     * @return
     * @Author Felix Ahrens
     */
    public static File[] gebeFileListeZurueck (String dateiPfad)
    {
        File gameFileVerzeichnis = new File(dateiPfad);
        return gameFileVerzeichnis.listFiles();
    }

    /**
     * Methode, die eine Zeile zu einem Artefakt parsed
     *
     * @param zeile
     * @return
     * @author Felix Ahrens
     */
    public static Artefakt erstelleArtefaktAusCSVZeile (String zeile)
    {
        String[] zeilenStuecke = zeile.split(Strings.SEMIKOLON);
        return new Artefakt(zeilenStuecke[Konstanten.INT_ZERO],
                Boolean.parseBoolean(zeilenStuecke[Konstanten.INT_ONE]),
                Integer.parseInt(zeilenStuecke[Konstanten.INT_TWO]),
                Integer.parseInt(zeilenStuecke[Konstanten.INT_THREE]));
    }

    /**
     * Methode, die eine Zeile zu einem Charakter parsed.
     *
     * @param zeile
     * @return
     * @author Felix Ahrens
     */
    public static Charakter erstelleCharakterAusCSVZeile (String zeile)
    {
        Integer[] zeilenStuecke = Arrays.stream(zeile.split(Strings.DOPPELPUNKT)[Konstanten.INT_ONE].split(Strings.SEMIKOLON))
                .map(String::trim)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        return new Charakter(zeile.split(Strings.DOPPELPUNKT)[Konstanten.INT_ZERO], zeilenStuecke[Konstanten.INT_ZERO],
                zeilenStuecke[Konstanten.INT_ONE], zeilenStuecke[Konstanten.INT_TWO], zeilenStuecke[Konstanten.INT_THREE],
                zeilenStuecke[Konstanten.INT_FOUR], zeilenStuecke[Konstanten.INT_FIVE], zeilenStuecke[Konstanten.INT_SIX],
                zeilenStuecke[Konstanten.INT_SEVEN], zeilenStuecke[Konstanten.INT_EIGHT], zeilenStuecke[Konstanten.INT_NINE],
                Boolean.valueOf(zeile.split(Strings.DOPPELPUNKT)[Konstanten.INT_TWO]));
    }

    /**
     * Methode, die die GameFile zurueckgibt, die zuletzt modifiziert (also bespielt) wurde.
     *
     * @return die GameFile, die zuletzt bespielt wurde
     * @throws IOException
     * @author Felix Ahrens
     */
    public static GameFile gebeLetztesSpielZurueck ()
    {
        try
        {
            return macheGameFileAusZeilenArray(Files.readAllLines(gebeJuengsteFileZurueck(filtereAlleCSVFilesHeraus(gebeFileListeZurueck(Strings.SPIELDATEIPFAD))).toPath()).toArray(new String[0]));
        } catch (Exception e)
        {
            return null;
        }

    }

    /**
     * Methode, die alle Files aus einem Stack zurueckgibt, die auf ".csv" enden. Dazu
     *
     * @param fileArray
     * @return
     * @author Felix Ahrens
     */
    public static Stack<File> filtereAlleCSVFilesHeraus (File[] fileArray)
    {
        Stack<File> csvStack = new Stack<>();
        for (File file : fileArray)
        {
            if (file.getName().endsWith(Strings.CSV_ENDUNG))
            {
                csvStack.push(file);
            }
        }
        return csvStack;
    }

    /**
     * Methode, die die File zurueckgibt, die zuletzt bearbeitet wurde. Dazu wird aus dem Stack ein Stream erzeugt, aus
     * dem dann mit "max" das maximale Element herausgefiltert wird, basierend auf dem angegebenen Comparator fuer long
     * aus lastModified fuer alle Files, das als long in ms den Zeitpunkt der letzten Bearbeitung zurueckgibt. Ist keine
     * File auf dem "dateiStack", wird "null" zurueckgegeben.
     *
     * @param dateiStack Der zu untersuchende Stack dessen juengste File gefragt ist.
     * @return die File, die auf dem dateiStack zuletzt bearbeitet wurde; oder null, wenn der Stack leer ist.
     * @precondititon Auf dem "dateiStack" muessen Files liegen.
     * @post Es wurde die File zurueckgegeben, die von allen im Stack zuletzt bearbeitet wurde.
     * @author Felix Ahrens
     */
    public static File gebeJuengsteFileZurueck (Stack<File> dateiStack)
    {
        File juengsteFile = dateiStack.stream()
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);
        return juengsteFile;
    }

    /**
     * Methode, die den Spielstand, also die Singleton-Instanz der GameFile, sofern gesetzt, als CSV-Datei schreiben
     * laesst.
     *
     * @pre Die Instanz muss gesetzt sein, damit der Spielstand erfolgreich gespeichert werden kann.#
     * @postcondtition Die dem "dateiPfadUndName" entsprechende csv-Datei entspricht den Werten der Singletoninstanz.
     * @Author Felix Ahrens
     */
    public static void speichereSpielstand ()
    {
        if (instanz == null)
        {
            MyIO.print(Strings.FEHLERMELDUNG_SPEICHERN);
            return;
        }
        schreibeGameFile(instanz);
    }

    /**
     * Methode, die die in der GameFile gespeicherten Charaktere als Array zurueckgibt.
     *
     * @return Als eindimensionales Array die fuenf in der GameFile gespeicherten Charaktere.
     * @pre Die GameFile muss auf einen Spielstand instanziiert sein. Die Charaktere muessen Parameter der Klasse
     * GameFile sein.
     * @post Es wurde ein Array, dass die fuenf Charaktere enthaelt, zurueckgegeben.
     * @Author Felix Ahrens
     */
    public Charakter[] gebeCharakterAlsArrayZurueck ()
    {
        return new Charakter[]{leader, medic, hunter, magician, scout};
    }

    /**
     * Ueberschriebene "toString"-methode der GameFile, die die gesamte Instanz der GameFile in einer fuer Menschen
     * einfach lesbaren Version zurueckgibt. Nutzen ist hauptsaechlich die Konsolenausgabe zu Debug-Zwecken.
     *
     * @return Als String eine menschlich einfach lesbare Version der Parameter der GameFile-Instanz
     * @pre Die gefragten Parameter muessen Parameter der GameFile sein und in der Klasse existieren.
     * @post Es wird ein String zurueckgegeben, der von Menschen einfach lesbar ist, mit Beschriftungen und
     * Zeilenumbruechen.
     * @author Felix Ahrens
     */
    @Override
    public String toString ()
    {
        return Strings.GAMEFILE + Strings.DOPPELPUNKT + Strings.NEWLINE +
                Strings.DATEINAME + Strings.DOPPELPUNKT + Strings.SPACE + dateiPfadUndName + Strings.NEWLINE +
                Strings.SCHWIERIGKEIT + Strings.DOPPELPUNKT + Strings.SPACE + schwierigkeit + Strings.NEWLINE +
                Strings.HOLZ + Strings.DOPPELPUNKT + Strings.SPACE + holzRessource + Strings.NEWLINE +
                Strings.STEIN + Strings.DOPPELPUNKT + Strings.SPACE + steinRessource + Strings.NEWLINE +
                Strings.GOLD + Strings.DOPPELPUNKT + Strings.SPACE + goldRessource + Strings.NEWLINE +
                Strings.GESUNDHEIT + Strings.DOPPELPUNKT + Strings.SPACE + gesundheitRessource + Strings.NEWLINE +
                Strings.BANONAS + Strings.DOPPELPUNKT + Strings.SPACE + banonasRessource + Strings.NEWLINE +
                leader + Strings.NEWLINE +
                medic + Strings.NEWLINE +
                hunter + Strings.NEWLINE +
                magician + Strings.NEWLINE +
                scout + Strings.NEWLINE +
                fraktionenCampFreigeschaltet + Strings.NEWLINE +
                trainingsgelaendeLevel + Strings.NEWLINE +
                magieverstaerkerLevel + Strings.NEWLINE;
    }

}
