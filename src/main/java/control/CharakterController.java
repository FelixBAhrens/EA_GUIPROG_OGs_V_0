package control;

import javafx.fxml.FXML;
import model.Charakter;
import res.Konstanten;
import res.Strings;

public class CharakterController
{

    private static Charakter[] charakterArray = new Charakter[Konstanten.INT_FIVE];

    public static Charakter[] getCharakterArray ()
    {
        return charakterArray;
    }

    public static Charakter[] erstelleDefaultCharakter ()
    {
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
                Konstanten.DEFAULT_VALUES_LEADER[Konstanten.INT_NINE],
                false
        );

        Charakter medic = new Charakter(Strings.MEDIC,
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_ONE],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_TWO],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_THREE],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_FOUR],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_FIVE],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_SIX],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_SEVEN],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_EIGHT],
                Konstanten.DEFAULT_VALUES_MEDIC[Konstanten.INT_NINE],
                false
        );

        Charakter hunter = new Charakter(Strings.HUNTER,
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_ONE],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_TWO],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_THREE],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_FOUR],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_FIVE],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_SIX],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_SEVEN],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_EIGHT],
                Konstanten.DEFAULT_VALUES_HUNTER[Konstanten.INT_NINE],
                false
        );

        Charakter magician = new Charakter(Strings.MAGICIAN,
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_ONE],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_TWO],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_THREE],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_FOUR],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_FIVE],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_SIX],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_SEVEN],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_EIGHT],
                Konstanten.DEFAULT_VALUES_MAGICIAN[Konstanten.INT_NINE],
                false
        );

        Charakter scout = new Charakter(Strings.SCOUT,
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_ONE],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_TWO],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_THREE],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_FOUR],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_FIVE],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_SIX],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_SEVEN],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_EIGHT],
                Konstanten.DEFAULT_VALUES_SCOUT[Konstanten.INT_NINE],
                false
        );

        charakterArray[Konstanten.INT_ZERO] = leader;
        charakterArray[Konstanten.INT_ONE] = medic;
        charakterArray[Konstanten.INT_TWO] = hunter;
        charakterArray[Konstanten.INT_THREE] = magician;
        charakterArray[Konstanten.INT_FOUR] = scout;
        return charakterArray;
    }

    @FXML
    public void initialize ()
    {
    }

}
