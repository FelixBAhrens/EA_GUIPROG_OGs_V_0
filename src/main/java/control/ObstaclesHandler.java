package control;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import res.Konstanten;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Klasse ObstaclesHandler
 * @Author David Kien
 */
public class ObstaclesHandler
{
    private AnchorPane plane;
    private double planeHeight;
    private double planeWidth;
    Random random = new Random();

    //@Author David Kien
    public ObstaclesHandler (AnchorPane plane, double planeHeight, double planeWidth)
    {
        this.plane = plane;
        this.planeHeight = planeHeight;
        this.planeWidth = planeWidth;
    }

    //@Author David Kien
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

    //@Author David Kien
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

    //@Author David Kien
    private void moveRectangle (Rectangle rectangle, double amount)
    {
        rectangle.setX(rectangle.getX() + amount);
    }
}
