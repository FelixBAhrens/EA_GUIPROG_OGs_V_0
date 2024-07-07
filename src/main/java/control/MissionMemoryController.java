package control;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import res.Konstanten;
import res.Strings;


public class MissionMemoryController
{
    private boolean ersterSteinUmgedreht = false;
    private AnchorPane ersterStein;

    @FXML
    private AnchorPane stein0a;
    @FXML
    private AnchorPane stein0b;
    @FXML
    private AnchorPane stein1a;
    @FXML
    private AnchorPane stein1b;
    @FXML
    private AnchorPane stein2a;
    @FXML
    private AnchorPane stein2b;
    @FXML
    private AnchorPane stein3a;
    @FXML
    private AnchorPane stein3b;
    @FXML
    private AnchorPane stein4a;
    @FXML
    private AnchorPane stein4b;
    @FXML
    private AnchorPane stein5a;
    @FXML
    private AnchorPane stein5b;
    @FXML
    private AnchorPane stein6a;
    @FXML
    private AnchorPane stein6b;
    @FXML
    private AnchorPane stein7a;
    @FXML
    private AnchorPane stein7b;


    @FXML
    public void initialize ()
    {

    }

    @FXML
    public void handleStein (MouseEvent event)
    {
        AnchorPane steinPane = (AnchorPane) event.getSource();
        if (!ersterSteinUmgedreht)
        {
            ersterStein = steinPane;
            ersterSteinUmgedreht = true;
            System.out.println(ersterSteinUmgedreht);
        } else
        {
            System.out.println(ersterStein.getId().toString().charAt(Konstanten.INT_FIVE));
            System.out.println(steinPane.getId().toString().charAt(Konstanten.INT_FIVE));
            if (ersterStein.getId().toString().charAt(Konstanten.INT_FIVE) == (steinPane.getId().toString().charAt(Konstanten.INT_FIVE)))
            {
                System.out.println(Strings.TOLL);

                ersterStein.setVisible(false);
                steinPane.setVisible(false);
            }
            ersterSteinUmgedreht = false;
            System.out.println(ersterSteinUmgedreht);
        }
    }
}
