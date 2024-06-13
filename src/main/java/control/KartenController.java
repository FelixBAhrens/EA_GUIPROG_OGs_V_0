package control;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class KartenController implements Initializable
{
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);
    private BooleanBinding movingHorizontally = wPressed.or(sPressed);
    private BooleanBinding movingVertically = aPressed.or(dPressed);

    private int movementVariable = 2;

    private static final double ROOT_2 = Math.sqrt(2);

    private List<Rectangle> barriers = new ArrayList<>();

    @FXML
    private Rectangle shape1;

    @FXML
    private AnchorPane scene;

    AnimationTimer timer = new AnimationTimer()
    {
        @Override
        public void handle (long timestamp)
        {
            double moveX = 0;
            double moveY = 0;

            if (wPressed.get())
            {
                if (!checkCollisionWithBarriers(shape1.getLayoutX(), shape1.getLayoutY() - movementVariable, shape1))
                {
                    moveY -= movementVariable;
                }

            }
            if (aPressed.get())
            {
                if (!checkCollisionWithBarriers(shape1.getLayoutX() - movementVariable, shape1.getLayoutY(), shape1))
                {
                    moveX -= movementVariable;
                }
            }
            if (sPressed.get())
            {
                if (!checkCollisionWithBarriers(shape1.getLayoutX(), shape1.getLayoutY() + movementVariable, shape1))
                {
                    moveY += movementVariable;
                }
            }
            if (dPressed.get())
            {
                if (!checkCollisionWithBarriers(shape1.getLayoutX() + movementVariable, shape1.getLayoutY(), shape1))
                moveX += movementVariable;
            }

            if (movingHorizontally.get() && movingVertically.get())
            {
                moveX /= ROOT_2;
                moveY /= ROOT_2;
            }

            shape1.setLayoutX(shape1.getLayoutX() + moveX);
            shape1.setLayoutY(shape1.getLayoutY() + moveY);
        }
    };

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        movementSetup();

        keyPressed.addListener((observableValue, aBoolean, t1) ->
        {
            if (!aBoolean)
            {
                timer.start();
            } else
            {
                timer.stop();
            }
        });

        for (Node node : scene.getChildren())
        {
            if (node instanceof Rectangle && !node.equals(shape1))
            {
                barriers.add((Rectangle) node);
            }
        }

        scene.requestFocus();
    }

    public void movementSetup ()
    {
        scene.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.W)
            {
                wPressed.set(true);
            }

            if (e.getCode() == KeyCode.A)
            {
                aPressed.set(true);
            }

            if (e.getCode() == KeyCode.S)
            {
                sPressed.set(true);
            }

            if (e.getCode() == KeyCode.D)
            {
                dPressed.set(true);
            }
        });

        scene.setOnKeyReleased(e ->
        {
            if (e.getCode() == KeyCode.W)
            {
                wPressed.set(false);
            }

            if (e.getCode() == KeyCode.A)
            {
                aPressed.set(false);
            }

            if (e.getCode() == KeyCode.S)
            {
                sPressed.set(false);
            }

            if (e.getCode() == KeyCode.D)
            {
                dPressed.set(false);
            }
        });
    }

    private boolean checkCollisionWithBarriers (double x, double y, Rectangle movingRectangle)
    {
        for (Rectangle barrier : barriers)
        {
            if (barrier.getBoundsInParent().intersects(x, y, movingRectangle.getWidth(), movingRectangle.getHeight()))
            {
                return true;
            }
        }
        return false;
    }
}
