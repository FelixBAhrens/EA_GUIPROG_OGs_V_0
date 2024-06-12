package control;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class Hilfencontroller {
    @FXML
    public void initialize(){
        zeigeHilfeText();
    }

    @FXML
    public void handleZurueck(){
        SceneManager.goBack();
    }

    public static void zeigeHilfeText(){
        //hier soll dann ein hilfe-text ins textfeld geladen werden, der der szene entspricht.
    }


}
