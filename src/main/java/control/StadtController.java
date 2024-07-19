package control;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import res.Strings;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StadtController extends ControllerController
{
    private Map<String, String> refMap = new HashMap<String, String>();

    @FXML
    private Pane gebaeudePane;

    @FXML
    private Pane hintergrundPane;

    /**
     * Methode, die die refMap konfiguriert, die eine Referenz zwischen den IDs der Panes und den zugehoerigen Dateinamen der FXML-Dateien der jeweiligen Gebaeude herstellt
     * @pre Die Strings muessen im Interface "Strings" vorhanden sein. Die refMap muss in der Klasse StadtController existieren. Die refMap muss Werte vom Typ "String" sowohl als Key als auch als Value speichern koennen.
     * @post Die "refMap" wurde initialisiert und mit den IDs der Panes als Keys und den Dateinamen der zu den IDs gehoerenden FXML-Dateien als Values gefuellt werden.
     * @return die konfigurierte "refMap" die Strings und Strings enthaelt
     * @Author Felix Ahrens
     */
    public Map<String, String> setupRefMap (){
        refMap.clear();
        refMap.put(Strings.SCHMIEDE, Strings.FXML_SCHMIEDE);
        refMap.put(Strings.SCHENKE, Strings.FXML_SCHENKE);
        refMap.put(Strings.MAGIEVERSTAERKER, Strings.FXML_MAGIEVERSTAERKER);
        refMap.put(Strings.BASISCAMP, Strings.FXML_BASISCAMP);
        refMap.put(Strings.FRAKTIONENCAMPS, Strings.FXML_FRAKTIONENCAMP);
        refMap.put(Strings.TRAININGSGELAENDE, Strings.FXML_TRAININGSGELAENDE);
        refMap.put(Strings.MARKT, Strings.FXML_MARKT);
        refMap.put(Strings.HAUPTQUARTIER, Strings.FXML_HAUPTQUARTIER);
        refMap.put(Strings.KARTE, Strings.FXML_KARTENEW);
        return refMap;
    }

    /**
     * Initialize-Methode der Klasse StadtController
     * @pre
     * @post
     * @Author Felix Ahrens
     */
    @FXML
    public void initialize () {
        setupRefMap();
    }

    /**
     * Methode, die aus der zugehoerigen FXML-Datei aufgerufen wird und die Elemente der Pane
     *  sichtbar setzt. Die StyleClass der Pane wird nun auf einen nicht-transparenten tuerkisen Rand gesetzt.
     * @pre Das MouseEvent muss von einer Pane stammen. Die Methode muss von einer Pane aufgerufen werden, wenn ein Mauscursor ihr Gebiet betritt. Die genutzten Methoden und Variablen muessen existieren.
     * @post Die Elemente der Pane und deren Rand wurden sichtbar und auf das Design aus der entsprechenden StyleClass gestellt.
     * @param event Das Ereignis, das durch eine Mausaktion ausgeloest wurde und zum Methodenaufruf gefuehrt hat.
     * @Author Felix Ahrens
     */
    @FXML
    private void handleMouseEnter (MouseEvent event)
    {
        Pane pane = (Pane) event.getSource();
        aendereStyleClass(pane, Strings.STYLECLASS_TRANSPARENT_TUERKISER_RAND);
        for (javafx.scene.Node node : pane.getChildren())
        {
            if (node instanceof Button)
            {
                node.setVisible(true);
            }
        }
    }

    /**
     * Methode, die von einer Pane aufgerufen wird, wenn der Mauscursor die Pane verlaesst.
     *  Die Methode setzt die Elemente der Pane auf nicht visible und stellt den Hintergrund der Pane
     *  auf durchsichtig.
     * @pre Das MouseEvent muss von einer Pane stammen. Die Methode muss von einer Pane aufgerufen werden, wenn ein Mauscursor ihr Gebiet verlaesst. Die genutzten Methoden und Variablen muessen existieren.
     * @post Die Elemente der Pane und deren Rand wurden unsichtbar und auf das Design aus der entsprechenden StyleClass gestellt.
     * @param event Das Ereignis, das durch eine Mausaktion ausgeloest wurde und zum Methodenaufruf gefuehrt hat.
     * @Author Felix Ahrens
     */
    @FXML
    private void handleMouseExit (MouseEvent event)
    {
        Pane pane = (Pane) event.getSource();
        aendereStyleClass(pane, Strings.STYLECLASS_TRANSPARENT);
        for (javafx.scene.Node node : pane.getChildren())
        {
            if (node instanceof Button)
            {
                node.setVisible(false);
            }
        }
    }

    /**
     * Methode zum aendern der StyleClass.
     *  Diese entfernt vorher alle StyleClasses und setzt die uebergebene StyleClass als einzige StyleClass.
     * @pre Der Methode muessen eine Pane und ein String uebergeben werden. Die Pane muss eine "observableList" mit den styleClasses zurueckliefern.
     *  Der uebergebene String muss einer vordefinierten StyleClass entsprechen.
     * @post Die uebergebene Pane enthaelt nur noch die dieser Methode uebergebene styleClass
     * @param pane Die Pane, die nur noch die uebergebene styleClass enthaelt.
     * @param styleClass Die styleClass, die die Pane nur noch enthalten soll.
     * @Author Felix Ahrens
     */
    public void aendereStyleClass (Pane pane, String styleClass) {
        ObservableList<String> styleClasses = pane.getStyleClass();
        styleClasses.clear();
        pane.getStyleClass().add(styleClass);
    }

    /**
     * Universelle Methode zum Oeffnen von Gebaeuden. Diese wird bei Mausklick auf eine der Panes
     *  aufgerufen und holt sich aus der ID der jeweiligen Pane mit der "refMap" den Pfad der FXML-Datei
     *  des jeweiligen Gebaeudes, um mit dieser (als String) die Methode "openGebaeude" aufzurufen.
     * @pre Das Event muss sich zu einer Pane typkonvertieren lassen und einer ID aus der refMap entsprechen.
     *  "refMap.get()" muss einen String zurueckliefern und die genutzten Methoden muessen existieren
     * @post Die Methode "oeffneGebaeude" der Klasse StadtController wurde mit einem String aus der "refMap" aufgerufen.
     * @param event Das Ereignis, das durch eine Mausaktion ausgeloest wurde und zum Methodenaufruf gefuehrt hat.
     * @Author Felix Ahrens
     */
    @FXML
    public void handleGebaeudeOeffnen (MouseEvent event){
        oeffneGebaeude(refMap.get(((Pane)event.getSource()).getId()));
    }

    /**
     * Methode, die ein Gebaeude in die Stadt reinlaedt und die entsprechende Controllerklasse setzt.
     * @pre Der uebergebene String fuer den Dateinamen einer FXML-Datei muss zu einer existierenden Datei passen.
     * @post Die FXML-Datei, die dem uebergebenen String entspricht, wurde in die Pane "gebaeudePane" geladen und diese sichtbar gesetzt.
     * @param fxmlFile die FXML-Datei des Gebaeudes, das in die Pane "gebaeudepane" reingeladen werden soll.
     * @Author Felix Ahrens
     */
    private void oeffneGebaeude (String fxmlFile)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = loader.load();
            PaneController controller = loader.getController();
            controller.setStadtController(this);
            gebaeudePane.getChildren().setAll(pane);
            gebaeudePane.setVisible(true);
            hintergrundPane.setVisible(true);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Methode zum schliessen des Gebaeudes. Die Panes "gebaeudePane" und "hintergrundPane" werden auf invisible
     *  gesetzt. Alles, was in der gebaeudePane enthalten war - also das geladene Gebaeude wird entfernt.
     * @pre Die Panes "gebaeudePane" und "hintergrundPane" muessen in der Klasse existieren
     * @post Die Panes wurden auf unsichtbar gesetzt und jeder Inhalt der "gebaeudePane" entfernt.
     * @Author Felix Ahrens
     */
    public void schliesseGebaeude ()
    {
        gebaeudePane.setVisible(false);
        hintergrundPane.setVisible(false);
        gebaeudePane.getChildren().clear();
    }
}
