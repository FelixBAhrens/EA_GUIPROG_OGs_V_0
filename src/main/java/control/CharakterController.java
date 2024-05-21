package control;

import model.Charakter;
import res.Konstanten;

public class CharakterController {
    private static Charakter[] charakterArray = new Charakter[6];
    public static Charakter[] getCharakterArray() {
        return charakterArray;
    }

    public static void erstelleDefaultCharakter (){
        Charakter leader = new Charakter("Leader",
                Konstanten.DEFAULT_VALUES_LEADER[0],
                Konstanten.DEFAULT_VALUES_LEADER[1],
                Konstanten.DEFAULT_VALUES_LEADER[2],
                Konstanten.DEFAULT_VALUES_LEADER[3],
                Konstanten.DEFAULT_VALUES_LEADER[4],
                Konstanten.DEFAULT_VALUES_LEADER[5],
                Konstanten.DEFAULT_VALUES_LEADER[6],
                Konstanten.DEFAULT_VALUES_LEADER[7],
                Konstanten.DEFAULT_VALUES_LEADER[8],
                Konstanten.DEFAULT_VALUES_LEADER[9]
                );
        Charakter engineer = new Charakter("Engineer",
                Konstanten.DEFAULT_VALUES_ENGINEER[0],
                Konstanten.DEFAULT_VALUES_ENGINEER[1],
                Konstanten.DEFAULT_VALUES_ENGINEER[2],
                Konstanten.DEFAULT_VALUES_ENGINEER[3],
                Konstanten.DEFAULT_VALUES_ENGINEER[4],
                Konstanten.DEFAULT_VALUES_ENGINEER[5],
                Konstanten.DEFAULT_VALUES_ENGINEER[6],
                Konstanten.DEFAULT_VALUES_ENGINEER[7],
                Konstanten.DEFAULT_VALUES_ENGINEER[8],
                Konstanten.DEFAULT_VALUES_ENGINEER[9]
        );
        Charakter medic = new Charakter("Medic",
                Konstanten.DEFAULT_VALUES_MEDIC[0],
                Konstanten.DEFAULT_VALUES_MEDIC[1],
                Konstanten.DEFAULT_VALUES_MEDIC[2],
                Konstanten.DEFAULT_VALUES_MEDIC[3],
                Konstanten.DEFAULT_VALUES_MEDIC[4],
                Konstanten.DEFAULT_VALUES_MEDIC[5],
                Konstanten.DEFAULT_VALUES_MEDIC[6],
                Konstanten.DEFAULT_VALUES_MEDIC[7],
                Konstanten.DEFAULT_VALUES_MEDIC[8],
                Konstanten.DEFAULT_VALUES_MEDIC[9]
        );
        Charakter hunter = new Charakter("Hunter",
                Konstanten.DEFAULT_VALUES_HUNTER[0],
                Konstanten.DEFAULT_VALUES_HUNTER[1],
                Konstanten.DEFAULT_VALUES_HUNTER[2],
                Konstanten.DEFAULT_VALUES_HUNTER[3],
                Konstanten.DEFAULT_VALUES_HUNTER[4],
                Konstanten.DEFAULT_VALUES_HUNTER[5],
                Konstanten.DEFAULT_VALUES_HUNTER[6],
                Konstanten.DEFAULT_VALUES_HUNTER[7],
                Konstanten.DEFAULT_VALUES_HUNTER[8],
                Konstanten.DEFAULT_VALUES_HUNTER[9]
        );
        Charakter magician = new Charakter("Magician",
                Konstanten.DEFAULT_VALUES_MAGICIAN[0],
                Konstanten.DEFAULT_VALUES_MAGICIAN[1],
                Konstanten.DEFAULT_VALUES_MAGICIAN[2],
                Konstanten.DEFAULT_VALUES_MAGICIAN[3],
                Konstanten.DEFAULT_VALUES_MAGICIAN[4],
                Konstanten.DEFAULT_VALUES_MAGICIAN[5],
                Konstanten.DEFAULT_VALUES_MAGICIAN[6],
                Konstanten.DEFAULT_VALUES_MAGICIAN[7],
                Konstanten.DEFAULT_VALUES_MAGICIAN[8],
                Konstanten.DEFAULT_VALUES_MAGICIAN[9]
        );
        Charakter scout = new Charakter("Scout",
                Konstanten.DEFAULT_VALUES_SCOUT[0],
                Konstanten.DEFAULT_VALUES_SCOUT[1],
                Konstanten.DEFAULT_VALUES_SCOUT[2],
                Konstanten.DEFAULT_VALUES_SCOUT[3],
                Konstanten.DEFAULT_VALUES_SCOUT[4],
                Konstanten.DEFAULT_VALUES_SCOUT[5],
                Konstanten.DEFAULT_VALUES_SCOUT[6],
                Konstanten.DEFAULT_VALUES_SCOUT[7],
                Konstanten.DEFAULT_VALUES_SCOUT[8],
                Konstanten.DEFAULT_VALUES_SCOUT[9]
        );

        charakterArray[0] = leader;
        charakterArray[1] = engineer;
        charakterArray[2] = medic;
        charakterArray[3] = hunter;
        charakterArray[4] = magician;
        charakterArray[5] = scout;
    }
}
