package control;

// NOT COMPLETED

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
 * Controllerklasse fuer die "mission-flappybird-view.fxml".
 * In ihr befinden sich Methoden zur Nutzerinteraktion.
 *
 * @author David Kien.
 */
public class MissionEntschluesselnController extends ControllerController implements Initializable
{
    AnimationTimer gameLoop;

    @FXML
    private AnchorPane plane;

    @FXML
    private Rectangle schluessel;

    @FXML
    private Text score;

    private double accelerationTime = Konstanten.INT_ZERO;

    private int gameTime = Konstanten.INT_ZERO;
    private int scoreCounter = Konstanten.INT_ZERO;

    private Schluessel schluesselKomponente;

    private ObstaclesHandler obstaclesHandler;

    ArrayList<Rectangle> obstacles = new ArrayList<>();

    //--------------------------------------------------------------------------

    /**
     * Diese Methode wird aufgerufen, um die Controller-Klasse zu initialisieren.
     * Sie richtet das Spiel-Setup ein, indem sie den Clipping-Bereich für die Spielflaeche
     * (`plane`) definiert, die Schluesselfunktionalitaet (`schluesselKomponente`)
     * und das Hindernis-Handling (`obstaclesHandler`) initialisiert und den Spiel-Loop
     * (`gameLoop`) startet.
     *
     * @pre Die GUI-Elemente `plane` und `bird` muessen initialisiert und die Konstanten
     * korrekt definiert sein.
     *
     * @post Das Spiel wurde initialisiert und der Game-Loop gestartet, der kontinuierlich
     * die `update()`-Methode aufruft, um den Spielzustand zu aktualisieren. Der Clipping-Bereich
     * fuer die Spielflaeche wurde festgelegt, um den sichtbaren Bereich des Spiels zu beschraenken.
     *
     * @param url Der Ort, von dem die FXML-Datei geladen wurde.
     *
     * @param resourceBundle Ein ResourceBundle, das zur Internationalisierung verwendet werden kann.
     *
     * @author David Kien.
     */
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        Rectangle clip = new Rectangle(plane.getPrefWidth(), plane.getPrefHeight());
        plane.setClip(clip);

        int jumpHeight = Konstanten.INT_FOURTYFIVE;
        schluesselKomponente = new Schluessel(schluessel, jumpHeight);
        double planeHeight = Konstanten.INT_SIX_HUNDRED;
        double planeWidth = Konstanten.INT_FOUR_HUNDRED;
        obstaclesHandler = new ObstaclesHandler(plane, planeHeight, planeWidth);

        gameLoop = new AnimationTimer()
        {
            @Override
            public void handle (long l)
            {
                update();
            }
        };

        load();

