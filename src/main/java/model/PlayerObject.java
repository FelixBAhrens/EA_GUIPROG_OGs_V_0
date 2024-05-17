package model;
/*
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class PlayerObject extends MovableObject
{
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    public PlayerObject (double x, double y, double width, double height, Color color, int movementSpeed)
    {
        super(x, y, width, height, color, movementSpeed);
    }

    public void setUpKeyListeners (Scene scene)
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

    public void move (boolean movingUp, boolean movingDown, boolean movingLeft, boolean movingRight)
    {
        int adjustedSpeed = movementSpeed;
        boolean movingHorizontally = movingLeft || movingRight;
        boolean movingVertically = movingUp || movingDown;

        if (movingVertically && movingHorizontally)
        {
            movementSpeed /= Math.sqrt(2);
        }

        if (movingUp && getY() > 0)
        {
            setY(getY() - adjustedSpeed);
        }
        if (movingUp && getY() < windowHeight - getHeight())
        {
            setY(getY() + adjustedSpeed);
        }
        if (movingLeft && getX() > 0)
        {
            setX(getX() - adjustedSpeed);
        }
        if (movingRight && getX() < windowWidth - getWidth())
        {
            setX(getX() + adjustedSpeed);
        }
    }

    public void update (double windowWidth, double windowHeight)
    {
        move (movingUp, movingDown, movingLeft, movingRight, windowWidth, windowHeight);
    }


}

 */
