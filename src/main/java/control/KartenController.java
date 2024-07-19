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
import javafx.scene.control.Label;
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

/**
 * Die Klasse KartenController bildet die Controllerklasse fuer die "karteNew-view.fxml". In ihr befinden sich die Methoden
 *  zum Darstellen der Karte, zum Verwenden von Nutzereingaben und zur generellen Kartenlogik.
 * @Author David Kien, Felix Ahrens
 */
public class KartenController extends ControllerController implements Initializable
{
    /**
     * Enum fuer den Kartentyp. Wird verwendet, dem Controller die Art der Karte mitzuteilen, bevor die FXML-Datei
     *  geladen wird.
     * @Author Felix Ahrens
     */
    public enum KartenTyp
    {
        STANDARD_KARTE(Strings.KARTE_STANDARD),
        SAMMELN_MISSION(Strings.MISSION_SAMMELN);
        private final String beschreibung;

        KartenTyp (String beschreibung)
        {
            this.beschreibung = beschreibung;
        }
    }

    public static KartenTyp kartenTyp;

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
    @FXML
    public AnchorPane startenDialog;
    @FXML
    public Label detailTextLabel;

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

    private int woodCount = GameFile.getInstanz().getHolzRessource();
    private int healthCount = GameFile.getInstanz().getGesundheitRessource();
    private int goldCount = GameFile.getInstanz().getGoldRessource();
    private int movementVariable = Konstanten.INT_TWO;
    private int timeRemaining = Konstanten.INT_NINETY;

    private HauptquartierController hauptquartierController;
    private boolean missionStatus;

    //--------------------------------------------------------------------------


    /**
     * Methode zum Animieren des Spielers auf der Karte.
     * @Author David Kien
     */
    private final AnimationTimer timer = new AnimationTimer()
    {
        /**
         * Handle-Methode
         * @param timestamp
         * @Author David Kien
         */
        @Override
        public void handle (long timestamp)
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
                moveX /= Math.sqrt(Konstanten.INT_TWO);
                moveY /= Math.sqrt(Konstanten.INT_TWO);
            }

