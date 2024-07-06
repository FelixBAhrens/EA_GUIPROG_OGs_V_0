package model;

import res.Konstanten;
import res.Strings;

public class Kaempfer extends Charakter
{

    private int xPosition;
    private int yPosition;
    private boolean istLeader;
    private boolean istAmZug;
    private String imageURL;

    public int getxPosition ()
    {
        return xPosition;
    }

    public void setxPosition (int xPosition)
    {
        this.xPosition = xPosition;
    }

    public int getyPosition ()
    {
        return yPosition;
    }

    public void setyPosition (int yPosition)
    {
        this.yPosition = yPosition;
    }

    public boolean isIstLeader ()
    {
        return istLeader;
    }

    public void setIstLeader (boolean istLeader)
    {
        this.istLeader = istLeader;
    }

    public boolean isIstAmZug ()
    {
        return istAmZug;
    }

    public void setIstAmZug (boolean istAmZug)
    {
        this.istAmZug = istAmZug;
    }

    public String getImageURL ()
    {
        return imageURL;
    }

    public void setImageURL (String imageURL)
    {
        this.imageURL = imageURL;
    }

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
     * @param initiative      weitere parameter
     * @Author Felix Ahrens
     */
    public Kaempfer (String name, int gesundheit, int schild, int manapunkte, int nahkampfWert, int fernkampfWert,
                     int fernkaempfeZahl, int zahlAusweichen, int magieResistenz, int bewegungsWeite, int initiative,
                     boolean angeheuert, int xPosition, int yPosition, boolean istLeader, boolean istAmZug, String imageURL)
    {
        super(name, gesundheit, schild, manapunkte, nahkampfWert, fernkampfWert, fernkaempfeZahl, zahlAusweichen, magieResistenz, bewegungsWeite, initiative, angeheuert);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.istLeader = istLeader;
        this.istAmZug = istAmZug;
        this.imageURL = imageURL;
    }

    public static Kaempfer macheNeuenKaempferAusCharakter (Charakter charakter)
    {
        boolean istLeader = false;
        if (charakter.getName().equals(Strings.LEADER))
        {
            istLeader = true;
        }
        return new Kaempfer(charakter.getName(), charakter.getGesundheit() * Konstanten.INT_TEN, charakter.getSchild(), charakter.getManapunkte(),
                charakter.getNahkampfWert(), charakter.getFernkampfWert(), charakter.getFernkaempfeVerbleibenZahl(), charakter.getZahlAusweichen(),
                charakter.getMagieResistenz(), charakter.getBewegungsWeite(), charakter.getInitiative(), charakter.istAngeheuert(),
                Konstanten.INT_ZERO, Konstanten.INT_ZERO, istLeader, true, Strings.DATEIPFAD_ENDGEGNER);
    }

    public static Kaempfer erstelleEndgegner ()
    {
        return new Kaempfer(Strings.ENDGEGNER, Konstanten.INT_ONE_HUNDRED, Konstanten.INT_TEN, Konstanten.INT_TEN, Konstanten.INT_TEN, Konstanten.INT_TEN,
                Konstanten.INT_TEN, Konstanten.INT_TEN, Konstanten.INT_TEN, Konstanten.INT_TEN, Konstanten.INT_TEN, false,
                Konstanten.INT_ELEVEN, Konstanten.INT_ZERO, false, false, Strings.DATEIPFAD_ENDGEGNER);
    }
}
