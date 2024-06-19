package control;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.GameFile;

public class SchenkenController extends PaneController {

    @FXML
    private AnchorPane charakterDisplay;
    @FXML
    private Pane medic;

    private CharakterController charakterController;

    @FXML
    public void initialize() {
    }

    @FXML
    public void openMedic() throws Exception {
        System.out.println("open medic");
        CharakterController charakterController = new CharakterController();
        charakterController.zeigeCharakterWerte(GameFile.getInstance().getMedic());
        charakterController.setzeDisplaySichtbar(true);
        charakterDisplay.setVisible(true);
    }

    @FXML
    public void openHunter(){

    }

    @FXML
    public void openEngineer(){

    }
    @FXML
    public void openScout(){

    }
}