            updateShapePosition(moveX, moveY);
            checkForResourceCollection();
            checkForMissionStarterCollision();
        }
    };

    /**
     * Ueberschriebene Initialize-Methode. Ist verpflichtend fuer Controllerklassen von FXML-Dateien
     * @pre Das verwendete GUI-Element und die Methode muessen erreichbar sein.
     * @post Der "startenDialog" wird initialisiert und visible gesetzt
     * @param url /
     * @param resourceBundle /
     * @Author David Kien, Felix Ahrens
     */
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        initialisiereDialog();
        startenDialog.setVisible(true);
    }

    /**
     * Methode zum Starten der Utility fuer die Mission in der Karte
     * @pre
     * @post
     * @Author David Kien
     */
    public void starteMissionsUtil () {
        setupMovement();

        keyPressed.addListener((observableValue, oldValue, newValue) ->
        {
            if (newValue)
            {
                timer.start();
            } else
            {
                timer.stop();
            }
        });

        addBarriers();
        map.requestFocus();
        checkForCollections();

        startSaving();
    }

    /**
     * Methode zum I des StartDialogs. Der angezeigte Text ist abhaengig vom gesetzten Enum.
     * @pre Das Enum muss auf einen der Werte der Cases gesetzt sein. Die verwendeten GUI-Elemente, Methoden und Konstanten muessen existieren und erreichbar sein.
     * @post Der "detailTextLabel"-Dialog wurde auf einen im Kontext sinnigen Text gesetzt.
     * @Author Felix Ahrens
     */
    public void initialisiereDialog(){
        detailTextLabel.setText(switch (kartenTyp){
            case SAMMELN_MISSION -> Strings.TEXT_SAMMELN + Strings.NEWLINE + Strings.SAMMELN_MISSION_BESCHREIBUNG;
            case STANDARD_KARTE -> Strings.KARTE_BESCHREIBUNG;
        });
    }

    /**
     * Methode zum Starten der "sammelnMission". Diese manipuliert die GUI-Elemente so, dass sie in der Mission sinnig verwendet werden koennen.
     *  Utility, wie der "missionTimer" wird sichtbar geschaltet.
     * @pre Die GUI-Elemente muessen existieren und von der Methode manipulierbar sein. Die verwendeten Methoden und Konstanten muessen erreichbar sein.
     * @post Die GUI fuer die "sammelnMission" wurde sinnvoll gesetzt.
     * @Author David Kien, Felix Ahrens
     */
    public void starteSammelnMission () {
        startenDialog.setVisible(false);
        healthCount = Konstanten.INT_ZERO;
        missionTimer.setVisible(true);
        startCountdown();
        gesammelteObjekte.setText(Strings.GESUNDHEIT_SPACE + healthCount);
        wood.setVisible(false);
        gold.setVisible(false);
    }

    /**
     * Methode zum Starten der Standardkarte, wie sie aus der Stadt zum einfachen Sammeln von Ressourcen etc aufgerufen wird.
     *  Die Methode initialisiert die GUI zum Anzeigen der gesammelten Objekte.
     * @pre Das GUI-Element, die Methoden und die Konstanten muessen erreichbar sein. Die Singleton-Instanz der GameFile muss gesetzt sein.
     * @post Das GUI-Element "gesammelteObjekte" zeigt die in der Karte gesammelten Ressourcen an.
     * @Author Felix Ahrens
     */
    public void starteStandardKarte () {
        gesammelteObjekte.setText(Strings.HOLZ_SPACE + GameFile.getInstanz().getHolzRessource()
                + Strings.GESUNDHEIT_SPACE_KOMMA + GameFile.getInstanz().getGesundheitRessource()
                + Strings.GOLD_SPACE_KOMMA + GameFile.getInstanz().getGoldRessource());
    }

    /**
     * Methode, die das Fortfahren handled. Diese wird vom Button "detailFortfahrenButton" aufgerufen und ruft die Methoden
     *  zum Starten des spezifischen Kartentyps auf.
     * @pre Der "startenDialog" muss existieren. Das Enum "kartenTyp" muss gesetzt sein und einem der beiden Cases entsprechen.
     *  Die verwendeten Methoden muessen existieren.
     * @post Die Kartenmethode wurde gestartet.
     * @Author Felix Ahrens
     */
    @FXML
    public void handleFortfahren (){
        startenDialog.setVisible(false);
        switch (kartenTyp){
            case SAMMELN_MISSION -> starteSammelnMission();
            case STANDARD_KARTE -> starteStandardKarte();
        }
        starteMissionsUtil();
    }

    /**
     * Methode zum Starten des Countdowns
     * @Author David Kien
     */
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

    //@Author David Kien
    private String formatTime (int seconds)
    {
        int minutes = seconds / Konstanten.INT_SIXTY;
        int secs = seconds % Konstanten.INT_SIXTY;
        return String.format(Strings.FORMAT_TIME, minutes, secs);
    }

    //@Author David Kien
    private void setupMovement ()
    {
        scene.setOnKeyPressed(e -> setMovementKeys(e.getCode(), true));
        scene.setOnKeyReleased(e -> setMovementKeys(e.getCode(), false));
    }

    //@Author David Kien
    private void setMovementKeys (KeyCode code, boolean pressed)
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

    //@Author David Kien
    private void checkForMissionStarterCollision ()
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
        } else
        {
            if (onMissionStarter)
            {
                onMissionStarter = false;
                missionStartenPane.getChildren().clear();
            }
        }
    }

    //@Author David Kien
    private void addBarriers ()
    {
        for (Node node : map.getChildren())
        {
            if (node instanceof Rectangle rectangle && !node.equals(shape1) && !node.equals(gold) && !node.equals(wood) && !node.equals(health) && !node.equals(missionStarter1) && !node.equals(missionStarter2))
            {
                barriers.add(rectangle);
            }
        }
    }

    //@Author David Kien
    private void loadFXMLIntoPane (Pane pane, String fxmlFile)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane newPane = loader.load();
            pane.getChildren().setAll(newPane);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //@Author David Kien
    private double handleMovement (double x, double y, double move, double movementVariable)
    {
        if (!checkCollisionWithBarriers(x, y, shape1))
        {
            move += movementVariable;
        }
        return move;
    }

    //@Author David Kien
    private void updateShapePosition (double moveX, double moveY)
    {
        shape1.setLayoutX(shape1.getLayoutX() + moveX);
        shape1.setLayoutY(shape1.getLayoutY() + moveY);
    }

    //@Author David Kien
    private void checkForResourceCollection ()
    {
        if (checkResourceCollection(shape1.getBoundsInParent().intersects(wood.getBoundsInParent()), wood, "Holz", () -> woodCount++, () -> {
            try
            {
                GameFile.getInstanz().setHolzRessource(GameFile.getInstanz().getHolzRessource() + Konstanten.INT_ONE);
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
                GameFile.getInstanz().setGesundheitRessource(GameFile.getInstanz().getGesundheitRessource() + Konstanten.INT_ONE);
            } catch (Exception e)
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
                GameFile.getInstanz().setGoldRessource(GameFile.getInstanz().getGoldRessource() + Konstanten.INT_ONE);
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        });

    }

    //@Author David Kien
    private boolean checkResourceCollection (boolean intersects, Rectangle resource, String resourceName, Runnable incrementCount, Runnable incrementResource)
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

    //@Author David Kien
    private void incrementResource (String resourceName, Runnable incrementCount, Runnable incrementResource)
    {
        try
        {
            incrementCount.run();
            incrementResource.run();
            if (missionStatus == true)
            {
                gesammelteObjekte.setText(String.format(Strings.GESUNDHEIT_PERCENT_D, healthCount));
            } else
            {
                gesammelteObjekte.setText(String.format(Strings.RESOURCES_PERCENTS_DS, woodCount, healthCount, goldCount));
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    //@Author David Kien
    private void checkForCollections ()
    {
        placeRandomlyWithinMap(wood);
        placeRandomlyWithinMap(health);
        placeRandomlyWithinMap(gold);
    }

    //@Author David Kien
    private void placeRandomlyWithinMap (Rectangle object)
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

    //@Author David Kien
    private boolean checkCollisionWithBarriers (double x, double y, Rectangle movingRectangle)
    {
        return barriers.stream().anyMatch(barrier -> barrier.getBoundsInParent().intersects(x, y, movingRectangle.getWidth(), movingRectangle.getHeight()));
    }

    /**
     * Ueberschriebene Methode zum Verwenden der Zurueck-Funktionalitaet.
     *  Diese ruft zusaetzlich die Methode "stopSaving" auf, um das Speichern zu beenden.
     * @pre Die verwendeten Methoden muessen erreichbar sein
     * @post Es wurde eine Szene zurueckgegangen und das Speichern gestoppt.
     * @Author David Kien, Felix Ahrens
     */
    @Override
    public void handleZurueck ()
    {
        stopSaving();
        SzenenManager.szeneZurueck();
    }
}
