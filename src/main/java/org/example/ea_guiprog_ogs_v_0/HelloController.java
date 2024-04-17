package org.example.ea_guiprog_ogs_v_0;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onFortfahrenButtonClick() {
        welcomeText.setText("Waehle bitte den Schwierigkeitsgrad.");
    }


}