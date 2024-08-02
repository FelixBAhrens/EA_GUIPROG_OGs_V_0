package control;

import model.Charakter;
import res.Konstanten;
import model.Kaempfer;

public class GegnerController {
    /**
     *
     * @param angreifer
     * @param verteidiger
     * @post Nach dieser Methode bewegt sich der Gegner zufällig oder greift den Spieler an.
     * @Author Enes Oezcan
     */
    public static void gegnerAgiert (Charakter angreifer, Charakter verteidiger) {
        for (int gegnerAktionen = Konstanten.INT_THREE; gegnerAktionen > Konstanten.INT_ZERO; gegnerAktionen--) {
            int xEntfernung;
            int yEntfernung;
            if (KampfController2.xPositionSpieler <= KampfController2.xPositionGegner) {
                xEntfernung = KampfController2.xPositionGegner - KampfController2.xPositionSpieler;
            } else {
                xEntfernung = KampfController2.xPositionSpieler - KampfController2.xPositionGegner;
            }
        if (KampfController2.yPositionSpieler <= KampfController2.yPositionGegner) {
            yEntfernung = KampfController2.yPositionGegner - KampfController2.yPositionSpieler;
        } else {
            yEntfernung = KampfController2.yPositionSpieler - KampfController2.yPositionGegner;
        }
            if (xEntfernung <= Konstanten.INT_ONE && yEntfernung <= Konstanten.INT_ONE) {
                verteidiger.setGesundheit(verteidiger.getGesundheit() - angreifer.getNahkampfWert());
            } else if (xEntfernung <= Konstanten.INT_THREE && yEntfernung <= Konstanten.INT_THREE) {
                verteidiger.setGesundheit(verteidiger.getGesundheit() - angreifer.getFernkampfWert());
            }
            KampfController2.attackiere(KampfController2.gegner, KampfController2.spieler);
            switch (waehleAktion()) {
                case Konstanten.INT_ONE:
                    KampfController2.attackiereMagie(KampfController2.gegner, KampfController2.spieler);
                    break;
                case Konstanten.INT_TWO:
                    switch (waehleRichtung()) {
                        case Konstanten.INT_ONE:
                            if (KampfController2.yPositionSpieler > Konstanten.INT_ZERO)
                                KampfController2.yPositionSpieler = +Konstanten.INT_ONE;
                            break;
                        case Konstanten.INT_TWO:
                            if (KampfController2.xPositionSpieler > Konstanten.INT_ZERO)
                                KampfController2.xPositionSpieler = -Konstanten.INT_ONE;
                            break;
                        case Konstanten.INT_THREE:
                            if (KampfController2.yPositionSpieler < Konstanten.INT_ELEVEN)
                                KampfController2.yPositionSpieler = +Konstanten.INT_ONE;
                            break;
                        case Konstanten.INT_FOUR:
                            if (KampfController2.xPositionSpieler < Konstanten.INT_ELEVEN)
                                KampfController2.xPositionSpieler = +Konstanten.INT_ONE;
                            break;
                    }
            }
            }

    }
    /**
     *
     * @return
     * @post Der Wert i wird return und nimmt dabei einen Wert
     * zwischen 1 und 2 ein um eine Zufallsentscheidung zu treffen
     * @Author Enes Oezcan
     */
    private static int waehleAktion() {
        int i = (int) (Math.random()*2+1);
        return i;
    }
    /**
     *
     * @return
     * @post Der Wert i wird return und nimmt dabei einen Wert
     * zwischen 1 und 4 ein um eine Zufallsentscheidung zu treffen
     * @Author Enes Oezcan
     */
    private static int waehleRichtung() {
        int j = (int) (Math.random()*4+1);
        return j;
    }
}
