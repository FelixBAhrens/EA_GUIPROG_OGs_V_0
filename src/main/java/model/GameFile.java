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
 *  dabei ein Singleton, da immer nur ein Spielstand zurzeit bespielt werden soll. Auf die (vorher gesetzte) Instanz
 *  kann von ueberall im Programm aus zugegriffen werden.
 * @author Felix Ahrens
 */
public class GameFile {
    /**
     * GameFile ist ein Singleton. Dies ist die einzige Instanz der Klasse GameFile. Sie muss zu Spielstart durch die
     *  Methode "setzeInstanz" gesetzt werden.
     * @author Felix Ahrens
     */
    private static GameFile instanz;

    /**
     * Getter fuer die Singleton-Instanz der Klasse GameFile,
     * @return Die einzige Instanz der Klasse GameFile, sofern diese vorher ueber "setzeInstanz" gesetzt wurde.
     * @throws Exception
     * @author Felix Ahrens
     */
    public static GameFile getInstanz() {
        if (instanz != null) {
            return instanz;
        }
        else {
            MyIO.print(Strings.FEHLERMELDUNG_GAMEFILE);
            return null;
        }
    }

    /**
     * Setter des Singletons der Klasse GameFile. Diese Methode muss bei Spielstart aufgerufen werden, um einen spielbaren
     *  Spielstand als Singleton zu setzen, damit Methoden von ueberall aus der Anwendung auf die Spieldateien zugreifen
     *  koennen.
     * @param gameFile Die Instanz der Klasse GameFile, die als Singleton gesetzt werden soll.
     * @precondition
     * @author Felix Ahrens
     */
    public static void setzeInstanz (GameFile gameFile){
        instanz = gameFile;
    }

    //Parameter der Klasse GameFile
    private String filePathAndName;
    private String schwierigkeit;
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

    /**
     * Getter fuer die Schwierigkeit. Gibt die Schwierigkeit zurueck.
     * @precondition Die Schwierigkeit muss als String in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable schwierigkeit als String zurueckgegeben.
     * @return Die Schwierigkeit als String.
     * @Author Felix Ahrens
     */
    public String getSchwierigkeit() {
        return schwierigkeit;
    }

    /**
     * Setter fuer die Schwierigkeit. Setzt die Schwierigkeit auf den der Methode als String uebergebenen Wert.
     * @precondition Die Schwierigkeit muss als String in der Klasse GameFile enthalten sein.
     * @postcondition Die in der Klasse gespeicherte Schwierigkeit stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param schwierigkeit Die Schwierigkeit als String, auf den die Klassenvariable "schwierigkeit" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setSchwierigkeit(String schwierigkeit) {
        this.schwierigkeit = schwierigkeit;
    }

    /**
     * Setter fuer den Dateipfad und Namen. Setzt den Dateipfad und Namen auf den der Methode als String uebergebenen Wert.
     * @precondition Der Dateipfad und Name muss als String in der Klasse GameFile enthalten sein.
     * @postcondition Der in der Klasse gespeicherte Dateipfad und Name stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param filePathAndName Der Dateipfad und Name als String, auf den die Klassenvariable "filePathAndName" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setFilePathAndName(String filePathAndName) {
        this.filePathAndName = filePathAndName;
    }

    /**
     * Getter fuer die HolzRessource. Gibt die HolzRessource zurueck.
     * @precondition Die HolzRessource muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable holzRessource als int zurueckgegeben.
     * @return Die HolzRessource als int.
     * @Author Felix Ahrens
     */
    public int getHolzRessource() {
        return holzRessource;
    }

    /**
     * Setter fuer die HolzRessource. Setzt die HolzRessource auf den der Methode als int uebergebenen Wert.
     * @precondition Die HolzRessource muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Die in der Klasse gespeicherte HolzRessource stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param holzRessource Die HolzRessource als int, auf den die Klassenvariable "holzRessource" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setHolzRessource(int holzRessource) {
        this.holzRessource = holzRessource;
    }

    /**
     * Getter fuer die SteinRessource. Gibt die SteinRessource zurueck.
     * @precondition Die SteinRessource muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable steinRessource als int zurueckgegeben.
     * @return Die SteinRessource als int.
     * @Author Felix Ahrens
     */
    public int getSteinRessource() {
        return steinRessource;
    }

