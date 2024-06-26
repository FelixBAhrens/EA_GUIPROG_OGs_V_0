package control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.GameFile;
import model.Kaempfer;
import res.Konstanten;
import res.Strings;

import java.net.URL;
import java.util.ResourceBundle;

// ControllerController
public class KampfController extends ControllerController implements Initializable
{
    public enum KampfTyp {
        ENDGEGNER_KAMPF(Strings.ENDGEGNER),
        ANDERER_KAMPF(Strings.ANDERER);
        private final String beschreibung;

        KampfTyp(String beschreibung) {
            this.beschreibung = beschreibung;
        }
    }

    public static KampfTyp kampfTyp;
    private Kaempfer spieler;
    private Kaempfer gegner;

    private static final int GRID_SIZE = Konstanten.INT_TWELVE;
    private static final int TILE_SIZE = Konstanten.INT_FOURTY_FIVE;

    @FXML
    private GridPane gridPane;
    @FXML
    private Rectangle spielerRec;
    @FXML
    private Rectangle gegnerRec;
    @FXML
    private static Text amZugText;


    public void initialisiereKampf ()
    {
        switch (kampfTyp)
        {
            case ENDGEGNER_KAMPF -> starteEndgegnerKampf();
            case ANDERER_KAMPF -> starteAndererKampf();
        }
    }

    public void starteEndgegnerKampf ()
    {
        this.spieler = Kaempfer.macheNeuenKaempferAusCharakter(GameFile.getInstance().getLeader());;
        this.gegner = Kaempfer.erstelleEndgegner();

        initializeCharacter();
        initialisiereGegner();
        updateCharacterPosition();
    }

    public void starteAndererKampf () {

    }

    public void beendeKampf () {

    }


    @FXML
    public void initialize (URL location, ResourceBundle resources)
    {
        createMap();
        initialisiereKampf();
        gridPane.sceneProperty().addListener(((observableValue, oldScene, newScene) ->
        {
            if (newScene != null)
            {
                newScene.setOnKeyPressed(this::handleKeyPress);
            }
        }));
    }

    @FXML
    private void eigenZug (){
        zeigeEigenZug();

        //gegnerZug();
    }

    private void gegnerZug () {
        zeigeGegnerZug();
        attackiere(gegner, spieler);
        eigenZug();
    }

    public void zeigeEigenZug () {
        System.out.println(Strings.AMZUG_DU);
        System.out.println(Strings.SPIELER_GESUNDHEIT + spieler.getGesundheit());
        //amZugText.setText(Strings.AMZUG_DU);

    }

    public void zeigeGegnerZug () {
        System.out.println(Strings.AMZUG_GEGNER);
        System.out.println(Strings.GEGNER_GESUNDHEIT + gegner.getGesundheit());
        //amZugText.setText(Strings.AMZUG_GEGNER);
    }

    /**
     * CreateMap-Methode
     * @author David Kien
     */
    private void createMap ()
    {
        for (int row = Konstanten.INT_ZERO; row < GRID_SIZE; row++)
        {
            for (int col = Konstanten.INT_ZERO; col < GRID_SIZE; col++)
            {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setFill(Color.LIGHTGRAY);
                tile.setStroke(Color.BLACK);
                gridPane.add(tile, col, row);
            }
        }
    }

    /**
     * initialize Charakter - Methode
     * @author David Kien
     */
    private void initializeCharacter ()
    {
        spielerRec = new Rectangle(TILE_SIZE, TILE_SIZE);
        spielerRec.setFill(Color.BLUE);
        gridPane.add(spielerRec, spieler.getxPosition(), spieler.getyPosition());
    }

    /**
     * InitialisiereGegner methode
     * @author Felix Ahrens
     */
    private void initialisiereGegner ()
    {
        gegnerRec = new Rectangle(TILE_SIZE, TILE_SIZE);
        gegnerRec.setFill(Color.RED);
        gridPane.add(gegnerRec, gegner.getxPosition(), gegner.getyPosition());
    }

