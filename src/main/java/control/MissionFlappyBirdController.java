package control;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Bird;
import res.Konstanten;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MissionFlappyBirdController extends ControllerController implements Initializable
{
    AnimationTimer gameLoop;

    @FXML
    private AnchorPane plane;

    @FXML
    private Rectangle bird;

    @FXML
    private Text score;

    private double accelerationTime = Konstanten.INT_ZERO;
    private int gameTime = Konstanten.INT_ZERO;
    private int scoreCounter = Konstanten.INT_ZERO;

    private Bird birdComponent;
    private ObstaclesHandler obstaclesHandler;

    ArrayList<Rectangle> obstacles = new ArrayList<>();


    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        Rectangle clip = new Rectangle(plane.getPrefWidth(), plane.getPrefHeight());
        plane.setClip(clip);

        int jumpHeight = Konstanten.INT_FOURTYFIVE;
        birdComponent = new Bird(bird, jumpHeight);
        double planeHeight = Konstanten.INT_SIX_HUNDRED;
        double planeWidth = Konstanten.INT_FOUR_HUNDRED;
        obstaclesHandler = new ObstaclesHandler(plane, planeHeight, planeWidth);

        gameLoop = new AnimationTimer()
        {
            @Override
            public void handle (long l)
            {
                update();
            }
        };

        load();

        gameLoop.start();
    }

    @FXML
    void pressed (KeyEvent event)
    {
        if (event.getCode() == KeyCode.SPACE)
        {
            birdComponent.fly();
            accelerationTime = Konstanten.INT_ZERO;
        }
    }


    //Called every game frame
    private void update ()
    {
        gameTime++;
        accelerationTime++;
        double yDelta = Konstanten.ZERO_POINT_ZERO_TWO;
        birdComponent.moveBirdY(yDelta * accelerationTime);

        if (pointChecker(obstacles, bird))
        {
            scoreCounter++;
            score.setText(String.valueOf(scoreCounter));
        }

        obstaclesHandler.moveObstacles(obstacles);

        if (gameTime % Konstanten.INT_FIVE_HUNDRED == Konstanten.INT_ZERO)
        {
            obstacles.addAll(obstaclesHandler.createObstacles());
        }

        if (birdComponent.isBirdDead(obstacles, plane))
        {
            resetGame();
        }
    }

    //Everything called once, at the game start
    private void load ()
    {
        obstacles.addAll(obstaclesHandler.createObstacles());
    }

    private void resetGame ()
    {
        bird.setY(Konstanten.INT_ZERO);
        plane.getChildren().removeAll(obstacles);
        obstacles.clear();
        gameTime = Konstanten.INT_ZERO;
        accelerationTime = Konstanten.INT_ZERO;
        scoreCounter = Konstanten.INT_ZERO;
        score.setText(String.valueOf(scoreCounter));
    }


    private boolean pointChecker (ArrayList<Rectangle> obstacles, Rectangle bird)
    {
        for (Rectangle obstacle : obstacles)
        {
            int birdPositionX = (int) (bird.getLayoutX() + bird.getX());
            if (((int) (obstacle.getLayoutX() + obstacle.getX()) == birdPositionX))
            {
                return true;
            }
        }
        return false;
    }
}