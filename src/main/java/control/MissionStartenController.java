package control;

// NOT COMPLETED

import javafx.fxml.FXML;
import res.Strings;

/**
 * MissionStartenController
 *
 * @Author David Kien
 */
public class MissionStartenController
{
    @FXML
    private void handleJa () throws Exception
    {
        KampfController.kampfTyp = KampfController.KampfTyp.ANDERER_KAMPF;
        SzenenManager.wechseleSzene(Strings.FXML_KAMPF);
    }

    //@Author David Kien
    @FXML
    private void handleNein ()
    {

    }

}
