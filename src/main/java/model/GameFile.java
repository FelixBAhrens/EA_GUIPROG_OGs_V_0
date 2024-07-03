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
 * GameFile habe ich mir ausgedacht, um die Spielstaende zu speichern. Instanzen der Klasse GameFile sind Spielstaende.
 *  Ich habe bisher erst den DateiNamen und das Erstelldatum als Parameter. Wie in der PROG-EA werden diese in einer
 *  CSV-Datei gespeichert. Diese Klasse beinhaltet diverse Methoden um GameFiles u.A. zu Lesen, zu bearbeiten etc..
 * @author Felix Ahrens
 */
public class GameFile {

    // Singleton der Gamefile -----------------------------
    /**
     * Die einzelne Instanz der Klasse GameFile
     * @author Felix Ahrens
     */
    private static GameFile instance;

    /**
     * Getter fuer die Singleton-Instanz der Klasse GameFile
     * @return
     * @throws Exception
     * @author Felix Ahrens
     */
    public static GameFile getInstance() {
        if (instance != null) {
            return instance;
        }
        else {
            MyIO.print(Strings.FEHLERMELDUNG_GAMEFILE);
            return null;
        }
    }

    /**
     * Setter des Singletons der Klasse GameFile.
     * @param gameFile
     * @author Felix Ahrens
     */
    public static void setzeGameFile(GameFile gameFile){
        instance = gameFile;
    }

    //------------------------------------------------------
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

    public String getSchwierigkeit() {
        return schwierigkeit;
    }

    public void setSchwierigkeit(String schwierigkeit) {
        this.schwierigkeit = schwierigkeit;
    }

    public static void setInstance(GameFile instance) {
        GameFile.instance = instance;
    }

    public void setFilePathAndName(String filePathAndName) {
        this.filePathAndName = filePathAndName;
    }

    public int getHolzRessource() {
        return holzRessource;
    }

    public void setHolzRessource(int holzRessource) {
        this.holzRessource = holzRessource;
    }

    public int getSteinRessource() {
        return steinRessource;
    }

    public void setSteinRessource(int steinRessource) {
        this.steinRessource = steinRessource;
    }

    public int getGoldRessource() {
        return goldRessource;
    }

    public void setGoldRessource(int goldRessource) {
        this.goldRessource = goldRessource;
    }

    public int getGesundheitRessource() {
        return gesundheitRessource;
    }

    public void setGesundheitRessource(int gesundheitRessource) {
        this.gesundheitRessource = gesundheitRessource;
    }

    public int getBanonasRessource() {
        return banonasRessource;
    }

    public void setBanonasRessource(int banonaRessource) {
        this.banonasRessource = banonaRessource;
    }

    public Artefakt getStatue() {
        return statue;
    }

    public void setStatue(Artefakt statue) {
        this.statue = statue;
    }

    public Artefakt getRing() {
        return ring;
    }

    public void setRing(Artefakt ring) {
        this.ring = ring;
    }

    public Artefakt getSchwert() {
        return schwert;
    }

    public void setSchwert(Artefakt schwert) {
        this.schwert = schwert;
    }

    public Charakter getLeader() {
        return leader;
    }

    public void setLeader(Charakter leader) {
        this.leader = leader;
    }

    public Charakter getMedic() {
        return medic;
    }

    public void setMedic(Charakter medic) {
        this.medic = medic;
    }

    public Charakter getHunter() {
        return hunter;
    }

    public void setHunter(Charakter hunter) {
        this.hunter = hunter;
    }

    public Charakter getMagician() {
        return magician;
    }

    public void setMagician(Charakter magician) {
        this.magician = magician;
    }

    public Charakter getScout() {
        return scout;
    }

    public void setScout(Charakter scout) {
        this.scout = scout;
    }

    public String getFilePathAndName() {return filePathAndName;}

    public boolean fraktionenCampIstFreigeschaltet() {
        return fraktionenCampFreigeschaltet;
    }

    public void setFraktionenCampFreigeschaltet(boolean fraktionenCampFreigeschaltet) {
        this.fraktionenCampFreigeschaltet = fraktionenCampFreigeschaltet;
    }

    public int getTrainingsgelaendeLevel() {
        return trainingsgelaendeLevel;
    }

    public void setTrainingsgelaendeLevel(int trainingsgelaendeLevel) {
        this.trainingsgelaendeLevel = trainingsgelaendeLevel;
    }

    public int getMagieverstaerkerLevel() {
        return magieverstaerkerLevel;
    }

    public void setMagieverstaerkerLevel(int magieverstaerkerLevel) {
        this.magieverstaerkerLevel = magieverstaerkerLevel;
    }

    public GameFile () {
    }

    /**
     *
     * @param filePathAndName
     * @param schwierigkeit
     * @param holzRessource
     * @param goldRessource
     * @param gesundheitRessource
     * @param statue
     * @param schwert
     * @param ring
     * @param leader
     * @param medic
     * @param hunter
     * @param magician
     * @param scout
     * @param fraktionenCampFreigeschaltet
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
     * Methode, die eine neue GameFile mit den ihr uebergebenen Parametern erstellt.
     * @return Eine neue, mit den Default-Werten erstellte Instanz der Klasse GameFile
     * @throws IOException
     * @Author Felix Ahrens
     */
    public static GameFile erstelleNeueGameFile (String spielName, String schwierigkeit)
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
        if (instance == null){
            System.out.println(Strings.FEHLERMELDUNG_SPEICHERN);
            return;
        }
        schreibeGameFile(instance);
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