    /**
     * Setter fuer die SteinRessource. Setzt die SteinRessource auf den der Methode als int uebergebenen Wert.
     * @precondition Die SteinRessource muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Die in der Klasse gespeicherte SteinRessource stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param steinRessource Die SteinRessource als int, auf den die Klassenvariable "steinRessource" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setSteinRessource(int steinRessource) {
        this.steinRessource = steinRessource;
    }

    /**
     * Getter fuer die GoldRessource. Gibt die GoldRessource zurueck.
     * @precondition Die GoldRessource muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable goldRessource als int zurueckgegeben.
     * @return Die GoldRessource als int.
     * @Author Felix Ahrens
     */
    public int getGoldRessource() {
        return goldRessource;
    }

    /**
     * Setter fuer die GoldRessource. Setzt die GoldRessource auf den der Methode als int uebergebenen Wert.
     * @precondition Die GoldRessource muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Die in der Klasse gespeicherte GoldRessource stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param goldRessource Die GoldRessource als int, auf den die Klassenvariable "goldRessource" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setGoldRessource(int goldRessource) {
        this.goldRessource = goldRessource;
    }

    /**
     * Getter fuer die GesundheitRessource. Gibt die GesundheitRessource zurueck.
     * @precondition Die GesundheitRessource muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable gesundheitRessource als int zurueckgegeben.
     * @return Die GesundheitRessource als int.
     * @Author Felix Ahrens
     */
    public int getGesundheitRessource() {
        return gesundheitRessource;
    }

    /**
     * Setter fuer die GesundheitRessource. Setzt die GesundheitRessource auf den der Methode als int uebergebenen Wert.
     * @precondition Die GesundheitRessource muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Die in der Klasse gespeicherte GesundheitRessource stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param gesundheitRessource Die GesundheitRessource als int, auf den die Klassenvariable "gesundheitRessource" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setGesundheitRessource(int gesundheitRessource) {
        this.gesundheitRessource = gesundheitRessource;
    }

    /**
     * Getter fuer die BanonasRessource. Gibt die BanonasRessource zurueck.
     * @precondition Die BanonasRessource muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable banonasRessource als int zurueckgegeben.
     * @return Die BanonasRessource als int.
     * @Author Felix Ahrens
     */
    public int getBanonasRessource() {
        return banonasRessource;
    }

    /**
     * Setter fuer die BanonasRessource. Setzt die BanonasRessource auf den der Methode als int uebergebenen Wert.
     * @precondition Die BanonasRessource muss als Integer in der Klasse GameFile enthalten sein.
     * @postcondition Die in der Klasse gespeicherte BanonasRessource stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param banonaRessource Die BanonasRessource als int, auf den die Klassenvariable "banonasRessource" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setBanonasRessource(int banonaRessource) {
        this.banonasRessource = banonaRessource;
    }

    /**
     * Getter fuer die Statue. Gibt die Statue zurueck.
     * @precondition Die Statue muss als Artefakt in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable statue als Artefakt zurueckgegeben.
     * @return Die Statue als Artefakt.
     * @Author Felix Ahrens
     */
    public Artefakt getStatue() {
        return statue;
    }

    /**
     * Setter fuer die Statue. Setzt die Statue auf den der Methode als Artefakt uebergebenen Wert.
     * @precondition Die Statue muss als Artefakt in der Klasse GameFile enthalten sein.
     * @postcondition Die in der Klasse gespeicherte Statue stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param statue Die Statue als Artefakt, auf den die Klassenvariable "statue" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setStatue (Artefakt statue) {
        this.statue = statue;
    }

    /**
     * Getter fuer den Ring. Gibt den Ring zurueck.
     * @precondition Der Ring muss als Artefakt in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable ring als Artefakt zurueckgegeben.
     * @return Der Ring als Artefakt.
     * @Author Felix Ahrens
     */
    public Artefakt getRing() {
        return ring;
    }

    /**
     * Setter fuer den Ring. Setzt den Ring auf den der Methode als Artefakt uebergebenen Wert.
     * @precondition Der Ring muss als Artefakt in der Klasse GameFile enthalten sein.
     * @postcondition Der in der Klasse gespeicherte Ring stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param ring Der Ring als Artefakt, auf den die Klassenvariable "ring" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setRing(Artefakt ring) {
        this.ring = ring;
    }

    /**
     * Getter fuer das Schwert. Gibt das Schwert zurueck.
     * @precondition Das Schwert muss als Artefakt in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable schwert als Artefakt zurueckgegeben.
     * @return Das Schwert als Artefakt.
     * @Author Felix Ahrens
     */
    public Artefakt getSchwert() {
        return schwert;
    }

