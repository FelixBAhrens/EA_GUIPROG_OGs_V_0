package control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
    public enum KampfTyp
    {
        ENDGEGNER_KAMPF(Strings.ENDGEGNER),
        ANDERER_KAMPF(Strings.ANDERER),
        ARENA_KAMPF(Strings.ARENA);
        private final String beschreibung;

        KampfTyp (String beschreibung)
        {
            this.beschreibung = beschreibung;
        }
    }

    public static KampfTyp kampfTyp;
    private static Kaempfer spieler;
    private static Kaempfer gegner;

    private static final int GRID_SIZE = Konstanten.INT_TWELVE;
    private static final int TILE_SIZE = Konstanten.INT_FOURTY_FIVE;

    private static String nachKampfSzenenName;

    @FXML
    public ImageView hintergrund;
    @FXML
    private GridPane gridPane;
    @FXML
    private Rectangle spielerRec;
    @FXML
    private Rectangle gegnerRec;
    @FXML
    public AnchorPane kampfStartDialog;
    @FXML
    public AnchorPane kampfEndeDialog;
    @FXML
    public Text siegerText;

    @FXML
    public AnchorPane kaempferPane;
    @FXML
    public Label kaempferStats;
    @FXML
    public ProgressBar kaempferGesundheitsBar;

    @FXML
    public AnchorPane gegnerPane;
    @FXML
    public Label gegnerStats;
    @FXML
    public ProgressBar gegnerGesundheitsBar;

    /**
     * Methode, um den Kampf zu initialisieren.
     *
     * @author David Kien, Felix Ahrens
     */
    @FXML
    public void initialisiereKampf ()
    {
        kampfStartDialog.setVisible(false);
        createMap();
        switch (kampfTyp) {
            case ENDGEGNER_KAMPF -> starteEndgegnerKampf();
            case ANDERER_KAMPF -> starteAndererKampf();
            case ARENA_KAMPF -> starteArenaKampf();
        }
    }

    /**
     * Methode, um den Endgegnerkampf zu initialisieren
     *
     * @author David Kien, Felix Ahrens
     */
    public void starteEndgegnerKampf ()
    {
        this.spieler = Kaempfer.macheNeuenKaempferAusCharakter(GameFile.getInstanz().getLeader());
        this.gegner = Kaempfer.erstelleEndgegner();

        initialisiereCharacter();
        initialisiereGegner();
        updateCharacterPosition();
        updateKampfAnchorPanes();
    }

    /**
     * PLATZHALTER Methode um einen anderen Kampf zu starten
     *
     * @author Felix Ahrens
     */
    public void starteAndererKampf ()
    {

    }

    /**
     * Methode, um den Stand-Alone-Kampf ueber Netzwerk zu starten.
     *
     * @author Felix Ahrens
     */
    public void starteArenaKampf ()
    {
        //String imagePath = getClass().getResource(Strings.ARENA_BILDPFAD).toExternalForm();
        Image bild = new Image(getClass().getResource(Strings.ARENA_BILDPFAD).toExternalForm());
        hintergrund.setImage(bild);
        //this.spieler = Kaempfer.macheNeuenKaempferAusCharakter(GameFile.getInstance().getLeader());
        //this.gegner
        //Schnittstelle zum Netzwerk. Hier müssen die gegnerischen Werte dann geladen werden
    }

    /**
     * Methode zum abschliessen des Kampfes. Der Sieger wurde von der checkeLebtNoch-Methode ermittelt.
     *
     * @param sieger
     * @author Felix Ahrens
     */
    public void beendeKampf (Kaempfer sieger)
    {
        siegerText.setText(sieger.getName() + Strings.HAT_GEWONNEN);
        kampfEndeDialog.setVisible(true);
    }

    /**
     * Initialize-Methode, deren Verwendung fuer FXML-Controllerklassen verpflichtend ist.
     *
     * @param location
     * @param resources
     * @author David Kien, Felix Ahrens
     */
    @FXML
    public void initialize (URL location, ResourceBundle resources)
    {
        kampfStartDialog.setVisible(true);
        gridPane.sceneProperty().addListener(((observableValue, oldScene, newScene) ->
        {
            if (newScene != null) {
                newScene.setOnKeyPressed(this::handleKeyPress);
            }
        }));
    }

    @FXML
    private void eigenZug ()
    {

        //gegnerZug();
    }

    @FXML
    public void verlasseKampfSzene ()
    {
        SzenenManager.wechseleSzene(nachKampfSzenenName);
    }

    private void gegnerZug ()
    {

        bewegeGegnerDynamisch();
        attackiere(gegner, spieler);
        eigenZug();
    }

    /**
     * CreateMap-Methode
     *
     * @author David Kien
     */
    private void createMap ()
    {
        for (int row = Konstanten.INT_ZERO; row < GRID_SIZE; row++) {
            for (int col = Konstanten.INT_ZERO; col < GRID_SIZE; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setFill(Color.LIGHTGRAY);
                tile.setStroke(Color.BLACK);
                gridPane.add(tile, col, row);
            }
        }
    }

    /**
     * initialize Charakter - Methode
     *
     * @author David Kien
     */
    private void initialisiereCharacter ()
    {
        spielerRec = new Rectangle(TILE_SIZE, TILE_SIZE);
        spielerRec.setFill(Color.BLUE);
        gridPane.add(spielerRec, spieler.getxPosition(), spieler.getyPosition());
    }

    /**
     * InitialisiereGegner methode
     *
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
     *
     * @param keyEvent
     * @author David Kien
     */
    private void handleKeyPress (KeyEvent keyEvent)
    {
        switch (keyEvent.getCode()) {
            case W:
                if (spieler.getyPosition() > Konstanten.INT_ZERO)
                    spieler.setyPosition(spieler.getyPosition() - Konstanten.INT_ONE);
                break;
            case A:
                if (spieler.getxPosition() > Konstanten.INT_ZERO)
                    spieler.setxPosition(spieler.getxPosition() - Konstanten.INT_ONE);
                break;
            case S:
                if (spieler.getyPosition() < GRID_SIZE - Konstanten.INT_ONE)
                    spieler.setyPosition(spieler.getyPosition() + Konstanten.INT_ONE);
                break;
            case D:
                if (spieler.getxPosition() < GRID_SIZE - Konstanten.INT_ONE)
                    spieler.setxPosition(spieler.getxPosition() + Konstanten.INT_ONE);
                break;
            case Q:
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
     *
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
     *
     * @param kaempfer
     */
    private void wirkeMagie (Kaempfer kaempfer)
    {

    }

    public void attackiere (Kaempfer angreifer, Kaempfer verteidiger)
    {
        verwalteSchaden(angreifer, verteidiger);
    }

    public void wendeArtefaktAn (Kaempfer angreifer, Kaempfer verteidiger)
    {
        System.out.println(Strings.ARTEFAKT);
    }

    public void verwalteSchaden (Kaempfer angreifer, Kaempfer verteidiger)
    {
        int xentf = Math.abs(angreifer.getxPosition() - verteidiger.getxPosition());
        int yentf = Math.abs(angreifer.getyPosition() - verteidiger.getyPosition());
        double entfernung = Math.sqrt((Math.pow(xentf, Konstanten.INT_TWO)) + (Math.pow(yentf, Konstanten.INT_TWO)));
        int schaden = Konstanten.INT_ZERO;
        if (entfernung <= Konstanten.INT_THREE) {
            schaden = angreifer.getNahkampfWert();
        } else if (entfernung < Konstanten.INT_SIX & angreifer.getFernkaempfeVerbleibenZahl() > Konstanten.INT_ZERO) {
            schaden = angreifer.getFernkampfWert();
        }

        verteidiger.setGesundheit(verteidiger.getGesundheit() - schaden);
        updateKampfAnchorPanes();
        checkeLebtNoch();

    }

    /**
     * Methode, die ueberprueft, ob einer der im Kampf Beteiligten einen Gesundheitswert kleiner eins hat. Ist dies der
     * Fall, wird
     *
     * @param
     * @author Felix Ahrens
     */
    public void checkeLebtNoch ()
    {
        if (spieler.getGesundheit() < Konstanten.INT_ONE) {
            nachKampfSzenenName = Strings.FXML_PLAYER_REBORN;
            beendeKampf(gegner);
        } else if (gegner.getGesundheit() < Konstanten.INT_ONE) {
            nachKampfSzenenName = Strings.FXML_HAUPTQUARTIER;
            beendeKampf(spieler);
        }
    }

    /**
     * Methode, mit der der Gegner den kuerzesten Weg zum Spieler nimmt.
     *
     * @author Felix Ahrens
     */
    public void bewegeGegnerDynamisch ()
    {
        int gegnerX = gegner.getxPosition();
        int gegnerY = gegner.getyPosition();
        int spielerX = spieler.getxPosition();
        int spielerY = spieler.getyPosition();
        int xDiff = spielerX - gegnerX;
        int yDiff = spielerY - gegnerY;

        if (Math.abs(xDiff) > Math.abs(yDiff)) {
            if (xDiff > Konstanten.INT_ZERO && gegnerX + Konstanten.INT_ONE < GRID_SIZE) {
                gegner.setxPosition(gegnerX + Konstanten.INT_ONE);
            } else if (xDiff < Konstanten.INT_ZERO && gegnerX - Konstanten.INT_ONE >= Konstanten.INT_ZERO) {
                gegner.setxPosition(gegnerX - Konstanten.INT_ONE);
            }
        } else {
            if (yDiff > Konstanten.INT_ZERO && gegnerY + Konstanten.INT_ONE < GRID_SIZE) {
                gegner.setyPosition(gegnerY + Konstanten.INT_ONE);
            } else if (yDiff < Konstanten.INT_ZERO && gegnerY - Konstanten.INT_ONE >= Konstanten.INT_ZERO) {
                gegner.setyPosition(gegnerY - Konstanten.INT_ONE);
            }
        }
    }

    public void updateKampfAnchorPanes ()
    {
        kaempferGesundheitsBar.setProgress((double) spieler.getGesundheit() / (double) Konstanten.INT_ONE_HUNDRED);
        gegnerGesundheitsBar.setProgress((double) gegner.getGesundheit() / (double) Konstanten.INT_ONE_HUNDRED);
        System.out.println(gegner.getGesundheit());
    }

    /**
     * Methode zum warten.
     *
     * @param ms
     * @author Felix Ahrens
     */
    public void halteAn (long ms)
    {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // Unterbrechung behandeln
            e.printStackTrace();
        }
    }
}
