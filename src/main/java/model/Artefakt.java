package model;

import res.Strings;

public class Artefakt extends GameFile {
    private String name;
    private boolean imBesitz;
    private int anzahlAnwendungen;
    private int staerke;

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
