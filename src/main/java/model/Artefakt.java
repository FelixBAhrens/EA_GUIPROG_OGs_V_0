package model;

import res.Strings;

public class Artefakt extends GameFile {
    private String name;
    private boolean imBesitz;
    private int anzahlAnwendungen;
    private int staerke;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImBesitz() {
        return imBesitz;
    }

    public void setImBesitz(boolean imBesitz) {
        this.imBesitz = imBesitz;
    }

    public int getAnzahlAnwendungen() {
        return anzahlAnwendungen;
    }

    public void setAnzahlAnwendungen(int anzahlAnwendungen) {
        this.anzahlAnwendungen = anzahlAnwendungen;
    }

    public int getStaerke() {
        return staerke;
    }

    public void setStaerke(int staerke) {
        this.staerke = staerke;
    }

    public String toCSV (){
        return name + Strings.CSV_ENDUNG + imBesitz + Strings.CSV_ENDUNG + anzahlAnwendungen + Strings.CSV_ENDUNG + staerke;
    }

    @Override
    public String toString () {
        return Strings.NAME + Strings.DOPPELPUNKT + Strings.SPACE + name + Strings.NEWLINE
                + Strings.IMBESITZ + Strings.DOPPELPUNKT + Strings.SPACE + imBesitz + Strings.NEWLINE
                + Strings.ANZAHL_ANWENDUNGEN + Strings.DOPPELPUNKT + Strings.SPACE + anzahlAnwendungen + Strings.NEWLINE
                + Strings.STAERKE + Strings.DOPPELPUNKT + Strings.SPACE + staerke + Strings.NEWLINE;
    }
}
