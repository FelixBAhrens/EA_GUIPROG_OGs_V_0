package model;

import res.Konstanten;
import res.Strings;
import utility.MyIO;

public class Artefakt extends GameFile
{
    private String name;
    private boolean imBesitz;
    private int anwendungenUebrig;
    private int staerke;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public boolean istImBesitz ()
    {
        return imBesitz;
    }

    public void setImBesitz (boolean imBesitz)
    {
        this.imBesitz = imBesitz;
    }

    public int getAnwendungenUebrig ()
    {
        return anwendungenUebrig;
    }

    public void setAnwendungenUebrig (int anwendungenUebrig)
    {
        this.anwendungenUebrig = anwendungenUebrig;
    }

    public int getStaerke ()
    {
        return staerke;
    }

    public void setStaerke (int staerke)
    {
        this.staerke = staerke;
    }

    public Artefakt (String name, boolean imBesitz, int anwendungenUebrig, int staerke)
    {
        this.name = name;
        this.imBesitz = imBesitz;
        this.anwendungenUebrig = anwendungenUebrig;
        this.staerke = staerke;
    }

    /**
     * Methode, die die jeweiligen Eigenschaften des Artefakts auf die GameFile anwendet.
     *
     * @TODO zuruecksetzen der hochgesetzten Eigenschaften nach der Runde muss in der Kampf-Klasse implementiert werden
     * @TODO Anwendbarkeit auf andere Charaktere als den Leader klaeren
     * @Author Felix Ahrens
     */
    public void wendeArtefaktAn () throws Exception
    {
        Charakter leader = GameFile.getInstanz().getLeader();
        if (anwendungenUebrig > Konstanten.INT_ZERO)
        {
            switch (this.getName())
            {
                case Strings.STATUE:
                    MyIO.print(Strings.RING_ANGEWENDET);
                    leader.setGesundheit(leader.getGesundheit() + this.getStaerke());
                    this.setAnwendungenUebrig(anwendungenUebrig - Konstanten.INT_ONE);
                    //Hier wird dann ein Attribut der Klasse GameFile fuer eine Runde im Kampf hochgesetzt um den jeweiligen Staerke-Wert
                    break;
                case Strings.SCHWERT:
                    MyIO.print(Strings.SCHWERT_ANGEWENDET);
                    break;
                case Strings.RING:
                    MyIO.print(Strings.RING_ANGEWENDET);
                    break;
            }
        } else
        {

        }
    }

    public String toCSV ()
    {
        return name + Strings.SEMIKOLON + imBesitz + Strings.SEMIKOLON + anwendungenUebrig + Strings.SEMIKOLON + staerke;
    }

    /**
     * Methode, die die Kosten fuer die Verbesserung ermittelt.
     * @precondition
     * @return
     * @Author Felix Ahrens
     */
    public int ermittleVerbesserungsKosten () {
        return ((this.getStaerke()+this.getAnwendungenUebrig())*Konstanten.INT_FIVE);
    }

    /**
     * Methode, die den Wert ermittelt
     * @return den Wert des Artefakts in der Einheit "Gold"
     * @Author Felix Ahrens
     */
    public int ermittleWert() {
        return (this.getStaerke()*Konstanten.INT_TEN);
    }

    @Override
    public String toString ()
    {
        return Strings.NAME + Strings.DOPPELPUNKT + Strings.SPACE + name + Strings.NEWLINE
                + Strings.IMBESITZ + Strings.DOPPELPUNKT + Strings.SPACE + imBesitz + Strings.NEWLINE
                + Strings.ANZAHL_ANWENDUNGEN + Strings.DOPPELPUNKT + Strings.SPACE + anwendungenUebrig + Strings.NEWLINE
                + Strings.STAERKE + Strings.DOPPELPUNKT + Strings.SPACE + staerke + Strings.NEWLINE;
    }
}