    /**
     * Setter fuer das Schwert. Setzt das Schwert auf den der Methode als Artefakt uebergebenen Wert.
     * @precondition Das Schwert muss als Artefakt in der Klasse GameFile enthalten sein.
     * @postcondition Das in der Klasse gespeicherte Schwert stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param schwert Das Schwert als Artefakt, auf den die Klassenvariable "schwert" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setSchwert(Artefakt schwert) {
        this.schwert = schwert;
    }

    /**
     * Getter fuer den Leader. Gibt den Leader zurueck.
     * @precondition Der Leader muss als Charakter in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable leader als Charakter zurueckgegeben.
     * @return Der Leader als Charakter.
     * @Author Felix Ahrens
     */
    public Charakter getLeader() {
        return leader;
    }

    /**
     * Setter fuer den Leader. Setzt den Leader auf den der Methode als Charakter uebergebenen Wert.
     * @precondition Der Leader muss als Charakter in der Klasse GameFile enthalten sein.
     * @postcondition Der in der Klasse gespeicherte Leader stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param leader Der Leader als Charakter, auf den die Klassenvariable "leader" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setLeader(Charakter leader) {
        this.leader = leader;
    }

    /**
     * Getter fuer den Medic. Gibt den Medic zurueck.
     * @precondition Der Medic muss als Charakter in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable medic als Charakter zurueckgegeben.
     * @return Der Medic als Charakter.
     * @Author Felix Ahrens
     */
    public Charakter getMedic() {
        return medic;
    }

    /**
     * Setter fuer den Medic. Setzt den Medic auf den der Methode als Charakter uebergebenen Wert.
     * @precondition Der Medic muss als Charakter in der Klasse GameFile enthalten sein.
     * @postcondition Der in der Klasse gespeicherte Medic stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param medic Der Medic als Charakter, auf den die Klassenvariable "medic" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setMedic(Charakter medic) {
        this.medic = medic;
    }

    /**
     * Getter fuer den Hunter. Gibt den Hunter zurueck.
     * @precondition Der Hunter muss als Charakter in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable hunter als Charakter zurueckgegeben.
     * @return Der Hunter als Charakter.
     * @Author Felix Ahrens
     */
    public Charakter getHunter() {
        return hunter;
    }

    /**
     * Setter fuer den Hunter. Setzt den Hunter auf den der Methode als Charakter uebergebenen Wert.
     * @precondition Der Hunter muss als Charakter in der Klasse GameFile enthalten sein.
     * @postcondition Der in der Klasse gespeicherte Hunter stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param hunter Der Hunter als Charakter, auf den die Klassenvariable "hunter" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setHunter(Charakter hunter) {
        this.hunter = hunter;
    }

    /**
     * Getter fuer den Magician. Gibt den Magician zurueck.
     * @precondition Der Magician muss als Charakter in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable magician als Charakter zurueckgegeben.
     * @return Der Magician als Charakter.
     * @Author Felix Ahrens
     */
    public Charakter getMagician() {
        return magician;
    }

    /**
     * Setter fuer den Magician. Setzt den Magician auf den der Methode als Charakter uebergebenen Wert.
     * @precondition Der Magician muss als Charakter in der Klasse GameFile enthalten sein.
     * @postcondition Der in der Klasse gespeicherte Magician stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param magician Der Magician als Charakter, auf den die Klassenvariable "magician" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setMagician(Charakter magician) {
        this.magician = magician;
    }

    /**
     * Getter fuer den Scout. Gibt den Scout zurueck.
     * @precondition Der Scout muss als Charakter in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable scout als Charakter zurueckgegeben.
     * @return Der Scout als Charakter.
     * @Author Felix Ahrens
     */
    public Charakter getScout() {
        return scout;
    }

    /**
     * Setter fuer den Scout. Setzt den Scout auf den der Methode als Charakter uebergebenen Wert.
     * @precondition Der Scout muss als Charakter in der Klasse GameFile enthalten sein.
     * @postcondition Der in der Klasse gespeicherte Scout stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param scout Der Scout als Charakter, auf den die Klassenvariable "scout" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setScout(Charakter scout) {
        this.scout = scout;
    }

    /**
     * Getter fuer den Dateipfad und Namen. Gibt den Dateipfad und Namen zurueck.
     * @precondition Der Dateipfad und Name muss als String in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable filePathAndName als String zurueckgegeben.
     * @return Der Dateipfad und Name als String.
     * @Author Felix Ahrens
     */
    public String getFilePathAndName() {
        return filePathAndName;
    }

