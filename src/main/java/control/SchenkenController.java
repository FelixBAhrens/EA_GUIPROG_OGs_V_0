package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.GameFile;
import res.Strings;

import java.io.IOException;

public class SchenkenController extends PaneController
{


    @FXML
    private AnchorPane charakterDisplay;
    private CharakterDisplayController charakterDisplayController;


    @FXML
    public void initialize ()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Strings.FXML_CHARAKTERDISPLAY));
        try {
            charakterDisplay = loader.load();
            charakterDisplayController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void openMedic () throws Exception
    {
        System.out.println(Strings.OPEN_MEDIC);
        charakterDisplayController.zeigeCharakterWerte(GameFile.getInstanz().getMedic());
        System.out.println(GameFile.getInstanz().getMedic().toString());
        charakterDisplayController.setzeDisplaySichtbar(true);
        charakterDisplay.setVisible(true);
    }

    @FXML
    public void openHunter ()
    {

    }

    @FXML
    public void openEngineer ()
    {

    }

    @FXML
    public void openScout ()
    {

    }
}
