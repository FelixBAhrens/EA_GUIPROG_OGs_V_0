package control;

import model.Artefakt;
import res.Konstanten;
import res.Strings;

/**
 * Klasse ArtefaktController, die die Controllerklasse fuer die Artefakte bildet.
 *
 * @Author Felix Ahrens
 */
public class ArtefaktController
{
    private static Artefakt[] artefaktArray = new Artefakt[Konstanten.INT_THREE];

    public static Artefakt[] getArtefaktArray ()
    {
        return artefaktArray;
    }

    public static Artefakt[] erstelleDefaultArtefakte ()
    {
        Artefakt statue = new Artefakt(Strings.STATUE,
                false,
                Konstanten.DEFAULT_VALUES_STATUE[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_STATUE[Konstanten.INT_ONE]
        );

        Artefakt schwert = new Artefakt(Strings.SCHWERT,
                false,
                Konstanten.DEFAULT_VALUES_SCHWERT[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_SCHWERT[Konstanten.INT_ONE]
        );

        Artefakt ring = new Artefakt(Strings.RING,
                false,
                Konstanten.DEFAULT_VALUES_RING[Konstanten.INT_ZERO],
                Konstanten.DEFAULT_VALUES_RING[Konstanten.INT_ONE]
        );

        artefaktArray[Konstanten.INT_ZERO] = statue;
        artefaktArray[Konstanten.INT_ONE] = schwert;
        artefaktArray[Konstanten.INT_TWO] = ring;
        return artefaktArray;
    }


}
