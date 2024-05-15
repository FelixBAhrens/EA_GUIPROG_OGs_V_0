package control;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class KartenController2
{

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 800;
    private static final int OBJECT_SIZE = 45; // Definiere die groesse des Objektes
    private static final int GRAIN_SIZE = 5;

    private static Pane root;
    static Rectangle object; // Deklariere ein Rechteck als Objekt
    private static Rectangle grain;
    private static int gesammelteKoerner = 0;
    private static Scene scene;

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
        hauptStage.getScene().setOnKeyPressed(event ->
        {
            switch (event.getCode())
            {
                case W:
                    System.out.println("W");
                    moveUp();
                    break;
                case A:
                    moveLeft();
                    break;
                case S:
                    moveDown();
                    break;
                case D:
                    moveRight();
                    break;
                case E:
                    collectGrain(hauptStage);
                    break;
            }
        });

        hauptStage.getScene().setOnKeyReleased(event ->
        {
            switch (event.getCode())
            {
                case W:
                case S:
                    object.setTranslateY(0);
                    break;
                case A:
                case D:
                    object.setTranslateX(0);
                    break;
            }
        });

        hauptStage.setScene(scene); // Setze die Szene im Hauptfenster
        hauptStage.setTitle("Objektsteuerung mit WASD"); // Setze den Fenstertitel
        hauptStage.show(); // Zeige das Hautfenster an
    }

    private static void moveUp ()
    {
        if (object.getY() > 0)
        {
            object.setTranslateY(-10);
        }
    }

    private static void moveDown ()
    {
        if (object.getY() < WINDOW_HEIGHT - OBJECT_SIZE)
        {
            object.setTranslateY(10);
        }
    }

    private static void moveLeft ()
    {
        if (object.getX() > 0)
        {
            object.setTranslateX(-10);
        }
    }

    private static void moveRight ()
    {
        if (object.getX() < WINDOW_WIDTH - OBJECT_SIZE)
        {
            object.setTranslateX(10);
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
        hauptStage.addEventHandler(Event.ANY, event ->
        {
        });
    }
}
