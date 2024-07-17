package control;

import model.Charakter;
import model.GameFile;
import res.Konstanten;
import res.Strings;

import java.util.HashMap;

public class CharakterController
{
    private static Charakter[] charakterArray = new Charakter[Konstanten.INT_FIVE];

    private static HashMap<Charakter, String> charakterStringHashMap = new HashMap<>();

    /**
     * Getter-Methode fuer das charakterArray
     * @pre Das charakterArray muss in der Klasse deklariert sein.
     * @post Das charakterArray wurde zurueckgegeben.
     * @return Das charakterArray als eindimensionales Array mit Instanzen der Klasse Charakter
     * @Author Felix Ahrens
     */
    public static Charakter[] getCharakterArray ()
    {
        return charakterArray;
    }

    /**
     * Getter-Methode fuer die "charakterStringHashMap"
     * @pre Die "charakterStringHashMap" muss in der Klasse deklariert sein.
     * @post Die "charakterStringHashMap" wurde zurueckgegeben.
     * @return Die "charakterStringHashMap".
     * @Author Felix Ahrens
     */
    public static HashMap<Charakter, String> getCharakterStringHashMap ()
    {
        return charakterStringHashMap;
    }

    /**
     * Methode, das "charakterArray" mit Charakteren mit den Default-Werten fuellt.
     * @pre Der Konstruktor der Klasse Charakter muss die uebergebenen String- und Integerwerte akzeptieren.
     *  Die genutzten Konstanten muessen verfuegbar und errechbar sein. Das "charakterArray" muss in der Klasse
     *  deklariert sein.
     * @post Die "charakterStringHashMap" wurde mit fuenf Schluesse-Wert-Paaren aus Charakter und zugehoerigem Farb-String gefuellt.
     * @Author Felix Ahrens
     */
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

    /**
     * Methode, die die HashMap mit Schluessel-Wert-Paaren fuellt, die einen Bezug von einem Charakter zu einer Farbe darstellen.
     *  Das ist fuer die GUI wichtig.
     * @pre Die Methode "getInstanz()" der Klasse GameFile muss die derzeit im Spiel aktive Instanz zurueckliefern, nicht null.
     *  Die genutzten Methoden und Konstanten muessen verfuegbar und errechbar sein. Die "charakterStringHashMap" muss in der Klasse
     *  deklariert sein.
     * @post Die "charakterStringHashMap" wurde mit fuenf Schluesse-Wert-Paaren aus Charakter und zugehoerigem Farb-String gefuellt.
     * @Author Felix Ahrens
     */
    public static void erstelleDefaultCharakterHashMap (){
        GameFile instanz = GameFile.getInstanz();
        charakterStringHashMap.put(instanz.getLeader(), Strings.BLUE);
        charakterStringHashMap.put(instanz.getMedic(), Strings.CYAN);
        charakterStringHashMap.put(instanz.getHunter(), Strings.NAVY);
        charakterStringHashMap.put(instanz.getScout(), Strings.SKYBLUE);
        charakterStringHashMap.put(instanz.getMagician(), Strings.DODGERBLUE);
    }
}
