package control;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import res.Konstanten;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Klasse ObstaclesHandler. Platziert und entfernt die Hindernisse fuer die
 * Entschluesselungsmission.
 *
 * @author David Kien.
 */
public class ObstaclesHandler
{
    private AnchorPane plane;
    private double planeHeight;
    private double planeWidth;
    Random random = new Random();

    //--------------------------------------------------------------------------

    /**
     * Konstruktor fuer den `ObstaclesHandler`. Initialisiert die Parameter `plane`,
     * `planeHeight` und `planeWidth`, welche zur Erstellung und Bewegung von Hindernissen
     * verwendet werden.
     *
     * @pre `plane` muss ein gueltiges `AnchorPane`-Objekt sein. Die Parameter
     * `planeHeight` und `planeWidth` muessen positive Werte besitzen, die die
     * Groesse der Spielflaeche repraesentieren.
     *
     * @post Initialisierte die Felder `plane`, `planeHeight` und `planeWidth` des
     * `ObstaclesHandler`-Objekts mit den uebergebenen Werten.
     *
     * @param plane Das `AnchorPane`, auf dem die Hindernisse dargestellt werden.
     *
     * @param planeHeight Die Hoehe der Spielflaeche.
     *
     * @param planeWidth Die Breite der Spielflaeche.
     *
     * @author David Kien.
     */
    public ObstaclesHandler (AnchorPane plane, double planeHeight, double planeWidth)
    {
        this.plane = plane;
        this.planeHeight = planeHeight;
        this.planeWidth = planeWidth;
    }

    /**
     * Erstellt ein Paar von Hindernissen (obere und untere Rechtecke) mit zufaelligen
     * Hoehen und fuegt sie der Spielflaeche hinzu. Die Hindernisse werden in einer
     * festen Breite erstellt und vertikal durch einen definierten Abstand (`space`) getrennt.
     * Die Hindernisse werden in einer ArrayList zurueckgegeben.
     *
     * @pre `plane` muss ein gueltiges `AnchorPane`-Objekt sein. `planeHeight` muss eine
     * positive Zahl sein, die die Hoehe der Spielflaeche repraesentiert. `Konstanten.INT_TWENTY_FIVE`,
     * `Konstanten.INT_TWO_HUNDRED`, `Konstanten.INT_ONE_HUNDRED`, und `Konstanten.INT_FIFTY`
     * muessen definierte Werte besitzen.
     *
     * @post Zwei neue `Rectangle`-Objekte wurde erstellt und auf der `plane` hinzugefuegt.
     * Diese Rechtecke repraesentieren die oberen und unteren Hindernisse.
     * Sie wurden in einer ArrayList zurueckgegeben.
     *
     * @return Eine `ArrayList` mit den zwei erstellten `Rectangle`-Objekten.
     *
     * @author David Kien.
     */
    public ArrayList<Rectangle> createObstacles ()
    {
        int width = Konstanten.INT_TWENTY_FIVE;
        double xPos = planeWidth;
        double space = Konstanten.INT_TWO_HUNDRED;
        double recTopHeight = random.nextInt((int) (planeHeight - space - Konstanten.INT_ONE_HUNDRED)) + Konstanten.INT_FIFTY;
        double recBottomHeight = planeHeight - space - recTopHeight;

        Rectangle rectangleTop = new Rectangle(xPos, Konstanten.INT_ZERO, width, recTopHeight);
        Rectangle rectangleBottom = new Rectangle(xPos, recTopHeight + space, width, recBottomHeight);

        plane.getChildren().addAll(rectangleTop, rectangleBottom);
        return new ArrayList<>(Arrays.asList(rectangleTop, rectangleBottom));
    }

    /**
     * Bewegt alle Hindernisse in der uebergebenen Liste `obstacles` nach links.
     * Hindernisse, die aus dem Bildschirm heraus bewegt wurden, werden entfernt.
     *
     * @pre Die `obstacles`-Liste muss eine gueltige Liste von `Rectangle`-Objekten enthalten.
     * Die Methode `moveRectangle` muss existieren und korrekt implementiert sein.
     *
     * @post Alle Rechtecke in der `obstacles`-Liste wurden nach links bewegt.
     * Rechtecke, die ausserhalb des Bildschirms sind, wurden aus der Liste `obstacles`
     * und von der Spielflaeche (`plane`) entfernt.
     *
     * @param obstacles Eine `ArrayList` von `Rectangle`-Objekten, die die Hindernisse repraesentieren.
     *
     * @author David Kien.
     */
    public void moveObstacles (ArrayList<Rectangle> obstacles)
    {

        ArrayList<Rectangle> outOfScreen = new ArrayList<>();

        for (Rectangle rectangle : obstacles)
        {
            moveRectangle(rectangle, -Konstanten.ZERO_POINT_SEVEN_FIVE);

            if (rectangle.getX() <= -rectangle.getWidth())
            {
                outOfScreen.add(rectangle);
            }
        }
        obstacles.removeAll(outOfScreen);
        plane.getChildren().removeAll(outOfScreen);
    }

    /**
     * Bewegt ein gegebenes Rechteck (`rectangle`) um einen bestimmten Betrag (`amount`) auf
     * der x-Achse.
     *
     * @pre `rectangle` muss ein gueltiges `Rectangle`-Objekt sein. `amount` sollte ein Wert
     * sein, der die Verschiebung entlang der x-Achse repraesentiert.
     *
     * @post Das `rectangle` wurde um den Wert `amount` auf der x-Achse verschoben.
     *
     * @param rectangle Das `Rectangle`, das bewegt werden soll.
     *
     * @param amount Der Betrag, um den das Rechteck auf der x-Achse verschoben wird.
     *
     * @author David Kien.
     */
    private void moveRectangle (Rectangle rectangle, double amount)
    {
        rectangle.setX(rectangle.getX() + amount);
    }
}