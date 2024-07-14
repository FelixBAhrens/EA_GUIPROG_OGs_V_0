package control;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

// @Author David Kien
public class CollisionHandler
{
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

