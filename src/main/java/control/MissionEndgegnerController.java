package control;

import javafx.fxml.FXML;
import res.Strings;

public class MissionEndgegnerController {
    @FXML
    public void initialize() {

    }

    @FXML
    public void starteKampf () {
        KampfController.kampfTyp = KampfController.KampfTyp.ENDGEGNER_KAMPF;
        SceneManager.changeScene(Strings.FXML_KAMPF);
    }
}
