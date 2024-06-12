package control;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class StoryController {
    @FXML
    public void initialize() {

    }

    @FXML
    public void handleFortfahren(MouseEvent event) throws IOException {
        SceneManager.changeScene("stadt-view.fxml");
    }
}
