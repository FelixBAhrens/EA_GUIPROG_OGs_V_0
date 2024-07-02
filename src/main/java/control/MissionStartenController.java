package control;

import javafx.fxml.FXML;
import res.Strings;

public class MissionStartenController
{
    @FXML
    private void handleJa () throws Exception
    {
        SzenenManager.wechseleSzene(Strings.FXML_KAMPF);
    }

    @FXML
    private void handleNein ()
    {

    }

}
