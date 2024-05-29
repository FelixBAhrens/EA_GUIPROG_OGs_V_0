package control;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.StickMan;
import res.Konstanten;
import res.Strings;

public class KampfController {
    static StickMan stickMan1 = new StickMan(Konstanten.INT_FIVE, Color.ORANGE, Konstanten.INT_ZERO,Konstanten.INT_ZERO, Konstanten.INT_ZERO,false,false,false,false);
    private static final int WINDOW_WIDTH = Konstanten.INT_SIX_HUNDRED;
    private static final int WINDOW_HEIGHT = Konstanten.INT_EIGHT_HUNDRED;
    private static final int OBJECT_SIZE = Konstanten.INT_FOURTY_FIVE; // Definiere die groesse des Objektes
    private static final int GRAIN_SIZE = Konstanten.INT_FIVE;

    private static Pane root;
    //private static Rectangle object; // Deklariere ein Rechteck als Objekt
    //private static Rectangle grain;
    //private static int gesammelteKoerner = Konstanten.INT_ZERO;
    private static Scene scene;


    public static void setzeKampfKarte(Stage hauptStage){
        root = new Pane();
        root.getChildren().add(stickMan1.getStickmanGroup());
        scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        setupKeyListeners(hauptStage);
        setupGameLoop(hauptStage);
        hauptStage.setScene(scene); // Setze die Szene im Hauptfenster
        hauptStage.setTitle(Strings.OBJEKTSTEUERUNG_MIT_WASD); // Setze den Fenstertitel
        hauptStage.show(); // Zeige das Hautfenster an
    }


    private static void setupKeyListeners (Stage hauptStage)
    {
        scene.setOnKeyPressed(event ->
        {
            switch (event.getCode())
            {
                case W:
                    stickMan1.setMovingDown(false);
                    stickMan1.setMovingUp(true);
                    break;
                case A:
                    stickMan1.setMovingRight(false);
                    stickMan1.setMovingLeft(true);
                    break;
                case S:
                    stickMan1.setMovingUp(false);
                    stickMan1.setMovingDown(true);
                    break;
                case D:
                    stickMan1.setMovingLeft(false);
                    stickMan1.setMovingRight(true);
                    break;
                case E:
                    //collectGrain(hauptStage);
                    break;
            }
            stickMan1.moveStickman((int)hauptStage.getHeight(), (int)hauptStage.getWidth());
        });

        scene.setOnKeyReleased(event ->
        {
            switch (event.getCode())
            {
                case W:
                    stickMan1.setMovingUp(false);
                    break;
                case A:
                    stickMan1.setMovingLeft(false);
                    break;
                case S:
                    stickMan1.setMovingDown(false);
                    break;
                case D:
                    stickMan1.setMovingDown(false);
                    break;
            }
            stickMan1.moveStickman((int)hauptStage.getHeight(), (int)hauptStage.getWidth());
        });
    }

    private static void setupGameLoop (Stage hauptStage)
    {
        new AnimationTimer()
        {
            public void handle (long now)
            {
                stickMan1.moveStickman((int)hauptStage.getHeight(), (int)hauptStage.getWidth());
                hauptStage.setScene(scene);
            }
        }.start();
    }

    private static void updateStickMan (Stage hauptStage){

    }
}
/**
 * Ich glaub' der Kampf ist ne Sache fuer sich und nimmt noch gut was an Zeit ein, das sollten wir beachten.
 * Vielleicht kann sich irgendwer schon Gedanken zur Realisierung machen.
 * ~Felix
 */
