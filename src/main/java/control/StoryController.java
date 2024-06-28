package control;

import javafx.fxml.FXML;
import res.Strings;

import java.io.IOException;

public class StoryController {
    @FXML
    public void initialize() {

    }

    @FXML
    public void handleFortfahren() {
        SceneManager.changeScene(Strings.FXML_STADT);
    }
}
