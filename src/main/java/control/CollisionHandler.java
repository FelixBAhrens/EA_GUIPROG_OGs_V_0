package control;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Prueft, ob eine Kollision zwischen dem Schluessel und den Hindernissen vorliegt.
 *
 * @author David Kien.
 */
public class CollisionHandler
{
    /**
     * Ueberprueft, ob eine Kollision zwischen einem Schluesselrechteck und einer
     * Liste von Barrierenrechtecken vorliegt.
     *
     * @pre Die Liste 'barrieren' sowie das 'schluessel'-Rechteck muessen initialisiert
     * und nicht null sein. Jedes Rechteck in der Liste 'barrieren' sowie das Rechteck
     * 'schluessel' muessen gueltige Bounding-Box-Koordinaten haben.
     *
     * @post Gab true zurueck, wenn eine Kollision zwischen dem 'schluessel'-Rechteck
     * und einem der Rechtecke in der 'barrieren'-Liste erkannt wurde. Andernfalls
     * wurde false zurueck gegeben.
     *
     * @param barrieren Eine Liste von Rechtecken, die als Hindernisse fungieren.
     *
     * @param schluessel Das Rechteck, das auf Kollisionen mit den Barrieren
     * ueberprueft wird.
     *
     * @return true, wenn eine Kollision festgestellt wird, andernfalls false.
     *
     * @author David Kien.
     */
    public boolean collisionDetection (ArrayList<Rectangle> barrieren, Rectangle schluessel)
    {
        for (Rectangle rectangle : barrieren)
        {
            if (rectangle.getBoundsInParent().intersects(schluessel.getBoundsInParent()))
            {
                return true;
            }
        }
        return false;
    }
}