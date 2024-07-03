package control;

import javafx.fxml.FXML;
import res.Strings;

public class MissionStartenController
{
    @FXML
    private void handleJa () throws Exception
    {
        KampfController.kampfTyp = KampfController.KampfTyp.ANDERER_KAMPF;
        SzenenManager.wechseleSzene(Strings.FXML_KAMPF);
    }

    @FXML
    private void handleNein ()
    {

    }

}
