package model;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import res.Konstanten;

public abstract class MovableObject extends Rectangle
{
    int movementSpeed = Konstanten.INT_FIVE;

    public MovableObject (double x, double y, double width, double height, Color color, int movementSpeed)
    {
        super(x, y, width, height);
        this.setFill(color);
        this.movementSpeed = movementSpeed;
    }

    public abstract void move (boolean movingUp, boolean movingDown, boolean movingLeft, boolean movingRight, boolean windowWidth, boolean windowHeight);

    public abstract void setupKeyListeners(Scene scene);

    ;
}
