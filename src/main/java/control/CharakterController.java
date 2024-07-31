package control;

import javafx.fxml.FXML;
import model.Charakter;
import res.Konstanten;
import res.Strings;

public class CharakterController {

    private static Charakter[] charakterArray = new Charakter[Konstanten.INT_FIVE];

    public static Charakter[] getCharakterArray() {
        return charakterArray;
    }

    public static Charakter[] erstelleDefaultCharakter () {
        Charakter leader = new Charakter(Strings.LEADER,
                NeuesSpielController.NEW_VALUES_LEADER[Konstanten.INT_ZERO],
                NeuesSpielController.NEW_VALUES_LEADER[Konstanten.INT_ONE],
                NeuesSpielController.NEW_VALUES_LEADER[Konstanten.INT_TWO],
                NeuesSpielController.NEW_VALUES_LEADER[Konstanten.INT_THREE],
                NeuesSpielController.NEW_VALUES_LEADER[Konstanten.INT_FOUR],
                NeuesSpielController.NEW_VALUES_LEADER[Konstanten.INT_FIVE],
                NeuesSpielController.NEW_VALUES_LEADER[Konstanten.INT_SIX],
                NeuesSpielController.NEW_VALUES_LEADER[Konstanten.INT_SEVEN],
                NeuesSpielController.NEW_VALUES_LEADER[Konstanten.INT_EIGHT],
                NeuesSpielController.NEW_VALUES_LEADER[Konstanten.INT_NINE]
        );

        Charakter medic = new Charakter(Strings.MEDIC,
                NeuesSpielController.NEW_VALUES_MEDIC[Konstanten.INT_ZERO],
                NeuesSpielController.NEW_VALUES_MEDIC[Konstanten.INT_ONE],
                NeuesSpielController.NEW_VALUES_MEDIC[Konstanten.INT_TWO],
                NeuesSpielController.NEW_VALUES_MEDIC[Konstanten.INT_THREE],
                NeuesSpielController.NEW_VALUES_MEDIC[Konstanten.INT_FOUR],
                NeuesSpielController.NEW_VALUES_MEDIC[Konstanten.INT_FIVE],
                NeuesSpielController.NEW_VALUES_MEDIC[Konstanten.INT_SIX],
                NeuesSpielController.NEW_VALUES_MEDIC[Konstanten.INT_SEVEN],
                NeuesSpielController.NEW_VALUES_MEDIC[Konstanten.INT_EIGHT],
                NeuesSpielController.NEW_VALUES_MEDIC[Konstanten.INT_NINE]
        );

        Charakter hunter = new Charakter(Strings.HUNTER,
                NeuesSpielController.NEW_VALUES_HUNTER[Konstanten.INT_ZERO],
                NeuesSpielController.NEW_VALUES_HUNTER[Konstanten.INT_ONE],
                NeuesSpielController.NEW_VALUES_HUNTER[Konstanten.INT_TWO],
                NeuesSpielController.NEW_VALUES_HUNTER[Konstanten.INT_THREE],
                NeuesSpielController.NEW_VALUES_HUNTER[Konstanten.INT_FOUR],
                NeuesSpielController.NEW_VALUES_HUNTER[Konstanten.INT_FIVE],
                NeuesSpielController.NEW_VALUES_HUNTER[Konstanten.INT_SIX],
                NeuesSpielController.NEW_VALUES_HUNTER[Konstanten.INT_SEVEN],
                NeuesSpielController.NEW_VALUES_HUNTER[Konstanten.INT_EIGHT],
                NeuesSpielController.NEW_VALUES_HUNTER[Konstanten.INT_NINE]
        );

        Charakter magician = new Charakter(Strings.MAGICIAN,
                NeuesSpielController.NEW_VALUES_MAGICIAN[Konstanten.INT_ZERO],
                NeuesSpielController.NEW_VALUES_MAGICIAN[Konstanten.INT_ONE],
                NeuesSpielController.NEW_VALUES_MAGICIAN[Konstanten.INT_TWO],
                NeuesSpielController.NEW_VALUES_MAGICIAN[Konstanten.INT_THREE],
                NeuesSpielController.NEW_VALUES_MAGICIAN[Konstanten.INT_FOUR],
                NeuesSpielController.NEW_VALUES_MAGICIAN[Konstanten.INT_FIVE],
                NeuesSpielController.NEW_VALUES_MAGICIAN[Konstanten.INT_SIX],
                NeuesSpielController.NEW_VALUES_MAGICIAN[Konstanten.INT_SEVEN],
                NeuesSpielController.NEW_VALUES_MAGICIAN[Konstanten.INT_EIGHT],
                NeuesSpielController.NEW_VALUES_MAGICIAN[Konstanten.INT_NINE]
        );

        Charakter scout = new Charakter(Strings.SCOUT,
                NeuesSpielController.NEW_VALUES_SCOUT[Konstanten.INT_ZERO],
                NeuesSpielController.NEW_VALUES_SCOUT[Konstanten.INT_ONE],
                NeuesSpielController.NEW_VALUES_SCOUT[Konstanten.INT_TWO],
                NeuesSpielController.NEW_VALUES_SCOUT[Konstanten.INT_THREE],
                NeuesSpielController.NEW_VALUES_SCOUT[Konstanten.INT_FOUR],
                NeuesSpielController.NEW_VALUES_SCOUT[Konstanten.INT_FIVE],
                NeuesSpielController.NEW_VALUES_SCOUT[Konstanten.INT_SIX],
                NeuesSpielController.NEW_VALUES_SCOUT[Konstanten.INT_SEVEN],
                NeuesSpielController.NEW_VALUES_SCOUT[Konstanten.INT_EIGHT],
                NeuesSpielController.NEW_VALUES_SCOUT[Konstanten.INT_NINE]
        );

        charakterArray[Konstanten.INT_ZERO] = leader;
        charakterArray[Konstanten.INT_ONE] = medic;
        charakterArray[Konstanten.INT_TWO] = hunter;
        charakterArray[Konstanten.INT_THREE] = magician;
        charakterArray[Konstanten.INT_FOUR] = scout;
        return charakterArray;
    }

    @FXML
    public void initialize() {
    }

}
