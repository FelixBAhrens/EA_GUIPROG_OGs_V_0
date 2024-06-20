package control;

import javafx.fxml.FXML;

import java.io.IOException;

public class StoryController {
    @FXML
    public void initialize() {

    }

    @FXML
    public void handleFortfahren() throws IOException {
        SceneManager.changeScene("stadt-view.fxml");
    }
}
