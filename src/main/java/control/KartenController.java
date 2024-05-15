package control;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

import static control.KartenController2.object;

public class KartenController
{
private static boolean moveUp;
private static boolean moveLeft;
private static boolean moveDown;
private static boolean moveRight;
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 800;
    private static final int OBJECT_SIZE = 45; // Definiere die groesse des Objektes
    private static final int GRAIN_SIZE = 5;

    private static Pane root;
    static Rectangle object; // Deklariere ein Rechteck als Objekt
    private static Rectangle grain;
    private static int gesammelteKoerner = 0;
    private static Scene scene;

    public static void setzeKarte (Stage hauptStage) // Ueberschreibe die start-Methode der Application-Klasse
    {
        Pane root = new Pane(); // Erstelle ein Pane, um die Benutzeroberflaeche zu halten
        Scene scene = new Scene(root, 600, 800); // Erstelle eine neue Szene mit einem Pane der Groesse 600x400

        Rectangle grain = versetzeKorn(new Rectangle(10,10, GRAIN_SIZE, GRAIN_SIZE));

        object = new Rectangle(50, 50, OBJECT_SIZE, OBJECT_SIZE); // Erstelle ein Rechteck und lege seine Position fest
        object.setFill(Color.RED); // Setze die Farbe des Rechteckes auf rot
        root.getChildren().addAll(object, grain); // Fuege das Rechteck zum Pane hinzu

        scene.setOnKeyPressed (event ->
        {
            switch (event.getCode())
            {
                case W:
                    moveUp = true;
                    break;
                case A:
                    moveLeft = true;
                    break;
                case S:
                    moveDown = true;
                    break;
                case D:
                    moveRight = true;
                    break;
                case E: // Wenn die Taste E gedrueckt wurde:
                    if (kornGesammelt(object.getX(), object.getY(), grain.getX(), grain.getY())){
                        gesammelteKoerner++;
                        hauptStage.setTitle("Gesammelte Koerner: " + gesammelteKoerner);
                        versetzeKorn(grain);
                    }
                    break;
            }
        });

        scene.setOnKeyReleased (event ->
        {
            switch (event.getCode())
            {
                case W:
                    moveUp = false;
                    break;
                case A:
                    moveLeft = false;
                    break;
                case S:
                    moveDown = false;
                    break;
                case D:
                    moveRight = false;
                    break;
            }
        }
        );

        hauptStage.setScene(scene); // Setze die Szene im Hauptfenster
        hauptStage.setTitle("Objektsteuerung mit WASD"); // Setze den Fenstertitel
        hauptStage.show(); // Zeige das Hautfenster an

        hauptStage.addEventHandler (javafx.event.Event.ANY, event -> // Fuege einen Event-Listener hinzu, der auf Tastendruecke reagiert
        {
            if (moveUp && !moveDown && object.getY() > 0)
            {
                object.setY(object.getY() - 10);
            }
            if (moveDown && !moveUp && object.getY() < hauptStage.getHeight() - object.getHeight())
            {
                object.setY(object.getY() + 10);
            }
            if (moveLeft && !moveRight && object.getX() > 0)
            {
                object.setX(object.getX() - 10);
            }
            if (moveRight && !moveLeft && object.getX() < hauptStage.getWidth() - object.getWidth())
            {
                object.setX(object.getX() + 10);
            }



        }
        );


    }

    private static boolean kornGesammelt(double xKoord, double yKoord, double xKoordGrain, double yKoordGrain){
        double difXKoord = xKoord - xKoordGrain;
        double difYKoord = yKoord - yKoordGrain;
        System.out.println(difXKoord + " " + difYKoord);
        return ((difXKoord > -50 & difXKoord<1) && (difYKoord > -50 & difYKoord < 1));
    }

    private static Rectangle versetzeKorn (Rectangle grain){
        Random random = new Random();
        grain.setX(random.nextInt(60)*10);
        grain.setY(random.nextInt(80)*10);
        return grain;
    }

}




/**
 * Hier sollten wir uns schon ueberlegen wie wir die Steuerung machen wollen
 * und wie die Elemente der Map aussehen sollen.
 * Mein Vorschlag waere: WASD-Steuerung plus Enter-Taste oder Mausklick.
 * Vom Design her brauchen wir dann noch am Bildschirmrand Anzeigen fuer gesammelte Objekte ect.
 * ~Felix
 */