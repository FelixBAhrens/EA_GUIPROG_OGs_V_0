package control;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PaneController extends ControllerController
{
    private control.StadtController stadtController;
    private control.KartenController kartenController;

    //--------------------------------------------------------------------------


    public void setStadtController(StadtController stadtController) {
        this.stadtController = stadtController;
    }

    public void setKartenController (KartenController kartenController)
    {
        this.kartenController = kartenController;
    }

    public void handleMouseEnter (MouseEvent event)
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

    public void handleMouseExit (MouseEvent event)
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

    public void openPane (String fxmlFile, Pane targetPane, Pane backgroundPane)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = loader.load();

            PaneController controller = loader.getController();
            if (kartenController != null)
            {
                controller.setKartenController(kartenController);
            }
            else if (stadtController != null)
            {
                controller.setStadtController(stadtController);
            }

            targetPane.getChildren().setAll(pane);
            targetPane.setVisible(true);
            backgroundPane.setVisible(true);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void closePane (Pane targetPane, Pane backgroundPane)
    {
        targetPane.setVisible(false);
        backgroundPane.setVisible(false);
        targetPane.getChildren().clear();
    }
}
