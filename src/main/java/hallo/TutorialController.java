package hallo;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TutorialController
{
    @FXML
    private TextArea tutorialTextArea;
    private Stage stage;
    private Scene menuScene;

    public void initialize (Stage stage, Scene menuScene)
    {
        this.stage = stage;
        this.menuScene = menuScene;
    }

    @FXML
    public void handleZurueck ()
    {
        stage.setScene(menuScene);
    }

    public void setTutorialText (String text)
    {
        tutorialTextArea.setText(text);
    }

}
