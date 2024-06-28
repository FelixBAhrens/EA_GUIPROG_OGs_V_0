package control;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.GameFile;
import res.Konstanten;
import res.Strings;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class KartenController extends ControllerController implements Initializable {
    @FXML
    private AnchorPane map;
    @FXML
    private AnchorPane scene;
    @FXML
    private Rectangle wood;
    @FXML
    private Rectangle gold;
    @FXML
    private Rectangle health;
    @FXML
    private Rectangle missionStarter1;
    @FXML
    private Rectangle missionStarter2;
    @FXML
    private Rectangle shape1;
    @FXML
    private Pane menuePane;
    @FXML
    private Pane missionStartenPane;
    @FXML
    private Pane hintergrundPane;
    @FXML
    private TextField gesammelteObjekte;
    @FXML
    private TextField missionTimer;

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanProperty ePressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(ePressed);
    private BooleanBinding movingHorizontally = wPressed.or(sPressed);
    private BooleanBinding movingVertically = aPressed.or(dPressed);
    private boolean onMissionStarter = false;

    private static final double ROOT_2 = Math.sqrt(Konstanten.INT_TWO);

    private List<Rectangle> barriers = new ArrayList<>();

    private int woodCount = GameFile.getInstance().getHolzRessource();
    private int healthCount = GameFile.getInstance().getGesundheitRessource();
    private int goldCount = GameFile.getInstance().getGoldRessource();
    private int movementVariable = Konstanten.INT_TWO;
    private int timeRemaining = Konstanten.INT_NINETY;

    private PaneController paneController = new PaneController();
    private HauptquartierController hauptquartierController;
    private boolean missionStatus;

    //--------------------------------------------------------------------------


    public KartenController ()
    {
        this.hauptquartierController = HauptquartierController.getInstance();
        this.missionStatus = hauptquartierController.istKarteFuerMission();
    }

    private final AnimationTimer timer = new AnimationTimer()
    {
        @Override
        public void handle(long timestamp)
        {
            double moveX = Konstanten.INT_ZERO;
            double moveY = Konstanten.INT_ZERO;

            if (wPressed.get())
            {
                moveY = handleMovement(shape1.getLayoutX(), shape1.getLayoutY() - movementVariable, moveY, -movementVariable);
            }
            if (aPressed.get())
            {
                moveX = handleMovement(shape1.getLayoutX() - movementVariable, shape1.getLayoutY(), moveX, -movementVariable);
            }
            if (sPressed.get())
            {
                moveY = handleMovement(shape1.getLayoutX(), shape1.getLayoutY() + movementVariable, moveY, movementVariable);
            }
            if (dPressed.get())
            {
                moveX = handleMovement(shape1.getLayoutX() + movementVariable, shape1.getLayoutY(), moveX, movementVariable);
            }

            if (movingHorizontally.get() && movingVertically.get())
            {
                moveX /= ROOT_2;
                moveY /= ROOT_2;
            }

            updateShapePosition(moveX, moveY);
            checkForResourceCollection();
            checkForMissionStarterCollision();
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println(missionStatus);
        if (missionStatus)
        {
            healthCount = Konstanten.INT_ZERO;

            missionTimer.setVisible(true);
            startCountdown();
            gesammelteObjekte.setText(Strings.GESUNDHEIT_SPACE + healthCount);

            wood.setVisible(false);
            gold.setVisible(false);
        }
        else
        {
            gesammelteObjekte.setText(Strings.HOLZ_SPACE + GameFile.getInstance().getHolzRessource() + Strings.GESUNDHEIT_SPACE_KOMMA + GameFile.getInstance().getGesundheitRessource() + Strings.GOLD_SPACE_KOMMA + GameFile.getInstance().getGoldRessource());
        }
        setupMovement();

        keyPressed.addListener((observableValue, oldValue, newValue) ->
        {
            if (newValue) {
                timer.start();
            } else {
                timer.stop();
            }
        });

        addBarriers();
        map.requestFocus();
        checkForCollections();

        startSaving();
    }

    private void startCountdown ()
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(Konstanten.INT_ONE), event ->
        {
            timeRemaining--;
            missionTimer.setText(formatTime(timeRemaining));
            if (timeRemaining <= Konstanten.INT_ZERO)
            {
                ((Timeline) event.getSource()).stop();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private String formatTime (int seconds)
    {
        int minutes = seconds / Konstanten.INT_SIXTY;
        int secs = seconds % Konstanten.INT_SIXTY;
        return String.format(Strings.FORMAT_TIME, minutes, secs);
    }

    private void setupMovement()
    {
        scene.setOnKeyPressed(e -> setMovementKeys(e.getCode(), true));
        scene.setOnKeyReleased(e -> setMovementKeys(e.getCode(), false));
    }

    private void setMovementKeys(KeyCode code, boolean pressed)
    {
        switch (code)
        {
            case W -> wPressed.set(pressed);
            case A -> aPressed.set(pressed);
            case S -> sPressed.set(pressed);
            case D -> dPressed.set(pressed);
            case E -> ePressed.set(pressed);
        }
    }

    private void checkForMissionStarterCollision()
    {
        boolean intersects1 = shape1.getBoundsInParent().intersects(missionStarter1.getBoundsInParent());
        boolean intersects2 = shape1.getBoundsInParent().intersects(missionStarter2.getBoundsInParent());

        if (intersects1 || intersects2)
        {
            if (!onMissionStarter)
            {
                onMissionStarter = true;
                loadFXMLIntoPane(missionStartenPane, Strings.FXML_MISSION_STARTEN);
            }
        }
        else
        {
            if (onMissionStarter)
            {
                onMissionStarter = false;
                missionStartenPane.getChildren().clear();
            }
        }
    }

    private void addBarriers()
    {
        for (Node node : map.getChildren())
        {
            if (node instanceof Rectangle rectangle && !node.equals(shape1) && !node.equals(gold) && !node.equals(wood) && !node.equals(health) && !node.equals(missionStarter1) && !node.equals(missionStarter2))
            {
                barriers.add(rectangle);
            }
        }
    }

    private void loadFXMLIntoPane(Pane pane, String fxmlFile)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane newPane = loader.load();
            pane.getChildren().setAll(newPane);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private double handleMovement(double x, double y, double move, double movementVariable)
    {
        if (!checkCollisionWithBarriers(x, y, shape1))
        {
            move += movementVariable;
        }
        return move;
    }

    private void updateShapePosition(double moveX, double moveY)
    {
        shape1.setLayoutX(shape1.getLayoutX() + moveX);
        shape1.setLayoutY(shape1.getLayoutY() + moveY);
    }

    private void checkForResourceCollection()
    {
        if (checkResourceCollection(shape1.getBoundsInParent().intersects(wood.getBoundsInParent()), wood, "Holz", () -> woodCount++, () -> {
            try
            {
                GameFile.getInstance().setHolzRessource(GameFile.getInstance().getHolzRessource() + Konstanten.INT_ONE);
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }))
        {
            return;
        }

        if (checkResourceCollection(shape1.getBoundsInParent().intersects(health.getBoundsInParent()), health, Strings.GESUNDHEIT, () -> healthCount++, () ->
        {
            try
            {
                GameFile.getInstance().setGesundheitRessource(GameFile.getInstance().getGesundheitRessource() + Konstanten.INT_ONE);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }))
        {
            return;
        }

        checkResourceCollection(shape1.getBoundsInParent().intersects(gold.getBoundsInParent()), gold, Strings.GOLD, () -> goldCount++, () ->
        {
            try
            {
                GameFile.getInstance().setGoldRessource(GameFile.getInstance().getGoldRessource() + Konstanten.INT_ONE);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        });

    }
    private boolean checkResourceCollection(boolean intersects, Rectangle resource, String resourceName, Runnable incrementCount, Runnable incrementResource)
    {
        if (intersects && ePressed.get())
        {
            incrementResource(resourceName, incrementCount, incrementResource);
            placeRandomlyWithinMap(resource);
            ePressed.set(false);
            return true;
        }
        return false;
    }

    private void incrementResource(String resourceName, Runnable incrementCount, Runnable incrementResource)
    {
        try
        {
            incrementCount.run();
            incrementResource.run();
            if (missionStatus == true)
            {
                gesammelteObjekte.setText(String.format(Strings.GESUNDHEIT_PERCENT_D, healthCount));
            }
            else
            {
                gesammelteObjekte.setText(String.format(Strings.RESOURCES_PERCENTS_DS, woodCount, healthCount, goldCount));
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
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
        double paneWidth = map.getPrefWidth();
        double paneHeight = map.getPrefHeight();

        double randomX;
        double randomY;
        boolean intersects;

        do
        {
            randomX = random.nextDouble() * (paneWidth - object.getWidth());
            randomY = random.nextDouble() * (paneHeight - object.getHeight());
            double finalRandomY = randomY;
            double finalRandomX = randomX;
            intersects = barriers.stream().anyMatch(barrier -> barrier.getBoundsInParent().intersects(finalRandomX, finalRandomY, object.getWidth(), object.getHeight()));
        }
        while (intersects);

        object.setLayoutX(randomX);
        object.setLayoutY(randomY);
    }

    private boolean checkCollisionWithBarriers(double x, double y, Rectangle movingRectangle)
    {
        return barriers.stream().anyMatch(barrier -> barrier.getBoundsInParent().intersects(x, y, movingRectangle.getWidth(), movingRectangle.getHeight()));
    }

    @FXML
    public void handlezurueck()
    {
        stopSaving();
        SceneManager.goBack();
    }
}
