package control;

import javafx.fxml.FXML;
import model.GameFile;

public class SchmiedenController extends PaneController{
    @FXML
    public void initialize() {

    }

    public void gebeArteFaktAus () throws Exception {
        System.out.println(GameFile.getInstance().getSchwert().toString());
    }
}
