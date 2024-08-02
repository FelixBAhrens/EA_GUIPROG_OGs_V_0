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
     * Wird nicht mehr genutzt da wir uns für eine andere Methode entschieden haben.
     * @Author Enes Oezcan
     */
    public static void gegnerAgiert (Charakter angreifer, Charakter verteidiger) {
        for (int gegnerAktionen = Konstanten.INT_THREE; gegnerAktionen > Konstanten.INT_ZERO; gegnerAktionen--) {
            int xEntfernung;
            int yEntfernung;
            if (KampfController.xPositionSpieler <= KampfController.xPositionGegner) {
                xEntfernung = KampfController.xPositionGegner - KampfController.xPositionSpieler;
            } else {
                xEntfernung = KampfController.xPositionSpieler - KampfController.xPositionGegner;
            }
        if (KampfController.yPositionSpieler <= KampfController.yPositionGegner) {
            yEntfernung = KampfController.yPositionGegner - KampfController.yPositionSpieler;
        } else {
            yEntfernung = KampfController.yPositionSpieler - KampfController.yPositionGegner;
        }
            if (xEntfernung <= Konstanten.INT_ONE && yEntfernung <= Konstanten.INT_ONE) {
                verteidiger.setGesundheit(verteidiger.getGesundheit() - angreifer.getNahkampfWert());
            } else if (xEntfernung <= Konstanten.INT_THREE && yEntfernung <= Konstanten.INT_THREE) {
                verteidiger.setGesundheit(verteidiger.getGesundheit() - angreifer.getFernkampfWert());
            }
            KampfController.attackiere(KampfController.gegner, KampfController.spieler);
            switch (waehleAktion()) {
                case Konstanten.INT_ONE:
                    KampfController.attackiereMagie(KampfController.gegner, KampfController.spieler);
                    break;
                case Konstanten.INT_TWO:
                    switch (waehleRichtung()) {
                        case Konstanten.INT_ONE:
                            if (KampfController.yPositionSpieler > Konstanten.INT_ZERO)
                                KampfController.yPositionSpieler = +Konstanten.INT_ONE;
                            break;
                        case Konstanten.INT_TWO:
                            if (KampfController.xPositionSpieler > Konstanten.INT_ZERO)
                                KampfController.xPositionSpieler = -Konstanten.INT_ONE;
                            break;
                        case Konstanten.INT_THREE:
                            if (KampfController.yPositionSpieler < Konstanten.INT_ELEVEN)
                                KampfController.yPositionSpieler = +Konstanten.INT_ONE;
                            break;
                        case Konstanten.INT_FOUR:
                            if (KampfController.xPositionSpieler < Konstanten.INT_ELEVEN)
                                KampfController.xPositionSpieler = +Konstanten.INT_ONE;
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
