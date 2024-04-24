package org.example.ea_guiprog_ogs_v_0;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Fortfahren");
    }
    @FXML
    protected void onFortfahrenButtonClick() {
        welcomeText.setText("Neues Spiel");
    }


}