package control;

import model.Charakter;
import res.Konstanten;
import model.Kaempfer;

public class GegnerController {
    public static void gegnerAgiert (Charakter angreifer, Charakter verteidiger) {
        for (int gegnerAktionen = Konstanten.INT_THREE; gegnerAktionen > Konstanten.INT_ZERO; gegnerAktionen--) {
            int xEntfernung;
            int yEntfernung;
            if (Kampf2.xPositionSpieler <= Kampf2.xPositionGegner) {
                xEntfernung = Kampf2.xPositionGegner - Kampf2.xPositionSpieler;
            } else {
                xEntfernung = Kampf2.xPositionSpieler - Kampf2.xPositionGegner;
            }
        if (Kampf2.yPositionSpieler <= Kampf2.yPositionGegner) {
            yEntfernung = Kampf2.yPositionGegner - Kampf2.yPositionSpieler;
        } else {
            yEntfernung = Kampf2.yPositionSpieler - Kampf2.yPositionGegner;
        }
            if (xEntfernung <= Konstanten.INT_ONE && yEntfernung <= Konstanten.INT_ONE) {
                verteidiger.setGesundheit(verteidiger.getGesundheit() - angreifer.getNahkampfWert());
            } else if (xEntfernung <= Konstanten.INT_THREE && yEntfernung <= Konstanten.INT_THREE) {
                verteidiger.setGesundheit(verteidiger.getGesundheit() - angreifer.getFernkampfWert());
            }
            Kampf2.attackiere(Kampf2.gegner, Kampf2.spieler);
            switch (waehleAktion()) {
                case Konstanten.INT_ONE:
                    Kampf2.attackiereMagie(Kampf2.gegner, Kampf2.spieler);
                    break;
                case Konstanten.INT_TWO:
                    switch (waehleRichtung()) {
                        case Konstanten.INT_ONE:
                            if (Kampf2.yPositionSpieler > Konstanten.INT_ZERO)
                                Kampf2.yPositionSpieler = +Konstanten.INT_ONE;
                            break;
                        case Konstanten.INT_TWO:
                            if (Kampf2.xPositionSpieler > Konstanten.INT_ZERO)
                                Kampf2.xPositionSpieler = -Konstanten.INT_ONE;
                            break;
                        case Konstanten.INT_THREE:
                            if (Kampf2.yPositionSpieler < Konstanten.INT_ELEVEN)
                                Kampf2.yPositionSpieler = +Konstanten.INT_ONE;
                            break;
                        case Konstanten.INT_FOUR:
                            if (Kampf2.xPositionSpieler < Konstanten.INT_ELEVEN)
                                Kampf2.xPositionSpieler = +Konstanten.INT_ONE;
                            break;
                    }
            }
            }

    }
    private static int waehleAktion() {
        int i = (int) (Math.random()*2+1);
        return i;
    }
    private static int waehleRichtung() {
        int j = (int) (Math.random()*4+1);
        return j;
    }
}
