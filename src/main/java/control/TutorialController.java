package control;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TutorialController
{
    @FXML
    private TextArea tutorialTextArea;
    private Stage stage;
    private Scene menueScene;

    @FXML
    public void initialize (Stage stage, Scene menueScene)
    {
        this.stage = stage;
        this.menueScene = menueScene;
    }

    @FXML
    public void handleZurueck ()
    {
        SceneManager.goBack();
    }


}
