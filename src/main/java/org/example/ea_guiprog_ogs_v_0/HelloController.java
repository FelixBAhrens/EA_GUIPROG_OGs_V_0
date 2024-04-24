package org.example.ea_guiprog_ogs_v_0;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    protected void onFortfahrenButtonClick() {
        welcomeText.setText("Fortfahren!");
    }
    @FXML
    protected void onNeuesSpielButtonClick() {
        welcomeText.setText("Neues Spiel! Waehle bitte den Schwierigkeitsgrad.");
    }
    @FXML
    protected void onSpielLadenButtonClick() {
        welcomeText.setText("Waehle bitte den Schwierigkeitsgrad.");
    }
    @FXML
    protected void onEinstellungenButtonClick() {
        welcomeText.setText("Waehle bitte den Schwierigkeitsgrad.");
    }
    @FXML
    protected void onBeendenButtonClick() {
        System.exit(0);
    }

}