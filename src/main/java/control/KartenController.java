package control;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import res.Konstanten;

import java.util.Random;

public class KartenController
{

    private static final int WINDOW_WIDTH = Konstanten.SIX_HUNDRED;
    private static final int WINDOW_HEIGHT = Konstanten.EIGHT_HUNDRED;
    private static final int OBJECT_SIZE = 45; // Definiere die groesse des Objektes
    private static final int GRAIN_SIZE = 5;

    private static Pane root;
    private static Rectangle object; // Deklariere ein Rechteck als Objekt
    private static Rectangle grain;
    private static int gesammelteKoerner = Konstanten.ZERO;
    private static Scene scene;

    private static boolean movingUp = false;
    private static boolean movingDown = false;
    private static boolean movingLeft = false;
    private static boolean movingRight = false;

    public static void setzeKarte (Stage hauptStage)
    {
        initialize(hauptStage);
    }

    private static void initialize (Stage hauptStage)
    {
        root = new Pane();
        scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        object = createObject();
        grain = createGrain();

        root.getChildren().addAll(object, grain);

        setupKeyListeners(hauptStage);
        setupGameLoop(hauptStage);
        hauptStage.setScene(scene); // Setze die Szene im Hauptfenster
        hauptStage.setTitle("Objektsteuerung mit WASD"); // Setze den Fenstertitel
        hauptStage.show(); // Zeige das Hautfenster an
    }

    private static Rectangle createObject ()
    {
        Rectangle object = new Rectangle(50, 50, OBJECT_SIZE, OBJECT_SIZE);
        object.setFill(Color.RED);

        return object;
    }

    private static Rectangle createGrain ()
    {
        Rectangle grain = new Rectangle(10, 10, GRAIN_SIZE, GRAIN_SIZE);
        grain.setFill(Color.BLACK);

        return grain;
    }

    private static void setupKeyListeners (Stage hauptStage)
    {
        scene.setOnKeyPressed(event ->
        {
            switch (event.getCode())
            {
                case W:
                    movingUp = true;
                    break;
                case A:
                    movingLeft = true;
                    break;
                case S:
                    movingDown = true;
                    break;
                case D:
                    movingRight = true;
                    break;
                case E:
                    collectGrain(hauptStage);
                    break;
            }
        });

        scene.setOnKeyReleased(event ->
        {
            switch (event.getCode())
            {
                case W:
                    movingUp = false;
                    break;
                case A:
                    movingLeft = false;
                    break;
                case S:
                    movingDown = false;
                    break;
                case D:
                    movingRight = false;
                    break;
            }
        });
    }

    private static void moveObject ()
    {
        int movementSpeed = 5;
        boolean movingHorizontally = movingLeft || movingRight;
        boolean movingVertically = movingUp || movingDown;

        if (movingVertically && movingHorizontally)
        {
            movementSpeed /= Math.sqrt(2);
        }

        if (movingUp && object.getY() > 0)
        {
            object.setY(object.getY() - movementSpeed);
        }
        if (movingDown && object.getY() < WINDOW_HEIGHT - OBJECT_SIZE)
        {
            object.setY(object.getY() + movementSpeed);
        }
        if (movingLeft && object.getX() > 0)
        {
            object.setX(object.getX() - movementSpeed);
        }
        if (movingRight && object.getX() < WINDOW_WIDTH - OBJECT_SIZE)
        {
            object.setX(object.getX() + movementSpeed);
        }
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
                moveObject();
            }
        }.start();
    }
}