    /**
     * Ueberprueft, ob das FraktionenCamp freigeschaltet ist.
     * @precondition Der Status des FraktionenCamps muss als boolean in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable fraktionenCampFreigeschaltet als boolean zurueckgegeben.
     * @return Der Status des FraktionenCamps als boolean.
     * @Author Felix Ahrens
     */
    public boolean fraktionenCampIstFreigeschaltet() {
        return fraktionenCampFreigeschaltet;
    }

    /**
     * Setter fuer den Status des FraktionenCamps. Setzt den Status des FraktionenCamps auf den der Methode als boolean uebergebenen Wert.
     * @precondition Der Status des FraktionenCamps muss als boolean in der Klasse GameFile enthalten sein.
     * @postcondition Der in der Klasse gespeicherte Status des FraktionenCamps stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param fraktionenCampFreigeschaltet Der Status des FraktionenCamps als boolean, auf den die Klassenvariable "fraktionenCampFreigeschaltet" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setFraktionenCampFreigeschaltet(boolean fraktionenCampFreigeschaltet) {
        this.fraktionenCampFreigeschaltet = fraktionenCampFreigeschaltet;
    }

    /**
     * Getter fuer das TrainingsgelaendeLevel. Gibt das TrainingsgelaendeLevel zurueck.
     * @precondition Das TrainingsgelaendeLevel muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable trainingsgelaendeLevel als int zurueckgegeben.
     * @return Das TrainingsgelaendeLevel als int.
     * @Author Felix Ahrens
     */
    public int getTrainingsgelaendeLevel() {
        return trainingsgelaendeLevel;
    }

    /**
     * Setter fuer das TrainingsgelaendeLevel. Setzt das TrainingsgelaendeLevel auf den der Methode als int uebergebenen Wert.
     * @precondition Das TrainingsgelaendeLevel muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Das in der Klasse gespeicherte TrainingsgelaendeLevel stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param trainingsgelaendeLevel Das TrainingsgelaendeLevel als int, auf den die Klassenvariable "trainingsgelaendeLevel" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setTrainingsgelaendeLevel(int trainingsgelaendeLevel) {
        this.trainingsgelaendeLevel = trainingsgelaendeLevel;
    }

    /**
     * Getter fuer das MagieverstaerkerLevel. Gibt das MagieverstaerkerLevel zurueck.
     * @precondition Das MagieverstaerkerLevel muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Es wurde der Inhalt der Variable magieverstaerkerLevel als int zurueckgegeben.
     * @return Das MagieverstaerkerLevel als int.
     * @Author Felix Ahrens
     */
    public int getMagieverstaerkerLevel() {
        return magieverstaerkerLevel;
    }

    /**
     * Setter fuer das MagieverstaerkerLevel. Setzt das MagieverstaerkerLevel auf den der Methode als int uebergebenen Wert.
     * @precondition Das MagieverstaerkerLevel muss als int in der Klasse GameFile enthalten sein.
     * @postcondition Das in der Klasse gespeicherte MagieverstaerkerLevel stimmt mit dem Parameter ueberein, der der Methode
     *  beim Aufruf uebergeben wurde.
     * @param magieverstaerkerLevel Das MagieverstaerkerLevel als int, auf den die Klassenvariable "magieverstaerkerLevel" gesetzt werden soll.
     * @Author Felix Ahrens
     */
    public void setMagieverstaerkerLevel(int magieverstaerkerLevel) {
        this.magieverstaerkerLevel = magieverstaerkerLevel;
    }

    /**
     * Default Konstruktor von GameFile
     * @Author Felix Ahrens
     */
    public GameFile () {
    }

