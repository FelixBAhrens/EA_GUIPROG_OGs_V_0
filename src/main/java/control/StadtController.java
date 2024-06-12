package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import control.GebaeudeController;

import java.io.IOException;

public class StadtController {

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
        SceneManager.changeScene("map-view.fxml");
    }

    private void openGebaeude(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = loader.load();

            // GebäudeController Zugriff
            GebaeudeController controller = loader.getController();
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
