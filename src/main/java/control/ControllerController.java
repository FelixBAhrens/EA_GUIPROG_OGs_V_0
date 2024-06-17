package control;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Trust me on this, it will be pure greatness.
 * @author Felix
 */
public class ControllerController {

    @FXML
    public void handleHilfe() throws IOException {
        SceneManager.changeScene("hilfe-view.fxml");
    }


}
