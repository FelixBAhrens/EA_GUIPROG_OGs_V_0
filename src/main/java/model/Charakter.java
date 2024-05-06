package model;

public class Charakter {
    /*Parameter eines Charakters. Hab da erstmal alle aus der Aufgabenstellung abgeschrieben, müssen noch auf
        Vollständigkeit überprüft werden.*/
    private String name;
    private String klasse; //optional
    //Kampfwerte
    /*
    private int gesundheit;
    private int schild;
    private int manapunkte;
    private int nahkampfWert;
    private int fernkampfWert;
    private int fernkaempfeZahl;
    private int zahlAusweichen;
    private int magieResistenz;
    private int bewegungsWeite;
    private int initiative;
     */

    /**
     * Charakterersteller. Wenn das Konzept so cool ist, können wir da noch die anderen Parameter hinzufuegen.
     * @param name
     * @param klasse
     * @return
     */
    public Charakter erstelleCharakter (String name, String klasse) {
        return new Charakter(name, klasse);
    }

    /**
     * Konstruktor der Klasse Charakter
     * @param name Der Name des Charakters, der zurueckgegeben werden soll
     * @param klasse
     * @Author Felix Ahrens
     */
    public Charakter (String name, String klasse) {}
}

/**
 * VORSCHLAG
 * Personen sind Instanzen der Klasse Charakter und uebernehmen dessen Eigenschaften.
 */
