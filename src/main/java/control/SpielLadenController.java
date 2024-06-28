package control;

import javafx.fxml.FXML;
import model.GameFile;

import java.io.IOException;

public class SpielLadenController extends ControllerController {
    @FXML
    public void initialize()  {
    }

    @FXML
    public void handleKnopf() throws IOException{
        System.out.println(GameFile.gebeLetztesSpielZurueck());
    }


}
