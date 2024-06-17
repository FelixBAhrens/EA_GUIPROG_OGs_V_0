package control;

import javafx.fxml.FXML;

import java.io.IOException;

public class SchmiedenController extends PaneController{
    @FXML
    public void initialize() {

    }

    @FXML
    public void handleHilfe() throws IOException {
        SceneManager.changeScene("hilfe-view.fxml");
    }
}
