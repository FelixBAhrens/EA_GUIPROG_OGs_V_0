package control;

// NOT COMPLETED

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Klasse CollisionHandler
 *
 * @author David Kien
 */
public class CollisionHandler
{
    /**
     * @param obstacles
     * @param bird
     * @return
     * @Author David Kien
     */
    public boolean collisionDetection (ArrayList<Rectangle> obstacles, Rectangle bird)
    {
        for (Rectangle rectangle : obstacles)
        {
            if (rectangle.getBoundsInParent().intersects(bird.getBoundsInParent()))
            {
                return true;
            }
        }
        return false;
    }
}

