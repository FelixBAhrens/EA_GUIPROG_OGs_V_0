package control;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.GameFile;


public class StadtController
{
    private Stage stage;
    private GameFile spielDatei;
    private ImageView imageView;



    public void initialize (Stage stage)
    {
        //this.spielDatei = GameFile.erstelleNeueGameFile(NeuesSpielController.getSchwierigkeit());
        this.stage = stage;
    }

    @FXML
    public void handleSpielBeenden(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
