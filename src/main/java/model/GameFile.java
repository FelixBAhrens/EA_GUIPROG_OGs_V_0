package model;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * GameFile habe ich mir ausgedacht, um die Spielstaende zu speichern. Instanzen der Klasse GameFile sind Spielstaende.
 *  Ich habe bisher erst den DateiNamen und das Erstelldatum als Parameter. Wie in der PROG-EA werden diese in einer
 *  CSV-Datei gespeichert. Diese Klasse beinhaltet diverse Methoden um GameFiles u.A. zu Lesen, zu bearbeiten etc..
 * @Author Felix Ahrens
 */
public class GameFile {
    private String fileName;
    private Date erstellDatum;

    public GameFile(String fileName, String erstellDatum) {}
    public String getFileName() {return fileName;}
    public Date getErstellDatum() {return erstellDatum;}

    /**
     * Methode, die eine neue GameFile mit den ihr uebergebenen Parametern erstellt.
     *  Die Parameterliste ist allerdings noch lange nicht vollstaendig.
     * @param fileName
     * @param erstellDatum
     * @return
     * @throws IOException
     * @Author Felix Ahrens
     */
    public static GameFile erstelleNeueGameFile(String fileName, Date erstellDatum) throws IOException {

        String filePathAndTitle = fileName + ".csv";
        try{
            FileWriter dateiSchreiber = new FileWriter(filePathAndTitle);
            dateiSchreiber.write(erstellDatum.toString());
            dateiSchreiber.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return leseGameFile(fileName);
    }

    /**
     * Methode, die eine bestimmte gespeicherte GameFile lesen kann.
     * @param fileName
     * @return
     * @Author Felix Ahrens
     */
    public static GameFile leseGameFile(String fileName){
        try {
            FileReader fileReader = new FileReader(fileName);
            return macheGameFileAusString(fileReader.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Methode, die aus dem einem CSV-String eine GameFile machen kann.
     *  GameFiles sollen noch mehr Daten als nur die Beispieldaten Name und Datum enthalten. Dafuer braucht es aber auch
     *  noch einen selbst erstellten Standard zur Reihenfolge.
     * @param fileInhalt
     * @return
     * @Author Felix Ahrens
     */
    public static GameFile macheGameFileAusString(String fileInhalt){
        String[] fileInhaltsArray = fileInhalt.split(";");
        return new GameFile(fileInhaltsArray[0], fileInhaltsArray[1]);
    }

    /**
     * Platzhalter im Body, die Methode ist noch nicht entwickelt.
     * @return
     * @Author Felix Ahrens
     */
    public GameFile[] gebeGameFileListeZurueck(){
        return new GameFile[0];
    }

    /**
     * Tostring-Methode fuer das Dateiformat. Habe ich erstmal so implementiert. Keine Ahnung ob die im Ende gebraucht wird.
     * @return
     * @Author Felix Ahrens
     */
    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return erstellDatum.toString();
    }
}