    /**
     * Konstruktor der Klasse GameFile. Bekommt alle Werte, mit der die GameFile erstellt wird, uebergeben.
     *  Gibt eine neue Instanz der Klasse GameFile zurueck.
     * @precondition Die Datentypen der uebergebenen Parameter muessen in der Reihenfolge mit denen der Instanzvariablen
     *  uebereinstimmen. Die Variablen muessen in der Klasse existieren.
     * @postcondition Es wurde eine neue Instanz der Klasse GameFile mit den dem Konstruktor uebergebenen Werten
     *  erstellt und zurueckgegeben.
     * @param filePathAndName Dateipfad- und name, um auf den Dateipfad der CSV-Datei der GameFile zugreifen zu koennen
     * @param schwierigkeit Die Schwierigkeit des Spielstands
     * @param holzRessource Die Anzahl an Holzressourcen des Spielstands
     * @param steinRessource Die Anzahl an Steinressourcen des Spielstandes
     * @param goldRessource Die Anzahl an Goldressourcen des Spielstands
     * @param gesundheitRessource Die Anzahl an Gesundheitsressourcen des Spielstands
     * @param banonasRessource Die Anzahl an Banonasressourcen des Spielstandes
     * @param statue Die Instanz "statue" der Klasse "Artefakt" des Spielstands
     * @param schwert Die Instanz "schwert" der Klasse "Artefakt" des Spielstands
     * @param ring Die Instanz "ring" der Klasse "Artefakt" des Spielstands
     * @param leader Die Instanz "leader" der Klasse "Charakter" des Spielstands
     * @param medic Die Instanz "medic" der Klasse "Charakter" des Spielstands
     * @param hunter Die Instanz "hunter" der Klasse "Charakter" des Spielstands
     * @param magician Die Instanz "magician" der Klasse "Charakter" des Spielstands
     * @param scout Die Instanz "scout" der Klasse "Charakter" des Spielstands
     * @param fraktionenCampFreigeschaltet Der boolesche Wert, ob das Fraktionencamp im Spiel freigeschaltet wurde
     * @param trainingsgelaendeLevel Das Level, in dem sich das verbesserbare Trainingsgelaende befindet
     * @param magieverstaerkerLevel Das Level, in dem sich der verbesserbare Magieverstaerker befindet
     * @Author Felix Ahrens
     */
    private GameFile(String filePathAndName, String schwierigkeit, int holzRessource, int steinRessource, int goldRessource, int gesundheitRessource, int banonasRessource, Artefakt statue, Artefakt schwert, Artefakt ring, Charakter leader, Charakter medic, Charakter hunter,
                     Charakter magician, Charakter scout, boolean fraktionenCampFreigeschaltet, int trainingsgelaendeLevel, int magieverstaerkerLevel) {
        this.filePathAndName = filePathAndName;
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
     *  werden die Standartwerte aus der jeweiligen Klasse verwendet, etwa bei Charakteren oder Artefakten.
     * @precondition Der Konstruktor der GameFile muss existieren und die uebergebenen Werte in der gleichen Reihenfolge
     *  "akzeptieren" und verwenden koennen. Die der Methode uebergebenen Werte muessen Strings fuer den Spielnamen und
     *  die Schwierigkeit sein
     * @return Eine neue, mit den Default-Werten erstellte Instanz der Klasse GameFile
     * @Author Felix Ahrens
     */
    public static GameFile erstelleNeueDefaultGameFile(String spielName, String schwierigkeit)
    {
        if (spielName.length()<Konstanten.INT_ONE)
        {
            spielName = Strings.SPIEL + (gebeFileListeZurueck(Strings.SPIELDATEIPFAD).length-1);
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

    public static void schreibeGameFile(GameFile gameFile) {
        try{
            FileWriter dateiSchreiber = new FileWriter(gameFile.filePathAndName);
            dateiSchreiber.write(gameFile.filePathAndName + Strings.NEWLINE + gameFile.schwierigkeit + Strings.NEWLINE);
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
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Methode, die eine bestimmte gespeicherte GameFile lesen kann.
     * @return
     * @Author Felix Ahrens
     */
    public static GameFile leseGameFile(String fileName){
        if (!fileName.contains(Strings.CSV_ENDUNG)){
            fileName = fileName + Strings.CSV_ENDUNG;
        }
        if (!fileName.contains(Strings.SPIELDATEIPFAD)){
            fileName = Strings.SPIELDATEIPFAD + fileName;
        }
        return macheGameFileAusZeilenArray(leseCSV(fileName));
    }

    /**
     * Methode, die eine CSV-Datei einliest und diese als String-Array, in welcher der CSV-Inhalt zeilenweise
     *  gespeichert ist, zurueckgibt.
     * @param filePathAndName Der Dateipfad und -name der zu einlesenden CSV-Datei.
     * @return String[] Das String-Array, das die Zeilen der CSV-Datei beinhaltet.
     * @author Felix Ahrens
     */
    public static String[] leseCSV(String filePathAndName) {
        System.out.println(filePathAndName);
        List<String> lines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePathAndName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.toArray(new String[0]);
    }

    /**
     * Methode, die aus dem Inhalt eines String-Arrays eine GameFile macht.
     *  GameFiles sollen noch mehr Daten als nur die Beispieldaten Name und Datum enthalten. Dafuer braucht es aber auch
     *  noch einen selbst erstellten Standard zur Reihenfolge.
     * @return
     * @Author Felix Ahrens
     */
    public static GameFile macheGameFileAusZeilenArray(String[] zeilenArray){
        System.out.println(zeilenArray[11]);
        try{
            return new GameFile(zeilenArray[Konstanten.INT_ZERO],
                    zeilenArray[Konstanten.INT_ONE],
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
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Methode, die alle Files in dem angegebenen Dateipfad als Array zurueckgibt.
     * @return
     * @Author Felix Ahrens
     */
    public static File[] gebeFileListeZurueck (String dateiPfad){
        File gameFileVerzeichnis = new File(dateiPfad);
        return gameFileVerzeichnis.listFiles();
    }

    /**
     * Methode, die eine Zeile zu einem Artefakt parsed
     * @param zeile
     * @return
     * @author Felix Ahrens
     */
    public static Artefakt erstelleArtefaktAusCSVZeile(String zeile){
        String[] zeilenStuecke = zeile.split(Strings.SEMIKOLON);
        return new Artefakt(zeilenStuecke[Konstanten.INT_ZERO],
                Boolean.parseBoolean(zeilenStuecke[Konstanten.INT_ONE]),
                Integer.parseInt(zeilenStuecke[Konstanten.INT_TWO]),
                Integer.parseInt(zeilenStuecke[Konstanten.INT_THREE]));
    }

    /**
     * Methode, die eine Zeile zu einem Charakter parsed
     * @param zeile
     * @return
     * @author Felix Ahrens
     */
    public static Charakter erstelleCharakterAusCSVZeile(String zeile){
        Integer[] zeilenStuecke = Arrays.stream(zeile.split(Strings.DOPPELPUNKT)[Konstanten.INT_ONE].split(Strings.SEMIKOLON))
                .map(String::trim)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        return new Charakter(zeile.split(Strings.DOPPELPUNKT)[Konstanten.INT_ZERO], zeilenStuecke[Konstanten.INT_ZERO],
                zeilenStuecke[Konstanten.INT_ONE], zeilenStuecke[Konstanten.INT_TWO], zeilenStuecke[Konstanten.INT_THREE],
                zeilenStuecke[Konstanten.INT_FOUR], zeilenStuecke[Konstanten.INT_FIVE], zeilenStuecke[Konstanten.INT_SIX],
                zeilenStuecke[Konstanten.INT_SEVEN], zeilenStuecke[Konstanten.INT_EIGHT], zeilenStuecke[Konstanten.INT_NINE]);
    }

    /**
     * Methode, die die GameFile zurueckgibt, die zuletzt modifiziert (also bespielt) wurde.
     * @return die GameFile, die zuletzt bespielt wurde
     * @throws IOException
     * @author Felix Ahrens
     */
    public static GameFile gebeLetztesSpielZurueck() {
        try {
            return macheGameFileAusZeilenArray(Files.readAllLines(gebeJuengsteFileZurueck(filtereAlleCSVFiles(gebeFileListeZurueck(Strings.SPIELDATEIPFAD))).toPath()).toArray(new String[0]));
        }
        catch (Exception e){
            return null;
        }

    }



    /**
     * Methode, die alle Files aus einem Stack zurueckgibt, die auf ".csv" enden.
     * @param fileArray
     * @return
     * @author Felix Ahrens
     */
    public static Stack<File> filtereAlleCSVFiles (File[] fileArray){
        Stack<File> csvStack = new Stack<>();
        for (File file : fileArray) {
            if (file.getName().endsWith(Strings.CSV_ENDUNG)) {
                csvStack.push(file);
            }
        }
        return csvStack;
    }

    /**
     * Methode, die die File zurueckgibt, die zuletzt bearbeitet wurde
     * @param fileStack
     * @return
     * @author Felix Ahrens
     */
    public static File gebeJuengsteFileZurueck (Stack<File> fileStack) {
        File juengsteFile = fileStack.stream()
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);
        return juengsteFile;
    }

    public static void speichereSpielstand() {
        if (instanz == null){
            System.out.println(Strings.FEHLERMELDUNG_SPEICHERN);
            return;
        }
        schreibeGameFile(instanz);
    }

    @Override
    public String toString() {
        return Strings.GAMEFILE + Strings.DOPPELPUNKT + Strings.NEWLINE +
                Strings.DATEINAME + Strings.DOPPELPUNKT + Strings.SPACE + filePathAndName + Strings.NEWLINE +
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
