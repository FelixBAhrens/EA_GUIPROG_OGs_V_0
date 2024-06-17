package control;

import javafx.fxml.FXML;

import java.io.IOException;

public class HauptquartierController extends GebaeudeController
{
    @FXML
    public void initialize()
    {

    }

    @FXML
    public void openKarte() throws IOException {
        SceneManager.changeScene("map-view.fxml");
    }
}
