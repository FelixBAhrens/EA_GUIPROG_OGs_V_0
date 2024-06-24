package control;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import res.Strings;

public class HauptquartierController extends PaneController
{
    @FXML
    private Text detailText;

    @FXML
    public void initialize()
    {

    }

    @FXML
    public void zeigeMissionsDetail (MouseEvent event) {
        detailText.setText(event.getSource().toString());
    }

    @FXML
    public void verbergeMissionsDetails (MouseEvent event) {
        detailText.setText("");
    }

    @FXML
    public void openKarte() {
        SceneManager.changeScene(Strings.FXML_KARTENEW);
    }

    @FXML
    public void ladeMissionFlappyBird  (MouseEvent event) {
        SceneManager.changeScene(Strings.FXML_MISSION_FLAPPYBIRD);
    }

    @FXML
    public void ladeMissionMemory (MouseEvent event) {
        SceneManager.changeScene(Strings.FXML_MISSION_MEMORY);
    }

    @FXML
    public void ladeMissionSammeln (MouseEvent event) {
        SceneManager.changeScene(Strings.FXML_MISSION_SAMMELN);
    }

    @FXML
    public void ladeMissionEndgegner  (MouseEvent event) {
        SceneManager.changeScene(Strings.FXML_MISSION_ENDGEGNER);
    }

}
