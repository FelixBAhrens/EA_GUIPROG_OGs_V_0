package model;

public class Kaempfer extends Charakter {

    private int xPosition;
    private int yPosition;
    private boolean istLeader;
    private boolean istAmZug;


    /**
     * Konstruktor der Klasse Charakter
     *
     * @param name            Der Name des Charakters, der zurueckgegeben werden soll
     * @param gesundheit
     * @param schild
     * @param manapunkte
     * @param nahkampfWert
     * @param fernkampfWert
     * @param fernkaempfeZahl
     * @param zahlAusweichen
     * @param magieResistenz
     * @param bewegungsWeite
     * @param initiative
     * @Author Felix Ahrens
     */
    public Kaempfer(String name, int gesundheit, int schild, int manapunkte, int nahkampfWert, int fernkampfWert, int fernkaempfeZahl, int zahlAusweichen, int magieResistenz, int bewegungsWeite, int initiative) {
        super(name, gesundheit, schild, manapunkte, nahkampfWert, fernkampfWert, fernkaempfeZahl, zahlAusweichen, magieResistenz, bewegungsWeite, initiative);
    }


}
