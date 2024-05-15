package control;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;


public class KartenController
{
    private static Rectangle object; // Deklariere ein Rechteck als Objekt
    private static final int OBJECT_SIZE = 45; // Definiere die groesse des Objektes
    private static final int GRAIN_SIZE = 5;
    private static int gesammleteKoerner = 0;


    public static void setzeKarte (Stage hauptStage) // Ueberschreibe die start-Methode der Application-Klasse
    {
        Pane root = new Pane(); // Erstelle ein Pane, um die Benutzeroberflaeche zu halten
        Scene scene = new Scene(root, 600, 800); // Erstelle eine neue Szene mit einem Pane der Groesse 600x400

        Rectangle grain = versetzeKorn(new Rectangle(10,10, GRAIN_SIZE, GRAIN_SIZE));

        object = new Rectangle(50, 50, OBJECT_SIZE, OBJECT_SIZE); // Erstelle ein Rechteck und lege seine Position fest
        object.setFill(Color.RED); // Setze die Farbe des Rechteckes auf rot
        root.getChildren().addAll(object, grain); // Fuege das Rechteck zum Pane hinzu

        scene.setOnKeyPressed (event -> // Fuege einen Event-Listener hinzu, der auf Tastendruecke reagiert
        {
            switch (event.getCode())  // Ueberpruefe die gedrueckte Taste
            {
                case W: // Wenn die Taste W gedrueckt wurde:
                    object.setY(object.getY() - 10); // Bewege das Rechteck nach oben
                    break;
                case A: // Wenn die Taste A gedrueckt wurde:
                    object.setX(object.getX() - 10); // Bewege das Rechteck nach links
                    break;
                case S: // Wenn die Taste S gedrueckt wurde:
                    object.setY(object.getY() + 10); // Bewege das Rechteck nach unten
                    break;
                case D: // Wenn die Taste D gedruekt wurde:
                    object.setX(object.getX() + 10); // Bewege das Rechteck nach rechts
                    break;
                case E: // Wenn die Taste E gedrueckt wurde:
                    if (kornGesammelt(object.getX(), object.getY(), grain.getX(), grain.getY())){
                        gesammleteKoerner++;
                        hauptStage.setTitle("Gesammelte Koerner: " + gesammleteKoerner);
                        versetzeKorn(grain);
                    }
                    break;
            }

        }
        );

        hauptStage.setScene(scene); // Setze die Szene im Hauptfenster
        hauptStage.setTitle("Objektsteuerung mit WASD"); // Setze den Fenstertitel
        hauptStage.show(); // Zeige das Hautfenster an
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