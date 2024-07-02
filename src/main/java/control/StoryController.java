package control;

import javafx.fxml.FXML;
import res.Strings;

public class StoryController {
    @FXML
    public void initialize() {

    }

    @FXML
    public void handleFortfahren() {
        SzenenManager.wechseleSzene(Strings.FXML_STADT);
    }
}
