package model;

import control.CharakterController;
import res.Konstanten;
import res.Strings;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

/**
 * GameFile habe ich mir ausgedacht, um die Spielstaende zu speichern. Instanzen der Klasse GameFile sind Spielstaende.
 *  Ich habe bisher erst den DateiNamen und das Erstelldatum als Parameter. Wie in der PROG-EA werden diese in einer
 *  CSV-Datei gespeichert. Diese Klasse beinhaltet diverse Methoden um GameFiles u.A. zu Lesen, zu bearbeiten etc..
 * @Author Felix Ahrens
 */
public class GameFile extends File {
    // Singleton der Gamefile -----------------------------
    private static GameFile instance;

    public static GameFile getInstance() throws Exception {
        if (instance != null) {
            return instance;
        }
        else {
            throw new Exception(Strings.FEHLERMELDUNG_GAMEFILE);
        }
    }
    public static void setzeGameFile(GameFile gameFile){
        instance = gameFile;
    }

    //------------------------------------------------------

    private String fileName;
    private String schwierigkeit;
    private int holzRessource;
    private int goldRessource;
    private int gesundheitRessource;
    private Charakter leader;
    private Charakter medic;
    private Charakter hunter;
    private Charakter magician;
    private Charakter scout;

    public String getSchwierigkeit() {
        return schwierigkeit;
    }

    public void setSchwierigkeit(String schwierigkeit) {
        this.schwierigkeit = schwierigkeit;
    }

