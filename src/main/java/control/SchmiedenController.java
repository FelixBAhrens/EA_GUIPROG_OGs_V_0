package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.GameFile;

public class SchmiedenController extends PaneController {
    @FXML
    public void zeigeArtefakt (ActionEvent event) {
        /**
        switch (){
            case()
        }
         */

    }

    @FXML
    public void initialize() {

    }

    public void gebeArteFaktAus () throws Exception {
        System.out.println(GameFile.getInstance().getSchwert().toString());
    }


}
