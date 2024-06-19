package control;

import javafx.fxml.FXML;

public class EinstellungenController {
    @FXML
    public void initialize (){

    }
    @FXML
    public void schwierigkeitAusgabeHandler(){
        SchwierigkeitController.gebeSchwierigkeitAus();
    }

    @FXML
    public void zurueckHandler(){
        SceneManager.goBack();
    }
}


