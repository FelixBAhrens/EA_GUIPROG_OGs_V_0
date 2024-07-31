package control;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Schluessel;
import res.Konstanten;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller fuer das Entschluesselungsspiel. Verwaltet das Spielfeld, den Schluessel,
 * die Hinternisse und den Punktestand. Implementiert die Hauptspielschleife und
 * die Eingabeereignisse.
 *
 * @version 1.0
 * @since 2024-06-24
 * @author David Kien
 */
public class MissionFlappyBirdController extends ControllerController implements Initializable
{
    AnimationTimer gameLoop; // Hauptschleife

    @FXML
    private AnchorPane spielfeld; // Das Spielfeld

    @FXML
    private Rectangle schluessel; // Der Schluessel

    @FXML
    private Text punktestandAnzeige; // Textfeld fuer den Punktestand

    private double beschleunigungszeit = Konstanten.INT_ZERO; // Zeit, die die Beschleunigung dauert

    private int spielzeit = Konstanten.INT_ZERO; // Laufzeit des Spiels

    private int punktestand = Konstanten.INT_ZERO; // Zaehler fuer den Punktestand

    private Schluessel schluesselKomponente; // Komponente die den Schluessel steuert

    private ObstaclesHandler hindernissBehandler; // Handler fuer die Hindernisse

    ArrayList<Rectangle> hindernisse = new ArrayList<>(); // Liste fuer die Hindernisse


    /**
     * Initialisiert die Spielkomponenten und startet die Spielschleife
     *
     * @param url URL zum FXML-Dokument
     * @param resourceBundle Resourcenbundle fuer die Lokalisierung
     *
     * @pre Die FXML-Komponenten muessen korrekt geladen sein.
     *
     * @post Die Spielkomponenten sind initialisiert und die Spielschleife startet
     *
     * @author David Kien
     */
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        // Setzt eine Schnittmaske, um das Spielfeld innerhalb seiner Grenzen zu halten.
        Rectangle schnittmaske = new Rectangle(spielfeld.getPrefWidth(), spielfeld.getPrefHeight());
        spielfeld.setClip(schnittmaske);

        // Initialisiert die Sprunghoehe des Schluessels
        int sprungHoehe = Konstanten.INT_FOURTYFIVE;
        schluesselKomponente = new Schluessel(schluessel, sprungHoehe);

        // Initialisiert die Dimensionenen des Spielfeldes
        double planeHeight = Konstanten.INT_SIX_HUNDRED;
        double planeWidth = Konstanten.INT_FOUR_HUNDRED;
        hindernissBehandler = new ObstaclesHandler(spielfeld, planeHeight, planeWidth);

        // Definiert die Hauptspielschleife
        gameLoop = new AnimationTimer()
        {
            @Override
            public void handle (long l)
            {
                update(); // Aktualisiert den Spielzustand in jedem Frame
            }
        };

        load(); // Laedt die Spielkomponenten

        gameLoop.start(); // Startet die Spielschleife
    }

    @FXML
    void pressed (KeyEvent event)
    {
        if (event.getCode() == KeyCode.SPACE)
        {
            schluesselKomponente.fliege();
            beschleunigungszeit = Konstanten.INT_ZERO;
        }
    }


    //Called every game frame
    private void update ()
    {
        spielzeit++;
        beschleunigungszeit++;
        double yDelta = Konstanten.ZERO_POINT_ZERO_TWO;
        schluesselKomponente.bewegeSchluesselY(yDelta * beschleunigungszeit);

        if (pointChecker(hindernisse, schluessel))
        {
            punktestand++;
            punktestandAnzeige.setText(String.valueOf(punktestand));
        }

        hindernissBehandler.moveObstacles(hindernisse);

        if (spielzeit % Konstanten.INT_FIVE_HUNDRED == Konstanten.INT_ZERO)
        {
            hindernisse.addAll(hindernissBehandler.createObstacles());
        }

        if (schluesselKomponente.isBirdDead(hindernisse, spielfeld))
        {
            resetGame();
        }
    }

    //Everything called once, at the game start
    private void load ()
    {
        hindernisse.addAll(hindernissBehandler.createObstacles());
    }

    private void resetGame ()
    {
        schluessel.setY(Konstanten.INT_ZERO);
        spielfeld.getChildren().removeAll(hindernisse);
        hindernisse.clear();
        spielzeit = Konstanten.INT_ZERO;
        beschleunigungszeit = Konstanten.INT_ZERO;
        punktestand = Konstanten.INT_ZERO;
        punktestandAnzeige.setText(String.valueOf(punktestand));
    }


    private boolean pointChecker (ArrayList<Rectangle> obstacles, Rectangle bird)
    {
        for (Rectangle obstacle : obstacles)
        {
            int birdPositionX = (int) (bird.getLayoutX() + bird.getX());
            if (((int) (obstacle.getLayoutX() + obstacle.getX()) == birdPositionX))
            {
                return true;
            }
        }
        return false;
    }
}