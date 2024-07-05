package control;

import javafx.fxml.FXML;
import model.GameFile;
import res.Strings;

public class KlassischesMenueController extends StartMenueController
{
    @FXML
    public void initialize ()
    {

    }

    @FXML
    public void handleDebugStadt ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_STADT);
    }

    @FXML
    public void handleDebugKarte ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_KARTE);
    }

    @FXML
    public void handleDebugKampf ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_KAMPF);
    }

    @FXML
    public void handleDebugArena ()
    {
        SzenenManager.wechseleSzene(Strings.FXML_ARENA);
    }

    @FXML
    public void handleDebugEndgegner ()
    {
        setzeGameFileInstanzLogisch();
        KampfController.kampfTyp = KampfController.KampfTyp.ENDGEGNER_KAMPF;
        SzenenManager.wechseleSzene(Strings.FXML_KAMPF);
    }

    @FXML
    public void gameFileAusgeben ()
    {
        System.out.println(GameFile.getInstanz().toString());
    }

}
