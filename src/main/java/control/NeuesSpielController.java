package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.GameFile;
import res.Strings;

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
    public void handleSchwierigkeit (MouseEvent mouseEvent) {
        Button source = (Button) mouseEvent.getSource();
        System.out.println(source.getText());
        GameFile.setzeGameFile(GameFile.erstelleNeueGameFile(source.getText()));
        SceneManager.changeScene(Strings.FXML_TUTORIAL);
    }


    @FXML
    public void handleZurueck(){
        SceneManager.goBack();
    }

}