        gameLoop.start();
    }

    /**
     * Diese Methode wird aufgerufen, wenn eine Taste gedrueckt wird. Wenn die Leertaste
     * (SPACE) gedrueckt wird, wird die Methode `fly()` der `schluesselKomponente`
     * aufgerufen, um den Schluessel nach oben zu bewegen. Der Beschleunigungs-Timer
     * (`accelerationTime`) wird zurueckgesetzt, um die Gravitationseffekte neu zu berechnen.
     *
     * @pre Das `schluesselKomponente`-Objekt muss initialisiert sein und das
     * `KeyEvent`-Objekt muss eine Taste beinhalten.
     *
     * @post Der Schluessel wurde um eine vordefinierte Sprunghoehe angehoben,
     * und der Beschleunigungs-Timer zurueckgesetzt, um die Bewegung des Schluessels zu steuern.
     *
     * @param event Das `KeyEvent`, das die gedrueckte Taste enthaelt.
     *
     * @author David Kien.
     */
    @FXML
    void pressed (KeyEvent event)
    {
        if (event.getCode() == KeyCode.SPACE)
        {
            schluesselKomponente.fly();
            accelerationTime = Konstanten.INT_ZERO;
        }
    }

    /**
     * Diese Methode wird kontinuierlich im Spiel-Loop aufgerufen, um den Zustand des
     * Spiels zu aktualisieren. Sie erhoeht die Spielzeit (`gameTime`) und den Beschleunigungs-Timer
     * (`accelerationTime`), bewegt den Schluessel nach unten, ueberprueft die Punkte,
     * bewegt die Hindernisse und fuegt neue Hindernisse hinzu. Wenn der Schluessel mit einem
     * Hindernis kollidiert, wird das Spiel zurueckgesetzt.
     *
     * @pre Die `schluesselKomponente` und `obstaclesHandler` muessen initialisiert sein.
     * Die Liste `obstacles` und die GUI-Elemente `plane` und `schluessel` muessen vorhanden sein.
     *
     * @post Die Position des Schluessels und der Hindernisse wurde aktualisiert,
     * Punkte ueberprueft und gegebenenfalls neue Hindernisse hinzugefuegt.
     * Bei einer Kollision wurde das Spiel zurueckgesetzt.
     *
     * @author David Kien.
     */
    private void update ()
    {
        gameTime++;
        accelerationTime++;
        double yDelta = Konstanten.ZERO_POINT_ZERO_TWO;
        schluesselKomponente.moveBirdY(yDelta * accelerationTime);

        if (pointChecker(obstacles, schluessel))
        {
            scoreCounter++;
            score.setText(String.valueOf(scoreCounter));
        }

        obstaclesHandler.moveObstacles(obstacles);

        if (gameTime % Konstanten.INT_FIVE_HUNDRED == Konstanten.INT_ZERO)
        {
            obstacles.addAll(obstaclesHandler.createObstacles());
        }

        if (schluesselKomponente.isKeyDead(obstacles, plane))
        {
            resetGame();
        }
    }

    /**
     * Diese Methode wird verwendet, um die anfaenglichen Hindernisse im Spiel zu laden.
     * Sie fuegt der `obstacles`-Liste Hindernisse hinzu, die durch den `obstaclesHandler`
     * erstellt wurden.
     *
     * @pre Der `obstaclesHandler` muss initialisiert sein und die Liste `obstacles` muss existieren.
     *
     * @post Die `obstacles`-Liste enthaelt die initialen Hindernisse, die im Spiel angezeigt werden.
     *
     * @author David Kien.
     */
    private void load ()
    {
        obstacles.addAll(obstaclesHandler.createObstacles());
    }

    /**
     * Diese Methode setzt den Spielzustand zurueck, um ein neues Spiel zu starten.
     * Sie setzt die Position des Schluessels zurueck, entfernt alle Hindernisse von der
     * Spielflaeche, setzt die Zaehler (`gameTime`, `accelerationTime`, `scoreCounter`) zurueck
     * und aktualisiert die angezeigte Punktzahl.
     *
     * @pre Die GUI-Elemente `plane` und `schluessel` sowie die Liste `obstacles`
     * muessen existieren. Die Variablen `gameTime`, `accelerationTime` und `scoreCounter`
     * muessen definiert sein.
     *
     * @post Der Spielzustand wurde zurueckgesetzt, die Hindernisse entfernt, und die
     * Zaehler sowie die angezeigte Punktzahl auf die Ausgangswerte gesetzt.
     *
     * @author David Kien.
     */
    private void resetGame ()
    {
        schluessel.setY(Konstanten.INT_ZERO);
        plane.getChildren().removeAll(obstacles);
        obstacles.clear();
        gameTime = Konstanten.INT_ZERO;
        accelerationTime = Konstanten.INT_ZERO;
        scoreCounter = Konstanten.INT_ZERO;
        score.setText(String.valueOf(scoreCounter));
    }

    /**
     * Diese Methode ueberprueft, ob der Schluessel einen Punkt fuer das Passieren eines
     * Hindernisses erhalten sollte. Sie vergleicht die x-Position des Vogels mit der
     * x-Position der Hindernisse und gibt true zurueck, wenn der Vogel ein Hindernis passiert
     * hat.
     *
     * @pre Die Liste `obstacles` und das Rechteck `schluessel` muessen initialisiert und gueltig sein.
     *
     * @post Gibt true zurueck, wenn der Vogel ein Hindernis passiert hat, ansonsten false.
     *
     * @param obstacles Die Liste der Hindernisse im Spiel.
     *
     * @param schluessel Der Schluessel, dessen Position ueberprueft wird.
     *
     * @return true, wenn ein Punkt erzielt wurde, sonst false.
     *
     * @author David Kien.
     */
    private boolean pointChecker (ArrayList<Rectangle> obstacles, Rectangle schluessel)
    {
        for (Rectangle obstacle : obstacles)
        {
            int birdPositionX = (int) (schluessel.getLayoutX() + schluessel.getX());
            if (((int) (obstacle.getLayoutX() + obstacle.getX()) == birdPositionX))
            {
                return true;
            }
        }
        return false;
    }
}