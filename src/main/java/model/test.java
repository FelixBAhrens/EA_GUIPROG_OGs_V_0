package model;
/*
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import res.Konstanten;

import java.awt.*;
import java.util.Random;

public class test {

    private static final int WINDOW_WIDTH = Konstanten.SIX_HUNDRED;
    private static final int WINDOW_HEIGHT = Konstanten.EIGHT_HUNDRED;
    private static final int OBJECT_SIZE = 45; // Definiere die Größe des Objektes
    private static final int GRAIN_SIZE = 5;

    private static Pane root;
    private static PlayerObject object; // Verwende die neue PlayerObject-Klasse
    private static Rectangle grain;
    private static int gesammelteKoerner = Konstanten.ZERO;
    private static Scene scene;

    public static void setzeKarte (Stage hauptStage)
    {
        initialize(hauptStage);
    }

    private static void initialize (Stage hauptStage)
    {
        root = new Pane();
        scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        object = new PlayerObject(50, 50, OBJECT_SIZE, OBJECT_SIZE, Color.RED, 5);
        grain = createGrain();

        root.getChildren().addAll(object, grain);

        object.setupKeyListeners(scene);
        setupGameLoop(hauptStage);
        hauptStage.setScene(scene); // Setze die Szene im Hauptfenster
        hauptStage.setTitle("Objektsteuerung mit WASD"); // Setze den Fenstertitel
        hauptStage.show(); // Zeige das Hautfenster an
    }

    private static Rectangle createGrain ()
    {
        Rectangle grain = new Rectangle(10, 10, GRAIN_SIZE, GRAIN_SIZE);
        grain.setFill(Color.BLACK);

        return grain;
    }

    private static void collectGrain (Stage hauptStage)
    {
        if (object.getBoundsInParent().intersects(grain.getBoundsInParent()))
        {
            gesammelteKoerner++;
            hauptStage.setTitle("Gesammelte Koerner: " + gesammelteKoerner);
            resetGrain();
        }
    }

    private static void resetGrain ()
    {
        Random random = new Random();
        grain.setX(random.nextInt(60) * 10); // ToDo: Weshalb nicht (601) als Grenze?
        grain.setY(random.nextInt(80) * 10);
    }

    private static void setupGameLoop (Stage hauptStage)
    {
        new AnimationTimer()
        {
            public void handle (long now)
            {
                object.update(WINDOW_WIDTH, WINDOW_HEIGHT);
                collectGrain(hauptStage);
            }
        }.start();
    }
}

 */