    /**
     * Handler fuer ein KeyEvent
     * @param keyEvent
     * @author David Kien
     */
    private void handleKeyPress (KeyEvent keyEvent)
    {
        switch (keyEvent.getCode())
        {
            case W:
                if (spieler.getyPosition() > Konstanten.INT_ZERO) spieler.setyPosition(spieler.getyPosition() - Konstanten.INT_ONE);
                break;
            case A:
                if (spieler.getxPosition() > Konstanten.INT_ZERO) spieler.setxPosition(spieler.getxPosition() - Konstanten.INT_ONE);
                break;
            case S:
                if (spieler.getyPosition() < GRID_SIZE - Konstanten.INT_ONE) spieler.setyPosition(spieler.getyPosition() + Konstanten.INT_ONE);
                break;
            case D:
                if (spieler.getxPosition() < GRID_SIZE - Konstanten.INT_ONE) spieler.setxPosition(spieler.getxPosition() + Konstanten.INT_ONE);
                break;
            case ENTER:
                attackiere(spieler, gegner);
                break;
            case P:
                wendeArtefaktAn(spieler, gegner);
                break;
        }
        updateCharacterPosition();
        gegnerZug();
    }

    /**
     * Methode zum Aktualisieren der Position des Charakters
     * @author David Kien
     */
    private void updateCharacterPosition ()
    {
        GridPane.setColumnIndex(spielerRec, spieler.getxPosition());
        GridPane.setRowIndex(spielerRec, spieler.getyPosition());
        GridPane.setColumnIndex(gegnerRec, gegner.getxPosition());
        GridPane.setRowIndex(gegnerRec, gegner.getyPosition());
    }

    /**
     * Methode zum Wirken von Magie
     * @param kaempfer
     */
    private void wirkeMagie (Kaempfer kaempfer) {

    }

    public void attackiere(Kaempfer angreifer, Kaempfer verteidiger) {
        verwalteSchaden(angreifer, verteidiger);
    }

    public void wendeArtefaktAn (Kaempfer angreifer, Kaempfer verteidiger) {
        System.out.println(Strings.ARTEFAKT);
    }

    public void verwalteSchaden (Kaempfer angreifer, Kaempfer verteidiger) {
        int xentf = Math.abs(angreifer.getxPosition()-verteidiger.getxPosition());
        int yentf = Math.abs(angreifer.getyPosition()-verteidiger.getyPosition());
        double entfernung = Math.sqrt((Math.pow(xentf, Konstanten.INT_TWO))+(Math.pow(yentf, Konstanten.INT_TWO)));
        int schaden = Konstanten.INT_ZERO;
        if (entfernung <= Konstanten.INT_THREE) {
            schaden = angreifer.getNahkampfWert();
        } else if (entfernung < Konstanten.INT_SIX & angreifer.getFernkaempfeVerbleibenZahl() > Konstanten.INT_ZERO) {
            schaden = angreifer.getFernkampfWert();
        }

        verteidiger.setGesundheit(verteidiger.getGesundheit() - schaden);
        checkeLebtNoch(verteidiger);
    }

    public void checkeLebtNoch (Kaempfer verwundeter) {
        if (verwundeter.getGesundheit() <= Konstanten.INT_ZERO) {
            System.out.println(Strings.TOT);
            if (spieler.getGesundheit() <= Konstanten.INT_ZERO) {
                SceneManager.changeScene(Strings.FXML_PLAYER_REBORN);
            }
            if (gegner.getGesundheit() <= Konstanten.INT_ZERO) {
                SceneManager.changeScene(Strings.FXML_HAUPTQUARTIER);
            }
        }
    }

    public void halteAn (long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // Unterbrechung behandeln
            e.printStackTrace();
        }
    }

    /**
     * README:
     * - Artefakte koennen im Kampf angewendet werden
     */
}
