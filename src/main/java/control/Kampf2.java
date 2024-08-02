package control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Charakter;
import model.GameFile;
import model.Kaempfer;
import res.Konstanten;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;


public class Kampf2 extends ControllerController implements Initializable
{

    public static int yPositionSpieler;
    public static int xPositionSpieler;
    public static int yPositionGegner;
    public static int xPositionGegner;
    public static Kaempfer spieler;
    public static Kaempfer gegner;
    private Kaempfer angreifer;
    private Kaempfer verteidiger;
    private boolean spielerZug = true;
    private static final int GRID_SIZE = Konstanten.INT_TWELVE;
    @FXML
    private Rectangle spielerPosition;
    @FXML
    private Rectangle gegnerPosition;
    @FXML
    private GridPane spielFeld;
    @FXML
    private ProgressBar gesundheitsBar;
    private double gesundheit;
    @FXML
    private ProgressBar manaBar;
    private double mana;
    @FXML
    private ProgressBar angriffeÜbrigBar;
    private double angriffeÜbrig;
    @FXML
    private ProgressBar magieAngriffeÜbrigBar;
    private double magieAngriffeÜbrig;
    @FXML
    private ProgressBar fernkampfAngriffeÜbrigBar;
    private double fernkampfAngriffeÜbrig;
    @FXML
    private ProgressBar ringBar;
    private double ring;
    @FXML
    private ProgressBar schwertBar;
    private double schwert;
    @FXML
    private ProgressBar statueBar;
    private double statue;
    @FXML
    private ProgressBar gegnerGesundheitsBar;
    private double gegnerGesundheit;
    @FXML
    public AnchorPane kampfStartDialog;

    public static void attackiere(Charakter angreifer, Charakter verteidiger) {
            int xEntfernung;
            int yEntfernung;
            int angriffeÜbrig = Konstanten.INT_ONE;
            if (xPositionSpieler <= xPositionGegner) {
                xEntfernung = xPositionGegner - xPositionSpieler;
            } else {
                xEntfernung = xPositionSpieler - xPositionGegner;
            }
            if (yPositionSpieler <= yPositionGegner) {
                yEntfernung = yPositionGegner - yPositionSpieler;
            } else {
                yEntfernung = yPositionSpieler - yPositionGegner;
            }
            if (xEntfernung <= Konstanten.INT_ONE && yEntfernung <= Konstanten.INT_ONE && angriffeÜbrig == Konstanten.INT_ONE) {
                verteidiger.setGesundheit(verteidiger.getGesundheit() - angreifer.getNahkampfWert());
                angriffeÜbrig--;
            } else if (xEntfernung <= Konstanten.INT_THREE && yEntfernung <= Konstanten.INT_THREE && angriffeÜbrig == Konstanten.INT_ONE) {
                verteidiger.setGesundheit(verteidiger.getGesundheit() - angreifer.getFernkampfWert());
                angriffeÜbrig--;
            }
        }
    public static void attackiereMagie(Charakter angreifer, Charakter verteidiger) {
        int magieAngriffeÜbrig = Konstanten.INT_ONE;
        int schaden = Konstanten.INT_ZERO;
        int angreiferManapunkteTemporaer;
        if (angreifer.getManapunkte() >= Konstanten.INT_ONE && magieAngriffeÜbrig == Konstanten.INT_ONE) {
            schaden = verteidiger.getMagieResistenz() - Konstanten.INT_TEN;
            verteidiger.setGesundheit(verteidiger.getGesundheit() - schaden);
            angreifer.setManapunkte(angreifer.getManapunkte() - Konstanten.INT_ONE);
            magieAngriffeÜbrig--;
        }
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        if (spielerZug == true) {
        switch (keyEvent.getCode()) {
            case W:
                if (yPositionSpieler > Konstanten.INT_ZERO) yPositionSpieler = +Konstanten.INT_ONE;
                else
                    break;
            case A:
                if (xPositionSpieler > Konstanten.INT_ZERO) xPositionSpieler = -Konstanten.INT_ONE;
                break;
            case S:
                if (yPositionSpieler < Konstanten.INT_ELEVEN) yPositionSpieler = +Konstanten.INT_ONE;
                break;
            case D:
                if (xPositionSpieler < Konstanten.INT_ELEVEN) xPositionSpieler = +Konstanten.INT_ONE;
                break;
              case Q:
            attackiere(spieler, gegner);
            break;
            case P:
         //   wendeArtefaktAn(spieler, gegner);
            break;
            case E:
                attackiereMagie(spieler, gegner);
                break;
    }
    aktualisierePosition();
        }
    }
    public void beendeZug () {
        spielerZug = false;
        GegnerController.gegnerAgiert(gegner, spieler);
        aktualisierePosition();
        spielerZug = true;
    }
    private void aktualisierePosition()  {
        spielFeld.add(spielerPosition, xPositionSpieler, yPositionSpieler);
        spielFeld.add(gegnerPosition, xPositionGegner, yPositionGegner);
    }
    private void initialisiereSpieler () {
        this.spieler = Kaempfer.macheNeuenKaempferAusCharakter(GameFile.getInstanz().getLeader());
        spielerPosition = new Rectangle(Konstanten.INT_FIFTY, Konstanten.INT_FIFTY);
        spielerPosition.setFill(Color.BLUE);
        xPositionSpieler = Konstanten.INT_ZERO;
        yPositionSpieler = Konstanten.INT_ZERO;
        aktualisierePosition();
    }
    private void initialisiereGegner () {
        this.gegner = Kaempfer.erstelleEndgegner();
        gegnerPosition = new Rectangle(Konstanten.INT_FIFTY, Konstanten.INT_FIFTY);
        gegnerPosition.setFill(Color.RED);
        xPositionGegner = Konstanten.INT_ZERO;
        yPositionGegner = Konstanten.INT_ZERO;
        aktualisierePosition();
    }
    private void initialisiereSpielFeld () {
            for (int row = Konstanten.INT_ZERO; row < GRID_SIZE; row++)
            {
                for (int col = Konstanten.INT_ZERO; col < GRID_SIZE; col++)
                {
                    Rectangle tile = new Rectangle(Konstanten.INT_FIFTY,Konstanten.INT_FIFTY);
                    tile.setFill(Color.LIGHTGRAY);
                    tile.setStroke(Color.BLACK);
                    spielFeld.add(tile, col, row);
                }
            }
        }
    public void initialisierKampf2 () {
        kampfStartDialog.setVisible(false);
        initialisiereSpielFeld();
        initialisiereSpieler();
        /*initialisiereGegner();
        initialisiereAlleProgressBar();*/
    }
    private void initialisiereAlleProgressBar (){
        gesundheitsBar.setProgress(1.0);
        manaBar.setProgress(1.0);
        angriffeÜbrigBar.setProgress(1.0);
        fernkampfAngriffeÜbrigBar.setProgress(1.0);
        magieAngriffeÜbrigBar.setProgress(1.0);
        ringBar.setProgress(1.0);
        schwertBar.setProgress(1.0);
        statueBar.setProgress(1.0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gesundheitsBar.setStyle("-fx-accent: red;");
        spielFeld.sceneProperty().addListener(((observableValue, oldScene, newScene) ->
        {
            if (newScene != null)
            {
                newScene.setOnKeyPressed(this::handleKeyPressed);
            }
        }));
    }
    private void schaueObGewonnen () {
        if (gesundheit == 0.0) {

        } else if (gegnerGesundheit == 0.0) {

        }
    }
    @FXML
    private void gewinne () {

    }
}

