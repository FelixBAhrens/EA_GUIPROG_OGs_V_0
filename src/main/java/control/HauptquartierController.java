package control;

import javafx.fxml.FXML;

import java.io.IOException;

public class HauptquartierController extends PaneController
{
    @FXML
    public void initialize()
    {

    }

    @FXML
    public void openKarte() throws IOException {
        SceneManager.changeScene("karten-view.fxml");
    }
}
