package control;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import res.Strings;

/**
 * Controllerklasse fuer die "tutorial-view.fxml"-Datei.
 *
 * @Author David Kien, Enes Oezcan
 */
public class TutorialController
{
    @FXML
    private TextArea tutorialTextArea;
    private Stage stage;
    private Scene menueScene;

    /**
     * @param stage
     * @param menueScene
     * @Author Enes Oezcan, David Kien
     */
    @FXML
    public void initialize (Stage stage, Scene menueScene)
    {
        this.stage = stage;
        this.menueScene = menueScene;
    }

    // @Author David Kien
    @FXML
    public void handleWeiter ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_STORY);
    }

    //@Author David Kien
    @FXML
    public void handleZurueck ()
    {
        SzenenManager.szeneZurueck();
    }


}
