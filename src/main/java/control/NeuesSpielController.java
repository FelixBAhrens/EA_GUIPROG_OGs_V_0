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
    private Slider threePositionSlider;

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

    public class Storycontroller {
        @FXML
        public void initialize() {

        }
        @FXML
        public void handleFortfahren(ActionEvent event) throws IOException {
            System.exit(0);
        }
    }

}
