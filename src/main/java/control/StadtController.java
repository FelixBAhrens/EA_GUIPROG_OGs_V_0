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
        return refMap;
    }

    @FXML
    private Pane gebaeudePane;
    @FXML
    private Pane hintergrundPane;

    /**
     * Initialize-Methode der Klasse StadtController
     * @Author Felix Ahrens
     */
    @FXML
    public void initialize () {
        setupRefMap();
    }

    /**
     * Methode, die aus der zugehoerigen FXML-Datei aufgerufen wird und die Elemente der Pane
     *  sichtbar setzt. Die StyleClass der Pane wird nun auf einen nicht-transparenten tuerkisen Rand gesetzt.
     * @param event
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
     * @param event
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
     * @param pane
     * @param styleClass
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
     * @param event
     * @Author Felix Ahrens
     */
    @FXML
    public void handleGebaeudeOeffnen (MouseEvent event){
        openGebaeude(refMap.get(((Pane)event.getSource()).getId()));
    }

    /**
     * Methode, die ein Gebaeude in die Stadt reinlaedt und die entsprechende Controllerklasse setzt.
     * @param fxmlFile
     * @Author
     */
    private void openGebaeude (String fxmlFile)
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

    public void closeGebaeude ()
    {
        gebaeudePane.setVisible(false);
        hintergrundPane.setVisible(false);
        gebaeudePane.getChildren().clear();
    }
}
