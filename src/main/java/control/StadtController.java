package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class StadtController extends GameMasterViewController {

    @FXML
    private Pane gebaeudePane;
    @FXML
    private Pane hintergrundPane;


    @FXML
    private void openSchmiede() {
        openGebaeude("schmiede-view.fxml");
    }

    @FXML
    private void openSchenke() {
        openGebaeude("schenke-view.fxml");
    }

    @FXML
    private void openMagieverstaerker() {
        openGebaeude("magieverstaerker-view.fxml");
    }

    @FXML
    private void openBasisCamp() {
        openGebaeude("basis-camp-view.fxml");
    }

    @FXML
    private void openFraktionenCamp() {
        openGebaeude("fraktionen-camp-view.fxml");
    }

    @FXML
    private void openTrainingsgelaende() {
        openGebaeude("trainingsgelaende-view.fxml");
    }

    @FXML
    private void openMarkt() {
        openGebaeude("markt-view.fxml");
    }

    @FXML
    private void openHauptquartier() {
        openGebaeude("hauptquartier-view.fxml");
    }

    @FXML
    private void openKarte() throws IOException {
        SceneManager.changeScene("karten-view.fxml");
    }

    @FXML
    private void handleMouseEnter(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        pane.setStyle("-fx-background-color: transparent; -fx-border-color: turquoise; -fx-border-width: 2;");
        for (javafx.scene.Node node : pane.getChildren()) {
            if (node instanceof Button) {
                node.setVisible(true);
            }
        }
    }

    @FXML
    private void handleMouseExit(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        pane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-border-width: 2;");
        for (javafx.scene.Node node : pane.getChildren()) {
            if (node instanceof Button) {
                node.setVisible(false);
            }
        }
    }

    private void openGebaeude (String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = loader.load();

            // GebäudeController Zugriff
            PaneController controller = loader.getController();
            controller.setStadtController(this);

            gebaeudePane.getChildren().setAll(pane);
            gebaeudePane.setVisible(true);
            hintergrundPane.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeGebaeude() {
        gebaeudePane.setVisible(false);
        hintergrundPane.setVisible(false);
        gebaeudePane.getChildren().clear();
    }
}
