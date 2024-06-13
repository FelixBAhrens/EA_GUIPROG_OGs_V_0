package control;

import javafx.fxml.FXML;
import model.Charakter;

public class SchenkenController extends GebaeudeController {

    @FXML
    public void initialize() {

    }

    @FXML
    public void openMedic(){
        CharakterController charakterController = new CharakterController();
        charakterController.zeigeCharakterWerte(new Charakter("medic", 1,1,1,1,1,1,1,1,1,1));
        charakterController.setzeDisplaySichtbar(true);

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