    public static void setInstance(GameFile instance) {
        GameFile.instance = instance;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getHolzRessource() {
        return holzRessource;
    }

    public void setHolzRessource(int holzRessource) {
        this.holzRessource = holzRessource;
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

    public String getFileName() {return fileName;}


    private GameFile(String dateipfad, String fileName, String schwierigkeit, int holzRessource, int goldRessource, int gesundheitRessource, Charakter leader, Charakter medic, Charakter hunter,
                     Charakter magician, Charakter scout) {
        super (dateipfad);
        this.fileName = fileName;
        this.schwierigkeit = schwierigkeit;
        this.holzRessource = holzRessource;
        this.goldRessource = goldRessource;
        this.gesundheitRessource = gesundheitRessource;
        this.leader = leader;
        this.medic = medic;
        this.hunter = hunter;
        this.magician = magician;
        this.scout = scout;
    }


    /**
     * Methode, die eine neue GameFile mit den ihr uebergebenen Parametern erstellt.
     *  Die Parameterliste ist allerdings noch lange nicht vollstaendig.
     * @return
     * @throws IOException
     * @Author Felix Ahrens
     */
    public static GameFile erstelleNeueGameFile(String schwierigkeit) throws IOException {
        String spielName = Strings.SPIEL + (gebeFileListeZurueck(Strings.SPIELDATEIPFAD).length-1);
        String spielPfad_Name = Strings.SPIELDATEIPFAD + spielName + Strings.CSV_ENDUNG;
        try{
            FileWriter dateiSchreiber = new FileWriter(spielPfad_Name);
            dateiSchreiber.write(spielName + Strings.NEWLINE + schwierigkeit + Strings.NEWLINE);
            dateiSchreiber.write(Konstanten.DEFAULT_VALUE_HOLZ + Strings.SEMIKOLON + Konstanten.DEFAULT_VALUE_GOLD + Strings.SEMIKOLON + Konstanten.DEFAULT_VALUE_GESUNDHEIT + Strings.NEWLINE);
            CharakterController.erstelleDefaultCharakter();
            Charakter[] charakterArray = CharakterController.getCharakterArray();
            dateiSchreiber.write(charakterArray[Konstanten.INT_ZERO]+Strings.NEWLINE);
            dateiSchreiber.write(charakterArray[Konstanten.INT_ONE]+Strings.NEWLINE);
            dateiSchreiber.write(charakterArray[Konstanten.INT_TWO]+Strings.NEWLINE);
            dateiSchreiber.write(charakterArray[Konstanten.INT_THREE]+Strings.NEWLINE);
            dateiSchreiber.write(charakterArray[Konstanten.INT_FOUR]+Strings.NEWLINE);
            dateiSchreiber.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return leseGameFile(spielPfad_Name);
    }

    /**
     * Methode, die eine bestimmte gespeicherte GameFile lesen kann.
     * @param fileName
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
        try{
            return new GameFile(Strings.SPIELDATEIPFAD + zeilenArray[Konstanten.INT_ZERO] + Strings.CSV_ENDUNG,
                    zeilenArray[Konstanten.INT_ZERO],
                    zeilenArray[Konstanten.INT_ONE],
                    Integer.parseInt(zeilenArray[Konstanten.INT_TWO].split(Strings.SEMIKOLON)[Konstanten.INT_ZERO]),
                    Integer.parseInt(zeilenArray[Konstanten.INT_TWO].split(Strings.SEMIKOLON)[Konstanten.INT_ONE]),
                    Integer.parseInt(zeilenArray[Konstanten.INT_TWO].split(Strings.SEMIKOLON)[Konstanten.INT_TWO]),
                    erstelleCharakterAusCSVZeile(zeilenArray[Konstanten.INT_THREE]), erstelleCharakterAusCSVZeile(zeilenArray[Konstanten.INT_FOUR]),
                    erstelleCharakterAusCSVZeile(zeilenArray[Konstanten.INT_FIVE]), erstelleCharakterAusCSVZeile(zeilenArray[Konstanten.INT_SIX]), erstelleCharakterAusCSVZeile(zeilenArray[Konstanten.INT_SEVEN]));
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Methode, die alle
     * @return
     * @Author Felix Ahrens
     */
    public static File[] gebeFileListeZurueck(String dateiPfad){
        File gameFileVerzeichnis = new File(dateiPfad);
        return gameFileVerzeichnis.listFiles();
    }

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
     *
     */
    public static GameFile gebeLetztesSpielZurueck() throws IOException {
        return macheGameFileAusZeilenArray(Files.readAllLines(gebeJuengsteFileZurueck(sortiereAlleCSVFiles(gebeFileListeZurueck(Strings.SPIELDATEIPFAD))).toPath()).toArray(new String[0]));
    }

    public static Stack<File> sortiereAlleCSVFiles (File[] fileArray){
        Stack<File> csvStack = new Stack<>();
        for (File file : fileArray) {
            if (file.getName().endsWith(Strings.CSV_ENDUNG)) {
                csvStack.push(file);
            }
        }
        return csvStack;
    }

    public static File gebeJuengsteFileZurueck (Stack<File> fileStack) {
        File latestCSVFile = fileStack.stream()
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);
        return latestCSVFile;
    }

    public static void speichereSpielstand() {
        if (instance == null){
            System.out.println(Strings.FEHLERMELDUNG_SPEICHERN);
            return;
        }
        try{
            FileWriter dateiSchreiber = new FileWriter(Strings.SPIELDATEIPFAD + instance.fileName + Strings.CSV_ENDUNG);
            dateiSchreiber.write(instance.fileName + Strings.NEWLINE + instance.schwierigkeit + Strings.NEWLINE);
            dateiSchreiber.write(instance.holzRessource + Strings.SEMIKOLON + instance.goldRessource + Strings.SEMIKOLON + instance.gesundheitRessource + Strings.NEWLINE);
            dateiSchreiber.write(instance.leader+Strings.NEWLINE + instance.medic + Strings.NEWLINE + instance.hunter +Strings.NEWLINE + instance.magician +Strings.NEWLINE + instance.scout);
            dateiSchreiber.close();
            System.out.println(leseCSV(Strings.SPIELDATEIPFAD + instance.fileName).toString());
        }
        catch (IOException e){
            System.out.println(Strings.FEHLERMELDUNG_SPEICHERN);
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return Strings.GAMEFILE + Strings.DOPPELPUNKT + Strings.NEWLINE +
                Strings.DATEINAME + Strings.DOPPELPUNKT + Strings.SPACE + fileName + Strings.NEWLINE +
                Strings.SCHWIERIGKEIT + Strings.DOPPELPUNKT + Strings.SPACE + schwierigkeit + Strings.NEWLINE +
                Strings.HOLZ + Strings.DOPPELPUNKT + Strings.SPACE + holzRessource + Strings.NEWLINE +
                Strings.GOLD + Strings.DOPPELPUNKT + Strings.SPACE + goldRessource + Strings.NEWLINE +
                Strings.GESUNDHEIT + Strings.DOPPELPUNKT + Strings.SPACE + gesundheitRessource + Strings.NEWLINE +
                leader + Strings.NEWLINE +
                medic + Strings.NEWLINE +
                hunter + Strings.NEWLINE +
                magician + Strings.NEWLINE +
                scout + Strings.NEWLINE +
                Strings.GESCHWEIFTE_KLAMMER_ZU;
    }

}
