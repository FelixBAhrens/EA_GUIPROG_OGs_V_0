package control;

import javafx.fxml.FXML;
import model.Artefakt;
import model.GameFile;
import res.Konstanten;
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

    /**
     * Methode zum setzen aller Ressourcen auf ein Tausend des jeweiligen Wertes. Zu Debugzwecken.
     * @Author Felix Ahrens
     */
    @FXML
    public void handleInfiniteMoney (){
        GameFile.getInstanz().setHolzRessource(Konstanten.INT_ONE_THOUSAND);
        GameFile.getInstanz().setSteinRessource(Konstanten.INT_ONE_THOUSAND);
        GameFile.getInstanz().setGoldRessource(Konstanten.INT_ONE_THOUSAND);
        GameFile.getInstanz().setGesundheitRessource(Konstanten.INT_ONE_THOUSAND);
        GameFile.getInstanz().setBanonasRessource(Konstanten.INT_ONE_THOUSAND);
    }

    /**
     * Methode, die alle Artefakte auf "imBesitz" setzt. Zu Debugzwecken.
     * @Author Felix Ahrens
     */
    @FXML
    public void handleAlleArtefakteBekommen () {
        GameFile instanz = GameFile.getInstanz();
        Artefakt[] artefakte = {instanz.getSchwert(), instanz.getStatue(),  instanz.getRing()};
        for (Artefakt artefakt : artefakte){
            artefakt.setImBesitz(true);
        }
    }
}
