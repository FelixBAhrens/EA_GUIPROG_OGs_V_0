package control;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Charakter;

public class SchenkenController extends GebaeudeController {

    @FXML
    private AnchorPane charakterDisplay;
    @FXML
    private Pane medic;

    private CharakterController charakterController;

    @FXML
    public void initialize() {
    }

    @FXML
    public void openMedic(){
        System.out.println("open medic");
        CharakterController charakterController = new CharakterController();
        charakterController.zeigeCharakterWerte(new Charakter("medic", 1,1,1,1,1,1,1,1,1,1));
        charakterController.setzeDisplaySichtbar(true);
        charakterDisplay.requestFocus();
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
