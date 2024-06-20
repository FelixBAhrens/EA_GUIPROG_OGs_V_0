package control;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Charakter;
import model.GameFile;

public class TrainingsGelaendeController extends PaneController {

    private int gesammeltesHolz;
    private int gesammelteNahrung;
    private int gesammeltesGold;
    @FXML
    private Text gesammelteObjekte;

    @FXML
    public void initialize()
    {
        speichereSpielstand();

        try
        {
            gesammeltesHolz = GameFile.getInstance().getHolzRessource();
            gesammelteNahrung = GameFile.getInstance().getGesundheitRessource();
            gesammeltesGold = GameFile.getInstance().getGoldRessource();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        gesammelteObjekte.setText("Holz: " + gesammeltesHolz + ", Nahrung: " + gesammelteNahrung + ", Gold: " + gesammeltesGold);


    }

    @FXML
    private void gesundheitVerbessern ()
    {

    }

    @FXML
    private void schildVerbessern ()
    {


    }

    @FXML
    private void manapunkteVerbessern ()
    {

    }

    @FXML
    private void nahkampfWertVerbessern ()
    {

    }

    @FXML
    private void fernkampfWertVerbessern ()
    {

    }

    @FXML
    private void fernkaempfeZahlVerbessern ()
    {

    }

    @FXML
    private void zahlAusweichenVerbessern ()
    {

    }

    @FXML
    private void magieResistenzVerbessern ()
    {

    }

    @FXML
    private void bewegungsWeiteVerbessern ()
    {

    }

}
