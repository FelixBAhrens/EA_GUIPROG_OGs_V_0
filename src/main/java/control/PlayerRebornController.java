package control;

import javafx.fxml.FXML;
import res.Strings;

public class PlayerRebornController extends ControllerController{
    @FXML
    public void initialize() {

    }

    @FXML
    public void handleZurStadt(){
        SceneManager.changeScene(Strings.FXML_STADT);
    }
}
