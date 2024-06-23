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
    public void openKarte() {
        SceneManager.changeScene("karteNew-view.fxml");
    }
}
