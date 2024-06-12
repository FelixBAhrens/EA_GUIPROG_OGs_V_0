package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import model.GameFile;

import java.io.IOException;

public class NeuesSpielController {
    private String schwierigkeit;
    public String getSchwierigkeit(){
        return schwierigkeit;
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void handleKnopf(ActionEvent event) throws IOException {
        //Button clickedButton = (Button) event.getSource();
        //String buttonText = clickedButton.getText();
        //schwierigkeit = buttonText;
        System.out.println("Schwierigkeit: ");
        //SceneManager.changeScene("story-view.fxml");
    }


    @FXML
    public void zurueckHandler(ActionEvent event) throws IOException{
        SceneManager.goBack();
    }
}
