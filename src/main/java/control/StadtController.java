package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import res.Strings;

import java.io.IOException;

public class StadtController extends ControllerController {

    @FXML
    private Pane gebaeudePane;
    @FXML
    private Pane hintergrundPane;


    @FXML
    private void openSchmiede()
    {
        openGebaeude(Strings.FXML_SCHMIEDE);
    }

    @FXML
    private void openSchenke()
    {
        openGebaeude(Strings.FXML_SCHENKE);
    }

    @FXML
    private void openMagieverstaerker()
    {
        openGebaeude(Strings.FXML_MAGIEVERSTAERKER);
    }

    @FXML
    private void openBasisCamp()
    {
        openGebaeude(Strings.FXML_BASISCAMP);
    }

    @FXML
    private void openFraktionenCamp()
    {
        openGebaeude(Strings.FXML_FRAKTIONENCAMP);
    }

    @FXML
    private void openTrainingsgelaende()
    {
        openGebaeude(Strings.FXML_TRAININGSGELAENDE);
    }

    @FXML
    private void openMarkt()
    {
        openGebaeude(Strings.FXML_MARKT);
    }

    @FXML
    private void openHauptquartier()
    {
        openGebaeude(Strings.FXML_HAUPTQUARTIER);
    }

    /**
     * @TODO Strings
     * @param event
     */
    @FXML
    private void handleMouseEnter(MouseEvent event)
    {
        Pane pane = (Pane) event.getSource();
        pane.setStyle("-fx-background-color: transparent; -fx-border-color: turquoise; -fx-border-width: 2;");
        for (javafx.scene.Node node : pane.getChildren())
        {
            if (node instanceof Button)
            {
                node.setVisible(true);
            }
        }
    }

    /**
     * @TODO Strings
     * @param event
     */
    @FXML
    private void handleMouseExit(MouseEvent event)
    {
        Pane pane = (Pane) event.getSource();
        pane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-border-width: 2;");
        for (javafx.scene.Node node : pane.getChildren())
        {
            if (node instanceof Button)
            {
                node.setVisible(false);
            }
        }
    }

    private void openGebaeude (String fxmlFile)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = loader.load();

            // GebäudeController Zugriff
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

    public void closeGebaeude()
    {
        gebaeudePane.setVisible(false);
        hintergrundPane.setVisible(false);
        gebaeudePane.getChildren().clear();
    }
}
