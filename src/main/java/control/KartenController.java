package control;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class KartenController implements Initializable
{
    @FXML
    private AnchorPane map;
    @FXML
    private Rectangle wood;
    @FXML
    private Rectangle gold;
    @FXML
    private Rectangle health;
    @FXML
    private Pane menuePane;
    @FXML
    private Pane hintergrundPane;
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
            if (node instanceof Rectangle && !node.equals(shape1) && !node.equals(gold) && !node.equals(wood) && !node.equals(health))
            {
                barriers.add((Rectangle) node);
            }
        }
        scene.requestFocus();
        checkForCollections();
    }

    private void checkForCollections()
    {
        placeRandomlyWithinMap(wood);
        placeRandomlyWithinMap(health);
        placeRandomlyWithinMap(gold);
    }

    private void placeRandomlyWithinMap(Rectangle object)
    {
        Random random = new Random();

        double paneWidth = map.getWidth();
        double paneHeight = map.getHeight();

        double randomX;
        double randomY;
        boolean intersects;

        do
        {
            intersects = false;
            System.out.println(object.getWidth());
            System.out.println(paneWidth);

            randomX = random.nextInt((int) (paneWidth - object.getWidth())); //* (paneWidth - object.getWidth());
            randomY = random.nextInt((int) (paneHeight - object.getHeight())); //* (paneHeight - object.getHeight());

            for (Rectangle barrier : barriers)
            {
                if (barrier.getBoundsInParent().intersects(randomX, randomY, object.getWidth(), object.getHeight()))
                {
                    intersects = true;
                    break;
                }
            }
        }
        while (intersects);
        {
            object.setLayoutX(randomX);
            object.setLayoutY(randomY);
        }
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


    @FXML
    public void handlezurueck(){
        SceneManager.goBack();
    }

    @FXML
    private void openMenue ()
    {
       openGebaeude("menue-view.fxml");
    }

    @FXML
    private void handleMouseEnter(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        pane.setStyle("-fx-background-color: transparent; -fx-border-color: turquoise; -fx-border-width: 2;");
        for (javafx.scene.Node node : pane.getChildren()) {
            if (node instanceof Button) {
                node.setVisible(true);
            }
        }
    }

    @FXML
    private void handleMouseExit(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        pane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-border-width: 2;");
        for (javafx.scene.Node node : pane.getChildren()) {
            if (node instanceof Button) {
                node.setVisible(false);
            }
        }
    }

    private void openGebaeude(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = loader.load();

            // GebäudeController Zugriff
            PaneController controller = loader.getController();
            controller.setKartenController(this);

            menuePane.getChildren().setAll(pane);
            menuePane.setVisible(true);
            hintergrundPane.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeGebaeude() {
        menuePane.setVisible(false);
        hintergrundPane.setVisible(false);
        menuePane.getChildren().clear();
    }


}
