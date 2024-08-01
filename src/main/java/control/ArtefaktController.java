package control;

// COMPLETED

import model.Artefakt;
import res.Konstanten;
import res.Strings;

/**
 * Klasse ArtefaktController, die die Controllerklasse fuer die Artefakte bildet.
 * In dieser befinden sich alle wesentlichen Methoden zum Verwalten der Artefakte.
 *
 * @author Felix Ahrens.
 */
public class ArtefaktController
{
    private static Artefakt[] artefaktArray = new Artefakt[Konstanten.INT_THREE];

    public static Artefakt[] getArtefaktArray ()
    {
        return artefaktArray;
    }

    /**
     * Methode zum Erstellen der Default-Artefakte.
     *
     * @return Als Array alle drei mit den Default-Werten erstellten Artefakte
     * @pre Die Klasse muss auf den Konstruktor der Klasse Artefakt zugreifen koennen.
     * Dieser muss die uebergebenen Werte akzeptieren und jeweils eine neue Instanz
     * vom Artefakt erstellen. Die Konstanten erreichbar sein.
     *
     * @post Es wurden die drei Artefakte {Statue, Schwert, Ring} erstellt und
     * als Artefakt-Array zurueckgegeben.
     *
     * @author Felix Ahrens.
     */
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