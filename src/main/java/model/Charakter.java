package model;

import res.Strings;

public class Charakter
{
    /*Parameter eines Charakters. Hab da erstmal alle aus der Aufgabenstellung abgeschrieben, müssen noch auf
        Vollständigkeit überprüft werden.*/
    private String name;
    //private String klasse; //optional
    //Kampfwerte

    private int gesundheit;
    private int schild;
    private int manapunkte;
    private int nahkampfWert;
    private int fernkampfWert;
    private int fernkaempfeVerbleibenZahl;
    private int zahlAusweichen;
    private int magieResistenz;
    private int bewegungsWeite;
    private int initiative;
    private boolean angeheuert;

    public int getInitiative ()
    {
        return initiative;
    }

    public void setInitiative (int initiative)
    {
        this.initiative = initiative;
    }

    public int getBewegungsWeite ()
    {
        return bewegungsWeite;
    }

    public void setBewegungsWeite (int bewegungsWeite)
    {
        this.bewegungsWeite = bewegungsWeite;
    }

    public int getMagieResistenz ()
    {
        return magieResistenz;
    }

    public void setMagieResistenz (int magieResistenz)
    {
        this.magieResistenz = magieResistenz;
    }

    public int getZahlAusweichen ()
    {
        return zahlAusweichen;
    }

    public void setZahlAusweichen (int zahlAusweichen)
    {
        this.zahlAusweichen = zahlAusweichen;
    }

    public int getFernkaempfeVerbleibenZahl ()
    {
        return fernkaempfeVerbleibenZahl;
    }

    public void setFernkaempfeVerbleibenZahl (int fernkaempfeVerbleibenZahl) {this.fernkaempfeVerbleibenZahl = fernkaempfeVerbleibenZahl;}

    public int getFernkampfWert ()
    {
        return fernkampfWert;
    }

    public void setFernkampfWert (int fernkampfWert)
    {
        this.fernkampfWert = fernkampfWert;
    }

    public int getNahkampfWert ()
    {
        return nahkampfWert;
    }

    public void setNahkampfWert (int nahkampfWert)
    {
        this.nahkampfWert = nahkampfWert;
    }

    public int getManapunkte ()
    {
        return manapunkte;
    }

    public void setManapunkte (int manapunkte)
    {
        this.manapunkte = manapunkte;
    }

    public int getSchild ()
    {
        return schild;
    }

    public void setSchild (int schild)
    {
        this.schild = schild;
    }

    public int getGesundheit ()
    {
        return gesundheit;
    }

    public void setGesundheit (int gesundheit)
    {
        this.gesundheit = gesundheit;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public boolean istAngeheuert ()
    {
        return angeheuert;
    }

    public void setAngeheuert (boolean angeheuert)
    {
        this.angeheuert = angeheuert;
    }

    /**
     * Konstruktor der Klasse Charakter
     *
     * @param name Der Name des Charakters, der zurueckgegeben werden soll
     * @Author Felix Ahrens
     */
    public Charakter (String name, int gesundheit, int schild, int manapunkte, int nahkampfWert, int fernkampfWert,
                      int fernkaempfeVerbleibenZahl, int zahlAusweichen, int magieResistenz, int bewegungsWeite,
                      int initiative, boolean angeheuert)
    {
        this.name = name;
        this.gesundheit = gesundheit;
        this.schild = schild;
        this.manapunkte = manapunkte;
        this.nahkampfWert = nahkampfWert;
        this.fernkampfWert = fernkampfWert;
        this.fernkaempfeVerbleibenZahl = fernkaempfeVerbleibenZahl;
        this.zahlAusweichen = zahlAusweichen;
        this.magieResistenz = magieResistenz;
        this.bewegungsWeite = bewegungsWeite;
        this.initiative = initiative;
        this.angeheuert = angeheuert;
    }

    public int berechnePreisInGold () {
        return (this.nahkampfWert + this.fernkampfWert + this.bewegungsWeite + this.initiative);
    }

    @Override
    public String toString ()
    {
        return name + Strings.DOPPELPUNKT + gesundheit + Strings.SEMIKOLON + schild + Strings.SEMIKOLON
                + manapunkte + Strings.SEMIKOLON + nahkampfWert + Strings.SEMIKOLON + fernkampfWert + Strings.SEMIKOLON
                + fernkaempfeVerbleibenZahl + Strings.SEMIKOLON + zahlAusweichen + Strings.SEMIKOLON + magieResistenz
                + Strings.SEMIKOLON + bewegungsWeite + Strings.SEMIKOLON + initiative + Strings.DOPPELPUNKT + angeheuert;
    }
}

/**
 * VORSCHLAG Personen sind Instanzen der Klasse Charakter und uebernehmen dessen Eigenschaften.
 */
