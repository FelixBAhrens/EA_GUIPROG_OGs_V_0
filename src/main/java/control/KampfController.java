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
    public static boolean endGegnerKampf;
    private int gegnerleben = Konstanten.INT_TEN;
    private Kaempfer spieler;
    private Kaempfer gegner;

    private static final int GRID_SIZE = Konstanten.INT_TWELVE;
    private static final int TILE_SIZE = Konstanten.INT_FOURTY_FIVE;
    private int characterX = Konstanten.INT_ZERO;
    private int characterY = Konstanten.INT_ZERO;

    @FXML
    private GridPane gridPane;
    @FXML
    private Rectangle spielerRec;
    @FXML
    private Rectangle gegnerRec;
    @FXML
    private static Text amZugText;


    public void initialisiereKampf (Kaempfer spieler, Kaempfer gegner) {
        this.spieler = spieler;
        this.gegner = gegner;
        createMap();
        initializeCharacter();
        updateCharacterPosition();

    }

    public void beendeKampf () {

    }


    @FXML
    public void initialize (URL location, ResourceBundle resources)
    {
        spieler = Kaempfer.macheNeuenKaempferAusCharakter(GameFile.getInstance().getLeader());
        gegner = Kaempfer.erstelleEndgegner();
        createMap();
        initializeCharacter();
        initialisiereGegner();
        initialisiereKampf(spieler, gegner);

        gridPane.sceneProperty().addListener(((observableValue, oldScene, newScene) ->
        {
            if (newScene != null)
            {
                newScene.setOnKeyPressed(this::handleKeyPress);
            }
        }));
    }

    private void eigenZug (){
        zeigeEigenZug();

        gegnerZug();
    }

    private void gegnerZug () {
        zeigeGegnerZug();

        eigenZug();
    }

    public static void zeigeEigenZug () {
        amZugText.setText(Strings.AMZUG_DU);

    }

    public static void zeigeGegnerZug () {
        amZugText.setText(Strings.AMZUG_DU);
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
        gridPane.add(spielerRec, characterX, characterY);
    }

    private void initialisiereGegner () {
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
                fuehreFernkampfAus();
                System.out.println(gegnerleben);
        }
        updateCharacterPosition();
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

    public void fuehreFernkampfAus () {
        if (spieler.getyPosition() == gegner.getyPosition()) {
            gegnerleben++;
        }
    }

    /**
     * README:
     * - Artefakte koennen im Kampf angewendet werden
     */
}
