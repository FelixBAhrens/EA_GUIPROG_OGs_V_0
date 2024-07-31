package control;

import model.Charakter;
import res.Konstanten;
import res.Strings;

public class Character {
    private static Charakter[] charakterArray = new Charakter[Konstanten.INT_FIVE];

    public static Charakter[] getCharakterArray() {
        return charakterArray;
    }
    public static Charakter[] erstelleDefaultCharakter() {
        Charakter leader = new Charakter(Strings.LEADER,
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_ONE],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_TWO],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_THREE],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_FOUR],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_FIVE],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_SIX],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_SEVEN],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_EIGHT],
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_NINE]
        );
        charakterArray[Konstanten.INT_ZERO] = leader;
        return charakterArray;
    }
}