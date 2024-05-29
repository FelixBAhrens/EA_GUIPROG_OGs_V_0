package model;

import control.CharakterController;
import res.Strings;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**
 * GameFile habe ich mir ausgedacht, um die Spielstaende zu speichern. Instanzen der Klasse GameFile sind Spielstaende.
 *  Ich habe bisher erst den DateiNamen und das Erstelldatum als Parameter. Wie in der PROG-EA werden diese in einer
 *  CSV-Datei gespeichert. Diese Klasse beinhaltet diverse Methoden um GameFiles u.A. zu Lesen, zu bearbeiten etc..
 * @Author Felix Ahrens
 */
public class GameFile extends File {
    private String fileName;
    private Charakter charakter1;
    private Charakter charakter2;
    private Charakter charakter3;
    private Charakter charakter4;
    private Charakter charakter5;
    public String getFileName() {return fileName;}


    public GameFile(String dateipfad, String fileName, Charakter charakter1, Charakter charakter2, Charakter charakter3,
                    Charakter charakter4, Charakter charakter5) {
        super (dateipfad);
        this.fileName = fileName;
        this.charakter1 = charakter1;
        this.charakter2 = charakter2;
        this.charakter3 = charakter3;
        this.charakter4 = charakter4;
        this.charakter5 = charakter5;
    }


    /**
     * Methode, die eine neue GameFile mit den ihr uebergebenen Parametern erstellt.
     *  Die Parameterliste ist allerdings noch lange nicht vollstaendig.
     * @return
     * @throws IOException
     * @Author Felix Ahrens
     */
    public static GameFile erstelleNeueGameFile() throws IOException {
        String spielName = "Spiel" + (gebeGameFileListeZurueck().length-1);
        String spielPfad_Name = "src/main/java/res/" + spielName + ".csv";
        try{
            FileWriter dateiSchreiber = new FileWriter(spielPfad_Name);
            dateiSchreiber.write(spielName+"\n");
            CharakterController.erstelleDefaultCharakter();
            Charakter[] charakterArray = CharakterController.getCharakterArray();
            dateiSchreiber.write(charakterArray[0]+"\n");
            dateiSchreiber.write(charakterArray[1]+"\n");
            dateiSchreiber.write(charakterArray[2]+"\n");
            dateiSchreiber.write(charakterArray[3]+"\n");
            dateiSchreiber.write(charakterArray[4]+"\n");
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
        return macheGameFileAusZeilenArray(leseCSV("src/main/java/res/" + fileName + ".csv"));
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
            return new GameFile("src/main/java/res/" + zeilenArray[0] + ".csv", zeilenArray[0],
                    erstelleCharakterAusCSVZeile(zeilenArray[1]) , erstelleCharakterAusCSVZeile(zeilenArray[2]),
                    erstelleCharakterAusCSVZeile(zeilenArray[3]), erstelleCharakterAusCSVZeile(zeilenArray[4]),
                    erstelleCharakterAusCSVZeile(zeilenArray[5]));
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Platzhalter im Body, die Methode ist noch nicht entwickelt.
     * @return
     * @Author Felix Ahrens
     */
    public static File[] gebeGameFileListeZurueck(){
        File gameFileVerzeichnis = new File("src/main/java/res/");
        File[] gameFiles = gameFileVerzeichnis.listFiles();
        return gameFiles;
    }

    public static Charakter erstelleCharakterAusCSVZeile(String zeile){
        Integer[] zeilenStuecke = Arrays.stream(zeile.split(Strings.DOPPELPUNKT)[1].split(";"))
                .map(String::trim)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        return new Charakter(zeile.split(Strings.DOPPELPUNKT)[0], zeilenStuecke[0], zeilenStuecke[1], zeilenStuecke[2], zeilenStuecke[3], zeilenStuecke[4],
                zeilenStuecke[5], zeilenStuecke[6], zeilenStuecke[7], zeilenStuecke[8], zeilenStuecke[9]);
    }

}
