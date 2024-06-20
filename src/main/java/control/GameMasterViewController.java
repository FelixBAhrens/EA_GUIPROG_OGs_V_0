package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.GameFile;

import java.io.IOException;

public class GameMasterViewController {
    private static GameFile aktuellesSpiel;

    public static GameFile getAktuellesSpiel() {
        return aktuellesSpiel;
    }

    public void setAktuellesSpiel(GameFile aktuellesSpiel) {
        this.aktuellesSpiel = aktuellesSpiel;
    }


    @FXML
    private StackPane szenenPane;

    @FXML
    private AnchorPane stadtView;

    @FXML
    public void handleHilfe() throws IOException {
        SceneManager.changeScene("hilfe-view.fxml");
    }

    @FXML
    private void initialize() {
    }

    private void openSzene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = loader.load();

            // GebäudeController Zugriff
            //PaneController controller = loader.getController();
            //controller.setStadtController(this);

            stadtView.getChildren().setAll(pane);
            stadtView.setVisible(true);
            szenenPane.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeGebaeude() {
        stadtView.setVisible(false);
        szenenPane.setVisible(false);
        stadtView.getChildren().clear();
    }

    @FXML
    public void oeffneStartMenue() throws IOException {
        SceneManager.changeScene("startMenue-view.fxml");
    }
}
