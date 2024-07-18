package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.GameFile;
import res.Konstanten;
import res.Strings;

/**
 * Controllerklasse der "fraktionen-camp-view.fxml".
 *
 * @author Felix Ahrens, David Kien
 */
public class FraktionenCampController extends PaneController
{
    @FXML
    public AnchorPane baustelle;
    @FXML
    public AnchorPane AnchPaneFraktionA;
    @FXML
    public AnchorPane AnchPaneFraktionB;
    @FXML
    public AnchorPane AnchPaneFraktionC;

    @FXML
    public VBox VBoxFraktionA;
    @FXML
    public VBox VBoxFraktionB;
    @FXML
    public VBox VBoxFraktionC;
    @FXML
    public Button fraktionAButton;
    @FXML
    public Button fraktionBButton;
    @FXML
    public Button fraktionCButton;

    // @Author David Kien
    @FXML
    public void initialize ()
    {
        if (GameFile.getInstanz().fraktionenCampIstFreigeschaltet())
        {
            baustelle.setVisible(false);
        }
        else
        {
            baustelle.setVisible(true);
        }
    }

    @FXML
    public void zeigeFraktion (ActionEvent event) {
        AnchPaneFraktionA.setVisible(false);
        AnchPaneFraktionB.setVisible(false);
        AnchPaneFraktionC.setVisible(false);
        System.out.println(((Button)event.getSource()).getId().split(Strings.UNTERSTRICH)[Konstanten.INT_ONE]);
        switch (((Button)event.getSource()).getId().split(Strings.UNTERSTRICH)[Konstanten.INT_ONE]){
            case Strings.FRAKTION_A -> AnchPaneFraktionA.setVisible(true);
            case Strings.FRAKTION_B -> AnchPaneFraktionB.setVisible(true);
            case Strings.FRAKTION_C -> AnchPaneFraktionC.setVisible(true);
        }
    }

    @FXML
    public void zeigeDetail (MouseEvent event){

    }

    @FXML
    public void entferneDetail (MouseEvent event)
    {

    }

    @FXML
    public void handleAufbau(){

    }

    @FXML
    public void handleAbzug(){

    }

    // @Author David Kien
    @FXML
    public void schalteBaustelleFrei ()
    {
        GameFile instanz = GameFile.getInstanz();
        if (instanz.getHolzRessource() >= Konstanten.INT_FIFTY && instanz.getGoldRessource() >= Konstanten.INT_FIVE)
        {
            instanz.setHolzRessource(instanz.getHolzRessource() - Konstanten.INT_FIFTY);
            instanz.setGoldRessource(instanz.getGoldRessource() - Konstanten.INT_FIVE);
            baustelle.setVisible(false);
        }
    }
}
